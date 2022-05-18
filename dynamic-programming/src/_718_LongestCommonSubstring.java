package src;

/**
 * https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1/
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 *
 * https://www.geeksforgeeks.org/longest-common-substring-dp-29/
 *
 * https://www.youtube.com/watch?v=HrybPYpOvz0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=23&t=1s&ab_channel=AdityaVerma
 */
public class _718_LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println(longestCommonSubstring("ABC", "ACB"));       // 1
        System.out.println(longestCommonSubstring("ABCDGH", "ACDGHR")); // 4
        System.out.println(longestCommonSubstring("12321", "32147"));   // 3
        System.out.println(longestCommonSubstring("00000", "00000"));   // 5
    }

    /**
     *       A  C  D  G  H  R
     *   [0, 0, 0, 0, 0, 0, 0]
     * A [0, 1, 0, 0, 0, 0, 0]
     * B [0, 0, 0, 0, 0, 0, 0]
     * C [0, 0, 1, 0, 0, 0, 0]
     * D [0, 0, 0, 2, 0, 0, 0]
     * G [0, 0, 0, 0, 3, 0, 0]
     * H [0, 0, 0, 0, 0, 4, 0]
     */
    private static int longestCommonSubstring(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        int maxLen = 0; // it stores the maximum value in the matrix

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];

                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {

                    dp[i][j] = 0;
                }
            }
        }
        return maxLen;  // at the end, we have to return the max value in the matrix and not t[m][n]
    }
}
