package src;

/**
 * https://www.geeksforgeeks.org/printing-longest-common-subsequence/
 *
 * https://leetcode.com/problems/shortest-common-supersequence/discuss/312710/C++Python-Find-the-LCS/290904 (We can follow this too to find out the answer in a single function call)
 */
public class LongestCommonSubsequence_Print {

    public static void main(String[] args) {
        System.out.println(printLCS("abcde", "ace"));   // ace
        System.out.println(printLCS("abc", "abc"));     // abc
        System.out.println(printLCS("abc", "def"));     //
        System.out.println(printLCS("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));     // hbgc
        System.out.println(printLCS("acbcf", "abcdaf"));     // abcf
    }

    private static String printLCS(String text1, String text2) {

        int[][] dp = longestCommonSubsequence(text1, text2);

        StringBuilder lcs = new StringBuilder();

        int i = text1.length();
        int j = text2.length();

        while (i > 0 && j > 0) {

            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else {
                i--;
            }
        }
        return lcs.reverse().toString();
    }

    private static int[][] longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) { // this part is just initialization
                    dp[i][j] = 0;
                    continue;
                }

                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp;
    }

}
