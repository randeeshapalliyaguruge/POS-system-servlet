package factories;

import java.util.HashMap;
import java.util.List;

public interface StockManagerInterface extends Manager {
    void addStock(int productId, int quantity, String purchaseDate, String expiryDate);
    void updateStock(int id, int quantity, String purchaseDate, String expiryDate);
    void deleteStock(int id);
    HashMap<String, Object> getStock(int id);
    List<HashMap<String, Object>> viewAllStocks();
}
