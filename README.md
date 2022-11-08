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

 Vì project triển khai trên một service nên thông tin nhà quản trị và khách hàng được lưu trên cùng 1 database

 Mối quan hệ:
- n-n: role
- 1-n: token

2. token (Lưu trữ token active)
 Mối quan hệ:
- n-1: account

3. role (Nhóm người dùng)
 Xác định phân quyền tài khoản

 Mối quan hệ:
- n-n: account

4. product (Sản phẩm)
 Mối quan hệ:
- n-n: category
- 1-n: variant, image

5. category (Nhóm sản phẩm)
 Mối quan hệ
- n-n: product

6. variant (Phiên bản sản phẩm)
 Mối quan hệ
- n-1: product

7. image (Ảnh sản phẩm)
 Mối quan hệ
- n-1: product

8. cart(Giỏ hàng)
 Mối quan hệ
- 1-n: cart_item

9. cart_item(Thành phần chi tiết giỏ hàng)
 Mối quan hệ
- n-1: cart

10. order(Đơn hàng)
 Mối quan hệ
- n-1: account
- 1-n: order_item

11. order_item(Thành phần chi tiết đơn hàng)
 Mối quan hệ
- n-1: order

12. shipping_address(Thông tin nhận hàng)
 Mối quan hệ
- 1-1: order

13. refund (Hoàn tiền)
 Todo

## Tính năng

1. Admin (quản trị)
 Đơn hàng:
- Tạo đơn hàng mới
- Cập nhật thông tin đơn hàng (Trạng thái, thông tin nhận hàng)
- Theo dõi thông tin chi tiết đơn hàng

 Sản phẩm:
- Tạo sản phẩm mới, tạo phiên bản sản phẩm
- Cập nhật thông tin sản phẩm, phiên bản sản phẩm, tồn kho theo phiên bản
- Xóa sản phẩm, phiên bản sản phẩm
- Tạo nhóm sản phẩm
- Cập nhật, xóa nhóm sản phẩm 

 Tài khoản:
- Lấy lại mật khẩu (Quên mật khẩu)

 Tài khoản cá nhân: 
- Đăng nhập, đăng xuất tài khoản
- Cập nhật thông tin (thay đổi mật khẩu, thông tin cá nhân)

 Tài khoản nhân viên:
- Thêm, xóa, cập nhật thông tin (thông tin cá nhân, nhóm tài khoản) => Chỉ tài khoản có role Admin có quyền

 Tài khoản khách hàng:
- Cập nhật thông tin khách hàng
- Xem thông tin khách hàng, lịch sử mua hàng

2. Website

 Tài khoản:
- Đăng kí, xác thực tài khoản
- Đăng nhập
- Theo dõi thông tin cá nhân, lịch sử mua hàng (Chi tiết đơn hàng) => Chỉ có tài khoản đã đăng kí và được kích hoạt có quyền sử dụng
- Cập nhật thông tin cá nhân, thay đổi mật khẩu
- Lấy lại mật khẩu (Quên mật khẩu)

 Mua hàng:
- Xem thông tin sản phẩm (Hiển thị size, màu sắc => mã và giá sp thay đổi khi chọn phiên bản sp)
- Lọc sản phẩm theo giá, màu sắc, size, nhóm sản phẩm
- Thêm sản phẩm vào giỏ hàng (giỏ hàng được lưu trữ)
- Đặt mua trên website

3. Todo

 Hoàn tiền 

 Dashboard (Theo dõi tình hình kinh doanh cho nhà quản trị)

 Giới hạn số lượng sản phẩm được thêm vào giỏ hàng (Không vượt quá lượng tồn kho)

Lưu trữ sản phẩm yêu thích

