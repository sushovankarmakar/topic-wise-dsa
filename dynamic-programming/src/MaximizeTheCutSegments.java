package src;

/**
 * https://practice.geeksforgeeks.org/problems/cutted-segments1642/1
 * https://www.geeksforgeeks.org/maximize-the-number-of-segments-of-length-p-q-and-r/
 * https://codeforces.com/problemset/problem/189/A (Ribbon Cut Problem)
 * <p>
 * https://www.youtube.com/watch?v=I-l6PBeERuc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=16 (Aditya Verma, Similar question)
 * <p>
 * https://www.geeksforgeeks.org/maximize-the-number-of-segments-of-length-p-q-and-r/
 */
public class MaximizeTheCutSegments {

    public static void main(String[] args) {
        // top down memoization
        System.out.println(maximizeCuts_topDownDP(7, 5, 5, 2)); // 2
        System.out.println(maximizeCuts_topDownDP(4, 2, 1, 1)); // 4
        System.out.println(maximizeCuts_topDownDP(5, 5, 3, 2)); // 2
        System.out.println(maximizeCuts_topDownDP(2, 5, 5, 3)); // 0
        System.out.println(maximizeCuts_topDownDP(418, 18, 14, 17)); // 29

        // bottom up
        System.out.println(maximizeCuts_bottomUpDP(2, 5, 5, 3)); // 0
        System.out.println(maximizeCuts_bottomUpDP(418, 18, 14, 17)); // 29
    }

    // TOP DOWN : https://discuss.geeksforgeeks.org/comment/9f75b07c90b5e6b65a11e1893bddeb04
    static int[][] dp = new int[0][0];

    public static int maximizeCuts_topDownDP(int n, int x, int y, int z) {

        int[] cuts = {x, y, z};

        dp = new int[n + 1][4];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 3; j++) {
                dp[i][j] = -1;
            }
        }

        int maxCuts = solve(n, cuts, 3);

        return Math.max(maxCuts, 0); // taking max for such corner cases : n = 2, x = 5, y = 5, z = 3, where output = 0
    }

    private static int solve(int n, int[] cuts, int len) {  // len represents the length of cuts array.

        // because no matter how many we take from empty cuts array, won't be enough,
        // cant take INT_MAX as we are using max function later
        if (len == 0 && n != 0) {
            return Integer.MIN_VALUE;   // it represents infinity
        }

        if (n == 0) {
            return 0;
        }

        if (dp[n][len] != -1) return dp[n][len];

        if (cuts[len - 1] <= n) {

            int include = 1 + solve(n - cuts[len - 1], cuts, len);
            int exclude = solve(n, cuts, len - 1);

            return dp[n][len] = Math.max(include, exclude);
        } else {

            return dp[n][len] = solve(n, cuts, len - 1);
        }
    }

    // BOTTOM UP : https://discuss.geeksforgeeks.org/comment/eadfd324fe588aeff58690e8e87315a2
    public static int maximizeCuts_bottomUpDP(int n, int x, int y, int z) {
        int[] cuts = {x, y, z};
        int[][] dp = new int[n + 1][4];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 3; j++) {

                // because no matter how many we take from empty cuts array, won't be enough,
                // cant take INT_MAX as we are using max function later
                if (j == 0) {
                    dp[i][j] = Integer.MIN_VALUE; // it represents infinity
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (cuts[j - 1] <= i) {

                    int include = 1 + dp[i - cuts[j - 1]][j];
                    int exclude = dp[i][j - 1];

                    dp[i][j] = Math.max(include, exclude);
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return Math.max(dp[n][3], 0);

        /*int[][] t = new int[4][n + 1];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == 0) {
                    t[i][j] = Integer.MIN_VALUE;
                }
                if (j == 0) {
                    t[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (cut[i - 1] <= j) {
                    t[i][j] = Math.max(t[i - 1][j], t[i][j - cut[i - 1]] + 1);
                } else {
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        if (t[3][n] < 0) {
            return 0;
        }
        return t[3][n];*/
    }

}
