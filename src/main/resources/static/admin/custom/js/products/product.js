//Initialize Select2 Elements
$(".select2").select2();

// Initialize editor
const easyMDE = new EasyMDE({
    element: document.getElementById("content"), spellChecker: false, maxHeight: "500px", renderingConfig: {
        codeSyntaxHighlighting: true,
    },
});

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}


// Query string tren url (id)
const URL = "/admin/products/"
const params = window.location.pathname;
const paramId = params.replace(URL, "");

const updateProductBtn = document.getElementById("update-product")
const nameEl = document.getElementById("name");
const descriptionEl = document.getElementById("description");
const categoriesSelect = document.getElementById("category");
const deleteProductBtn = document.getElementById("btn-delete-product")

// =========== RENDER =================

// Lấy thông tin sản phẩm
const getProductAPI = (id) => {
    return axios.get(`/api/v1/products/${id}`)
}

let productObj;

const getProduct = async () => {
    try {
        console.log(paramId)
        let res = await getProductAPI(+paramId);
        productObj = res.data;
        console.log(productObj)
        renderProduct(productObj)
    } catch (e) {
        console.log(e.response.data.message)
    }
}

getProduct();

// Hiển thị thông tin sản phẩm
const renderProduct = (obj) => {
    nameEl.value = obj.name;
    descriptionEl.value = obj.description;
    easyMDE.value(obj.content)
    renderVariant(obj.variantEntities)
    console.log(obj.categoryEntities)
    // renderCategories(obj.categoryEntities)
    getCategories(obj.categoryEntities);
    renderImg(obj.imageEntities)
}

// ---------- CATEGORY ----------

// 1. Lấy danh sách toàn bộ nhóm sản phẩm
const getCategories = async (arr) => {
    try {
        let res = await axios.get("/api/v1/categories")
        console.log(res.data)
        renderCategories(res.data, arr);
    } catch (e){
        console.log(e.response.data.message)
    }
}

// 2. Hiển thị danh sách nhóm sp
const renderCategories = (res,arr) => {
        let html = "";
        res.forEach(el => {
            if(arr.find(ele => ele.id === el.id)){
                html += `<option value="${el.id}" selected>${el.name}</option>`;
            } else {
            html += `<option value="${el.id}">${el.name}</option>`;
            }
        })
        categoriesSelect.innerHTML = html;
}

// ---------- VARIANT ----------
// 1. Hiển thị phiên bản sản phẩm
let idx = 1;

const renderVariant = (arr) => {
    let variantsList = document.getElementById("variants");
    let html = "";
    arr.forEach(el => {
        html += `
        <div class="row pt-3 variant-obj" id="${el.id}">
            <div class="col-3">
                <label>Sku</label>
                <input type="text" class="form-control sku" value="${el.sku}" disabled/>
            </div>
            <div class="col-3">
                <label>Màu sắc</label>
                <input type="text" class="form-control color" value="${el.color}" disabled/>
            </div>
            <div class="col-2">
                <label>Kích thước</label>
                <input type="text" class="form-control size" value="${el.size}" disabled/>
            </div>
            <div class="col-3">
                <label>Giá bán</label>
                <input type="text" class="form-control price" value="${formatVND(el.price)}" disabled/>
            </div>
            <div class="col-1 d-flex align-items-end">
                <button class="btn btn-danger btn-flat btn-delete-variant" onclick="removeVariant(${el.id})"
                style="height: 60%; border-radius: 0.25rem;">Xóa</button>
            </div>
        </div>`
        idx++;
    })
    variantsList.innerHTML = html;
}

// 2. Thêm phiên bản
const addVariant = document.getElementById("add-variant");
const createVariant = document.getElementById("create-variant");
const createSku = document.getElementById("createSku");
const createColor = document.getElementById("createColor");
const createSize = document.getElementById("createSize");
const createPrice = document.getElementById("createPrice");

addVariant.addEventListener("click", () => {
    createSku.value = "";
    createColor.value = "";
    createSize.value = "";
    createPrice.value = "";
})

createVariant.addEventListener("click", async () => {
    try {
        let ids = productObj.id;
        let newVar = await axios.post(`/api/v1/products/${ids}/variant`,{
            sku: createSku.value,
            color: createColor.value,
            size: createSize.value,
            price: createPrice.value
        })
        toastr.success("Thêm phiên bản thành công")
        productObj.variantEntities.push(newVar.data);
        renderVariant(productObj.variantEntities);
    } catch (e){
        toastr.error(e.response.data.message);
    }
})

// 3. Xóa phiên bản
const removeVariant = async (id) => {
    try {
        await axios.delete(`/api/v1/variants/${id}`)
        productObj.variantEntities = productObj.variantEntities.filter(el => el.id !== id);
        const removeEl = document.getElementById(`${id}`);
        removeEl.remove();
        toastr.success("Xóa phiên bản thành công")
    } catch (e) {
        toastr.error(e.response.data.message)
    }
}

// ---------- IMAGE ----------

// 1. Hiển thị danh sách ảnh
const output = document.getElementById("result");

const renderImg = (imgs) => {
    let html = "";
    imgs.forEach(img => {
        html += `
            <div class="col-2 m-2" id="${img.id}">
                    <div class="row">
                        <div class="image-item col-12">
                            <img src="${img.url}" alt="">
                        </div>
                        <div class="col-12 mt-2">
                            <button class="btn-delete-img" onclick="removeImgProduct(${img.id})">Delete</button>
                        </div>
                    </div>
            </div>
        `
    })
    output.innerHTML = html;
}

// =========== UPDATE ===========

// ---------- PRODUCT -----------

// 1. Cập nhật thông tin sp
updateProductBtn.addEventListener("click", async () => {
    try {
        console.log(getCategoriesChose())
        console.log(nameEl.value)
        console.log(descriptionEl.value)
        let updatePro = await axios.put(`/api/v1/products/${paramId}`, {
            name: nameEl.value,
            content: easyMDE.value(),
            description: descriptionEl.value,
            categoryId: getCategoriesChose()
        })
        toastr.success("Cập nhật thành công");
    } catch (e) {
        toastr.error(e.response.data.message)
    }
})

// 2. Xóa sản phẩm
deleteProductBtn.addEventListener("click", async () => {
    try {
        await axios.delete(`/api/v1/products/${paramId}`);
        console.log("success")
        window.location.href = "/admin/products"
    } catch (e) {
        console.log(e.response.data.message)
    }
})

// ---------- IMAGE ----------

// 1. Thêm ảnh sp
const inputFileEl = document.getElementById("input-file");
inputFileEl.addEventListener("change", async (e) => {
    try {
        const files = e.target.files;
        console.log(files);
        let formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            formData.append("file[]" ,files[i]);
        }
        console.log(formData.getAll("file[]"))
        let newImgArr = await axios.post(`/api/v1/products/${productObj.id}/images`, formData);
        console.log(newImgArr.data)

        newImgArr.data.forEach(newimg => {
            productObj.imageEntities.push(newimg);
        })

        console.log(productObj.imageEntities)
        renderImg(productObj.imageEntities)
        toastr.success("Upload thành công");
    } catch (e){
        toastr.error(e.response.data.message)
    }
})


// 2. Xóa ảnh của sp
const removeImgProduct = async (ids) => {
    try {
        await axios.delete(`/api/v1/images/${ids}`)
        productObj.imageEntities = productObj.imageEntities.filter(el => el.id !== ids)
        console.log(productObj.imageEntities)
        renderImg(productObj.imageEntities)
        toastr.success("Xóa ảnh thành công");
    } catch (e){
        toastr.error(e.response.data.message)
    }
}

// ---------- CATEGORY ----------

// 1. Lưu trữ category được chọn
const getCategoriesChose = () => {
    let categoryArr = [];
    let categoryOptionEl = categoriesSelect.querySelectorAll("option");
    categoryOptionEl.forEach(el => {
        if (el.selected) {
            categoryArr.push(+el.value)
        }
    })
    return categoryArr;
}

