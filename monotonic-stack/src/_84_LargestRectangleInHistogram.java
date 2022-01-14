package src;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/maximum-rectangular-area-in-a-histogram-1587115620/1
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * https://www.youtube.com/watch?v=J2X70jj_I1o&ab_channel=AdityaVerma (Best explanation)
 *
 * https://www.youtube.com/watch?v=X0X6G-eWgQ8&ab_channel=takeUforward
 * https://www.youtube.com/watch?v=jC_cWLy7jSI&ab_channel=takeUforward
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/452612/Thinking-Process-for-Stack-Solution
 */
public class _84_LargestRectangleInHistogram {

  public static void main(String[] args) {
    System.out.println(getMaxArea(new long[]{7, 2, 8, 9, 1, 3, 6, 5}, 8));  // 16
    System.out.println(getMaxArea(new long[]{6, 2, 5, 4, 5, 1, 6}, 7));     // 12
  }

  static class Pair {
    long val;
    long pos;
    Pair (final long val, final long pos) {
      this.val = val;
      this.pos = pos;
    }
  }

  public static long getMaxArea(final long[] hist, long n) {
    long[] nsl = findNearestSmallerLeft(hist, hist.length);
    long[] nsr = findNearestSmallerRight(hist, hist.length);

        /*for (int i = 0; i < n; i++) {
            System.out.print(nsl[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(nsr[i] + " ");
        }
        System.out.println();*/

    return findMaxArea(nsl, nsr, hist);
  }

  private static long[] findNearestSmallerLeft(final long[] hist, int n) {
    long[] nsl = new long[n];
    Stack<Pair> stack = new Stack<>();

    for (int i = 0; i < n; i++) {
      long currNum = hist[i];

      while (!stack.isEmpty() && stack.peek().val >= currNum) {
        stack.pop();
      }

      nsl[i] = stack.isEmpty() ? -1 : stack.peek().pos;
      stack.push(new Pair(currNum, i));
    }
    return nsl;
  }

  private static long[] findNearestSmallerRight(final long[] hist, int n) {
    long[] nsr = new long[n];
    Stack<Pair> stack = new Stack<>();

    for (int i = n - 1; i >= 0; i--) {
      long currNum = hist[i];

      while (!stack.isEmpty() && stack.peek().val >= currNum) {
        stack.pop();
      }
      nsr[i] = stack.isEmpty() ? n : stack.peek().pos;
      stack.push(new Pair(currNum, i));
    }
    return nsr;
  }

  private static long findMaxArea(final long[] nsl, final long[] nsr, final long[] hist) {

    int n = hist.length;
    long maxArea = Long.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      long currArea = ((nsr[i] - nsl[i]) - 1) * hist[i];
      maxArea = Math.max(maxArea, currArea);
    }
    return maxArea;
  }

}
