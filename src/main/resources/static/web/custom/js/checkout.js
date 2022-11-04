$(document).ready(() => {
    getProvince();
    getCartInfo();
})

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
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

// ============= Hiển thị thông tin sp trong giỏ hàng ===========

function getCartInfo(){
    let cart_id = localStorage.getItem("cart_id");
    $.ajax({
        url: `/api/v1/shop/cart/${cart_id}`,
        type: "GET",
        dataType: "json",
        async: true,
        success: function (res){
            renderCartInfo(res.items)
            console.log(res);
        },
        error: function (e){
            console.log(e);
        }
    })
}

function renderCartInfo(arr){
    let html = "";
    arr.forEach(obj => {
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
           <td class="cart__price" data-item-price="${obj.variant.price}">${formatVND(obj.variant.price)}</td>
        </tr>`
    })
    $(".checkout__cart__items").html(html);
}
