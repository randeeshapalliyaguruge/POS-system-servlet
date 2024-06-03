package managers;

import factories.Manager;
import models.Product;

import java.util.HashMap;
import java.util.Scanner;

public class ProductManager implements Manager {
    @Override
    public void manage() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a Product");
            System.out.println("2. Update a Product");
            System.out.println("3. View All Product");
            System.out.println("4. Delete a Product");
            System.out.println("-------------------");
            System.out.println("5. Go Back");
            System.out.println("-------------------");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            int choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    scanner = new Scanner(System.in);

                    System.out.println("Enter product name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter price:");
                    double price = scanner.nextDouble();

                    // Create a Product instance and call the create method
                    Product product = new Product();
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", name);
                    data.put("price", price);
                    HashMap<String, Object> productModel = product.create(data);

                    System.out.println("Product added successfully:");
                    System.out.println(productModel);

                    break;

                case 2:
                    // Display the list of products
                    Product product1 = new Product();
                    product1.all();

                    System.out.println("Enter the ID of the product to update:");
                    int id = scanner.nextInt();

                    // Get the product with the given ID
                    HashMap<String, Object> productToUpdate = product1.get(id);

                    // Check if the product exists
                    if (productToUpdate != null) {
                        // Display current values
                        System.out.println("Current product details:");
                        System.out.println("Name: " + productToUpdate.get("name"));
                        System.out.println("Price: " + productToUpdate.get("price"));

                        // Prompt the user for new details
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter new name (or press Enter to keep existing):");
                        String newName = scanner.nextLine();
                        if (newName.isEmpty()) {
                            newName = (String) productToUpdate.get("name");
                        }

                        System.out.println("Enter new price (or enter 0 to keep existing):");
                        double newPrice;

                        if (scanner.hasNextDouble()) {
                            newPrice = scanner.nextDouble();
                            if (newPrice == 0) {
                                newPrice = (int) productToUpdate.get("price");
                            }
                        } else {
                            System.out.println("Invalid input. Keeping existing price.");
                            newPrice = (int) productToUpdate.get("price");
                            scanner.nextLine();
                        }

                        // Update the product
                        HashMap<String, Object> dataToUpdate = new HashMap<>();
                        dataToUpdate.put("name", newName);
                        dataToUpdate.put("price", newPrice);
                        HashMap<String, Object> updatedProduct = product1.update(id, dataToUpdate);
                        System.out.println("Product updated successfully:");

                        System.out.println("Updated product details:");
                        System.out.println("Name: " + updatedProduct.get("name"));
                        System.out.println("Price: " + updatedProduct.get("price"));
                    } else {
                        System.out.println("Product with ID " + id + " does not exist.");
                    }
                    break;

                case 3:
                    Product product2 = new Product();
                    product2.all();
                    break;

                case 4:
                    // delete product by id, with details deleted
                    Product product3 = new Product();
                    product3.all();
                    System.out.println("Enter the ID of the product to delete:");
                    int idToDelete = scanner.nextInt();
                    product3.delete(idToDelete);
                    System.out.println("Product deleted successfully.");
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
