package src;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions4610/1/
 * https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/
 * <p>
 * https://www.youtube.com/watch?v=CFwCCNbRuLY&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=27&ab_channel=AdityaVerma
 */
public class _1312_MinInsertionStepsToMakeStringPalindrome {

    public static void main(String[] args) {
        System.out.println(minInsertions("zzazz")); // 0
        System.out.println(minInsertions("mbadm")); // 2
        System.out.println(minInsertions("leetcode")); // 5
        System.out.println(minInsertions("aebcbda")); // 2
        System.out.println(minInsertions("aba"));   // 0
    }

    /**
     * 1. Find x = LCS ( str , reverse Str)
     * 2. minimum number of deletion required = len(str) - X
     */
    private static int minInsertions(String s) {
        return s.length() - longestPalindromicSubSequence(s);
    }

    private static int longestPalindromicSubSequence(String input) {

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
