import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ChitietBenhAn extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel chitietPanel = new JPanel();
    private JTextField hotenField;
    private JTextField benhnenField;
    private JTextField lichsuField;
    private JTextField cannangField;
    private JTextField chieucaoField;
    private JTextField huyetapField;
    private JRadioButton nam, nu;
    private JLabel tuoiBenhnhan;
    private Map<String, ChitietBenhAn> dsChiTiet = new HashMap<>();
    private String maBenhNhan;
    private String trieuChungTamLy;
    private String lichSuBenh;
    private String benhNen;
    private String canNang;
    private String chieuCao;
    private String huyetAp;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            ChitietBenhAn dialog = new ChitietBenhAn(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public ChitietBenhAn(JFrame parent) {
        super(parent, "Chi tiết Bệnh án", true); // Gọi constructor cha để set title và modal
        setSize(828, 351);
        setLocationRelativeTo(parent);
        getContentPane().setLayout(new BorderLayout());
        chitietPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(chitietPanel, BorderLayout.CENTER);
        chitietPanel.setLayout(null);

        JLabel hotenLabel = new JLabel("Họ tên:");
        hotenLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        hotenLabel.setBounds(179, 69, 84, 21);
        chitietPanel.add(hotenLabel);

        JPanel anhBenhanPanel = new JPanel();
        anhBenhanPanel.setBackground(new Color(220, 220, 220));
        anhBenhanPanel.setBounds(22, 69, 133, 188);
        chitietPanel.add(anhBenhanPanel);

        JLabel tieudeLabel = new JLabel("CHI TIẾT BỆNH ÁN");
        tieudeLabel.setBackground(new Color(240, 240, 240));
        tieudeLabel.setForeground(new Color(25, 25, 112));
        tieudeLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        tieudeLabel.setBounds(344, 11, 253, 39);
        chitietPanel.add(tieudeLabel);

        JLabel benhnenLabel = new JLabel("Lịch sử bệnh nền:");
        benhnenLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        benhnenLabel.setBounds(179, 204, 133, 21);
        chitietPanel.add(benhnenLabel);

        JLabel tuoiLabel = new JLabel("Tuổi:");
        tuoiLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        tuoiLabel.setBounds(179, 133, 84, 21);
        chitietPanel.add(tuoiLabel);

        JLabel gioitinhLabel = new JLabel("Giới tính:");
        gioitinhLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        gioitinhLabel.setBounds(179, 101, 84, 21);
        chitietPanel.add(gioitinhLabel);

        JLabel lichkhamLabel = new JLabel("Lịch khám gần nhất:");
        lichkhamLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        lichkhamLabel.setBounds(179, 236, 156, 21);
        chitietPanel.add(lichkhamLabel);

        JLabel trieuchungLabel = new JLabel("Triệu chứng tâm lí:");
        trieuchungLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        trieuchungLabel.setBounds(179, 165, 133, 21);
        chitietPanel.add(trieuchungLabel);

        hotenField = new JTextField();
        hotenField.setFont(new Font("Constantia", Font.PLAIN, 10));
        hotenField.setBounds(352, 68, 185, 21);
        chitietPanel.add(hotenField);
        hotenField.setColumns(10);

        benhnenField = new JTextField();
        benhnenField.setFont(new Font("Constantia", Font.PLAIN, 10));
        benhnenField.setColumns(10);
        benhnenField.setBounds(352, 203, 185, 21);
        chitietPanel.add(benhnenField);

        lichsuField = new JTextField();
        lichsuField.setFont(new Font("Constantia", Font.PLAIN, 10));
        lichsuField.setColumns(10);
        lichsuField.setBounds(352, 235, 185, 21);
        chitietPanel.add(lichsuField);

        String[] trieuChung = {"Lo âu","Trầm cảm","Mất ngủ","Rối loạn trí nhớ","Không có"};
        JComboBox trieuchungTamlicomboBox = new JComboBox(trieuChung);
        trieuchungTamlicomboBox.setBackground(new Color(245, 245, 245));
        trieuchungTamlicomboBox.setFont(new Font("Constantia", Font.PLAIN, 12));
        trieuchungTamlicomboBox.setBounds(352, 163, 185, 23);
        chitietPanel.add(trieuchungTamlicomboBox);

        nam = new JRadioButton("Nam");
        nam.setVerticalAlignment(SwingConstants.TOP);
        nam.setFont(new Font("Constantia", Font.PLAIN, 12));
        nam.setBounds(352, 99, 70, 23);
        chitietPanel.add(nam);

        nu = new JRadioButton("Nữ");
        nu.setVerticalAlignment(SwingConstants.TOP);
        nu.setFont(new Font("Constantia", Font.PLAIN, 12));
        nu.setBounds(435, 99, 70, 23);
        chitietPanel.add(nu);

        ButtonGroup gioitinhBenhan = new ButtonGroup();
        gioitinhBenhan.add(nam);
        gioitinhBenhan.add(nu);

        tuoiBenhnhan = new JLabel("...");
        tuoiBenhnhan.setBounds(352, 135, 49, 14);
        chitietPanel.add(tuoiBenhnhan);

        JLabel cannangLabel = new JLabel("Cân nặng:");
        cannangLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        cannangLabel.setBounds(580, 69, 84, 21);
        chitietPanel.add(cannangLabel);

        JLabel chieucaoLabel = new JLabel("Chiều cao:");
        chieucaoLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        chieucaoLabel.setBounds(580, 101, 84, 21);
        chitietPanel.add(chieucaoLabel);

        cannangField = new JTextField();
        cannangField.setFont(new Font("Constantia", Font.PLAIN, 10));
        cannangField.setColumns(10);
        cannangField.setBounds(694, 70, 96, 21);
        chitietPanel.add(cannangField);

        chieucaoField = new JTextField();
        chieucaoField.setFont(new Font("Constantia", Font.PLAIN, 10));
        chieucaoField.setColumns(10);
        chieucaoField.setBounds(694, 100, 96, 21);
        chitietPanel.add(chieucaoField);

        JLabel huyetapLabel = new JLabel("Huyết áp:");
        huyetapLabel.setFont(new Font("Constantia", Font.PLAIN, 15));
        huyetapLabel.setBounds(580, 135, 84, 21);
        chitietPanel.add(huyetapLabel);

        huyetapField = new JTextField();
        huyetapField.setFont(new Font("Constantia", Font.PLAIN, 10));
        huyetapField.setColumns(10);
        huyetapField.setBounds(694, 132, 96, 21);
        chitietPanel.add(huyetapField);

        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);

            JButton suaBenhanButton = new JButton("Sửa");
            suaBenhanButton.setBackground(new Color(220, 220, 220));
            suaBenhanButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
            buttonPane.add(suaBenhanButton);

            suaBenhanButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lấy dữ liệu từ các trường nhập liệu
                    String trieuchung = (String) trieuchungTamlicomboBox.getSelectedItem();
                    String lichsu = lichsuField.getText().trim();
                    String benhnen = benhnenField.getText().trim();
                    String cannang = cannangField.getText().trim();
                    String chieucao = chieucaoField.getText().trim();
                    String huyetap = huyetapField.getText().trim();

                    // Kiểm tra dữ liệu bắt buộc không được để trống
                    if (trieuchung == null || trieuchung.isEmpty() ||
                            lichsu.isEmpty() || benhnen.isEmpty() ||
                            cannang.isEmpty() || chieucao.isEmpty() || huyetap.isEmpty()) {
                        JOptionPane.showMessageDialog(ChitietBenhAn.this,
                                "Vui lòng điền đầy đủ thông tin!",
                                "Lỗi nhập liệu",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Cập nhật dữ liệu cho đối tượng hiện tại
                    setTrieuChungTamLy(trieuchung);
                    setLichSuBenh(lichsu);
                    setBenhNen(benhnen);
                    setCanNang(cannang);
                    setChieuCao(chieucao);
                    setHuyetAp(huyetap);

                    // Cập nhật Map với key mã bệnh nhân
                    dsChiTiet.put(maBenhNhan, ChitietBenhAn.this);

                    // Thông báo thành công
                    JOptionPane.showMessageDialog(ChitietBenhAn.this,
                            "Đã cập nhật chi tiết bệnh nhân!",
                            "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            {
                JButton cancelButton = new JButton("Đóng");
                cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
                cancelButton.setBackground(new Color(220, 220, 220));
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);

                cancelButton.addActionListener(e -> {
                    dispose();
                });
            }
        }
    }
    // Sửa đúng biến hotenField
    public void setHoTen(String hoTen) {
        hotenField.setText(hoTen);
    }

    // Nếu muốn set giới tính, cần tick chọn radio button
    public void setGioiTinh(String gioiTinh) {
        if ("Nam".equalsIgnoreCase(gioiTinh)) {
            nam.setSelected(true);
        } else if ("Nữ".equalsIgnoreCase(gioiTinh)) {
            nu.setSelected(true);
        }
    }

    // Sửa đúng JLabel tuổi
    public void setTuoi(String tuoi) {
        tuoiBenhnhan.setText(tuoi);
    }

    public String getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(String maBenhNhan) { this.maBenhNhan = maBenhNhan; }
    public String getTrieuChungTamLy() { return trieuChungTamLy; }
    public void setTrieuChungTamLy(String trieuChungTamLy) { this.trieuChungTamLy = trieuChungTamLy; }
    public String getLichSuBenh() { return lichSuBenh; }
    public void setLichSuBenh(String lichSuBenh) { this.lichSuBenh = lichSuBenh; }
    public String getBenhNen() { return benhNen; }
    public void setBenhNen(String benhNen) { this.benhNen = benhNen; }
    public String getCanNang() { return canNang; }
    public void setCanNang(String canNang) { this.canNang = canNang; }
    public String getChieuCao() { return chieuCao; }
    public void setChieuCao(String chieuCao) { this.chieuCao = chieuCao; }
    public String getHuyetAp() { return huyetAp; }
    public void setHuyetAp(String huyetAp) { this.huyetAp = huyetAp; }
}
