/* There is a list {1,3,2,,4,6} as input.
 * Op =5
 * In above, list is not sorted and should not be sorted in process
 * Here assume that list will start from 1 and go to n
 * Just check which number is missing from 1 to n without sorting list
 */
import java.util.*;

public class FindMissingNumber {

    public static void main(String[] args) {
        // Sample data for testing
        List<Integer> numbers = Arrays.asList(0, 1, 2, 4, 6);
        int n = 6; // Range from 0 to 6

        // Call the method and print the result
        List<Integer> missingNumbers = findMissing(numbers, n);
        System.out.println("Missing numbers from 0 to " + n + ": " + missingNumbers);
 
    }

    public static List<Integer> findMissing(List<Integer> A, int n){
        Set<Integer> seen= new HashSet<Integer>(A);
        List<Integer> missing = new ArrayList<>();

        for(int i=0; i<=n; i++){
            if(!seen.contains(i)){
                missing.add(i); // adding all missing numbers
            }
        }
         
        return missing;
        
    }
    
}
