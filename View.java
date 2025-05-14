import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;


public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JTextField IDField; // Mã cư dân
    private JTextField hotenField; // Họ và tên bệnh nhân (benhnhanPanel)
    private JTextField sodienthoaiField; //Sđt bệnh nhân (benhnhanPanel)
    private JTextField timkiemBenhNhanField; // Tìm kiếm (benhnhanPanel)
    private JTable benhnhanTable; //Bảng bệnh nhân
    private JTextField mabenhanField;
    private JTextField macudanField;
    private JTextField hotenBenhAnField;
    private JTextField chandoanField;
    private JTextField donthuocField;
    private JTextField lienlacField;
    private JTextField tennguoithanField;
    private JTextField timkiemBenhAnField;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    View frame = new View();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public View() {
        setTitle("ELDERCARE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1280, 695));
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 245, 245));
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(mainPanel);
        mainPanel.setLayout(null);

        JButton trangchuButton = new JButton("Trang chủ");
        trangchuButton.setHorizontalAlignment(SwingConstants.LEFT);
        trangchuButton.setIcon(new ImageIcon(View.class.getResource("/images/trangchu.png")));
        trangchuButton.setBackground(new Color(245, 245, 245));
        trangchuButton.setFont(new Font("Constantia", Font.BOLD, 19));
        trangchuButton.setBounds(35, 54, 170, 38);
        mainPanel.add(trangchuButton);


        JButton benhnhanButton = new JButton("Bệnh nhân");
        benhnhanButton.setHorizontalAlignment(SwingConstants.LEFT);
        benhnhanButton.setIcon(new ImageIcon(View.class.getResource("/images/benhnhan.png")));
        benhnhanButton.setBackground(new Color(245, 245, 245));
        benhnhanButton.setFont(new Font("Constantia", Font.BOLD, 19));
        benhnhanButton.setBounds(35, 125, 170, 38);
        mainPanel.add(benhnhanButton);

        JButton benhanButton = new JButton("Bệnh án");
        benhanButton.setIcon(new ImageIcon(View.class.getResource("/images/vienthuoc.png")));
        benhanButton.setHorizontalAlignment(SwingConstants.LEFT);
        benhanButton.setFont(new Font("Constantia", Font.BOLD, 19));
        benhanButton.setBackground(new Color(245, 245, 245));
        benhanButton.setBounds(35, 196, 170, 38);
        mainPanel.add(benhanButton);

        JButton hoadonButton = new JButton("Hóa đơn");
        hoadonButton.setIcon(new ImageIcon(View.class.getResource("/images/hoadon.png")));
        hoadonButton.setHorizontalAlignment(SwingConstants.LEFT);
        hoadonButton.setFont(new Font("Constantia", Font.BOLD, 19));
        hoadonButton.setBackground(new Color(245, 245, 245));
        hoadonButton.setBounds(35, 262, 170, 38);
        mainPanel.add(hoadonButton);

        JButton nhansuButton = new JButton("Nhân sự");
        nhansuButton.setHorizontalAlignment(SwingConstants.LEFT);
        nhansuButton.setIcon(new ImageIcon(View.class.getResource("/images/nhansu.png")));
        nhansuButton.setBackground(new Color(245, 245, 245));
        nhansuButton.setFont(new Font("Constantia", Font.BOLD, 19));
        nhansuButton.setBounds(35, 330, 170, 38);
        mainPanel.add(nhansuButton);

        JButton phancongButton = new JButton("Phân công");
        phancongButton.setIcon(new ImageIcon(View.class.getResource("/images/phancong.png")));
        phancongButton.setHorizontalAlignment(SwingConstants.LEFT);
        phancongButton.setForeground(Color.BLACK);
        phancongButton.setFont(new Font("Constantia", Font.BOLD, 19));
        phancongButton.setBackground(new Color(245, 245, 245));
        phancongButton.setBounds(35, 400, 170, 38);
        mainPanel.add(phancongButton);

        JButton baocaoButton = new JButton("Báo cáo");
        baocaoButton.setHorizontalAlignment(SwingConstants.LEFT);
        baocaoButton.setIcon(new ImageIcon(View.class.getResource("/images/baocao.png")));
        baocaoButton.setBackground(new Color(245, 245, 245));
        baocaoButton.setFont(new Font("Constantia", Font.BOLD, 19));
        baocaoButton.setBounds(35, 469, 170, 38);
        mainPanel.add(baocaoButton);

        JButton nhantinButton = new JButton("Nhắn tin");
        nhantinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        nhantinButton.setHorizontalAlignment(SwingConstants.LEFT);
        nhantinButton.setIcon(new ImageIcon(View.class.getResource("/images/nhantin.png")));
        nhantinButton.setBackground(new Color(245, 245, 245));
        nhantinButton.setFont(new Font("Constantia", Font.BOLD, 19));
        nhantinButton.setBounds(35, 532, 170, 38);
        mainPanel.add(nhantinButton);

        JLabel logoutLabel = new JLabel("Đăng xuất"); //Nút nhấn đăng xuất
//        logoutLabel.setIcon(new ImageIcon(View.class.getResource("/images/logout.png")));
        logoutLabel.setFont(new Font("Constantia", Font.PLAIN, 17));
        logoutLabel.setBounds(35, 615, 110, 32);
        mainPanel.add(logoutLabel);


        JPanel Panel = new JPanel(); //Panel chính dùng CardLayout
        Panel.setBackground(new Color(245, 245, 245));
        Panel.setBounds(215, 11, 1041, 636);
        mainPanel.add(Panel);
        Panel.setLayout(new CardLayout(0, 0));


        TrangChuPanel trangchuPanel = new TrangChuPanel();
        Panel.add(trangchuPanel, "trangchuPanel");

        // Sự kiện click nút Trang chủ
        trangchuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "trangchuPanel");
            }
        });


        BenhNhanPanel benhnhanPanel = new BenhNhanPanel();
        Panel.add(benhnhanPanel, "benhnhanPanel");

        //Nhấn nút Bệnh Nhân về panel Bệnh nhân

        benhnhanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "benhnhanPanel");
            }
        });


        JPanel benhanPanel = new JPanel();
        benhanPanel.setBackground(new Color(245, 245, 245));
        Panel.add(benhanPanel, "benhanPanel");
        benhanPanel.setLayout(null);

        //Nhấn nút Bệnh án về panel Bệnh án

        benhanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "benhanPanel");
            }
        });


        DefaultTableModel benhanModel = new DefaultTableModel(new String[] {"Mã bệnh án","Mã cư dân", "Họ tên", "Phụ trách", "Ngày khám", "Tổng quan", "Chẩn đoán", "Đơn thuốc", "Ghi chú"}, 0);

        JTable benhanTable = new JTable(benhanModel);
        benhanTable.setToolTipText("");
        benhanTable.setBorder(new LineBorder(new Color(64, 64, 64), 2, true));
        benhanTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
        benhanTable.setForeground(new Color(128, 0, 0));
        benhanTable.setBackground(new Color(227, 227, 227));

        JScrollPane benhanScrollPane = new JScrollPane(benhanTable);
        benhanScrollPane.setViewportBorder(null);
        benhanScrollPane.setToolTipText("");
        benhanScrollPane.setBounds(10, 204, 838, 375);
        benhanPanel.add(benhanScrollPane);

        JLabel NewLabel = new JLabel("Mã bệnh án:");
        NewLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        NewLabel.setBounds(29, 33, 90, 29);
        benhanPanel.add(NewLabel);

        JLabel lblMCDn = new JLabel("Mã cư dân:");
        lblMCDn.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblMCDn.setBounds(29, 89, 90, 29);
        benhanPanel.add(lblMCDn);

        JLabel lblHTn = new JLabel("Họ tên:");
        lblHTn.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblHTn.setBounds(29, 148, 90, 29);
        benhanPanel.add(lblHTn);

        JLabel lblPhTrch = new JLabel("Phụ trách:");
        lblPhTrch.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblPhTrch.setBounds(296, 39, 90, 29);
        benhanPanel.add(lblPhTrch);

        JLabel lblNgyKhm = new JLabel("Ngày khám:");
        lblNgyKhm.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblNgyKhm.setBounds(296, 95, 90, 29);
        benhanPanel.add(lblNgyKhm);

        JLabel lblTngQuan = new JLabel("Tổng quan:");
        lblTngQuan.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblTngQuan.setBounds(296, 148, 90, 29);
        benhanPanel.add(lblTngQuan);

        JLabel lblChnon = new JLabel("Chẩn đoán");
        lblChnon.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblChnon.setBounds(591, 39, 90, 29);
        benhanPanel.add(lblChnon);

        JLabel lblnThuc = new JLabel("Đơn thuốc:");
        lblnThuc.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblnThuc.setBounds(591, 95, 90, 29);
        benhanPanel.add(lblnThuc);

        JLabel lblGhiCh = new JLabel("Ghi chú:");
        lblGhiCh.setFont(new Font("Constantia", Font.PLAIN, 15));
        lblGhiCh.setBounds(591, 148, 90, 29);
        benhanPanel.add(lblGhiCh);

        mabenhanField = new JTextField();
        mabenhanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        mabenhanField.setBounds(129, 36, 108, 26);
        benhanPanel.add(mabenhanField);
        mabenhanField.setColumns(10);

        macudanField = new JTextField();
        macudanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        macudanField.setColumns(10);
        macudanField.setBounds(129, 92, 108, 26);
        benhanPanel.add(macudanField);

        hotenBenhAnField = new JTextField();
        hotenBenhAnField.setFont(new Font("Constantia", Font.PLAIN, 15));
        hotenBenhAnField.setColumns(10);
        hotenBenhAnField.setBounds(129, 151, 108, 26);
        benhanPanel.add(hotenBenhAnField);

        chandoanField = new JTextField();
        chandoanField.setFont(new Font("Constantia", Font.PLAIN, 15));
        chandoanField.setColumns(10);
        chandoanField.setBounds(691, 36, 108, 26);
        benhanPanel.add(chandoanField);

        donthuocField = new JTextField();
        donthuocField.setFont(new Font("Constantia", Font.PLAIN, 15));
        donthuocField.setColumns(10);
        donthuocField.setBounds(691, 92, 108, 26);
        benhanPanel.add(donthuocField);

        JTextArea ghichuField = new JTextArea();
        ghichuField.setFont(new Font("Monospaced", Font.PLAIN, 15));
        ghichuField.setBounds(691, 149, 108, 44);
        benhanPanel.add(ghichuField);

        JComboBox phutrachComboBox = new JComboBox();
        phutrachComboBox.setFont(new Font("Constantia", Font.PLAIN, 15));
        phutrachComboBox.setBounds(396, 41, 138, 26);
        benhanPanel.add(phutrachComboBox);

        JComboBox tongquanComboBox = new JComboBox();
        tongquanComboBox.setFont(new Font("Constantia", Font.PLAIN, 15));
        tongquanComboBox.setBounds(396, 150, 138, 26);
        benhanPanel.add(tongquanComboBox);

        JPanel chucnangBenhAnPanel = new JPanel();
        chucnangBenhAnPanel.setLayout(null);
        chucnangBenhAnPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1EE9c n\u0103ng", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        chucnangBenhAnPanel.setBackground(new Color(245, 245, 245));
        chucnangBenhAnPanel.setBounds(872, 255, 159, 210);
        benhanPanel.add(chucnangBenhAnPanel);

        JLabel them_1 = new JLabel("Thêm");
        them_1.setIcon(new ImageIcon(View.class.getResource("/images/them.png")));
        them_1.setFont(new Font("Constantia", Font.PLAIN, 15));
        them_1.setBounds(10, 22, 94, 36);
        chucnangBenhAnPanel.add(them_1);

        JLabel sua_1 = new JLabel("Sửa");
        sua_1.setIcon(new ImageIcon(View.class.getResource("/images/sua.png")));
        sua_1.setFont(new Font("Constantia", Font.PLAIN, 15));
        sua_1.setBounds(10, 74, 94, 36);
        chucnangBenhAnPanel.add(sua_1);

        JLabel xuatExcel_1 = new JLabel("Xuất Excel");
        xuatExcel_1.setIcon(new ImageIcon(View.class.getResource("/images/excel.png")));
        xuatExcel_1.setFont(new Font("Constantia", Font.PLAIN, 15));
        xuatExcel_1.setBounds(10, 168, 124, 36);
        chucnangBenhAnPanel.add(xuatExcel_1);

        JLabel xoa_1_1 = new JLabel("Xóa");
        xoa_1_1.setIcon(new ImageIcon(View.class.getResource("/images/xoa.png")));
        xoa_1_1.setFont(new Font("Constantia", Font.PLAIN, 15));
        xoa_1_1.setBounds(10, 121, 94, 36);
        chucnangBenhAnPanel.add(xoa_1_1);

        JPanel timkiemPanel_1 = new JPanel();
        timkiemPanel_1.setLayout(null);
        timkiemPanel_1.setBorder(new TitledBorder(null, "T\u00ECm ki\u1EBFm", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(105, 105, 105)));
        timkiemPanel_1.setBackground(new Color(245, 245, 245));
        timkiemPanel_1.setBounds(872, 476, 159, 103);
        benhanPanel.add(timkiemPanel_1);

        timkiemBenhAnField = new JTextField();
        timkiemBenhAnField.setFont(new Font("Constantia", Font.PLAIN, 15));
        timkiemBenhAnField.setColumns(10);
        timkiemBenhAnField.setBackground(Color.WHITE);
        timkiemBenhAnField.setBounds(22, 28, 131, 20);
        timkiemPanel_1.add(timkiemBenhAnField);

        JLabel timkiemButton_1 = new JLabel("");
        timkiemButton_1.setIcon(new ImageIcon(View.class.getResource("/images/timkiem.png")));
        timkiemButton_1.setBounds(129, 59, 24, 33);
        timkiemPanel_1.add(timkiemButton_1);

        JDateChooser ngaykham = new JDateChooser();
        ngaykham.getCalendarButton().setForeground(new Color(224, 255, 255));
        ngaykham.getCalendarButton().setFont(new Font("Constantia", Font.PLAIN, 11));
        ngaykham.getCalendarButton().setBackground(new Color(240, 255, 255));
        ngaykham.setForeground(Color.BLACK);
        ngaykham.setDateFormatString("dd/MM/yyyy");
        ngaykham.setBackground(UIManager.getColor("Button.background"));
        ngaykham.setBounds(396, 98, 138, 20);
        benhanPanel.add(ngaykham);

    }
}