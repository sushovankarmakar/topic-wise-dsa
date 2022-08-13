package src;

/**
 * https://www.youtube.com/watch?v=g0VjciqYeDU&t=1079s&ab_channel=AndreyGrehov
 * https://github.com/andreygrehov/dp/tree/master/lecture15
 * <p>
 * https://www.youtube.com/watch?v=I4UR2T6Ro3w&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=16&t=53s&ab_channel=AdityaVerma
 * https://practice.geeksforgeeks.org/problems/coin-change2448/1
 * https://leetcode.com/problems/coin-change-2/
 * https://www.techiedelight.com/coin-change-problem-find-total-number-ways-get-denomination-coins/ (Coin change all variations)
 */
/*
Problem:
	Coin change
	Given an unlimited supply of coins of given denominations,
	find the unique number of ways to make a change of size n.
	Denominations:

	coins = [1, 2, 3, 5]

	Transition function:

	i >= 1: f[i][1] = f[i-1][1]
	i >= 2: f[i][2] = f[i-1][1] + f[i-2][2]
	i >= 3: f[i][3] = f[i-1][1] + f[i-2][2] + f[i-3][3]
	i >= 5: f[i][5] = f[i-1][1] + f[i-2][2] + f[i-3][3] + f[i-5][5]
*/
public class CoinChange_UniqueWays_01KnapsackVariation {

    public static void main(String[] args) {
        int[] denominations1 = {1, 2, 3, 5};
        int change1 = 4;
        System.out.println(count_uniqueWays(denominations1, change1)); // 4

        //    1  2  3  5  - denomination
        // -----------------------------
        //    0  1  2  3
        // 0 [1, 1, 1, 1]
        // 1 [1, 1, 1, 1]
        // 2 [1, 2, 2, 2]
        // 3 [1, 2, 3, 3]
        // 4 [1, 3, 4, 4]

        System.out.println(count_uniqueWays(new int[]{1, 2, 3}, 4));
    }

    // here we're asking question :
    // for each denomination, in how many ways to make a change of size n
    /*
     * denomination : [1]
     *
     *    (1)     (1)     (1)
     * 3 ----- 2 ----- 1 ----- 0
     *
     *
     * denomination : [1, 2]
     *
     *     (1)    (1)      (1)
     * / ----- 2 ----- 1 ----- 0
     * 3
     * \ ----- 1 ---- 0
     *    (2)     (1)
     *
     * denomination : [1, 2, 3]
     *
     *    (1)     (1)     (1)
     * / ----- 2 ----- 1 ----- 0
     * |
     * /----- 1 ----- 0
     * | (2)      (1)
     * 3
     * |
     * \----- 0
     *    (3)
     */
    // APPROACH - 2 : this is reverse of CoinChange_OrderOfCoinDoesNotMatter_WithAllPermutation algorithm.
    private static int count_uniqueWays(int[] denominations, int change) {

        // here we're asking question :
        // for each denomination, in how many ways to make a change of size n

        int[] dp = new int[change + 1];
        dp[0] = 1;

        for (int denomination : denominations) {

            for (int i = 1; i <= change; i++) {

                if (i >= denomination) {
                    dp[i] += dp[i - denomination];
                }
            }
        }
        return dp[change];

        // [ 1, 1, 2, 3, 4]
    }

    // APPROACH - 1
    private static int count(int[] denominations, int change) {

        int n = denominations.length;
        int[][] dp = new int[change + 1][n];

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= change; i++) {
            for (int j = 0; j < denominations.length; j++) {
                for (int k = 0; k <= j; k++) {
                    if (i >= denominations[k]) {
                        dp[i][j] += dp[i - denominations[k]][k];
                    }
                }
            }

            /*if (i >= 1) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][0];
                dp[i][2] = dp[i - 1][0];
                dp[i][3] = dp[i - 1][0];
            }
            if (i >= 2) {
                dp[i][1] += dp[i - 2][1];
                dp[i][2] += dp[i - 2][1];
                dp[i][3] += dp[i - 2][1];
            }
            if (i >= 3) {
                dp[i][2] += dp[i - 3][2];
                dp[i][3] += dp[i - 3][2];
            }
            if (i >= 5) {
                dp[i][3] += dp[i - 5][3];
            }*/
        }

        return dp[change][n - 1];
    }

}
