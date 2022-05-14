package src;

/**
 * https://practice.geeksforgeeks.org/problems/rod-cutting0840/1/
 * https://www.youtube.com/watch?v=SZqAQLjDsag&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=15&t=18s&ab_channel=AdityaVerma
 *
 * https://discuss.geeksforgeeks.org/comment/2f21cf3ee4d8dae7905f8b9c8f5e3078
 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 */
public class RodCutting_UnboundedKnapsack {

    public static void main(String[] args) {

        // The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5 + 17 = 22.
        System.out.println(cutRod(new int[]{1, 5, 8, 9, 10, 17, 17, 20}, 8));

        // The maximum obtainable value is 24 by cutting the rod into 8 pieces of length 1, i.e, 8 * 3 = 24.
        System.out.println(cutRod(new int[]{3, 5, 8, 9, 10, 17, 17, 20}, 8));
    }

    private static int cutRod(int[] price, int n) {
        int[] length = new int[n];
        for (int i = 0; i < n; i++) {
            length[i] = i + 1;
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (length[i - 1] <= j) {

                    int inclusiveCurrVal = price[i - 1] + dp[i][j - length[i - 1]];
                    int exclusiveCurrVal = dp[i - 1][j];

                    dp[i][j] = Math.max(inclusiveCurrVal, exclusiveCurrVal);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][n];
    }

}
