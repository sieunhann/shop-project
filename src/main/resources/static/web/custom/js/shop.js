$(document).ready(() => {
    setActiveMenu();

    getProducts();

    getFilter();

    $(".price__list").on("change", function (event){

        filterByPrice(event.target);
    })

    $(".size__list").on("change", function (event){
        filterBySize(event.target);
    })

    $(".color__list").on("change", function (event){
        filterByColor(event.target);
    })
})

function setActiveMenu(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    let param = urlParams.get("category");

    $('.header__menu__items li.active').removeClass('active');
    switch (param) {
        case "1":
            $("#menu_men").addClass('active');
            break;
        case "2":
            $("#menu_women").addClass('active');
            break;
        case "13":
            $("#menu_kid").addClass('active');
            break;
        default:
            $("#menu_all").addClass('active');
            break;
    }
}

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

// Lấy danh sách các sản phẩm
const getProducts = async () => {
    try {
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);

        let res = await axios.get(`/api/v1/shop/products?${urlParams}`);
        console.log(res.data);
        renderShopInfo(res.data)
    } catch (e) {
        console.log(e.response.data.message)
    }
}

const listProductItems = document.getElementById("list-product-items");
const renderProducts = (obj) => {
    let items = obj.productPage.content;
    let html = "";
    items.forEach(el => {
        let img = el.imageEntities[0];
        html += `
        <div class="col-lg-4 col-md-6 my-3">
           <div class="product_item">
              <div class="product__item__image">
                    <img src="/api/v1/product/images/${img.url}" alt="">
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
                 <div class="product__price">${renderNewProductPrice(el)}</div>
              </div>
           </div>
        </div>
        `
    })
    listProductItems.innerHTML = html;
}

const itemsPagination = document.getElementById("pagination-items")
const renderPagination = (obj) => {
    let arr = obj.pageNumbers
    let html = `<a href="javascript:void(0)"><i class="fa fa-angle-left"></i></a>`;
    arr.forEach(el => {
        html += `<a href="javascript:void(0)" onclick="getProductsPage(${el})">${el}</a>`
    })
    html += `<a href="javascript:void(0)"><i class="fa fa-angle-right"></i></a>`
    itemsPagination.innerHTML = html;
}

function renderFilterColor(obj){
    let colors = obj.colorList;
    let html = `
        <label for="color-0">
            Tất cả
            <input name="fil-color" type="radio" id="color-0" data-color="all">
            <span class="checkmark"></span>
        </label>`;
    let a = 1;
    colors.forEach(color => {
        html +=`
        <label for="color-${a}">
            ${color}
            <input name="fil-color" type="radio" id="color-${a}" data-color="${color}">
            <span class="checkmark"></span>
        </label>`
        a++;
    })
    $(".color__list").html(html);
}
function renderFilterSize(obj){
    let sizes = obj.sizeList;
    let html = `
        <label for="size-0">
            Tất cả
            <input name="fil-size" type="radio" id="size-0" data-size="all">
            <span class="checkmark"></span>
        </label>`;

    let a = 1;
    sizes.forEach(size => {
        html +=`
        <label for="size-${a}">
            ${size}
            <input name="fil-size" type="radio" id="size-${a}" data-size="${size}">
            <span class="checkmark"></span>
        </label>`
        a++;
    })
    $(".size__list").html(html);
}

// Chuyển page
const getProductsPage = async (page) => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    urlParams.set("page", page);

    window.history.replaceState(page, "page", `?${urlParams}`)
    getProducts();
}

// Hiển thị thông tin sp
function renderShopInfo(obj){
    renderProducts(obj);
    renderPagination(obj);
}

// Hiển thị bộ lọc
function renderFilter(obj){
    renderFilterSize(obj);
    renderFilterColor(obj);
}

// Lọc theo giá
function filterByPrice(el){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    let min = el.dataset.minval;
    let max = el.dataset.maxval;
    if(min === "all" && max === "all"){
        urlParams.delete("min");
        urlParams.delete("max");
    } else {
        urlParams.set("min", min);
        urlParams.set("max", max);
    }
    window.history.replaceState(min, "min", `?${urlParams}`)
    window.history.replaceState(max, "max", `?${urlParams}`)

    getProducts();
}

// Lọc theo kích thước
function filterBySize(el){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    let size = el.dataset.size;
    if(size === "all"){
        urlParams.delete("size")
    } else {
        urlParams.set("size", size);
    }
    window.history.replaceState(size, "size", `?${urlParams}`)

    getProducts();
}

// Lọc theo màu sắc
function filterByColor(el){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    let color = el.dataset.color;

    if(color === "all"){
        urlParams.delete("color");
    }
    else {
        urlParams.set("color", color);
    }
    window.history.replaceState(color, "color", `?${urlParams}`)

    getProducts();
}

// Lấy dữ liệu bộ lọc
const getFilter = async () => {
    try {
        let res = await axios.get(`/api/v1/shop/products/filter`);
        console.log(res.data);
        renderFilter(res.data)
    } catch (e) {
        console.log(e.response.data.message)
    }
}

