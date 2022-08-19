package fixed_size;

/**
 * https://practice.geeksforgeeks.org/problems/max-sum-subarray-of-size-k5313/1
 * <p>
 * https://www.youtube.com/watch?v=KtpqeN0Goro&ab_channel=AdityaVerma (BEST)
 * <p>
 * https://www.callicoder.com/maximum-sum-subarray-of-size-k/ (Good explanation)
 */

public class MaxSumSubArrayOfSizeK {

    public static void main(String[] args) {
        int[] arr = new int[]{100, 200, 300, 400};
        System.out.println(getMaxSumSubArray(arr, 2));  // 700

        System.out.println(getMaxSumSubArray(new int[]{2, 5, 1, 8, 2, 9, 1}, 3));   // 19
        System.out.println(getMaxSumSubArray_2(new int[]{2, 5, 1, 8, 2, 9, 1}, 3));   // 19
    }

    private static int getMaxSumSubArray(int[] arr, int k) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        int left = 0, right = 0;
        int currSum = 0;

        for (; right < n; right++) {

            currSum += arr[right];
            int window = right - left + 1; // right - left + 1 <- is the window size.

            if (window == k) {
                maxSum = Math.max(maxSum, currSum);

                currSum -= arr[left];
                left++;
            }
        }
        return maxSum;
    }

    // this method of code is easy to visualize.
    private static int getMaxSumSubArray_2(int[] arr, int k) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE;

        int left = 0, right = 0;
        int currSum = 0;
        int window;

        while (right < n) {

            currSum += arr[right];
            window = right - left + 1; // right - left + 1 <- is the window size.

            if (window < k) {
                right++;
            } else if (window == k) {
                maxSum = Math.max(maxSum, currSum);

                currSum -= arr[left];

                // now slide the window
                left++;
                right++;
            }
        }
        return maxSum;
    }
}
