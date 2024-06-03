package factories;

import java.util.HashMap;
import java.util.List;

public interface BillManagerInterface extends Manager {
    void createBill(HashMap<String, Object> billData);
    List<HashMap<String, Object>> viewAllBills();
    HashMap<String, Object> getBill(int id);
}
