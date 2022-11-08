$(document).ready(()=>{
    $('.header__menu__items li.active').removeClass('active');

    getProduct();

    getHotProduct();

    $(".cart-btn").on("click", function () {
        addToCart();
    })
})

function getHotProduct(){
    $.ajax({
        url: `/api/v1/shop/products/new?query=15&limit=4`,
        type: "GET",
        dataType: "json",
        async: true,
        success: function (res){
            let html = `
            <div class="col-lg-12 text-center">
                <div class="related__title">
                    <h5>HOT PRODUCTS</h5>
                </div>
            </div>`
            res.forEach(el => {
                html += setHotProductItems(el)
            })
            $(".hot-products").html(html);
        },
        error: function (e){
            console.log(e)
        }
    });
}

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

// Query string tren url (id)
const URL = "/shop/products/"
const params = window.location.pathname;
const paramId = params.replace(URL, "");
let proObj;

// Lấy thông tin sản phẩm
const getProduct = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/${paramId}`);
        proObj = res.data;
        console.log(proObj)
        renderProductInfo(proObj);
    } catch (e) {
        console.log(e.response.data.message);
    }
}

const renderProductImg = (obj) => {
    const imgLeft = document.querySelector(".product__details__pic__left");
    const imgSlider = document.querySelector(".product__details__pic__slider")
    let images = obj.imageEntities;
    let leftHtml = `
        <a class="pt active" href="#product-1">
            <img src="/api/v1/products/images/${images[0].url}" alt="">
        </a>
    `
    let sliderHtml = `
         <img data-hash="product-1" class="product__big__img" 
         src="/api/v1/products/images/${images[0].url}" alt="">
    `
    if(images.length > 1){
        for(let i = 1; i < images.length; i++){
            leftHtml += `
                <a class="pt" href="#product-${i+1}">
                    <img src="/api/v1/products/images/${images[i].url}" alt="">
                </a>
            `
            sliderHtml += `
                <img data-hash="product-${i+1}" class="product__big__img" src="/api/v1/products/images/${images[i].url}" alt="">
            `
        }
    }

    imgLeft.innerHTML = leftHtml;
    imgSlider.innerHTML = sliderHtml;
}

const renderProductInfo = (obj) => {
    renderContentAndDes(obj);
    renderSize(obj);
    renderProductImg(obj);
    setImgProduct();
    $(".breadcrumb__links").find("span").html(obj.name);
}

const renderContentAndDes = (obj) => {
    const desEl = document.querySelector(".product__details__description");
    desEl.textContent = obj.description;

    const contentEl = document.querySelector("#tabs-1");
    let arr = obj.content.split("\n")

    arr.forEach(el => {
        const para = document.createElement("p");
        para.innerText = el;
        contentEl.appendChild(para)
    })
}

const renderSize = (obj) => {
    const sizeBtn = document.querySelector(".size__btn");
    let varArr = obj.variantEntities;
    let sizeArr = [];

    varArr.forEach(el => {
        if(!sizeArr.includes(el.size)){
            sizeArr.push(el.size);
        }
    })

    sizeArr.sort();

    html = `
        <label for="${sizeArr[0]}-btn" class="active" onclick="activeSize(this)" data-size="${sizeArr[0]}">
            <input type="radio" id="${sizeArr[0]}-btn">${sizeArr[0]}
        </label>
        `

    if(sizeArr.length > 1){
        for (let i = 1; i < sizeArr.length; i++){
            html += `
                <label for="${sizeArr[i]}-btn" class="" onclick="activeSize(this)" data-size="${sizeArr[i]}">
                    <input type="radio" id="${sizeArr[i]}-btn">${sizeArr[i]}
                </label>
                `
        }
    }
    sizeBtn.innerHTML = html;
    getColor();
    renderPrice();
    renderNameAndSku();
}

const activeSize = (el) => {
    const sizeEl = document.querySelectorAll(".size__btn label");
    sizeEl.forEach(ele => {
        ele.classList.remove("active");
    })
    el.classList.add("active");
    console.log(el.dataset.size)
    getColor();
    renderPrice();
    renderNameAndSku();
}

const getColor = () => {
    let varArr = proObj.variantEntities;
    const sizeEl = document.querySelectorAll(".size__btn label");

    let size;
    sizeEl.forEach(ele => {
        if(ele.classList.contains("active")){
            size = ele.dataset.size;
        }
    })
    console.log(size);

    let colorArr = varArr.filter(el => el.size === size);
    console.log(colorArr);
    renderColor(colorArr);
}

const renderColor = (arr) => {
    const colorItems = document.querySelector(".color__checkbox");
    colorItems.innerHTML = "";
    html = `
        <label for="${arr[0].color}-btn" class="active" data-color="${arr[0].color}" onclick="activeColor(this)">
            <input type="radio" id="${arr[0].color}-btn">${arr[0].color}
        </label>
        `
    if(arr.length > 1){
        for(let i = 1; i < arr.length; i++){
            html += `
                <label for="${arr[i].color}-btn" data-color="${arr[i].color}" onclick="activeColor(this)">
                    <input type="radio" id="${arr[i].color}-btn">${arr[i].color}
                </label>
                `
        }
    }
    colorItems.innerHTML = html;
}

const activeColor = (el) => {
    const colorEl = document.querySelectorAll(".color__checkbox label");
    colorEl.forEach(ele => {
        ele.classList.remove("active");
    })
    el.classList.add("active");
    renderPrice();
    renderNameAndSku();
}

const getVariant = () => {
    let color;
    let size;

    const sizeEl = document.querySelectorAll(".size__btn label");
    sizeEl.forEach(ele => {
        if(ele.classList.contains("active")){
            size = ele.dataset.size;
        }
    })

    const colorEl = document.querySelectorAll(".color__checkbox label");
    colorEl.forEach(ele => {
        if(ele.classList.contains("active")){
            color = ele.dataset.color;
        }
    })

    let varArr = proObj.variantEntities;
    let res = varArr.find(el => el.size === size && el.color === color);

    return res;
}

const renderPrice = () => {
    let res = getVariant();
    const priceEl = document.querySelector(".product__details__price");
    priceEl.innerHTML = "";

    console.log(res.price)
    priceEl.innerHTML = `<div>${formatVND(res.price)}</div>`;
}

const renderNameAndSku = () => {
    let res = getVariant();

    const detailName = document.querySelector(".product__details__name");
    detailName.innerHTML = `<h3 style="font-size: 25px">${proObj.name}
                                <span class="variant__sku" data-var-id="${res.id}">SKU: ${res.sku}</span>
                            </h3>`;
    console.log($(".variant__sku").data("var-id"))
}

function setHotProductItems (el){
    return `
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg">
                            <img src="/api/v1/products/images/${el.imageEntities[0].url}" alt="">
                            <div class="label new">New</div>
                            <ul class="product__hover">
                                <li><a href="/api/v1/products/images/${el.imageEntities[0].url}" class="image-popup"><span class="arrow_expand"></span></a></li>
                                <li><a href="#"><span class="icon_heart_alt"></span></a></li>
                                <li><a href="#"><span class="icon_bag_alt"></span></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="/shop/products/${el.id}">${el.name}</a></h6>
                            <div class="rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <div class="product__price">${formatVND(el.variantEntities[0].price)}</div>
                        </div>
                    </div>
                </div>`
}

const addToCart = () => {
    let cart_id = localStorage.getItem("cart_id");
    $.ajax({
        url: "/api/v1/shop/cart",
        type: "POST",
        dataType: "json",
        async: true,
        contentType: "application/json",
        data: JSON.stringify({
            cartId: +cart_id,
            note: "",
            variantId: $(".variant__sku").data("var-id"),
            quantity: $(".pro-qty input").val()
        }),
        success: function (res){
            toastr.success("Sản phẩm đã được thêm vào giỏ");
            localStorage.setItem("cart_id", res.cartId);
        },
        error: function (e){
            console.log(e);
            toastr.error("Thêm sản phẩm thất bại");
        }
    })
}


