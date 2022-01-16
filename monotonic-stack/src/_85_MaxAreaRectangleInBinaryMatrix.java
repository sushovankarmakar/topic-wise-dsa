package src;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/max-rectangle/1/
 * https://leetcode.com/problems/maximal-rectangle/
 *
 * https://www.youtube.com/watch?v=St0Jf_VmG_g&t=2s&ab_channel=AdityaVerma
 */
public class _85_MaxAreaRectangleInBinaryMatrix {

  public int maxArea(int[][] mat, int n, int m) {

    int[] heights = new int[m];
    int maxArea = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {

        if (mat[i][j] == 1) {
          heights[j] += 1;
        } else {
          heights[j] = 0;
        }
      }

      // find max area from each row
      int currMaxArea = getMaxAreaFromRow(heights);
      maxArea = Math.max(maxArea, currMaxArea);
    }
    return maxArea;
  }

  public class Pair {

    int val;
    int pos;

    Pair(final int val, final int pos) {
      this.val = val;
      this.pos = pos;
    }
  }

  private int getMaxAreaFromRow(int[] heights) {

    int[] nsl = getNearestSmallerLeft(heights);
    int[] nsr = getNearestSmallerRight(heights);

    return getMaxArea(heights, nsl, nsr);
  }

  private int[] getNearestSmallerLeft(int[] heights) {

    int len = heights.length;
    int[] nsl = new int[len];
    Stack<Pair> stack = new Stack<>();

    for (int i = 0; i < len; i++) {

      int currHeight = heights[i];

      while (!stack.isEmpty() && stack.peek().val >= currHeight) {
        stack.pop();
      }

      nsl[i] = stack.isEmpty() ? -1 : stack.peek().pos;
      stack.push(new Pair(currHeight, i));
    }

    return nsl;
  }

  private int[] getNearestSmallerRight(int[] heights) {

    int len = heights.length;
    int[] nsr = new int[len];
    Stack<Pair> stack = new Stack<>();

    for (int i = len - 1; i >= 0; i--) {

      int currHeight = heights[i];

      while (!stack.isEmpty() && stack.peek().val >= currHeight) {
        stack.pop();
      }

      nsr[i] = stack.isEmpty() ? len : stack.peek().pos;
      stack.push(new Pair(currHeight, i));
    }

    return nsr;
  }

  private int getMaxArea(int[] heights, int[] nsl, int[] nsr) {

    int len = heights.length;
    int maxArea = Integer.MIN_VALUE;

    for (int i = 0; i < len; i++) {

      int width = ((nsr[i] - nsl[i]) - 1);
      int currArea = width * heights[i];

      maxArea = Math.max(maxArea, currArea);
    }
    return maxArea;
  }

}
