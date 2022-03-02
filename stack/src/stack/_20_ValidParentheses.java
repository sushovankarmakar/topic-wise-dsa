package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * https://practice.geeksforgeeks.org/problems/parenthesis-checker2744/1
 * https://practice.geeksforgeeks.org/problems/valid-expression1025/1/
 *
 * https://leetcode.com/problems/valid-parentheses/discuss/9178/Short-java-solution (BEST SHORT SOLUTION)
 */
public class _20_ValidParentheses {

  public static void main(String[] args) {
    System.out.println(checkValidity2("()"));     // true
    System.out.println(checkValidity2("()[]{}")); // true
    System.out.println(checkValidity2("(]")); // false
    System.out.println(checkValidity2("]")); // false       // special case
    System.out.println(checkValidity2("{([(()]")); // false
    System.out.println(checkValidity2("([)]")); // false
    System.out.println(checkValidity2("[(])")); // false
    System.out.println(checkValidity2("(])")); // false   // special case
  }

  /**
   * CONCISE solution.
   * https://leetcode.com/problems/valid-parentheses/discuss/9178/Short-java-solution
   *
   * The basic idea is
   * 1. to push the right parentheses ')', ']', or '}' into the stack each time when we encounter left ones.
   * 2. And if a right bracket appears in the string, we need check if the stack is empty
   * 3. and also whether the top element is the same with that right bracket.
   * 4. If not, the string is not a valid one.
   * 5. At last, we also need check if the stack is empty.
   */
  private static boolean checkValidity2(String str) {
    Stack<Character> stack = new Stack<>();

    for (char ch : str.toCharArray()) {

      if (ch == '(') {
        stack.push(')');
      } else if (ch == '[') {
        stack.push(']');
      } else if (ch == '{') {
        stack.push('}');
      } else if (stack.empty() || stack.pop() != ch) {
        return false;
      }
    }
    return stack.isEmpty();
  }

  /**
   * INTUITIVE solution.
   */
  /**
   * https://www.youtube.com/watch?v=wkDfsKijrZ8&ab_channel=takeUforward
   * https://github.com/striver79/SDESheet/blob/main/balancedParanthesisJava
   *
   * Time complexity : O(N)
   * Space complexity : O(N)
   */
  private static boolean checkValidity1(String str) {

    Stack<Character> stack = new Stack<>();

    for (char ch : str.toCharArray()) {

      if (ch == '(' || ch == '[' || ch == '{' ) {
        stack.push(ch);
      } else {
        if (stack.isEmpty()) return false;    // example : "]"  // special case

        if ((ch == ')' && stack.peek() == '(') ||
            (ch == '}' && stack.peek() == '{') ||
            (ch == ']' && stack.peek() == '[')) {

          stack.pop();
        } else {
          return false; // example : "(])"    // special case
        }
      }
    }

    return stack.isEmpty();
  }



}
