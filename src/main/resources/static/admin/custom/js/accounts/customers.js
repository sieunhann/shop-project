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

// find customers by phone
const searchInput = document.getElementById("search-customer");
const searchBtn = document.getElementById("search-customer-btn");
searchBtn.addEventListener("click", async () => {
    try {
        await axios.get(`/admin/customers?phone=${searchInput.value}&page=1`)
        console.log("successful")
        window.location.href = `/admin/customers?phone=${searchInput.value}&page=1`
    } catch (e){
        console.log(e);
    }
})


const provinceEl = document.querySelector("#select-province");
let provinceArr = [];

// Lay danh sach tỉnh thành phố
const getProvince = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/")
        console.log(res);

        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Hien thi thanh pho
const renderProvince = arr => {
    provinceEl.innerHTML = "";

    let html = "<option hidden>--Select Province</option>";
    arr.forEach(p => {
        provinceArr.push({code: p.code, name: p.name});
        html += `<option value=${p.code}>${p.name}</option>`;
    });
    provinceEl.innerHTML = html;
}

provinceEl.addEventListener("change", () => {
    let provinceCode = provinceEl.value;
    console.log(provinceCode)
})

const getMyProvince = () => {
    let pCode;
    let pValue;
    let provinceOptionEl = provinceEl.querySelectorAll("option");
    provinceOptionEl.forEach(el => {
        if(el.selected){
            pCode = +el.value;
            pValue = provinceArr.find(proEl => proEl.code === pCode);
        }
    })
    if(pValue === undefined){
        return null;
    }
    return pValue.name;
}

getProvince();

// create new customer
const nameInput = document.getElementById("createName")
const phoneInput = document.getElementById("createPhone")
const emailInput = document.getElementById("createEmail")
const passwordInput = document.getElementById("createPassword")
const addressInput = document.getElementById("createAddress")
const createBtn = document.getElementById("create-customer")
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
    addressInput.value = "";
})

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
                    address: addressInput.value,
                    city: getMyProvince(),
                    roles: [1]
                })
            console.log("successful")
            window.location.href = "http://localhost:8686/admin/customers";
        }
    } catch (e){
        console.log(e)
    }
})



