package src;

/**
 * https://practice.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
 */
public class _01KnapsackWithDuplicateItems_Unbounded {

    public static void main(String[] args) {
        System.out.println(knapsack(2, 3, new int[]{1, 1}, new int[]{2, 1})); // 3
        System.out.println(knapsack(4, 8, new int[]{1, 4, 5, 7}, new int[]{1, 3, 4, 5})); // 11
    }

    /**
     * this is unbounded knapsack because
     * in question it is mentioned that each item can be taken any number of times.
     */
    private static int knapsack(int N, int W, int[] val, int[] wt) {

        int[][] dp = new int[N + 1][W + 1];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= W; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (wt[i - 1] <= j) {

                    // unbounded knapsack that's why
                    // instead of dp[i - 1][j - wt[i - 1]]
                    // it is dp[i][j - wt[i - 1]]

                    int includingCurrVal = val[i - 1] + dp[i][j - wt[i - 1]];
                    int excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = Math.max(includingCurrVal, excludingCurrVal);

                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[N][W];
    }
}
