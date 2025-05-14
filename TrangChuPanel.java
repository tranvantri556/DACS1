import javax.swing.*;
import java.awt.*;

public class TrangChuPanel extends JPanel {
    public TrangChuPanel() {
        setBackground(new Color(245, 245, 245));
        setLayout(null);

        JLabel homeLabel1 = new JLabel("");
        homeLabel1.setBounds(761, 11, 256, 256);
//        homeLabel1.setIcon(new ImageIcon(View.class.getResource("/images/logoElderCare.png")));
        add(homeLabel1);

        JLabel homeLabel2 = new JLabel("HỆ THỐNG QUẢN LÍ VIỆN DƯỠNG LÃO");
        homeLabel2.setForeground(new Color(0, 0, 128));
        homeLabel2.setFont(new Font("Segoe UI", Font.BOLD, 25));
        homeLabel2.setBounds(216, 11, 599, 43);
        add(homeLabel2);

        JLabel homeLabel3 = new JLabel("Sống trọn từng khoảnh khắc – khỏe mạnh, yêu đời, đủ đầy");
        homeLabel3.setForeground(new Color(0, 0, 128));
        homeLabel3.setFont(new Font("Segoe UI", Font.BOLD, 15));
        homeLabel3.setBounds(584, 431, 447, 25);
        add(homeLabel3);

        JLabel homeLabel4 = new JLabel("<html><b>ElderCare</b> là mái ấm dành cho người cao tuổi, nơi chúng tôi mang đến sự chăm sóc tận tình, môi trường thân thiện và cuộc sống ý nghĩa cho người thân yêu của bạn.<br><br>Ứng dụng quản lý viện dưỡng lão giúp theo dõi thông tin cư dân, sức khỏe, lịch khám – tất cả trong một giao diện dễ dùng, hiện đại và nhân văn.</html>");
        homeLabel4.setForeground(new Color(0, 0, 128));
        homeLabel4.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        homeLabel4.setVerticalAlignment(SwingConstants.TOP);
        homeLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        homeLabel4.setBounds(30, 147, 719, 222);
        add(homeLabel4);

        JLabel tacgia = new JLabel("Tống Mỹ Trà - Trần Văn Tri");
        tacgia.setFont(new Font("Courier New", Font.PLAIN, 15));
        tacgia.setBounds(349, 600, 243, 25);
        add(tacgia);
    }
}
