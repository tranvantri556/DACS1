import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NhanSuPanel extends JPanel {
    public JTextField timkiemField;
    public DefaultTableModel nhansuModel;

    public NhanSuPanel(){
        setBackground(new Color(245, 245, 245));
        setLayout(null);

        nhansuModel = new DefaultTableModel(new String[] {"Mã nhân viên","Họ tên", "Giới tính", "Ngày sinh", "Điện thoại", "Địa chỉ", "Phòng ban", "Chức vụ", "Mức lương"}, 0);

        ConnectData con = new ConnectData();
        nhansuModel = con.getNhanvienModel();
        JTable nhansuTable = new JTable(nhansuModel);
        nhansuTable.setToolTipText("");
        nhansuTable.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
        nhansuTable.setFont(new Font("Constantia", Font.PLAIN, 10));
        nhansuTable.setBackground(new Color(227, 227, 227));

        // Chỉnh độ rộng từng cột (tùy bạn điều chỉnh số pixel theo nội dung)
        nhansuTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // Mã nhân viên
        nhansuTable.getColumnModel().getColumn(1).setPreferredWidth(150);  // Họ tên
        nhansuTable.getColumnModel().getColumn(2).setPreferredWidth(60);   // Giới tính
        nhansuTable.getColumnModel().getColumn(3).setPreferredWidth(100);  // Ngày sinh
        nhansuTable.getColumnModel().getColumn(4).setPreferredWidth(120);  // Điện thoại
        nhansuTable.getColumnModel().getColumn(5).setPreferredWidth(200);  // Địa chỉ
        nhansuTable.getColumnModel().getColumn(6).setPreferredWidth(140);  // Phòng ban
        nhansuTable.getColumnModel().getColumn(7).setPreferredWidth(120);  // Chức vụ
        nhansuTable.getColumnModel().getColumn(8).setPreferredWidth(100);  // Mức lương


        JScrollPane nhansuScrollPane = new JScrollPane(nhansuTable);
        nhansuScrollPane.setViewportBorder(null);
        nhansuScrollPane.setToolTipText("");
        nhansuScrollPane.setBounds(10, 167, 1021, 458);
        add(nhansuScrollPane);

        JLabel label1 = new JLabel("DANH SÁCH NHÂN VIÊN");
        label1.setForeground(new Color(0, 0, 128));
        label1.setFont(new Font("Segoe UI", Font.BOLD, 25));
        label1.setBounds(380, 11, 311, 51);
        add(label1);

        JPanel chucnangPanel = new JPanel();
        chucnangPanel.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
        chucnangPanel.setBounds(718, 73, 313, 83);
        add(chucnangPanel);
        chucnangPanel.setLayout(null);

        JLabel themNhansuLabel = new JLabel("");
        themNhansuLabel.setIcon(new ImageIcon(View.class.getResource("/images/themNhansu.png")));
        themNhansuLabel.setBounds(24, 24, 65, 48);
        chucnangPanel.add(themNhansuLabel);

        themNhansuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ThemNhanSuPanel dialog = new ThemNhanSuPanel(
                        (JFrame) SwingUtilities.getWindowAncestor(themNhansuLabel), nhansuModel);
                dialog.setVisible(true);
            }
        });

        JLabel suaNhansuLabel = new JLabel("");
        suaNhansuLabel.setIcon(new ImageIcon(View.class.getResource("/images/suaNhansu.png")));
        suaNhansuLabel.setBounds(99, 24, 54, 48);
        chucnangPanel.add(suaNhansuLabel);

        suaNhansuLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SuaNhanSuPanel dialog = new SuaNhanSuPanel(
                        (JFrame) SwingUtilities.getWindowAncestor(suaNhansuLabel), nhansuModel);
                dialog.setVisible(true);
            }
        });

        JLabel xuatExcel = new JLabel("");
        xuatExcel.setIcon(new ImageIcon(View.class.getResource("/images/excelIcon.png")));
        xuatExcel.setBounds(238, 24, 65, 48);
        chucnangPanel.add(xuatExcel);

        JLabel xoaNhansu = new JLabel("");
        xoaNhansu.setIcon(new ImageIcon(View.class.getResource("/images/delete.png")));
        xoaNhansu.setBounds(173, 24, 54, 48);
        chucnangPanel.add(xoaNhansu);

        JPanel locdanhsachPanel = new JPanel();
        locdanhsachPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "L\u1ECDc danh s\u00E1ch", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        locdanhsachPanel.setBounds(10, 73, 698, 83);
        locdanhsachPanel.setLayout(null);
        add(locdanhsachPanel);

        String[] phongban = {"Phòng y tế", "Phòng đào tạo", "Phòng tuyển dụng", "Phòng kế toán","Phòng hồ sơ và pháp lí"};
        JComboBox phongbanComboBox = new JComboBox(phongban);
        phongbanComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        phongbanComboBox.setBounds(10, 50, 206, 22);
        phongbanComboBox.setSelectedItem(null); // không chọn gì cả
        phongbanComboBox.setEditable(true);
        phongbanComboBox.getEditor().setItem("Phòng ban");
        Component phongbanEditor = phongbanComboBox.getEditor().getEditorComponent();
        if (phongbanEditor instanceof JTextField textField) {
            textField.setEditable(false);                // Không cho gõ
            textField.setFocusable(false);               // Không tab vào
            textField.setForeground(Color.BLACK);       // Nền trắng
            textField.setBorder(null);                   // Bỏ viền trong
            textField.setDisabledTextColor(Color.GRAY);  // Phòng trường hợp bị disable
        }
        locdanhsachPanel.add(phongbanComboBox);

        String[] gioitinh = {"Nam", "Nữ"};
        JComboBox gioitinhComboBox = new JComboBox(gioitinh);
        gioitinhComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        gioitinhComboBox.setBounds(226, 51, 117, 22);
        gioitinhComboBox.setSelectedItem(null); // không chọn gì cả
        gioitinhComboBox.setEditable(true);
        gioitinhComboBox.getEditor().setItem("Giới tính");
        Component gioitinhEditor = gioitinhComboBox.getEditor().getEditorComponent();
        if (gioitinhEditor instanceof JTextField textField) {
            textField.setEditable(false);                // Không cho gõ
            textField.setFocusable(false);               // Không tab vào
            textField.setForeground(Color.BLACK);       // Nền trắng
            textField.setBorder(null);                   // Bỏ viền trong
            textField.setDisabledTextColor(Color.GRAY);  // Phòng trường hợp bị disable
        }
        locdanhsachPanel.add(gioitinhComboBox);

        String[] mucluong = {"10-20M","21-35M","36-45M"};
        JComboBox mucluongComboBox = new JComboBox(mucluong);
        mucluongComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mucluongComboBox.setBounds(539, 50, 149, 22);
        mucluongComboBox.setSelectedItem(null); // không chọn gì cả
        mucluongComboBox.setEditable(true);
        mucluongComboBox.getEditor().setItem("Mức lương");
        Component mucluongEditor = mucluongComboBox.getEditor().getEditorComponent();
        if (mucluongEditor instanceof JTextField textField) {
            textField.setEditable(false);                // Không cho gõ
            textField.setFocusable(false);               // Không tab vào
            textField.setForeground(Color.BLACK);       // Nền trắng
            textField.setBorder(null);                   // Bỏ viền trong
            textField.setDisabledTextColor(Color.GRAY);  // Phòng trường hợp bị disable
        }
        locdanhsachPanel.add(mucluongComboBox);

        String[] dotuoi = {"20-30","31-40","41-55"};
        JComboBox dotuoiComboBox = new JComboBox(dotuoi);
        dotuoiComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dotuoiComboBox.setBounds(357, 51, 172, 22);
        dotuoiComboBox.setSelectedItem(null); // không chọn gì cả
        dotuoiComboBox.setEditable(true);
        dotuoiComboBox.getEditor().setItem("Độ tuổi");
        Component dotuoiEditor = dotuoiComboBox.getEditor().getEditorComponent();
        if (dotuoiEditor instanceof JTextField textField) {
            textField.setEditable(false);                // Không cho gõ
            textField.setFocusable(false);               // Không tab vào
            textField.setForeground(Color.BLACK);       // Nền trắng
            textField.setBorder(null);                   // Bỏ viền trong
            textField.setDisabledTextColor(Color.GRAY);  // Phòng trường hợp bị disable
        }
        locdanhsachPanel.add(dotuoiComboBox);

        timkiemField = new JTextField();
        timkiemField.setBounds(357, 17, 295, 20);
        locdanhsachPanel.add(timkiemField);
        timkiemField.setColumns(10);

        JLabel timkiemLabel = new JLabel("");
        timkiemLabel.setIcon(new ImageIcon(View.class.getResource("/images/timkiem.png")));
        timkiemLabel.setBounds(664, 17, 24, 22);
        locdanhsachPanel.add(timkiemLabel);

        timkiemLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String keyword = timkiemField.getText().trim().toLowerCase();

                String phongbanFilter = phongbanComboBox.getSelectedItem() != null && !phongbanComboBox.getSelectedItem().equals("Phòng ban")
                        ? phongbanComboBox.getSelectedItem().toString().toLowerCase() : "";
                String gioitinhFilter = gioitinhComboBox.getSelectedItem() != null && !gioitinhComboBox.getSelectedItem().equals("Giới tính")
                        ? gioitinhComboBox.getSelectedItem().toString().toLowerCase() : "";
                String dotuoiFilter = dotuoiComboBox.getSelectedItem() != null && !dotuoiComboBox.getSelectedItem().equals("Độ tuổi")
                        ? dotuoiComboBox.getSelectedItem().toString() : "";
                String mucluongFilter = mucluongComboBox.getSelectedItem() != null && !mucluongComboBox.getSelectedItem().equals("Mức lương")
                        ? mucluongComboBox.getSelectedItem().toString().toLowerCase() : "";

                // Lấy dữ liệu gốc từ ConnectData
                DefaultTableModel originalModel = con.getNhanvienModel();

                DefaultTableModel filteredModel = new DefaultTableModel(
                        new String[]{"Mã nhân viên", "Họ tên", "Giới tính", "Ngày sinh", "Điện thoại", "Địa chỉ", "Phòng ban", "Chức vụ", "Mức lương"}, 0);

                for (int i = 0; i < originalModel.getRowCount(); i++) {
                    String maNV = String.valueOf(originalModel.getValueAt(i, 0)).toLowerCase();
                    String hoTen = String.valueOf(originalModel.getValueAt(i, 1)).toLowerCase();
                    String gioiTinh = String.valueOf(originalModel.getValueAt(i, 2)).toLowerCase();
                    String ngaySinh = String.valueOf(originalModel.getValueAt(i, 3));
                    String dienThoai = String.valueOf(originalModel.getValueAt(i, 4)).toLowerCase();
                    String diaChi = String.valueOf(originalModel.getValueAt(i, 5)).toLowerCase();
                    String phongBan = String.valueOf(originalModel.getValueAt(i, 6)).toLowerCase();
                    String chucVu = String.valueOf(originalModel.getValueAt(i, 7)).toLowerCase();
                    String mucLuong = String.valueOf(originalModel.getValueAt(i, 8)).toLowerCase();

                    // Check từ khóa tìm kiếm (check tất cả các cột trừ Ngày sinh)
                    boolean keywordMatch = keyword.isEmpty() || maNV.contains(keyword) || hoTen.contains(keyword)
                            || gioiTinh.contains(keyword) || dienThoai.contains(keyword) || diaChi.contains(keyword)
                            || phongBan.contains(keyword) || chucVu.contains(keyword) || mucLuong.contains(keyword);

                    // Check bộ lọc phòng ban
                    boolean phongbanMatch = phongbanFilter.isEmpty() || phongBan.equals(phongbanFilter);

                    // Check bộ lọc giới tính
                    boolean gioitinhMatch = gioitinhFilter.isEmpty() || gioiTinh.equals(gioitinhFilter);

                    // Check bộ lọc mức lương
                    boolean mucluongMatch = mucluongFilter.isEmpty() || mucLuong.equals(mucluongFilter);

                    // Check bộ lọc độ tuổi dựa vào Ngày sinh
                    boolean dotuoiMatch = true;
                    if (!dotuoiFilter.isEmpty()) {
                        // Định dạng ngày sinh: "yyyy-MM-dd" hoặc "dd/MM/yyyy" cần parse thành tuổi
                        // Ví dụ: tính tuổi đơn giản dựa vào năm hiện tại và năm sinh
                        try {
                            String[] parts = null;
                            int yearOfBirth = 0;
                            if (ngaySinh.contains("-")) { // "yyyy-MM-dd"
                                parts = ngaySinh.split("-");
                                yearOfBirth = Integer.parseInt(parts[0]);
                            } else if (ngaySinh.contains("/")) { // "dd/MM/yyyy"
                                parts = ngaySinh.split("/");
                                yearOfBirth = Integer.parseInt(parts[2]);
                            }
                            int currentYear = java.time.LocalDate.now().getYear();
                            int age = currentYear - yearOfBirth;

                            // Độ tuổi lọc dạng "20-30", "31-40", "41-55"
                            String[] ageRange = dotuoiFilter.split("-");
                            int minAge = Integer.parseInt(ageRange[0]);
                            int maxAge = Integer.parseInt(ageRange[1]);

                            dotuoiMatch = age >= minAge && age <= maxAge;

                        } catch (Exception ex) {
                            dotuoiMatch = true; // Nếu parse lỗi thì không lọc theo tuổi
                        }
                    }

                    if (keywordMatch && phongbanMatch && gioitinhMatch && mucluongMatch && dotuoiMatch) {
                        // Thỏa tất cả điều kiện, thêm dòng
                        filteredModel.addRow(new Object[]{
                                originalModel.getValueAt(i, 0),
                                originalModel.getValueAt(i, 1),
                                originalModel.getValueAt(i, 2),
                                originalModel.getValueAt(i, 3),
                                originalModel.getValueAt(i, 4),
                                originalModel.getValueAt(i, 5),
                                originalModel.getValueAt(i, 6),
                                originalModel.getValueAt(i, 7),
                                originalModel.getValueAt(i, 8),
                        });
                    }
                }

                // Cập nhật lại bảng
                nhansuModel.setRowCount(0);
                for (int i = 0; i < filteredModel.getRowCount(); i++) {
                    nhansuModel.addRow(new Object[]{
                            filteredModel.getValueAt(i, 0),
                            filteredModel.getValueAt(i, 1),
                            filteredModel.getValueAt(i, 2),
                            filteredModel.getValueAt(i, 3),
                            filteredModel.getValueAt(i, 4),
                            filteredModel.getValueAt(i, 5),
                            filteredModel.getValueAt(i, 6),
                            filteredModel.getValueAt(i, 7),
                            filteredModel.getValueAt(i, 8),
                    });
                }
            }
        });
    }

    public DefaultTableModel getNhanSuModel() {
        return nhansuModel;
    }

}