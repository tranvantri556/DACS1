import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class BaoCaoPanel extends JPanel {
    private JLabel tongBenhNhanLabel;
    private JLabel benhNhanMoiLabel;
    private JLabel hoaDonLabel;
    private JLabel tongTienLabel;
    private ConnectData con;

    public BaoCaoPanel() {
        setLayout(new BorderLayout());
        con = new ConnectData();

        JPanel thongKePanel = new JPanel();
        thongKePanel.setLayout(new GridLayout(4, 1, 10, 10));
        tongBenhNhanLabel = new JLabel();
        benhNhanMoiLabel = new JLabel();
        hoaDonLabel = new JLabel();
        tongTienLabel = new JLabel();

        thongKePanel.add(tongBenhNhanLabel);
        thongKePanel.add(benhNhanMoiLabel);
        thongKePanel.add(hoaDonLabel);
        thongKePanel.add(tongTienLabel);

        JPanel bieuDoPanel = new JPanel();
        bieuDoPanel.setLayout(new GridLayout(1, 2, 10, 10));

        try {
            // Biểu đồ giới tính
            Map<String, Integer> gioiTinhMap = con.getThongKeBenhNhanTheoGioiTinh();
            bieuDoPanel.add(createPieChart(gioiTinhMap, "Giới tính bệnh nhân"));

            // Biểu đồ bộ phận nhân viên
            Map<String, Integer> boPhanMap = con.getThongKeNhanVienTheoBoPhan();
            bieuDoPanel.add(createPieChart(boPhanMap, "Nhân viên theo bộ phận"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(thongKePanel, BorderLayout.NORTH);
        add(bieuDoPanel, BorderLayout.CENTER);
    }

    private JPanel createPieChart(Map<String, Integer> data, String title) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }
        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);
        return new ChartPanel(chart);
    }
}

