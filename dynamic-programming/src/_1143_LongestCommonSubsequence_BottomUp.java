package src;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://practice.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1
 * <p>
 * https://www.youtube.com/watch?v=hR3s9rGlMTU&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=22&t=12s&ab_channel=AdityaVerma
 * <p>
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 */
public class _1143_LongestCommonSubsequence_BottomUp {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));   // 3
        System.out.println(longestCommonSubsequence("abc", "abc"));     // 3
        System.out.println(longestCommonSubsequence("abc", "def"));     // 0
        System.out.println(longestCommonSubsequence("pmjghexybyrgzczy", "hafcdqbgncrcbihkd"));     // 4
    }

    /**
     * Time Complexity  : O(n * m)
     * Space Complexity : O(n * m)
     */
    private static int longestCommonSubsequence(String text1, String text2) {

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
        return dp[m][n];
    }
}
