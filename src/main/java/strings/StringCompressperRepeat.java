package main.java.strings;

/*
Implement a method to perform basic string compression using the counts of repeated characters. 
For example, the string aabcccccaaa would become a2b1c5a3. 
Crucially, if the compressed string would not be smaller than the original string, your method should return the original string instead

Input Case 	Expected Output	Logic
aabcccccaaa	"a2b1c5a3"	Compressed is shorter than original (8 < 11 characters).
aabb	"aabb"	Compressed a2b2 is not shorter than aabb, so return original.
abcd	"abcd"	Compressed a1b1c1d1 is longer than original, so return original.
"" (Empty)	""	Edge case: Handle empty or null strings gracefully.

Initialize Your Tools
Start by checking for empty inputs. 
Create a StringBuilder and variables to track the current character and its count.
Iterate and Count
Loop through the string starting from the second character.
Compare and Append
If the current character matches the previous one, increment your counter. If it doesn't, append the previous character and its count to your StringBuilder, then reset the counter for the new character.
Final Append and Return
Manually append the final character group after the loop finishes. Finally, compare lengths: if your compressed version isn't actually shorter, return the original string. 


*/

public class StringCompressperRepeat {

    public static void main(String[] args) {
        String s = "aaabccaaa";
        String res = stringcheck(s);
        System.out.println(res);
    }

    public static String stringcheck(String s){

        StringBuilder sb = new StringBuilder();
        int freq =0;

        for (int i=0; i<s.length(); i++){
            /*  following frq++ is combination of 2 logics.
            1. there is hidden if logic of if (prev character == next character) then freq ++
            2. Since we declared freq = 0 initially, no matter what freq of char will be 1
            combining above 2 we dont need if condition and can directly do freq++ no matter what and do freq=0 in next if
            */
            freq ++;
            //check if it is not the end and if next is not same as previos character
            if(i+1 >= s.length() || s.charAt(i) != s.charAt(i+1)){
                sb.append(s.charAt(i));
                sb.append(freq);
                freq=0;
            }
        }
        // 3. Return the shorter of the two
         return sb.length() < s.length() ? sb.toString() : s;

    }
    
}
