package src;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-and-insertions0209/1
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 * <p>
 * https://www.youtube.com/watch?v=-fx6aDxcWyg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=27&ab_channel=AdityaVerma
 * <p>
 * https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/
 */
public class _583_MinNumberOfDeletionsAndInsertions_LCS_Variation {

    public static void main(String[] args) {
        System.out.println(minOperations("heap", "pea")); // 3
        System.out.println(minOperations("geeksforgeeks", "geeks")); // 8
        System.out.println(minOperations("sea", "eat")); // 2
        System.out.println(minOperations("leetcode", "etco")); // 4
    }

    private static int minOperations(String word1, String word2) {

        int lengthOfLCS = lcs(word1, word2);

        return word1.length() + word2.length() - (2 * lengthOfLCS);
    }

    private static int lcs(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n];
    }

}
