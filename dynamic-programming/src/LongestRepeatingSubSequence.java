package src;

/**
 * https://practice.geeksforgeeks.org/problems/longest-repeating-subsequence2004/1
 * https://www.geeksforgeeks.org/longest-repeated-subsequence/
 *
 * https://www.youtube.com/watch?v=hbTaCmQGqLg&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=29&ab_channel=AdityaVerma
 */
public class LongestRepeatingSubSequence {

    public static void main(String[] args) {
        System.out.println(longestRepeatingSubsequence("axxzxy")); // 2
        System.out.println(longestRepeatingSubsequence("axxxy")); // 2
        System.out.println(longestRepeatingSubsequence("aab")); // 1
        System.out.println(longestRepeatingSubsequence("AABEBCDD")); // 3
    }

    private static int longestRepeatingSubsequence(String str) {

        int m = str.length();
        int[][] dp = new int[m + 1][m + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (str.charAt(i - 1) == str.charAt(j - 1) && (i != j)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][m];
    }

}
