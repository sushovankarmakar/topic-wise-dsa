package src;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://practice.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1
 * <p>
 * https://www.youtube.com/watch?v=4Urd0a0BNng&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=19
 * <p>
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 */
public class _1143_LongestCommonSubsequence_Recursive {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));   // 3
        System.out.println(longestCommonSubsequence("abc", "abc"));     // 3
        System.out.println(longestCommonSubsequence("abc", "def"));     // 0
    }

    /**
     * Time complexity of the above naive recursive approach is O(2 ^ n) in worst case
     * and worst case happens when all characters of X and Y mismatch i.e., length of LCS is 0.
     */
    private static int longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        return lcs(text1, text2, m, n);
    }

    private static int lcs(String text1, String text2, int m, int n) {

        if (m == 0 || n == 0) {
            return 0;
        }

        if (text1.charAt(m - 1) == text2.charAt(n - 1)) {

            return 1 + lcs(text1, text2, m - 1, n - 1);

        } else {
            return Math.max(lcs(text1, text2, m, n - 1),
                    lcs(text1, text2, m - 1, n));
        }
    }
}
