// Query string tren url (id)
const URL = "/admin/order/"
const params = window.location.pathname;
const paramId = params.replace(URL, "");

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

// ============= SHIPPING ADDRESS =============
let shipObj;

// ============= RENDER =============

// 1. Lấy thông tin nhận hàng
const shippingInfo = document.getElementById("shipping-info");
const getShippingAPI = () => {
    return axios.get(`/api/v1/order/${paramId}/shipping`);
}

// 2. Hiển thị thông tin nhận hàng

const getShipping = async () => {
    try {
        let res = await getShippingAPI();
        shipObj = res.data;
        console.log("successful");
        renderShipping(shipObj);
    } catch (e) {
        console.log(e.response.data.message)
    }
}

// Hiển thị tại đơn hàng
const renderShipping = (obj) => {
    shippingInfo.innerHTML = `
        <div class="col-6">
            <div class="row">
                <div class="col-6 py-1">
                    <label>Tên người nhận:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.name}
                </div>
                <div class="col-6 py-1">
                    <label>Email:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.email}
                </div>
                <div class="col-6 py-1">
                    <label>Số điện thoại:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.phone}
                </div>
            </div>
        </div>
        <div class="col-6">
            <div class="row">
                <div class="col-6 py-1">
                    <label>Tỉnh/Thành phố:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.city}
                </div>
                <div class="col-6 py-1">
                    <label>Địa chỉ:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.address}
                </div>
            </div>
        </div>                             
    `
}

// Hiển thị tại modal update
const updateNameInput = document.getElementById("updateName");
const updatePhoneInput = document.getElementById("updatePhone");
const updateEmailInput = document.getElementById("updateEmail");
const updateAddressInput = document.getElementById("updateAddress");
const updateShippngModal = document.getElementById("modal-update-shipping");

const renderShippingModal = (obj) => {
    let provinceOptionEl = provinceEl.querySelectorAll("option");
    updateNameInput.value = obj.name;
    updatePhoneInput.value = obj.phone;
    updateEmailInput.value = obj.email;
    updateAddressInput.value = obj.address;

    provinceOptionEl.forEach(el => {
        if(el.dataset.name === obj.city){
            el.selected = true;
        }
    });
}

getShipping();

// ============= UPDATE =============
const updateShippingBtn = document.getElementById("btn-update-shipping");

updateShippingBtn.addEventListener("click", async () => {
    try {
        let res = await axios.put(`/api/v1/order/${paramId}/shipping`,
            {
                name: updateNameInput.value,
                phone: updatePhoneInput.value,
                email: updateEmailInput.value,
                address: updateAddressInput.value,
                city: getMyProvince()
            })
        console.log("successful");
        shipObj = res.data
        renderShipping(shipObj);
    } catch (e){
        console.log(e.response.data.message)
    }
})

// SHIPPING ADDRESS
const openModalUpdateBtn = document.getElementById("update-shipping");

openModalUpdateBtn.addEventListener("click", () => {
    renderShippingModal(shipObj);
})

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
        html += `<option value=${p.code} data-name="${p.name}">${p.name}</option>`;
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
        if (el.selected) {
            pCode = +el.value;
            pValue = provinceArr.find(proEl => proEl.code === pCode);
        }
    })
    if (pValue === undefined) {
        return null;
    }
    return pValue.name;
}

getProvince();

// ============= CUSTOMER =============
const customerInfo = document.getElementById("customer-info");

// 1. Lấy thông tin khách hàng và hiển thị
const getCustomerAPI = () => {
    return axios.get(`/api/v1/order/${paramId}/customer`);
}

const getCustomer = async () => {
    try {
        let res = await getCustomerAPI();
        console.log("successful");
        renderCustomer(res.data);
    } catch (e) {
        console.log(e.response.data.message);
    }
}

getCustomer();

// 2. Hiển thị tại đơn hàng
const renderCustomer = (obj) => {
    customerInfo.innerHTML = `
        <div class="col-6">
            <div class="row">
                <div class="col-6 py-1">
                    <label>Tên khách hàng:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.name}
                </div>
                <div class="col-6 py-1">
                    <label>Email:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.email}
                </div>
                <div class="col-6 py-1">
                    <label>Số điện thoại:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.phone}
                </div>
            </div>
        </div>
        <div class="col-6">
            <div class="row">
                <div class="col-6 py-1">
                    <label>Tỉnh/Thành phố:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.city}
                </div>
                <div class="col-6 py-1">
                    <label>Địa chỉ:</label>
                </div>
                <div class="col-6 py-1">
                    ${obj.address}
                </div>
            </div>
        </div>                             
    `
}

// ============= ORDER ITEMS =============
const itemList = document.getElementById("list-items")

// 1. Lấy thông tin
const getOrderItems = async () => {
    try {
        let items = await axios.get(`/api/v1/order/${paramId}/order-items`);
        renderOrderItem(items.data);
        console.log("successful")
    } catch (e){
        console.log(e.response.data.message);
    }
}

getOrderItems();


// 2. Hiển thị tại đơn hàng
const renderOrderItem = (arr) => {
    let html = "";
    arr.forEach(el => {
        html += `
            <tr id="${el.id}" class="line_item">
                <td class="line_product" data-vari="${el.variantId}">
                <p style="margin-bottom: 0px">${el.productName} (${el.variantColor} - ${el.variantSize})</p>
                <p style="font-size: 14px"><i>SKU: ${el.variantSku}</i></p>
                </td>
                <td class ="line_qty" data-qty="${el.quantity}">${el.quantity}
                </td>
                <td class="line_price" data-price="${el.price}">${formatVND(el.price)}</td>
                <td class="line_total">${formatVND(el.total)}</td>
            </tr>
            `
    })
    itemList.innerHTML = html;
}

// ============= ORDER =============
// ============= RENDER =============
const noteEl = document.getElementById("note");
const statusEl = document.getElementById("select-status")
const paymentEl = document.getElementById("select-payment")
const fulfillmentEl = document.getElementById("select-fulfillment")
const totalEl = document.getElementById("order-total")

// 1. Lấy thông tin
const getOrder = async () => {
    try {
        let order = await axios.get(`/api/v1/order/${paramId}`);
        renderOrder(order.data);
        console.log("successful")
    } catch (e){
        console.log(e.response.data.message);
    }
}

getOrder();

// 2. Hiển thị thông tin
const renderOrder = (obj) => {
    noteEl.value = obj.note;
    renderSelect(statusEl, obj.status);
    renderSelect(paymentEl, obj.payment);
    renderSelect(fulfillmentEl, obj.fulfillment);
    totalEl.innerHTML = `<div>${formatVND(obj.total)}</div>`

}

// 3. Hiển thị trạng thái
const renderSelect = (obj, result) => {
    let optionEl = obj.querySelectorAll("option");
    optionEl.forEach(el => {
        if(el.value === result){
            el.selected = true;
        }
    })
}

// ============= UPDATE =============

// 1. Lấy trạng thái được update
const updateSelect = (obj) => {
    let result;
    let optionEl = obj.querySelectorAll("option");
    optionEl.forEach(el => {
        if(el.selected){
            result = el.value;
        }
    })
    return result;
}

// 2. Cập nhật đơn hàng
const updateOrderBtn = document.getElementById("btn-update-order");

updateOrderBtn.addEventListener("click", async () => {
    try {
        await axios.put(`/api/v1/order/${paramId}`,
            {
                note: noteEl.value,
                status: updateSelect(statusEl),
                payment: updateSelect(paymentEl),
                fulfillment: updateSelect(fulfillmentEl)
            })
            console.log("successful");
            getOrder();
    } catch (e) {
        console.log(e.response.data.message)
    }
})