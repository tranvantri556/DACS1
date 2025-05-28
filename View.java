import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class View extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel mainPanel;
    private JTextField timkiemField;
    private JTextField maphancongField;
    private JTextField ghichuField;


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

        JLabel ngaygioLabel = new JLabel("Time:");
        ngaygioLabel.setFont(new Font("Constantia", Font.PLAIN, 10));
        ngaygioLabel.setBounds(10, 11, 135, 14);
        mainPanel.add(ngaygioLabel);

        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("ComboBox.selectionBackground", new Color(0xDCEEFF));
        UIManager.put("ComboBox.selectionForeground", Color.BLACK);
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(new Color(180, 180, 180)));
        UIManager.put("ComboBox.buttonBackground", Color.WHITE);


        JButton trangchuButton = new JButton("Trang chủ");
        trangchuButton.setHorizontalAlignment(SwingConstants.LEFT);
        trangchuButton.setIcon(new ImageIcon(View.class.getResource("/images/trangchu.png")));
        trangchuButton.setBackground(new Color(245, 245, 245));
        trangchuButton.setFont(new Font("Constantia", Font.BOLD, 19));
        trangchuButton.setBounds(35, 54, 170, 38);
        trangchuButton.setBorder(new RoundedBorder(15));
        mainPanel.add(trangchuButton);


        JButton benhnhanButton = new JButton("Bệnh nhân");
        benhnhanButton.setHorizontalAlignment(SwingConstants.LEFT);
        benhnhanButton.setIcon(new ImageIcon(View.class.getResource("/images/benhnhan.png")));
        benhnhanButton.setBackground(new Color(245, 245, 245));
        benhnhanButton.setFont(new Font("Constantia", Font.BOLD, 19));
        benhnhanButton.setBounds(35, 125, 170, 38);
        benhnhanButton.setBorder(new RoundedBorder(15));
        mainPanel.add(benhnhanButton);

        JButton benhanButton = new JButton("Bệnh án");
        benhanButton.setIcon(new ImageIcon(View.class.getResource("/images/vienthuoc.png")));
        benhanButton.setHorizontalAlignment(SwingConstants.LEFT);
        benhanButton.setFont(new Font("Constantia", Font.BOLD, 19));
        benhanButton.setBackground(new Color(245, 245, 245));
        benhanButton.setBounds(35, 196, 170, 38);
        benhanButton.setBorder(new RoundedBorder(15));
        mainPanel.add(benhanButton);

        JButton hoadonButton = new JButton("Hóa đơn");
        hoadonButton.setIcon(new ImageIcon(View.class.getResource("/images/hoadon.png")));
        hoadonButton.setHorizontalAlignment(SwingConstants.LEFT);
        hoadonButton.setFont(new Font("Constantia", Font.BOLD, 19));
        hoadonButton.setBackground(new Color(245, 245, 245));
        hoadonButton.setBounds(35, 262, 170, 38);
        hoadonButton.setBorder(new RoundedBorder(15));
        mainPanel.add(hoadonButton);

        JButton nhansuButton = new JButton("Nhân sự");
        nhansuButton.setHorizontalAlignment(SwingConstants.LEFT);
        nhansuButton.setIcon(new ImageIcon(View.class.getResource("/images/nhansu.png")));
        nhansuButton.setBackground(new Color(245, 245, 245));
        nhansuButton.setFont(new Font("Constantia", Font.BOLD, 19));
        nhansuButton.setBounds(35, 330, 170, 38);
        nhansuButton.setBorder(new RoundedBorder(15));
        mainPanel.add(nhansuButton);

        JButton phancongButton = new JButton("Phân công");
        phancongButton.setIcon(new ImageIcon(View.class.getResource("/images/phancong.png")));
        phancongButton.setHorizontalAlignment(SwingConstants.LEFT);
        phancongButton.setForeground(Color.BLACK);
        phancongButton.setFont(new Font("Constantia", Font.BOLD, 19));
        phancongButton.setBackground(new Color(245, 245, 245));
        phancongButton.setBounds(35, 400, 170, 38);
        phancongButton.setBorder(new RoundedBorder(15));
        mainPanel.add(phancongButton);

        JButton baocaoButton = new JButton("Báo cáo");
        baocaoButton.setHorizontalAlignment(SwingConstants.LEFT);
        baocaoButton.setIcon(new ImageIcon(View.class.getResource("/images/baocao.png")));
        baocaoButton.setBackground(new Color(245, 245, 245));
        baocaoButton.setFont(new Font("Constantia", Font.BOLD, 19));
        baocaoButton.setBounds(35, 469, 170, 38);
        baocaoButton.setBorder(new RoundedBorder(15));
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
        nhantinButton.setBorder(new RoundedBorder(15));
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
        benhnhanPanel.tennguoithanField.setBounds(432, 117, 123, 24);
        benhnhanPanel.nguoithanButton.setBounds(558, 118, 93, 24);
        benhnhanPanel.getBenhNhanTable().setFont(new Font("Constantia", Font.PLAIN, 10));
        Panel.add(benhnhanPanel, "benhnhanPanel");


        //Nhấn nút Bệnh Nhân về panel Bệnh nhân

        benhnhanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "benhnhanPanel");
            }
        });


        BenhAnPanel benhanPanel = new BenhAnPanel();
        Panel.add(benhanPanel,"benhanPanel");

        //Nhấn nút Bệnh án về panel Bệnh án

        benhanButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "benhanPanel");
            }
        });

        HoaDonPanel hoadonPanel = new HoaDonPanel();
        Panel.add(hoadonPanel, "hoadonPanel");

        hoadonButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "hoadonPanel");
            }
        });

        NhanSuPanel nhansuPanel = new NhanSuPanel();
        Panel.add(nhansuPanel, "nhansuPanel");

        nhansuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "nhansuPanel");
            }
        });


        PhanCongPanel phancongPanel = new PhanCongPanel();
        phancongPanel.setBackground(new Color(245, 245, 245));
        Panel.add(phancongPanel, "phancongPanel");

        phancongButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "phancongPanel");
            }
        });


        BaoCaoPanel baocaoPanel = new BaoCaoPanel();
        Panel.add(baocaoPanel, "baocaoPanel");

        baocaoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cl = (CardLayout) Panel.getLayout();
                cl.show(Panel, "baocaoPanel");
            }
        });

    }

    static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(radius + 1, radius + 1, radius + 1, radius + 1);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.GRAY);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}