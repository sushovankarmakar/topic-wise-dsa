package fixed_size;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://workat.tech/problem-solving/practice/sliding-window-maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 * https://practice.geeksforgeeks.org/problems/maximum-of-all-subarrays-of-size-k3101/1
 * https://practice.geeksforgeeks.org/problems/deee0e8cf9910e7219f663c18d6d640ea0b87f87/1
 * <p>
 * https://www.callicoder.com/sliding-window-maximum/
 * <p>
 * https://www.youtube.com/watch?v=xFJXtB5vSmM&ab_channel=AdityaVerma
 */
public class _239_MaximumValueSlidingWindowSizeK {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 2, 3, 1, 4, 5, 2, 3, 6}, 3))); // 3 3 4 5 5 5 6

        // queue.peekLast() <= currVal won't work. below input is the example.
        System.out.println(Arrays.toString(maxSlidingWindow_1(new int[]{-7, -8, 7, 5, 7, 1, 6, 0}, 4))); // 7 7 7 7 7
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

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

        // You want to ensure the deque window only has STRICTLY decreasing elements. That way, the leftmost element is always the largest.
        Deque<Integer> queue = new ArrayDeque<>();

        int left = 0, right = 0;
        for (; right < n; right++) {

            int currVal = nums[right];
            /**
             * 1st step :
             * before placing the element,
             * remove all the elements which are smaller than current element and in front of current element
             */
            while (!queue.isEmpty() && queue.peekLast() < currVal) {  // IMP : peek from last, not from front :  queue.peekLast() <= currVal won't work.
                queue.pollLast();   // IMP : poll from last, not from front.
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

    private static int[] maxSlidingWindow_1(int[] nums, int k) {

        int n = nums.length;

        int[] maxValues = new int[n - k + 1];
        Deque<Integer> queue = new LinkedList<>();

        int left = 0, right = 0;
        int window = 0;
        int idx = 0;

        while (right < n) {

            int valRight = nums[right];
            while (!queue.isEmpty() && queue.peekLast() < valRight) { // queue.peekLast() <= currVal won't work.
                queue.pollLast();
            }
            queue.offer(valRight);

            window = right - left + 1;

            if (window < k) {

                right++;

            } else if (window == k) {

                int maxVal = queue.peekFirst();
                maxValues[idx++] = maxVal;

                if (maxVal == nums[left]) {
                    queue.pollFirst();
                }

                left++;
                right++;
            }
        }
        return maxValues;
    }

}
