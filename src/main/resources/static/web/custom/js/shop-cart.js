$(document).ready(() => {
    $('.header__menu__items li.active').removeClass('active');

    getCart();

    let t;
    $("#cart__note").keyup(function (){
        let cart_id = localStorage.getItem("cart_id")

        clearTimeout(t);
        t = setTimeout(function (){
            updateCartNote(cart_id);
        }, 500)
    });
})

// format sang tiền Việt
const formatVND = (obj) => {
    obj = obj.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
    return obj;
}

function getCart(){
    let cart_id = localStorage.getItem("cart_id")
    $.ajax({
        url: `/api/v1/shop/cart/${+cart_id}`,
        type: "GET",
        dataType: "json",
        async: true,
        success: function (res){
            console.log(res);
            renderItem(res.items);
            $("#cart__note").html(res.note)
        },
        error: function (e){
            console.log(e);
        }
    })
}

// render cart

function renderItem(arr){
    let html = "";
    if(arr != null) {
        arr.forEach(obj => {
            html += `
        <tr>
            <td class="cart__product__item">
               <img class="cart__product__img" src="/api/v1/products/images/${obj.imageUrl}" alt="">
               <div class="cart__product__item__title">
                    <h6>${obj.productName}</h6>
               <div class="variant__item__sku pt-2">
                    <i>Sku: ${obj.variant.sku}</i>
               </div>
               <div class="variant__item__properties pt-2">
                    <i>Thuộc tính: ${obj.variant.color}/${obj.variant.size}</i>
               </div>
           </td>
           <td class="cart__price" data-item-price="${obj.variant.price}">${formatVND(obj.variant.price)}</td>
           <td class="cart__quantity">
                <div class="pro-qty" data-item="${obj.itemId}">
                    <input type="text" value="${obj.quantity}">
                </div>
           </td>
           <td class="cart__total" data-total="${obj.total}">${formatVND(obj.total)}</td>
           <td class="cart__close" onclick="deleteCartItem(${obj.itemId}, this)"><span class="icon_close"></span></td>
        </tr>
        `
        })

        $(".cart__items").html(html);
        setQtyBtn();
        changeCartTotal();
    } else {
        html = `<div class="row justify-content-center">
        <div class="col-5 d-flex justify-content-center text-center">
            <div style="font-size: 25px;">Chưa có sản phẩm nào được thêm vào giỏ hàng</div>
        </div>
        <div class="col-12 pt-3 d-flex justify-content-center">
            <a href="/shop/products">>> Tiếp tục mua hàng <<</a>
        </div>
    </div>`;
        $(".shop-cart").html(html);
    }
}

function setQtyBtn(){
    let proQty = $('.pro-qty');
    proQty.prepend('<span class="dec qtybtn">-</span>');
    proQty.append('<span class="inc qtybtn">+</span>');
    proQty.on('click', '.qtybtn', function () {
        let $button = $(this);
        let oldValue = $button.parent().find('input').val();
        let newVal;
        if ($button.hasClass('inc')) {
            newVal = parseFloat(oldValue) + 1;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 0) {
                newVal = parseFloat(oldValue) - 1;
            } else {
                newVal = 0;
            }
        }
        $button.parent().find('input').val(newVal);
        let el = $button.parent().data("item");
        updateCartItem($button.parent());
    });
}

function updateCartItem(el){
    updateCartItemAPI(el);
    changeItemTotal(el);
}

function updateCartItemAPI(el){
    let id = el.data("item");
    let newVal = el.find('input').val()
    $.ajax({
        url: `/api/v1/shop/cart/items/${id}`,
        type: "PUT",
        async: true,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            quantity: newVal
        }),
        success: function (res){
            console.log(res)
        },
        error: function (e){
            console.log(e);
            toastr.error("Đã có lỗi xảy ra")
        }
    })
}

function changeItemTotal(el){
    let newVal = el.find('input').val()
    let ele = $(el).parent()
    let price = $(ele).parent().find(".cart__price").attr("data-item-price")
    console.log($(el).parent())
    console.log(price)
    let total = newVal*price;
    $(ele).parent().find(".cart__total").attr("data-total", total).html(formatVND(total));
    changeCartTotal();
}

function changeCartTotal(){
    let res = 0;
    const itemTotalEl = document.querySelectorAll(".cart__total");
    itemTotalEl.forEach(el => res += +el.dataset.total)
    $(".cart__price_total").find("span").html(formatVND(res));
    console.log(res)
}

function deleteCartItem(id, el){
    $.ajax({
        url: `/api/v1/shop/cart/items/${id}`,
        type: "DELETE",
        async: true,
        success: function (){
            toastr.success("Sản phẩm đã được xóa khỏi giỏ hàng");
            $(el).parent().remove();
            changeCartTotal();
        },
        error: function (e) {
            toastr.error("Đã có lỗi xảy ra");
            console.log(e);
        }
    })
}

function updateCartNote(id){
    $.ajax({
        url: `/api/v1/shop/cart/${id}`,
        type: "PUT",
        async: true,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            note: $("#cart__note").val()
        }),
        success: function (res){
        },
        error: function (e) {
            console.log(e)
        }
    })
}


