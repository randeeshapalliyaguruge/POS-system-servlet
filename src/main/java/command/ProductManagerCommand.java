package command;

import factories.Manager;
import managers.ProductManager;
import java.sql.ResultSet;
import java.util.HashMap;

public class ProductManagerCommand implements Command {
    private ProductManager productManager;

    public ProductManagerCommand(Manager productManager) {
        this.productManager = (ProductManager) productManager;
    }

    @Override
    public void execute() {
        productManager.manage();
    }

    // New methods for servlet integration
    public void addProduct(String name, double price) {
        productManager.addProduct(name, price);
    }

    public void updateProduct(int id, String name, double price) {
        productManager.updateProduct(id, name, price);
    }

    public void deleteProduct(int id) {
        productManager.deleteProduct(id);
    }

    public ResultSet viewAllProducts() {
        return productManager.viewAllProducts();
    }

    public HashMap<String, Object> getProduct(int id) {
        return productManager.getProduct(id);
    }
}
