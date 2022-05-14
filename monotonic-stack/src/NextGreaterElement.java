package src;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/next-larger-element-1587115620/1
 *
 * https://www.youtube.com/watch?v=Du881K7Jtk8&ab_channel=takeUforward (I followed this approach)
 * https://www.youtube.com/watch?v=NXOOYYwpbg4&ab_channel=AdityaVerma (Good explanation)
 */
public class NextGreaterElement {

  public static void main(String[] args) {
    long[] elements = nextLargerElement(new long[]{4, 12, 5, 3, 1, 2, 5, 3, 1, 2, 4, 6});
    for (long element : elements) {
      System.out.print(element + " ");  // 12 -1 6 5 2 5 6 4 2 4 6 -1
    }
    System.out.println();

    long[] elements3 = nextLargerElement(new long[]{3, 8, 4, 1, 2});
    for (long element : elements3) {
      System.out.print(element + " ");  // 8 -1 -1 2 -1
    }
    System.out.println();
  }

  /**
   * Use stack and store values in stack in sorted order.
   */
  public static long[] nextLargerElement(long[] inputs) {

    int n = inputs.length;
    long[] outputs = new long[n];
    Stack<Long> stack = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {
      long currNum = inputs[i];

      while (!stack.isEmpty() && currNum >= stack.peek()) {
        stack.pop();
      }

      outputs[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(currNum);
    }
    return outputs;
  }

  /**
   * nearest greater element in right :
   *
   * traverse from right side and stack stores values from top in increasing order.
   */

  /**
   * nearest smaller element in right :
   *
   * traverse from right side and stack stores values from top in decreasing order.
   */

  /**
   * nearest greater element in left :
   *
   * traverse from left side and stack stores values from top in increasing order.
   */

  /**
   * nearest smaller element in left :
   *
   * traverse from left side and stack stores values from top in decreasing order.
   */

}
