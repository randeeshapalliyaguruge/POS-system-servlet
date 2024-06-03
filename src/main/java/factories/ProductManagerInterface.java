package factories;

import java.util.HashMap;
import java.util.List;

public interface ProductManagerInterface extends Manager {
    void addProduct(String name, double price);
    void updateProduct(int id, String name, double price);
    void deleteProduct(int id);
    HashMap<String, Object> getProduct(int id);
    List<HashMap<String, Object>> viewAllProducts();
}
