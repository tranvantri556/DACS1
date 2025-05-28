import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConnectData {
    private Connection con;
    String url = "jdbc:sqlserver://localhost:1433;databaseName=ELDERCARE;encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8";
    String user = "sa";
    String pass = "123456789";
    public ConnectData(){
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối");
            e.printStackTrace();
        }
    }

//  login
    public boolean checkLogin(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ? AND pass = ?";
        try {PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email.trim());
            stmt.setString(2, password.trim());

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true nếu có tài khoản
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//  resgister
    public boolean registerUser(String name, String email, String password) {
        String checkSql = "SELECT * FROM Users WHERE email = ?";
        String sql = "INSERT INTO Users (name, email, pass) VALUES (?, ?, ?)";
        try {
            // Kiểm tra email đã tồn tại chưa
            PreparedStatement checkStmt = con.prepareStatement(checkSql);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Email already registered!");
                return false;
            }

            // Thêm người dùng mới
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            int rows = stmt.executeUpdate();

            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//  Bảng hiển thị dữ liệu bệnh nhân
    public DefaultTableModel getBenhNhanModel() {
        // Tiêu đề cột phải khớp với JTable của bạn
        String[] columns = {
                "Mã cư dân", "Họ tên", "Ngày sinh", "Giới tính",
                "Địa chỉ", "Số điện thoại", "Người thân", "Liên lạc", "Ngày vào viện"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT id_benhnhan, hoten, ngaysinh, gioitinh, diachi, sdt, nguoithan, lienlac, ngayvaovien "
                + "FROM Benhnhan";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Object[] row = new Object[] {
                        rs.getInt("id_benhnhan"),
                        rs.getString("hoten"),
                        rs.getDate("ngaysinh"),
                        rs.getString("gioitinh"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getString("nguoithan"),
                        rs.getString("lienlac"),
                        rs.getDate("ngayvaovien")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean kiemTraSDT(String table, String sdt) {
        String sql = "SELECT COUNT(*) FROM " + table + " WHERE sdt = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, sdt);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ThemBenhnhan(String hoten, String ngaysinh, String gioitinh, String diachi,
                                String sdt, String nguoithan, String lienlac, String ngayvaovien) {
        if (kiemTraSDT("Benhnhan", sdt)) {
            System.out.println("Đã tồn tại");
            return false;
        }
        String sql = "INSERT INTO Benhnhan(hoten, ngaysinh, gioitinh, diachi, sdt, nguoithan, lienlac, ngayvaovien)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, hoten);
            pstmt.setString(2, ngaysinh);
            pstmt.setString(3, gioitinh);
            pstmt.setString(4, diachi);
            pstmt.setString(5, sdt);
            pstmt.setString(6, nguoithan);
            pstmt.setString(7, lienlac);
            pstmt.setString(8, ngayvaovien);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaBenhNhan(int id, String hoten, String ngaysinh, String gioitinh, String diachi,
                               String sdt, String nguoithan, String lienlac, String ngayvaovien) {
        String sql = "UPDATE Benhnhan SET hoten=?, ngaysinh=?, gioitinh=?, diachi=?, sdt=?, " +
                     "nguoithan=?, lienlac=?, ngayvaovien=? WHERE id_benhnhan=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, hoten);
            stmt.setString(2, ngaysinh);
            stmt.setString(3, gioitinh);
            stmt.setString(4, diachi);
            stmt.setString(5, sdt);
            stmt.setString(6, nguoithan);
            stmt.setString(7, lienlac);
            stmt.setString(8, ngayvaovien);
            stmt.setInt(9, id);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean XoaBenhNhan(int id) {
        String sql = "DELETE FROM Benhnhan WHERE id_benhnhan = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void timKiemBenhNhan(DefaultTableModel model, String keyword) {
        String sql = "SELECT * FROM Benhnhan WHERE hoten LIKE ? OR sdt LIKE ? OR nguoithan LIKE ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            stmt.setString(3, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            model.setRowCount(0); // Xóa dữ liệu cũ

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id_benhnhan"),
                        rs.getString("hoten"),
                        rs.getDate("ngaysinh"),
                        rs.getString("gioitinh"),
                        rs.getString("diachi"),
                        rs.getString("sdt"),
                        rs.getString("nguoithan"),
                        rs.getString("lienlac"),
                        rs.getDate("ngayvaovien")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//  _____________________________________________________
//  Bệnh án
//  Bảng hiển th dữ liệu bệnh án
    public DefaultTableModel getBenhAnModel() {
        // Tiêu đề cột phải khớp với JTable của bạn
        String[] columns = {
                "Mã bệnh án", "Mã cư dân", "Họ tên", "Phụ trách", "Ngày khám",
                "Tổng quan", "Chẩn đoán", "Đơn thuốc", "Ghi chú"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT id_benhan, id_benhnhan, ten_benhnhan, phutrach, ngaykham, tinhtrangchung, chandoan, donthuoc, ghichu FROM Benhan";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date ngay = rs.getDate("ngaykham");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                Object[] row = new Object[] {
                        rs.getInt("id_benhan"),
                        rs.getInt("id_benhnhan"),
                        rs.getString("ten_benhnhan"),
                        rs.getString("phutrach"),
                        sdf.format(ngay),
                        rs.getString("chandoan"),
                        rs.getString("donthuoc"),
                        rs.getString("ghichu"),
                        rs.getString("tinhtrangchung")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean themBenhAn(int maBA, int maBN, String tenBN, String phutrach, Date ngaykham, String chandoan, String donthuoc, String ghichu, String ttc) {
        String sql = "INSERT INTO Benhan(id_benhnhan, ten_benhnhan, phutrach, ngaykham, tinhtrangchung, chandoan, donthuoc, ghichu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            java.sql.Date sqlNgay = new java.sql.Date(ngaykham.getTime());

            ps.setInt(1, maBN);
            ps.setString(2, tenBN);
            ps.setString(3, phutrach);
            ps.setDate(4, sqlNgay);
            ps.setString(5, ttc);
            ps.setString(6, chandoan);
            ps.setString(7, donthuoc);
            ps.setString(8, ghichu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaBenhAn(int maBA, int maBN, String tenBN, String phutrach, Date ngaykham, String chandoan, String donthuoc, String ghichu, String ttc) {
        String sql = "UPDATE Benhan SET id_benhnhan=?, ten_benhnhan=?, phutrach=?, ngaykham=?, tinhtrangchung=?, chandoan=?, " +
                "donthuoc=?, ghichu=? WHERE id_benhan=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            java.sql.Date sqlNgay = new java.sql.Date(ngaykham.getTime());

            ps.setInt(1, maBN);
            ps.setString(2, tenBN);
            ps.setString(3, phutrach);
            ps.setDate(4, sqlNgay);
            ps.setString(5, ttc);
            ps.setString(6, chandoan);
            ps.setString(7, donthuoc);
            ps.setString(8, ghichu);
            ps.setInt(9, maBA);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean XoaBenhAn(int idBA) {
        String sql = "DELETE FROM Benhan WHERE id_benhan = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idBA);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void timKiemBenhAn(DefaultTableModel model, String keyword) {
        String sql = "SELECT * FROM Benhan WHERE ten_benhnhan LIKE ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();

            model.setRowCount(0); // Xóa dữ liệu cũ

            while (rs.next()) {
                Date ngay = rs.getDate("ngaykham");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                model.addRow(new Object[]{
                        rs.getInt("id_benhan"),
                        rs.getInt("id_benhnhan"),
                        rs.getString("ten_benhnhan"),
                        rs.getString("phutrach"),
                        sdf.format(ngay),
                        rs.getString("chandoan"),
                        rs.getString("donthuoc"),
                        rs.getString("ghichu"),
                        rs.getString("tinhtrangchung")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getBenhNhanByMaCuDan(int maCuDan) {
        ArrayList<String> ds = new ArrayList<>();
        String query = "SELECT * FROM Benhnhan WHERE id_benhnhan = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, maCuDan);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                        ds.add(String.valueOf(rs.getInt("id_benhnhan")));
                        ds.add(rs.getString("hoten"));
                        ds.add(String.valueOf(rs.getDate("ngaysinh")));
                        ds.add(rs.getString("gioitinh"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }
//  Lấy danh sách nhân viên cho Bệnh án và Phân công
    public ArrayList<String> getDanhSachNhanVien() {
        ArrayList<String> danhSach = new ArrayList<>();
        String sql = "SELECT hoten FROM Nhanvien";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                danhSach.add(rs.getString("hoten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }
//  _____________________________________________________
//  Hóa đơn
//  Hiển thị dữ liệu lên bảng Hóa đơn
    public DefaultTableModel getHoaDonModel() {
        String[] columns = {
                "Mã hóa đơn", "Mã bệnh nhân", "Họ tên", "Từ ngày", "Đến ngày", "Ngày lập", "Dịch vụ", "Số ngày", "Đơn giá", "Thành tiền", "Trạng thái"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT id_hoadon, id_benhnhan, hoten, tungay, denngay, dichvu, dongia, tongtien, ngaylap, trangthai FROM Hoadon";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date tungay = rs.getDate("tungay");
                Date denngay = rs.getDate("denngay");
                long diff = Math.abs(denngay.getTime() - tungay.getTime());
                int songay = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                Object[] row = new Object[] {
                        rs.getInt("id_hoadon"),
                        rs.getInt("id_benhnhan"),
                        rs.getString("hoten"),
                        sdf.format(tungay),
                        sdf.format(denngay),
                        rs.getString("ngaylap"),
                        rs.getString("dichvu"),
                        songay,
                        rs.getDouble("dongia"),
                        rs.getDouble("tongtien"),
                        rs.getString("trangthai")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean themHoaDon(String maHoaDon, Date tuNgay, Date denNgay, String maBenhNhan,
                              String hoTen, String dichVu, double thanhTien, String ngayLap, String trangThai,
                              int soNgay, double donGia) {
        String sqlInvoices = "INSERT INTO Hoadon (id_benhnhan, hoten, tungay, denngay, dichvu, dongia, tongtien, ngaylap, trangthai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement psHD = con.prepareStatement(sqlInvoices)) {

            java.sql.Date sqlTuNgay = new java.sql.Date(tuNgay.getTime());
            java.sql.Date sqlDenNgay = new java.sql.Date(denNgay.getTime());
            java.sql.Date sqlNgayLap = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(ngayLap).getTime());

            // Thêm vào bảng Hóa đơn
            psHD.setInt(1, Integer.parseInt(maBenhNhan));// id_benhnhan
            psHD.setString(2, hoTen); // hoten
            psHD.setDate(3, sqlTuNgay); // tungay
            psHD.setDate(4, sqlDenNgay);
            psHD.setString(5, dichVu); // dichvu
            psHD.setDouble(6, donGia); // dongia
            psHD.setDouble(7, thanhTien); // tongtien
            psHD.setDate(8, sqlNgayLap);
            psHD.setString(9, trangThai); // trangthai
            int rowHD = psHD.executeUpdate();

            return rowHD > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean suaHoaDon(String maHoaDon, Date tuNgay, Date denNgay, String maBenhNhan,
                             String hoTen, String dichVu, double thanhTien, String ngayLap, String trangThai,
                             int soNgay, double donGia) {
        String sql = "UPDATE Hoadon SET id_benhnhan = ?, hoten = ?, tungay = ?, denngay = ?, ngaylap = ?, " +
                "dichvu = ?, dongia = ?, tongtien = ?, trangthai = ? WHERE id_hoadon = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            java.sql.Date sqlTuNgay = new java.sql.Date(tuNgay.getTime());
            java.sql.Date sqlDenNgay = new java.sql.Date(denNgay.getTime());
            java.sql.Date sqlNgayLap = new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(ngayLap).getTime());

            ps.setInt(1, Integer.parseInt(maBenhNhan));  // MaBenhNhan
            ps.setString(2, hoTen);                      // HoTen
            ps.setDate(3, sqlTuNgay);                    // TuNgay
            ps.setDate(4, sqlDenNgay);                   // DenNgay
            ps.setDate(5, sqlNgayLap);                   // NgayLap
            ps.setString(6, dichVu);
            ps.setDouble(7, donGia);                     // DonGia
            ps.setDouble(8, thanhTien);                  // ThanhTien
            ps.setString(9, trangThai);                  // TrangThai
            ps.setString(10, maHoaDon);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
//  ________________________________________________________
//  Nhân sự
//  Bảng hiển thị dữ liệu lên Nhân viên
    public DefaultTableModel getNhanvienModel() {
        String[] columns = {
                "Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "Điện thoại", "Địa chỉ", "Phòng ban", "Chức vụ", "Mức lương"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT id_nhanvien, hoten, gioitinh, ngaysinh, dienthoai, diachi, phongban, chucvu, mucluong FROM Nhanvien";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date ngay = rs.getDate("ngaysinh");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Object[] row = new Object[] {
                        rs.getInt("id_nhanvien"),
                        rs.getString("hoten"),
                        rs.getString("gioitinh"),
                        sdf.format(ngay),
                        rs.getString("dienthoai"),
                        rs.getString("diachi"),
                        rs.getString("phongban"),
                        rs.getString("chucvu"),
                        rs.getString("mucluong")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean themNhansu(String manv, String hoten, String gioitinh, Date ngsinh, String dienthoai,
                              String diachi, String phongban, String chucvu, String mucluong) {
        String sql = "INSERT INTO Nhanvien (hoten, gioitinh, ngaysinh, dienthoai, diachi, phongban, chucvu, mucluong) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {

            java.sql.Date sqlngSinh = new java.sql.Date(ngsinh.getTime());

            ps.setString(1, hoten);
            ps.setString(2, gioitinh);
            ps.setDate(3, sqlngSinh);
            ps.setString(4, dienthoai);
            ps.setString(5, diachi);
            ps.setString(6, phongban);
            ps.setString(7, chucvu);
            ps.setString(8, mucluong);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaNhansu(String manv, String hoten, String gioitinh, Date ngsinh, String dienthoai,
                             String diachi, String phongban, String chucvu, String mucluong) {
        String sql = "UPDATE Nhanvien SET hoten=?, gioitinh=?, ngaysinh=?, dienthoai=?, diachi=?, phongban=?, chucvu=?, mucluong=? WHERE id_nhanvien=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, hoten);
            stmt.setString(2, gioitinh);
            stmt.setDate(3, new java.sql.Date(ngsinh.getTime()));
            stmt.setString(4, dienthoai);
            stmt.setString(5, diachi);
            stmt.setString(6, phongban);
            stmt.setString(7, chucvu);
            stmt.setString(8, mucluong);
            stmt.setString(9, manv);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaNhanSu(int idNS) {
        String sql = "DELETE FROM Nhanvien WHERE id_nhanvien = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idNS);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
//  _____________________________________________________________
//  Phân công
//  Bảng hiển thị dữ liệu lên Phân công
    public DefaultTableModel getPhanCongModel() {
        String[] columns = {
                "Mã phân công", "Tên bệnh nhân", "Tên nhân viên", "Ngày", "Phòng", "Ca", "Ghi chú"
        };
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        String sql = "SELECT id_phancong, ten_benhnhan, ten_nhanvien, ngayphancong, phong, ca, ghichu FROM Phancong";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Date ngay = rs.getDate("ngayphancong");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                Object[] row = new Object[] {
                        rs.getString("id_phancong"),
                        rs.getString("ten_benhnhan"),
                        rs.getString("ten_nhanvien"),
                        sdf.format(ngay),
                        rs.getString("phong"),
                        rs.getString("ca"),
                        rs.getString("ghichu")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    public boolean themPhanCong(String maBN, String maNV, Date ngay, String phong, String ca, String ghichu) {
        String sql = "INSERT INTO Phancong(ten_benhnhan, ten_nhanvien, ngayphancong, phong, ca, ghichu) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            java.sql.Date sqlNgay = new java.sql.Date(ngay.getTime());
            ps.setString(1, maBN);
            ps.setString(2, maNV);
            ps.setDate(3, sqlNgay);
            ps.setString(4, phong);
            ps.setString(5, ca);
            ps.setString(6, ghichu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaPhanCong(int id, String maBN, String maNV, Date ngay, String phong, String ca, String ghichu) {
        String sql = "UPDATE Phancong SET ten_benhnhan=?, ten_nhanvien=?, ngayphancong=?, phong=?, ca=?, ghichu=? WHERE id_phancong=?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            java.sql.Date sqlNgay = new java.sql.Date(ngay.getTime());

            stmt.setString(1, maBN);
            stmt.setString(2, maNV);
            stmt.setDate(3, sqlNgay);
            stmt.setString(4, phong);
            stmt.setString(5, ca);
            stmt.setString(6, ghichu);
            stmt.setInt(7, id);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaPhanCong(int id) {
        String sql = "DELETE FROM Phancong WHERE id_phancong = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

//  Lấy danh sách bênh nhân qua Phân công
    public ArrayList<String> getDanhSachBenhNhan() {
        ArrayList<String> danhSach = new ArrayList<>();
        String sql = "SELECT hoten FROM Benhnhan";
        try {Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                danhSach.add(rs.getString("hoten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }
//  Báo cáo
    public Map<String, Integer> getThongKeBenhNhanTheoGioiTinh() throws SQLException {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT GioiTinh, COUNT(*) AS SoLuong FROM Benhnhan GROUP BY gioitinh";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            data.put(rs.getString("gioitinh"), rs.getInt("SoLuong"));
        }
        return data;
    }

    public Map<String, Integer> getThongKeNhanVienTheoBoPhan() throws SQLException {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT chucvu, COUNT(*) AS SoLuong FROM Nhanvien GROUP BY chucvu";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            data.put(rs.getString("chucvu"), rs.getInt("SoLuong"));
        }
        return data;
    }


    public static void main(String[] args) {
        ConnectData cd = new ConnectData();
    }
}