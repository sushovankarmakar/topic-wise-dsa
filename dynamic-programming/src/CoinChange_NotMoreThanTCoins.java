package src;

/**
 * https://www.youtube.com/watch?v=fdPcf3m7Ehw&ab_channel=AndreyGrehov
 * https://github.com/andreygrehov/dp/tree/master/lecture13
 */
/*
Problem:
	Coin change
	Given an unlimited supply of coins of given denominations,
	find the total number of ways to make a change of size n, by
	using no more than t coins.

	f(i,t) = f(i - 1, t - 1) + f(i - 2, t - 1) + f(i - 3, t - 1) + f(i - 5, t - 1)
*/
public class CoinChange_NotMoreThanTCoins {

    public static void main(String[] args) {

        int[] denominations1 = {1, 2, 3, 5};
        int change1 = 7;
        int t1 = 3;
        System.out.println(count(denominations1, change1, t1)); // 11

        //    0  1  2  3
        // 0 [1, 1, 1, 1]   - // this is the only different between "exactly t coins" and "no more than t coins".
        // 1 [0, 1, 1, 1]
        // 2 [0, 1, 2, 2]
        // 3 [0, 1, 3, 4]
        // 4 [0, 0, 3, 6]
        // 5 [0, 1, 3, 9]
        // 6 [0, 0, 3, 10]
        // 7 [0, 0, 2, 11]

    }

    private static int count(int[] denominations, int change, int t) {

        int[][] dp = new int[change + 1][t + 1];
        dp[0][0] = 1;

        // this is the only different between "exactly t coins" and "no more than t coins".
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = 1;
        }

        for (int i = 1; i <= change; i++) {
            for (int j = 1; j <= t; j++) {

                //dp[i][j] = dp[i - 1][j - 1] + dp[i - 3][j - 1] + dp[i - 5][j - 1] + dp[i - 10][j - 1];

                for (int denomination : denominations) {

                    if (i >= denomination) {
                        dp[i][j] += dp[i - denomination][j - 1];
                    }
                }
            }
        }

        return dp[change][t];
    }
}
