package managers;

import factories.Manager;
import models.ReportGenerator;

import java.sql.SQLException;
import java.util.Scanner;

public class ReportManager implements Manager {
    @Override
    public void manage() {
        Scanner scanner = new Scanner(System.in);
        ReportGenerator reportGenerator = new ReportGenerator();
        while (true) {
            System.out.println("\nChoose a report to generate:");
            System.out.println("1. Total sale for a given day");
            System.out.println("2. Products to be reshelved");
            System.out.println("3. Reorder levels of stock");
            System.out.println("4. Stock report");
            System.out.println("5. Bill report");
            System.out.println("-------------------");
            System.out.println("6. Go Back");
            System.out.println("-------------------");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }

            int choice = scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        System.out.println("Enter the date (YYYY-MM-DD):");
                        String date = scanner.next();
                        reportGenerator.generateSalesReport(date);
                        break;
                    case 2:
                        reportGenerator.generateReshelvingReport();
                        break;
                    case 3:
                        reportGenerator.generateReorderReport();
                        break;
                    case 4:
                        reportGenerator.generateStockReport();
                        break;
                    case 5:
                        reportGenerator.generateBillReport();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (SQLException e) {
                System.out.println("An error occurred while generating the report: " + e.getMessage());
            }
        }
    }
}
