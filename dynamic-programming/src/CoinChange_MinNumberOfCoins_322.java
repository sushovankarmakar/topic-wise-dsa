package src;

/**
 * https://www.youtube.com/watch?v=v4Qmbc3i8Wg&ab_channel=AndreyGrehov
 * https://github.com/andreygrehov/dp/tree/master/lecture16
 * <p>
 * https://leetcode.com/problems/coin-change/
 * https://practice.geeksforgeeks.org/problems/number-of-coins1824/1
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/DP_MinNumOfCoinChange.java
 */
public class CoinChange_MinNumberOfCoins_322 {

    public static void main(String[] args) {
        int[] denominations1 = {1, 3, 5};
        int change1 = 8;
        System.out.println(count(denominations1, change1)); // 2

        int[] denominations2 = {1, 3, 5};
        int change2 = 29;
        System.out.println(count(denominations2, change2)); // 7

        int[] denominations3 = {15, 4, 3};
        int change3 = 56;
        System.out.println(count(denominations3, change3)); // 6

        int[] denominations4 = {2, 3, 5};
        int change4 = 1;
        System.out.println(count(denominations4, change4)); // -1
    }

    private static int count(int[] denominations, int change) {
        int[] dp = new int[change + 1];
        dp[0] = 0;

        for (int i = 1; i <= change; i++) {

            dp[i] = Integer.MAX_VALUE;

            /*if (i >= 1) {
                dp[i] = Math.min(dp[i], dp[i - 1]);
            }

            if (i >= 3) {
                dp[i] = Math.min(dp[i], dp[i - 3]);
            }

            if (i >= 5) {
                dp[i] = Math.min(dp[i], dp[i - 5]);
            }*/

            for (int denomination : denominations) {
                if (i >= denomination && dp[i - denomination] != -1) {
                    dp[i] = Math.min(dp[i], dp[i - denomination]);
                }
            }

            // at this point, if dp[i] is still MAX_VAL,
            // then it is not possible to make given change with given denominations.
            // mark this situation as -1.

            dp[i] = (dp[i] == Integer.MAX_VALUE) ? -1 : dp[i] + 1;
        }

        return dp[change];
    }


}
