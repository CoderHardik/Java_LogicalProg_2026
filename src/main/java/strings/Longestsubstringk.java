import java.util.HashMap;
import java.util.Map;

/*
"Given a string and a number 
, find the length of the longest substring that contains at most 
 distinct (unique) characters."
It is a classic "Sliding Window" problem often used in technical interviews to test your ability to manage a sub-section of data efficiently.
A Real-World Example
Imagine the string is "eceba" and 

:
"e" (1 distinct character) — Length 1
"ec" (2 distinct) — Length 2
"ece" (2 distinct: 'e' and 'c') — Length 3
"eceb" (3 distinct: 'e', 'c', 'b') — Invalid (too many unique characters)
The answer here is 3.

-- Code implementation ---
s = "eceba" with k = 2.
Step 1: Lead Scout i starts moving
i points at 'e': Map = {e:1}. Size is 1 (which is  2). Keep going.
i points at 'c': Map = {e:1, c:1}. Size is 2 (which is 2). Keep going.
i points at 'e': Map = {e:2, c:1}. Size is still 2 (only 'e' and 'c' are inside). Keep going.

Step 2: The "Limit" is hit
i points at 'b': Map = {e:2, c:1, b:1}.
Wait! hm.size() is now 3, which is greater than k=2.
The while (hm.size() > k) loop triggers.

Step 3: The Tail j starts shrinking (The "Cleaning" Phase)
The window is currently [e, c, e, b]. We need to kick someone out from the left (j) until we only have 2 unique characters again.
First j move: j points at the first 'e'.
hm.put('e', 2 - 1) 
 Map is {e:1, c:1, b:1}.
e count is not 0, so we don't remove it from the map.
j++.

Check: Map size is still 3. The while loop runs again!
Second j move: j points at 'c'.
hm.put('c', 1 - 1) 
 Map is {e:1, c:0, b:1}.
 
The Critical Step: Since 'c' is now 0, we hm.remove('c').
Map is now {e:1, b:1}.
Check: Map size is now 2. The while loop stops!
Step 4: Resume
Now the window is just [e, b]. i moves to the next character ('a') and the process repeats.
Why
*/
public class Longestsubstringk {

    // add main method
    public static int longstringK(String s, int k) {
    // Map tracks the characters and their counts in our current window
    Map<Character, Integer> hm = new HashMap<>();
    int i = 0, j = 0, max_length = 0;

    while (i < s.length()) {
        char rightChar = s.charAt(i);
        // Expand: Add the current character to the map
        hm.put(rightChar, hm.getOrDefault(rightChar, 0) + 1);

        // Shrink: If we have more than K unique characters, move 'j'
        while (hm.size() > k) {
            char leftChar = s.charAt(j);
            hm.put(leftChar, hm.get(leftChar) - 1);
            
            // Critical step: If count is 0, it's no longer "distinct" in our window
            if (hm.get(leftChar) == 0) {
                hm.remove(leftChar);
            }
            j++; // Move the left boundary
        }

        // Measure: Calculate window size and update max
        max_length = Math.max(max_length, i - j + 1);
        i++;
    }
    return max_length;
}

    
}
