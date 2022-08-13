package src;

/**
 * https://www.youtube.com/watch?v=I4UR2T6Ro3w&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=16&t=53s&ab_channel=AdityaVerma
 * https://practice.geeksforgeeks.org/problems/coin-change2448/1
 * https://leetcode.com/problems/coin-change-2/
 */
public class _518_CoinChange_2 {

    /**
     * coin change : how many ways to we can get the amount using given coins.
     * <p>
     * coins are unlimited.
     */
    public static void main(String[] args) {
        System.out.println(findNumOfWaysToChangeTheAmount(new int[]{1, 2, 5}, 5));      // 4
        System.out.println(findNumOfWaysToChangeTheAmount(new int[]{2}, 3));            // 0
        System.out.println(findNumOfWaysToChangeTheAmount(new int[]{10}, 10));          // 1
        System.out.println(findNumOfWaysToChangeTheAmount(new int[]{1, 2, 3}, 4));      // 4
        System.out.println(findNumOfWaysToChangeTheAmount(new int[]{2, 5, 3, 6}, 10));  // 5
        System.out.println(findNumOfWaysToChangeTheAmount(new int[]{1, 2, 3}, 4));      // 4
    }

    /**
     * Exact similar to unbounded knapsack problem.
     */
    private static int findNumOfWaysToChangeTheAmount(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];

        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= amount; j++) {

                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (coins[i - 1] <= j) {

                    int includingCurrVal = dp[i][j - coins[i - 1]];
                    int excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = includingCurrVal + excludingCurrVal;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[len][amount];
    }

}
