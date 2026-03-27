package collections;

import java.util.*;

public class DuplicateFinder {
    public static void main(String[] args) {
        int[] events = {101, 102, 103, 101, 104, 102};
        
        Set<Integer> seen = new HashSet<>();
        Set<Integer> duplicates = new LinkedHashSet<>(); // Using LinkedHashSet to keep order

        for (int id : events) {
            // .add returns false if the element is already in the set
            if (!seen.add(id)) {
                duplicates.add(id);
            }
        }

        System.out.println("Duplicate Event IDs: " + duplicates); 
        // Output: [101, 102]
    }
}

