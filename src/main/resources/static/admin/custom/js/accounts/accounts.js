//Initialize Select2 Elements
$('.select2').select2()

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

// tìm kiếm nhân viên theo tên
const searchInput = document.getElementById("search-account");
const searchBtn = document.getElementById("search-account-btn");
searchBtn.addEventListener("click", async () => {
    try {
        await axios.get(`/admin/accounts?name=${searchInput.value}&page=1`)
        console.log("successful")
        console.log(window.location.search)
        window.location.href = `/admin/accounts/?name=${searchInput.value}&page=1`;
    } catch (e){
        console.log(e);
    }
})

// create new account
const nameInput = document.getElementById("createName")
const phoneInput = document.getElementById("createPhone")
const emailInput = document.getElementById("createEmail")
const passwordInput = document.getElementById("createPassword")
const rolesSelect = document.getElementById("select-roles")
const createBtn = document.getElementById("create-account")
const registerBtn = document.getElementById("register")
const inputEles = document.querySelectorAll('.input-row');

registerBtn.addEventListener("click", () => {
    Array.from(inputEles).map((ele) =>
        ele.classList.remove('success', 'error')
    );
    nameInput.value = "";
    phoneInput.value = "";
    emailInput.value = "";
    passwordInput.value = "";
})

// Lưu trữ các role được chọn
const getRolesChose = () => {
    let roleArr = [];
    let roleOptionEl = rolesSelect.querySelectorAll("option");
    roleOptionEl.forEach(el => {
        if (el.selected) {
            roleArr.push(el.value)
        }
    })
    return roleArr;
}

// Thực hiện đăng kí
createBtn.addEventListener("click", async () => {
    try {
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        let isValid = checkValidate();
        if(isValid) {
            await axios.post("/api/v1/auth/register",
                {
                    name: nameInput.value,
                    phone: phoneInput.value,
                    email: emailInput.value,
                    password: passwordInput.value,
                    address: null,
                    city: null,
                    roles: getRolesChose()
                })
            window.location.href = "http://localhost:8686/admin/accounts";
        }
    } catch (e){
        toastr.error(e.response.data.message);
    }
})





