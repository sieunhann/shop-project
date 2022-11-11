-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: db
-- Thời gian đã tạo: Th10 09, 2022 lúc 04:08 AM
-- Phiên bản máy phục vụ: 8.0.29
-- Phiên bản PHP: 8.0.19

-- SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
-- START TRANSACTION;
-- SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shop-project`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
                           `id` bigint NOT NULL,
                           `created_at` datetime DEFAULT NULL,
                           `updated_at` datetime DEFAULT NULL,
                           `address` varchar(255) DEFAULT NULL,
                           `city` varchar(255) DEFAULT NULL,
                           `email` varchar(255) NOT NULL,
                           `enabled` bit(1) DEFAULT NULL,
                           `name` varchar(255) NOT NULL,
                           `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                           `phone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `created_at`, `updated_at`, `address`, `city`, `email`, `enabled`, `name`, `password`, `phone`) VALUES
                                                                                                                                 (2, '2022-10-15 09:43:52', '2022-11-08 23:17:25', NULL, NULL, 'cuong@gmail.com', b'1', 'Mạnh Cường', '$2a$10$Bd6nCp3NWcP6q1Y0zDlRG.UKgawl9bzre.vL1/L5NdnIXnBmwOZ4q', '0902172880'),
                                                                                                                                 (6, '2022-10-15 14:40:54', '2022-10-15 14:40:54', NULL, NULL, 'macie@gmail.com', b'1', 'Ngọc Mai', '$2a$10$H/3g3.57OoybotwktKlG5uvSpSDssLhxSYdQ.pfyb.0i2kW1P6VHS', '0933001996'),
                                                                                                                                 (7, '2022-10-15 14:40:54', '2022-10-15 14:40:54', NULL, NULL, 'huy@gmail.com', b'1', 'Minh Huy', '$2a$10$ISnWxJGYOAp9EWf5ZVrH9.9kr9CWoncqHaUopE4u6m3Ug5XcOh/dO', '0908970986'),
                                                                                                                                 (8, '2022-10-15 14:40:54', '2022-10-15 14:40:54', NULL, NULL, 'tuan@gmail.com', b'1', 'Anh Tuấn', '$2a$10$9IMj.3sLx57ARF0YpdyNZ.0aFOlCMbanKpdwxf.39f9b.hrgIgdeO', '0909780976'),
                                                                                                                                 (9, '2022-10-15 14:40:54', '2022-10-15 14:40:54', NULL, NULL, 'minh@gmail.com', b'1', 'Công Minh', '$2a$10$3HkLXMSNu61/yh7L5dIAgeZuwsEfJLoEH82Ig08s2s.Gqzbi2nDya', '0399567908'),
                                                                                                                                 (15, '2022-10-18 11:27:04', '2022-10-18 11:27:04', 'Số 7 hồ Thiền Quang', NULL, 'minhtung@gmail.com', b'0', 'Minh Tùng', '$2a$10$C.VYnjYQqsyQanpJmJz1ueK59mdGd4M3YwdEz6tJDol6ZkmiewJme', '0908563412'),
                                                                                                                                 (16, '2022-10-18 11:29:26', '2022-10-18 11:29:26', 'Số 7 hồ Thiền Quang', NULL, 'quangkhai@gmail.com', b'0', 'Quang Khải', '$2a$10$AYxg3GOyHX/VAgarLAZwKulgPY419GvUyOQtMbJ4twFvLOt.Nnrpq', '0978905612'),
                                                                                                                                 (17, '2022-10-18 11:31:54', '2022-10-31 22:17:54', 'Số 5 Phùng Khắc Khoan', 'Thành phố Hải Phòng', 'minhchien@gmail.com', b'1', 'Minh Chiến', '$2a$10$oQhc5ucXFaR6M51ksd48SuufYCvP7cldfms2t9P9g23ih0YoZIgbe', '0936589012'),
                                                                                                                                 (18, '2022-10-18 11:42:23', '2022-10-18 11:42:23', 'Số 7 hồ Thiền Quang', NULL, 'thanhchung@gmail.com', b'0', 'Thành Chung', '$2a$10$3mQ2lPpfQzspTra7xQrPcOxCfBRmWDyXfChREuojewc81RRfED252', '0973457689'),
                                                                                                                                 (19, '2022-10-18 11:43:56', '2022-11-03 10:00:53', 'Số 8A, đường Chiến Thắng', NULL, 'minhlong@gmail.com', b'1', 'Minh Long', '$2a$10$3XdpamNHevjagMx8n8emtubuhrG.5RPgyAqXElAnOvvJvXm/rUYRK', '0896432478'),
                                                                                                                                 (20, '2022-10-18 11:46:02', '2022-10-31 22:22:28', 'Số 201 phường Hoàng Văn Thụ', 'Tỉnh Cao Bằng', 'khanhvan@gmail.com', b'0', 'Khánh Vân', '$2a$10$R6kGRXkUMYxv0MRgXx5hJuIBDpB.fJESHrKXlkP2DJTuYlQlAySu2', '0947890132'),
                                                                                                                                 (21, '2022-10-18 11:49:34', '2022-10-31 11:21:36', 'Số 90 ngách 69 Hoàng Mai', 'Thành phố Hà Nội', 'thaiminh@gmail.com', b'1', 'Thái Minh', '$2a$10$3OwcANKs9MuynxP8UxKs/uczO412lA2XBao0wwlcl68R2jvvm9UOi', '0934568901'),
                                                                                                                                 (22, '2022-10-18 11:57:40', '2022-10-31 08:31:03', '401 Tống Vạn', 'Tỉnh Hà Giang', 'huuthang@gmail.com', b'1', 'Hữu Thắng', '$2a$10$9u.kBAcpd7aUGnHsxGkFT.pSu3XU6IGEzZceyNDM44p5gMkJbl3D.', '0918764512'),
                                                                                                                                 (25, '2022-10-31 01:19:18', '2022-10-31 01:19:59', NULL, NULL, 'cocsnef@gmail.com', b'1', 'Ngọc Cóc', '$2a$10$oD2VDA1FHoGip3OCi1qCe.thIMwPudTp.UpT2RWFQXhASH3.CZa2G', '0904578912'),
                                                                                                                                 (28, '2022-10-31 01:36:57', '2022-10-31 01:37:18', NULL, NULL, 'hoaian@gmail.com', b'1', 'Hoài An', '$2a$10$v30.b4co3/CKB1yl58gcbOT2MBQey5fv/k8nblIy.KtzBKFjjr2MK', '0939034578'),
                                                                                                                                 (36, '2022-10-31 21:05:48', '2022-10-31 22:09:05', NULL, NULL, 'quang@gmail.com', b'1', 'Ngọc Quang', '$2a$10$IDODLZShJTAmCYT0Xn8Ufu281.FEoirg7W8UBJWBbAHuuHSR5xvWu', '0903478913'),
                                                                                                                                 (37, '2022-10-31 21:09:59', '2022-10-31 21:10:05', NULL, NULL, 'vanthien@gmail.com', b'1', 'Văn Thiện', '$2a$10$Pg9l3Jm6HrzNDyJGLO1bK.7l5VuOhO3KAlI9cwekQYlvgrw8AbOEm', '0983345789'),
                                                                                                                                 (39, '2022-10-31 22:34:29', '2022-10-31 22:38:44', '372 Tân Triều, quận Tây Hồ', 'Tỉnh Hà Giang', 'hoangcong@gmail.com', b'1', 'Hoàng Công', '$2a$10$IR40FRkY2R70AYGMycttzOkOXlOL2M4uANDU4YqbtvB36KSt1VeU2', '0912277881'),
                                                                                                                                 (40, '2022-10-31 22:35:44', '2022-10-31 22:38:40', '567 Thái An, quận Liên Chiều', 'Tỉnh Sóc Trăng', 'dacthang@gmail.com', b'1', 'Đắc Thắng', '$2a$10$FOz/KCCqieGH64wlEQ.LSe2LHN9LGs7sEz/vn0x1Fjwg47GblN0/u', '0912299781'),
                                                                                                                                 (41, '2022-10-31 22:36:35', '2022-10-31 22:38:36', '198 Văn Cao, quận Hai Bà Trưng', 'Tỉnh Bắc Ninh', 'thaivu@gmail.com', b'1', 'Thái Vũ', '$2a$10$2Lak3P7PX09jp.1dv6IyK.i2RjwlvwjF3zsnUvcH78Kwe5thcGYqe', '0914455786'),
                                                                                                                                 (42, '2022-10-31 22:37:25', '2022-10-31 22:38:32', '78 Trương Định, phố Bà Triệu', 'Tỉnh Phú Thọ', 'quangminh@gmail.com', b'1', 'Quang Minh', '$2a$10$/7aemAfSLe41Gc8hbI5GOeXtm0iBbGuBl1AWSKSf8yo5vtGoDHVem', '0912299761'),
                                                                                                                                 (43, '2022-10-31 22:39:57', '2022-10-31 22:41:22', '129 Tây Sơn, quận Đống Đa', 'Thành phố Hà Nội', 'khanhlinh@gmail.com', b'1', 'Khánh Linh', '$2a$10$QqKYeBLyqrE9ufDvrjQXMOFesSYVpEa7xOlPvMldiU0VIscLOakN6', '0912244523'),
                                                                                                                                 (44, '2022-10-31 22:41:06', '2022-10-31 22:41:19', '901 Văn Phú, quận Đống Đa', 'Tỉnh Thừa Thiên Huế', 'thanhhang@gmail.com', b'1', 'Thanh Hằng', '$2a$10$pL6DUjSTyC9Nj8/CrNzlmO07nlKhNcf4e0x3M/2Ftw7Ic9gk9SEGa', '0914433251'),
                                                                                                                                 (46, '2022-11-03 09:59:25', '2022-11-03 10:00:57', NULL, NULL, 'thuyminh@gmail.com', b'1', 'Thùy Minh', '$2a$10$1P7ptnMIlgUjpYrTZfM2tOZHPOGmsudkmDR7gB43MjTI4ZFw3aBoi', '0933991112'),
                                                                                                                                 (48, '2022-11-06 12:04:15', '2022-11-06 12:04:31', '34 Tân Mai, quận Hoàng Mai', 'Thành phố Hà Nội', 'customer@gmail.com', b'1', 'Customer', '$2a$10$wBhwveOPVuBVVe2JhI9nO.Q1O43cGdOmRPmB.9WeZy2rFL3wopose', '0902172312'),
                                                                                                                                 (49, '2022-11-06 16:29:26', '2022-11-06 16:29:36', '198 Nguyễn Thái Học, phường Đống Đa', 'Tỉnh Quảng Bình', 'vancong@gmail.com', b'1', 'Văn Công', '$2a$10$sbA6IHy0zikM8GnIZ1fa5.wHC9wxHzNFRhGYDCdjbYs3KQ/pU4mVe', '0978912312'),
                                                                                                                                 (50, '2022-11-06 16:32:36', '2022-11-06 16:32:43', 'thaiyen@gmail.com', 'Tỉnh Bắc Kạn', 'thaiyen@gmail.com', b'1', 'Thái Yên', '$2a$10$etaGR9uTvt25Z7QtfDwnGuuNZgiJSM6taeXNybXsl.g1zAR.47kqC', '0902346717'),
                                                                                                                                 (51, '2022-11-06 16:39:12', '2022-11-06 16:39:19', '198 Nguyễn Thái Học, phường Đống Đa', 'Tỉnh Hà Nam', 'huynhnhu@gmail.com', b'1', 'Huỳnh Như', '$2a$10$3bnhbMO5RHAa4ZPzcqN6Zu.q4HfqUTVDAZMAPUzvNrHNh3FkXsPGq', '0902313212'),
                                                                                                                                 (52, '2022-11-06 16:40:49', '2022-11-06 16:41:01', '198 Nguyễn Chí Thanh, phường Đống Đa', 'Tỉnh Ninh Bình', 'thaibao@gmail.com', b'1', 'Thái Bảo', '$2a$10$mH.hkPoev2k86MpqlGij5Oacgjb52X5fXIPIFiwJjYlNXbH6gHB5C', '0902341256'),
                                                                                                                                 (53, '2022-11-06 16:41:51', '2022-11-06 16:43:28', '1981 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Phú Yên', 'vanmanh@gmail.com', b'1', 'Văn Mạnh', '$2a$10$eK/JUhCc7RiSg1cDl2lBt.pGwWp5FLYK2lKEslrfh4GMrY2VB7pye', '0912288321'),
                                                                                                                                 (54, '2022-11-06 16:44:15', '2022-11-06 16:44:33', '190 Hoàng Cầu, quận Cầu Giấy', 'Tỉnh Lai Châu', 'trungkien@gmail.com', b'1', 'Trung Kiên', '$2a$10$VIZNUPcDwfK05QC4t8/3bOXTmUpjhPk8EHb2y3S4nzMKzyMP4FtDK', '0902341231'),
                                                                                                                                 (55, '2022-11-06 16:46:10', '2022-11-06 16:46:24', '198 Văn Phú, quận Hà Đông', 'Thành phố Hà Nội', 'minhchi@gmail.com', b'1', 'Minh Chí', '$2a$10$kkLHliJZFnoTQDrzPI3Y2.Iw4Ej2JLEJxhkpeUsqX.kJVUjOLMiy.', '0981122341'),
                                                                                                                                 (56, '2022-11-06 16:47:50', '2022-11-06 16:48:01', '1001 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Điện Biên', 'linhchi@gmail.com', b'1', 'Linh Chi', '$2a$10$dayJDB7UpqsXq4fC1Wv.XO8g3BDFp0PFTd2d/h9G2m3Br3qhdaiie', '0902314578'),
                                                                                                                                 (57, '2022-11-06 16:51:34', '2022-11-06 16:51:43', '1901 Hoàng Cầu, quận Cầu Giấy', 'Tỉnh Yên Bái', 'huynhvan@gmail.com', b'1', 'Huỳnh Văn', '$2a$10$KKwU17AICQ19NFU7KHSEPubzR4S24GZE42LksYJRgyBm/UAD6PkbK', '0913341890'),
                                                                                                                                 (58, '2022-11-06 16:59:23', '2022-11-06 16:59:34', '190 Hoàng Cầu, quận Cầu Giấy', 'Tỉnh Quảng Ninh', 'thaison@gmail.com', b'1', 'Thái Sơn', '$2a$10$ihlCYVSUmBN4K4SixjHXk.qs2.HByReMEFPCQ7g/ay0hSlTqlGEs.', '0912233441'),
                                                                                                                                 (59, '2022-11-06 17:06:27', '2022-11-06 17:12:45', '99 Hoàng Văn Thụ, quận Hoàng Mai', 'Tỉnh Bắc Giang', 'thaiduc@gmail.com', b'1', 'Thái Đức', '$2a$10$Ew6./F0qxtNn3ZjA4ap0AeJ.OfE0ZncqR4hfAGU2nqeFr50k4VU7C', '0913300991'),
                                                                                                                                 (60, '2022-11-06 17:08:50', '2022-11-06 17:12:50', '190 Hoàng Cầu, quận Cầu Giấy', 'Tỉnh Vĩnh Phúc', 'minhvan1@gmail.com', b'1', 'Văn Minh', '$2a$10$Xc8Hn/6y7rZ/yFu.vww4qOCMhU64xdxxuhzzZBnnXr1KemwLwRWpK', '0912299089'),
                                                                                                                                 (61, '2022-11-06 17:16:34', '2022-11-06 17:19:56', '1001 Điện Biên Phủ, phường Điện Biên', 'Thành phố Cần Thơ', 'hoangduong@gmail.com', b'1', 'Hoàng Dương', '$2a$10$Pfn2S.xZYKJzTWZ8.8ouoefLGbU9dYi40uYGgUfBkARhmlYLIVSJS', '0913344521'),
                                                                                                                                 (62, '2022-11-06 17:18:20', '2022-11-06 17:19:52', '791 Hoàng Cầu, quận Cầu Giấy', 'Tỉnh Tiền Giang', 'haidang@gmail.com', b'1', 'Hải Đăng', '$2a$10$yyz3uDOu30LpK8SoRr/Iau2yurAp/gMk4CVIqdEIQCuvsqwzXvBOy', '0912288764'),
                                                                                                                                 (63, '2022-11-06 17:19:25', '2022-11-08 22:48:10', '1001 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Bắc Kạn', 'phuket@gmail.com', b'1', 'Nguyễn Văn Phú', '$2a$10$xT8VkErBQqHFdj.oiAFb7u8y3zhtRykS0R65gXQBQ48vckayEdyy2', '0913344890'),
                                                                                                                                 (64, '2022-11-07 23:33:29', '2022-11-07 23:34:21', '198 Nguyễn Chí Thanh, phường Đống Đa', 'Tỉnh Hà Giang', 'huynhde@gmail.com', b'1', 'Huỳnh Chiến', '$2a$10$goCqOPhqdBgej4UuxF6IBeF5OjZt9kCyu7.NW90H8xfLsLbe.ks5u', '0902134576'),
                                                                                                                                 (65, '2022-11-08 17:08:48', '2022-11-08 17:09:00', '198 Nguyễn Chí Thanh, phường Đống Đa', 'Tỉnh Hà Giang', 'huynhanh@gmail.com', b'1', 'Huỳnh Anh', '$2a$10$rBRTnJLaTK9z4kQLAChi8OT0spY0GHoQn4.b/rdF2wDnw1ub2JtD6', '0903412342'),
                                                                                                                                 (66, '2022-11-08 22:18:32', '2022-11-08 22:18:51', '198 Nguyễn Thái Học, phường Đống Đa', 'Tỉnh Hà Giang', 'vancong1@gmail.com', b'1', 'Văn Công', '$2a$10$2Ecwf0dUmQqxtvzK/OW./.8a94GrvPPZ3XDuGN82bmCVDkm0nNbGi', '0901234512'),
                                                                                                                                 (67, '2022-11-08 22:48:46', '2022-11-08 22:48:58', '1981 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Hà Giang', 'thaiyen11@gmail.com', b'1', 'Thái Yên', '$2a$10$xcnuv7RmThOnhfvhQmftlundG0RD5Uagw3RwfWshbozXGxjcAKlZu', '0978912311');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account_role`
--

CREATE TABLE `account_role` (
                                `account_id` bigint NOT NULL,
                                `role_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `account_role`
--

INSERT INTO `account_role` (`account_id`, `role_id`) VALUES
                                                         (6, 2),
                                                         (7, 3),
                                                         (7, 4),
                                                         (8, 1),
                                                         (9, 5),
                                                         (9, 3),
                                                         (15, 1),
                                                         (16, 1),
                                                         (17, 1),
                                                         (18, 1),
                                                         (19, 1),
                                                         (20, 1),
                                                         (21, 1),
                                                         (22, 1),
                                                         (25, 2),
                                                         (28, 3),
                                                         (28, 4),
                                                         (36, 3),
                                                         (37, 5),
                                                         (39, 1),
                                                         (40, 1),
                                                         (41, 1),
                                                         (42, 1),
                                                         (43, 1),
                                                         (44, 1),
                                                         (46, 4),
                                                         (48, 1),
                                                         (49, 1),
                                                         (50, 1),
                                                         (51, 1),
                                                         (52, 1),
                                                         (53, 1),
                                                         (54, 1),
                                                         (55, 1),
                                                         (56, 1),
                                                         (57, 1),
                                                         (58, 1),
                                                         (59, 1),
                                                         (60, 1),
                                                         (61, 1),
                                                         (62, 1),
                                                         (63, 1),
                                                         (64, 1),
                                                         (65, 1),
                                                         (66, 1),
                                                         (67, 1),
                                                         (2, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
                        `id` bigint NOT NULL,
                        `note` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`, `note`) VALUES
                                      (18, 'Đơn hàng cần ship gấp trong ngày hôm nay, giờ hành chính.'),
                                      (22, 'Ship vào thứ 2 ngày 9/11'),
                                      (23, ''),
                                      (24, ''),
                                      (25, NULL),
                                      (26, ''),
                                      (27, NULL),
                                      (28, NULL),
                                      (29, NULL),
                                      (30, NULL),
                                      (31, NULL),
                                      (32, NULL),
                                      (33, NULL),
                                      (34, ''),
                                      (35, ''),
                                      (36, NULL),
                                      (37, NULL),
                                      (38, NULL),
                                      (39, 'Đơn hàng cần ship gấp'),
                                      (40, 'Đơn hàng cần ship gấp'),
                                      (41, 'Đơn hàng cần ship gấp'),
                                      (42, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_item`
--

CREATE TABLE `cart_item` (
                             `id` bigint NOT NULL,
                             `quantity` int DEFAULT NULL,
                             `variant_id` bigint DEFAULT NULL,
                             `cart_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `cart_item`
--

INSERT INTO `cart_item` (`id`, `quantity`, `variant_id`, `cart_id`) VALUES
                                                                        (11, 4, 3, 18),
                                                                        (13, 3, 1, 18),
                                                                        (14, 3, 28, 18),
                                                                        (15, 3, 32, 22),
                                                                        (16, 2, 38, 23),
                                                                        (17, 2, 38, 24),
                                                                        (18, 2, 39, 25),
                                                                        (20, 2, 26, 26),
                                                                        (21, 1, 27, 26),
                                                                        (22, 1, 7, 27),
                                                                        (23, 1, 28, 28),
                                                                        (24, 1, 16, 29),
                                                                        (25, 1, 3, 30),
                                                                        (26, 1, 32, 31),
                                                                        (27, 1, 13, 32),
                                                                        (28, 1, 43, 33),
                                                                        (29, 1, 43, 34),
                                                                        (30, 1, 41, 34),
                                                                        (31, 2, 30, 35),
                                                                        (32, 1, 43, 35),
                                                                        (33, 1, 52, 36),
                                                                        (34, 1, 48, 37),
                                                                        (35, 1, 57, 38),
                                                                        (37, 3, 51, 39),
                                                                        (50, 3, 51, 40),
                                                                        (51, 3, 51, 41),
                                                                        (52, 1, 10, 42);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
                            `id` bigint NOT NULL,
                            `created_at` datetime DEFAULT NULL,
                            `updated_at` datetime DEFAULT NULL,
                            `description` text,
                            `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `created_at`, `updated_at`, `description`, `name`) VALUES
                                                                                     (1, '2022-10-20 12:14:00', '2022-10-20 12:14:00', 'Sản phẩm thời trang dành cho nam giới', 'Men'),
                                                                                     (2, '2022-10-20 12:14:00', '2022-10-20 12:14:00', 'Sản phẩm thời trang dành cho nữ giới', 'Women'),
                                                                                     (3, '2022-10-20 12:14:00', '2022-10-20 12:14:00', 'Sản phẩm thời trang nổi bật', 'Featured Products'),
                                                                                     (4, '2022-10-20 12:14:00', '2022-10-20 12:14:00', 'Sản phẩm thời trang mới', 'New Products'),
                                                                                     (13, '2022-10-21 09:23:20', '2022-10-21 09:23:20', 'Thời trang dành riêng cho trẻ em', 'Kid'),
                                                                                     (15, '2022-10-21 10:10:43', '2022-10-21 10:10:43', 'Sản phẩm bán chạy', 'Hot Products'),
                                                                                     (16, '2022-11-01 10:11:07', '2022-11-01 10:11:07', 'Sản phẩm thời trang xu hướng', 'Trend Products'),
                                                                                     (20, '2022-11-08 22:53:59', '2022-11-08 22:53:59', 'Sản phẩm khuyến mãi', 'Discount');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image`
--

CREATE TABLE `image` (
                         `id` bigint NOT NULL,
                         `url` varchar(255) DEFAULT NULL,
                         `product_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `image`
--

INSERT INTO `image` (`id`, `url`, `product_id`) VALUES
                                                    (7, '1666704151014', 13),
                                                    (8, '1666704151041', 13),
                                                    (9, '1666704151055', 13),
                                                    (11, '1666704525347', 14),
                                                    (12, '1666704525393', 14),
                                                    (15, '1666761315371', 17),
                                                    (16, '1666761315421', 17),
                                                    (25, '1666848003342', 14),
                                                    (27, '1666860421679', 14),
                                                    (30, '1666860966477', 13),
                                                    (32, '1666861149487', 10),
                                                    (33, '1666861149515', 10),
                                                    (34, '1666861149544', 10),
                                                    (35, '1666861205355', 9),
                                                    (36, '1666861205380', 9),
                                                    (37, '1666861205395', 9),
                                                    (39, '1666861436911', 5),
                                                    (40, '1666861436939', 5),
                                                    (41, '1666861436956', 5),
                                                    (46, '1666861626805', 3),
                                                    (47, '1666861626831', 3),
                                                    (48, '1666861647170', 3),
                                                    (49, '1666877163720', 1),
                                                    (50, '1666877163807', 1),
                                                    (51, '1666877163826', 1),
                                                    (53, '1667289519106', 18),
                                                    (54, '1667289519134', 18),
                                                    (55, '1667289519165', 18),
                                                    (56, '1667290074377', 19),
                                                    (57, '1667290074399', 19),
                                                    (58, '1667290074425', 19),
                                                    (59, '1667798353507', 20),
                                                    (60, '1667798353539', 20),
                                                    (61, '1667798353561', 20),
                                                    (62, '1667798842507', 21),
                                                    (63, '1667798842531', 21),
                                                    (64, '1667798842561', 21),
                                                    (65, '1667812395069', 22),
                                                    (66, '1667812395111', 22),
                                                    (67, '1667812577653', 23),
                                                    (68, '1667812577688', 23),
                                                    (69, '1667812775197', 24),
                                                    (70, '1667812775217', 24),
                                                    (71, '1667812775235', 24),
                                                    (72, '1667836016664', 25),
                                                    (73, '1667836016824', 25),
                                                    (74, '1667836016854', 25),
                                                    (78, '1667902312781', 27),
                                                    (79, '1667902312820', 27),
                                                    (81, '1667923035767', 27),
                                                    (82, '1667923186710', 28),
                                                    (83, '1667923186740', 28),
                                                    (84, '1667924875287', 29),
                                                    (85, '1667924875306', 29),
                                                    (86, '1667924875324', 29);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
                          `id` varchar(255) NOT NULL,
                          `created_at` datetime DEFAULT NULL,
                          `updated_at` datetime DEFAULT NULL,
                          `note` text,
                          `total` double DEFAULT NULL,
                          `account_id` bigint DEFAULT NULL,
                          `fulfillment` varchar(255) DEFAULT NULL,
                          `payment` varchar(255) DEFAULT NULL,
                          `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`id`, `created_at`, `updated_at`, `note`, `total`, `account_id`, `fulfillment`, `payment`, `status`) VALUES
                                                                                                                               ('DH-1', '2022-10-30 10:43:33', '2022-10-31 11:51:43', 'note', 1360000, 21, 'UNFULFILLED', 'PAID', 'CANCELED'),
                                                                                                                               ('DH-10', '2022-11-05 15:53:28', '2022-11-05 15:53:28', '', 880000, 2, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-11', '2022-11-05 15:58:36', '2022-11-05 15:58:36', NULL, 880000, 2, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-12', '2022-11-07 00:08:59', '2022-11-07 00:08:59', '', 825000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-13', '2022-11-07 00:09:34', '2022-11-07 00:09:34', NULL, 290000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-14', '2022-11-07 00:49:23', '2022-11-07 00:49:23', NULL, 220000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-15', '2022-11-07 00:50:07', '2022-11-07 00:50:07', NULL, 390000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-16', '2022-11-07 00:50:56', '2022-11-07 00:50:56', NULL, 250000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-17', '2022-11-07 00:51:52', '2022-11-07 00:51:52', NULL, 265000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-18', '2022-11-07 08:27:22', '2022-11-07 12:11:14', '', 360000, 63, 'UNFULFILLED', 'PAID', 'CONFIRMED'),
                                                                                                                               ('DH-19', '2022-11-07 12:13:44', '2022-11-07 12:13:44', '', 820000, NULL, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-2', '2022-10-30 10:52:21', '2022-10-31 11:56:16', 'Đơn hàng ship trong ngày, giờ hành chính', 1940000, 21, 'PARTIALLY_FULFILLED', 'PAID', 'CANCELED'),
                                                                                                                               ('DH-20', '2022-11-07 14:25:46', '2022-11-07 14:25:46', NULL, 405000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-21', '2022-11-07 14:27:07', '2022-11-07 14:27:07', '', 845000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-22', '2022-11-07 14:28:15', '2022-11-07 22:40:07', '', 785000, 63, 'UNFULFILLED', 'UNPAID', 'CONFIRMED'),
                                                                                                                               ('DH-23', '2022-11-07 23:11:53', '2022-11-07 23:11:53', NULL, 199000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-24', '2022-11-07 23:12:12', '2022-11-07 23:12:12', NULL, 199000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-25', '2022-11-07 23:17:52', '2022-11-07 23:17:52', NULL, 400000, 63, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-26', '2022-11-08 17:08:05', '2022-11-08 17:08:05', 'Đơn hàng ship gấp', 440000, NULL, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-27', '2022-11-08 17:55:41', '2022-11-08 22:38:53', '', 199000, 63, 'PARTIALLY_FULFILLED', 'PAID', 'CONFIRMED'),
                                                                                                                               ('DH-28', '2022-11-08 22:10:49', '2022-11-08 22:39:21', 'Đơn hàng cần ship gấp', 360000, NULL, 'UNFULFILLED', 'UNPAID', 'CANCELED'),
                                                                                                                               ('DH-29', '2022-11-08 22:14:18', '2022-11-08 22:14:18', 'Đơn hàng cần ship gấp', 360000, NULL, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-3', '2022-10-31 11:57:40', '2022-10-31 22:52:49', 'Đơn hàng ship trong tuần, giờ hành chính, (t2 - t6)', 855000, NULL, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-30', '2022-11-08 22:15:28', '2022-11-08 22:15:28', 'Đơn hàng cần ship gấp', 360000, NULL, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-31', '2022-11-08 22:43:52', '2022-11-08 22:43:52', 'Đơn hàng cần ship gấp', 1730000, NULL, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-4', '2022-11-05 14:43:50', '2022-11-05 14:43:50', 'Đơn hàng cần ship gấp trong ngày hôm nay, giờ hành chính.', 2230000, 2, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-5', '2022-11-05 14:48:59', '2022-11-05 14:48:59', 'Đơn hàng cần ship gấp trong ngày hôm nay, giờ hành chính.', 2230000, 2, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-6', '2022-11-05 14:51:40', '2022-11-05 14:51:40', 'Đơn hàng cần ship gấp trong ngày hôm nay, giờ hành chính.', 2230000, 2, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-7', '2022-11-05 15:08:59', '2022-11-05 15:08:59', 'Đơn hàng cần ship gấp trong ngày hôm nay, giờ hành chính.', 2230000, 2, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-8', '2022-11-05 15:29:20', '2022-11-05 15:29:20', 'Ship vào thứ 2 ngày 9/11', 795000, 2, 'UNFULFILLED', 'UNPAID', 'NEW'),
                                                                                                                               ('DH-9', '2022-11-05 15:36:56', '2022-11-05 15:36:56', '', 880000, 2, 'UNFULFILLED', 'UNPAID', 'NEW');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_item`
--

CREATE TABLE `order_item` (
                              `id` bigint NOT NULL,
                              `price` double DEFAULT NULL,
                              `product_id` bigint DEFAULT NULL,
                              `quantity` int DEFAULT NULL,
                              `total` double DEFAULT NULL,
                              `variant_id` bigint DEFAULT NULL,
                              `order_id` varchar(255) DEFAULT NULL,
                              `product_name` varchar(255) DEFAULT NULL,
                              `variant_color` varchar(255) DEFAULT NULL,
                              `variant_size` varchar(255) DEFAULT NULL,
                              `variant_sku` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `order_item`
--

INSERT INTO `order_item` (`id`, `price`, `product_id`, `quantity`, `total`, `variant_id`, `order_id`, `product_name`, `variant_color`, `variant_size`, `variant_sku`) VALUES
                                                                                                                                                                          (35, 300000, 14, 2, 600000, 24, 'DH-1', 'Áo giữ nhiệt WM nữ Original cổ lọ ', 'Trắng sữa', 'XL', 'G9SMT007XL'),
                                                                                                                                                                          (36, 380000, 14, 2, 760000, 10, 'DH-1', 'Áo giữ nhiệt WM nữ Original cổ lọ ', 'Trắng sữa', 'L', 'G9SMT007L'),
                                                                                                                                                                          (37, 390000, 14, 2, 780000, 16, 'DH-2', 'Áo giữ nhiệt WM nữ Original cổ lọ ', 'Trắng sữa', 'S', 'G9SMT007S'),
                                                                                                                                                                          (38, 290000, 13, 4, 1160000, 8, 'DH-2', 'Áo len nữ có cổ dài tay dáng ôm', 'Nâu', 'M', 'I9SWT503M'),
                                                                                                                                                                          (39, 290000, 13, 2, 580000, 8, 'DH-3', 'Áo len nữ có cổ dài tay dáng ôm', 'Nâu', 'M', 'I9SWT503M'),
                                                                                                                                                                          (40, 275000, 17, 1, 275000, 27, 'DH-3', 'Áo len nữ dài tay cổ xẻ V có dây buộc', 'Trắng', 'M', 'I9SWT507M'),
                                                                                                                                                                          (41, 250000, 10, 18, NULL, 3, 'DH-4', 'Áo T-Shirt nữ khóa nẹp dáng ngắn', 'Trắng ', 'L', 'I9TSH624L'),
                                                                                                                                                                          (42, 190000, 9, 10, NULL, 1, 'DH-4', 'Áo T-shirt cổ tròn dài tay', 'Tím nhạt', 'L', 'I9TSH635L'),
                                                                                                                                                                          (43, 220000, 5, 100, NULL, 28, 'DH-4', 'Áo T-Shirt nữ cổ tròn tay bồng', 'Xanh', 'L', 'I9TSH625L'),
                                                                                                                                                                          (44, 250000, 10, 18, NULL, 3, 'DH-5', 'Áo T-Shirt nữ khóa nẹp dáng ngắn', 'Trắng ', 'L', 'I9TSH624L'),
                                                                                                                                                                          (45, 190000, 9, 10, NULL, 1, 'DH-5', 'Áo T-shirt cổ tròn dài tay', 'Tím nhạt', 'L', 'I9TSH635L'),
                                                                                                                                                                          (46, 220000, 5, 100, NULL, 28, 'DH-5', 'Áo T-Shirt nữ cổ tròn tay bồng', 'Xanh', 'L', 'I9TSH625L'),
                                                                                                                                                                          (47, 250000, 10, 18, NULL, 3, 'DH-6', 'Áo T-Shirt nữ khóa nẹp dáng ngắn', 'Trắng ', 'L', 'I9TSH624L'),
                                                                                                                                                                          (48, 190000, 9, 10, NULL, 1, 'DH-6', 'Áo T-shirt cổ tròn dài tay', 'Tím nhạt', 'L', 'I9TSH635L'),
                                                                                                                                                                          (49, 220000, 5, 100, NULL, 28, 'DH-6', 'Áo T-Shirt nữ cổ tròn tay bồng', 'Xanh', 'L', 'I9TSH625L'),
                                                                                                                                                                          (50, 250000, 10, 18, NULL, 3, 'DH-7', 'Áo T-Shirt nữ khóa nẹp dáng ngắn', 'Trắng ', 'L', 'I9TSH624L'),
                                                                                                                                                                          (51, 190000, 9, 10, NULL, 1, 'DH-7', 'Áo T-shirt cổ tròn dài tay', 'Tím nhạt', 'L', 'I9TSH635L'),
                                                                                                                                                                          (52, 220000, 5, 100, NULL, 28, 'DH-7', 'Áo T-Shirt nữ cổ tròn tay bồng', 'Xanh', 'L', 'I9TSH625L'),
                                                                                                                                                                          (53, 265000, 1, 100, NULL, 32, 'DH-8', 'Áo T-Shirt nữ cổ vuông dài tay', 'Hồng', 'L', 'I9TSH636L'),
                                                                                                                                                                          (54, 440000, 19, 100, NULL, 38, 'DH-9', 'Áo Gile nam chất liệu len cổ tim', 'Be', 'L', 'C7WCO001L'),
                                                                                                                                                                          (55, 440000, 19, 100, NULL, 38, 'DH-10', 'Áo Gile nam chất liệu len cổ tim', 'Be', 'L', 'C7WCO001L'),
                                                                                                                                                                          (56, 440000, 19, 2, NULL, 39, 'DH-11', 'Áo Gile nam chất liệu len cổ tim', 'Be', 'M', 'C7WCO001M'),
                                                                                                                                                                          (57, 275000, 17, 2, NULL, 26, 'DH-12', 'Áo len nữ dài tay cổ xẻ V có dây buộc', 'Trắng', 'L', 'I9SWT507L'),
                                                                                                                                                                          (58, 275000, 17, 1, NULL, 27, 'DH-12', 'Áo len nữ dài tay cổ xẻ V có dây buộc', 'Trắng', 'M', 'I9SWT507M'),
                                                                                                                                                                          (59, 290000, 13, 1, NULL, 7, 'DH-13', 'Áo len nữ có cổ dài tay dáng ôm', 'Nâu', 'L', 'I9SWT503L'),
                                                                                                                                                                          (60, 220000, 5, 1, NULL, 28, 'DH-14', 'Áo T-Shirt nữ cổ tròn tay bồng', 'Xanh', 'L', 'I9TSH625L'),
                                                                                                                                                                          (61, 390000, 14, 1, NULL, 16, 'DH-15', 'Áo giữ nhiệt WM nữ Original cổ lọ ', 'Trắng sữa', 'S', 'G9SMT007S'),
                                                                                                                                                                          (62, 250000, 10, 1, NULL, 3, 'DH-16', 'Áo T-Shirt nữ khóa nẹp dáng ngắn', 'Trắng', 'L', 'I9TSH624L'),
                                                                                                                                                                          (63, 265000, 1, 1, NULL, 32, 'DH-17', 'Áo T-Shirt nữ cổ vuông dài tay', 'Hồng', 'L', 'I9TSH636L'),
                                                                                                                                                                          (64, 360000, 14, 1, 360000, 13, 'DH-18', 'Áo giữ nhiệt WM nữ Original cổ lọ ', 'Hồng tím', 'L', 'G9SMT006L'),
                                                                                                                                                                          (65, 410000, 19, 2, 820000, 40, 'DH-19', 'Áo Gile nam chất liệu len cổ tim', 'Be', 'S', 'C7WCO001S'),
                                                                                                                                                                          (66, 405000, 21, 1, 405000, 43, 'DH-20', 'Quần nỉ nữ cạp chun', 'Loang xanh', 'L', 'I9JOG502L'),
                                                                                                                                                                          (67, 405000, 21, 1, 405000, 43, 'DH-21', 'Quần nỉ nữ cạp chun', 'Loang xanh', 'L', 'I9JOG502L'),
                                                                                                                                                                          (68, 440000, 20, 1, 440000, 41, 'DH-21', 'Áo nỉ nam chui đầu', 'Xám', 'L', 'I7SWS553L'),
                                                                                                                                                                          (69, 190000, 3, 2, 380000, 30, 'DH-22', 'Áo T-Shirt nữ cổ lọ dáng ôm', 'Hồng nâu', 'L', 'I9TSH622L'),
                                                                                                                                                                          (70, 405000, 21, 1, 405000, 43, 'DH-22', 'Quần nỉ nữ cạp chun', 'Loang xanh', 'L', 'I9JOG502L'),
                                                                                                                                                                          (71, 199000, 25, 1, 199000, 52, 'DH-23', 'Áo Polo nữ cổ trơn dệt nổi 2 sọc', 'Vàng', 'L', 'I9POL810KL'),
                                                                                                                                                                          (72, 199000, 25, 1, 199000, 52, 'DH-24', 'Áo Polo nữ cổ trơn dệt nổi 2 sọc', 'Vàng', 'L', 'I9POL810KL'),
                                                                                                                                                                          (73, 400000, 23, 1, 400000, 48, 'DH-25', 'Quần Khakis bé trai cạp chun', 'Đen', 'L', 'N5KHA005L'),
                                                                                                                                                                          (74, 440000, 19, 1, 440000, 39, 'DH-26', 'Áo Gile nam chất liệu len cổ tim', 'Be', 'M', 'C7WCO001M'),
                                                                                                                                                                          (75, 199000, 27, 1, 199000, 57, 'DH-27', 'Áo Polo nữ cổ vân trắng', 'Tím', 'L', 'I9POL811KL'),
                                                                                                                                                                          (76, 120000, 24, 3, 360000, 51, 'DH-28', 'Áo giữ nhiệt nữ WM Air cổ lọ', 'Xanh', 'M', 'G9SMT025M'),
                                                                                                                                                                          (77, 120000, 24, 3, 360000, 51, 'DH-29', 'Áo giữ nhiệt nữ WM Air cổ lọ', 'Xanh', 'M', 'G9SMT025M'),
                                                                                                                                                                          (78, 120000, 24, 3, 360000, 51, 'DH-30', 'Áo giữ nhiệt nữ WM Air cổ lọ', 'Xanh', 'M', 'G9SMT025M'),
                                                                                                                                                                          (79, 410000, 19, 1, 410000, 40, 'DH-31', 'Áo Gile nam chất liệu len cổ tim', 'Be', 'S', 'C7WCO001S'),
                                                                                                                                                                          (80, 440000, 19, 3, 1320000, 39, 'DH-31', 'Áo Gile nam chất liệu len cổ tim', 'Be', 'M', 'C7WCO001M');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
                           `id` bigint NOT NULL,
                           `created_at` datetime DEFAULT NULL,
                           `updated_at` datetime DEFAULT NULL,
                           `content` text,
                           `description` text,
                           `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `created_at`, `updated_at`, `content`, `description`, `name`) VALUES
                                                                                               (1, '2022-10-21 16:39:58', '2022-10-27 20:27:20', '- Áo T-shirt chất liệu cotton nhẹ, co dãn 4 chiều.\n- Thiết kế phom basic dễ mặc, thoải mái trong mọi hoạt động.\n- Kiểu dáng và màu sắc trẻ trung, hiện đại phù hợp với cô nàng năng động.', 'Áo T-Shirt nữ cổ vuông dài tay', 'Áo T-Shirt nữ cổ vuông dài tay'),
                                                                                               (3, '2022-10-24 14:22:29', '2022-11-01 10:26:46', 'Áo T-Shirt  nữ cổ lọ dáng ôm\n\n- Áo T-shirt chất liệu cotton nhẹ, co dãn 4 chiều.\n- Thiết kế phom basic dễ mặc, thoải mái trong mọi hoạt động.\n- Kiểu dáng và màu sắc trẻ trung, hiện đại phù hợp với cô nàng năng động.', 'Áo T-Shirt  nữ cổ lọ dáng ôm', 'Áo T-Shirt nữ cổ lọ dáng ôm'),
                                                                                               (5, '2022-10-24 15:08:58', '2022-11-01 10:27:05', 'Áo T-Shirt nữ cổ tròn tay bồng\n\n- Áo T-shirt chất liệu cotton nhẹ, co dãn 4 chiều.\n- Thiết kế phom basic dễ mặc, thoải mái trong mọi hoạt động.\n- Kiểu dáng và màu sắc trẻ trung, hiện đại phù hợp với cô nàng năng động.', 'Áo T-Shirt nữ cổ tròn tay bồng', 'Áo T-Shirt nữ cổ tròn tay bồng'),
                                                                                               (9, '2022-10-24 20:59:01', '2022-11-01 10:27:19', '', 'Áo T-shirt cổ tròn dài tay', 'Áo T-shirt cổ tròn dài tay'),
                                                                                               (10, '2022-10-24 21:07:38', '2022-10-27 16:08:11', 'Áo T-Shirt nữ khóa nẹp dáng ngắn\n\n- Áo T-shirt chất liệu cotton nhẹ, co dãn 4 chiều.\n- Thiết kế phom basic dễ mặc, thoải mái trong mọi hoạt động.\n- Kiểu dáng và màu sắc trẻ trung, hiện đại phù hợp với cô nàng năng động.', 'Áo T-Shirt nữ khóa nẹp dáng ngắn', 'Áo T-Shirt nữ khóa nẹp dáng ngắn'),
                                                                                               (13, '2022-10-25 20:22:31', '2022-11-03 19:52:28', 'Áo len nữ có cổ dài tay dáng ôm\n\nÁo len TokyoLife kiểu dáng Basic, thanh lịch phù hợp với những ngày tiết trời se lạnh.\nĐường may tinh tế với sự kết hợp từ chất liệu len sợi co dãn cho cảm giác mềm mại, thoải mái khi mặc.\nÁo dễ dàng phối cùng quần jeans, chân váy, giày bốt, thích hợp diện đi làm, đi học, đi chơi...', 'Áo len nữ có cổ dài tay dáng ôm', 'Áo len nữ có cổ dài tay dáng ôm'),
                                                                                               (14, '2022-10-25 20:28:45', '2022-10-27 15:47:05', 'Áo giữ nhiệt WM nữ Original cổ lọ \n\n- Áo giữ nhiệt thông minh từ TokyoLife với 3 dòng tiên tiến: WarmMax Air/Original/Extra giữ ấm cơ thể vượt trội nhờ những cải tiến trong chất liệu và thiết kế sẽ cho bạn ngày dài hoạt động thoải mái chẳng sợ lạnh.\n- Công thức vải hiện đại với những thớ vải mềm xốp, thoáng khí, giữ ấm vượt trội.\n- Sử dụng cấu trúc xơ rỗng đặc biệt để cách nhiệt, ngăn nhiệt bên ngoài tác động vào cơ thể.\n- Không gây kích ứng da, khử mùi hiệu quả.\n- Sợi vải hấp thụ nước thoát ra từ cơ thể, giúp thân nhiệt ổn định, tránh tình trạng lạnh đột ngột.\n- Co giãn cực kỳ tốt, độ bền cao, không biến dạng dù giặt nhiều lần.\n- Thiết kế mỏng nhẹ, ôm sát cơ thể, tạo cảm giác \"mặc như không\", thoải mái khi vận động.', 'Áo giữ nhiệt WM nữ Original cổ lọ ', 'Áo giữ nhiệt WM nữ Original cổ lọ '),
                                                                                               (17, '2022-10-26 12:15:15', '2022-11-08 23:32:01', 'Áo len nữ dài tay cổ xẻ V có dây buộc\n\nÁo len TokyoLife kiểu dáng Basic, thanh lịch phù hợp với những ngày tiết trời se lạnh.\nĐường may tinh tế với sự kết hợp từ chất liệu len sợi co dãn cho cảm giác mềm mại, thoải mái khi mặc.\nÁo dễ dàng phối cùng quần jeans, chân váy, giày bốt, thích hợp diện đi làm, đi học, đi chơi...', 'Áo len nữ dài tay cổ xẻ V có dây buộc.', 'Áo len nữ dài tay cổ xẻ V có dây buộc'),
                                                                                               (18, '2022-11-01 14:58:39', '2022-11-01 14:58:39', 'Áo khoác 1 lớp Nữ dáng suông\n\nÁo khoác chất liệu vải dày, ấm áp, chống gió tốt.\nMàu sắc thời trang cá tính, dễ kết hợp với nhiều loại trang phục.\nThiết kế trẻ trung, kiểu dáng cá tính tôn lên cá tính người mặc.\nÁo phù hợp với bạn trẻ yêu thích phong cách thời trang khỏe khoắn, năng động.', 'Áo khoác 1 lớp Nữ dáng suông', 'Áo khoác 1 lớp Nữ dáng suông'),
                                                                                               (19, '2022-11-01 15:07:54', '2022-11-02 21:44:50', 'Áo Gile nam chất liệu len cổ tim\n\nÁo len TokyoLife kiểu dáng Basic, thanh lịch phù hợp với những ngày tiết trời se lạnh.\nĐường may tinh tế với sự kết hợp từ chất liệu len sợi co dãn cho cảm giác mềm mại, thoải mái khi mặc.\nÁo dễ dàng phối cùng quần jeans, chân váy, giày bốt, thích hợp diện đi làm, đi học, đi chơi...\n', 'Áo Gile nam chất liệu len cổ tim', 'Áo Gile nam chất liệu len cổ tim'),
                                                                                               (20, '2022-11-07 12:19:13', '2022-11-07 12:19:13', 'Áo nỉ nam chui đầu\n\nÁo nỉ là trang phục được nhiều bạn trẻ yêu thích bởi kiểu dáng đa dạng, dễ mặc, thích hợp mọi hoàn cảnh.\nChất liệu nỉ mềm mại, giữ ấm cơ thể luôn mang đến cảm giác ấm áp và thoải mái khi mặc.\nÁo có thể kết hợp cùng quần jeans, quần âuhoặc nhiều kiểu chân váy nữ tính khác nhau.', 'Áo nỉ nam chui đầu', 'Áo nỉ nam chui đầu'),
                                                                                               (21, '2022-11-07 12:27:22', '2022-11-07 22:47:56', 'Quần nỉ nữ cạp chun\n\nMàu sắc trẻ trung, lịch lãm tôn lên vẻ ngoài của người mặc.\nPhom dáng hiện đại, khỏe khoắn, cá tính thích hợp đi làm, đi chơi.\nChất liệu vải dày dặn, không bai xù, thấm hút mồ hôi tốt tạo cảm giác thoải mái khi vận động.\nQuần dễ dàng kết hợp với nhiều trang phục và phụ kiện để tạo nên cá tính riêng cho phái đẹp.', 'Quần nỉ nữ cạp chun', 'Quần nỉ nữ cạp chun'),
                                                                                               (22, '2022-11-07 16:13:15', '2022-11-07 16:13:15', 'Áo len bé gái cổ tròn gắn nơ \nÁo len không thể thiếu khi thời tiết se lạnh.\nSản phẩm làm từ len sợi mềm mại và ấm áp.\nBé thoải mái diện những bộ đồ mình thích và mặc cùng áo len xinh xắn để có thể đi học, đi chơi cùng gia đình.', 'Áo len bé gái cổ tròn gắn nơ', 'Áo len bé gái cổ tròn gắn nơ'),
                                                                                               (23, '2022-11-07 16:16:17', '2022-11-07 16:16:17', 'Quần Khakis bé trai cạp chun\n\nQuần trẻ em là sản phẩm ưa chuộng của nhiều mẹ khi lựa chọn đồ cho bé yêu.\nKiểu dáng đa dạng, chất liệu sợi tổng hợp từ sợi cotton tạo ra bề mặt vải bền, thấm mồ hôi và co giãn tốt, giúp trẻ thoải mái khi mặc đồ.\nQuần có nhiều mẫu mã, nhấn nhá, cách điệu để thêm phần nổi bật, phù hợp với sở thích với các bé.\nSản phẩm có thể kết hợp với nhiều trang phục khác nhau, phù hợp cho bé cả khi đi học, đi chơi.', 'Quần Khakis bé trai cạp chun', 'Quần Khakis bé trai cạp chun'),
                                                                                               (24, '2022-11-07 16:19:35', '2022-11-08 21:11:47', 'Áo giữ nhiệt nữ WM Air cổ lọ\n\nÁo giữ nhiệt thông minh từ TokyoLife với 3 dòng tiên tiến: WarmMax Air/Original/Extra giữ ấm cơ thể vượt trội nhờ những cải tiến trong chất liệu và thiết kế sẽ cho bạn ngày dài hoạt động thoải mái chẳng sợ lạnh.\nCông thức vải hiện đại với những thớ vải mềm xốp, thoáng khí, giữ ấm vượt trội.\nSử dụng cấu trúc xơ rỗng đặc biệt để cách nhiệt, ngăn nhiệt bên ngoài tác động vào cơ thể.\nKhông gây kích ứng da, khử mùi hiệu quả.\nSợi vải hấp thụ nước thoát ra từ cơ thể, giúp thân nhiệt ổn định, tránh tình trạng lạnh đột ngột.\nCo giãn cực kỳ tốt, độ bền cao, không biến dạng dù giặt nhiều lần.\nThiết kế mỏng nhẹ, ôm sát cơ thể, tạo cảm giác \"mặc như không\", thoải mái khi vận động.\n', 'Áo giữ nhiệt nữ WM Air cổ lọ\n', 'Áo giữ nhiệt nữ WM Air cổ lọ'),
                                                                                               (25, '2022-11-07 22:46:56', '2022-11-07 22:46:56', 'Áo Polo nữ cổ trơn dệt nổi 2 sọc\n\nThiết kế đơn giản, tinh tế, mang tới sự mới mẻ về phong cách và linh hoạt trong việc phối đồ.\nMàu sắc trẻ trung đem đến vẻ ngoài năng động, hiện đại, khỏe khoắn cho phái mạnh.\nSản phẩm được thiết kế từ chất liệu cotton mềm mại, họa tiết màu sắc nổi bật, phù hợp với nhiều phom dáng và hoàn cảnh khác nhau.', 'Áo Polo nữ cổ trơn dệt nổi 2 sọc', 'Áo Polo nữ cổ trơn dệt nổi 2 sọc'),
                                                                                               (27, '2022-11-08 17:11:53', '2022-11-08 22:57:48', 'Áo Polo nữ cổ vân trắng \n\n- Sản phẩm có kiểu dáng năng động, khỏe khoắn.\n- Màu sắc và họa tiết kết hợp đơn giản, phóng khoáng, bền màu, không độc hại, an toàn cho da.\n- Áo thích hợp: mặc đi học, đi chơi, picnic, vận động thể thao, mặc ở nhà...\n- Sản phẩm làm từ chất liệu cotton tổng hợp giúp cho bề mặt vải mỏng, mát và nhanh khô.\n- Đường may cẩn thận, tinh tế, tăng tính thẩm mỹ và độ bền cho áo', 'Áo Polo nữ cổ vân trắng', 'Áo Polo nữ cổ vân trắng'),
                                                                                               (28, '2022-11-08 22:59:47', '2022-11-08 23:26:21', 'Áo nỉ bé trai in hình phi hành gia C5SWS003L\n\nÁo nỉ không thể thiếu khi thời tiết se lạnh.\nSản phẩm mềm mại và ấm áp.\nBé thoải mái diện những bộ đồ mình thích để có thể đi học, đi chơi cùng gia đình.', 'Áo nỉ bé trai in hình phi hành gia', 'Áo nỉ bé trai in hình phi hành gia'),
                                                                                               (29, '2022-11-08 23:27:55', '2022-11-08 23:28:28', 'Áo Polo nam cổ vân trắng \n\n- Sản phẩm có kiểu dáng năng động, khỏe khoắn.\n- Màu sắc và họa tiết kết hợp đơn giản, phóng khoáng, bền màu, không độc hại, an toàn cho da.\n- Áo thích hợp: mặc đi học, đi chơi, picnic, vận động thể thao, mặc ở nhà...\n- Sản phẩm làm từ chất liệu cotton tổng hợp giúp cho bề mặt vải mỏng, mát và nhanh khô.\n- Đường may cẩn thận, tinh tế, tăng tính thẩm mỹ và độ bền cho áo.', 'Áo Polo nam cổ vân trắng', 'Áo Polo nam cổ vân trắng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_category`
--

CREATE TABLE `product_category` (
                                    `product_id` bigint NOT NULL,
                                    `category_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `product_category`
--

INSERT INTO `product_category` (`product_id`, `category_id`) VALUES
                                                                 (19, 1),
                                                                 (20, 1),
                                                                 (29, 1),
                                                                 (1, 2),
                                                                 (3, 2),
                                                                 (5, 2),
                                                                 (9, 2),
                                                                 (10, 2),
                                                                 (13, 2),
                                                                 (14, 2),
                                                                 (17, 2),
                                                                 (18, 2),
                                                                 (21, 2),
                                                                 (24, 2),
                                                                 (25, 2),
                                                                 (27, 2),
                                                                 (3, 3),
                                                                 (10, 3),
                                                                 (14, 3),
                                                                 (18, 3),
                                                                 (24, 3),
                                                                 (25, 3),
                                                                 (3, 4),
                                                                 (5, 4),
                                                                 (9, 4),
                                                                 (14, 4),
                                                                 (19, 4),
                                                                 (20, 4),
                                                                 (21, 4),
                                                                 (24, 4),
                                                                 (27, 4),
                                                                 (22, 13),
                                                                 (23, 13),
                                                                 (28, 13),
                                                                 (1, 15),
                                                                 (5, 15),
                                                                 (13, 15),
                                                                 (19, 15),
                                                                 (24, 15),
                                                                 (27, 15),
                                                                 (9, 16),
                                                                 (13, 16),
                                                                 (17, 16),
                                                                 (21, 16),
                                                                 (28, 16);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `refund`
--

CREATE TABLE `refund` (
                          `id` bigint NOT NULL,
                          `created_at` datetime DEFAULT NULL,
                          `updated_at` datetime DEFAULT NULL,
                          `order_item_id` bigint DEFAULT NULL,
                          `total` double DEFAULT NULL,
                          `order_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
                        `id` bigint NOT NULL,
                        `created_at` datetime DEFAULT NULL,
                        `updated_at` datetime DEFAULT NULL,
                        `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `created_at`, `updated_at`, `name`) VALUES
                                                                  (1, '2022-10-14 23:25:13', '2022-10-14 23:25:13', 'CUSTOMER'),
                                                                  (2, '2022-10-14 23:25:13', '2022-10-14 23:25:13', 'ADMIN'),
                                                                  (3, '2022-10-14 23:25:13', '2022-10-14 23:25:13', 'ORDER'),
                                                                  (4, '2022-10-14 23:25:13', '2022-10-14 23:25:13', 'INVENTORY'),
                                                                  (5, '2022-10-14 23:25:13', '2022-10-14 23:25:13', 'CUSTOMER_CARE');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shipping_address`
--

CREATE TABLE `shipping_address` (
                                    `id` bigint NOT NULL,
                                    `address` varchar(255) NOT NULL,
                                    `city` varchar(255) NOT NULL,
                                    `email` varchar(255) DEFAULT NULL,
                                    `name` varchar(255) NOT NULL,
                                    `phone` varchar(255) NOT NULL,
                                    `order_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `shipping_address`
--

INSERT INTO `shipping_address` (`id`, `address`, `city`, `email`, `name`, `phone`, `order_id`) VALUES
                                                                                                   (18, '41 Tây Sơn', 'Tỉnh Hà Giang', 'theson@gmail.com', 'Thế Sơn', '0902157811', 'DH-1'),
                                                                                                   (19, '890 Thủy Tạ, quận Hoàn Kiếm', 'Thành phố Hà Nội', 'vanlong@gmail.com', 'Văn Long', '0904578912', 'DH-2'),
                                                                                                   (20, '1090 Giải Phóng, quận Hai Bà Trưng', 'Tỉnh Bắc Giang', 'hoanganh@gmail.com', 'Hoàng Anh', '0989012345', 'DH-3'),
                                                                                                   (21, '1041 Giải Phóng, quận Hai Bà Trưng', 'Tỉnh Bắc Kạn', 'quang@gmail.com', 'Quang', '0908769087', 'DH-6'),
                                                                                                   (22, '1041 Giải Phóng, quận Hai Bà Trưng', 'Tỉnh Tuyên Quang', 'quang@gmail.com', 'Quang', '0906758942', 'DH-7'),
                                                                                                   (23, '1041 Trần Đại Nghĩa, quận Hai Bà Trưng', 'Tỉnh Tuyên Quang', 'hi@gmail.com', 'Minh Long', '0908769087', 'DH-8'),
                                                                                                   (24, '1041 Giải Phóng, quận Hai Bà Trưng', 'Tỉnh Bắc Kạn', 'quang190@gmail.com', 'Quang Minh', '0906758942', 'DH-9'),
                                                                                                   (25, '1041 Giải Phóng, quận Hai Bà Trưng', 'Tỉnh Hà Giang', 'quang1@gmail.com', 'Quang', '0904568900', 'DH-10'),
                                                                                                   (26, '2009 Giải Phóng, quận Hai Bà Trưng', 'Thành phố Hà Nội', 'cuong@gmail.com', 'Mạnh Cường', '0933001997', 'DH-11'),
                                                                                                   (27, '1001 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Tuyên Quang', 'phuket@gmail.com', 'Văn Phú', '0913344891', 'DH-12'),
                                                                                                   (28, '1001 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Hà Giang', 'phuket@gmail.com', 'Văn Phú', '0913344891', 'DH-13'),
                                                                                                   (29, '1041 Giải Phóng, quận Hai Bà Trưng', 'Thành phố Hà Nội', 'hi@gmail.com', 'Quang', '0902172309', 'DH-14'),
                                                                                                   (30, 'C34 nhà C33 A1 ', 'Tỉnh Lào Cai', 'cuong@gmail.com', 'Mạnh Cường', '0902172309', 'DH-15'),
                                                                                                   (31, '1191 Thái An, quận Bình Thạnh', 'Thành phố Hồ Chí Minh', 'chien@gmail.com', 'Minh Chiến', '0912288901', 'DH-16'),
                                                                                                   (32, '901 Bà Triệu, quận Thái Ấp', 'Tỉnh Quảng Ninh', 'tueminh@gmail.com', 'Tuệ Minh', '0913344781', 'DH-17'),
                                                                                                   (33, 'C34 nhà C33 A1 ', 'Thành phố Hà Nội', 'cuong@gmail.com', 'Mạnh Cường', '0908769087', 'DH-18'),
                                                                                                   (34, '192 Nguyễn Văn Trỗi', 'Tỉnh Hà Giang', 'vanminh@gmail.com', 'Văn Minh', '0989091298', 'DH-19'),
                                                                                                   (35, '911 Đại La, quận Hai Bà Trưng', 'Thành phố Hà Nội', '', 'Thanh Vân', '0913344567', 'DH-20'),
                                                                                                   (36, '202 Hoa Lư, quận Mai Động', 'Tỉnh Thanh Hóa', '', 'Thu Thủy', '0912267891', 'DH-21'),
                                                                                                   (37, '109 Phố Huế, quận Hai Bà Trưng', 'Thành phố Hà Nội', '', 'Thanh Mai', '0912234561', 'DH-22'),
                                                                                                   (38, '998 Thạch Thất, quận Hà Đông', 'Tỉnh Ninh Thuận', '', 'Thu Thùy', '0902347891', 'DH-23'),
                                                                                                   (39, '998 Thạch Thất, quận Hà Đông', 'Tỉnh Ninh Thuận', '', 'Thu Thùy', '0902347891', 'DH-24'),
                                                                                                   (40, '1001 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Sóc Trăng', '', 'Văn Phú', '0908769087', 'DH-25'),
                                                                                                   (41, '199 Tây Sơn', 'Tỉnh Hà Giang', 'longminh@gmail.com', 'Minh Long', '0902134567', 'DH-26'),
                                                                                                   (42, '1041 Giải Phóng, quận Hai Bà Trưng', 'Tỉnh Hà Giang', 'quang@gmail.com', 'Quang', '0904568901', 'DH-27'),
                                                                                                   (43, '1001 Điện Biên Phủ, phường Điện Biên', 'Tỉnh Cao Bằng', '', 'Mạnh Cường', '0913344891', 'DH-28'),
                                                                                                   (44, '1001 Điện Biên Phủ, phường Điện Biên', 'Thành phố Hà Nội', '', 'Mạnh Cường', '0913344891', 'DH-29'),
                                                                                                   (45, '1001 Điện Biên Phủ, phường Điện Biên', 'Thành phố Hà Nội', '', 'Mạnh Cường', '0913344891', 'DH-30'),
                                                                                                   (46, '191 Bà Triệu', 'Thành phố Hà Nội', 'manhcuong@gmail.com', 'Mạnh Cường', '0901235674', 'DH-31');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `token`
--

CREATE TABLE `token` (
                         `id` int NOT NULL,
                         `confirmed_at` datetime DEFAULT NULL,
                         `created_at` datetime NOT NULL,
                         `expires_at` datetime NOT NULL,
                         `token` varchar(255) NOT NULL,
                         `account_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `token`
--

INSERT INTO `token` (`id`, `confirmed_at`, `created_at`, `expires_at`, `token`, `account_id`) VALUES
                                                                                                  (5, NULL, '2022-10-18 11:27:04', '2022-10-18 11:42:04', '4b1bcc97-ffab-4da6-9991-aec219248b7d', 15),
                                                                                                  (6, NULL, '2022-10-18 11:29:26', '2022-10-18 11:44:26', '00f3c2d2-2325-4c4d-9b31-880690cefc35', 16),
                                                                                                  (7, NULL, '2022-10-18 11:31:54', '2022-10-18 11:46:54', 'db0e609b-91f5-4627-a248-ca9be5e41016', 17),
                                                                                                  (8, NULL, '2022-10-18 11:42:23', '2022-10-18 11:57:23', '729213f6-d76b-4146-a907-e2fa3f6ed4d9', 18),
                                                                                                  (9, NULL, '2022-10-18 11:43:56', '2022-10-18 11:58:56', '3fabd3ed-f69c-4024-b5a3-ffe076c490bf', 19),
                                                                                                  (10, NULL, '2022-10-18 11:46:02', '2022-10-18 12:01:02', '0f9835b1-8137-4e6c-8a5e-47e298ad024f', 20),
                                                                                                  (11, NULL, '2022-10-18 11:49:34', '2022-10-18 12:04:34', '3c35a18e-0078-49e0-95ec-09d3c2f70893', 21),
                                                                                                  (12, NULL, '2022-10-18 11:57:40', '2022-10-18 12:12:40', 'b33a3579-1d86-4db4-8c86-3940f410d95e', 22),
                                                                                                  (15, NULL, '2022-10-31 01:19:18', '2022-10-31 01:34:18', '464e4275-6453-4d51-bcb9-575e7c0afd4b', 25),
                                                                                                  (16, '2022-10-31 01:19:59', '2022-10-31 01:19:19', '2022-10-31 01:34:19', 'b20508fb-e293-496c-8198-92e4a99a8c33', 25),
                                                                                                  (18, '2022-10-31 01:25:33', '2022-10-31 01:25:01', '2022-10-31 01:40:01', 'da60f31d-3cfc-4825-beca-fc59fefb9096', 17),
                                                                                                  (20, '2022-10-31 01:37:18', '2022-10-31 01:36:58', '2022-10-31 01:51:58', 'd5c7d178-21f9-4f73-b52a-99d057bb908d', 28),
                                                                                                  (24, '2022-10-31 08:31:03', '2022-10-31 08:30:52', '2022-10-31 08:45:52', '4e05d494-24a5-4cb4-a5ee-fac4754cca69', 22),
                                                                                                  (25, '2022-10-31 08:34:15', '2022-10-31 08:33:56', '2022-10-31 08:48:56', '9b741b33-cde6-47b6-b9d4-c82f6d9c3621', 21),
                                                                                                  (28, '2022-10-31 21:05:54', '2022-10-31 21:05:48', '2022-10-31 21:20:48', '34f86ba2-fcf9-406c-9aec-eeb6141188b3', 36),
                                                                                                  (29, '2022-10-31 21:10:05', '2022-10-31 21:09:59', '2022-10-31 21:24:59', '19a3e73c-47b5-466f-9093-c031a8687030', 37),
                                                                                                  (31, '2022-10-31 22:38:44', '2022-10-31 22:34:29', '2022-10-31 22:49:29', '1a1189ff-0cfe-41e6-8f41-3e6b10d96e82', 39),
                                                                                                  (32, '2022-10-31 22:38:40', '2022-10-31 22:35:44', '2022-10-31 22:50:44', 'cebcbd78-210e-459f-9661-2962b0fdd8eb', 40),
                                                                                                  (33, '2022-10-31 22:38:36', '2022-10-31 22:36:35', '2022-10-31 22:51:35', '150e5c5a-ab70-4e86-b949-947fef8e08da', 41),
                                                                                                  (34, '2022-10-31 22:38:32', '2022-10-31 22:37:25', '2022-10-31 22:52:25', '8ce14a32-0e73-429f-8acd-1b755bd1b541', 42),
                                                                                                  (35, '2022-10-31 22:41:22', '2022-10-31 22:39:57', '2022-10-31 22:54:57', '67159300-b687-467e-80b1-76db2a76ab68', 43),
                                                                                                  (36, '2022-10-31 22:41:19', '2022-10-31 22:41:06', '2022-10-31 22:56:06', '56905ac8-d576-43d7-b765-77208f8c3081', 44),
                                                                                                  (37, NULL, '2022-11-03 09:54:20', '2022-11-03 10:09:20', 'abf1cefd-3fc1-4458-a141-dc0c9d82ad7a', 19),
                                                                                                  (38, NULL, '2022-11-03 09:55:09', '2022-11-03 10:10:09', '30d0b0c2-6ee9-4f86-bae0-dfc390810934', 19),
                                                                                                  (39, NULL, '2022-11-03 09:56:41', '2022-11-03 10:11:41', 'd59adb4c-96f6-4335-b22d-d1a3b95cefc3', 19),
                                                                                                  (41, '2022-11-03 10:00:53', '2022-11-03 09:58:39', '2022-11-03 10:13:39', '23930964-fc35-4677-9346-08d60d2c616e', 19),
                                                                                                  (42, '2022-11-03 10:00:56', '2022-11-03 09:59:25', '2022-11-03 10:14:25', '5825f112-ce87-476a-94b1-dcea96015635', 46),
                                                                                                  (44, '2022-11-06 12:04:31', '2022-11-06 12:04:16', '2022-11-06 12:19:16', '3f902ee3-026b-480a-9e2c-d7184a6a9e21', 48),
                                                                                                  (45, '2022-11-06 16:29:36', '2022-11-06 16:29:26', '2022-11-06 16:44:26', '12d32738-ea0a-4878-9fc5-52f66148e9e2', 49),
                                                                                                  (46, '2022-11-06 16:32:43', '2022-11-06 16:32:36', '2022-11-06 16:47:36', 'af3c74c0-8214-4d0d-9202-60e87d1acd92', 50),
                                                                                                  (47, '2022-11-06 16:39:18', '2022-11-06 16:39:12', '2022-11-06 16:54:12', '8cf096c2-225a-4090-b86e-d46c0ade865f', 51),
                                                                                                  (48, '2022-11-06 16:41:01', '2022-11-06 16:40:49', '2022-11-06 16:55:49', '701a74ab-0061-492d-a6f8-9e9d9d09ef7a', 52),
                                                                                                  (49, '2022-11-06 16:43:28', '2022-11-06 16:41:51', '2022-11-06 16:56:51', 'edcb964d-ea77-418c-ae07-c309146481b6', 53),
                                                                                                  (50, '2022-11-06 16:44:33', '2022-11-06 16:44:15', '2022-11-06 16:59:15', '11ca15e1-814f-4839-abdf-02fe66311019', 54),
                                                                                                  (51, '2022-11-06 16:46:24', '2022-11-06 16:46:10', '2022-11-06 17:01:10', 'fc803bbb-e8b1-42d0-9ad3-e4edc8f107eb', 55),
                                                                                                  (52, '2022-11-06 16:48:01', '2022-11-06 16:47:50', '2022-11-06 17:02:50', '6d1039a8-efb9-420b-948f-e80001125472', 56),
                                                                                                  (53, '2022-11-06 16:51:43', '2022-11-06 16:51:34', '2022-11-06 17:06:34', 'b2c946c8-79f3-4581-bdc5-7c4cb2c297e8', 57),
                                                                                                  (54, '2022-11-06 16:59:34', '2022-11-06 16:59:23', '2022-11-06 17:14:23', 'afedf3dd-8dbd-4ef6-a751-e3d12f850f5f', 58),
                                                                                                  (55, '2022-11-06 17:12:45', '2022-11-06 17:06:27', '2022-11-06 17:21:27', 'f007a3bc-1eb7-4e8c-a3fa-790e9cc14865', 59),
                                                                                                  (56, '2022-11-06 17:12:50', '2022-11-06 17:08:51', '2022-11-06 17:23:51', '3616c24f-fd44-4a07-a504-f6ccbc55c5cc', 60),
                                                                                                  (57, '2022-11-06 17:19:56', '2022-11-06 17:16:34', '2022-11-06 17:31:34', '9d5bd5e3-51a3-4d48-93d4-ca1f36770902', 61),
                                                                                                  (58, '2022-11-06 17:19:52', '2022-11-06 17:18:20', '2022-11-06 17:33:20', '7579dc90-6c7c-4282-b1fb-b320453f32b1', 62),
                                                                                                  (59, '2022-11-06 17:19:49', '2022-11-06 17:19:25', '2022-11-06 17:34:25', '65df17a9-e05e-4ace-9f07-fad3c315dc04', 63),
                                                                                                  (60, '2022-11-07 23:34:21', '2022-11-07 23:33:29', '2022-11-07 23:48:29', 'ad3aed58-092f-474b-a81c-05c174e5b343', 64),
                                                                                                  (61, '2022-11-08 17:09:00', '2022-11-08 17:08:48', '2022-11-08 17:23:48', '3592f891-ff1d-47f6-9d47-38be2d7558b9', 65),
                                                                                                  (62, '2022-11-08 22:18:51', '2022-11-08 22:18:32', '2022-11-08 22:33:32', 'e690db4a-b63d-4d00-8e84-3b3b367d6e0e', 66),
                                                                                                  (63, '2022-11-08 22:48:58', '2022-11-08 22:48:47', '2022-11-08 23:03:47', 'f0923f3c-bba3-4cc6-addd-b13ebc76949a', 67);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `variant`
--

CREATE TABLE `variant` (
                           `id` bigint NOT NULL,
                           `color` varchar(255) DEFAULT NULL,
                           `price` double DEFAULT NULL,
                           `quantity` int DEFAULT NULL,
                           `size` varchar(255) DEFAULT NULL,
                           `sku` varchar(255) NOT NULL,
                           `product_id` bigint DEFAULT NULL,
                           `created_at` datetime DEFAULT NULL,
                           `updated_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `variant`
--

INSERT INTO `variant` (`id`, `color`, `price`, `quantity`, `size`, `sku`, `product_id`, `created_at`, `updated_at`) VALUES
                                                                                                                        (2, 'Tím nhạt', 190000, 20, 'M', 'I9TSH635M', 9, NULL, NULL),
                                                                                                                        (3, 'Trắng', 250000, 17, 'L', 'I9TSH624L', 10, NULL, '2022-11-07 00:50:56'),
                                                                                                                        (4, 'Trắng', 250000, 85, 'XL', 'I9TSH624XL', 10, '2022-10-26 23:00:08', '2022-10-28 21:12:39'),
                                                                                                                        (7, 'Nâu', 290000, 89, 'L', 'I9SWT503L', 13, '2022-10-25 20:22:31', '2022-11-07 00:09:34'),
                                                                                                                        (8, 'Nâu', 290000, 98, 'M', 'I9SWT503M', 13, '2022-10-25 20:22:31', '2022-10-31 11:57:40'),
                                                                                                                        (10, 'Trắng sữa', 380000, 93, 'L', 'G9SMT007L', 14, '2022-10-25 20:28:45', '2022-10-31 11:51:43'),
                                                                                                                        (13, 'Hồng tím', 360000, 48, 'L', 'G9SMT006L', 14, '2022-10-25 20:28:45', '2022-11-07 08:27:22'),
                                                                                                                        (16, 'Trắng sữa', 390000, 47, 'S', 'G9SMT007S', 14, '2022-10-27 09:52:32', '2022-11-07 00:50:07'),
                                                                                                                        (24, 'Trắng sữa', 320000, 91, 'XL', 'G9SMT007XL', 14, '2022-10-27 15:52:51', '2022-10-31 23:07:11'),
                                                                                                                        (26, 'Trắng', 275000, 78, 'L', 'I9SWT507L', 17, '2022-10-27 15:54:32', '2022-11-07 00:08:59'),
                                                                                                                        (27, 'Trắng', 275000, 123, 'M', 'I9SWT507M', 17, '2022-10-27 15:54:51', '2022-11-07 00:08:59'),
                                                                                                                        (28, 'Xanh', 220000, 99, 'L', 'I9TSH625L', 5, '2022-10-27 16:03:23', '2022-11-07 00:49:23'),
                                                                                                                        (29, 'Xanh', 210000, 100, 'XL', 'I9TSH625XL', 5, '2022-10-27 16:03:45', '2022-10-28 21:13:03'),
                                                                                                                        (30, 'Hồng nâu', 190000, 98, 'L', 'I9TSH622L', 3, '2022-10-27 16:05:15', '2022-11-07 14:28:15'),
                                                                                                                        (31, 'Hồng nâu', 190000, 100, 'M', 'I9TSH622M', 3, '2022-10-27 16:05:28', '2022-10-28 21:13:12'),
                                                                                                                        (32, 'Hồng', 265000, 99, 'L', 'I9TSH636L', 1, '2022-10-27 20:26:34', '2022-11-07 00:51:52'),
                                                                                                                        (33, 'Hồng', 265000, 100, 'M', 'I9TSH636M', 1, '2022-10-27 20:26:51', '2022-10-28 21:12:54'),
                                                                                                                        (36, 'Xám', 790000, 70, 'L', 'I9JCK505L', 18, '2022-11-01 14:58:39', '2022-11-06 00:09:37'),
                                                                                                                        (37, 'Xám', 790000, 59, 'M', 'I9JCK505M', 18, '2022-11-01 14:58:39', '2022-11-06 00:09:42'),
                                                                                                                        (39, 'Be', 440000, 94, 'M', 'C7WCO001M', 19, '2022-11-01 15:07:54', '2022-11-08 22:43:52'),
                                                                                                                        (40, 'Be', 410000, 97, 'S', 'C7WCO001S', 19, '2022-11-01 15:07:54', '2022-11-08 22:43:52'),
                                                                                                                        (41, 'Xám', 440000, 49, 'L', 'I7SWS553L', 20, '2022-11-07 12:19:13', '2022-11-07 14:27:07'),
                                                                                                                        (42, 'Xám', 420000, 200, 'XL', 'I7SWS553XL', 20, '2022-11-07 12:19:13', '2022-11-07 12:21:18'),
                                                                                                                        (43, 'Loang xanh', 405000, 73, 'L', 'I9JOG502L', 21, '2022-11-07 12:27:22', '2022-11-07 14:28:15'),
                                                                                                                        (44, 'Loang xanh', 405000, 91, 'M', 'I9JOG502M', 21, '2022-11-07 12:27:22', '2022-11-07 12:28:35'),
                                                                                                                        (45, 'Hồng', 440000, 50, 'L', 'C4SWT006L', 22, '2022-11-07 16:13:15', '2022-11-07 16:13:38'),
                                                                                                                        (46, 'Hồng', 440000, 50, 'M', 'C4SWT006M', 22, '2022-11-07 16:13:15', '2022-11-07 16:13:31'),
                                                                                                                        (47, 'Đen', 400000, 50, 'M', 'N5KHA005M', 23, '2022-11-07 16:16:18', '2022-11-07 16:16:34'),
                                                                                                                        (48, 'Đen', 400000, 49, 'L', 'N5KHA005L', 23, '2022-11-07 16:16:18', '2022-11-07 23:17:52'),
                                                                                                                        (49, 'Nâu', 400000, 50, 'M', 'N5KHA006M', 23, '2022-11-07 16:16:18', '2022-11-07 16:16:39'),
                                                                                                                        (50, 'Xanh', 130000, 90, 'L', 'G9SMT025L', 24, '2022-11-07 16:19:35', '2022-11-08 20:47:00'),
                                                                                                                        (51, 'Xanh', 120000, 94, 'M', 'G9SMT025M', 24, '2022-11-07 16:19:35', '2022-11-08 22:39:21'),
                                                                                                                        (52, 'Vàng', 199000, 198, 'L', 'I9POL810KL', 25, '2022-11-07 22:46:57', '2022-11-07 23:12:12'),
                                                                                                                        (53, 'Vàng', 199000, 150, 'M', 'I9POL810KM', 25, '2022-11-07 22:46:57', '2022-11-07 22:47:22'),
                                                                                                                        (54, 'Vàng', 199000, 150, 'S', 'I9POL810KS', 25, '2022-11-07 22:46:57', '2022-11-07 22:47:30'),
                                                                                                                        (57, 'Tím', 199000, 99, 'L', 'I9POL811KL', 27, '2022-11-08 17:11:53', '2022-11-08 17:55:41'),
                                                                                                                        (59, 'Trắng sữa', 140000, 0, 'L', 'G9SMT026L', 24, '2022-11-08 21:11:43', '2022-11-08 21:11:43'),
                                                                                                                        (60, 'Hồng tím', 200000, 0, 'M', 'I9POL811KM', 27, '2022-11-08 22:57:41', '2022-11-08 22:57:41'),
                                                                                                                        (61, 'Xanh', 210000, 0, 'L', 'AN9001L', 28, '2022-11-08 22:59:47', '2022-11-08 22:59:47'),
                                                                                                                        (62, 'Xanh', 210000, 0, 'M', 'AN9001M', 28, '2022-11-08 22:59:47', '2022-11-08 22:59:47'),
                                                                                                                        (63, 'Xanh', 199000, 0, 'L', 'I7POL812L', 29, '2022-11-08 23:27:55', '2022-11-08 23:27:55'),
                                                                                                                        (64, 'Xanh', 199000, 0, 'M', 'I7POL812M', 29, '2022-11-08 23:27:55', '2022-11-08 23:27:55');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_q0uja26qgu1atulenwup9rxyr` (`email`),
  ADD UNIQUE KEY `UK_dgdnj692f2g5ebicy1xyc2l3w` (`phone`);

--
-- Chỉ mục cho bảng `account_role`
--
ALTER TABLE `account_role`
    ADD KEY `FKrs2s3m3039h0xt8d5yhwbuyam` (`role_id`),
  ADD KEY `FK1f8y4iy71kb1arff79s71j0dh` (`account_id`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
    ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `cart_item`
--
ALTER TABLE `cart_item`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
    ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `image`
--
ALTER TABLE `image`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKgpextbyee3uk9u6o2381m7ft1` (`product_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK3c7gbsfawn58r27cf5b2km72f` (`account_id`);

--
-- Chỉ mục cho bảng `order_item`
--
ALTER TABLE `order_item`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jmivyxk9rmgysrmsqw15lqr5b` (`name`);

--
-- Chỉ mục cho bảng `product_category`
--
ALTER TABLE `product_category`
    ADD PRIMARY KEY (`product_id`,`category_id`),
  ADD KEY `FKkud35ls1d40wpjb5htpp14q4e` (`category_id`);

--
-- Chỉ mục cho bảng `refund`
--
ALTER TABLE `refund`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FK80vls36avhp4yl7h8apkqm0ek` (`order_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
    ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `shipping_address`
--
ALTER TABLE `shipping_address`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKl88fq4d2ypn9qvg8x90uimnca` (`order_id`);

--
-- Chỉ mục cho bảng `token`
--
ALTER TABLE `token`
    ADD PRIMARY KEY (`id`),
  ADD KEY `FKftkstvcfb74ogw02bo5261kno` (`account_id`);

--
-- Chỉ mục cho bảng `variant`
--
ALTER TABLE `variant`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_llpabmolrn143l5uh3dp92bgy` (`sku`),
  ADD KEY `FKjjpllnln6hk6hj98uesgxno00` (`product_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT cho bảng `cart_item`
--
ALTER TABLE `cart_item`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `image`
--
ALTER TABLE `image`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=87;

--
-- AUTO_INCREMENT cho bảng `order_item`
--
ALTER TABLE `order_item`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `refund`
--
ALTER TABLE `refund`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `shipping_address`
--
ALTER TABLE `shipping_address`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `token`
--
ALTER TABLE `token`
    MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT cho bảng `variant`
--
ALTER TABLE `variant`
    MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `account_role`
--
ALTER TABLE `account_role`
    ADD CONSTRAINT `FK1f8y4iy71kb1arff79s71j0dh` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FKrs2s3m3039h0xt8d5yhwbuyam` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Các ràng buộc cho bảng `cart_item`
--
ALTER TABLE `cart_item`
    ADD CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

--
-- Các ràng buộc cho bảng `image`
--
ALTER TABLE `image`
    ADD CONSTRAINT `FKgpextbyee3uk9u6o2381m7ft1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
    ADD CONSTRAINT `FK3c7gbsfawn58r27cf5b2km72f` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `order_item`
--
ALTER TABLE `order_item`
    ADD CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `product_category`
--
ALTER TABLE `product_category`
    ADD CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Các ràng buộc cho bảng `refund`
--
ALTER TABLE `refund`
    ADD CONSTRAINT `FK80vls36avhp4yl7h8apkqm0ek` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `shipping_address`
--
ALTER TABLE `shipping_address`
    ADD CONSTRAINT `FKl88fq4d2ypn9qvg8x90uimnca` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `token`
--
ALTER TABLE `token`
    ADD CONSTRAINT `FKftkstvcfb74ogw02bo5261kno` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Các ràng buộc cho bảng `variant`
--
ALTER TABLE `variant`
    ADD CONSTRAINT `FKjjpllnln6hk6hj98uesgxno00` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
