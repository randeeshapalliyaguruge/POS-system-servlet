package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stock extends DALModel {
    protected String table = "stocks";

    public Stock() {
        this.fillable = new String[]{"id", "product_id", "quantity", "purchase_date", "expire_date"};
    }

    //sql query to show the stock
    public ResultSet allStock() {
        String sql = "SELECT * FROM " + getTable();
        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<HashMap<String, Object>> getAllByProductId(int productId) {
        List<HashMap<String, Object>> stocks = new ArrayList<>();
        String sql = "SELECT * FROM " + getTable() + " WHERE product_id = " + productId + " ORDER BY expire_date ASC";
        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                HashMap<String, Object> result = new HashMap<>();
                for (String column : fillable) {
                    result.put(column, rs.getObject(column));
                }
                stocks.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    // Convert ResultSet to List<HashMap<String, Object>>
    public List<HashMap<String, Object>> convertResultSetToList(ResultSet rs) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>();
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

    // Retrieve product name by product ID
    public String getProductName(int productId) {
        String sql = "SELECT name FROM products WHERE id = " + productId;
        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
