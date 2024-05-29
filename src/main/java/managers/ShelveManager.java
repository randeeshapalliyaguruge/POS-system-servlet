package managers;

import factories.Manager;
import models.Product;
import models.Shelve;
import models.Stock;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ShelveManager implements Manager {
    @Override
    public void manage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Shelve");
            System.out.println("2. Update Shelve");
            System.out.println("3. Delete Shelve");
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
                    System.out.println("Enter the product ID:");
                    int productId = scanner.nextInt();
                    HashMap<String, Object> productData = product.get(productId);
                    if (productData != null) {
                        System.out.println("Enter the quantity:");
                        int quantity = scanner.nextInt();

                        Stock stock = new Stock();
                        List<HashMap<String, Object>> stocks = stock.getAllByProductId(productId);
                        stocks.sort(Comparator.comparing((HashMap<String, Object> o) -> (String) o.get("expire_date"))
                                .thenComparing(o -> (String) o.get("purchase_date")));

                        for (HashMap<String, Object> stockData : stocks) {
                            int stockQuantity = (int) stockData.get("quantity");
                            if (stockQuantity >= quantity) {
                                stockData.put("quantity", stockQuantity - quantity);
                                stock.update((int) stockData.get("id"), stockData);

                                Shelve shelve = new Shelve();
                                HashMap<String, Object> shelveData = new HashMap<>();
                                shelveData.put("stock_id", stockData.get("id"));
                                shelveData.put("product_id", productData.get("id"));
                                shelveData.put("quantity", quantity);
                                HashMap<String, Object> shelveModel = shelve.create(shelveData);

                                System.out.println("Shelve added successfully:");
                                System.out.println(shelveModel);
                                break;
                            } else {
                                stockData.put("quantity", 0);
                                stock.update((int) stockData.get("id"), stockData);

                                if (stockQuantity > 0) {
                                    Shelve shelve = new Shelve();
                                    HashMap<String, Object> shelveData = new HashMap<>();
                                    shelveData.put("stock_id", stockData.get("id"));
                                    shelveData.put("product_id", productData.get("id"));
                                    shelveData.put("quantity", stockQuantity);
                                    HashMap<String, Object> shelveModel = shelve.create(shelveData);

                                    System.out.println("Shelve added successfully:");
                                    System.out.println(shelveModel);
                                }
                                quantity -= stockQuantity;
                            }
                        }
                    } else {
                        System.out.println("Product with ID " + productId + " does not exist.");
                    }
                    break;
                case 2:
                    Shelve shelve1 = new Shelve();
                    Stock stock1 = new Stock();
                    shelve1.allShelves();
                    System.out.println("Enter the shelve ID to update:");
                    int id = scanner.nextInt();

                    HashMap<String, Object> shelveToUpdate = shelve1.get(id);
                    if (shelveToUpdate != null) {
                        int oldQuantity = (int) shelveToUpdate.get("quantity");
                        int stockIdToUpdate = (int) shelveToUpdate.get("stock_id");
                        HashMap<String, Object> stockDataToUpdate = stock1.get(stockIdToUpdate);

                        System.out.println("Current shelve details:");
                        System.out.println("Stock ID: " + shelveToUpdate.get("stock_id"));
                        System.out.println("Quantity: " + shelveToUpdate.get("quantity"));

                        System.out.println("Enter new quantity (or enter 0 to keep existing):");
                        int newQuantity;
                        if (scanner.hasNextInt()) {
                            newQuantity = scanner.nextInt();
                            if (newQuantity == 0) {
                                newQuantity = (int) shelveToUpdate.get("quantity");
                            }
                        } else {
                            System.out.println("Invalid input. Keeping existing quantity.");
                            newQuantity = (int) shelveToUpdate.get("quantity");
                            scanner.nextLine();
                        }

                        int stockQuantityToUpdate = (int) stockDataToUpdate.get("quantity");
                        stockDataToUpdate.put("quantity", stockQuantityToUpdate + oldQuantity - newQuantity);
                        stock1.update(stockIdToUpdate, stockDataToUpdate);

                        HashMap<String, Object> dataToUpdate = new HashMap<>();
                        dataToUpdate.put("quantity", newQuantity);
                        HashMap<String, Object> updatedShelve = shelve1.update(id, dataToUpdate);
                        System.out.println("Shelve updated successfully:");

                        System.out.println("Updated shelve details:");
                        System.out.println("Stock ID: " + updatedShelve.get("stock_id"));
                        System.out.println("Quantity: " + updatedShelve.get("quantity"));
                    } else {
                        System.out.println("Shelve with ID " + id + " does not exist.");
                    }
                    break;
                case 3:
                    Shelve shelve2 = new Shelve();
                    Stock stock2 = new Stock();
                    shelve2.allShelves();
                    System.out.println("Enter the ID of the shelve to delete:");
                    int idToDelete = scanner.nextInt();

                    HashMap<String, Object> shelveToDelete = shelve2.get(idToDelete);
                    if (shelveToDelete != null) {
                        int quantityToDelete = (int) shelveToDelete.get("quantity");
                        int stockIdToDelete = (int) shelveToDelete.get("stock_id");
                        HashMap<String, Object> stockDataToDelete = stock2.get(stockIdToDelete);

                        int stockQuantityToDelete = (int) stockDataToDelete.get("quantity");
                        stockDataToDelete.put("quantity", stockQuantityToDelete + quantityToDelete);
                        stock2.update(stockIdToDelete, stockDataToDelete);

                        shelve2.delete(idToDelete);
                        System.out.println("Shelve deleted successfully.");
                    } else {
                        System.out.println("Shelve with ID " + idToDelete + " does not exist.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public HashMap<String, Object> createShelve(HashMap<String, Object> data) {
        Shelve shelve = new Shelve();
        Stock stock = new Stock();
        Product product = new Product();
        HashMap<String, Object> productData = product.get((int) data.get("product_id"));
        if (productData != null) {
            int productId = (int) data.get("product_id");
            int quantity = (int) data.get("quantity");

            List<HashMap<String, Object>> stocks = stock.getAllByProductId(productId);
            stocks.sort(Comparator.comparing((HashMap<String, Object> o) -> (String) o.get("expire_date"))
                    .thenComparing(o -> (String) o.get("purchase_date")));

            for (HashMap<String, Object> stockData : stocks) {
                int stockQuantity = (int) stockData.get("quantity");
                if (stockQuantity >= quantity) {
                    stockData.put("quantity", stockQuantity - quantity);
                    stock.update((int) stockData.get("id"), stockData);

                    data.put("stock_id", stockData.get("id"));
                    HashMap<String, Object> shelveModel = shelve.create(data);
                    return shelveModel;
                } else {
                    stockData.put("quantity", 0);
                    stock.update((int) stockData.get("id"), stockData);

                    if (stockQuantity > 0) {
                        data.put("stock_id", stockData.get("id"));
                        data.put("quantity", stockQuantity);
                        HashMap<String, Object> shelveModel = shelve.create(data);
                        quantity -= stockQuantity;
                    }
                }
            }
        }
        return null;
    }

    public HashMap<String, Object> updateShelve(int i, HashMap<String, Object> data) {
        Shelve shelve = new Shelve();
        Stock stock = new Stock();
        HashMap<String, Object> shelveToUpdate = shelve.get(i);
        if (shelveToUpdate != null) {
            int oldQuantity = (int) shelveToUpdate.get("quantity");
            int stockIdToUpdate = (int) shelveToUpdate.get("stock_id");
            HashMap<String, Object> stockDataToUpdate = stock.get(stockIdToUpdate);

            int newQuantity = (int) data.get("quantity");
            int stockQuantityToUpdate = (int) stockDataToUpdate.get("quantity");
            stockDataToUpdate.put("quantity", stockQuantityToUpdate + oldQuantity - newQuantity);
            stock.update(stockIdToUpdate, stockDataToUpdate);

            data.put("quantity", newQuantity);
            return shelve.update(i, data);
        }
        return null;
    }
}
