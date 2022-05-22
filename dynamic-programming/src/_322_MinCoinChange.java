package src;

/**
 * https://practice.geeksforgeeks.org/problems/min-coin5549/1
 * https://practice.geeksforgeeks.org/problems/number-of-coins1824/1/
 * <p>
 * https://www.youtube.com/watch?v=I-l6PBeERuc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=16&t=15s&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=rMfOgY07TFs&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=17&ab_channel=AdityaVerma
 * <p>
 * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 * https://www.educative.io/edpresso/minimum-coin-change-in-cpp---a-dynamic-programming-question
 * https://www.techiedelight.com/coin-change-making-problem-unlimited-supply-coins/
 * <p>
 * (1D Array Solution)
 * https://github.com/sushovankarmakar/topic-wise-dsa/blob/master/dynamic-programming/src/CoinChange_MinNumberOfCoins_322.java
 */
public class _322_MinCoinChange {

    public static void main(String[] args) {
        System.out.println(minCoin(new int[]{1, 2, 5}, 11));    // 3
        System.out.println(minCoin(new int[]{2, 6}, 7));        // -1
        System.out.println(minCoin(new int[]{25, 10, 5}, 30));  // 2
        System.out.println(minCoin(new int[]{9, 6, 5, 1}, 11)); // 2
        System.out.println(minCoin(new int[]{2}, 3)); // -1
        System.out.println(minCoin(new int[]{1}, 0)); // 0
        System.out.println(minCoin(new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422}, 9864)); // 24
    }

    /**
     * Best thing of this question is you have to initialise DP table with INT_MAX-1 instead of INT_MAX.
     * Reason: if we take INT_MAX as initialisation,
     * then in dp[i][j] = min((dp[i][j-a[i-1]]+1), dp[i-1][j])
     * <p>
     * (dp[i][j-a[i-1]]+1) ==> this part become INT_MIN if case is not valid
     * and min(INT_MIN, INT_MAX) = INT_MIN which affects our answer.
     */
    // similar to unbounded knapsack code and choice diagram
    private static int minCoin(int[] coins, int amount) {

        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];

        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= amount; j++) {

                if (j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = (Integer.MAX_VALUE - 1); // this value represents infinity.
                    continue;
                }

                if (coins[i - 1] <= j) {

                    int includingCurrVal = 1 + dp[i][j - coins[i - 1]];
                    int excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = Math.min(includingCurrVal, excludingCurrVal);
                } else {

                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[len][amount] == (Integer.MAX_VALUE - 1) ? -1 : dp[len][amount];
    }

    /**
     * We can also do this question w/o initializing the second row, as it resembles with unbounded knapsack,
     * we just need to increment the sub - solution by 1 when are including the coin in the knapsack. below is the code -
     * BTW mad respect to you brother for making such gold content.
     * ---------------------------------------------------------------------------------------------------------
     *
     *   int coinChange(vector<int>& coins, int amount) {
     *         int n = coins.size();
     *         int t[n+1][amount+1];
     *
     *         for(int j=0; j <= amount; j++)
     *             t[0][j] = amount+1;
     *         for(int i=0; i <= n; i++)
     *             t[i][0] = 0;
     *
     *         for(int i=1; i <= n; i++){
     *             for(int j=1; j <= amount; j++) {
     *                 if(coins[i-1] <= j)
     *                     t[i][j] = min(**1 + t[i][j-coins[i-1]]**, t[i-1][j]);
     *                 else
     *                     t[i][j] = t[i-1][j];
     *             }
     *         }
     *
     *         return t[n][amount] > amount ? -1 : t[n][amount];
     *     }
     */
}
