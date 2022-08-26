package src;

/**
 * https://practice.geeksforgeeks.org/problems/count-palindromic-subsequences/1
 * <p>
 * https://www.geeksforgeeks.org/count-palindromic-subsequence-given-string/
 * <p>
 * https://www.youtube.com/watch?v=YHSjvswCXC8
 * <p>
 * https://leetcode.com/problems/count-different-palindromic-subsequences/(A bit different question)
 * https://www.youtube.com/watch?v=fvYlinirmFg
 */
public class CountPalindromicSubsequences {

    /**
     * TODO :
     * Pep Coding's Palindromic subsequences questions solving pattern is different. need to read that too.
     */

    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("aab"));    // 4 :  "a", "a", "b", "aa"
        System.out.println(countPalindromicSubsequences("abcd"));   // 4 :  "a" ,"b", "c" ,"d"
    }

    private static long[][] dp;

    private static final long mod = 1000000007;

    private static long countPalindromicSubsequences(String str) {

        int n = str.length();

        dp = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        long ans = count(str, 0, n - 1);

        return ans < 0 ? (ans + mod) : ans;
    }

    private static long count(String str, int start, int end) {

        if (start > end) return 0;

        if (start == end) return 1;

        if (dp[start][end] != -1) return dp[start][end];

        if (str.charAt(start) == str.charAt(end)) {

            return dp[start][end] = (count(str, start, end - 1)
                    + count(str, start + 1, end)
                    + 1) % mod;
        } else {

            return dp[start][end] = (count(str, start, end - 1)
                    + count(str, start + 1, end)
                    - count(str, start + 1, end - 1)) % mod;
        }
    }
}
