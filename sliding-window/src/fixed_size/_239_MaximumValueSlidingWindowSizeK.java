package fixed_size;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://workat.tech/problem-solving/practice/sliding-window-maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 * https://practice.geeksforgeeks.org/problems/maximum-of-all-subarrays-of-size-k3101/1
 *
 * https://www.youtube.com/watch?v=xFJXtB5vSmM&ab_channel=AdityaVerma
 *
 */
public class _239_MaximumValueSlidingWindowSizeK {

  public int[] maxSlidingWindow(int[] nums, int k) {

    // BASIC CHECKS
    if (nums == null || nums.length == 0 || k <= 0) {
      return new int[0];
    }
    final int n = nums.length;
    if (k == 1) {
      return Arrays.copyOf(nums, n);
    }

    // ACTUAL LOGIC
    int[] maxSubArrays = new int[n - k + 1];
    int idx = 0;

    Deque<Integer> queue = new ArrayDeque<>();

    int left = 0, right = 0;
    for (; right < n; right++) {

      int currVal = nums[right];
      //1st step
      while (!queue.isEmpty() && queue.peekLast() < currVal) {
        queue.pollLast();
      }
      queue.offer(currVal);

      if (right - left + 1 == k) {

        //2nd step
        int max = queue.peekFirst();
        maxSubArrays[idx++] = max;

        //3rd step
        if (max == nums[left]) {
          queue.pollFirst();
        }
        left++;
      }
    }

    return maxSubArrays;
  }

}
