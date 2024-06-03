package factories;

import java.util.HashMap;
import java.util.List;

public interface ShelveManagerInterface extends Manager {
    HashMap<String, Object> createShelve(HashMap<String, Object> data);
    HashMap<String, Object> updateShelve(int id, HashMap<String, Object> data);
    void deleteShelve(int id);
    List<HashMap<String, Object>> viewAllShelves();
    HashMap<String, Object> getShelve(int id);
}
