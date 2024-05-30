package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Shelve extends DALModel {

    protected String table = "shelves";

    public Shelve() {
        this.fillable = new String[]{"id", "product_id", "stock_id", "quantity"};
    }

    //sql query to show the shelves
    public ResultSet allShelves() {
        String sql = "SELECT * FROM " + getTable();

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

    // Method to retrieve shelf data by product ID
    public HashMap<String, Object> findByProductId(int productId) {
        String sql = "SELECT * FROM " + this.table + " WHERE product_id = " + productId;
        try {
            Connection conn = Database.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                HashMap<String, Object> result = new HashMap<>();
                for (String column : fillable) {
                    result.put(column, rs.getObject(column));
                }
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to update shelf quantity by product ID
    public void updateShelfQuantity(int productId, int newQuantity) {
        HashMap<String, Object> shelfData = findByProductId(productId);
        if (shelfData != null) {
            Integer shelfId = (Integer) shelfData.get("id"); // Retrieve the shelf ID
            if (shelfId != null) {
                shelfData.put("quantity", newQuantity);
                this.update(shelfId, shelfData);
            } else {
                System.out.println("ID not found in shelf data: " + shelfData);
            }
        } else {
            System.out.println("Shelf data not found for product ID: " + productId);
        }
    }
}