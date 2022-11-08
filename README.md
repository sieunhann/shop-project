# Project shop thời trang (Ashion shop)

## Đối tượng người dùng
- Quản trị shop (Chủ shop, quản lý, nhân viên)
- Khách hàng (Người mua hàng)

## Công nghệ
- Database: MySQL
- Backend:
+ Ngôn ngữ: Java
+ Framework: Java Spring
- Frontend:
+ Html, CSS
+ JavaScript, Jquery
+ Bootstrap 

## Cấu trúc Database

1. account (Quản trị + khách hàng)
- Vì project triển khai trên một service nên thông tin nhà quản trị và khách hàng được lưu trên cùng 1 database
- Mối quan hệ:
+ n-n: role
+ 1-n: token

2. token (Lưu trữ token active)
- Mối quan hệ:
+ n-1: account

3. role (Nhóm người dùng)
- Xác định phân quyền tài khoản
- Mối quan hệ:
+ n-n: account

4. product (Sản phẩm)
- Mối quan hệ:
+ n-n: category
+ 1-n: variant, image

5. category (Nhóm sản phẩm)
6. variant (Phiên bản sản phẩm)
7. image (Ảnh sản phẩm)
8. cart(Giỏ hàng)
9. cart_item(Thành phần chi tiết giỏ hàng)
10. order(Đơn hàng)
11. order_item(Thành phần chi tiết đơn hàng)
12. shipping_address(Thông tin nhận hàng)

//============ Todo =============//
<br>
13. refund (Hoàn tiền)
