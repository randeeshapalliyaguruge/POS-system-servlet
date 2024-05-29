package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Product extends DALModel {

    protected String table = "products";

    public Product() {
        this.fillable = new String[]{"name", "price"};
    }

    public void all() {
        // SQL query to select all products
        String sql = "SELECT * FROM " + getTable();

        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}