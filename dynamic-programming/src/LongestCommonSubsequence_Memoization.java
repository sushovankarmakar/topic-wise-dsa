package src;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://practice.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1
 * <p>
 * https://www.youtube.com/watch?v=g_hIx4yn9zg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=20
 * <p>
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 */
public class LongestCommonSubsequence_Memoization {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));   // 3
        System.out.println(longestCommonSubsequence("abc", "abc"));     // 3
        System.out.println(longestCommonSubsequence("abc", "def"));     // 0
        System.out.println(longestCommonSubsequence("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));     // 4
    }

    private static int longestCommonSubsequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = -1;
            }
        }

        return lcs(text1, text2, n, m, dp);
    }

    private static int lcs(String text1, String text2, int n, int m, int[][] dp) {

        if (n == 0 || m == 0) {
            return 0;
        }

        if (dp[n][m] != -1) return dp[n][m];

        if (text1.charAt(n - 1) == text2.charAt(m - 1)) {

            return dp[n][m] = 1 + lcs(text1, text2, n - 1, m - 1, dp);

        } else {
            return dp[n][m] = Math.max(lcs(text1, text2, n, m - 1, dp),
                    lcs(text1, text2, n - 1, m, dp));
        }
    }
}
