package src;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string3411/1/
 * https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string1956/1/
 * <p>
 * https://www.youtube.com/watch?v=Msghl9189X4 (Helpful to understand the logic)
 * https://leetcode.com/problems/longest-palindromic-substring/discuss/1056859/recursion-a-few-dp-variants (I followed this memoization approach)
 */
public class _5_LongestPalindromicSubstring_Memoization {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));   // aca
        System.out.println(longestPalindrome("babad")); // bab
        System.out.println(longestPalindrome("cbbd"));  // bb
        System.out.println(longestPalindrome("abbcccbbbcaaccbababcbcabca")); // bbcccbb
    }

    // Memoization + Bottom Up DP
    public static String longestPalindrome(String input) {

        int n = input.length();
        String[][] dp = new String[n + 1][n + 1];

        return lps(input, 0, n - 1, dp);
    }

    private static String lps(String input, int start, int end, String[][] dp) {

        if (start > end) return ""; // base condition
        if (start == end) return String.valueOf(input.charAt(start));   // base condition

        if (dp[start][end] != null) return dp[start][end];  // memoization

        if (input.charAt(start) == input.charAt(end)) {

            int lpsRemainingStringLength = end - start - 1;

            String middleString = lps(input, start + 1, end - 1, dp);

            if (lpsRemainingStringLength == middleString.length()) {

                return dp[start][end] = input.charAt(start) + middleString + input.charAt(end);
            }
        }

        String left = lps(input, start + 1, end, dp);
        String right = lps(input, start, end - 1, dp);

        return dp[start][end] = (left.length() > right.length() ? left : right);
    }
}
