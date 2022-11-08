const inputEles = document.querySelectorAll('.input-row');
const nameInput = document.getElementById("name")
const phoneInput = document.getElementById("phone")
const emailInput = document.getElementById("email")

// ============ THÔNG TIN ============
let detObj;

// Lấy dữ liệu và hiển thị
const getDetail = async () => {
    try {
        let res = await axios.get("/api/v1/detail");
        detObj = res.data;
        console.log(detObj)
        renderDetail(detObj);
    } catch (e) {
        console.log(e.response.data.message())
    }
}

getDetail();

const renderDetail = (obj) => {
    nameInput.value = obj.name;
    phoneInput.value = obj.phone;
    emailInput.value = obj.email;
}

// Kiểm tra thông tin
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

// Cập nhật thông tin người dùng

const updateBtn = document.getElementById("btn-update");
updateBtn.addEventListener("click", async () => {
    try {
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        let isCheck = checkValidate();
        if(isCheck){
            let res = await axios.put(`/api/v1/accounts/${detObj.id}`, {
                name: nameInput.value,
                phone: phoneInput.value,
                roleEntities: detObj.roleEntities
            })
            toastr.success("Cập nhật thông tin thành công");
            detObj = res.data;
            renderDetail(detObj);
        }
    } catch (e){
        toastr.error("Cập nhật thất bại");
    }
})

// Thay đổi mật khẩu

const changePassBtn = document.getElementById("btn-change-password");
const oldPassInput = document.getElementById("old-password");
const newPassInput = document.getElementById("new-password");

changePassBtn.addEventListener("click", async () => {
    try {
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        let isCheck = checkPassword();
        if(isCheck) {
            $("#btn-change-password").attr("disabled", true)
            let res = await axios.put("/api/v1/detail/password", {
                oldPassword: oldPassInput.value,
                newPassword: newPassInput.value
            })
            toastr.success("Thay đổi mật khẩu thành công");
            oldPassInput.value = "";
            newPassInput.value = "";
        }
    } catch (e) {
        toastr.error(e.response.data.message);
        $("#btn-change-password").removeAttr("disabled")
    }
})

// Check validate password
function checkPassword() {
    let oldPassValue = oldPassInput.value;
    let newPassValue = newPassInput.value;

    let isCheck = true;

    // Kiểm tra trường mk cũ
    if (oldPassValue === '') {
        setError(oldPassInput, 'Mật khẩu không được để trống');
        isCheck = false;
    } else if(oldPassValue.length < 3){
        setError(oldPassInput, 'Mật khẩu phải có ít nhất 3 kí tự');
        isCheck = false;
    }
    else {
        setSuccess(oldPassInput);
    }

    // Kiểm tra trường mk mới
    if (newPassValue === '') {
        setError(newPassInput, 'Mật khẩu không được để trống');
        isCheck = false;
    } else if(newPassValue.length < 3){
        setError(newPassInput, 'Mật khẩu phải có ít nhất 3 kí tự');
        isCheck = false;
    }
    else {
        setSuccess(newPassInput);
    }

    return isCheck;
}