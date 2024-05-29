package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stock extends DALModel {
    protected String table = "stocks";

    public Stock() {
        this.fillable = new String[]{"id", "product_id", "quantity", "purchase_date", "expire_date"};
    }

    //sql query to show the stock
    public void allStock() {
        String sql = "SELECT * FROM " + getTable();

        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getInt("product_id") + "\t" +
                        rs.getInt("quantity") + "\t" +
                        rs.getString("purchase_date")+ "\t" +
                        rs.getString("expire_date"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
}