package src;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/next-larger-element-1587115620/1
 *
 * https://www.youtube.com/watch?v=Du881K7Jtk8&ab_channel=takeUforward (I followed this approach)
 * https://www.youtube.com/watch?v=NXOOYYwpbg4&ab_channel=AdityaVerma
 */
public class NextGreaterElement {

  /**
   * Use stack and store values in stack in sorted order.
   */

  public static long[] nextLargerElement(long[] inputs, int n) {

    int len = inputs.length;

    long[] outputs = new long[len];
    outputs[len - 1] = -1;

    Stack<Long> stack = new Stack<>();
    stack.push(inputs[len - 1]);

    for (int i = len - 2; i >= 0; i--) {
      long currNum = inputs[i];

      while (!stack.isEmpty() && currNum >= stack.peek()) {
        stack.pop();
      }

      outputs[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(currNum);
    }
    return outputs;
  }

}
