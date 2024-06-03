package factories;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ReportManagerInterface extends Manager {
    List<Map<String, Object>> generateSalesReport(String date) throws SQLException;
    List<Map<String, Object>> generateReshelvingReport() throws SQLException;
    List<Map<String, Object>> generateReorderReport() throws SQLException;
    List<Map<String, Object>> generateStockReport() throws SQLException;
    List<Map<String, Object>> generateBillReport() throws SQLException;
}
