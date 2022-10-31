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
        await axios.post("/api/v1/product", formData);
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
        formData.append("files", file)
    }

    previewFiles(formData);
})

// Hiển thị ảnh preview
const previewFiles = files => {
    output.innerHTML = "";

    let html = "";
    files.getAll("files").forEach((file, index) => {
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
    const files = formData.getAll("files")
        .filter((file, i) => i !== index);

    formData.delete("files");
    for (const file of files) {
        formData.append("files", file)
    }

    previewFiles(formData);
}
// let formData = new FormData();
// const btnDeleteImage = document.getElementById("btn-delete-image");

// 1. Render Ảnh khi select
// function handleFileSelect() {
//     if (window.File && window.FileList && window.FileReader) {
//         let output = document.getElementById("result");
//
//         let files = event.target.files; //FileList object
//
//         for (let i = 0; i < files.length; i++) {
//             formData.append("file[]",files[i]);
//         }
//         console.log(formData.getAll("file[]"));
//         for (let i = 0; i < files.length; i++) {
//             let file = files[i]
//             console.log(file)
//             if (!file.type.match('image')) continue;
//             let picReader = new FileReader();
//             picReader.addEventListener("load", function (event) {
//                 let picFile = event.target;
//                 let div = document.createElement("div");
//
//                 div.classList.add("image-item", "col-2", "m-2")
//                 div.setAttribute("onclick", "choseImage(this)")
//                 div.innerHTML = "<img class='thumbnail' src='" + picFile.result + "'" + "title='" + picFile.name + "'/>";
//                 console.log(file.name + '::' + file.size);
//
//                 output.insertBefore(div, null);
//             });
//             picReader.readAsDataURL(file);
//         }
//     } else {
//         console.log("Your browser does not support File API");
//     }
// }
// document.getElementById('files').addEventListener('change', handleFileSelect, false);
//
//
// // 2. Chọn ảnh
// // Chọn ảnh
//
// const choseImage = ele => {
//     // Xoa het image chon truoc do
//     const imageSelected = document.querySelector(".selected");
//     if (imageSelected) {
//         imageSelected.classList.remove("border-3", "border-primary", "selected");
//     }
//
//     // Hightlight anh duoc click
//     ele.classList.add("border-3", "border-primary", "selected");
//     btnDeleteImage.disabled = false;
// }

// // Xóa ảnh
// btnDeleteImage.addEventListener("click", function(){
//     const imageSelected = document.querySelector(".selected");
//     const imgSelected = imageSelected.querySelector("img")
//     console.log(imgSelected)
//     fileArr.filter(file => file.lastModified !== imgSelected.title)
//     imageSelected.remove();
//     console.log(fileArr.length)
// })

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


