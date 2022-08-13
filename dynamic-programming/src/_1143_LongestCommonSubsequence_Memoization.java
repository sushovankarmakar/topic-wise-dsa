package src;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://practice.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1
 * <p>
 * https://www.youtube.com/watch?v=g_hIx4yn9zg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=20
 * <p>
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 */
public class _1143_LongestCommonSubsequence_Memoization {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));   // 3
        System.out.println(longestCommonSubsequence("abc", "abc"));     // 3
        System.out.println(longestCommonSubsequence("abc", "def"));     // 0
        System.out.println(longestCommonSubsequence("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));     // 4
    }

    /**
     * Time Complexity  : O(m * n)
     * Space Complexity : O(m * n)
     */
    private static int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return lcs(text1, text2, m, n, dp);
    }

    private static int lcs(String text1, String text2, int m, int n, int[][] dp) {

        if (m == 0 || n == 0) {
            return 0;
        }

        if (dp[m][n] != -1) return dp[m][n];

        if (text1.charAt(m - 1) == text2.charAt(n - 1)) {

            return dp[m][n] = 1 + lcs(text1, text2, m - 1, n - 1, dp);

        } else {
            return dp[m][n] = Math.max(lcs(text1, text2, m, n - 1, dp),
                    lcs(text1, text2, m - 1, n, dp));
        }
    }
}
