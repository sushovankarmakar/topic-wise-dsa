package fixed_size;

/**
 * https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
 *
 * https://www.youtube.com/watch?v=KtpqeN0Goro&ab_channel=AdityaVerma
 */

public class MaxSumSubarrayOfSizeK {
  private int getMaxSumSubarray(int[] arr, int k) {
    int n = arr.length;
    int maxSum = Integer.MIN_VALUE;

    int left = 0, right = 0;
    int windowSize = right - left + 1;
    int currSum = 0;

    for (; right < n; right++) {
      currSum += arr[right];

      if (windowSize == k) {
        maxSum = Math.max(maxSum, currSum);

        currSum -= arr[left];
        left++;
      }
    }
    return maxSum;
  }
}
