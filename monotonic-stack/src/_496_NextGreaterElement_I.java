package src;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * https://leetcode.com/problems/next-greater-element-i/
 *
 * https://www.youtube.com/watch?v=aiB9r8oeVp4&ab_channel=Pepcoding (Next Greater Element I | Leetcode 496)
 */
public class _496_NextGreaterElement_I {

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {

    int len1 = nums1.length;
    int len2 = nums2.length;

    int lastIndex = len2 - 1;
    Map<Integer, Integer> nextGreaterElementMap = new HashMap<>();

    Deque<Integer> stack = new LinkedList<>();

    for (int i = lastIndex; i >= 0; i--) {

      int currNum = nums2[i];

      while (!stack.isEmpty() && currNum > stack.peek()) {
        stack.pop();
      }

      int nextGreaterElement = stack.isEmpty() ? -1 : stack.peek();

      nextGreaterElementMap.put(currNum, nextGreaterElement);
      stack.push(currNum);
    }

    int[] outputs = new int[len1];

    for (int i = 0; i < len1; i++) {
      int currNum = nums1[i];
      outputs[i] = nextGreaterElementMap.get(currNum);
    }

    return outputs;
  }

}
