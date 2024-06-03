package managers;

import factories.ReportManagerInterface;
import models.ReportGenerator;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ReportManager implements ReportManagerInterface {
    @Override
    public List<Map<String, Object>> generateSalesReport(String date) throws SQLException {
        ReportGenerator reportGenerator = new ReportGenerator();
        return reportGenerator.generateSalesReport(date);
    }

    @Override
    public List<Map<String, Object>> generateReshelvingReport() throws SQLException {
        ReportGenerator reportGenerator = new ReportGenerator();
        return reportGenerator.generateReshelvingReport();
    }

    @Override
    public List<Map<String, Object>> generateReorderReport() throws SQLException {
        ReportGenerator reportGenerator = new ReportGenerator();
        return reportGenerator.generateReorderReport();
    }

    @Override
    public List<Map<String, Object>> generateStockReport() throws SQLException {
        ReportGenerator reportGenerator = new ReportGenerator();
        return reportGenerator.generateStockReport();
    }

    @Override
    public List<Map<String, Object>> generateBillReport() throws SQLException {
        ReportGenerator reportGenerator = new ReportGenerator();
        return reportGenerator.generateBillReport();
    }

    @Override
    public void manage() {

    }
}
