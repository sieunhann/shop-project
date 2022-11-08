
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

// Tạo nhóm sp
const createBtn = document.getElementById("create-category");
const nameCreateInput = document.getElementById("createName");
const desCreateInput = document.getElementById("createDescription");
createBtn.addEventListener("click", async () => {
    try {
        await axios.post("/api/v1/categories/create",
            {
                name: nameCreateInput.value,
                description: desCreateInput.value
            })
        window.location.href = "/admin/categories"
    } catch (e) {
        console.log(e.response.data.message)
    }
})

// tìm kiếm nhóm sp theo tên
const searchInput = document.getElementById("search-category");
const searchBtn = document.getElementById("search-category-btn");
searchBtn.addEventListener("click", async () => {
    try {
        await axios.get(`/admin/categories?name=${searchInput.value}&page=1`)
        console.log("successful")
        console.log(window.location.search)
        window.location.href = `/admin/categories/?name=${searchInput.value}&page=1`;
    } catch (e){
        console.log(e);
    }
})

// update category
async function updateCategory(id) {
    try {
        const modalUpdate = document.querySelector(`#modal-update-${id}`)
        const idUpdateEl = modalUpdate.querySelector(".updateId")
        const nameUpdateEl = modalUpdate.querySelector(".updateName")
        const descriptionUpdateEl = modalUpdate.querySelector(".updateDescription")
        await axios.put(`/api/v1/categories/${idUpdateEl.value}`,
            {
                name: nameUpdateEl.value,
                description: descriptionUpdateEl.value
            })
        console.log("successful")
        window.location.href = "/admin/categories"
    } catch (ex) {
        console.log(ex)
    }
}

// delete category
async function deleteCategory(id){
    try {
        await axios.delete(`/api/v1/categories/${id}`)
        console.log("successful")
        window.location.href = "/admin/categories"
    } catch (ex){
        console.log(ex)
    }
}