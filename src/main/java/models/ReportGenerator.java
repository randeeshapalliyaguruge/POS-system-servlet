package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator extends DALModel {
    private Connection connection;

    public ReportGenerator() {
        this.connection = Database.getInstance().getConnection();
    }

    public List<Map<String, Object>> generateSalesReport(String date) throws SQLException {
        String sql = "SELECT b.id, bp.product_id, p.name, p.id, SUM(bp.product_quantity) as total_quantity, SUM(bp.total_cost) as total_revenue " +
                "FROM bills b " +
                "JOIN bill_products bp ON b.id = bp.bill_id " +
                "JOIN products p ON bp.product_id = p.id " +
                "WHERE b.created_date = ? " +
                "GROUP BY bp.product_id";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        return convertResultSetToList(rs);
    }

    public List<Map<String, Object>> generateReshelvingReport() throws SQLException {
        String sql = "SELECT p.id, p.name, s.quantity " +
                "FROM shelves s " +
                "JOIN products p ON s.product_id = p.id " +
                "WHERE s.quantity <= 5";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return convertResultSetToList(rs);
    }

    public List<Map<String, Object>> generateReorderReport() throws SQLException {
        String sql = "SELECT p.id, p.name, s.quantity " +
                "FROM stocks s " +
                "JOIN products p ON s.product_id = p.id " +
                "WHERE s.quantity < 50";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return convertResultSetToList(rs);
    }

    public List<Map<String, Object>> generateStockReport() throws SQLException {
        List<Map<String, Object>> stockReport = new ArrayList<>();
        String sql = "SELECT s.id, s.product_id, p.name as product_name, s.quantity, s.purchase_date, s.expire_date " +
                "FROM stocks s " +
                "JOIN products p ON s.product_id = p.id";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("product_id", rs.getInt("product_id"));
                row.put("product_name", rs.getString("product_name"));
                row.put("quantity", rs.getInt("quantity"));
                row.put("purchase_date", rs.getString("purchase_date"));
                row.put("expire_date", rs.getString("expire_date"));
                stockReport.add(row);
            }
        }

        return stockReport;
    }

    public List<Map<String, Object>> generateBillReport() throws SQLException {
        String sql = "SELECT * FROM bills";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        return convertResultSetToList(rs);
    }

    // Utility method to convert ResultSet to List<Map<String, Object>>
    public List<Map<String, Object>> convertResultSetToList(ResultSet rs) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
