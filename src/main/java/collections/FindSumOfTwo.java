import java.util.*;
public class FindSumOfTwo {

    public static void main(String[] args) {
        int A[]= {10, 20, 30};
        int target = 50;
        sumoftwo(A, target);
    }

    public static void sumoftwo(int A[], int target) {
        
        Set <Integer> result = new HashSet<>();
        
        for (int i: A){
            int compliment = target-i;
            if (result.contains(compliment)){
                System.out.println("numbers are "+i+" and "+compliment);
            }
            result.add(i);
        }
    }
    
}

/*
;
To find if this we have to return indexes
public static List<int[]> findAllPairs(int[] nums, int target) {
    List<int[]> allResults = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            // Instead of returning, we add to our list and KEEP GOING
            allResults.add(new int[] { map.get(complement), i }); // we are adding pair here. So list will contain pair in each element
        }
        map.put(nums[i], i);
    }
    return allResults; // Returns a list of all found pairs
}


For sorted array, will only return one pair and program will stop
ublic static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int currentSum = numbers[left] + numbers[right];

            if (currentSum == target) {
                return new int[] { left, right };
            } else if (currentSum < target) {
                left++; // Move left to increase the sum
            } else {
                right--; // Move right to decrease the sum
            }
        }

        return new int[] {}; // No solution found
    }


}

*/