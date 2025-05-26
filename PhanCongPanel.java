import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PhanCongPanel extends JPanel {
    public JTextField maphancongField, ghichuField;

    public PhanCongPanel(){
        setBackground(new Color(245, 245, 245));
        setLayout(null);

        DefaultTableModel phancongModel = new DefaultTableModel(new String[] {"Mã phân công","Tên bệnh nhân", "Tên nhân viên", "Ngày", "Phòng", "Ca", "Ghi chú"}, 0);
        JTable phancongTable = new JTable(phancongModel);
        phancongTable.setFont(new Font("Constantia", Font.PLAIN, 10));
        JScrollPane phancongScrollPane = new JScrollPane(phancongTable);
        phancongScrollPane.setBounds(10, 66, 1021, 281);
        phancongScrollPane.setFont(new Font("Constantia", Font.PLAIN, 10));
        add(phancongScrollPane);

        JPanel ChitietPhancongPanel = new JPanel();
        ChitietPhancongPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Chi tiết lịch trực", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
        ChitietPhancongPanel.setBounds(10, 384, 877, 241);
        add(ChitietPhancongPanel);
        ChitietPhancongPanel.setLayout(null);

        JDateChooser ngaytruc = new JDateChooser();
        ngaytruc.getCalendarButton().setForeground(new Color(224, 255, 255));
        ngaytruc.getCalendarButton().setFont(new Font("Constantia", Font.PLAIN, 11));
        ngaytruc.getCalendarButton().setBackground(new Color(240, 255, 255));
        ngaytruc.setForeground(Color.BLACK);
        ngaytruc.setDateFormatString("dd/MM/yyyy");
        ngaytruc.setBackground(UIManager.getColor("Button.background"));
        ngaytruc.setBounds(460, 21, 199, 20);
        ChitietPhancongPanel.add(ngaytruc);

        String[] ca = {"Sáng","Chiều","Tối"};
        JComboBox caComboBox = new JComboBox(ca);
        caComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        caComboBox.setBounds(299, 21, 151, 20);
        caComboBox.setSelectedItem(null); // không chọn gì cả
        caComboBox.setEditable(true);
        caComboBox.getEditor().setItem("Ca");
        Component gioitinhEditor = caComboBox.getEditor().getEditorComponent();
        if (gioitinhEditor instanceof JTextField textField) {
            textField.setEditable(false);                // Không cho gõ
            textField.setFocusable(false);               // Không tab vào
            textField.setForeground(Color.BLACK);       // Nền trắng
            textField.setBorder(null);                   // Bỏ viền trong
            textField.setDisabledTextColor(Color.GRAY);  // Phòng trường hợp bị disable
        }
        ChitietPhancongPanel.add(caComboBox);

        JLabel lammoiLabel = new JLabel("");
        lammoiLabel.setIcon(new ImageIcon(View.class.getResource("/images/load.png")));
        lammoiLabel.setBounds(669, 11, 39, 32);
        ChitietPhancongPanel.add(lammoiLabel);

        JLabel label3 = new JLabel("Mã phân công:");
        label3.setFont(new Font("Constantia", Font.PLAIN, 15));
        label3.setBounds(51, 90, 121, 20);
        ChitietPhancongPanel.add(label3);

        JLabel label2 = new JLabel("Tên bệnh nhân:");
        label2.setFont(new Font("Constantia", Font.PLAIN, 15));
        label2.setBounds(51, 142, 121, 20);
        ChitietPhancongPanel.add(label2);

        JLabel label5 = new JLabel("Tên nhân viên:");
        label5.setFont(new Font("Constantia", Font.PLAIN, 15));
        label5.setBounds(51, 198, 121, 20);
        ChitietPhancongPanel.add(label5);

        JLabel label4 = new JLabel("Phòng:");
        label4.setFont(new Font("Constantia", Font.PLAIN, 15));
        label4.setBounds(470, 89, 121, 20);
        ChitietPhancongPanel.add(label4);

        JLabel label1 = new JLabel("Ghi chú:");
        label1.setFont(new Font("Constantia", Font.PLAIN, 15));
        label1.setBounds(470, 143, 121, 20);
        ChitietPhancongPanel.add(label1);

        maphancongField = new JTextField();
        maphancongField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        maphancongField.setBounds(182, 89, 207, 26);
        ChitietPhancongPanel.add(maphancongField);
        maphancongField.setColumns(10);

        JComboBox tenBenhnhanComboBox = new JComboBox();
        tenBenhnhanComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tenBenhnhanComboBox.setBounds(182, 140, 207, 22);
        ChitietPhancongPanel.add(tenBenhnhanComboBox);

        JComboBox tenNhanvienComboBox = new JComboBox();
        tenNhanvienComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tenNhanvienComboBox.setBounds(182, 196, 207, 22);
        ChitietPhancongPanel.add(tenNhanvienComboBox);

        String[] phong = {"a1", "a2", "a3", "a4", "a5",
                "b1", "b2", "b3", "b4", "b5",
                "c1", "c2", "c3", "c4", "c5",
                "d1", "d2", "d3", "d4", "d5"};
        JComboBox phongComboBox = new JComboBox(phong);
        phongComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        phongComboBox.setBounds(565, 89, 206, 26);
        ChitietPhancongPanel.add(phongComboBox);

        ghichuField = new JTextField();
        ghichuField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        ghichuField.setColumns(10);
        ghichuField.setBounds(565, 140, 206, 90);
        ChitietPhancongPanel.add(ghichuField);

        JLabel lblNewLabel = new JLabel("BẢNG PHÂN CÔNG NHÂN VIÊN");
        lblNewLabel.setForeground(new Color(0, 0, 128));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblNewLabel.setBounds(343, 11, 391, 41);
        add(lblNewLabel);

        JPanel ChucnangPanel = new JPanel();
        ChucnangPanel.setBorder(new TitledBorder(null, "Ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(105, 105, 105)));
        ChucnangPanel.setBounds(897, 384, 134, 241);
        add(ChucnangPanel);
        ChucnangPanel.setLayout(null);

        JLabel themLabel = new JLabel("Thêm");
        themLabel.setIcon(new ImageIcon(View.class.getResource("/images/add.png")));
        themLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        themLabel.setBounds(10, 46, 117, 38);
        ChucnangPanel.add(themLabel);

        JLabel capnhatLabel = new JLabel("Cập nhật");
        capnhatLabel.setIcon(new ImageIcon(View.class.getResource("/images/sua2.png")));
        capnhatLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        capnhatLabel.setBounds(10, 119, 117, 38);
        ChucnangPanel.add(capnhatLabel);

        JLabel xoaLabel = new JLabel("Xóa");
//        xoaLabel.setIcon(new ImageIcon(View.class.getResource("/images/delete2.png")));
        xoaLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        xoaLabel.setBounds(10, 192, 117, 38);
        ChucnangPanel.add(xoaLabel);


    }

}