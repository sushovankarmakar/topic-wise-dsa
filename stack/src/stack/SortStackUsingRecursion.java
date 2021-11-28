package stack;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/sort-a-stack/1
 * <p>
 * <p>
 * https://www.youtube.com/watch?v=AZ4jEY_JAVc&ab_channel=AdityaVerma (Sort an array) <- Pre-requisite
 * https://www.youtube.com/watch?v=MOGBRkkOhkY&ab_channel=AdityaVerma (Sort a stack)
 */

public class SortStackUsingRecursion {

  /**
   * RECURSIVE METHOD
   */
  public Stack<Integer> sortRecursively(Stack<Integer> stack) {
    if (stack.size() == 1) {
      return stack;
    }

    int topElement = stack.pop();
    stack = sortRecursively(stack);
    return insert(stack, topElement);
  }

  private Stack<Integer> insert(Stack<Integer> sortedStack,
      int valueToBeInserted) {

    if (sortedStack.isEmpty() || sortedStack.peek() <= valueToBeInserted) {
      sortedStack.push(valueToBeInserted);
      return sortedStack;
    }

    int topElement = sortedStack.pop();
    sortedStack = insert(sortedStack, valueToBeInserted);
    sortedStack.push(topElement);

    return sortedStack;
  }


  /**
   * ITERATIVE METHOD
   */
  public Stack<Integer> sortIteratively(Stack<Integer> s) {
    if (s == null || s.isEmpty()) {
      return s;
    }
    Stack<Integer> tempStack = new Stack<>();

    while (!s.isEmpty()) {
      int topElement = s.pop();
      while (!tempStack.isEmpty() && topElement < tempStack.peek()) {
        s.push(tempStack.pop());
      }
      tempStack.push(topElement);
    }
    return tempStack;
  }

}
