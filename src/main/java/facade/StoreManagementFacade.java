package facade;

import command.*;
import factories.ManagerFactory;

import java.sql.ResultSet;
import java.util.HashMap;

public class StoreManagementFacade {
    private Command manageProductsCommand;
    private Command manageStocksCommand;
    private Command manageBillsCommand;
    private Command manageShelvesCommand;
    private Command generateReportsCommand;

    public StoreManagementFacade() {
        this.manageProductsCommand = new ProductManagerCommand(ManagerFactory.getManager("Product"));
        this.manageStocksCommand = new StockManagerCommand(ManagerFactory.getManager("Stock"));
        this.manageBillsCommand = new BillManagerCommand(ManagerFactory.getManager("Bill"));
        this.manageShelvesCommand = new ShelveManagerCommand(ManagerFactory.getManager("Shelve"));
        this.generateReportsCommand = new ReportManagerCommand(ManagerFactory.getManager("Report"));
    }

    public void manageProducts() {
        manageProductsCommand.execute();
    }

    public void manageStocks() {
        manageStocksCommand.execute();
    }

    public void manageBills() {
        manageBillsCommand.execute();
    }

    public void manageShelves() {
        manageShelvesCommand.execute();
    }

    public void generateReports() {
        generateReportsCommand.execute();
    }

    // Additional methods for servlet integration
    public void addProduct(String name, double price) {
        ((ProductManagerCommand) manageProductsCommand).addProduct(name, price);
    }

    public void updateProduct(int id, String name, double price) {
        ((ProductManagerCommand) manageProductsCommand).updateProduct(id, name, price);
    }

    public void deleteProduct(int id) {
        ((ProductManagerCommand) manageProductsCommand).deleteProduct(id);
    }

    public ResultSet viewAllProducts() {
        return ((ProductManagerCommand) manageProductsCommand).viewAllProducts();
    }

    public HashMap<String, Object> getProduct(int id) {
        return ((ProductManagerCommand) manageProductsCommand).getProduct(id);
    }
}
