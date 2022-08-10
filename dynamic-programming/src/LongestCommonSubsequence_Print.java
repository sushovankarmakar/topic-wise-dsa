package src;

/**
 * https://practice.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1
 * https://www.youtube.com/watch?v=x5hQvnUcjiM&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go (Aditya Verma)
 * https://www.youtube.com/watch?v=-zI4mrF2Pb4 (take U forward)
 * <p>
 * https://www.geeksforgeeks.org/printing-longest-common-subsequence/
 * https://www.geeksforgeeks.org/printing-longest-common-subsequence-set-2-printing/
 * <p>
 * https://leetcode.com/problems/shortest-common-supersequence/discuss/312710/C++Python-Find-the-LCS/290904 (We can follow this too to find out the answer in a single function call)
 */
public class LongestCommonSubsequence_Print {

    public static void main(String[] args) {
        System.out.println(printLCS("abcde", "ace"));   // ace
        System.out.println(printLCS("abc", "abc"));     // abc
        System.out.println(printLCS("abc", "def"));     //
        System.out.println(printLCS("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));     // hbgc
        System.out.println(printLCS("acbcf", "abcdaf"));     // abcf
        System.out.println(printLCS("abaaa", "baabaca"));     // baaa
    }

    static int[][] dp;

    private static String printLCS(String text1, String text2) {

        int[][] dp = longestCommonSubsequence(text1, text2);

        /*for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/

        StringBuilder lcs = new StringBuilder();

        int i = text1.length();
        int j = text2.length();

        while (i > 0 && j > 0) {

            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                lcs.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs.reverse().toString();
    }

    private static int[][] longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        dp = new int[m + 1][n + 1];

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
