package src;

/**
 * https://www.youtube.com/watch?v=nbh8kfCJcb0&ab_channel=AndreyGrehov
 * https://github.com/andreygrehov/dp/tree/master/lecture14
 */
/*
Problem:
	Coin change
	Given an unlimited supply of coins of given denominations,
	find the total number of ways to make a change of size n, by
	using an even number of coins.
	// 1, 3, 5, 10  - denominations

	transition functions :
	f[i][0] = f[i - 1][1] + f[i - 3][1] + f[i - 5][1] + f[i - 10][1]
	f[i][1] = f[i - 1][0] + f[i - 3][0] + f[i - 5][0] + f[i - 10][0]
*/
public class CoinChange_EvenNumberOfCoins {

    public static void main(String[] args) {
        int[] denominations1 = {1, 3, 5, 10};
        int change1 = 4;
        System.out.println(count(denominations1, change1)); // 3
        // 0 [0, 1]
        // 1 [1, 0]
        // 2 [0, 1]
        // 3 [2, 0]
        // 4 [0, 3]

        int[] denominations2 = {1, 3, 5, 10};
        int change2 = 6;
        System.out.println(count(denominations2, change2)); // 8
        // 0 [0, 1]
        // 1 [1, 0]
        // 2 [0, 1]
        // 3 [2, 0]
        // 4 [0, 3]
        // 5 [5, 0]
        // 6 [0, 8]
    }

    private static int count(int[] denominations, int change) {
        int[][] dp = new int[change + 1][2];    // 0th column represents odd case, 1st column represents even case.
        dp[0][0] = 0;
        dp[0][1] = 1;

        for (int i = 1; i <= change; i++) {

            // dp[i][0] = dp[i - 1][1] + dp[i - 3][1] + dp[i - 5][1] + dp[i - 10][1];   // odd case
            // dp[i][1] = dp[i - 1][0] + dp[i - 3][0] + dp[i - 5][0] + dp[i - 10][0];   // even case

            for (int denomination : denominations) {

                if (i >= denomination) {
                    dp[i][0] += dp[i - denomination][1];
                    dp[i][1] += dp[i - denomination][0];
                }
            }
        }
        return dp[change][1];
    }
}
