package managers;

import factories.ShelveManagerInterface;
import models.Product;
import models.Shelve;
import models.Stock;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ShelveManager implements ShelveManagerInterface {
    @Override
    public HashMap<String, Object> createShelve(HashMap<String, Object> data) {
        Shelve shelve = new Shelve();
        Stock stock = new Stock();
        Product product = new Product();
        HashMap<String, Object> productData = product.get((int) data.get("product_id"));
        if (productData != null) {
            int productId = (int) data.get("product_id");
            int quantity = (int) data.get("quantity");

            List<HashMap<String, Object>> stocks = stock.getAllByProductId(productId);
            stocks.sort(Comparator.comparing((HashMap<String, Object> o) -> (String) o.get("expire_date"))
                    .thenComparing(o -> (String) o.get("purchase_date")));

            for (HashMap<String, Object> stockData : stocks) {
                int stockQuantity = (int) stockData.get("quantity");
                if (stockQuantity >= quantity) {
                    stockData.put("quantity", stockQuantity - quantity);
                    stock.update((int) stockData.get("id"), stockData);

                    data.put("stock_id", stockData.get("id"));
                    HashMap<String, Object> shelveModel = shelve.create(data);
                    return shelveModel;
                } else {
                    stockData.put("quantity", 0);
                    stock.update((int) stockData.get("id"), stockData);

                    if (stockQuantity > 0) {
                        data.put("stock_id", stockData.get("id"));
                        data.put("quantity", stockQuantity);
                        HashMap<String, Object> shelveModel = shelve.create(data);
                        quantity -= stockQuantity;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public HashMap<String, Object> updateShelve(int id, HashMap<String, Object> data) {
        Shelve shelve = new Shelve();
        Stock stock = new Stock();
        HashMap<String, Object> shelveToUpdate = shelve.get(id);
        if (shelveToUpdate != null) {
            int oldQuantity = (int) shelveToUpdate.get("quantity");
            int stockIdToUpdate = (int) shelveToUpdate.get("stock_id");
            HashMap<String, Object> stockDataToUpdate = stock.get(stockIdToUpdate);

            int newQuantity = (int) data.get("quantity");
            int stockQuantityToUpdate = (int) stockDataToUpdate.get("quantity");
            stockDataToUpdate.put("quantity", stockQuantityToUpdate + oldQuantity - newQuantity);
            stock.update(stockIdToUpdate, stockDataToUpdate);

            data.put("quantity", newQuantity);
            return shelve.update(id, data);
        }
        return null;
    }

    @Override
    public void deleteShelve(int id) {
        Shelve shelve = new Shelve();
        Stock stock = new Stock();
        HashMap<String, Object> shelveToDelete = shelve.get(id);
        if (shelveToDelete != null) {
            int quantityToDelete = (int) shelveToDelete.get("quantity");
            int stockIdToDelete = (int) shelveToDelete.get("stock_id");
            HashMap<String, Object> stockDataToDelete = stock.get(stockIdToDelete);

            int stockQuantityToDelete = (int) stockDataToDelete.get("quantity");
            stockDataToDelete.put("quantity", stockQuantityToDelete + quantityToDelete);
            stock.update(stockIdToDelete, stockDataToDelete);

            shelve.delete(id);
        }
    }

    @Override
    public List<HashMap<String, Object>> viewAllShelves() {
        Shelve shelve = new Shelve();
        ResultSet rs = shelve.allShelves();
        return shelve.convertResultSetToList(rs);
    }

    @Override
    public HashMap<String, Object> getShelve(int id) {
        Shelve shelve = new Shelve();
        return shelve.get(id);
    }

    @Override
    public void manage() {

    }
}
