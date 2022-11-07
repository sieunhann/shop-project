$(document).ready(() => {
    console.log(localStorage.getItem("cart_id"))
    getProvinces();
    getCartInfo();
    $("#create-order").on("click", function (event){
        event.preventDefault();
        const inputEles = document.querySelectorAll('.input-row');
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );

        let isCheck = checkShippingAddress()
        if(isCheck){
            createOrder();
        }
    })
})

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

// =========== CITY ===========

const provinceElement = document.querySelector("#city");
let provinceArray = [];

// Lay danh sach tỉnh thành phố
const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/")
        console.log(res);

        renderProvinces(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Hien thi thanh pho
const renderProvinces = arr => {
    provinceElement.innerHTML = "";

    let html = "<option hidden>--Select Province</option>";
    arr.forEach(p => {
        provinceArray.push({code: p.code, name: p.name});
        html += `<option value=${p.code}>${p.name}</option>`;
    });
    provinceElement.innerHTML = html;
}

provinceElement.addEventListener("change", () => {
    let provinceCode = provinceElement.value;
    console.log(provinceCode)
})

const getMyProvinces = () => {
    let pCode;
    let pValue;
    let provinceOptionEl = provinceElement.querySelectorAll("option");
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

// ============= Hiển thị thông tin sp trong giỏ hàng ===========
let cartDto;

function getCartInfo(){
    let cart_id = localStorage.getItem("cart_id");
    $.ajax({
        url: `/api/v1/shop/cart/${cart_id}`,
        type: "GET",
        dataType: "json",
        async: true,
        success: function (res){
            cartDto = res;
            renderCartItemInfo(res.items)
            console.log(res);
        },
        error: function (e){
            console.log(e);
        }
    })
}

function renderCartItemInfo(arr){
    let html = "";
    arr.forEach(obj => {
        let total = (obj.variant.price) * (obj.quantity);
        html += `
        <tr>
            <td class="cart__product__item">
                <div class="row py-3">
                    <div class="col-3">
                        <img class="cart__product__img" src="/api/v1/product/images/${obj.imageUrl}" alt="">
                    </div>
                    <div class="col-8">
                        <div class="cart__product__item__title">
                            <h6>${obj.productName}</h6>
                       <div class="variant__item__sku pt-2">
                            <i>Sku: ${obj.variant.sku}</i>
                       </div>
                       <div class="variant__item__properties pt-2">
                            <i>Thuộc tính: ${obj.variant.color}/${obj.variant.size}</i>
                       </div>
                   </div>
                </div>
           </td>
           <td class="cart__total" data-total="${total}">${formatVND(total)}</td>
        </tr>`
    })
    $(".checkout__cart__items").html(html);
    renderCartTotal();
}

function renderCartTotal(){
    let res = 0;
    const itemTotalEl = document.querySelectorAll(".cart__total");
    itemTotalEl.forEach(el => res += +el.dataset.total)
    $(".cart__price_total").attr("data-total", res).find("span").html(formatVND(res));
}

function getShippingAddress(){
    return {
        name: $("#name").val(),
        phone: $("#phone").val(),
        email: $("#email").val(),
        address: $("#address").val(),
        city: getMyProvinces()
    };
}

function getOrderItems(){
    let items = cartDto.items;
    let orderItems = [];

    items.forEach(el => {
        let obj = {
            productId: el.productId,
            productName: el.productName,
            variantId: el.variant.id,
            variantSku: el.variant.sku,
            variantColor: el.variant.color,
            variantSize: el.variant.size,
            price: el.variant.price,
            quantity: el.quantity,
            total: el.total
        }
        orderItems.push(obj);
    })
    return orderItems;
}

function createOrder(){
    $.ajax({
        url: `/api/v1/shop/checkout`,
        type: "POST",
        dataType: "json",
        contentType: "application/json",
        async: true,
        data: JSON.stringify({
            note: cartDto.note,
            total: $(".cart__price_total").data("total"),
            orderItems: getOrderItems(),
            shippingAddress: getShippingAddress()
        }),
        success: function (res) {
            localStorage.removeItem("cart_id");
            toastr.success("Đơn hàng được tạo thành công");
        },
        error: function (e) {
            toastr.error("Đã xảy ra lỗi, vui lòng thử lại");
            console.log(e);
        }

    })
}

// Kiểm tra thông tin
// Validate form
// Validate dữ liệu trong các ô input và highlight
function checkShippingAddress() {
    const nameRequest = document.getElementById("name")
    const phoneRequest = document.getElementById("phone")
    const addressRequest = document.getElementById("address")
    const cityRequest = document.getElementById("city")

    let nameValue = nameRequest.value;
    let phoneValue = phoneRequest.value;
    let addressValue = addressRequest.value;

    let isCheck = true;

    // Kiểm tra trường name
    if (nameValue === '') {
        setError(nameRequest, 'Tên không được để trống');
        isCheck = false;
    } else {
        setSuccess(nameRequest);
    }

    // Kiểm tra trường phone
    if (phoneValue === '') {
        setError(phoneRequest, 'Số điện thoại không được để trống');
        isCheck = false;
    } else if (!isPhone(phoneValue)) {
        setError(phoneRequest, 'Số điện thoại không đúng định dạng');
        isCheck = false;
    } else {
        setSuccess(phoneRequest);
    }

    // Kiểm tra địa chỉ
    if (addressValue === '') {
        setError(addressRequest, 'Địa chỉ không được để trống');
        isCheck = false;
    } else {
        setSuccess(addressRequest);
    }

    if(getMyProvinces() === null){
        setError(cityRequest, 'Thành phố không được để trống');
        isCheck = false;
    } else {
        setSuccess(cityRequest);
    }
    return isCheck;
}
