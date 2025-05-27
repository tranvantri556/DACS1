import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BenhAnPanel extends JPanel {
    private JTextField mabenhanField, macudanField, hotenBenhAnField, chandoanField, donthuocField, timkiemBenhAnField;
    private JTable benhanTable;
    private JComboBox<String> phutrachComboBox, tongquanComboBox;
    private JTextArea ghichuField;
    private JDateChooser ngaykham;
    private JLabel themBenhan, suaBenhan, xoaBenhan, xuatExcelBenhan;
    private JButton chitietbenhanButton;
    private DefaultTableModel benhanModel;

    public BenhAnPanel() {
        setBackground(new Color(245, 245, 245));
        setLayout(null);

        // Initialize table model
        benhanModel = new DefaultTableModel(new String[]{"Mã bệnh án", "Mã cư dân", "Họ tên", "Phụ trách", "Ngày khám", "Tổng quan", "Chẩn đoán", "Đơn thuốc", "Ghi chú"}, 0);
        ConnectData con = new ConnectData();
        benhanModel = con.getBenhAnModel();
        benhanTable = new JTable(benhanModel);
        benhanTable.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
        benhanTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        benhanTable.setBackground(new Color(227, 227, 227));

        JScrollPane benhanScrollPane = new JScrollPane(benhanTable);
        benhanScrollPane.setBounds(10, 204, 838, 375);
        add(benhanScrollPane);

        // Initialize fields
        mabenhanField = new JTextField();
        mabenhanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        mabenhanField.setBounds(129, 36, 108, 26);
        add(mabenhanField);

        macudanField = new JTextField();
        macudanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        macudanField.setBounds(129, 92, 108, 26);
        add(macudanField);

        hotenBenhAnField = new JTextField();
        hotenBenhAnField.setFont(new Font("Constantia", Font.PLAIN, 15));
        hotenBenhAnField.setBounds(129, 151, 108, 26);
        add(hotenBenhAnField);

        chandoanField = new JTextField();
        chandoanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        chandoanField.setBounds(691, 36, 138, 26);
        add(chandoanField);

        donthuocField = new JTextField();
        donthuocField.setFont(new Font("Constantia", Font.PLAIN, 15));
        donthuocField.setBounds(691, 92, 138, 26);
        add(donthuocField);

        ghichuField = new JTextArea();
        ghichuField.setFont(new Font("Monospaced", Font.PLAIN, 15));
        ghichuField.setBounds(691, 149, 138, 44);
        add(ghichuField);

        phutrachComboBox = new JComboBox<>();
        phutrachComboBox.setFont(new Font("Constantia", Font.PLAIN, 15));
        phutrachComboBox.setBounds(396, 41, 138, 26);
        add(phutrachComboBox);

        String[] tongquan = {"Tốt", "Yếu", "Đi lại khó khăn", "Mất ngủ", "Lú lẫn", "Ăn uống kém"};
        tongquanComboBox = new JComboBox<>(tongquan);
        tongquanComboBox.setFont(new Font("Constantia", Font.PLAIN, 15));
        tongquanComboBox.setBounds(396, 150, 138, 26);
        add(tongquanComboBox);

        ngaykham = new JDateChooser();
        ngaykham.setDateFormatString("dd/MM/yyyy");
        ngaykham.setBounds(396, 98, 138, 20);
        add(ngaykham);

        // Labels
        JLabel mabenhanLabel = new JLabel("Mã bệnh án:");
        mabenhanLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        mabenhanLabel.setBounds(29, 33, 90, 29);
        add(mabenhanLabel);

        JLabel macudanLabel = new JLabel("Mã cư dân:");
        macudanLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        macudanLabel.setBounds(29, 89, 90, 29);
        add(macudanLabel);

        JLabel hotenLabel = new JLabel("Họ tên:");
        hotenLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        hotenLabel.setBounds(29, 148, 90, 29);
        add(hotenLabel);

        JLabel phutrachLabel = new JLabel("Phụ trách:");
        phutrachLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        phutrachLabel.setBounds(296, 39, 90, 29);
        add(phutrachLabel);

        JLabel ngaykhamLabel = new JLabel("Ngày khám:");
        ngaykhamLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        ngaykhamLabel.setBounds(296, 95, 90, 29);
        add(ngaykhamLabel);

        JLabel tongquanLabel = new JLabel("Tổng quan:");
        tongquanLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        tongquanLabel.setBounds(296, 148, 90, 29);
        add(tongquanLabel);

        JLabel chandoanLabel = new JLabel("Chẩn đoán");
        chandoanLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        chandoanLabel.setBounds(591, 39, 90, 29);
        add(chandoanLabel);

        JLabel donthuocLabel = new JLabel("Đơn thuốc:");
        donthuocLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        donthuocLabel.setBounds(591, 95, 90, 29);
        add(donthuocLabel);

        JLabel ghichuLabel = new JLabel("Ghi chú:");
        ghichuLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        ghichuLabel.setBounds(591, 148, 90, 29);
        add(ghichuLabel);

        // Functionality panel
        JPanel chucnangBenhAnPanel = new JPanel();
        chucnangBenhAnPanel.setLayout(null);
        chucnangBenhAnPanel.setBorder(new TitledBorder(new EtchedBorder(), "Chức năng", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        chucnangBenhAnPanel.setBackground(new Color(245, 245, 245));
        chucnangBenhAnPanel.setBounds(872, 255, 159, 210);
        add(chucnangBenhAnPanel);

        themBenhan = new JLabel("Thêm");
        themBenhan.setIcon(new ImageIcon(getClass().getResource("/images/them.png")));
        themBenhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        themBenhan.setBounds(10, 22, 94, 36);
        chucnangBenhAnPanel.add(themBenhan);

        suaBenhan = new JLabel("Sửa");
        suaBenhan.setIcon(new ImageIcon(getClass().getResource("/images/sua.png")));
        suaBenhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        suaBenhan.setBounds(10, 74, 94, 36);
        chucnangBenhAnPanel.add(suaBenhan);

        xoaBenhan = new JLabel("Xóa");
        xoaBenhan.setIcon(new ImageIcon(getClass().getResource("/images/xoa.png")));
        xoaBenhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        xoaBenhan.setBounds(10, 121, 94, 36);
        chucnangBenhAnPanel.add(xoaBenhan);

        xuatExcelBenhan = new JLabel("Xuất Excel");
        xuatExcelBenhan.setIcon(new ImageIcon(getClass().getResource("/images/excel.png")));
        xuatExcelBenhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        xuatExcelBenhan.setBounds(10, 168, 124, 36);
        chucnangBenhAnPanel.add(xuatExcelBenhan);

        // Search panel
        JPanel timkiemBenhAnPanel = new JPanel();
        timkiemBenhAnPanel.setLayout(null);
        timkiemBenhAnPanel.setBorder(new TitledBorder(null, "Tìm kiếm", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        timkiemBenhAnPanel.setBackground(new Color(245, 245, 245));
        timkiemBenhAnPanel.setBounds(872, 476, 159, 103);
        add(timkiemBenhAnPanel);

        timkiemBenhAnField = new JTextField();
        timkiemBenhAnField.setFont(new Font("Constantia", Font.PLAIN, 15));
        timkiemBenhAnField.setBounds(10, 28, 143, 20);
        timkiemBenhAnPanel.add(timkiemBenhAnField);

        JLabel timkiemBenhAn = new JLabel("");
        timkiemBenhAn.setIcon(new ImageIcon(getClass().getResource("/images/timkiem.png")));
        timkiemBenhAn.setBounds(129, 59, 24, 33);
        timkiemBenhAnPanel.add(timkiemBenhAn);

        // Detail button
        chitietbenhanButton = new JButton("Chi tiết");
        chitietbenhanButton.setBackground(new Color(245, 245, 245));
        chitietbenhanButton.setIcon(new ImageIcon(getClass().getResource("/images/chitiet.png")));
        chitietbenhanButton.setFont(new Font("Constantia", Font.PLAIN, 15));
        chitietbenhanButton.setBounds(872, 205, 159, 29);
        add(chitietbenhanButton);

        // Event listeners
        chitietbenhanButton.addActionListener(e -> {
            ChitietBenhAn dialog = new ChitietBenhAn((JFrame) SwingUtilities.getWindowAncestor(this));
            dialog.setVisible(true);
        });

        themBenhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // 1. Get data from form
                    int maBenhAn = Integer.parseInt(mabenhanField.getText().trim());
                    int maCuDan = Integer.parseInt(macudanField.getText().trim());
                    String hoTen = hotenBenhAnField.getText().trim();
                    String phuTrach = (String) phutrachComboBox.getSelectedItem();
                    java.util.Date utilNgayKham = ngaykham.getDate();
                    String tongQuan = (String) tongquanComboBox.getSelectedItem();
                    String chanDoan = chandoanField.getText().trim();
                    String donThuoc = donthuocField.getText().trim();
                    String ghiChu = ghichuField.getText().trim();

                    // 2. Validate inputs
                    if (hoTen.isEmpty() || phuTrach == null || utilNgayKham == null ||
                            tongQuan == null || chanDoan.isEmpty() || donThuoc.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin bắt buộc!");
                        return;
                    }

                    // 3. Convert date
                    Date sqlNgayKham = new Date(utilNgayKham.getTime());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String ngayKhamStr = sdf.format(sqlNgayKham);

                    // 4. Add to database (assuming ConnectData has a method for medical records)
                    ConnectData connectData = new ConnectData();
                    boolean success = connectData.themBenhAn(maBenhAn, maCuDan, hoTen, phuTrach, sqlNgayKham, tongQuan, chanDoan, donThuoc, ghiChu);

                    // 5. Update UI
                    if (success) {
                        benhanModel.addRow(new Object[]{maBenhAn, maCuDan, hoTen, phuTrach, ngayKhamStr, tongQuan, chanDoan, donThuoc, ghiChu});
                        JOptionPane.showMessageDialog(null, "Thêm bệnh án thành công!");
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại (có thể mã bệnh án đã tồn tại)!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                }
            }
        });

        suaBenhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = benhanTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh án để sửa!");
                    return;
                }

                try {
                    // 1. Get data from form
                    int maBenhAn = Integer.parseInt(mabenhanField.getText().trim());
                    int maCuDan = Integer.parseInt(macudanField.getText().trim());
                    String hoTen = hotenBenhAnField.getText().trim();
                    String phuTrach = (String) phutrachComboBox.getSelectedItem();
                    java.util.Date utilNgayKham = ngaykham.getDate();
                    String tongQuan = (String) tongquanComboBox.getSelectedItem();
                    String chanDoan = chandoanField.getText().trim();
                    String donThuoc = donthuocField.getText().trim();
                    String ghiChu = ghichuField.getText().trim();

                    // 2. Validate inputs
                    if (hoTen.isEmpty() || phuTrach == null || utilNgayKham == null ||
                            tongQuan == null || chanDoan.isEmpty() || donThuoc.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin bắt buộc!");
                        return;
                    }

                    // 3. Convert date
                    Date sqlNgayKham = new Date(utilNgayKham.getTime());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    String ngayKhamStr = sdf.format(sqlNgayKham);

                    // 4. Update database
                    ConnectData connectData = new ConnectData();
                    boolean success = connectData.suaBenhAn(maBenhAn, maCuDan, hoTen, phuTrach, sqlNgayKham, tongQuan, chanDoan, donThuoc, ghiChu);

                    // 5. Update UI
                    if (success) {
                        benhanModel.setValueAt(maBenhAn, selectedRow, 0);
                        benhanModel.setValueAt(maCuDan, selectedRow, 1);
                        benhanModel.setValueAt(hoTen, selectedRow, 2);
                        benhanModel.setValueAt(phuTrach, selectedRow, 3);
                        benhanModel.setValueAt(ngayKhamStr, selectedRow, 4);
                        benhanModel.setValueAt(tongQuan, selectedRow, 5);
                        benhanModel.setValueAt(chanDoan, selectedRow, 6);
                        benhanModel.setValueAt(donThuoc, selectedRow, 7);
                        benhanModel.setValueAt(ghiChu, selectedRow, 8);
                        JOptionPane.showMessageDialog(null, "Cập nhật bệnh án thành công!");
                        clearForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                }
            }
        });

        xoaBenhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = benhanTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh án để xóa!");
                    return;
                }

                int maBenhAn = (int) benhanModel.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa bệnh án mã " + maBenhAn + " không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;

                ConnectData connectData = new ConnectData();
                boolean success = connectData.XoaBenhAn(maBenhAn);
                if (success) {
                    benhanModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Xóa bệnh án thành công!");
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại!");
                }
            }
        });

        timkiemBenhAn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String keyword = timkiemBenhAnField.getText().trim();
                if (keyword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa!");
                    return;
                }

                ConnectData connectData = new ConnectData();
                connectData.timKiemBenhAn(benhanModel, keyword);
                if (benhanModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!");
                }
            }
        });
//        Lấy danh sách nhân viên từ bảng nhân sự
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        ArrayList<String> dsNhanVien = con.getDanhSachNhanVien();
        for (String ten : dsNhanVien) {
            model.addElement(ten);
        }
        phutrachComboBox.setModel(model);

        benhanTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = benhanTable.getSelectedRow();
                if (selectedRow >= 0) {
                    mabenhanField.setText(benhanModel.getValueAt(selectedRow, 0).toString());
                    macudanField.setText(benhanModel.getValueAt(selectedRow, 1).toString());
                    hotenBenhAnField.setText(benhanModel.getValueAt(selectedRow, 2).toString());
                    phutrachComboBox.setSelectedItem(benhanModel.getValueAt(selectedRow, 3));
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String strNgaykham = benhanTable.getValueAt(selectedRow, 4).toString();
                        ngaykham.setDate(sdf.parse(strNgaykham));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    tongquanComboBox.setSelectedItem(benhanModel.getValueAt(selectedRow, 5));
                    chandoanField.setText(benhanModel.getValueAt(selectedRow, 6).toString());
                    donthuocField.setText(benhanModel.getValueAt(selectedRow, 7).toString());
                    ghichuField.setText(benhanModel.getValueAt(selectedRow, 8).toString());
                }
            }
        });
    }

    public void clearForm () {
        mabenhanField.setText("");
        macudanField.setText("");
        hotenBenhAnField.setText("");
        phutrachComboBox.setSelectedIndex(-1);
        ngaykham.setDate(null);
        tongquanComboBox.setSelectedIndex(-1);
        chandoanField.setText("");
        donthuocField.setText("");
        ghichuField.setText("");
    }
}