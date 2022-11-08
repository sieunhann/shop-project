//Initialize Select2 Elements
$(".select2").select2();

// Query string tren url (id)
const URL = "/admin/accounts/"
const params = window.location.pathname;
const paramId = params.replace(URL, "");

const nameInput = document.getElementById("name");
const phoneInput = document.getElementById("phone");
const emailInput = document.getElementById("email");
const roleSelect = document.getElementById("select-roles")
const inputEles = document.querySelectorAll('.input-row');

// ========= ACCOUNT =========

// 1. Hiển thị thông tin nhân viên

const getAccountAPI = () => {
    return axios.get(`/api/v1/accounts/${paramId}`)
}

const getAccount = async () => {
    try {
        let res = await getAccountAPI();
        console.log(res.data)
        renderAccount(res.data);
    } catch (e) {
        console.log(e.response.data.message)
    }
}

getAccount();

const renderAccount = (obj) => {
    nameInput.value = obj.name;
    phoneInput.value = obj.phone;
    emailInput.value = obj.email;
    getRoles(obj.roleEntities)
}

let roles = [];
const getRoles = async (arr) => {
    try {
        let res = await axios.get("/api/v1/roles")
        roles = res.data;
        renderRole(res.data, arr);
    } catch (e) {
        console.log(e.response.data.message)
    }
}

const renderRole = (res, arr) => {
    let html = "";
    res.forEach(el => {
        if(arr.find(ele => ele.id === el.id)) {
            html += `<option value="${el.id}" selected>${el.name}</option>`
        } else {
            html += `<option value="${el.id}">${el.name}</option>`
        }
    })
    roleSelect.innerHTML = html;
}

// 2. Update thông tin
const updateAccountBtn = document.getElementById("btn-update");
updateAccountBtn.addEventListener("click", async () => {
    try {
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        let isCheck = checkValidate();
        if(isCheck)
        {
            let res = await axios.put(`/api/v1/accounts/${paramId}`,
                {
                    name: nameInput.value,
                    phone: phoneInput.value,
                    roleEntities: getRoleSelect()
                })
            console.log("successful")
            renderAccount(res.data);
            toastr.success("Cập nhật thành công");
        }
    } catch (e) {
        toastr.error(e.response.data.message)
    }
})

const getRoleSelect = () => {
    const roleOption = roleSelect.querySelectorAll("option");
    let roleArr = [];
    roleOption.forEach(el => {
        if(el.selected){
            let role = roles.find(ele => ele.id === +el.value);
            roleArr.push(role);
        }
    })
    return roleArr;
}

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

// 4. Xóa nhân viên
const deleteBtn = document.getElementById("btn-delete-account");

deleteBtn.addEventListener("click", async () => {
    try{
        await axios.delete(`/api/v1/accounts/${paramId}`);
        window.location.href = "/admin/accounts";
    } catch (e) {
        toastr.error(e.response.data.message);
    }
})