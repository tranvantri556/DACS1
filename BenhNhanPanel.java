import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BenhNhanPanel extends JPanel {
    public JTextField IDField, hotenField, sodienthoaiField, lienlacField, timkiemBenhNhanField, tennguoithanField;
    public JRadioButton namRadioButton, nuRadioButton;
    public JTable benhnhanTable;
    public DefaultTableModel benhnhanModel;
    public JLabel hienthianhLabel;
    public JDateChooser ngaysinh, ngayvaovien;
    public JComboBox<String> nguoithanButton;
    public JButton chonanhButton;

    public BenhNhanPanel() {
        setLayout(null);
        setBackground(new Color(245, 245, 245));

        benhnhanModel = new DefaultTableModel(new String[]{"Mã cư dân", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Số điện thoại", "Người thân", "Liên lạc", "Ngày vào viện"}, 0);
        ConnectData con = new ConnectData();
        benhnhanModel = con.getBenhNhanModel();
        benhnhanTable = new JTable(benhnhanModel);
        benhnhanTable.setToolTipText("");
        benhnhanTable.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
        benhnhanTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        benhnhanTable.setForeground(new Color(128, 0, 0));
        benhnhanTable.setBackground(new Color(227, 227, 227));

        JScrollPane benhnhanScrollPane = new JScrollPane(benhnhanTable);
        benhnhanScrollPane.setViewportBorder(null);
        benhnhanScrollPane.setToolTipText("");
        benhnhanScrollPane.setBounds(16, 245, 842, 380);
        add(benhnhanScrollPane);

        JLabel[] labels = new JLabel[]{
                new JLabel("Mã cư dân:"), new JLabel("Họ tên:"), new JLabel("Ngày sinh:"), new JLabel("Giới tính:"),
                new JLabel("Địa chỉ:"), new JLabel("Số điện thoại:"), new JLabel("Người thân:"), new JLabel("Ngày vào viện:"), new JLabel("Liên lạc:")
        };

        int[][] bounds = {
                {33, 11, 83, 26}, {33, 66, 83, 26}, {33, 115, 83, 26}, {33, 173, 83, 26},
                {311, 11, 83, 26}, {311, 65, 99, 26}, {311, 115, 93, 26}, {621, 11, 112, 26}, {311, 173, 99, 26}
        };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Constantia", Font.PLAIN, 15));
            labels[i].setBounds(bounds[i][0], bounds[i][1], bounds[i][2], bounds[i][3]);
            add(labels[i]);
        }

        IDField = new JTextField();
        IDField.setFont(new Font("Constantia", Font.PLAIN, 15));
        IDField.setBounds(126, 13, 109, 20);
        add(IDField);

        hotenField = new JTextField();
        hotenField.setFont(new Font("Constantia", Font.PLAIN, 15));
        hotenField.setBounds(126, 68, 109, 20);
        add(hotenField);

        sodienthoaiField = new JTextField();
        sodienthoaiField.setFont(new Font("Constantia", Font.PLAIN, 15));
        sodienthoaiField.setBounds(432, 69, 123, 20);
        add(sodienthoaiField);

        JTextArea diachiField = new JTextArea();
        diachiField.setFont(new Font("Monospaced", Font.PLAIN, 15));
        diachiField.setBounds(432, 11, 123, 33);
        add(diachiField);

        namRadioButton = new JRadioButton("Nam");
        namRadioButton.setBackground(new Color(245, 245, 245));
        namRadioButton.setFont(new Font("Constantia", Font.PLAIN, 14));
        namRadioButton.setBounds(122, 173, 57, 23);
        add(namRadioButton);

        nuRadioButton = new JRadioButton("Nữ");
        nuRadioButton.setBackground(new Color(245, 245, 245));
        nuRadioButton.setFont(new Font("Constantia", Font.PLAIN, 14));
        nuRadioButton.setBounds(181, 173, 57, 23);
        add(nuRadioButton);

        ButtonGroup gioitinh = new ButtonGroup();
        gioitinh.add(namRadioButton);
        gioitinh.add(nuRadioButton);

        lienlacField = new JTextField();
        lienlacField.setFont(new Font("Constantia", Font.PLAIN, 15));
        lienlacField.setBounds(432, 170, 123, 20);
        add(lienlacField);

        String[] quanhe = {"Con", "Cháu", "Vợ", "Chồng", "Người bảo hộ"};
        nguoithanButton = new JComboBox<>(quanhe);
        nguoithanButton.setFont(new Font("Constantia", Font.PLAIN, 12));
        nguoithanButton.setBounds(525, 117, 57, 24);
        add(nguoithanButton);

        tennguoithanField = new JTextField();
        tennguoithanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        tennguoithanField.setBounds(432, 117, 93, 24);
        add(tennguoithanField);

        ngaysinh = new JDateChooser();
        ngaysinh.setDateFormatString("dd/MM/yyyy");
        ngaysinh.setBounds(126, 115, 109, 20);
        add(ngaysinh);

        ngayvaovien = new JDateChooser();
        ngayvaovien.setDateFormatString("dd/MM/yyyy");
        ngayvaovien.setBounds(729, 11, 123, 20);
        add(ngayvaovien);

        JPanel avatarPanel = new JPanel();
        avatarPanel.setBackground(new Color(211, 211, 211));
        avatarPanel.setBounds(868, 0, 163, 199);
        avatarPanel.setLayout(null);
        add(avatarPanel);

        hienthianhLabel = new JLabel("");
        hienthianhLabel.setBounds(10, 11, 143, 172);
        avatarPanel.add(hienthianhLabel);

        chonanhButton = new JButton("Chọn ảnh đại diện");
        chonanhButton.setFont(new Font("Constantia", Font.PLAIN, 12));
//        chonanhButton.setIcon(new ImageIcon(View.class.getResource("/images/chonanh.png")));
        chonanhButton.setBounds(868, 210, 163, 33);
        add(chonanhButton);

        chonanhButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Chọn ảnh");
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Ảnh", "jpg", "png", "jpeg", "bmp", "gif"));

                int result = fileChooser.showOpenDialog(avatarPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                    Image img = icon.getImage();
                    Image scaledImg = img.getScaledInstance(hienthianhLabel.getWidth(), hienthianhLabel.getHeight(), Image.SCALE_SMOOTH);
                    hienthianhLabel.setIcon(new ImageIcon(scaledImg));
                }
            }
        });

        JPanel chucnangBenhNhanPanel = new JPanel();
        chucnangBenhNhanPanel.setBackground(new Color(245, 245, 245));
        chucnangBenhNhanPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1EE9c n\u0103ng", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        chucnangBenhNhanPanel.setBounds(868, 252, 163, 210);
        add(chucnangBenhNhanPanel);
        chucnangBenhNhanPanel.setLayout(null);

        JLabel themBenhNhan = new JLabel("Thêm");
        themBenhNhan.setBounds(10, 22, 94, 36);
        themBenhNhan.setIcon(new ImageIcon(View.class.getResource("/images/them.png")));
        themBenhNhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        chucnangBenhNhanPanel.add(themBenhNhan);

        JLabel suaBenhNhan = new JLabel("Sửa");
        suaBenhNhan.setIcon(new ImageIcon(View.class.getResource("/images/sua.png")));
        suaBenhNhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        suaBenhNhan.setBounds(10, 74, 94, 36);
        chucnangBenhNhanPanel.add(suaBenhNhan);

        JLabel xuatExcelBenhNhan = new JLabel("Xuất Excel");
        xuatExcelBenhNhan.setIcon(new ImageIcon(View.class.getResource("/images/excel.png")));
        xuatExcelBenhNhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        xuatExcelBenhNhan.setBounds(10, 168, 124, 36);
        chucnangBenhNhanPanel.add(xuatExcelBenhNhan);

        JLabel xoaBenhNhan = new JLabel("Xóa");
        xoaBenhNhan.setIcon(new ImageIcon(View.class.getResource("/images/xoa.png")));
        xoaBenhNhan.setFont(new Font("Constantia", Font.PLAIN, 15));
        xoaBenhNhan.setBounds(10, 121, 94, 36);
        chucnangBenhNhanPanel.add(xoaBenhNhan);

        // Tìm kiếm panel
        JPanel timkiemPanel = new JPanel();
        timkiemPanel.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        timkiemPanel.setBackground(new Color(245, 245, 245));
        timkiemPanel.setBounds(868, 473, 163, 103);
        add(timkiemPanel);
        timkiemPanel.setLayout(null);

        timkiemBenhNhanField = new JTextField();
        timkiemBenhNhanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        timkiemBenhNhanField.setBackground(new Color(255, 255, 255));
        timkiemBenhNhanField.setBounds(22, 28, 131, 20);
        timkiemPanel.add(timkiemBenhNhanField);
        timkiemBenhNhanField.setColumns(10);

        JLabel timkiemButton = new JLabel("");
        timkiemButton.setIcon(new ImageIcon(View.class.getResource("/images/timkiem.png")));
        timkiemButton.setBounds(129, 59, 24, 33);
        timkiemPanel.add(timkiemButton);

        // Sau khi khởi tạo benhnhanTable và benhnhanModel
        benhnhanTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = benhnhanTable.getSelectedRow();
                if (row < 0) return;

                IDField.setText(     benhnhanModel.getValueAt(row, 0).toString());
                hotenField.setText(  benhnhanModel.getValueAt(row, 1).toString());

                // Ngày sinh:
                Object objNs = benhnhanModel.getValueAt(row, 2);
                if (objNs instanceof java.sql.Date) {
                    ngaysinh.setDate(new java.util.Date(((java.sql.Date)objNs).getTime()));
                }

                // Giới tính
                String gt = benhnhanModel.getValueAt(row, 3).toString();
                namRadioButton.setSelected(gt.equals("Nam"));
                nuRadioButton.setSelected(gt.equals("Nữ"));

                // Địa chỉ
                diachiField.setText( benhnhanModel.getValueAt(row, 4).toString());

                // SĐT
                sodienthoaiField.setText(benhnhanModel.getValueAt(row, 5).toString());

                // Người thân: tách tên – quan hệ nếu cần
                String fullThan = benhnhanModel.getValueAt(row, 6).toString();

                if (fullThan.contains(" (") && fullThan.endsWith(")")) {
                    // Tách name và rel
                    String name = fullThan.substring(0, fullThan.indexOf(" (")).trim();
                    String rel = fullThan.substring(fullThan.indexOf(" (") + 2, fullThan.length() - 1).trim(); // Bỏ dấu "(" và ")"

                    // Cập nhật JTextField với name
                    tennguoithanField.setText(name);

                    // Tìm item phù hợp trong ComboBox
                    boolean found = false;
                    for (int i = 0; i < nguoithanButton.getItemCount(); i++) {
                        String item = nguoithanButton.getItemAt(i).toString().trim();
                        if (item.equalsIgnoreCase(rel)) {
                            nguoithanButton.setSelectedIndex(i); // Chọn item phù hợp
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        nguoithanButton.setSelectedIndex(0); // Chọn mặc định nếu không khớp
                    }
                } else {
                    // Nếu không có dấu "(" và ")"
                    tennguoithanField.setText(fullThan.trim());
                    nguoithanButton.setSelectedIndex(0); // Không có thông tin người thân
                }


                // Liên lạc
                lienlacField.setText(benhnhanModel.getValueAt(row, 7).toString());

                // Ngày vào viện
                Object objNv = benhnhanModel.getValueAt(row, 8);
                if (objNv instanceof java.sql.Date) {
                    ngayvaovien.setDate(new java.util.Date(((java.sql.Date)objNv).getTime()));
                }
            }
        });

        themBenhNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // 1. Lấy dữ liệu từ form
                    int id = Integer.parseInt(IDField.getText().trim());
                    String hoten = hotenField.getText().trim();
                    String diachi = diachiField.getText().trim();
                    String sodienthoai = sodienthoaiField.getText().trim();
                    String nguoithan = tennguoithanField.getText().trim();
                    String quanHe = (String) nguoithanButton.getSelectedItem();
                    String lienlac = lienlacField.getText().trim();
                    String gioiTinh = namRadioButton.isSelected() ? "Nam"
                            : nuRadioButton.isSelected() ? "Nữ" : "";

                    // 2. Lấy ngày từ JDateChooser và chuyển sang java.sql.Date
                    java.util.Date utilNs = ngaysinh.getDate();
                    java.util.Date utilNv = ngayvaovien.getDate();
                    if (utilNs == null || utilNv == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh và ngày vào viện!");
                        return;
                    }
                    java.sql.Date sqlNs = new java.sql.Date(utilNs.getTime());
                    java.sql.Date sqlNv = new java.sql.Date(utilNv.getTime());

                    // 3. Kiểm tra bắt buộc
                    if (hoten.isEmpty() || diachi.isEmpty() || sodienthoai.isEmpty()
                            || nguoithan.isEmpty() || lienlac.isEmpty() || gioiTinh.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                        return;
                    }

                    // 4. Gọi hàm thêm vào database
                    ConnectData connectData = new ConnectData();
                    // Lưu ý: phương thức ThemBenhnhan() phải được đổi để nhận java.sql.Date cho 2 tham số ngày
                    boolean ok = connectData.ThemBenhnhan(
                            hoten,
                            String.valueOf(sqlNs),
                            gioiTinh,
                            diachi,
                            sodienthoai,
                            nguoithan + " (" + quanHe + ")",
                            lienlac,
                            String.valueOf(sqlNv)
                    );

                    // 5. Cập nhật UI
                    if (ok) {
                        JOptionPane.showMessageDialog(null, "Thêm bệnh nhân thành công!");
                        benhnhanModel.addRow(new Object[]{
                                id, hoten, sqlNs, gioiTinh, diachi,
                                sodienthoai, nguoithan + " (" + quanHe + ")", lienlac, sqlNv
                        });
                        // reset form
                        IDField.setText("");
                        hotenField.setText("");
                        diachiField.setText("");
                        sodienthoaiField.setText("");
                        ngaysinh.setDate(null);
                        ngayvaovien.setDate(null);
                        tennguoithanField.setText("");
                        lienlacField.setText("");
                        namRadioButton.setSelected(false);
                        nuRadioButton.setSelected(false);
                        nguoithanButton.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại (có thể SĐT đã tồn tại)");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID phải là số nguyên!");
                }
            }
        });

        suaBenhNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = benhnhanTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân để sửa!");
                    return;
                }

                try {
                    // 1. Lấy dữ liệu từ form
                    int id = Integer.parseInt(IDField.getText().trim());
                    String hoten = hotenField.getText().trim();
                    String diachi = diachiField.getText().trim();
                    String sodienthoai = sodienthoaiField.getText().trim();
                    String tenNguoiThan = tennguoithanField.getText().trim();
                    String quanHe = (String) nguoithanButton.getSelectedItem();
                    String lienlac = lienlacField.getText().trim();
                    String gioiTinh = namRadioButton.isSelected() ? "Nam" :
                            nuRadioButton.isSelected() ? "Nữ" : "";

                    java.util.Date utilNs = ngaysinh.getDate();
                    java.util.Date utilNv = ngayvaovien.getDate();
                    if (utilNs == null || utilNv == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh và ngày vào viện!");
                        return;
                    }
                    java.sql.Date sqlNs = new java.sql.Date(utilNs.getTime());
                    java.sql.Date sqlNv = new java.sql.Date(utilNv.getTime());

                    if (hoten.isEmpty() || diachi.isEmpty() || sodienthoai.isEmpty() ||
                            tenNguoiThan.isEmpty() || lienlac.isEmpty() || gioiTinh.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
                        return;
                    }

                    String nguoiThanFull = tenNguoiThan + " (" + quanHe + ")";

                    // 2. Gọi hàm cập nhật trong ConnectData
                    ConnectData connectData = new ConnectData();
                    boolean ok = connectData.suaBenhNhan(
                            id, hoten, String.valueOf(sqlNs), gioiTinh, diachi,
                            sodienthoai, nguoiThanFull, lienlac, String.valueOf(sqlNv)
                    );

                    if (ok) {
                        // 3. Cập nhật lại dòng trong JTable
                        benhnhanModel.setValueAt(hoten, selectedRow, 1);
                        benhnhanModel.setValueAt(sqlNs, selectedRow, 2);
                        benhnhanModel.setValueAt(gioiTinh, selectedRow, 3);
                        benhnhanModel.setValueAt(diachi, selectedRow, 4);
                        benhnhanModel.setValueAt(sodienthoai, selectedRow, 5);
                        benhnhanModel.setValueAt(nguoiThanFull, selectedRow, 6);
                        benhnhanModel.setValueAt(lienlac, selectedRow, 7);
                        benhnhanModel.setValueAt(sqlNv, selectedRow, 8);

                        JOptionPane.showMessageDialog(null, "Cập nhật thành công!");

                        IDField.setText("");
                        hotenField.setText("");
                        diachiField.setText("");
                        sodienthoaiField.setText("");
                        ngaysinh.setDate(null);
                        ngayvaovien.setDate(null);
                        tennguoithanField.setText("");
                        lienlacField.setText("");
                        namRadioButton.setSelected(false);
                        nuRadioButton.setSelected(false);
                        nguoithanButton.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
                }
            }
        });

        xoaBenhNhan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = benhnhanTable.getSelectedRow();
                if (row < 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một bệnh nhân để xóa!");
                    return;
                }

                // Đọc ID từ cột 0
                int id = (int) benhnhanModel.getValueAt(row, 0);

                // Xác nhận lại
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Bạn có chắc muốn xoá bệnh nhân mã " + id + " không?",
                        "Xác nhận xoá",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm != JOptionPane.YES_OPTION) return;

                // Gọi xóa trong DB
                ConnectData cd = new ConnectData();
                boolean success = cd.XoaBenhNhan(id);
                if (success) {
                    // Xóa khỏi UI
                    benhnhanModel.removeRow(row);
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");

                    IDField.setText("");
                    hotenField.setText("");
                    diachiField.setText("");
                    sodienthoaiField.setText("");
                    ngaysinh.setDate(null);
                    ngayvaovien.setDate(null);
                    tennguoithanField.setText("");
                    lienlacField.setText("");
                    namRadioButton.setSelected(false);
                    nuRadioButton.setSelected(false);
                    nguoithanButton.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại. Vui lòng thử lại.");
                }
            }
        });

        timkiemButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String keyword = timkiemBenhNhanField.getText().trim();
                if (keyword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa!");
                    return;
                }

                ConnectData connect = new ConnectData();
                connect.timKiemBenhNhan(benhnhanModel, keyword); // truyền model trực tiếp

                if (benhnhanModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào.");
                }
            }
        });
    }

    public DefaultTableModel getBenhNhanModel() {
        return benhnhanModel;
    }



    public JTable getBenhNhanTable() {
        return benhnhanTable;
    }
}
