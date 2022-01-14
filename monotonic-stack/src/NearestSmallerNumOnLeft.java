package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/smallest-number-on-left3403/1/
 *
 * https://www.geeksforgeeks.org/find-the-nearest-smaller-numbers-on-left-side-in-an-array/
 * https://www.youtube.com/watch?v=85LWui3FlVk&ab_channel=AdityaVerma
 */
public class NearestSmallerNumOnLeft {

  public static void main(String[] args) {
    List<Integer> values = nearestSmallerNumOnLeft(new int[]{1, 5, 0, 3, 4, 5}, 6);

    for (int val : values) {
      System.out.print(val + " ");  // -1, 1, -1, 0, 3, 4
    }
    System.out.println();
  }

  private static List<Integer> nearestSmallerNumOnLeft(int[] a, int n) {

    List<Integer> smallestNumOnLeft = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < n; i++) {

      int num = a[i];

      // pop until stack's top is greater than num
      while (!stack.isEmpty() && stack.peek() >= num) {
        stack.pop();
      }

      smallestNumOnLeft.add(stack.isEmpty() ? -1 : stack.peek());
      stack.push(num);
    }
    return smallestNumOnLeft;
  }

}
