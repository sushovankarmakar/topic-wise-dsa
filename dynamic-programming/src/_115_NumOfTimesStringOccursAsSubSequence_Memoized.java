package src;

/**
 * https://practice.geeksforgeeks.org/problems/find-number-of-times-a-string-occurs-as-a-subsequence3020/1/
 * https://leetcode.com/problems/distinct-subsequences/
 * https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
 * <p>
 * <p>
 * Similar concept like this problem : Count of Subsets Sum with a Given Sum
 * https://www.youtube.com/watch?v=F7wqWbqYn9g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=9&ab_channel=AdityaVerma
 *
 * https://leetcode.com/discuss/general-discussion/1276555/find-number-of-times-a-string-occurs-as-a-subsequence-in-given-string
 *
 * companies :
 * amazon, mathworks, google, bloomberg, apple, adobe, goldman sachs
 *
 * choice diagram :
 * https://drive.google.com/file/d/1PSJa3WNpQm-GI0_BSEUYoWdH0qoGH0_s/view?usp=sharing
 */
public class _115_NumOfTimesStringOccursAsSubSequence_Memoized {

    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit")); // 3
        System.out.println(numDistinct("babgbag", "bag"));  // 5
        System.out.println(numDistinct("geeksforgeeks","gks")); // 4
    }

    private static int numDistinct(String s1, String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return count(s1, s2, m, n, dp);
    }

    private static int count(String s1, String s2, int m, int n, int[][] dp) {

        if (n == 0) {
            dp[m][n] = 1;
            return 1;
        }

        if (m == 0 || m < n) {
            dp[m][n] = 0;
            return 0;
        }

        if (dp[m][n] != -1) {
            return dp[m][n];
        }

        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {

            int includingCurrChar = count(s1, s2, m - 1, n - 1, dp);
            int excludingCurrChar = count(s1, s2, m - 1, n, dp);

            dp[m][n] = includingCurrChar + excludingCurrChar;

            return dp[m][n];

        } else {

            return dp[m][n] = count(s1, s2, m - 1, n, dp);
        }
    }
}
