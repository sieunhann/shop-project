//Chuyển page

const params = new URLSearchParams(window.location.search);
const currentPage = params.get("page")
const pageNumberEl = document.querySelectorAll(".page-number");
const urlParams = new URL(window.location.href);

pageNumberEl.forEach(page => {
    if(page.dataset.value === currentPage){
        page.classList.add("active");
    }
})

const changePage = (e, pageNumber) => {
    e.preventDefault();
    urlParams.searchParams.set('page', pageNumber);
    window.location.href = urlParams;
}

const preNextPage = (e, param) => {
    e.preventDefault();
    urlParams.searchParams.set('page', +currentPage + param);
    window.location.href = urlParams;
}

// Tìm sản phẩm
const searchBtn = document.getElementById("search-products-btn");
const searchInput = document.getElementById("search-products");

searchBtn.addEventListener("click", async () => {
    console.log(searchInput.value)
    try {
        await axios.get(`/admin/products/?query=${searchInput.value}&page=1`)
        console.log("successful")
        window.location.href = `/admin/products/?query=${searchInput.value}&page=1`;
    } catch (e) {
        console.log(e.response.data.message)
    }
})