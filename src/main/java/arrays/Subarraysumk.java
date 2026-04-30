/*
Given an array of integers nums and an integer k, 
return the total number of non-empty contiguous subarrays whose sum equals to k
Solution - https://medium.com/@zehrarizvi2565/subarray-sum-equals-k-124ad0b65231
The prefix sum approach offers a more optimized way to calculate subarray sums by reducing redundant computations. 
The key idea is instead of recalculating the sum for every subarray from scratch, we can precompute the prefix sum of the array, 
which helps in determining the sum of any subarray in constant time.
Let’s break down the approach in a few simple steps:
1. Build Prefix Sum Array: Create a prefix sum array, sum, where each element sum[i] stores the sum of all elements in nums from the beginning up to the (i-1)-th index.
2. Calculate Subarray Sums Using Prefix Sum: Sum of a subarray nums[i:j] = sum[j+1]−sum[i]. This gives the sum of elements from index i to j in constant time.
3. Check for Target Sum: For each subarray nums[i:j], if the sum equals k, increment the count.


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
