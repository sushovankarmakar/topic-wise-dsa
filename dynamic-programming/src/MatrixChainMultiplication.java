package src;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/matrix-chain-multiplication-dp-8/
 * https://practice.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1/
 * <p>
 * https://www.youtube.com/watch?v=D7AFvtnDeMU&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=33&ab_channel=AdityaVerma (Introduction)
 * https://www.youtube.com/watch?v=kMK148J9qEE&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=34&ab_channel=AdityaVerma (Recursive)
 * https://www.youtube.com/watch?v=9uUVFNOT3_Y&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=34&t=19s&ab_channel=AdityaVerma (Memoization)
 */
public class MatrixChainMultiplication {

    public static void main(String[] args) {
        System.out.println(matrixMultiplication(new int[]{40, 20, 30, 10, 30}));    // 26000
        System.out.println(matrixMultiplication(new int[]{10, 30, 5, 60})); // 4500
    }
    private static int matrixMultiplication(int[] arr) {

        int n = arr.length;
        int i = 1;
        int j = n - 1;
        int[][] dp = new int[n + 1][ n + 1];

        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return solve(arr, i, j, dp);
    }
    private static int solve(int[] arr, int i, int j, int[][] dp) {

        if (i >= j) return 0;

        if (dp[i][j] != -1) return dp[i][j];

        int minCost = Integer.MAX_VALUE;

        for (int k = i; k <= j - 1; k++) {
            int cost = solve(arr, i, k, dp) + solve(arr, k + 1, j, dp) + (arr[i - 1] * arr[k] * arr[j]);

            minCost = Math.min(minCost, cost);
        }

        return dp[i][j] = minCost;
    }

}
