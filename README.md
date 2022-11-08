# Project shop thời trang (Ashion shop)

## Đối tượng người dùng
- Quản trị shop (Chủ shop, quản lý, nhân viên)
- Khách hàng (Người mua hàng)

## Công nghệ
1. Database: MySQL
2. Backend:
+ Ngôn ngữ: Java
+ Framework: Java Spring
3. Frontend:
+ Html, CSS
+ JavaScript, Jquery
+ Bootstrap 

## Cấu trúc Database

1. account (Quản trị + khách hàng)
- Vì project triển khai trên một service nên thông tin nhà quản trị và khách hàng được lưu trên cùng 1 database
- Mối quan hệ:
n-n: role 
1-n: token

2. token (Lưu trữ token active)
- Mối quan hệ:
n-1: account

3. role (Nhóm người dùng)
- Xác định phân quyền tài khoản
- Mối quan hệ:
n-n: account

4. product (Sản phẩm)
- Mối quan hệ:
n-n: category
1-n: variant, image

5. category (Nhóm sản phẩm)
<<<<<<< HEAD
- Mối quan hệ
n-n: product

6. variant (Phiên bản sản phẩm)
- Mối quan hệ
n-1: product

7. image (Ảnh sản phẩm)
- Mối quan hệ
n-1: product

8. cart(Giỏ hàng)
- Mối quan hệ
1-n: cart_item

9. cart_item(Thành phần chi tiết giỏ hàng)
- Mối quan hệ
n-1: cart

10. order(Đơn hàng)
- Mối quan hệ
n-1: account
1-n: order_item

11. order_item(Thành phần chi tiết đơn hàng)
- Mối quan hệ
n-1: order

12. shipping_address(Thông tin nhận hàng)
- Mối quan hệ
1-1: order

//============ Todo =============//
<br>
13. refund (Hoàn tiền)
=======
7. variant (Phiên bản sản phẩm)
8. image (Ảnh sản phẩm)
9. cart(Giỏ hàng)
10. cart_item(Thành phần chi tiết giỏ hàng)
11. order(Đơn hàng)
12. order_item(Thành phần chi tiết đơn hàng)
13. shipping_address(Thông tin nhận hàng)

============ Todo =============
<br>
13. refund (Hoàn tiền)
>>>>>>> 6c5981d121f341915478483154144ba24515a5eb
