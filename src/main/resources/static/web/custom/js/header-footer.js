$(document).ready(() => {
    $("#create-customer").on("click", function (){
        userRegister();
    })

    $("#register-btn").on("click", function (){
        showFormRegister();
    })
})

// Validate form
// Validate dữ liệu trong các ô input và highlight
function checkValidate() {
    const nameInput = document.getElementById("createName")
    const phoneInput = document.getElementById("createPhone")
    const emailInput = document.getElementById("createEmail")
    const passwordInput = document.getElementById("createPassword")


    let nameValue = nameInput.value;
    let emailValue = emailInput.value;
    let passwordValue = passwordInput.value;
    let phoneValue = phoneInput.value;

    let isCheck = true;

    // Kiểm tra trường name
    if (nameValue === '') {
        setError(nameInput, 'Tên không được để trống');
        isCheck = false;
    } else {
        setSuccess(nameInput);
    }

    // Kiểm tra trường email
    if (emailValue === '') {
        setError(emailInput, 'Email không được để trống');
        isCheck = false;
    } else if (!isEmail(emailValue)) {
        setError(emailInput, 'Email không đúng định dạng');
        isCheck = false;
    } else {
        setSuccess(emailInput);
    }

    // Kiểm tra trường password
    if (passwordValue === '' || passwordValue.length < 3) {
        setError(passwordInput, 'Password phải có ít nhất 3 kí tự');
        isCheck = false;
    }
    else {
        setSuccess(passwordInput);
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

// Kiểm tra định dạng email
function isEmail(email) {
    return /^[a-zA-Z][\w]+@([\w]+\.[\w]{2,}|[\w]+\.[\w]{2,}\.[\w]{2,})$/.test(email);
}

// Kiểm tra định dạng sđt
function isPhone(number) {
    return /^(0|\+84)[1-9][0-9]{8}$/.test(number);
}

// Đăng kí ng dùng
function showFormRegister(){
    $("#createName").val("")
    $("#createPhone").val("")
    $("#createEmail").val("")
    $("#createPassword").val("")
    $("#createAddress").val("")

    const inputEles = document.querySelectorAll('.input-row');
    Array.from(inputEles).map((ele) =>
        ele.classList.remove('success', 'error')
    );
}

function userRegister(){
    const inputEles = document.querySelectorAll('.input-row');
    Array.from(inputEles).map((ele) =>
        ele.classList.remove('success', 'error')
    );
    let isCheck = checkValidate();
    if(isCheck){
        userRegisterAPI();
    }
}

function userRegisterAPI(){
    $.ajax({
        url: "/api/v1/auth/register",
        type: "POST",
        async: true,
        contentType: "application/json",
        data: JSON.stringify({
            name: $("#createName").val(),
            phone: $("#createPhone").val(),
            email: $("#createEmail").val(),
            password: $("#createPassword").val(),
            address: $("#createAddress").val(),
            city: getMyProvince(),
            roles: [1]
        }),
        success: function (res){
            toastr.success("Link kích hoạt đã được gửi tới email của bạn.");
        },
        error: function (e){
            toastr.error("Đăng kí thất bại")
        }
    })
}
