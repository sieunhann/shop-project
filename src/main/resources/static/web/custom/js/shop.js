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
        renderProducts(res.data)
        renderPagination(res.data)
    } catch (e) {
        console.log(e.response.data.message)
    }
}

getProducts();

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

const getProductsPage = async (page) => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    urlParams.set("page", page);

    window.history.replaceState(page, "page", `?${urlParams}`)
    getProducts(page);
}

