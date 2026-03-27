/* 
You’re given a string (like "(){}[]" or "({[]})").
You need to check if it is well-formed or not — meaning:
✅ every opening bracket has a matching closing bracket,
✅ in the correct order.
Return a boolean value (True or False).

Stack Theory:
A Stack is a "Last-In, First-Out" (LIFO) data structure. Think of it exactly like a stack of physical trays in a cafeteria:
- You can only add a new tray to the top (push).
- You can only remove the tray that is currently on the top (pop).
- If you want the tray at the bottom, you have to remove everything above it first.

The 3 Essential Stack Commands
In Java, you use the java.util.Stack class:
push(element): Adds an item to the very top.
pop(): Removes and returns the top item. (Throws an error if the stack is empty!)
peek(): Looks at the top item without removing it (useful for checking matches).
isEmpty(): Returns true if there’s nothing in the stack.

Following program is:

If (first char is starting bracket then add to stack)

Else (if it is closing bracket)
1 Then it is not proper if it string is null -error handling
2 If it is ending bracket and stack is empty then it is not valid (means there is no starting bracket yet)
3 If it is closing bracket and if stack is not empty then start popping latest element of stack, if that element is 
not relavant starting bracket then false
ex for 3, if you see } but when you pop stack (which will have latest starting bracket) and if it is not {  then return false

In else, if it goes through all and stack is empty then your return true that your string is valid
Note - way we return true here is stack.isEmpty()
*/

import java.util.Stack;
public class FindOpenCloseBracketsTesla {
public static boolean isValid(String s) {
        // A stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();

        // Loop through every character in the string
        for (char c : s.toCharArray()) {
            
            // If it's an opening bracket, push to stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            // 2. If it's a CLOSING bracket, we need to check the top tray
            else {
                // If we see a closing bracket but the stack is empty, 
                // it means there was never an opening bracket to match it!
                if (stack.isEmpty()) {
                    return false;
                }
                // 'pop' the top tray to see what the last opened bracket was
                char top = stack.pop();

                // 3. Check if the 'lastOpened' matches the current 'closing' bracket
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        // If the stack is empty, all brackets were matched correctly
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Test Cases
        System.out.println(isValid("(){}[]")); // True
        System.out.println(isValid("(("));     // False
    }

}
