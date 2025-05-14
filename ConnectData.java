import javax.swing.table.DefaultTableModel;
import java.sql.*;

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

//    Bảng hiển thị dữ liệu
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

    public static void main(String[] args) {
        ConnectData cd = new ConnectData();
    }
}