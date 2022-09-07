package src.kadane;

/**
 * https://practice.geeksforgeeks.org/problems/kadanes-algorithm-1587115620/1
 * https://leetcode.com/problems/maximum-subarray/
 * <p>
 * https://www.youtube.com/watch?v=w_KEocd__20 (Striver : Kadane's Algorithm)
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/DP_ContiguousSubArrayMaxSumKadaneAlgo.java
 */
public class _53_MaximumSubArraySum {

    public int maxSubArray(int[] nums) {

        int currSum = 0;
        int maxSum = nums[0];

        for (int num : nums) {

            currSum += num;

            if (currSum > maxSum) {
                maxSum = currSum;
            }

            if (currSum < 0) {
                currSum = 0;
            }
        }
        return maxSum;
    }
}
