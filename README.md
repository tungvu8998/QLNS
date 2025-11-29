Họ tên: Vũ Hữu Tùng
MSSV: K24DTCN634
Lớp: D24TXCN15-K

Ứng dụng **Quản lý Nhân viên** được xây dựng bằng **Java Swing** và **JDBC** nhằm giúp sinh viên làm quen với:
- Lập trình giao diện trên Java
- Kết nối và thao tác cơ sở dữ liệu MySQL
- Tổ chức ứng dụng theo mô hình DAO – Model – UI
- Thao tác CRUD căn bản trong lập trình Java

## Chức năng chính

✔ Hiển thị danh sách nhân viên  
✔ Thêm mới nhân viên  
✔ Cập nhật thông tin  
✔ Xóa nhân viên  
✔ Reset form nhập  
✔ Tự động load dữ liệu vào bảng (JTable)  

---

## Cơ sở dữ liệu
- Thêm thư viện JDBC Driver : Tải file mysql-connector-j-8.x.x.jar và thêm vào:
### Hệ quản trị: **MySQL**

```sql
CREATE DATABASE qlnhanvien;

USE qlnhanvien;

CREATE TABLE NhanVien (
    MaNV VARCHAR(10) PRIMARY KEY,
    HoTen VARCHAR(100) NOT NULL,
    ChucVu VARCHAR(50),
    Luong DOUBLE,
    Phone VARCHAR(15)
);
