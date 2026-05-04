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

Why sum-k:
In [1, 2, 1],
- you would find [1, 2] because the sum is 3. But you would miss the subarray [2, 1] because the total sum at that point is 4.
- When the sum is 4, you look for 4 - 3 = 1. Since the sum was 1 at the very beginning, the code knows that everything added after that 1 (which is 2 + 1) must equal 3.

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
