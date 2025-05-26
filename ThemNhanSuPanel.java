import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class ThemNhanSuPanel extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField manhanvienField;
    private JTextField hotenField;
    private JTextField dienthoaiField;
    private JTextField diachiField;
    private JTextField mucluongField;
    private DefaultTableModel nhansuModel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "Điện thoại", "Địa chỉ", "Phòng ban", "Chức vụ", "Mức lương"}, 0);
            ThemNhanSuPanel dialog = new ThemNhanSuPanel(null, model);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            dialog.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ThemNhanSuPanel(JFrame parent , DefaultTableModel nhansuModel) {
        super(parent, "Thêm nhân sự", true);
        this.nhansuModel = nhansuModel;
        setSize(1072,506);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("ComboBox.selectionBackground", new Color(0xDCEEFF));
        UIManager.put("ComboBox.selectionForeground", Color.BLACK);
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(new Color(180, 180, 180)));
        UIManager.put("ComboBox.buttonBackground", Color.WHITE);

        JLabel label1 = new JLabel("THÊM NHÂN SỰ");
        label1.setForeground(new Color(0, 0, 128));
        label1.setFont(new Font("Segoe UI", Font.BOLD, 25));
        label1.setBounds(399, 30, 207, 41);
        contentPanel.add(label1);

        JLabel label2 = new JLabel("Mã nhân viên:");
        label2.setFont(new Font("Constantia", Font.PLAIN, 15));
        label2.setBounds(35, 101, 137, 29);
        contentPanel.add(label2);

        JLabel lblHTn = new JLabel("Họ tên:");
        lblHTn.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblHTn.setBounds(35, 181, 137, 29);
        contentPanel.add(lblHTn);

        JLabel lblGiiTnh = new JLabel("Giới tính:");
        lblGiiTnh.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblGiiTnh.setBounds(35, 257, 137, 29);
        contentPanel.add(lblGiiTnh);

        JLabel lblNgySinh = new JLabel("Ngày sinh:");
        lblNgySinh.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblNgySinh.setBounds(35, 348, 137, 29);
        contentPanel.add(lblNgySinh);

        JLabel lblinThoi = new JLabel("Điện thoại:");
        lblinThoi.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblinThoi.setBounds(399, 101, 137, 29);
        contentPanel.add(lblinThoi);

        JLabel lblGiiTnh_1 = new JLabel("Địa chỉ:");
        lblGiiTnh_1.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblGiiTnh_1.setBounds(399, 187, 137, 29);
        contentPanel.add(lblGiiTnh_1);

        JLabel lblPhngBan = new JLabel("Phòng ban:");
        lblPhngBan.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblPhngBan.setBounds(399, 263, 137, 29);
        contentPanel.add(lblPhngBan);

        JLabel lblChcV = new JLabel("Chức vụ:");
        lblChcV.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblChcV.setBounds(399, 348, 137, 29);
        contentPanel.add(lblChcV);

        JLabel lblMcLng = new JLabel("Mức lương:");
        lblMcLng.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblMcLng.setBounds(727, 101, 137, 29);
        contentPanel.add(lblMcLng);

        manhanvienField = new JTextField();
        manhanvienField.setFont(new Font("Constantia", Font.PLAIN, 12));
        manhanvienField.setBounds(35, 128, 247, 29);
        contentPanel.add(manhanvienField);
        manhanvienField.setColumns(10);

        hotenField = new JTextField();
        hotenField.setFont(new Font("Constantia", Font.PLAIN, 12));
        hotenField.setColumns(10);
        hotenField.setBounds(35, 217, 247, 29);
        contentPanel.add(hotenField);

        dienthoaiField = new JTextField();
        dienthoaiField.setFont(new Font("Constantia", Font.PLAIN, 12));
        dienthoaiField.setColumns(10);
        dienthoaiField.setBounds(399, 130, 247, 29);
        contentPanel.add(dienthoaiField);

        diachiField = new JTextField();
        diachiField.setFont(new Font("Constantia", Font.PLAIN, 12));
        diachiField.setColumns(10);
        diachiField.setBounds(399, 219, 247, 29);
        contentPanel.add(diachiField);

        JRadioButton nam = new JRadioButton("Nam");
        nam.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nam.setBounds(75, 293, 68, 23);
        contentPanel.add(nam);

        JRadioButton nu = new JRadioButton("Nữ");
        nu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        nu.setBounds(168, 293, 68, 23);
        contentPanel.add(nu);

        ButtonGroup gioitinhNhanvien = new ButtonGroup();
        gioitinhNhanvien.add(nam);
        gioitinhNhanvien.add(nu);

        String[] phongban = {"Phòng y tế", "Phòng đào tạo", "Phòng tuyển dụng", "Phòng kế toán","Phòng hồ sơ và pháp lí", "Phòng hành chính-quản lí"};
        JComboBox phongbanComboBox = new JComboBox(phongban);
        phongbanComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        phongbanComboBox.setBounds(399, 293, 247, 29);
        contentPanel.add(phongbanComboBox);

        String[] chucvu = {"Trưởng ban","Phó ban", "Chuyên viên", "Bác sĩ", "Y tá", "Điều dưỡng"};
        JComboBox chucvuComboBox = new JComboBox(chucvu);
        chucvuComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        chucvuComboBox.setBounds(399, 378, 247, 29);
        contentPanel.add(chucvuComboBox);

        JDateChooser ngaysinh = new JDateChooser();
        ngaysinh.getCalendarButton().setForeground(new Color(224, 255, 255));
        ngaysinh.getCalendarButton().setFont(new Font("Constantia", Font.PLAIN, 11));
        ngaysinh.getCalendarButton().setBackground(new Color(240, 255, 255));
        ngaysinh.setForeground(Color.BLACK);
        ngaysinh.setDateFormatString("dd/MM/yyyy");
        ngaysinh.setBackground(UIManager.getColor("Button.background"));
        ngaysinh.setBounds(35, 378, 248, 29);
        contentPanel.add(ngaysinh);


        mucluongField = new JTextField();
        mucluongField.setFont(new Font("Constantia", Font.PLAIN, 12));
        mucluongField.setColumns(10);
        mucluongField.setBounds(727, 130, 247, 29);
        contentPanel.add(mucluongField);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton themButton = new JButton("Thêm");
                themButton.setBackground(new Color(220, 220, 220));
                themButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
                themButton.setActionCommand("Thêm");
                buttonPane.add(themButton);
                getRootPane().setDefaultButton(themButton);

                themButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String manv = manhanvienField.getText().trim();
                        String hoten = hotenField.getText().trim();
                        String gioitinh = nam.isSelected() ? "Nam"
                                : nu.isSelected() ? "Nữ" : "";
                        java.util.Date ngsinh = ngaysinh.getDate();
                        if (ngsinh == null) {
                            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
                            return;
                        }
                        java.sql.Date sqlNs = new java.sql.Date(ngsinh.getTime());
                        String dienthoai = dienthoaiField.getText().trim();
                        String diachi = diachiField.getText().trim();
                        String phongban = (String) phongbanComboBox.getSelectedItem();
                        String chucvu = (String) chucvuComboBox.getSelectedItem();
                        String mucluong = mucluongField.getText().trim();
                        if (manv.isEmpty() || hoten.isEmpty() || gioitinh.isEmpty() || dienthoai.isEmpty()
                            || diachi.isEmpty() || phongban.isEmpty() || chucvu.isEmpty() || mucluong.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
                        }
                        ConnectData con = new ConnectData();
                        boolean ok = con.themNhansu(manv, hoten, gioitinh, ngsinh, dienthoai, diachi, phongban, chucvu, mucluong);
                        if (ok){
                            JOptionPane.showMessageDialog(null, "Thêm nhân sự thành công!");
                            nhansuModel.addRow(new Object[]{manv, hoten, gioitinh, sqlNs, dienthoai, diachi, phongban, chucvu, mucluong});
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm nhân sự thất bại!");
                        }
                    }
                });
            }
            {
                JButton dongButton = new JButton("Đóng");
                dongButton.setBackground(new Color(220, 220, 220));
                dongButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
                dongButton.setActionCommand("Đóng");
                buttonPane.add(dongButton);
                dongButton.addActionListener(e -> {
                    dispose();
                });
            }
        }
    }
}