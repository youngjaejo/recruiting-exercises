

### Problem

Inventory allocator is a class that computes all possible shipments given a particular inventory distribution. The way we would use the output is take each shipment configuration and estimate how much it costs and pick the optimal one.

Your task is to implement InventoryAllocator class to produce all possible shipment configurations. The class should have one function that takes in some input. The first input will be a map of items that are being ordered and how many of them are ordered. The second input will be a map of warehouses to inventory amounts for these items. 

You can use any language of your choice to write the solution (internally we use Typescript/Javascript and some Java). Please write unit tests with your code, a few are mentioned below, but these are not comprehensive. Fork the repository and put your solution inside of the src directory and include a way to run your tests!

### Examples

*Happy Case, exact inventory match!**

Input: `{ apple: 1 }, { owd: { apple: 1 } }`  
Output: `[{ owd: { apple: 1 } }]`

*Not enough inventory -> no allocations!*

Input: `{ apple: 1 }, { owd: { apple: 0 } }`  
Output: `[]`

*Should split an item across warehouses:*

Input: `{ apple: 10 }, { owd: { apple: 5 }, dm: { apple: 5 }}`  
Output: `[{ dm: { apple: 5 }, owd: { apple: 5 } }]`

*Should provide two suggestions if an item can go in either warehouse:*

Input: ```   
       ({ apple: 5, banana: 5, orange: 5 }, 
       { 
          owd: { apple: 5, orange: 10 }, 
          dm: { banana: 5, orange: 10 } 
       })```  
Output: `[{ dm: { banana: 5 }, owd: { apple: 5, orange: 5 } }]`

### What are we looking for

We'll evaluate your code via the following guidelines in no particular order:

1. **Readability**: naming, spacing, consistency
2. **Correctness**: is the solution correct and does it solve the problem
1. **Test Code Quality**: Is the test code comperehensive and covering all cases.
1. **Tool/Language mastery**: is the code using up to date syntax and techniques. 