package managers;

import factories.BillManagerInterface;
import models.Bill;
import java.util.HashMap;
import java.util.List;

public class BillManager implements BillManagerInterface {
    @Override
    public void createBill(HashMap<String, Object> billData) {
        Bill bill = new Bill();
        bill.create(billData);
    }

    @Override
    public List<HashMap<String, Object>> viewAllBills() {
        Bill bill = new Bill();
        return bill.all();
    }

    @Override
    public HashMap<String, Object> getBill(int id) {
        Bill bill = new Bill();
        return bill.get(id);
    }

    @Override
    public void manage() {

    }
}
