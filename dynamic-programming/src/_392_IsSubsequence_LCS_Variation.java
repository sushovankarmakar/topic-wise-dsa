package src;

/**
 * https://practice.geeksforgeeks.org/problems/check-for-subsequence4930/1/
 * https://leetcode.com/problems/is-subsequence/
 *
 * https://www.geeksforgeeks.org/given-two-strings-find-first-string-subsequence-second/
 * https://www.youtube.com/watch?v=QVntmksK2es&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=30&t=8s&ab_channel=AdityaVerma (LCS Variation)
 */
public class _392_IsSubsequence_LCS_Variation {

    public static void main(String[] args) {
        /*System.out.println(isSubsequence_usingLCS("gksrek", "geeksforgeeks"));   // true
        System.out.println(isSubsequence_usingLCS("abc", "ahbgdc")); // true
        System.out.println(isSubsequence_usingLCS("axc", "ahbgdc")); // false
        System.out.println(isSubsequence_usingLCS("AXY", "YADXCP")); // false*/

        System.out.println(isSubSequence_twoPointers("gksrek", "geeksforgeeks"));   // true
        System.out.println(isSubSequence_twoPointers("abc", "ahbgdc")); // true
        System.out.println(isSubSequence_twoPointers("axc", "ahbgdc")); // false
        System.out.println(isSubSequence_twoPointers("AXY", "YADXCP")); // false
    }

    /**
     * time complexity : O(n)
     * space complexity : O(1)
     */
    private static boolean isSubSequence_twoPointers(String a, String b) {

        int i = 0, j = 0;
        int m = a.length();
        int n = b.length();

        while (i < m && j < n) {

            if (a.charAt(i) == b.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == m;
    }

    /**
     * time complexity  : O(n^2)
     * space complexity : O(n^2)
     */
    private static boolean isSubsequence_usingLCS(String a, String b) {

        int m = a.length();
        int n = b.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (a.charAt(i - 1) == b.charAt(j - 1)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[m][n] == a.length();
    }
}
