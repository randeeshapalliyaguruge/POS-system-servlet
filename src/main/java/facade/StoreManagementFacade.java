package facade;

import factories.*;
import models.Shelve;
import models.Stock;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreManagementFacade {
    private ProductManagerInterface productManager;
    private StockManagerInterface stockManager;
    private ShelveManagerInterface shelveManager;
    private BillManagerInterface billManager;
    private ReportManagerInterface reportManager;

    public StoreManagementFacade() {
        this.productManager = (ProductManagerInterface) ManagerFactory.getManager("Product");
        this.stockManager = (StockManagerInterface) ManagerFactory.getManager("Stock");
        this.shelveManager = (ShelveManagerInterface) ManagerFactory.getManager("Shelve");
        this.billManager = (BillManagerInterface) ManagerFactory.getManager("Bill");
        this.reportManager = (ReportManagerInterface) ManagerFactory.getManager("Report");
    }

    // Product methods
    public void addProduct(String name, double price) {
        productManager.addProduct(name, price);
    }

    public void updateProduct(int id, String name, double price) {
        productManager.updateProduct(id, name, price);
    }

    public void deleteProduct(int id) {
        productManager.deleteProduct(id);
    }

    public HashMap<String, Object> getProduct(int id) {
        return productManager.getProduct(id);
    }

    public List<HashMap<String, Object>> viewAllProducts() {
        return productManager.viewAllProducts();
    }

    // Stock methods
    public void addStock(int productId, int quantity, String purchaseDate, String expiryDate) {
        stockManager.addStock(productId, quantity, purchaseDate, expiryDate);
    }

    public void updateStock(int id, int quantity, String purchaseDate, String expiryDate) {
        stockManager.updateStock(id, quantity, purchaseDate, expiryDate);
    }

    public void deleteStock(int id) {
        stockManager.deleteStock(id);
    }

    public HashMap<String, Object> getStock(int id) {
        return stockManager.getStock(id);
    }

    public List<HashMap<String, Object>> viewAllStocks() {
        return stockManager.viewAllStocks();
    }

    // Shelve methods
    public HashMap<String, Object> addShelve(HashMap<String, Object> data) {
        return shelveManager.createShelve(data);
    }

    public HashMap<String, Object> updateShelve(int id, int quantity) {
        Shelve shelve = new Shelve();
        Stock stock = new Stock();
        HashMap<String, Object> shelveToUpdate = shelve.get(id);
        if (shelveToUpdate != null) {
            int oldQuantity = (int) shelveToUpdate.get("quantity");
            int stockIdToUpdate = (int) shelveToUpdate.get("stock_id");
            HashMap<String, Object> stockDataToUpdate = stock.get(stockIdToUpdate);

            int stockQuantityToUpdate = (int) stockDataToUpdate.get("quantity");
            stockDataToUpdate.put("quantity", stockQuantityToUpdate + oldQuantity - quantity);
            stock.update(stockIdToUpdate, stockDataToUpdate);

            HashMap<String, Object> dataToUpdate = new HashMap<>();
            dataToUpdate.put("quantity", quantity);
            return shelve.update(id, dataToUpdate);
        }
        return null;
    }

    public void deleteShelve(int id) {
        shelveManager.deleteShelve(id);
    }

    public List<HashMap<String, Object>> viewAllShelves() {
        return shelveManager.viewAllShelves();
    }

    public HashMap<String, Object> getShelve(int id) {
        return shelveManager.getShelve(id);
    }

    // Bill methods
    public void createBill(HashMap<String, Object> billData) {
        billManager.createBill(billData);
    }

    public List<HashMap<String, Object>> viewAllBills() {
        return billManager.viewAllBills();
    }

    public HashMap<String, Object> getBill(int id) {
        return billManager.getBill(id);
    }

    // Report methods
    public List<Map<String, Object>> generateSalesReport(String date) throws SQLException {
        return reportManager.generateSalesReport(date);
    }

    public List<Map<String, Object>> generateReshelvingReport() throws SQLException {
        return reportManager.generateReshelvingReport();
    }

    public List<Map<String, Object>> generateReorderReport() throws SQLException {
        return reportManager.generateReorderReport();
    }

    public List<Map<String, Object>> generateStockReport() throws SQLException {
        return reportManager.generateStockReport();
    }

    public List<Map<String, Object>> generateBillReport() throws SQLException {
        return reportManager.generateBillReport();
    }
}
