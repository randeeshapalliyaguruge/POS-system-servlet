package managers;

import factories.Manager;
import models.Product;
import models.Stock;

import java.util.HashMap;
import java.util.Scanner;

public class StockManager implements Manager {
    @Override
    public void manage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Stock");
            System.out.println("2. Update Stock");
            System.out.println("3. Delete Stock");
            System.out.println("-------------------");
            System.out.println("4. Go Back");
            System.out.println("-------------------");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    Product product = new Product();
                    product.all();
                    System.out.println("Enter the product ID:");
                    int productId = scanner.nextInt();
                    HashMap<String, Object> productData = product.get(productId);
                    if (productData != null) {
                        System.out.println("Enter the quantity:");
                        int quantity = scanner.nextInt();
                        System.out.println("Enter the purchase date (YYYY-MM-DD):");
                        String purchase_date = scanner.next();

                        System.out.println("Enter the expire date (YYYY-MM-DD):");
                        String expire_date = scanner.next();

                        Stock stock = new Stock();
                        HashMap<String, Object> stockData = new HashMap<>();
                        stockData.put("product_id", productId);
                        stockData.put("quantity", quantity);
                        stockData.put("purchase_date", purchase_date);
                        stockData.put("expire_date", expire_date);
                        HashMap<String, Object> stockModel = stock.create(stockData);

                        System.out.println("Stock added successfully:");
                        System.out.println(stockModel);
                    } else {
                        System.out.println("Product with ID " + productId + " does not exist.");
                    }

                    break;
                case 2:
                    // update stock by id
                    Stock stock1 = new Stock();
                    stock1.allStock();
                    System.out.println("Enter the stock ID to update:");
                    int id = scanner.nextInt();

                    HashMap<String, Object> stockToUpdate = stock1.get(id);
                    if (stockToUpdate != null) {
                        System.out.println("Current stock details:");
                        System.out.println("Product ID: " + stockToUpdate.get("product_id"));
                        System.out.println("Quantity: " + stockToUpdate.get("quantity"));
                        System.out.println("Purchase Date: " + stockToUpdate.get("purchase_date"));
                        System.out.println("Expire Date: " + stockToUpdate.get("expire_date"));

                        System.out.println("Enter new quantity (or enter 0 to keep existing):");
                        int newQuantity;
                        if (scanner.hasNextInt()) {
                            newQuantity = scanner.nextInt();
                            if (newQuantity == 0) {
                                newQuantity = (int) stockToUpdate.get("quantity");
                            }
                        } else {
                            System.out.println("Invalid input. Keeping existing quantity.");
                            newQuantity = (int) stockToUpdate.get("quantity");
                            scanner.nextLine();
                        }

                        scanner.nextLine();
                        System.out.println("Enter new purchase date (YYYY-MM-DD) (or press Enter to keep existing):");
                        String newPurchaseDate = scanner.nextLine();
                        if (newPurchaseDate.isEmpty()) {
                            newPurchaseDate = (String) stockToUpdate.get("purchase_date");
                        }

                        scanner.nextLine();
                        System.out.println("Enter new expire date (YYYY-MM-DD) (or press Enter to keep existing):");
                        String newExpireDate = scanner.nextLine();
                        if (newExpireDate.isEmpty()) {
                            newExpireDate = (String) stockToUpdate.get("expire_date");
                        }

                        HashMap<String, Object> dataToUpdate = new HashMap<>();
                        dataToUpdate.put("quantity", newQuantity);
                        dataToUpdate.put("purchase_date", newPurchaseDate);
                        dataToUpdate.put("expire_date", newExpireDate);
                        HashMap<String, Object> updatedStock = stock1.update(id, dataToUpdate);
                        System.out.println("Stock updated successfully:");

                        System.out.println("Updated stock details:");
                        System.out.println("Product ID: " + updatedStock.get("product_id"));
                        System.out.println("Quantity: " + updatedStock.get("quantity"));
                        System.out.println("Purchase Date: " + updatedStock.get("purchase_date"));
                        System.out.println("Expire Date: " + updatedStock.get("expire_date"));
                    } else {
                        System.out.println("Stock with ID " + id + " does not exist.");
                    }
                    break;
                case 3:// delete stock by id, show the stock first
                    Stock stock2 = new Stock();
                    stock2.allStock();
                    System.out.println("Enter the ID of the stock to delete:");
                    int idToDelete = scanner.nextInt();
                    stock2.delete(idToDelete);
                    System.out.println("Stock deleted successfully.");

                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
