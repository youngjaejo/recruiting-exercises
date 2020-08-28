
import java.util.*;

public class WareHouse {

    private Map<String, Object> wareHouse = new HashMap<>();

    public WareHouse(String name, Map<String, Integer> items) {
        addAWareHouse(name, items);
    }

    public void addAWareHouse(String name, Map<String, Integer> items) {
        this.wareHouse.put("name", name);
        this.wareHouse.put("inventory", items);
        System.out.println(this.wareHouse);

    }

    public Map<String, Object> getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(Map<String, Object> wareHouse) {
        this.wareHouse = wareHouse;
    }

    @Override
    public String toString() {

        return this.wareHouse.toString();
    }
}