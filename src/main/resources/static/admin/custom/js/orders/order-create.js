const searchProductBtn = document.getElementById("search-product-btn")
const searchProductInput = document.getElementById("search-product-input")
const productSelectList = document.getElementById("list-product-chose")
const itemList = document.getElementById("list-items")

let varArr = []

// Tìm kiếm sp trong modal
searchProductBtn.addEventListener("click", async () => {
    try {
        let queryVal = searchProductInput.value;
        let vars = await axios.get(`/api/v1/orders/variants?query=${queryVal}`);
        varArr = vars.data;
        console.log(varArr)

        renderVariant(varArr);
    } catch (error) {
        console.log(error.response.data.message);
    }
})

// Hiển thị danh sách sp trong modal
const renderVariant = (arr) => {
    productSelectList.innerHTML = "";
    let html = "";

    arr.forEach(el => {
        html += `
        <tr id="chose-${el.varId}">
            <td>${el.proName}</td>
            <td>${el.varSku}</td>
            <td>${el.varColor} - ${el.varSize}</td>
            <td>${el.varQuantity}</td>
            <td id="chose-btn-${el.varId}">
                <a href="javascript:void(0)" onclick="selectProduct(${el.varId})">Chọn</a>
            </td>
        </tr>`
    });
    productSelectList.innerHTML = html;
}

// Hiển thị sp được lựa chọn
const selectProduct = (ids) => {
    let varEl = varArr.find((el) => {
        return el.varId === ids;
    })
    html = itemList.innerHTML;
    html += `
    <tr id="${varEl.varId}" class="line_item">
        <td class="line_product" data-pro="${varEl.proId}" data-vari="${varEl.varId}">
        <p style="margin-bottom: 0px">${varEl.proName} (${varEl.varColor} - ${varEl.varSize})</p>
        <p style="font-size: 14px"><i>SKU: ${varEl.varSku}</i></p>
        </td>
        <td>
            <input type="number" class="line_qty" id="quantity" min="0" max="${varEl.varQuantity}" value="0" onchange="changeNumber(this)">
        </td>
        <td class="line_price" data-price="${varEl.varPrice}">${formatVND(varEl.varPrice)}</td>
        <td class="line_total">0</td>
        <td>
            <a href="javascript:void(0)" onclick="removeProduct(${varEl.varId})">Xóa</a>
        </td>
    </tr>
    `
    itemList.innerHTML = html;
    document.getElementById(`chose-btn-${ids}`).innerHTML = "<p>Đã chọn</p>";
}

// Thay đổi tổng tiền từng sp khi thay đổi số lượng sp
function changeNumber(el){
    let qty = $(el).val();
    let price = $(el).closest(".line_item").find(".line_price").attr("data-price");
    let total = qty * price;
    $(el).closest(".line_item").find(".line_total").attr("data-total", total).html(formatVND(total));
    changeTotal();
}

// Thay đổi tổng giá trị đơn hàng
function changeTotal(){
    let toltalEl = document.querySelectorAll(".line_total");
    let orderTotal = 0;
    toltalEl.forEach(el => orderTotal += +el.dataset.total);
    let orderTotalEl = document.getElementById("order-total");
    orderTotalEl.setAttribute("data-total", `${orderTotal}`)
    orderTotalEl.innerHTML = `<div class="text-bold">${formatVND(orderTotal)}</div>`
}

// Xóa sản phẩm đã chọn trong đơn hàng
function removeProduct(ids){
    document.getElementById(ids).remove();
    changeTotal();
    document.getElementById(`chose-btn-${ids}`).innerHTML =
        `<a href="javascript:void(0)" onclick="selectProduct(${ids})">Chọn</a>`;
}


// =========== CITY ===========

const provinceEl = document.querySelector("#select-province");
let provinceArr = [];

// Lay danh sach tỉnh thành phố
const getProvince = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/")
        console.log(res);

        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Hien thi thanh pho
const renderProvince = arr => {
    provinceEl.innerHTML = "";

    let html = "<option hidden>--Select Province</option>";
    arr.forEach(p => {
        provinceArr.push({code: p.code, name: p.name});
        html += `<option value=${p.code}>${p.name}</option>`;
    });
    provinceEl.innerHTML = html;
}

provinceEl.addEventListener("change", () => {
    let provinceCode = provinceEl.value;
    console.log(provinceCode)
})

const getMyProvince = () => {
    let pCode;
    let pValue;
    let provinceOptionEl = provinceEl.querySelectorAll("option");
    provinceOptionEl.forEach(el => {
        if(el.selected){
            pCode = +el.value;
            pValue = provinceArr.find(proEl => proEl.code === pCode);
        }
    })
    if(pValue === undefined){
        return null;
    }
    return pValue.name;
}

getProvince();


// ============== TẠO ĐƠN HÀNG ==============

const createOrderBtn = document.getElementById("btn-create-order")

// 1. Tạo đơn hàng
createOrderBtn.addEventListener("click", async () => {
    const noteOrder = document.getElementById("note")
    const orderTotalEl = document.getElementById("order-total");
    let isCheck = checkValidate();

    try {
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        if(isCheck) {
            console.log(getOrderItems())
            await axios.post("/api/v1/admin/orders/create",
                {
                    note: noteOrder.value,
                    total: +orderTotalEl.dataset.total,
                    orderItems: getOrderItems(),
                    shippingAddress: getShippingAddress()
                })
            window.location.href = "/admin/orders";
        }
    } catch (e) {
        console.log(e.response.data.message)
    }
})

// 2. Lấy thông tin nhận hàng
const shippingNameEL = document.getElementById("shipping-name");
const shippingEmailEL = document.getElementById("shipping-email");
const shippingPhoneEL = document.getElementById("shipping-phone");
const shippingAddressEL = document.getElementById("shipping-address");

const getShippingAddress = () => {
    return {
        name: shippingNameEL.value,
        email: shippingEmailEL.value,
        phone: shippingPhoneEL.value,
        address: shippingAddressEL.value,
        city: getMyProvince()
    };
}

// 3. Lấy order item
const getOrderItems = () => {
    let orderItems = [];
    const listItem = document.querySelectorAll(".line_item")
    listItem.forEach(item => {
        console.log(item)
        let itemPro = item.querySelector(".line_product");
        console.log(itemPro)
        let itemQty = item.querySelector(".line_qty");
        let itemPrice = item.querySelector(".line_price");
        let itemTotal = item.querySelector(".line_total");
        let itemObj = {
            productId: +itemPro.dataset.pro,
            variantId: +itemPro.dataset.vari,
            price: +itemPrice.dataset.price,
            quantity: +itemQty.value,
            total: +itemTotal.dataset.total
        }
        orderItems.push(itemObj)
    })
    console.log(orderItems);
    return orderItems;
}

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

// 3. Kiểm tra thông tin
// Validate form
// Validate dữ liệu trong các ô input và highlight
const inputEles = document.querySelectorAll('.input-row');

function checkValidate() {
    let nameValue = shippingNameEL.value;
    let phoneValue = shippingPhoneEL.value;
    let addressValue = shippingAddressEL.value;

    let isCheck = true;

    // Kiểm tra trường name
    if (nameValue === '') {
        setError(shippingNameEL, 'Tên không được để trống');
        isCheck = false;
    } else {
        setSuccess(shippingNameEL);
    }

    // Kiểm tra trường phone
    if (phoneValue === '') {
        setError(shippingPhoneEL, 'Số điện thoại không được để trống');
        isCheck = false;
    } else if (!isPhone(phoneValue)) {
        setError(shippingPhoneEL, 'Số điện thoại không đúng định dạng');
        isCheck = false;
    } else {
        setSuccess(shippingPhoneEL);
    }

    // Kiểm tra trường địa chỉ
    if (addressValue === '') {
        setError(shippingAddressEL, 'Địa chỉ không được để trống');
        isCheck = false;
    } else {
        setSuccess(shippingAddressEL);
    }

    return isCheck;
}

// Set hiển thị highlight ô input và message error
function setError(ele, message) {
    let parentEle = ele.parentNode;
    parentEle.classList.add('error');
    parentEle.querySelector('small').innerText = message;
}

// Set hiển thị highlight ô input và message success
function setSuccess(ele) {
    ele.parentNode.classList.add('success');
}

// Kiểm tra định dạng sđt
function isPhone(number) {
    return /^(0|\+84)[1-9][0-9]{8}$/.test(number);
}