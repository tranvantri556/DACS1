import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.Component;

public class LognIn extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JTextField emailField02;
    private JTextField passwordField02;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LognIn frame = new LognIn();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LognIn() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 786, 457);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel logoLabel = new JLabel("");
        logoLabel.setVerticalAlignment(SwingConstants.BOTTOM);
//        logoLabel.setIcon(new ImageIcon(LognIn.class.getResource("/logo.png")));
        logoLabel.setBounds(0, 0, 441, 420);
        contentPane.add(logoLabel);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 240));
        panel.setBounds(377, 0, 395, 420);
        contentPane.add(panel);
        panel.setLayout(new CardLayout(0, 0));

        JPanel LogIn = new JPanel();
        LogIn.setBackground(new Color(255, 255, 240));
        LogIn.setForeground(new Color(255, 255, 240));
        panel.add(LogIn, "login");
        LogIn.setLayout(null);

        JLabel logninLabel = new JLabel("LOGIN");
        logninLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logninLabel.setFont(new Font("Cooper Black", Font.PLAIN, 30));
        logninLabel.setBounds(105, 11, 158, 70);
        LogIn.add(logninLabel);

        JLabel emailLabel = new JLabel("Email-address:");
        emailLabel.setVerticalAlignment(SwingConstants.TOP);
        emailLabel.setFont(new Font("Constantia", Font.PLAIN, 20));
        emailLabel.setBounds(10, 148, 140, 18);
        LogIn.add(emailLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setVerticalAlignment(SwingConstants.TOP);
        passwordLabel.setFont(new Font("Constantia", Font.PLAIN, 20));
        passwordLabel.setBounds(10, 194, 140, 18);
        LogIn.add(passwordLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Constantia", Font.PLAIN, 16));
        emailField.setBounds(174, 148, 192, 27);
        LogIn.add(emailField);
        emailField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Constantia", Font.PLAIN, 16));
        passwordField.setBounds(174, 194, 192, 27);
        LogIn.add(passwordField);

        JCheckBox showPassword = new JCheckBox("Show Password");
        showPassword.setBackground(new Color(255, 255, 224));
        showPassword.setFont(new Font("Constantia", Font.PLAIN, 13));
        showPassword.setBounds(253, 234, 123, 18);
        LogIn.add(showPassword);

        JButton lognInButton = new JButton("OK");
        lognInButton.setVerticalAlignment(SwingConstants.TOP);
        lognInButton.setFont(new Font("Constantia", Font.PLAIN, 20));
        lognInButton.setBackground(new Color(255, 218, 185));
        lognInButton.setBounds(132, 253, 89, 27);
        LogIn.add(lognInButton);

        JLabel Text1 = new JLabel("If you don't have an account!");
        Text1.setFont(new Font("Constantia", Font.PLAIN, 15));
        Text1.setBounds(10, 359, 195, 14);
        LogIn.add(Text1);

        JLabel registerText = new JLabel("Register");
        registerText.setForeground(new Color(220, 20, 60));
        registerText.setFont(new Font("Constantia", Font.BOLD, 15));
        registerText.setBounds(203, 359, 89, 14);
        LogIn.add(registerText);

        JPanel Register = new JPanel();
        Register.setBackground(new Color(255, 255, 240));
        panel.add(Register, "register");
        Register.setLayout(null);

        JLabel registerText1 = new JLabel("REGISTER");
        registerText1.setFont(new Font("Cooper Black", Font.PLAIN, 30));
        registerText1.setBounds(109, 33, 189, 45);
        Register.add(registerText1);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Constantia", Font.PLAIN, 20));
        nameLabel.setBounds(10, 149, 104, 30);
        Register.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Constantia", Font.PLAIN, 16));
        nameField.setBounds(167, 150, 198, 30);
        Register.add(nameField);
        nameField.setColumns(10);

        JLabel emailLabel2 = new JLabel("Email:");
        emailLabel2.setFont(new Font("Constantia", Font.PLAIN, 20));
        emailLabel2.setBounds(10, 199, 110, 30);
        Register.add(emailLabel2);

        emailField02 = new JTextField();
        emailField02.setFont(new Font("Constantia", Font.PLAIN, 16));
        emailField02.setBounds(167, 200, 198, 30);
        Register.add(emailField02);
        emailField02.setColumns(10);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Constantia", Font.PLAIN, 20));
        passLabel.setBounds(10, 250, 104, 26);
        Register.add(passLabel);

        passwordField02 = new JTextField();
        passwordField02.setFont(new Font("Constantia", Font.PLAIN, 16));
        passwordField02.setBounds(167, 249, 198, 30);
        Register.add(passwordField02);
        passwordField02.setColumns(10);

        JButton btnNewButton = new JButton("OK");
        btnNewButton.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton.setBackground(new Color(255, 218, 185));
        btnNewButton.setFont(new Font("Constantia", Font.PLAIN, 20));
        btnNewButton.setBounds(133, 307, 89, 23);
        Register.add(btnNewButton);

        JLabel Text2 = new JLabel("If you have an account!");
        Text2.setFont(new Font("Constantia", Font.PLAIN, 15));
        Text2.setBounds(10, 355, 178, 23);
        Register.add(Text2);

        JLabel loginText = new JLabel("LogIn");
        loginText.setForeground(new Color(220, 20, 60));
        loginText.setFont(new Font("Constantia", Font.BOLD, 15));
        loginText.setBounds(167, 359, 49, 14);
        Register.add(loginText);

        registerText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) panel.getLayout();
                cl.show(panel, "register"); // tên constraints của Register panel
            }
        });

        loginText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) panel.getLayout();
                cl.show(panel, "login"); // tên constraints của LognIn panel
            }
        });
    }
}
