package models;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Bill extends DALModel {

    protected String table = "bills";

    public Bill() {
        this.fillable = new String[]{"created_date", "serial_number", "total_price", "cash_tendered", "discount", "change"};
    }

    public int getLastSerialNumber() {
        String sql = "SELECT MAX(serial_number) AS max_serial_number FROM " + getTable();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("max_serial_number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public HashMap<String, Object> create(HashMap<String, Object> billData) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = dtf.format(java.time.LocalDate.now());
        String tempSerialNumber = "TEMP" + currentDate; // temporary serial number

        String sql = "INSERT INTO " + getTable() + " (created_date, serial_number, total_price, discount, cash_tendered, change) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, (String) billData.get("created_date"));
            pstmt.setString(2, tempSerialNumber); // use temporary serial number
            pstmt.setDouble(3, (Double) billData.get("total_price"));
            pstmt.setDouble(4, (Double) billData.get("discount"));
            pstmt.setDouble(5, (Double) billData.get("cash_tendered"));
            pstmt.setDouble(6, (Double) billData.get("change"));
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int billId = generatedKeys.getInt(1);
                    String serialNumber = billId + currentDate;
                    billData.put("serial_number", serialNumber);

                    // Now update the bill with the generated serial number
                    String updateSql = "UPDATE " + getTable() + " SET serial_number = ? WHERE id = ?";
                    try (PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                        updatePstmt.setString(1, serialNumber);
                        updatePstmt.setInt(2, billId);
                        updatePstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return billData;
    }

    public void createBillProduct(HashMap<String, Object> billProductData) {
        String sql = "INSERT INTO bill_products (bill_id, product_id, product_quantity, total_cost) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, (Integer) billProductData.get("bill_id"));
            pstmt.setInt(2, (Integer) billProductData.get("product_id"));
            pstmt.setInt(3, (Integer) billProductData.get("product_quantity"));
            pstmt.setDouble(4, (Double) billProductData.get("total_cost"));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void generateBill(HashMap<String, Object> product, int quantity) {

        if (product != null) {
            int productId = (int) product.get("id");
            Shelve shelveModel = new Shelve();
            HashMap<String, Object> shelveData = shelveModel.findByProductId(productId);

            if (shelveData != null) {
                int availableQuantity = (int) shelveData.get("quantity");

                if (availableQuantity >= quantity) {
                    double price = ((Number) product.get("price")).doubleValue();
                    double totalCost = price * quantity;

                    // Print the bill
                    System.out.println("Product: " + product.get("name"));
                    System.out.println("Price per unit: " + price);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Total cost for this product: " + totalCost);
                    System.out.println("---------------");

                    // Update shelve quantity
                    shelveModel.updateShelfQuantity(productId, availableQuantity - quantity);

                    // Save product details to bill_products table
                    Bill billModel = new Bill();
                    HashMap<String, Object> billProductData = new HashMap<>();
                    billProductData.put("bill_id", billModel.getLastSerialNumber() + 1);
                    billProductData.put("product_id", productId);
                    billProductData.put("product_quantity", quantity);
                    billProductData.put("total_cost", totalCost);
                    billModel.createBillProduct(billProductData);
                } else {
                    System.out.println("Insufficient quantity on shelve for product: " + product.get("name"));
                    System.out.println("Available quantity: " + availableQuantity);
                }
            } else {
                System.out.println("Shelve data not found for product ID: " + productId);
            }
        } else {
            System.out.println("Product does not exist.");
        }
    }
}