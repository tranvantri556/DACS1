import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectData {
    private Connection con;
    String url = "jdbc:sqlserver://localhost:1433;databaseName=ELDERCARE;encrypt=true;trustServerCertificate=true";
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
                                String sdt, String ngayvaovien, String sdtnquiloithan) {
        if (kiemTraSDT("Benhnhan", sdt)) {
            System.out.println("Đã tồn tại");
            return false;
        }
        String sql = "INSERT INTO Benhnhan(hoten, ngaysinh, gioitinh, diachi, sdt, ngayvaovien, sdtnguoithan)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, hoten);
            pstmt.setString(2, ngaysinh);
            pstmt.setString(3, gioitinh);
            pstmt.setString(4, diachi);
            pstmt.setString(5, sdt);
            pstmt.setString(6, ngayvaovien);
            pstmt.setString(7, sdtnquiloithan);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean themNhanVien(String ten, String ngaysinh, String chucvu, String sdt) {
        String sql = "INSERT INTO Nhanvien(hoten, ngaysinh, chucvu, sdt) VALUES (?, ?, ?, ?)";
        if (kiemTraSDT("Nhanvien", sdt)) {
            System.out.println("Đã tồn tại");
            return false;
        }
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ten);
            ps.setString(2, ngaysinh);
            ps.setString(3, chucvu);
            ps.setString(4, sdt);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean themPhanCong(int maNV, int maBN, String ngay, String ghichu) {
        String sql = "INSERT INTO Phancong(id_benhnhan, id_nhanvien, ngayphancong, ghichu) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maNV);
            ps.setInt(2, maBN);
            ps.setString(3, ngay);
            ps.setString(4, ghichu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean themBenhAn(int maBN, String ngaykham, String chandoan, String donthuoc, String ghichu) {
        String sql = "INSERT INTO Benhan(id_benhnhan, ngaykham, chandoan, donthuoc, ghichu) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maBN);
            ps.setString(2, ngaykham);
            ps.setString(3, chandoan);
            ps.setString(4, donthuoc);
            ps.setString(5, ghichu);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean themHoaDon(int maBN, String dichvu, int songay, double dongia, String ngaylap) {
        String sql = "INSERT INTO Hoadon(id_benhnhan, dichvu, songay, dongia, ngaylap) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maBN);
            ps.setString(2, dichvu);
            ps.setInt(3, songay);
            ps.setDouble(4, dongia);
            ps.setString(5, ngaylap);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaBenhnhanTheoID(int idBenhnhan) {
        String sql = "DELETE FROM Benhnhan WHERE id_benhnhan = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idBenhnhan);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public DefaultTableModel getDanhSachBenhnhan() {
//        String sql = "SELECT id_benhnhan, hoten, ngaysinh, gioitinh, diachi, sdt, ngayvaovien, sdtnguoithan FROM Benhnhan";
//        DefaultTableModel model = new DefaultTableModel();
//        model.addColumn("ID");
//        model.addColumn("Họ tên");
//        model.addColumn("Ngày sinh");
//        model.addColumn("Giới tính");
//        model.addColumn("Địa chỉ");
//        model.addColumn("Số điện thoại");
//        model.addColumn("Ngày vào viện");
//        model.addColumn("Số điện thoại người thân");
//
//        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
//            var rs = pstmt.executeQuery();
//            while (rs.next()) {
//                model.addRow(new Object[]{
//                        rs.getInt("id_benhnhan"),
//                        rs.getString("hoten"),
//                        rs.getString("ngaysinh"),
//                        rs.getString("gioitinh"),
//                        rs.getString("diachi"),
//                        rs.getString("sdt"),
//                        rs.getString("ngayvaovien"),
//                        rs.getString("sdtnguoithan")
//                });
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return model;
//    }

    public static void main(String[] args) {
        ConnectData cd = new ConnectData();
        cd.ThemBenhnhan("Nguyen Thi A", "1990-05-01", "Female", "123 ABC Street", "0987654321", "2025-04-20", "0901234567");
        cd.themNhanVien("Tran Thi B", "1985-07-15", "Doctor", "0912345678");
        cd.themPhanCong(1, 1, "2025-04-20", "Assign to check patient");
        cd.themBenhAn(1, "2025-04-20", "Flu", "Thuốc cảm", "Ghi chú bệnh án");
        cd.themHoaDon(1, "General checkup", 1, 100.50, "2025-04-20");
    }
}