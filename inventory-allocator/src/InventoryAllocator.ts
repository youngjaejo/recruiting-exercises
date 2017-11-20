import { ShipmentProposal } from "./ShipmentProposal";
import { SkuMap } from "./SkuMap";
import { WarehouseInventory } from "./WarehouseInventory";



export class InventoryAllocator {

  /**
   * Provides an array of all possible unique shipments that could be fulfilled 
   * given the available inventory.
   * @param requestedSkus - the skus with quantities that were ordered by a customer
   * @param availableSkusPerWarehouse - a map of warehouses where the values are SKUs 
   * and quantities are available.
   * @returns an array of ALL possible shipments given available inventory; 
   * an empty array if there is a stock out (not enough inventory).
   */
  public computeProposals(requestedSkus: SkuMap, availableSkusPerWarehouse: WarehouseInventory):
    ShipmentProposal[] {
    return [];
  }

}
