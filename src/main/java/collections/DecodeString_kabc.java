/*
Direct Decoded OutputThe string k[abc] decodes to:abc repeated k times (e.g., abcabc...abc).Practical 
Examples3[a] → aaa2[abc] → abcabc3[a2[c]] → accaccacc (The inner 2[c] is decoded first into cc, making the outer string 3[acc])

How the Decoding Algorithm WorksTo handle nested brackets and varying values of k, developers typically use a stack-based approach:
Maintain Two Stacks: One for the repetition numbers (k) and one for the strings being built.
Iterate through the String:
If you see a digit: 
Build the full number k.
If you see an opening bracket [: Push the current built string and the number k onto their respective stacks, then reset your temporary variables.
If you see a closing bracket ]: Pop the top number and string from the stacks. Repeat the current substring k times and append it to the popped string.
If you see a letter: Simply append it to your current working string

A Walkthrough: 
abc2[de]Before the bracket: 
currentString is "abc".
At the [: We push "abc" onto the stringStack. 
We reset currentString to empty to start collecting the "filling" ("de").
Inside the bracket: currentString becomes "de".
At the ]:int repeatTimes = countStack.pop(); (this is 2).
StringBuilder previousString = stringStack.pop(); (this is "abc").
The Loop: We append the "filling" ("de") to the "bread" ("abc") twice.
Round 1: previousString becomes "abcde"
Round 2: previousString becomes "abcdede"
The Assignment: currentString = previousString; (now our working string is "abcdede").
Why it's accuratepreviousString does not contain the current inner string yet. 
It contains everything that came before the number and the opening bracket. 
By popping it and appending the currentString to it multiple times, you are correctly "stitching" the repeated section back onto the main string.
Analogy:
If you are writing: I love 3[very ]much
previousString is "I love ".currentString is "very ".
The loop adds "very " to "I love " three times.
Result: "I love very very very "... and then the code continues to add "much".
*/


import java.util.Stack;

public class Solution {
    public String decodeString(String s) {
        // Using the legacy Stack class
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Handle multi-digit numbers (e.g., "10[a]")
                //If ch is '5', then ch - '0' is mathematically 53 - 48, which equals the integer 5.
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // Start of a new nested level: save state and reset
                countStack.push(k);
                stringStack.push(currentString);
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // End of level: pop count and previous string to combine
                int repeatTimes = countStack.pop();
                StringBuilder previousString = stringStack.pop();
                
                // Repeat current string k times and append to the previous level
                for (int i = 0; i < repeatTimes; i++) {
                    previousString.append(currentString);
                }
                currentString = previousString;
            } else {
                // Regular character: add to current working string
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}
