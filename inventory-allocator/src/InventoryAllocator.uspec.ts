import { InventoryAllocator } from "./InventoryAllocator";

describe("Inventory Allocator", () => {

  let allocator: InventoryAllocator;

  beforeAll(() => {
    allocator = new InventoryAllocator();
  });

  it("should create one shipment where inventory is exactly matching", () => {
    expect(allocator.computeProposals({ apple: 1 }, { owd: { apple: 1 } }))
      .toEqual([{ owd: { apple: 1 } }]);
  });

  it("should create one shipment where inventory is more than needed", () => {
    expect(allocator.computeProposals({ apple: 1 }, { owd: { apple: 10 } }))
      .toEqual([{ owd: { apple: 1 } }]);
  });

  it("should create no shipments when there is not enough inventory", () => {
    expect(allocator.computeProposals({ apple: 1 }, { owd: { apple: 0 } }))
      .toEqual([]);
  });

  it("should be able to split a single item across warehouses", () => {
    expect(allocator.computeProposals({ apple: 10 }, {
      owd: { apple: 5 },
      dm: { apple: 5 }
    }))
    .toEqual([{ dm: { apple: 5 }, owd: { apple: 5 } }]);
  });

  it("should be able to get any item from all available warehouses", () => {
    expect(allocator.computeProposals({ apple: 5 }, {
      owd: { apple: 5 }, dm: { apple: 5 },
      sp: { apple: 5, orange: 2 },
    }))
      .toEqual([{ owd: { apple: 5 } }, { dm: { apple: 5 } }, { sp: { apple: 5 } },
      ]);
  });

  it("should be able to split two items across warehouses", () => {
    expect(allocator.computeProposals({ apple: 5, banana: 5 }, {
      owd: { apple: 5 },
      dm: { banana: 5 },
    }))
      .toEqual([{ dm: { banana: 5 }, owd: { apple: 5 } }]);
  });

  it("should provide two suggestions if an item can go along with another sku from either warehouse", () => {
    expect(allocator.computeProposals({ apple: 5, banana: 5, orange: 5 },
      { owd: { apple: 5, orange: 10 }, dm: { banana: 5, orange: 10 } }))
      .toEqual([{ dm: { banana: 5 }, owd: { apple: 5, orange: 5 } },
      { dm: { banana: 5, orange: 5 }, owd: { apple: 5 } }]);
  });

  it("should fail to allocate if one sku is missing inventory", () => {
    expect(allocator.computeProposals({ apple: 5, banana: 5, orange: 5 },
      { owd: { apple: 5, orange: 2 }, dm: { banana: 5, orange: 2 } }))
      .toEqual([]);
  });

});
