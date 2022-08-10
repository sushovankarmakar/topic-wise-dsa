package src;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-sum-partition3317/1/ (No negative values)
 * https://leetcode.com/problems/partition-array-into-two-arrays-to-minimize-sum-difference/ (With negative values)
 * https://www.geeksforgeeks.org/partition-a-set-into-two-subsets-such-that-the-difference-of-subset-sums-is-minimum/
 * <p>
 * https://www.youtube.com/watch?v=-GtpxG6l_Mc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=10&ab_channel=AdityaVerma
 */
public class MinimumSubsetSumDifference {

    public static void main(String[] args) {
        System.out.println(minDifference(new int[]{1, 6, 11, 5}, 4));   // 1
        System.out.println(minDifference(new int[]{1, 4}, 2));          // 3
        System.out.println(minDifference(new int[]{1, 2, 7}, 3));       // 4
        System.out.println(minDifference(new int[]{3, 9, 7, 3}, 4));       // 2
    }

    public static int minDifference(int[] arr, int n) {
        int sum = totalSum(arr, n);
        return subsetSum(arr, n, sum);
    }

    private static int totalSum(int[] arr, int n) {
        // Calculate sum of all elements
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }

    // Time Complexity = O(n * sum) where n is the number of elements and sum is the sum of all elements.
    // Space Complexity = O(n * sum)
    private static int subsetSum(int[] arr, int n, int sum) {

        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Fill the partition table in bottom up manner
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {

                if (j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = false;
                    continue;
                }

                if (arr[i - 1] <= j) {
                    boolean includingCurrVal = dp[i - 1][j - arr[i - 1]];
                    boolean excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = includingCurrVal || excludingCurrVal;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return findMinDiff(dp, n, sum);
    }

    private static int findMinDiff(boolean[][] dp, int n, int sum) {

        int min = Integer.MAX_VALUE;    // Initialize difference of two sums.

        // Find the largest j such that dp[n][j]
        // is true where j loops from sum/2 t0 0
        for (int j = sum / 2; j >= 0; j--) {

            if (dp[n][j]) {
                min = sum - (2 * j);
                break;
            }
        }

        return min;
    }

}
