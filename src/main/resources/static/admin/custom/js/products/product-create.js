//Initialize Select2 Elements
$(".select2").select2();

// Initialize editor
const easyMDE = new EasyMDE({
    element: document.getElementById("content"),
    spellChecker: false,
    maxHeight: "500px",
    renderingConfig: {
        codeSyntaxHighlighting: true,
    },
});


// Tạo sản phẩm
const createBtn = document.getElementById("create-product");
const createNameInput = document.getElementById("name");
const categorySelect = document.getElementById("category");
const descriptionInput = document.getElementById("description");
// const easyMDE = new EasyMDE({element: document.getElementById('content')});

createBtn.addEventListener("click", async () => {
    try {
        formData.append("applicant", JSON.stringify(
            {
                name: createNameInput.value,
                content: easyMDE.value(),
                description: descriptionInput.value,
                categoryId: getCategoriesChose(),
                variants: getVariantsList()
            }
        ))
        console.log(formData.getAll("file[]"));
        await axios.post("/api/v1/products/create", formData);
        console.log("successful");
        formData.delete("file[]")
        formData.delete("applicant")
        window.location.href = "/admin/products"
    } catch (error) {
        console.log(error.response.data.message)
    }
})

// Lưu trữ category được chọn
const getCategoriesChose = () => {
    let categoryArr = [];
    let categoryOptionEl = categorySelect.querySelectorAll("option");
    categoryOptionEl.forEach(el => {
        if (el.selected) {
            categoryArr.push(+el.value)
        }
    })
    return categoryArr;
}

// Lưu trữ danh sách variant
const getVariantsList = () => {
    let variantArr = [];
    const variantList = document.querySelectorAll(".variant-obj");
    variantList.forEach(variant => {
        let varSku = variant.querySelector(".sku")
        let varColor = variant.querySelector(".color")
        let varSize = variant.querySelector(".size")
        let varPrice = variant.querySelector(".price")
        let variantObj = {
            "sku": varSku.value,
            "color": varColor.value,
            "size": varSize.value,
            "price": varPrice.value
        }
        variantArr.push(variantObj);
    })
    return variantArr;
}

// Xử lý image
const inputFileEl = document.getElementById("input-file");
const output = document.getElementById("result");

const formData = new FormData();

// Add event lắng nghe sự kiện thay đổi ô input
inputFileEl.addEventListener("change", (e) => {
    const files = e.target.files;
    console.log(files)
    for (const file of files) {
        file.preview = URL.createObjectURL(file)
        formData.append("file[]", file)
    }

    previewFiles(formData);
})

// Hiển thị ảnh preview
const previewFiles = files => {
    output.innerHTML = "";

    let html = "";
    files.getAll("file[]").forEach((file, index) => {
        html += `
                <div class="col-2 m-2">
                    <div class="row">
                        <div class="image-item col-12">
                            <img src="${file.preview}" alt="">
                        </div>
                        <div class="col-12 mt-2">
                            <button class="btn-delete-img" onclick=deleteImagePreview(${index})>Delete</button>
                        </div>
                    </div>
                </div>
                `
    });

    output.innerHTML = html;
}

// Xóa ảnh preview
const deleteImagePreview = (index) => {
    const files = formData.getAll("file[]")
        .filter((file, i) => i !== index);

    formData.delete("file[]");
    for (const file of files) {
        formData.append("file[]", file)
    }

    previewFiles(formData);
}


// Phiên bản sp
// 1. Thêm phiên bản
let idx = 1;
const addVariant = document.getElementById("add-variant")
addVariant.addEventListener("click", () => {
    let variantsList = document.getElementById("variants")
    let newVariant = document.createElement("div")
    newVariant.classList.add("row", "pt-3", "variant-obj")
    newVariant.setAttribute("id", `${idx}`)
    newVariant.innerHTML = `
        <div class="col-3">
            <label>Sku</label>
            <input type="text" class="form-control sku"/>
        </div>
        <div class="col-3">
            <label>Màu sắc</label>
            <input type="text" class="form-control color"/>
        </div>
        <div class="col-2">
            <label>Kích thước</label>
            <input type="text" class="form-control size"/>
        </div>
        <div class="col-3">
            <label>Giá bán</label>
            <input type="text" class="form-control price"/>
        </div>
        <div class="col-1 d-flex align-items-end">
            <button class="btn btn-danger btn-flat btn-delete-variant" onclick="removeVariant(${idx})"
            style="height: 60%; border-radius: 0.25rem;">Xóa</button>
        </div>`
    idx++;
    variantsList.appendChild(newVariant)
})


// 2. Xóa phiên bản
const removeVariant = (id) => {
    const removeEl = document.getElementById(`${id}`);
    removeEl.remove()
}


