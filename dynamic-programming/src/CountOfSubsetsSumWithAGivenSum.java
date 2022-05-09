package src;

/**
 * https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/
 *
 * https://www.youtube.com/watch?v=F7wqWbqYn9g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=10&ab_channel=AdityaVerma
 */
public class CountOfSubsetsSumWithAGivenSum {

    public static void main(String[] args) {
        System.out.println(perfectSum(new int[]{2, 3, 5, 6, 8, 10}, 6, 10));    // 3
        System.out.println(perfectSum(new int[]{1, 2, 3, 4, 5}, 5, 10));        // 3
    }

    private static int perfectSum(int[] arr, int n, int sum) {

        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {

                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (arr[i - 1] <= j) {

                    int includingCurrVal = dp[i - 1][j - arr[i - 1]];
                    int excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = includingCurrVal + excludingCurrVal;

                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }
}
