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
    const urlParams = new URL(window.location.href);
    urlParams.searchParams.set('page', pageNumber);
    window.location.href = urlParams;
}

const preNextPage = (e,param) => {
    e.preventDefault();
    const urlParams = new URL(window.location.href);
    urlParams.searchParams.set('page', +currentPage + param);
    window.location.href = urlParams;
}

const searchInput = document.getElementById("search-order");
const searchBtn = document.getElementById("search-order-btn");
searchBtn.addEventListener("click", async () => {
    try {
        await axios.get(`/admin/orders?query=${searchInput.value}&page=1`)
        console.log("successful")
        console.log(window.location.search)
        window.location.href = `/admin/orders/?query=${searchInput.value}&page=1`;
    } catch (e){
        console.log(e);
    }
})


