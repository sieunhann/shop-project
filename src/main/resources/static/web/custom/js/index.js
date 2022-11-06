$(document).ready(() => {
    setActiveMenu();
})

function setActiveMenu(){
    $('.header__menu__items li.active').removeClass('active');
    $("#menu_home").addClass('active');
}

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

// =========== SẢN PHẨM MỚI ===========

// Danh sách sản phẩm mới
const newProductBtn = document.getElementById("new-products-all");

newProductBtn.addEventListener("click", () => {
    getNewProducts();
})

const getNewProducts = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/new`);
        renderNewProducts(res.data);
        console.log(res.data)
    } catch (e) {
        console.log(e.response.data.message);
    }
}

getNewProducts();

const newProducts = document.getElementById("new-products");

const renderNewProducts = (arr) => {
    html = "";
    arr.forEach(el => {
        let img = el.imageEntities[0];
        html += `
        <div class="col-lg-3 col-md-4 col-sm-6 my-3">
                <div class="product_item">
                    <div class="product__item__image">
                    <img src="/api/v1/product/images/${img.url}" alt="">
                    </div>
                    <div class="product__item__text">
                        <h6><a href="#">${el.name}</a></h6>
                        <div class="rating">
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                            <i class="fa fa-star"></i>
                        </div>
                        <div class="product__price">${renderNewProductPrice(el)}</div>
                    </div>
                </div>
            </div>
        `
    })
    newProducts.innerHTML = html;
}

const renderNewProductPrice = (obj) => {
    let arr = obj.variantEntities;
    let priceMin = arr[0].price;
    return formatVND(priceMin)
}

// Danh sách sản phẩm nữ mới
const newWonmenBtn = document.getElementById("new-products-women");

const getNewWomenProducts = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/new?query=2`);
        renderNewProducts(res.data);
        console.log(res.data)
    } catch (e) {
        console.log(e.response.data.message);
    }
}

newWonmenBtn.addEventListener("click", () => {
    getNewWomenProducts();
})

// Danh sách sản phẩm nam mới
const newMenBtn = document.getElementById("new-products-men");

const getNewMenProducts = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/new?query=1`);
        renderNewProducts(res.data);
        console.log(res.data)
    } catch (e) {
        console.log(e.response.data.message);
    }
}

newMenBtn.addEventListener("click", () => {
    getNewMenProducts();
})

// Danh sách sản phẩm trẻ em mới
const newKidBtn = document.getElementById("new-products-kid");

const getNewKidProducts = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/new?query=13`);
        renderNewProducts(res.data);
        console.log(res.data)
    } catch (e) {
        console.log(e.response.data.message);
    }
}

newKidBtn.addEventListener("click", () => {
    getNewKidProducts();
})

// ================= SẢN PHẨM TREND =================
const trendItems = document.getElementById("trend-items");
const hotItems = document.getElementById("hot-items");
const featuredItems = document.getElementById("featured-items");

const renderTrend = (objNode, arr) => {
    let html = "";
    arr.forEach(el => {
        let img = el.imageEntities[0];
        html += `
        <div class="trend__item">
            <div class="trend__item__pic">
                 <img src="/api/v1/product/images/${img.url}" alt="" style="width: 90px; max-height: 90px; object-fit: cover">
            </div>
            <div class="trend__item__text">
                <h6><a>${el.name}</a></h6>
            <div class="rating">
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>
                <i class="fa fa-star"></i>                            
            </div>
                 <div class="product__price">${renderNewProductPrice(el)}</div>
            </div>
        </div>
        `
    })
    objNode.innerHTML = html;
}

// 1. Sản phẩm trend
const getTrendProduct = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/new?query=16&limit=3`)
        renderTrend(trendItems, res.data);
    } catch (e){
        console.log(e.response.data.message)
    }
}

getTrendProduct();

const getHotProduct = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/new?query=15&limit=3`)
        renderTrend(hotItems, res.data);
    } catch (e){
        console.log(e.response.data.message)
    }
}

getHotProduct();

const getFeaturedProduct = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/new?query=3&limit=3`)
        renderTrend(featuredItems, res.data);
    } catch (e){
        console.log(e.response.data.message)
    }
}

getFeaturedProduct();

