package facade;

import command.*;
import factories.ManagerFactory;

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
}
