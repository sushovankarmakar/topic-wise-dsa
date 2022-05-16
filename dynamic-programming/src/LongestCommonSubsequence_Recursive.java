package src;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * https://practice.geeksforgeeks.org/problems/longest-common-subsequence-1587115620/1
 * <p>
 * https://www.youtube.com/watch?v=4Urd0a0BNng&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=19
 * <p>
 * https://www.geeksforgeeks.org/longest-common-subsequence-dp-4/
 */
public class LongestCommonSubsequence_Recursive {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));   // 3
        System.out.println(longestCommonSubsequence("abc", "abc"));     // 3
        System.out.println(longestCommonSubsequence("abc", "def"));     // 0
    }

    private static int longestCommonSubsequence(String text1, String text2) {

        int n = text1.length();
        int m = text2.length();

        return lcs(text1, text2, n, m);
    }

    private static int lcs(String text1, String text2, int n, int m) {

        if (n == 0 || m == 0) {
            return 0;
        }

        if (text1.charAt(n - 1) == text2.charAt(m - 1)) {

            return 1 + lcs(text1, text2, n - 1, m - 1);

        } else {
            return Math.max(lcs(text1, text2, n, m - 1),
                    lcs(text1, text2, n - 1, m));
        }
    }
}
