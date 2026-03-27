package arrays;
import java.util.*;

public class HackerRank {
    public static int getMaxSubarrayLen(List<Integer> team_a, List<Integer> team_b) {
        int n = team_a.size();
        if (n == 0) return 0;

        // dpA[i] = max non-decreasing length ending at index i using team_a[i]
        // dpB[i] = max non-decreasing length ending at index i using team_b[i]
        int[] dpA = new int[n];
        int[] dpB = new int[n];

        // Base case: at the first element, the max length is always 1
        dpA[0] = 1;
        dpB[0] = 1;
        int maxOverall = 1;

        for (int i = 1; i < n; i++) {
            // Initialize with 1 (current element starts a new subarray)
            dpA[i] = 1;
            dpB[i] = 1;

            // Transitions for dpA[i]
            // Can we extend a sequence ending at team_a[i-1]?
            if (team_a.get(i) >= team_a.get(i - 1)) {
                dpA[i] = Math.max(dpA[i], dpA[i - 1] + 1);
            }
            // Can we extend a sequence ending at team_b[i-1]?
            if (team_a.get(i) >= team_b.get(i - 1)) {
                dpA[i] = Math.max(dpA[i], dpB[i - 1] + 1);
            }

            // Transitions for dpB[i]
            // Can we extend a sequence ending at team_a[i-1]?
            if (team_b.get(i) >= team_a.get(i - 1)) {
                dpB[i] = Math.max(dpB[i], dpA[i - 1] + 1);
            }
            // Can we extend a sequence ending at team_b[i-1]?
            if (team_b.get(i) >= team_b.get(i - 1)) {
                dpB[i] = Math.max(dpB[i], dpB[i - 1] + 1);
            }

            // Update the global maximum length found so far
            maxOverall = Math.max(maxOverall, Math.max(dpA[i], dpB[i]));
        }

        return maxOverall;
    }

    public static void main(String[] args) {
        // Example from your image:
        // team_a = [5, 2, 4, 1], team_b = [3, 6, 2, 2]
        List<Integer> a = Arrays.asList(5, 2, 4, 1);
        List<Integer> b = Arrays.asList(3, 6, 2, 2);
        System.out.println("Max Subarray Length: " + getMaxSubarrayLen(a, b)); 
        // Output: 3 (Subarray from index 1 to 3: 2 -> 2 -> 2)
    }
}
