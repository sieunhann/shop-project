// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

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

// Update phiên bản sp
async function updateVariant(id) {
    try {
        const modalUpdate = document.querySelector(`#modal-update-${id}`);
        const priceInput = modalUpdate.querySelector(".updatePrice")
        const quantityInput = modalUpdate.querySelector(".updateQuantity")
        let res = await axios.put(`/api/v1/variants/${id}`,
            {
                price: priceInput.value,
                quantity: quantityInput.value
            })
        console.log(res.data);
        toastr.success("Cập nhật thành công")
        renderUpdate(id, priceInput.value, quantityInput.value);
    } catch (e) {
        toastr.error(e.response.data.message)
    }
}

// Hiển thị lại sau update
function renderUpdate(id, newPrice, newQuantity){
    console.log(formatVND(newPrice))
    const variantRow = document.getElementById(`${id}`);
    const priceCol = variantRow.querySelector(".varPrice")
    const quantityCol = variantRow.querySelector(".varQuantity")
    priceCol.innerHTML= formatVND(+newPrice);
    quantityCol.innerText = newQuantity;
}

// Xóa phiên bản sp
async function deleteVariant(id){
    try {
        await axios.delete(`/api/v1/variants/${id}`)
        console.log("successful");
        const variantRow = document.getElementById(`${id}`);
        variantRow.remove();
        toastr.success("Xóa thành công")
    } catch (e) {
        toastr.error(e.response.data.message)
    }
}

// Tìm phiên bản sp
const searchBtn = document.getElementById("search-variant-btn");

searchBtn.addEventListener("click", async () => {
    const searchInput = document.getElementById("search-variant");
    try {
        await axios.get(`/admin/variants?query=${searchInput.value}&page=1`)
        window.location.href = `/admin/variants?query=${searchInput.value}&page=1`;
    } catch (e) {
        console.log(e.response.data.message)
    }
})
