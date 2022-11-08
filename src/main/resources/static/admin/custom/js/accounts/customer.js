// Query string tren url (id)
const API_URL = "/admin/customers/"
const urlParam = window.location.pathname;
const paramId = urlParam.replace(API_URL, "");

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

// =========== CITY ===========

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

// =========== CUSTOMER ===========
const nameInput = document.getElementById("name");
const phoneInput = document.getElementById("phone");
const emailInput = document.getElementById("email");
const addressInput = document.getElementById("address");
let cusObj;

// 1. Hiển thị
const getCustomerAPI = () => {
    console.log(paramId)
    return axios.get(`/api/v1/customers/${paramId}`);
}

// Lấy thông tin KH
const getCustomer = async () => {
    try {
        let res = await getCustomerAPI();
        cusObj = res.data;
        console.log(cusObj)
        renderCustomer(cusObj);
    } catch (e) {
        console.log(e.response.data.message)
    }
}

getCustomer();

// Hiển thị thông tin KH
const renderCustomer = (obj) => {
    nameInput.value = obj.name;
    phoneInput.value = obj.phone;
    emailInput.value = obj.email;
    addressInput.value = obj.address;
    renderCustomerCity(obj);
}

// Hiển thị thành phố của khách hàng
const renderCustomerCity = (obj) => {
    let cityObj = provinceArr.find(el => el.name === obj.city);
    console.log(cityObj)
    let pCode = "" + cityObj.code;
    console.log(typeof pCode)

    let provinceOptionEl = provinceEl.querySelectorAll("option");
    provinceOptionEl.forEach(proEl => {
        if(proEl.value === pCode){
            proEl.selected = true;
        }
    })
}

// 2. Cập nhật
const updateBtn = document.getElementById("btn-update-customer");

updateBtn.addEventListener("click", async () => {
    try {
        let isCheck = checkValidate();
        if(isCheck) {
            let res = await axios.put(`/api/v1/customers/${paramId}`,
                {
                    name: nameInput.value,
                    phone: phoneInput.value,
                    address: addressInput.value,
                    city: getMyProvince()
                })
            cusObj = res.data;
            console.log(cusObj)
            renderCustomer(cusObj);
            toastr.success("Cập nhật thành công")
        }
    } catch (e) {
        toastr.error(e.response.data.message)
    }
});

// 3. Kiểm tra thông tin
// Validate form
// Validate dữ liệu trong các ô input và highlight
function checkValidate() {
    let nameValue = nameInput.value;
    let phoneValue = phoneInput.value;

    let isCheck = true;

    // Kiểm tra trường name
    if (nameValue === '') {
        setError(nameInput, 'Tên không được để trống');
        isCheck = false;
    } else {
        setSuccess(nameInput);
    }

    // Kiểm tra trường phone
    if (phoneValue === '') {
        setError(phoneInput, 'Số điện thoại không được để trống');
        isCheck = false;
    } else if (!isPhone(phoneValue)) {
        setError(phoneInput, 'Số điện thoại không đúng định dạng');
        isCheck = false;
    } else {
        setSuccess(phoneInput);
    }

    return isCheck;
}

// Set hiển thị highlight ô input và message error
function setError(ele, message) {
    let parentEle = ele.parentNode;
    parentEle.classList.add('error');
    parentEle.querySelector('small').innerText = message;
}

// Set hiển thị highlight ô input và message success
function setSuccess(ele) {
    ele.parentNode.classList.add('success');
}

// Kiểm tra định dạng sđt
function isPhone(number) {
    return /^(0|\+84)[1-9][0-9]{8}$/.test(number);
}