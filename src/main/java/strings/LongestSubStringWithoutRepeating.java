import java.util.*;


public class LongestSubStringWithoutRepeating {

    public static void main(String[] args) {
        String s = "abcabcbb";
        longstring(s);

    }

    public static void longstring(String s){
        Set <Character> hs = new LinkedHashSet<>();
        Set<Character> bestSet = new LinkedHashSet<>(); // Added this to save the winner
        int i =0, j=0, max_length=0;
        while (i<s.length()){
            if (!hs.contains(s.charAt(i))){
                hs.add(s.charAt(i));
                // Check if we just found a longer string
                    if (hs.size() > max_length) {
                    max_length = hs.size();
                    bestSet = new LinkedHashSet<>(hs); // Save a copy of the current win
                    }
                i++;
            }
        
            else{
                hs.remove(s.charAt(j));//j is counter to keep track of which position element we removed
                j++;
            }
        }
        System.out.println(max_length);
        System.out.println(bestSet);
        System.out.println(hs.size());

    }
    
}
