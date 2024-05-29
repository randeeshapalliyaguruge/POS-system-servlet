package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportGenerator extends DALModel {

    private Connection connection;

    public ReportGenerator() {
        this.connection = Database.getInstance().getConnection();
    }
    public void generateSalesReport(String date) throws SQLException {
        String sql = "SELECT b.id, bp.product_id, p.name, p.id, SUM(bp.product_quantity) as total_quantity, SUM(bp.total_cost) as total_revenue " +
                "FROM bills b " +
                "JOIN bill_products bp ON b.id = bp.bill_id " +
                "JOIN products p ON bp.product_id = p.id " +
                "WHERE b.created_date = ? " +
                "GROUP BY bp.product_id";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        stmt.setString(1, date);
        System.out.println("Executing SQL query: " + stmt.toString()); // print the SQL query
        ResultSet rs = stmt.executeQuery();
        boolean hasResults = false;
        while (rs.next()) {
            hasResults = true;
            System.out.println(rs.getString("product_id") + ", " + rs.getString("name") + ", " + ", " + rs.getInt("total_quantity") + ", " + rs.getDouble("total_revenue"));
        }
        if (!hasResults) {
            System.out.println("No sales data for the given date: " + date);
        }
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

    public void generateReorderReport() throws SQLException {
        String sql = "SELECT p.id, p.name, s.quantity " +
                "FROM stocks s " +
                "JOIN products p ON s.product_id = p.id " +
                "WHERE s.quantity < 50";
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("id") + ", " + rs.getString("name") + ", " + ", " + rs.getInt("quantity"));
        }
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