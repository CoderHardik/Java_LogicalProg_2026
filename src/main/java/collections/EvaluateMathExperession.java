/*
Design a function that takes a string representing a mathematical expression and returns its numerical value. 
You should handle basic operators (\(+\), \(-\), \(*\), \(/\)), 
parenthetical grouping, and ensure you respect the standard order of operations without using built-in library functions like eval()."

input and output:
"10 + 2 * 6"Multiplication (\(2 \times 6\)) happens first, then addition.\(22\)

High level logic:
To solve this manually (as expected in an interview), you would typically use two Stacks:Operand Stack: To store the numbers.
Operator Stack: To store signs (\(+\) , \(*\)) and parentheses.
As you iterate through the string, you push numbers to the operand stack. When you encounter an operator, 
you compare its precedence to the top of the operator stack to decide whether to calculate immediately or keep pushin

Details logic:

You are essentially building a "conveyor belt" for numbers and a "waiting room" for operators.
Here is the breakdown of how these three functions work together to solve the "precedence" problem you mentioned.

1. evaluate (The Coordinator)This function moves through the string from left to right.The "Split": 
As you noted, numbers go into the values stack, and symbols (+, *, etc.) go into the ops stack.
The "Wait or Go" Decision: When it sees an operator, it doesn't just push it. 
It looks at what is already at the top of the ops stack. 
If the operator already sitting there is "stronger" (higher precedence), we must solve that one before adding the new one.

2. hasPrecedence (The Judge)This is where the "ordering" logic you asked about lives. 
It compares the current operator from the string with the top operator on the stack.
Your concern about 1st vs 3rd: The stack naturally handles this. 
Because we only compare the current character to the top of the stack, we are always comparing the "next step" with the "last step.
"The Rules:If the existing operator (top of stack) is * or / and the new one is + or -, hasPrecedence returns true. 
This tells evaluate to: "Stop! Solve that multiplication/division first before you add this addition/subtraction to the stack."If they are equal (e.g., both are + and -), 
it also returns true to maintain left-to-right order.

3. applyOp (The Worker)This is the simplest part. 
It’s a "dumb" calculator. It takes two numbers and one sign, does the math, and hands the result back.
Crucial Detail: In a stack (Last-In, First-Out), the first number you pop() was actually the second number in the math expression (the right side).
That’s why the code uses applyOp(op, b, a) where b is the first pop and a is the second.

*/


import java.util.Stack;

public class ExpressionEvaluator {

    public static int evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Integer> values = new Stack<>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // Skip whitespace
            if (tokens[i] == ' ') continue;

            // Current token is a number, push it to stack for numbers
            if (tokens[i] >= '0' && tokens[i] <= '9') {
                StringBuilder sbuf = new StringBuilder();
                // There may be more than one digit in number
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                    sbuf.append(tokens[i++]);
                }
                values.push(Integer.parseInt(sbuf.toString()));
                i--; // Backtrack i as the outer loop will increment it
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(') {
                ops.push(tokens[i]);
            }

            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')') {
                while (ops.peek() != '(') {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.pop();
            }

            // Current token is an operator
            else if (tokens[i] == '+' || tokens[i] == '-' || 
                     tokens[i] == '*' || tokens[i] == '/') {
                // While top of 'ops' has same or higher precedence to current 
                // token, apply operator on top of 'ops' to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining ops to remaining values
        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        // Top of 'values' contains result
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1'
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    // Method to apply an operator 'op' on operands 'a' and 'b'
    public static int applyOp(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(ExpressionEvaluator.evaluate("10 + 2 * 6"));        // Output: 22
        System.out.println(ExpressionEvaluator.evaluate("100 * ( 2 + 12 ) / 14")); // Output: 100
    }
}
