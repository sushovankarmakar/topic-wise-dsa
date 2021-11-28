package stack;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/delete-middle-element-of-a-stack/1
 * <p>
 * https://www.youtube.com/watch?v=oCcUNRMl7dA&ab_channel=AdityaVerma
 */

public class DeleteMidElementOfStack {

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);

    deleteMid_withNoExtraSpaces(stack, 5);
    System.out.println(stack);
  }

  public static void deleteMid_withNoExtraSpaces(Stack<Integer> stack, int sizeOfStack) {
    int midIndex = ((sizeOfStack / 2) + (sizeOfStack % 2));
    deleteMid(stack, midIndex);
  }

  /*
   * Time complexity : O(n / 2) = O(n)
   * Space complexity : O(1)
   */

  /*
   * Base condition :
   * once the stack size and middle index is same, then we are currently standing at middle element. Delete that.
   *
   * Induction step :
   * 1. pop and store the top element for now.
   * 2. visit the smaller stack in recursive call.
   * 3. after visit, push back that top element again.
   */
  private static void deleteMid(Stack<Integer> stack, int midIndex) {

    // Base condition
    if (stack.size() == midIndex) {
      stack.pop();
      return;
    }

    // Induction step
    int topElement = stack.pop();
    deleteMid(stack, midIndex);
    stack.push(topElement);
  }


  //Function to delete middle element of a stack.
  public void deleteMid_withExtraSpace(Stack<Integer> stack, int sizeOfStack) {

    Stack<Integer> temp = new Stack<>();

    // if sizeOfStack is 5 (odd  num) then, halfSize = 2 + 1 = 3
    // if sizeOfStack is 4 (even num) then, halfSize = 2 + 0 = 2
    int halfSize = ((sizeOfStack / 2) + (sizeOfStack % 2));   // IMPORTANT

    while (stack.size() > halfSize) {
      temp.push(stack.pop());
    }

    stack.pop();  // pop out the middle element.

    while (!temp.isEmpty()) {
      stack.push(temp.pop());
    }
  }

}
