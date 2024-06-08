package src.stack;

import java.util.Stack;

/**
 * This problem was asked by Facebook.
 * <p>
 * Given a string consisting of parentheses, single digits, and positive and negative signs,
 * convert the string into a mathematical expression to obtain the answer.
 * <p>
 * Don't use eval or a similar built-in parser.
 * <p>
 * For example, given '-1 + (2 + 3)', you should return 4
 */

public class _20240604_1416_ExpressionEvaluator {

    // https://chatgpt.com/share/dacd0aff-5d17-415c-bf21-5541c84e995b

    /**
     * Use a Stack for Evaluation :
     * --------------------------
     * Use stacks to manage numbers and operators as you parse the expression.
     * This helps in handling the precedence of operations and parentheses.
     * <p>
     * Handle Parentheses :
     * ------------------
     * When encountering an opening parenthesis (, push the current result and the sign onto the stack.
     * When encountering a closing parenthesis ), pop from the stack and combine the results.
     */
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Integer> opStack = new Stack<>();

        int num = 0;
        int sign = 1;  // 1 means positive, -1 means negative
        int result = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = 0;
                // There could be more than one digit in the number
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;
                result += sign * num;
            } else if (c == '+') {
                sign = 1;
            } else if (c == '-') {
                sign = -1;
            } else if (c == '(') {
                // Push the result and sign onto the stack
                numStack.push(result);
                opStack.push(sign);
                // Reset result and sign for new sub-expression
                result = 0;
                sign = 1;
            } else if (c == ')') {
                // Calculate the result for the current sub-expression
                result = numStack.pop() + opStack.pop() * result;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        _20240604_1416_ExpressionEvaluator evaluator = new _20240604_1416_ExpressionEvaluator();
        String expression = "-1 + (2 + 3)";
        System.out.println(evaluator.calculate(expression));  // Output: 4
    }
}
