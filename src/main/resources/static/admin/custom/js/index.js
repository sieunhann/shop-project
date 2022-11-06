const emailEl = document.getElementById("username");
const passwordEl = document.getElementById("password");
const btnLogin = document.getElementById("btn-login");
const inputEles = document.querySelectorAll('.input-row');

// Đăng nhập
btnLogin && btnLogin.addEventListener("click", async () => {
    try {
        let res = await axios.post("api/v1/auth/login",
            {
                email: emailEl.value,
                password: passwordEl.value
            })
        window.location.href = "/shop"
    } catch (e){
        console.log(e);
    }
})

// Reset Password
const emailForgotInput = document.getElementById("forgot-email");
const forgotPasswordBtn = document.getElementById("forgot-password");

forgotPasswordBtn.addEventListener("click", async () => {
    try {
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        let isCheck = checkValidate();
        if(isCheck) {
            await axios.put(`/api/v1/detail/forgot?email=${emailForgotInput.value}`);
            toastr.success("Mật khẩu mới đã được gửi tới email")
        }
    } catch (e) {
        console.log(e.response.data.message)
    }
})

// Validate form
// Validate dữ liệu trong các ô input và highlight
function checkValidate() {
    let emailValue = emailForgotInput.value;

    let isCheck = true;

    // Kiểm tra trường email
    if (emailValue === '') {
        setError(emailForgotInput, 'Email không được để trống');
        isCheck = false;
    } else if (!isEmail(emailValue)) {
        setError(emailForgotInput, 'Email không đúng định dạng');
        isCheck = false;
    } else {
        setSuccess(emailForgotInput);
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