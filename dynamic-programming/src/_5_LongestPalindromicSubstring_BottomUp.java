package src;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string3411/1/
 * https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string1956/1/
 * <p>
 * https://www.youtube.com/watch?v=UflHuQj6MVA (Good to understand the topic)
 * https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * <p>
 * https://www.youtube.com/watch?v=Msghl9189X4
 * https://thecodingsimplified.com/longest-palindromic-substring/ (recursive + memoization + bottom up)
 * <p>
 * (followed the below link for recursive and memoization)
 * https://leetcode.com/problems/longest-palindromic-substring/discuss/1056859/recursion-a-few-dp-variants
 * <p>
 * (followed the below link for Bottom Up)
 * https://leetcode.com/problems/longest-palindromic-substring/discuss/578435/Evolution-from-Recursion-to-Top-Down-DP-to-Bottoms-Up-DP.-Easy-understanding-code
 * <p>
 * similar question : https://leetcode.com/problems/palindromic-substrings/ (647. Palindromic Substrings)
 */
public class _5_LongestPalindromicSubstring_BottomUp {

    public static void main(String[] args) {

        System.out.println(longestPalindrome("aacabdkacaa"));   // aca
        System.out.println(longestPalindrome("ababb"));   // bab
        System.out.println(longestPalindrome("babad")); // bab OR aba
        System.out.println(longestPalindrome("cbbd"));  // bb
        System.out.println(longestPalindrome("abbcccbbbcaaccbababcbcabca")); // bbcccbb OR cbababc
        System.out.println(longestPalindrome("aaaa")); // aaaa
        System.out.println(longestPalindrome("aaaabbaa")); // aabbaa
    }

    // https://leetcode.com/problems/longest-palindromic-substring/discuss/578435/Evolution-from-Recursion-to-Top-Down-DP-to-Bottoms-Up-DP.-Easy-understanding-code
    public static String longestPalindrome(String input) {

        int n = input.length();
        boolean[][] dp = new boolean[n][n]; //means if str.substring(i, j + 1) is palindrome.

        int maxStart = 0;
        int maxLen = 1;

        /**
         * While converting a Top Down to A Bottoms up solution,
         * it is important to make sure the outermost loop is reversed.
         *
         * During the top down, the order of execution is reverse.
         * So to make sure this also happens in bottoms up, it is essential to reverse the direction.
         *
         * This is true for ALL dynamic programming problems.
         *
         * Another hint is because the main equation has memo[left][right] = memo[left + 1][right - 1];
         *
         * This means, before left can be computed left+1 must be precomputed. So left goes from n,n-1,...0.
         * Also, before right can be computed right-1 must be precomputed.So right goes 0,1,2....n.
         */
        for (int start = n - 1; start >= 0; start--) { // notice this goes backwards
            for (int end = start; end < n; end++) {

                if (input.charAt(start) == input.charAt(end)) {

                    if (end - start <= 2) { // base condition check.
                        dp[start][end] = true;
                    } else {
                        dp[start][end] = dp[start + 1][end - 1];
                    }

                    int len = end - start + 1;

                    /**
                     * In case of conflict,
                     * 1. if we have to return the substring which occurs first ( with the least starting index )
                     *      then maxLen <= len
                     * 2. if we have to return the substring which occurs last ( with the max starting index )
                     *      then maxLen < len
                     */
                    if (dp[start][end] && maxLen <= len) {
                        maxLen = len;
                        maxStart = start;
                    }
                }
            }
        }

        return input.substring(maxStart, maxStart + maxLen);
    }
}
