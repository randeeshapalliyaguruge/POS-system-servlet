package managers;

import factories.Manager;
import models.Bill;
import models.Product;

import java.util.HashMap;
import java.util.Scanner;

public class BillManager implements Manager {
    @Override
    public void manage() {
        Scanner scanner = new Scanner(System.in);
        Product product = new Product();

        System.out.println("Enter the number of item types:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int numberOfItems = scanner.nextInt();

        double totalBill = 0;

        System.out.println("------BILL------");

        for (int i = 0; i < numberOfItems; i++) {

            System.out.println("Enter the product ID for item " + (i + 1) + ":");
            int productId = scanner.nextInt();
            HashMap<String, Object> productData = product.get(productId);

            if (productData != null) {
                System.out.println("Enter the quantity for item " + (i + 1) + ":");
                int quantity = scanner.nextInt();

                Bill.generateBill(productData, quantity);
                double price = Double.parseDouble(productData.get("price").toString());
                totalBill += price * quantity;
            } else {
                System.out.println("Product with ID " + productId + " does not exist.");
            }
        }

        System.out.println("Enter the discount percentage:");
        double discountPercentage = scanner.nextDouble();
        double discountAmount = (totalBill * discountPercentage) / 100;
        totalBill -= discountAmount;

        System.out.println("Enter the amount tendered in cash:");
        double cashTendered = scanner.nextDouble();
        double change = cashTendered - totalBill;

        System.out.println("Change to be returned: " + change);

        System.out.println("---------------");
        System.out.println("Total Bill: " + totalBill);
        System.out.println("Discount: " + discountAmount);
        System.out.println("Cash Tendered: " + cashTendered);
        System.out.println("Change: " + change);
        System.out.println("---------------");

        // Save the bill with a unique serial number
        Bill bill = new Bill();
        HashMap<String, Object> billData = new HashMap<>();
        billData.put("total_price", totalBill);
        billData.put("cash_tendered", cashTendered);
        billData.put("change", change);
        billData.put("discount", discountAmount);
        billData.put("created_date", java.time.LocalDate.now().toString()); // current date
        bill.create(billData);
    }
}
