package src;

/**
 * https://www.geeksforgeeks.org/partition-problem-dp-18/
 * https://practice.geeksforgeeks.org/problems/subset-sum-problem2014/1
 * https://leetcode.com/problems/partition-equal-subset-sum/
 *
 * https://www.youtube.com/watch?v=UmMh7xp07kY&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=9&ab_channel=AdityaVerma
 */
public class _416_PartitionEqualSubsetSum {

    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));   // true
        System.out.println(canPartition(new int[]{1, 3, 5}));   // false
        System.out.println(canPartition(new int[]{1, 2, 3, 5}));   // false
    }

    private static boolean canPartition(int[] nums) {

        int N = nums.length;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        boolean isTotalSumEven = (sum % 2 == 0);

        return isTotalSumEven && subsetSum(nums, sum / 2, N);
    }

    // below code is same as https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/
    private static boolean subsetSum(int[] nums, int sum, int N) {

        boolean[][] dp = new boolean[N + 1][sum + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= sum; j++) {

                // j == 0 means first column. so irrespective of number of input array size, if the sum is zero then
                // it is always possible to get a subset (which is empty) whose sum is zero.
                if (j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                // i == 0 means first row. so irrespective of sum, if there is no value in the array then
                // it is always impossible to get a subset whose sum is equal with given sum.
                if (i == 0) {
                    dp[i][j] = false;
                    continue;
                }

                // arr[i - 1] <= j means if the current value is smaller than the sum, then we have a choice to
                // either take that value or don't take that value.
                if (nums[i - 1] <= j) {

                    boolean includingCurrVal = dp[i - 1][j - nums[i - 1]];
                    boolean excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = includingCurrVal || excludingCurrVal;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[N][sum];
    }

}
