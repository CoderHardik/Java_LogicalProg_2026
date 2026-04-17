
/*
Logic - 

Frame 2 is running this loop:
─────────────────────────────────────────────────────
backtrack(startIndex=1, current=[2])

for (int i = 1; i < 6; i++) {

    i=1: add(2) → [2, 2]  → explore → remove → [2]   ← back
    i=2: add(3) → [2, 3]  → explore → remove → [2]   ← back
    i=3: add(4) → [2, 4]  → explore → remove → [2]   ← back
    i=4: add(5) → [2, 5]  → explore → remove → [2]   ← back  ← Frame 3 was called here!
    i=5: add(6) → [2, 6]  → explore → remove → [2]   ← back

}
// Frame 2 returns to Frame 1
Every single frame follows this same rhythm:
add → go deep → come back → remove → next i
add → go deep → come back → remove → next i
add → go deep → come back → remove → next i
*/

import java.util.*;

public class Sumof3 {

    public static List<List<Integer>> findNSum(int[] numbers, int n, int targetSum) {
        // 1. Remove zeros and duplicates, then sort for efficient exploration
        Set<Integer> uniqueSet = new HashSet<>();
        for (int num : numbers) {
            if (num != 0) uniqueSet.add(num);
        }
        
        List<Integer> nums = new ArrayList<>(uniqueSet);
        Collections.sort(nums);
        
        List<List<Integer>> results = new ArrayList<>();
        backtrack(results, new ArrayList<>(), nums, n, targetSum, 0);
        return results;
    }

    private static void backtrack(List<List<Integer>> results, List<Integer> path, 
                                 List<Integer> nums, int n, int remainingSum, int start) {
        // Base case: if we have n numbers, check if they sum to target
        // Remember that we are going in reverse order.
        //first we check if it fits criteria, then we start logic of building path list.
        // in short, we are building path list, if it matched then we check in beggining to add and start over
        if (path.size() == n) {
            if (remainingSum == 0) {
                results.add(new ArrayList<>(path));
            }
            return;
        }
        // we are passing start here because everytime we loop recursively, start will change. only first initial call will be sum=0
        for (int i = start; i < nums.size(); i++) {
            int current = nums.get(i);
            
            // Optimization: if current number exceeds remaining sum, 
            // no need to check further because list is sorted
            if (current > remainingSum) break;

            path.add(current);
            // Now comes main logic - since we added current to path, we will look for next element that is remaining-current until remaining =0
            backtrack(results, path, nums, n, remainingSum - current, i + 1);
            //After we tried this current number, the loop can move to the next i and try a different number in that same slot.
            // Basically following line will remove element for all trials that did not work out.i.e. 1,2,3 -> 1,2 : 1,2,6 -> 1 and then jump to 2,3 if unmderstand
            path.remove(path.size() - 1); // Backtrack to check next
        }
    }

    public static void main(String[] args) {
        int[] numbers = {0, 5, 2, 6, 4, 5, 3, 1, 2, 0};

        System.out.println("n=3, target=13: " + findNSum(numbers, 3, 13));
        System.out.println("n=4, target=13: " + findNSum(numbers, 4, 13));
    }
}

