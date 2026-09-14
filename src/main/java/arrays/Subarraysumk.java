/*
Given an array of integers nums and an integer k, 
return the total number of non-empty contiguous subarrays whose sum equals to k

Solution - https://medium.com/@zehrarizvi2565/subarray-sum-equals-k-124ad0b65231
The prefix sum approach offers a more optimized way to calculate subarray sums by reducing redundant computations. 

The key idea is instead of recalculating the sum for every subarray from scratch, we can precompute the prefix sum of the array, 
which helps in determining the sum of any subarray in constant time.

The HashMap approach leverages the concept of cumulative sums. Instead of recalculating the sum of subarrays repeatedly, we use a hash map to track cumulative sums and their frequencies. 
This allows us to determine how many subarrays sum to k in linear time. The main idea behind this approach is:
If the prefix sum up to two indices, say i and j, differs by k, then the sum of the subarray nums[i:j] is k.

Using this logic, the problem boils down to finding how many times a specific prefix sum occurs as we traverse the array. Let’s see how:
1. Prefix Sum: We calculate the prefix sum (sum[i]) for the array as we iterate through it. The prefix sum up to index i is the sum of all elements from the start of the array to i.
2. HashMap to Track Frequencies: We use a hash map (map) to store the frequency of each prefix sum we encounter. The key is the prefix sum, and the value is the number of times this sum has occurred.
3. Subarray Sum Condition: For every prefix sum sum[i] at index i, we check if the hash map contains the sum sum[i] - k. 
If it does, it means there exists a subarray whose sum is k, and we increment the count by how many times that difference has occurred.
4. HashMap Update: After processing each element, we update the hash map to reflect the new prefix sum, ensuring that future elements can find valid subarrays starting at previous indices.

======================================================================
DRY RUN: Subarray Sum Equals K
======================================================================
Input:
  nums = [1, 2, 3]
  k = 3

Initial Setup:
  map = {0=1}  (Base case: prefix sum of 0 has appeared 1 time)
  count = 0
  sum = 0

----------------------------------------------------------------------
Iteration 1: num = 1
----------------------------------------------------------------------
1. Update sum:
   sum = 0 + 1 = 1

2. Check if (sum - k) exists in map:
   sum - k = 1 - 3 = -2
   Is -2 in map? No.
   Action: count remains 0

3. Update Map with current sum (1):
   map.put(1, map.getOrDefault(1, 0) + 1)
   State: map = {0=1, 1=1}
   State: count = 0

----------------------------------------------------------------------
Iteration 2: num = 2
----------------------------------------------------------------------
1. Update sum:
   sum = 1 + 2 = 3

2. Check if (sum - k) exists in map:
   sum - k = 3 - 3 = 0
   Is 0 in map? YES! (Value/Frequency is 1)
   Action: count += map.get(0) -> count = 0 + 1 = 1
   Subarray found: [1, 2] (Indices 0 to 1)

3. Update Map with current sum (3):
   map.put(3, map.getOrDefault(3, 0) + 1)
   State: map = {0=1, 1=1, 3=1}
   State: count = 1

----------------------------------------------------------------------
Iteration 3: num = 3
----------------------------------------------------------------------
1. Update sum:
   sum = 3 + 3 = 6

2. Check if (sum - k) exists in map:
   sum - k = 6 - 3 = 3
   Is 3 in map? YES! (Value/Frequency is 1)
   Action: count += map.get(3) -> count = 1 + 1 = 2
   Subarray found: [3] (Indices 2 to 2)

3. Update Map with current sum (6):
   map.put(6, map.getOrDefault(6, 0) + 1)
   State: map = {0=1, 1=1, 3=1, 6=1}
   State: count = 2

======================================================================
Execution Finished
======================================================================
Total Subarrays Found (count): 2

So basically it will keep count of all sub array from beginning
checking sum-k in map will tell us if some subarray alread totalled this, if so then we have new sub array that has sum of K (removing that subarray that totalled sum at that time)

*/


import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Map to store (prefixSum, frequency)
        Map<Integer, Integer> map = new HashMap<>();
        
        // Base case: prefix sum of 0 has appeared once 
        // (to handle subarrays starting from index 0)
        map.put(0, 1);
        
        int count = 0;
        int sum = 0;
        
        for (int num : nums) {
            sum += num; // Update cumulative sum (Prefix Sum)
            
            // If (sum - k) exists, there's a subarray ending here that equals k
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            
            // Record this current prefix sum in the map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
}
