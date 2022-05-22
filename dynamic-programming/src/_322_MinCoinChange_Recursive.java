package src;

/**
 * https://practice.geeksforgeeks.org/problems/min-coin5549/1
 * https://practice.geeksforgeeks.org/problems/number-of-coins1824/1/
 * <p>
 * https://www.youtube.com/watch?v=I-l6PBeERuc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=16&t=15s&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=rMfOgY07TFs&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=17&ab_channel=AdityaVerma
 * <p>
 * https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 */
public class _322_MinCoinChange_Recursive {

    public static void main(String[] args) {
        System.out.println(minCoin_Recursive(new int[]{1, 2, 5}, 11));    // 3
        System.out.println(minCoin_Recursive(new int[]{2, 6}, 7));        // -1
        System.out.println(minCoin_Recursive(new int[]{25, 10, 5}, 30));  // 2
        System.out.println(minCoin_Recursive(new int[]{9, 6, 5, 1}, 11)); // 2
        System.out.println(minCoin_Recursive(new int[]{2}, 3)); // -1
        System.out.println(minCoin_Recursive(new int[]{}, 0)); // 0
        System.out.println(minCoin_Recursive(new int[]{411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422}, 9864)); // 24
    }

    // DONE BY MYSELF - similar to unbounded knapsack code and choice diagram
    private static int minCoin_Recursive(int[] coins, int amount) {

        int minNumOfCoins = minCoin(coins, coins.length, amount);

        return minNumOfCoins == (Integer.MAX_VALUE - 1) ? -1 : minNumOfCoins;
    }

    private static int minCoin(int[] coins, int len, int amount) {

        if (amount == 0) {
            return 0;
        }

        if (len == 0) {
            return Integer.MAX_VALUE - 1;
        }

        /*if (dp[len][amount] != 0) { // to make this memoized, uncomment this part
            return dp[len][amount];
        }*/

        if (coins[len - 1] <= amount) {

            int includingCurrVal = 1 + minCoin(coins, len, amount - coins[len - 1]);
            int excludingCurrVal = minCoin(coins, len - 1, amount);

            return Math.min(includingCurrVal, excludingCurrVal);
        } else {
            return minCoin(coins, len - 1, amount);
        }
    }
}
