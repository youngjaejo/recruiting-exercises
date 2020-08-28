import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

public class InventoryAllocator {

    private List<WareHouse> wareHouses = new ArrayList<WareHouse>();

    public List<Map<String, Object>> searchWareHouses(Map<String, Integer> items, List<WareHouse> wareHouses) {
        System.out.println("input: " + items + ", " + wareHouses);
        List<Map<String, Object>> searchedReuslt = new ArrayList<Map<String, Object>>();
        if (items.isEmpty()) { // emty item return emty list
            System.out.println("Output:" + searchedReuslt + "\n");
            return searchedReuslt;
        }

        Map<String, Integer> valuesOfWareHouse = new HashMap<>();
        Map<String, Integer> searchedItem;
        Map<String, Object> searchedWareHouse;
        int requiredQuantity;
        int itemQuantity;

        for (WareHouse wareHouse : wareHouses) {

            Iterator<String> iterator = items.keySet().iterator();

            if (items.isEmpty()) { // Break For-each loop if all items already founded before ending loop.
                break;
            }
            searchedItem = new HashMap<>();
            searchedWareHouse = new HashMap<>();
            valuesOfWareHouse = (Map<String, Integer>) wareHouse.getWareHouse().get("inventory");

            while (iterator.hasNext()) {
                String keyItem = iterator.next();
                requiredQuantity = items.get(keyItem);
                if (valuesOfWareHouse.containsKey(keyItem)) {
                    itemQuantity = valuesOfWareHouse.get(keyItem);
                    if (itemQuantity >= requiredQuantity) { // Enough quantity of item in the warehouse
                        searchedItem.put(keyItem, requiredQuantity);
                        iterator.remove(); // remove required item.it becomes faster processing.
                    } else if (itemQuantity < requiredQuantity) { // Not enough quantity of item in the warehouse
                        searchedItem.put(keyItem, itemQuantity);
                        items.replace(keyItem, requiredQuantity - itemQuantity);
                    }
                }
            }
            if (!searchedItem.isEmpty()) {
                searchedWareHouse.put(wareHouse.getWareHouse().get("name").toString(), searchedItem);
                searchedReuslt.add(searchedWareHouse);
            }
        }
        if (!items.isEmpty()) { // If input item doesnt't meet required quantity, Key & value will be stay in
                                // "items" variable.
            searchedReuslt.clear();
            System.out.println("Output:" + searchedReuslt + "\n");
            return searchedReuslt;
        }
        System.out.println("Output:" + searchedReuslt + "\n");
        return searchedReuslt;

    }

    public void addWareHouse(WareHouse wareHouse) {
        getWareHouses().add(wareHouse);

    }

    public List<WareHouse> getWareHouses() {
        return this.wareHouses;
    }

    public void setWareHouses(List<WareHouse> wareHouses) {
        this.wareHouses = wareHouses;
    }

    @Override
    public String toString() {

        return "List WareHouses : " + this.wareHouses;
    }

}