package managers;

import factories.StockManagerInterface;
import models.Stock;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class StockManager implements StockManagerInterface {
    @Override
    public void addStock(int productId, int quantity, String purchaseDate, String expiryDate) {
        Stock stock = new Stock();
        HashMap<String, Object> data = new HashMap<>();
        data.put("product_id", productId);
        data.put("quantity", quantity);
        data.put("purchase_date", purchaseDate);
        data.put("expire_date", expiryDate);
        stock.create(data);
    }

    @Override
    public void updateStock(int id, int quantity, String purchaseDate, String expiryDate) {
        Stock stock = new Stock();
        HashMap<String, Object> stockToUpdate = stock.get(id);
        if (stockToUpdate != null) {
            stockToUpdate.put("quantity", quantity);
            stockToUpdate.put("purchase_date", purchaseDate);
            stockToUpdate.put("expire_date", expiryDate);
            stock.update(id, stockToUpdate);
        }
    }

    @Override
    public void deleteStock(int id) {
        Stock stock = new Stock();
        stock.delete(id);
    }

    @Override
    public HashMap<String, Object> getStock(int id) {
        Stock stock = new Stock();
        return stock.get(id);
    }

    @Override
    public List<HashMap<String, Object>> viewAllStocks() {
        Stock stock = new Stock();
        ResultSet rs = stock.allStock();
        return stock.convertResultSetToList(rs);
    }

    @Override
    public void manage() {

    }
}
