import java.util.*;
/* 
input = abbccaadf
targeted_values = [4,2,5,3,7] 
num_targets = 5

Result: 
[true,true,false,true,false] or 'true,true,false,true,false'
Explanation:
a - 1
b - 2
bb - 4
c - 3
cc - 6
a - 1
aa - 2
d - 4
f – 6
*/
public class UniformLetterCheck {
    public static void main(String[] args) {
        
    }

    public static boolean[] containsTargetedValues(String input, int[] targets) {
        // Use a HashSet for O(1) lookups
        Set<Integer> weights = new HashSet<>();
        int currentWeight = 0;
        char prevChar = ' ';
        // Single pass O(n) to find all uniform substring weights
        for (char c : input.toCharArray()) {
            int currentval = c-'a'+1;
            if (prevChar==c){
                currentWeight += currentval;
            }
            else{
                currentWeight=currentval;
                prevChar=c;
            }
        weights.add(currentWeight);    
        }

        boolean [] results = new boolean[targets.length];
        for(int i=0; i<targets.length; i++){
            results[i]= weights.contains(targets[i]);
        }
        return results;
    }

}
