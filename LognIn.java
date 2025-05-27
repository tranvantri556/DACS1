import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
//import org.eclipse.wb.swing.*;
import java.awt.event.*;


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
        setBounds(100, 100, 786, 476);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(82, 120, 171));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 240));
        panel.setBounds(377, 0, 395, 439);
        contentPane.add(panel);
        panel.setLayout(new CardLayout(0, 0));

        JPanel LogIn = new JPanel();
        LogIn.setBackground(new Color(245, 245, 245));
        LogIn.setForeground(new Color(255, 255, 240));
        panel.add(LogIn, "login");
        LogIn.setLayout(null);

        JLabel logninLabel = new JLabel("LOGIN");
        logninLabel.setForeground(new Color(0, 0, 128));
        logninLabel.setBackground(new Color(128, 0, 0));
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
        showPassword.setBackground(new Color(245, 245, 245));
        showPassword.setFont(new Font("Constantia", Font.PLAIN, 13));
        showPassword.setBounds(253, 234, 123, 18);
        LogIn.add(showPassword);
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);  // Hiện mật khẩu
                } else {
                    passwordField.setEchoChar('•');       // Ẩn mật khẩu
                }
            }
        });

        JButton lognInButton = new JButton("OK");
        lognInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lognInButton.setBackground(Color.white);
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                ConnectData con = new ConnectData();
                if (con.checkLogin(email, password)) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    View v = new View();
                    v.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password.");
                }

            }
        });
        lognInButton.setVerticalAlignment(SwingConstants.TOP);
        lognInButton.setFont(new Font("Constantia", Font.PLAIN, 20));
        lognInButton.setBackground(new Color(220, 220, 220));
        lognInButton.setBounds(132, 253, 89, 27);
        LogIn.add(lognInButton);

        JLabel Text1 = new JLabel("If you don't have an account!");
        Text1.setFont(new Font("Constantia", Font.PLAIN, 15));
        Text1.setBounds(10, 335, 195, 14);
        LogIn.add(Text1);

        JLabel registerText = new JLabel("Register");
        registerText.setForeground(new Color(220, 20, 60));
        registerText.setFont(new Font("Constantia", Font.BOLD, 15));
        registerText.setBounds(203, 335, 89, 14);
        LogIn.add(registerText);

        JLabel quenmatkhauLabel = new JLabel("Forgot your paswword");
        quenmatkhauLabel.setForeground(new Color(0, 0, 128));
        quenmatkhauLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        quenmatkhauLabel.setBounds(10, 310, 158, 14);
        LogIn.add(quenmatkhauLabel);

        quenmatkhauLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                nhapEmail dialog = new nhapEmail((JFrame) SwingUtilities.getWindowAncestor(quenmatkhauLabel));
                dialog.setVisible(true);
            }
        });

        JPanel Register = new JPanel();
        Register.setBackground(new Color(245, 245, 245));
        panel.add(Register, "register");
        Register.setLayout(null);

        JLabel registerText1 = new JLabel("REGISTER");
        registerText1.setForeground(new Color(0, 0, 128));
        registerText1.setBackground(new Color(128, 0, 0));
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
        btnNewButton.setToolTipText("");
        btnNewButton.setVerticalAlignment(SwingConstants.TOP);
        btnNewButton.setBackground(new Color(211, 211, 211));
        btnNewButton.setFont(new Font("Constantia", Font.PLAIN, 20));
        btnNewButton.setBounds(133, 307, 89, 30);
        Register.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField02.getText().trim();
                String password = passwordField02.getText().trim();

                ConnectData con = new ConnectData();
                if (name.equals("") || email.equals("") || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                } else {
                    boolean success = con.registerUser(name, email, password);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Registration successful!");
                        // Chuyển về giao diện Login
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed!");
                    }
                }

            }
        });

        JLabel Text2 = new JLabel("If you have an account!");
        Text2.setFont(new Font("Constantia", Font.PLAIN, 15));
        Text2.setBounds(10, 355, 178, 23);
        Register.add(Text2);

        JLabel loginText = new JLabel("LogIn");
        loginText.setForeground(new Color(220, 20, 60));
        loginText.setFont(new Font("Constantia", Font.BOLD, 15));
        loginText.setBounds(167, 359, 49, 14);
        Register.add(loginText);
//        panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{LogIn, logninLabel, emailLabel, passwordLabel, emailField, passwordField, showPassword, lognInButton, Text1, registerText, Register, registerText1, nameLabel, nameField, emailLabel2, emailField02, passLabel, passwordField02, btnNewButton, Text2, loginText, quenmatkhauLabel}));

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBackground(new Color(102, 153, 204));
        lblNewLabel.setIcon(new ImageIcon(LognIn.class.getResource("/images/logo.png")));
        lblNewLabel.setBounds(33, 34, 302, 375);
        contentPane.add(lblNewLabel);

        registerText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) panel.getLayout();
                cl.show(panel, "register");
            }
        });

        loginText.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                CardLayout cl = (CardLayout) panel.getLayout();
                cl.show(panel, "login");
            }
        });
    }
}
