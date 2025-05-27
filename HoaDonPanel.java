import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class HoaDonPanel extends JPanel {
    private JTextField mahoadonFiled;
    private JTextField hotenField;
    private JTextField mabenhnhanField;
    private JTextField dongia;
    private JLabel tongtienLabel;
    private DefaultTableModel hoadonModel;
    private JTable hoadonTable;
    private ConnectData con;

    public HoaDonPanel() {
        setBackground(new Color(245, 245, 245));
        setLayout(null);

        hoadonModel = new DefaultTableModel(new String[]{"Mã hóa đơn", "Mã bệnh nhân", "Ngày lập", "Dịch vụ", "Số ngày", "Đơn giá", "Thành tiền", "Trạng thái"}, 0);

        ConnectData con = new ConnectData();
        hoadonModel = con.getHoaDonModel();
        hoadonTable = new JTable(hoadonModel);

        hoadonTable.getColumnModel().getColumn(2).setMinWidth(0);
        hoadonTable.getColumnModel().getColumn(2).setMaxWidth(0);
        hoadonTable.getColumnModel().getColumn(2).setWidth(0);

        hoadonTable.getColumnModel().getColumn(3).setMinWidth(0);
        hoadonTable.getColumnModel().getColumn(3).setMaxWidth(0);
        hoadonTable.getColumnModel().getColumn(3).setWidth(0);

        hoadonTable.getColumnModel().getColumn(4).setMinWidth(0);
        hoadonTable.getColumnModel().getColumn(4).setMaxWidth(0);
        hoadonTable.getColumnModel().getColumn(4).setWidth(0);
        hoadonTable.setToolTipText("");
        hoadonTable.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
        hoadonTable.setFont(new Font("Constantia", Font.PLAIN, 10));
        hoadonTable.setBackground(new Color(227, 227, 227));

        JScrollPane hoadonScrollPane = new JScrollPane(hoadonTable);
        hoadonScrollPane.setViewportBorder(null);
        hoadonScrollPane.setToolTipText("");
        hoadonScrollPane.setBounds(422, 301, 609, 324);
        add(hoadonScrollPane);

        JPanel thongtinhoadonPanel = new JPanel();
        thongtinhoadonPanel.setBorder(new LineBorder(new Color(169, 169, 169), 1, true));
        thongtinhoadonPanel.setBounds(10, 11, 402, 614);
        add(thongtinhoadonPanel);
        thongtinhoadonPanel.setLayout(null);

        JLabel label = new JLabel("Viện dưỡng lão ELDERCARE\r\n");
        label.setForeground(new Color(0, 0, 128));
        label.setFont(new Font("Segoe UI", Font.BOLD, 19));
        label.setBounds(67, 54, 263, 25);
        thongtinhoadonPanel.add(label);

        JLabel thoigianLabel = new JLabel("Thời gian:");
        thoigianLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        thoigianLabel.setBounds(23, 150, 208, 25);
        thongtinhoadonPanel.add(thoigianLabel);

        JLabel thunganLabel = new JLabel("Thu ngân:");
        thunganLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        thunganLabel.setBounds(23, 186, 208, 25);
        thongtinhoadonPanel.add(thunganLabel);

        JLabel mahoadonLabel = new JLabel("Mã hóa đơn:");
        mahoadonLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        mahoadonLabel.setBounds(23, 222, 208, 25);
        thongtinhoadonPanel.add(mahoadonLabel);

        JPanel banggiaPanel = new JPanel();
        banggiaPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
        banggiaPanel.setBounds(23, 258, 369, 108);
        thongtinhoadonPanel.add(banggiaPanel);
        banggiaPanel.setLayout(new GridLayout(2, 3, 5, 5));

        JLabel dichvuLabel = new JLabel("Dịch vụ");
        dichvuLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        banggiaPanel.add(dichvuLabel);

        JLabel soluongLabel = new JLabel("Số ngày");
        soluongLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        banggiaPanel.add(soluongLabel);

        JLabel dongiaLabel = new JLabel("Đơn giá");
        dongiaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        banggiaPanel.add(dongiaLabel);

        JLabel dichvuField = new JLabel("...");
        dichvuField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        banggiaPanel.add(dichvuField);

        JLabel songayField = new JLabel("...");
        songayField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        banggiaPanel.add(songayField);

        JLabel dongiaField = new JLabel("...");
        dongiaField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        banggiaPanel.add(dongiaField);

        JLabel label1 = new JLabel("Tổng tiền: ");
        label1.setForeground(new Color(255, 0, 0));
        label1.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label1.setBounds(130, 420, 111, 34);
        thongtinhoadonPanel.add(label1);

        tongtienLabel = new JLabel("0 ");
        tongtienLabel.setForeground(Color.RED);
        tongtienLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        tongtienLabel.setBounds(264, 420, 128, 34);
        thongtinhoadonPanel.add(tongtienLabel);

        JLabel xuatpdfLabel = new JLabel("Xuất pdf");
        xuatpdfLabel.setForeground(new Color(0, 0, 128));
        xuatpdfLabel.setIcon(new ImageIcon(View.class.getResource("/images/pdf.png")));
        xuatpdfLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        xuatpdfLabel.setBounds(23, 557, 97, 46);
        thongtinhoadonPanel.add(xuatpdfLabel);

        JLabel label2 = new JLabel("HÓA ĐƠN THANH TOÁN");
        label2.setForeground(new Color(0, 0, 128));
        label2.setFont(new Font("Segoe UI", Font.BOLD, 25));
        label2.setBounds(47, 11, 308, 32);
        thongtinhoadonPanel.add(label2);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Đã thanh toán");
        chckbxNewCheckBox.setForeground(new Color(105, 105, 105));
        chckbxNewCheckBox.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        chckbxNewCheckBox.setBounds(247, 472, 149, 34);
        thongtinhoadonPanel.add(chckbxNewCheckBox);

        JPanel thongtinPanel = new JPanel();
        thongtinPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin chi ti\u1EBFt", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        thongtinPanel.setBounds(422, 11, 609, 279);
        add(thongtinPanel);
        thongtinPanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Mã hóa đơn:");
        lblNewLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblNewLabel.setBounds(10, 49, 121, 26);
        thongtinPanel.add(lblNewLabel);

        JLabel label4 = new JLabel("Họ tên:");
        label4.setFont(new Font("Constantia", Font.PLAIN, 15));
        label4.setBounds(10, 157, 121, 26);
        thongtinPanel.add(label4);

        JLabel label5 = new JLabel("Từ ngày:");
        label5.setFont(new Font("Constantia", Font.PLAIN, 15));
        label5.setBounds(288, 49, 96, 26);
        thongtinPanel.add(label5);

        JDateChooser tungay = new JDateChooser();
        tungay.setDateFormatString("dd/MM/yyyy");
        tungay.setBounds(394, 50, 121, 20);
        thongtinPanel.add(tungay);

        JLabel label6 = new JLabel("Đến ngày\r\n:");
        label6.setFont(new Font("Constantia", Font.PLAIN, 15));
        label6.setBounds(288, 105, 96, 26);
        thongtinPanel.add(label6);

        JDateChooser denngay = new JDateChooser();
        denngay.setDateFormatString("dd/MM/yyyy");
        denngay.setBounds(394, 100, 121, 20);
        thongtinPanel.add(denngay);

        JLabel label7 = new JLabel("Dịch vụ:");
        label7.setFont(new Font("Constantia", Font.PLAIN, 15));
        label7.setBounds(288, 157, 121, 26);
        thongtinPanel.add(label7);

        mahoadonFiled = new JTextField();
        mahoadonFiled.setBounds(124, 51, 121, 20);
        thongtinPanel.add(mahoadonFiled);
        mahoadonFiled.setColumns(10);

        hotenField = new JTextField();
        hotenField.setColumns(10);
        hotenField.setBounds(124, 159, 121, 20);
        thongtinPanel.add(hotenField);

        String[] dichvu = {"Gói cơ bản", "Gói chăm sóc đặc biệt", "Gói ngắn ngày", "Gói điều dưỡng"};

        JComboBox dichvuComboBox = new JComboBox(dichvu);
        dichvuComboBox.setFont(new Font("Constantia", Font.PLAIN, 11));
        dichvuComboBox.setBounds(394, 161, 121, 20);
        thongtinPanel.add(dichvuComboBox);

        JLabel mabenhnhanLabel = new JLabel("Mã bệnh nhân:");
        mabenhnhanLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        mabenhnhanLabel.setBounds(10, 105, 121, 26);
        thongtinPanel.add(mabenhnhanLabel);

        mabenhnhanField = new JTextField();
        mabenhnhanField.setColumns(10);
        mabenhnhanField.setBounds(124, 107, 121, 20);
        thongtinPanel.add(mabenhnhanField);

        JLabel lammoiLabel = new JLabel("");
        lammoiLabel.setIcon(new ImageIcon(View.class.getResource("/images/load.png")));
        lammoiLabel.setBounds(124, 226, 32, 42);
        thongtinPanel.add(lammoiLabel);

        JLabel suaLabel = new JLabel("");
        suaLabel.setIcon(new ImageIcon(View.class.getResource("/images/sua2.png")));
        suaLabel.setBounds(68, 226, 32, 42);
        thongtinPanel.add(suaLabel);

        JLabel themLabel = new JLabel("");
        themLabel.setIcon(new ImageIcon(View.class.getResource("/images/add.png")));
        themLabel.setBounds(10, 226, 32, 42);
        thongtinPanel.add(themLabel);

        JLabel label8 = new JLabel("Đơn giá:");
        label8.setFont(new Font("Constantia", Font.PLAIN, 15));
        label8.setBounds(288, 205, 121, 26);
        thongtinPanel.add(label8);

        dongia = new JTextField(); //Để tự dộng khi nhấn gói dịch vụ
        dongia.setColumns(10);
        dongia.setBounds(394, 207, 121, 20);
        thongtinPanel.add(dongia);

        themLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String maHoaDon = mahoadonFiled.getText();
                String maBenhNhan = mabenhnhanField.getText();
                String hoTen = hotenField.getText();
                String dichVu = (String) dichvuComboBox.getSelectedItem();
                java.util.Date tuNgay = tungay.getDate();
                java.util.Date denNgay = denngay.getDate();
                double donGia = Double.parseDouble(dongia.getText().isEmpty() ? "0" : dongia.getText());
                String ngayLap = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                String trangThai = chckbxNewCheckBox.isSelected() ? "Đã thanh toán" : "Chưa thanh toán";

                // Giả định số ngày tính từ khoảng thời gian
                long diffInMillies = denNgay.getTime() - tuNgay.getTime();
                int soNgay = (int) (diffInMillies / (1000 * 60 * 60 * 24));
                double thanhTien = soNgay * donGia;

                if (con.themHoaDon(maHoaDon, tuNgay, denNgay, maBenhNhan, hoTen, dichVu, thanhTien, ngayLap, trangThai, soNgay, donGia)) {
                    hoadonModel.addRow(new Object[]{maHoaDon, maBenhNhan, hoTen, new SimpleDateFormat("dd/MM/yyyy").format(tuNgay), new SimpleDateFormat("dd/MM/yyyy").format(denNgay), ngayLap, dichVu, soNgay, donGia, thanhTien, trangThai});
                    capNhatTongTien();
                    JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm hóa đơn thất bại!");
                }
            }
        });

        suaLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = hoadonTable.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        String maHoaDon = mahoadonFiled.getText();
                        String maBenhNhan = mabenhnhanField.getText();
                        String hoTen = hotenField.getText();
                        String dichVu = (String) dichvuComboBox.getSelectedItem();
                        java.util.Date tuNgay = tungay.getDate();
                        java.util.Date denNgay = denngay.getDate();
                        double donGia = Double.parseDouble(dongia.getText().isEmpty() ? "0" : dongia.getText());
                        String ngayLap = new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
                        String trangThai = chckbxNewCheckBox.isSelected() ? "Đã thanh toán" : "Chưa thanh toán";

                        long diffInMillies = denNgay.getTime() - tuNgay.getTime();
                        int soNgay = (int) (diffInMillies / (1000 * 60 * 60 * 24));
                        double thanhTien = soNgay * donGia;

                        if (con.suaHoaDon(maHoaDon, tuNgay, denNgay, maBenhNhan, hoTen, dichVu, thanhTien, ngayLap, trangThai, soNgay, donGia)) {
                            hoadonModel.addRow(new Object[]{maHoaDon, maBenhNhan, hoTen, new SimpleDateFormat("dd/MM/yyyy").format(tuNgay), new SimpleDateFormat("dd/MM/yyyy").format(denNgay), ngayLap, dichVu, soNgay, donGia, thanhTien, trangThai});
                            capNhatTongTien();
                            JOptionPane.showMessageDialog(null, "Sửa hóa đơn thành công!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Sửa hóa đơn thất bại!");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi sửa hóa đơn: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để sửa!");
                }
            }
        });


        lammoiLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mahoadonFiled.setText("");
                mabenhnhanField.setText("");
                hotenField.setText("");
                tungay.setDate(null);
                denngay.setDate(null);
                dichvuComboBox.setSelectedIndex(0);
                dongia.setText("");
                chckbxNewCheckBox.setSelected(false);

                // Hiển thị lên các JLabel tương ứng
                thoigianLabel.setText("Thời gian: ");
                thunganLabel.setText("Thu ngân: ");
                mahoadonLabel.setText("Mã hóa đơn: ");
                dichvuField.setText("...");
                songayField.setText("...");
                dongiaField.setText("...");
                tongtienLabel.setText("");
                chckbxNewCheckBox.setSelected(false);
            }
        });

        // Giả sử hoadonTable là JTable đang dùng
        hoadonTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = hoadonTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Lấy dữ liệu từng cột trong dòng được chọn
                    String maHoaDon = hoadonModel.getValueAt(selectedRow, 0).toString();
                    String maBenhNhan = hoadonModel.getValueAt(selectedRow, 2).toString();
                    String ngayLap = hoadonModel.getValueAt(selectedRow, 5).toString();
                    String dichVu = hoadonModel.getValueAt(selectedRow, 6).toString();
                    String soNgay = hoadonModel.getValueAt(selectedRow, 7).toString();
                    String donGia = hoadonModel.getValueAt(selectedRow, 8).toString();
                    String thanhTien = hoadonModel.getValueAt(selectedRow, 9).toString();
                    String trangThai = hoadonModel.getValueAt(selectedRow, 10).toString();

                    // Hiển thị lên các JLabel tương ứng
                    thoigianLabel.setText("Thời gian: " + ngayLap);
                    thunganLabel.setText("Thu ngân: " + maBenhNhan);
                    mahoadonLabel.setText("Mã hóa đơn: " + maHoaDon);
                    dichvuField.setText(dichVu);
                    songayField.setText(soNgay);
                    dongiaField.setText(donGia);
                    tongtienLabel.setText(thanhTien);
                    // Còn các label khác tương tự
                    // Nếu có label thu ngân, có thể lấy từ data hoặc bổ sung
                    // Nếu trạng thái là "Đã thanh toán", set JCheckBox tương ứng
                    chckbxNewCheckBox.setSelected(trangThai.trim().equalsIgnoreCase("Đã thanh toán"));
                }
            }
        });
//  Lấy dữ liệu lên ô nhập
        hoadonTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = hoadonTable.getSelectedRow();
                if (selectedRow != -1) {
                    mahoadonFiled.setText(hoadonTable.getValueAt(selectedRow, 0).toString());
                    mabenhnhanField.setText(hoadonTable.getValueAt(selectedRow, 1).toString());
                    hotenField.setText(hoadonTable.getValueAt(selectedRow, 2).toString());

                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String strTuNgay = hoadonTable.getValueAt(selectedRow, 3).toString();
                        String strDenNgay = hoadonTable.getValueAt(selectedRow, 4).toString();
                        tungay.setDate(sdf.parse(strTuNgay));
                        denngay.setDate(sdf.parse(strDenNgay));
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    dichvuComboBox.setSelectedItem(hoadonTable.getValueAt(selectedRow, 6).toString());
                    dongia.setText(hoadonTable.getValueAt(selectedRow, 8).toString());
                }
            }
        });
    }

    private void capNhatTongTien() {
        double tong = 0;
        for (int i = 0; i < hoadonModel.getRowCount(); i++) {
            Object value = hoadonModel.getValueAt(i, 6); // Cột thành tiền
            if (value != null) {
                try {
                    tong += Double.parseDouble(value.toString());
                } catch (NumberFormatException e) {
                    // bỏ qua hoặc xử lý
                }
            }
        }
        tongtienLabel.setText(String.format("%.2f", tong));
    }

}