// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

// Hiển thị giá sp = giá phiên bản đầu tiên
const renderNewProductPrice = (obj) => {
    let arr = obj.variantEntities;
    let priceMin = arr[0].price;
    return formatVND(priceMin)
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

getProduct();

const renderProductImg = (obj) => {
    const imgLeft = document.querySelector(".product__details__pic__left");
    const imgSlider = document.querySelector(".product__details__pic__slider")
    let images = obj.imageEntities;
    let leftHtml = `
        <a class="pt active" href="#product-1">
            <img src="/api/v1/product/images/${images[0].url}" alt="">
        </a>
    `
    let sliderHtml = `
         <img data-hash="product-1" class="product__big__img" 
         src="/api/v1/product/images/${images[0].url}" alt="">
    `
    if(images.length > 1){
        for(let i = 1; i < images.length; i++){
            leftHtml += `
                <a class="pt" href="#product-${i+1}">
                    <img src="/api/v1/product/images/${images[i].url}" alt="">
                </a>
            `
            sliderHtml += `
                <img data-hash="product-${i+1}" class="product__big__img" src="/api/v1/product/images/${images[i].url}" alt="">
            `
        }
    }

    imgLeft.innerHTML = leftHtml;
    imgSlider.innerHTML = sliderHtml;
}

const renderProductInfo = (obj) => {
    const desEl = document.querySelector(".product__details__description");
    desEl.textContent = proObj.description;

    const contentEl = document.querySelector("#tabs-1 p");
    contentEl.textContent = proObj.content;
    
    renderSize(obj);
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
}

const renderPrice = () => {
    const priceEl = document.querySelector(".product__details__price");
    priceEl.innerHTML = "";

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

    console.log(res.price)
    priceEl.innerHTML = `<div>${formatVND(res.price)}</div>`;

    const detailName = document.querySelector(".product__details__name");
    detailName.innerHTML = `<h3 style="font-size: 25px">${proObj.name}<span>SKU: ${res.sku}</span></h3>`;
}