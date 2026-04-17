import java.util.*;

/*

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Why: While "ADOBEC" contains 'A', 'B', and 'C', it has a length of 6. "BANC" also contains all three but has a length of only 4.


The Strategy: "Expand and Shrink"
Think of it like an accordion:
Expand (Right pointer): Grow until you have everything you need.
Shrink (Left pointer): Contract as much as possible to "trim the fat" until the window breaks.
Repeat: Move the Right pointer again to find a better window.

The Tools
targetFreq[] (Array): Our "Locker Room." Positive means "I need this," Zero/Negative means "I have enough/extra."
count: Our "Checklist." When count == t.length(), the window is valid.
minLen & startIndex: Our "Record Keepers" for the best answer found so far.

The Dry Run: 
Step	Window	count	Action	minLen
Start	[]	0	targetFreq has A:1, B:1, C:1	
Expand 'A'	[A]	1	Found A! targetFreq[A] becomes 0.	
Expand 'D'	[AD]	1	Junk char. targetFreq[D] becomes -1.	
Expand 'B'	[ADB]	2	Found B! targetFreq[B] becomes 0.	
Expand 'C'	[ADBC]	3	Found C! Window Valid.	4
Shrink 'A'	[DBC]	2	Window Breaks! We removed 'A' and now targetFreq[A] is 1.	4
Final Result: "ADBC" (length 4)




 because each pointer travels across the string exactly once."
*/


public class MinumumWindowSubString {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // 1. Build frequency map for the target string T
        int[] targetFreq = new int[128]; // Use 128 for all ASCII characters
        for (char c : t.toCharArray()) {
            targetFreq[c]++;
        }

        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;
        int count = 0; // Tracks how many characters from T are currently in our window

        // 2. Expand the window using the 'right' pointer
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            
            // If this character is needed for T, increment our satisfied count
            if (targetFreq[rightChar] > 0) {
                count++;
            }
            // Decrement frequency (negative values mean extra characters)
            targetFreq[rightChar]--;

            // 3. When the window is "valid" (contains all characters of T), try to shrink it
            while (count == t.length()) {
                // Update the minimum window if the current one is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);
                // When moving 'left', we put the character back into the requirements
                targetFreq[leftChar]++;
                
                // If the frequency becomes positive, it means we lack a character needed for T
                if (targetFreq[leftChar] > 0) {
                    count--;
                }
                left++; // Contract the window
            }
            right++; // Expand the window
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println("Result: " + sol.minWindow("ADOBECODEBANC", "ABC")); // Output: BANC
    }
}
 {
    
}
