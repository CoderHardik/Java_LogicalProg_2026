import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Input: An array (or list) of strings (e.g., ["flower", "flow", "flight"]).
Output: A single string representing the longest shared prefix. If no common prefix exists, the output is an empty string ""
o/p- fl

The Logic:
1. Sort the array alphabetically.
After sorting, the first and last strings will be the most different from each other.
Input: ["flower", "flow", "flight", "fly"]
Sorted: ["flight", "flow", "flower", "fly"]
Because they are alphabetized, the first string ("flight") and the last string ("fly") are the "outer boundaries." If a character exists in the same position in both the first and last strings, it must exist in that same position for every string in the middle.


2. Any prefix shared by the first and last strings must be shared by everything in between.
We ignore everything in the middle and only look at these two:
first = "flight"
last = "fly"
i = 0: first[0] is 'f', last[0] is 'f'. Match! i becomes 1.
i = 1: first[1] is 'l', last[1] is 'l'. Match! i becomes 2.
i = 2: first[2] is 'i', last[2] is 'y'. Mismatch! The loop stops.

3. Result: You only have to compare two strings instead of the whole array!
We then take the substring from 0 to i (which is 2):
first.substring(0, 2) → returns "fl".

*/
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String strs[]= {"flower","flow","flight"};
        List <Character> re = new ArrayList<>(longestcommon(s));
    for(char c: re){
      System.out.println(c);
    }
    }
    public static List<Character> longcom(String []s){
        //if (s==null || s.length==0) return ""; --change this to suite list
        Arrays.sort(s);
    String first = s[0];
    String last = s[s.length-1];
    int length = Math.min(first.length(), last.length());
    List<Character> result = new ArrayList<>();
   
    for (int i=0; i<length; i++){
      if (first.charAt(i)==last.charAt(i)){
        result.add(first.charAt(i));
      }
      else{
        break;
      }
    }
    
    return result;

    }
    
}
