package stack;

import java.util.Stack;

/*
 * https://www.youtube.com/watch?v=8YXQ68oHjAs&t=208s&ab_channel=AdityaVerma
 */

public class ReverseStackUsingRecursion {

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);
    System.out.println(stack);  // 1, 2, ,3, 4, 5
    System.out.println(reverseStack(stack)); // 5, 4, 3, 2, 1
  }

  private static Stack<Integer> reverseStack(Stack<Integer> stack) {

    if (stack.size() == 1) {
      return stack;
    }

    int topElement = stack.pop();
    stack = reverseStack(stack);
    insertElement(stack, topElement);
    return stack;
  }

  private static void insertElement(Stack<Integer> stack, int valueToBeInserted) {
    if (stack.isEmpty()) {
      stack.push(valueToBeInserted);
      return;
    }

    int topElement = stack.pop();
    insertElement(stack, valueToBeInserted);
    stack.push(topElement);
  }
}
