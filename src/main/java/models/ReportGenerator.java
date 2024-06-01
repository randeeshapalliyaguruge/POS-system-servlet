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
        List<Map<String, Object>> results = new ArrayList<>();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            row.put("product_id", rs.getString("product_id"));
            row.put("name", rs.getString("name"));
            row.put("total_quantity", rs.getInt("total_quantity"));
            row.put("total_revenue", rs.getDouble("total_revenue"));
            results.add(row);
        }
        return results;
    }

    public void generateReshelvingReport() throws SQLException {
        String sql = "SELECT p.id, p.name, s.quantity " +
                "FROM shelves s " +
                "JOIN products p ON s.product_id = p.id " +
                "WHERE s.quantity <= 0";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("id") + ", " + rs.getString("name") + ", " + ", " + rs.getInt("quantity"));
        }
    }

    public ResultSet generateReorderReport() {
        String sql = "SELECT p.id, p.name, s.quantity " +
                "FROM stocks s " +
                "JOIN products p ON s.product_id = p.id " +
                "WHERE s.quantity < 50";

        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            return rs;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void generateStockReport() throws SQLException {
        String sql = "SELECT s.id, p.id as product_id, p.name, s.quantity, s.purchase_date, s.expire_date " +
                "FROM stocks s " +
                "JOIN products p ON s.product_id = p.id";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("id") + ", " +
                    rs.getString("product_id") + ", " +
                    rs.getString("name") + ", " +
                    rs.getInt("quantity") + ", " +
                    rs.getString("purchase_date") + ", " +
                    rs.getString("expire_date"));
        }
    }

    public void generateBillReport() throws SQLException {
        String sql = "SELECT * FROM bills";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("id") + ", " +
                    rs.getString("created_date") + ", " +
                    rs.getString("serial_number") + ", " +
                    rs.getDouble("discount") + ", " +
                    rs.getDouble("total_price") + ", " +
                    rs.getDouble("cash_tendered") + ", " +
                    rs.getDouble("change"));
        }
    }
}