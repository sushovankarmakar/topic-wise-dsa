package src.kadane;

/**
 * https://practice.geeksforgeeks.org/problems/smallest-sum-contiguous-subarray/1
 * https://www.geeksforgeeks.org/smallest-sum-contiguous-subarray/
 * <p>
 * https://www.youtube.com/watch?v=w_KEocd__20 (Striver : Kadane's Algorithm) (Exactly same logic as Kadane's algo. There it was find max, here it is find min)
 */
public class MinimumSubArraySum {

    static int minimumSumSubArray(int[] nums) {

        int currSum = 0;
        int minSum = nums[0];

        for (int num : nums) {

            currSum += num;

            if (currSum < minSum) {
                minSum = currSum;
            }

            if (currSum > 0) {
                currSum = 0;
            }
        }
        return minSum;
    }
}
