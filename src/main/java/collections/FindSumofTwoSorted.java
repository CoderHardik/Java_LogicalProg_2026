package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindSumofTwoSorted {
public static List<int[]> findAllPairs(int[] nums, int target) {
        List<int[]> allResults = new ArrayList<>();
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum == target) {
                // Found a pair! Add it as a new array object
                allResults.add(new int[] { nums[left], nums[right] });
                // Move both pointers to look for new numbers
                left++;
                right--;
            } else if (currentSum < target) {
                left++; // Sum too small, move left pointer to a bigger number
            } else {
                right--; // Sum too big, move right pointer to a smaller number
            }
        }
        return allResults;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 6, 8, 9};
        int target = 10;
        
        List<int[]> results = findAllPairs(nums, target);
        
        for (int[] pair : results) {
            System.out.println(Arrays.toString(pair));
        }
    }

}
