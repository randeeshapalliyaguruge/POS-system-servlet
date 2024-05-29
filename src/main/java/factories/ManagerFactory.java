package factories;

import managers.*;

public class ManagerFactory {
    public static Manager getManager(String managerType) {
        switch (managerType) {
            case "Product":
                return new ProductManager();
            case "Stock":
                return new StockManager();
            case "Bill":
                return new BillManager();
            case "Shelve":
                return new ShelveManager();
            case "Report":
                return new ReportManager();
            default:
                throw new IllegalArgumentException("Unknown manager type: " + managerType);
        }
    }
}
