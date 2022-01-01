package src;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 *
 * https://www.youtube.com/watch?v=RkG94TvnUFs&ab_channel=Pepcoding (The BEST resource to understand - I followed this)
 */
public class _503_NextGreaterElement_II {

  /**
   * Very similar to next greater element problem.
   * here we have to do an EXTRA PROCESS before doing the ACTUAL PROCESS.
   */
  public int[] nextGreaterElements(int[] nums) {

    int len = nums.length;
    Deque<Integer> stack = new LinkedList<>();

    // ------------------ EXTRA PROCESS needed because array is cyclic
    for (int i = len - 1; i >= 0; i--) {

      int currNum = nums[i];

      while (!stack.isEmpty() && currNum >= stack.peek()) {
        stack.pop();
      }

      stack.push(currNum);
    }

    // ------------------ ACTUAL PROCESS
    int[] outputs = new int[len];

    for (int i = len - 1; i >= 0; i--) {

      int currNum = nums[i];

      while (!stack.isEmpty() && currNum >= stack.peek()) {
        stack.pop();
      }

      outputs[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(currNum);
    }

    return outputs;
  }
}
