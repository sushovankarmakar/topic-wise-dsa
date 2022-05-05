package src;

/**
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1/
 * <p>
 * https://www.youtube.com/watch?v=fJbIuhs24zQ&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=4&ab_channel=AdityaVerma
 */
public class _01Knapsack_Recursive_Memoization {

    public static void main(String[] args) {

        int[] val = new int[]{60, 100, 120};
        int[] wt = new int[]{10, 20, 30};
        int W = 50;
        int n = val.length;
        System.out.println(knapsack_memoization(W, wt, val, n)); // 220
    }

    /**
     * Complexity Analysis:
     * <p>
     * Time Complexity: O(N * W).
     * where ‘N’ is the number of weight element and ‘W’ is capacity.
     * As for every weight element we traverse through all weight capacities 1<=w<=W.
     *
     * <p>
     * Auxiliary Space: O(N * W)
     */
    static int knapsack_memoization(int W, int[] wt, int[] val, int n) {

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = -1;
            }
        }

        return knapsack(W, wt, val, n, dp);
    }

    static int knapsack(int W, int[] wt, int[] val, int n, int[][] dp) {

        if (n == 0 || W == 0) {
            return 0;
        }

        if (dp[n][W] != -1) {
            return dp[n][W];
        }

        if (wt[n - 1] <= W) {

            int profitWithInclusion = val[n - 1] + knapsack(W - wt[n - 1], wt, val, n - 1, dp);
            int profitWithExclusion = knapsack(W, wt, val, n - 1, dp);

            return dp[n][W] = Math.max(profitWithInclusion, profitWithExclusion); // store value before return
        } else {
            return dp[n][W] = knapsack(W, wt, val, n - 1, dp); // store value before return
        }
    }
}
