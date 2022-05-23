package src;

/**
 * https://leetcode.com/problems/longest-palindromic-subsequence/
 * https://practice.geeksforgeeks.org/problems/longest-palindromic-subsequence-1612327878/1/
 * <p>
 * https://www.youtube.com/watch?v=wuOOOATz_IA&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=27&ab_channel=AdityaVerma
 */
public class _516_LongestPalindromicSubsequence_LCS_Variation {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubsequence("bbbab"));  // 4
        System.out.println(longestPalindromeSubsequence("cbbd"));   // 2
        System.out.println(longestPalindromeSubsequence("bbabcbcab")); // 7
        System.out.println(longestPalindromeSubsequence("abcd"));   // 1
        System.out.println(longestPalindromeSubsequence("abbcba"));   // 5
    }

    private static int longestPalindromeSubsequence(String input) {

        String reversedInput = new StringBuilder(input).reverse().toString();

        int m = input.length();
        int n = reversedInput.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (input.charAt(i - 1) == reversedInput.charAt(j - 1)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }
}
