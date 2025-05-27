import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

public class nhapEmail extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField emailField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            nhapEmail dialog = new nhapEmail(null);
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
    public nhapEmail(JFrame parent) {
        super(parent, "Nhập Email", true);
        setSize(427, 231);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        {
            JLabel NewLabel = new JLabel("NHẬP EMAIL CỦA BẠN");
            NewLabel.setForeground(new Color(0, 0, 128));
            NewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
            NewLabel.setBounds(110, 11, 231, 54);
            contentPanel.add(NewLabel);
        }

        emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        emailField.setBounds(10, 95, 397, 31);
        contentPanel.add(emailField);
        emailField.setColumns(10);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setBackground(new Color(220, 220, 220));
                okButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setBackground(new Color(220, 220, 220));
                cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                cancelButton.addActionListener(e -> {
                    dispose();
                });
            }
        }
    }
}
