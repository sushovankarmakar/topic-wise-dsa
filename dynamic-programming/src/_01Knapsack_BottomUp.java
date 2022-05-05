package src;

/**
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1/
 * <p>
 * https://www.youtube.com/watch?v=ntCGbPMeqgg&t=113s&ab_channel=AdityaVerma
 */

/**
 * the recursive solution is top-down approach as we started at the top and kept making recursive calls
 * until we reached the base case and then started returning values
 */
/**
 *  this tabulation approach is not top-down but bottom-up.
 *  It is called  bottom-up, because we start the solution from base case (initialize matrix using base case)
 *  and build the solution to the top (t[n][w] in this case).
 */
public class _01Knapsack_BottomUp {

    public static void main(String[] args) {

        int[] val = new int[]{60, 100, 120};
        int[] wt = new int[]{10, 20, 30};
        int W = 50;
        int n = val.length;
        System.out.println(knapsack_bottomUp(W, wt, val, n));  // 220
    }

    static int knapsack_bottomUp(int W, int[] wt, int[] val, int n) {

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (i == 0 || j == 0) { // base condition or initialization
                    dp[i][j] = 0;
                    continue;
                }

                if (wt[i - 1] <= j) {

                    int profitWithInclusion = val[i - 1] + dp[i - 1][j - wt[i - 1]];
                    int profitWithExclusion = dp[i - 1][j];

                    dp[i][j] = Math.max(profitWithInclusion, profitWithExclusion);

                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][W];
    }
}
