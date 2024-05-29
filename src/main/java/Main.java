import facade.StoreManagementFacade;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StoreManagementFacade facade = new StoreManagementFacade();
        while (true) {
            System.out.println("\n");
            System.out.println("   **************************************");
            System.out.println("   *                                    *");
            System.out.println("*     Welcome to Synex Outlet Store        *");
            System.out.println("   *                                    *");
            System.out.println("   **************************************");
            System.out.println("\nChoose an option:");
            System.out.println("   1. Manage Products");
            System.out.println("   2. Manage Stocks");
            System.out.println("   3. Create a Bill");
            System.out.println("   4. Manage Shelves");
            System.out.println("   5. Generate Reports");
            System.out.println("   -------------------");
            System.out.println("   6. Exit");
            System.out.println("   -------------------");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    facade.manageProducts();
                    break;
                case 2:
                    facade.manageStocks();
                    break;
                case 3:
                    facade.manageBills();
                    break;
                case 4:
                    facade.manageShelves();
                    break;
                case 5:
                    facade.generateReports();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
