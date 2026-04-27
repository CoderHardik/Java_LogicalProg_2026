/*
Input array - [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Max sub array - [4, -1, 2, 1]
Max sum - 6

"Given an integer array, find the contiguous subarray which has the largest sum and return its sum."
"Write an algorithm for the Maximum Subarray problem."

Instead of shrinking from the outside in, the standard 



 solution (Kadane's) grows from the inside out. 
 It makes a single pass and, at every element, 
 asks: "Should I add this element to my current running total, or is the current total so bad (negative) that I should just start fresh from this element

 Basically start from first element
 - start adding
 - see if which of total sum and number itself is higher. store higher of 2 to current max -- 
 this step will restart if previous num was negative
 i.e. [-2,1], currentMax = -2 -> i=1 : currentMax = Math.max(1, -2 + 1)  - Math.max(1, -1) = 1 -> 
insight: The previous sum was negative, so we "threw it away" and started fresh at 1.

 - whichever is max of current max and max so far, store to max so far.
*/


public class MaximumSubarray {


    public static int maxSubArray(int[] nums) {
        // 1. Initialize variables to the first element
        int maxSoFar = nums[0];
        int currentMax = nums[0];

        // 2. Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            // Decide whether to start a new subarray or extend the current one
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            
            // Update the global maximum if the current local max is higher
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] sample = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Subarray Sum: " + maxSubArray(sample)); // Output: 6
    }
    
}
