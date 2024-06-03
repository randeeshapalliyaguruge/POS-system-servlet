package managers;

import factories.ProductManagerInterface;
import models.Product;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class ProductManager implements ProductManagerInterface {
    @Override
    public void addProduct(String name, double price) {
        Product product = new Product();
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("price", price);
        product.create(data);
    }

    @Override
    public void updateProduct(int id, String name, double price) {
        Product product = new Product();
        HashMap<String, Object> productToUpdate = product.get(id);
        if (productToUpdate != null) {
            productToUpdate.put("name", name);
            productToUpdate.put("price", price);
            product.update(id, productToUpdate);
        }
    }

    @Override
    public void deleteProduct(int id) {
        Product product = new Product();
        product.delete(id);
    }

    @Override
    public HashMap<String, Object> getProduct(int id) {
        Product product = new Product();
        return product.get(id);
    }

    @Override
    public List<HashMap<String, Object>> viewAllProducts() {
        Product product = new Product();
        ResultSet rs = product.all();
        return product.convertResultSetToList(rs);
    }

    @Override
    public void manage() {

    }
}
