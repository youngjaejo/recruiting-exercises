import java.util.HashMap;
import java.util.Map;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;

public class TestInventoryAllocator {

  // Test1 Order can be shipped using one warehouse
  @Test
  public void testA() {

    InventoryAllocator inventoryAllocator = new InventoryAllocator();
    Map<String, Integer> item = new HashMap<>();
    System.out.println("Test1: Order can be shipped using one warehouse");
    item.put("carrot", 3);
    item.put("apple", 3);
    WareHouse wareHouse = new WareHouse("WareHouse1", item);
    inventoryAllocator.addWareHouse(wareHouse); // Add a warehouse
    System.out.println(inventoryAllocator);

    item = new HashMap<>(); // user order
    item.put("apple", 1);
    inventoryAllocator.searchWareHouses(item, inventoryAllocator.getWareHouses());

  }

  // Test2 Order can be shipped using multiple warehouses
  @Test
  public void testB() {
    InventoryAllocator inventoryAllocator = new InventoryAllocator();
    Map<String, Integer> item = new HashMap<>();

    System.out.println("Test2: Order can be shipped using multiple warehouses");
    item.put("carrot", 3);
    item.put("apple", 3);
    WareHouse wareHouse = new WareHouse("WareHouse1", item);
    inventoryAllocator.addWareHouse(wareHouse);
    item = new HashMap<>();
    item.put("banana", 3);
    item.put("orange", 5);
    wareHouse = new WareHouse("WareHouse2", item);
    inventoryAllocator.addWareHouse(wareHouse);

    item = new HashMap<>();
    item.put("banana", 3);
    item.put("mango", 3);
    wareHouse = new WareHouse("WareHouse3", item);
    inventoryAllocator.addWareHouse(wareHouse);

    item = new HashMap<>();
    item.put("banana", 5);
    item.put("mango", 3);
    item.put("tomato", 3);
    wareHouse = new WareHouse("WareHouse4", item);
    inventoryAllocator.addWareHouse(wareHouse);
    System.out.println(inventoryAllocator);

    item = new HashMap<>(); // user order
    item.put("mango", 5);
    item.put("orange", 5);
    item.put("banana", 10);
    inventoryAllocator.searchWareHouses(item, inventoryAllocator.getWareHouses());

  }

  // Test3 Order cannot be shipped because there is not enough inventory or empty
  // item
  @Test
  public void testC() {
    InventoryAllocator inventoryAllocator = new InventoryAllocator();
    Map<String, Integer> item = new HashMap<>();

    System.out.println("Test3: Order cannot be shipped because there is not enough inventory");
    item.put("carrot", 3);
    item.put("apple", 3);
    WareHouse wareHouse = new WareHouse("WareHouse1", item);
    inventoryAllocator.addWareHouse(wareHouse);
    item = new HashMap<>();
    item.put("banana", 3);
    item.put("orange", 5);
    wareHouse = new WareHouse("WareHouse2", item);
    inventoryAllocator.addWareHouse(wareHouse);

    item = new HashMap<>();
    item.put("banana", 3);
    item.put("mango", 3);
    wareHouse = new WareHouse("WareHouse3", item);
    inventoryAllocator.addWareHouse(wareHouse);
    System.out.println(inventoryAllocator);

    item = new HashMap<>(); // user orders
    item.put("mango", 3);
    item.put("orange", 5);
    item.put("banana", 7);
    // User input banana:7 > Quantity of banana(total:6) in warehouses
    inventoryAllocator.searchWareHouses(item, inventoryAllocator.getWareHouses());

    // change orange value to 0 at warehouse2
    item = (Map<String, Integer>) inventoryAllocator.getWareHouses().get(1).getWareHouse().get("inventory");
    item.replace("orange", 0);
    inventoryAllocator.getWareHouses().get(1).getWareHouse().replace("inventory", item);

    item = new HashMap<>(); // user order
    item.put("mango", 4);
    item.put("orange", 5);
    item.put("banana", 5);
    inventoryAllocator.searchWareHouses(item, inventoryAllocator.getWareHouses());

    item = new HashMap<>();
    inventoryAllocator.searchWareHouses(item, inventoryAllocator.getWareHouses());
    inventoryAllocator.getWareHouses().clear();
    inventoryAllocator.searchWareHouses(item, inventoryAllocator.getWareHouses());
  }

}