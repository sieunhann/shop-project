$(document).ready(() => {
    $('.header__menu__items li.active').removeClass('active');

    getProvinces();
    getCustomerApi();
    getOrderApi();

    $(".update-info-btn").on("click", function (event){
        event.preventDefault();
        const inputEles = document.querySelectorAll('.input-row');
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        let isCheck = checkValidateUpdate();
        if(isCheck){
            updateUserInfo();
        }
    })
})

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

function formatDate (obj){
    let myDate = new Date(obj);
    return myDate.toLocaleString();
}

// ========== CUSTOMER =========
function getCustomerApi(){
    $.ajax({
        url: "/api/v1/shop/detail",
        type: "GET",
        async: true,
        dataType: "json",
        success: function (res) {
            console.log(res)
            renderCustomer(res);
        },
        error: function (e) {
            console.log(e);
        }
    })
}

function renderCustomer(obj){
    $("#name").val(obj.name);
    $("#phone").val(obj.phone);
    $("#email").val(obj.email);
    $("#address").val(obj.address);
    renderCustomerCity(obj);
}

// =========== ORDERS ===========
function getOrderApi(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    $.ajax({
        url: `/api/v1/shop/detail/order?${urlParams}`,
        type: "GET",
        async: true,
        dataType: "json",
        success: function (res) {
            console.log(res);
            renderOrderInfo(res, urlParams);
        },
        error: function (e) {
            console.log(e)
        }
    })
}

function renderOrderInfo(obj, urlParams){
    renderOrder(obj);
    renderPagination(obj, urlParams);
}

// Hiển thị danh sách đơn hàng
function renderOrder(obj){
    console.log(obj.orderPage.content)
    let arr = obj.orderPage.content
    let html = "";
    arr.forEach(el => {
    html += `
    <div class="row">
        <div class="order-id col-7 pb-2">
            <a href="javascript:void(0)" data-toggle="modal" data-target="#order-items-${el.id}"
            onclick="getOrderItemApi('${el.id}')">Đơn hàng ${el.id}</a>
        </div>
        <div class="order-id col-5 d-flex justify-content-end  pb-2"
        style="color: #2fc251"><b>${mapOrderStatus(el.status)}</b></div>
    </div>
    <div class="row">
        <div class="order-id col-7 pb-2">
            <div class="order-create">${formatVND(el.total)}</div>
        </div>
        <div class="order-id col-5 d-flex justify-content-end  pb-2">${formatDate(el.createdAt)}</div>
    </div>
    <hr>
    <!-- Modal -->
    <div class="modal fade" id="order-items-${el.id}" tabindex="-1" 
         aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content" style="width: 140%">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
          <div class="row justify-content-center">
            <div class="col-md-12">
                <div class="text-bold" style="font-size: 18px; font-weight: bold">Chi tiết đơn hàng</div>
                <hr>
                    <div class="row">
                        <div class="col-12 pt-2">
                            <table class="table table-bordered table-hover">
                                <thead style="font-size: 14px; background-color: #ebeaea;">
                                    <th>Tên sản phẩm</th>
                                    <th>Số lượng</th>
                                    <th>Giá</th>
                                    <th>Thành tiền</th>
                                </thead>
                                <tbody id="list-items-${el.id}">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
              </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          </div>
        </div>
      </div>
    </div>
        `
    })
    $(".order-info").html(html);
}

// Chi tiết đơn hàng
function getOrderItemApi(id){
    console.log(id)
    $.ajax({
        url: `/api/v1/shop/detail/order/${id}`,
        type: "GET",
        async: true,
        dataType: "json",
        success: function (res) {
            console.log(res);
            console.log(id);
            renderOrderItems(res, id)
            console.log(res)
        },
        error: function (e) {
            console.log(e)
        }
    })
}

// Hiển thị chi tiết đơn hàng
function renderOrderItems(arr, id){
    let html = "";
    arr.forEach(el => {
        html += `
            <tr id="${el.id}" class="line_item" style="font-size: 14px">
                <td class="line_product" data-vari="${el.variantId}">
                <div style="margin-bottom: 0px">${el.productName} (${el.variantColor} - ${el.variantSize})</div>
                <div style="font-size: 12px"><i>SKU: ${el.variantSku}</i></div>
                </td>
                <td class ="line_qty" data-qty="${el.quantity}">${el.quantity}
                </td>
                <td class="line_price" data-price="${el.price}">${formatVND(el.price)}</td>
                <td class="line_total">${formatVND(el.total)}</td>
            </tr>
            `
    });
    $(`#list-items-${id}`).html(html);
}

// Hiển thị button chuyển page
const itemsPagination = document.getElementById("pagination-items")
const renderPagination = (obj, urlParams) => {
    let arr = obj.pageNumbers
    let html = `<a href="javascript:void(0)"><i class="fa fa-angle-left"></i></a>`;
    arr.forEach(el => {
        html += `<a href="javascript:void(0)" class="page-number" data-num="${el}" onclick="getCusOrderPage(${el})">${el}</a>`
    })
    html += `<a href="javascript:void(0)"><i class="fa fa-angle-right"></i></a>`
    itemsPagination.innerHTML = html;

    let number = urlParams.get("page");
    if(number !== undefined){
        const pageNumber = document.querySelectorAll(".page-number");
        pageNumber.forEach(el => {
            if(el.dataset.num === number){
                el.classList.add("active")
            }
        })
    }
}

// Chuyển page
const getCusOrderPage = async (page) => {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    urlParams.set("page", page);

    window.history.replaceState(page, "page", `?${urlParams}`)
    getOrderApi();
}
// =========== CITY ===========

const provinceElement = document.querySelector("#city");
let provinceArray = [];


// Lay danh sach tỉnh thành phố
const getProvinces = async () => {
    try {
        let res = await axios.get("https://provinces.open-api.vn/api/p/")
        console.log(res);
        renderProvinces(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Hien thi thanh pho
const renderProvinces = arr => {
    provinceElement.innerHTML = "";

    let html = "<option hidden>--Select Province</option>";
    arr.forEach(p => {
        provinceArray.push({code: p.code, name: p.name});
        html += `<option value=${p.code}>${p.name}</option>`;
    });
    provinceElement.innerHTML = html;
}

provinceElement.addEventListener("change", () => {
    let provinceCode = provinceElement.value;
    console.log(provinceCode)
})

const getMyProvinces = () => {
    let pCode;
    let pValue;
    let provinceOptionEl = provinceElement.querySelectorAll("option");
    provinceOptionEl.forEach(el => {
        if(el.selected){
            pCode = +el.value;
            pValue = provinceArray.find(proEl => proEl.code === pCode);
        }
    })
    if(pValue === undefined){
        return null;
    }
    return pValue.name;
}

// Hiển thị thành phố của khách hàng
const renderCustomerCity = (obj) => {
    console.log(provinceArray)
    console.log(obj.city)
    let cityObj = provinceArray.find(el => el.name === obj.city);
    let pCode = "" + cityObj.code;


    let provinceOptionEl = provinceElement.querySelectorAll("option");
    provinceOptionEl.forEach(proEl => {
        if(proEl.value === pCode){
            proEl.selected = true;
        }
    })
}

// Thay đổi mk

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

// Thay đổi mật khẩu

const changePassBtn = document.getElementById("btn-change-password");
const oldPassInput = document.getElementById("old-password");
const newPassInput = document.getElementById("new-password");
const inputEles = document.querySelectorAll('.input-row');

changePassBtn.addEventListener("click", async () => {
    try {
        Array.from(inputEles).map((ele) =>
            ele.classList.remove('success', 'error')
        );
        let isCheck = checkPassword();
        if(isCheck) {
            $("#btn-change-password").attr("disabled", true);
            let res = await axios.put("/api/v1/shop/detail/password", {
                oldPassword: oldPassInput.value,
                newPassword: newPassInput.value
            })
            toastr.success("Thay đổi mật khẩu thành công");
        }
    } catch (e) {
        toastr.error("Đã có lỗi xảy ra");
        console.log(e);
        $("#btn-change-password").removeAttr('disabled')
    }
})

// Kiểm tra thông tin
// Validate form
// Validate dữ liệu trong các ô input và highlight
function checkValidateUpdate() {
    const nameUpdate = document.getElementById("name")
    const phoneUpdate = document.getElementById("phone")
    let nameValue = nameUpdate.value;
    let phoneValue = phoneUpdate.value;

    let isCheck = true;

    // Kiểm tra trường name
    if (nameValue === '') {
        setError(nameUpdate, 'Tên không được để trống');
        isCheck = false;
    } else {
        setSuccess(nameUpdate);
    }

    // Kiểm tra trường phone
    if (phoneValue === '') {
        setError(phoneUpdate, 'Số điện thoại không được để trống');
        isCheck = false;
    } else if (!isPhone(phoneValue)) {
        setError(phoneUpdate, 'Số điện thoại không đúng định dạng');
        isCheck = false;
    } else {
        setSuccess(phoneUpdate);
    }

    return isCheck;
}

function updateUserInfo(){
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    $.ajax({
        url: "/api/v1/shop/detail",
        type: "PUT",
        dataType: "json",
        async: true,
        contentType: "application/json",
        data: JSON.stringify({
            name: $("#name").val(),
            phone: $("#phone").val(),
            address: $("#address").val(),
            city: getMyProvinces()
        }),
        success: function (res) {
            toastr.success("Cập nhật thành công")
            console.log(res);
            renderOrderInfo(res, urlParams);
        },
        error: function (e){
            console.log(e);
            toastr.error("Đã có lỗi xảy ra")
        }
    })
}

function mapOrderStatus(val){
    let statusArr = [["NEW", "Mới"], ["CONFIRMED","Xác thực"], ["CANCELED", "Hủy"], ["COMPLETED", "Thành công"]]
    let res = statusArr.find(el => el[0] === val);
    return res[1];
}