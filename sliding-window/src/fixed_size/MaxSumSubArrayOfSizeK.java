package fixed_size;

/**
 * https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
 *
 * https://www.youtube.com/watch?v=KtpqeN0Goro&ab_channel=AdityaVerma
 */

public class MaxSumSubArrayOfSizeK {

  public static void main(String[] args) {
    int[] arr = new int[]{100, 200, 300, 400};
    System.out.println(getMaxSumSubarray(arr, 2));
  }

  private static int getMaxSumSubarray(int[] arr, int k) {
    int n = arr.length;
    int maxSum = Integer.MIN_VALUE;

    int left = 0, right = 0;
    int currSum = 0;

    for (; right < n; right++) {
      currSum += arr[right];

      if ((right - left + 1) == k) {  // right - left + 1 <- is the window size.
        maxSum = Math.max(maxSum, currSum);

        currSum -= arr[left];
        left++;
      }
    }
    return maxSum;
  }
}
