package src;

/**
 * https://practice.geeksforgeeks.org/problems/shortest-common-supersequence0322/1/
 * https://leetcode.com/problems/shortest-common-supersequence/
 * <p>
 * https://www.youtube.com/watch?v=823Grn4_dCQ&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=25&ab_channel=AdityaVerma
 *
 * https://www.geeksforgeeks.org/print-shortest-common-supersequence/ (Similar to printing LCS)
 * https://www.youtube.com/watch?v=VDhRg-ZJTuc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=30&ab_channel=AdityaVerma (Easy to remember)
 * <p>
 * https://leetcode.com/problems/shortest-common-supersequence/discuss/312710/C++Python-Find-the-LCS/290904 (Followed this)
 */
public class _1092_ShortestCommonSuperSequence_LCS_Variation {

    public static void main(String[] args) {
        System.out.println(shortestCommonSuperSequence("abac", "cab")); // cabac
        System.out.println(shortestCommonSuperSequence("aaaaaaaa", "aaaaaaaa")); // aaaaaaaa
        System.out.println(shortestCommonSuperSequence("abcdaf", "acbcf")); // acbcdaf
    }

    /**
     * Approach 1 : (1 step)
     * 1. find length of lcs and the dp array.
     * 2. find the lcs string using that dp array.
     * 3. then find the shortest common super sequence using str1, str2, lcs.
     * <p>
     * Approach 2 : (2 step)
     * 1. directly find the lcs string.
     * 2. then find the shortest common super sequence using str1, str2, lcs.
     */
    private static String shortestCommonSuperSequence(String str1, String str2) {

        // APPROACH 1
        // int[][] dp = getLengthOfLCS(str1, str2);
        // String lcs = getLCS(str1, str2, dp);
        // return getSCS(str1, str2, lcs);

        // APPROACH 2 : https://leetcode.com/problems/shortest-common-supersequence/discuss/312710/C++Python-Find-the-LCS/290904
        // String lcs = getLCS(str1, str2);
        // return getSCS(str1, str2, lcs);

        // easy to remember approach : similar to printing lcs printing.
        // APPROACH 3 : https://www.youtube.com/watch?v=VDhRg-ZJTuc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=30&ab_channel=AdityaVerma
        // https://www.geeksforgeeks.org/print-shortest-common-supersequence/
        int[][] dp = getLengthOfLCS(str1, str2);
        return getSCS_1(str1, str2, dp);
    }

    private static int[][] getLengthOfLCS(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {

                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp;
    }

    // code is very similar to 'print lcs'
    private static String getSCS_1(String str1, String str2, int[][] dp) {

        StringBuilder scs = new StringBuilder();

        int i = str1.length();
        int j = str2.length();

        while (i > 0 && j > 0) {

            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                scs.append(str1.charAt(i - 1));

                i--;
                j--;
            } else {

                if (dp[i][j - 1] > dp[i - 1][j]) {
                    scs.append(str2.charAt(j - 1)); // extra line added to make scs
                    j--;
                } else {
                    scs.append(str1.charAt(i - 1)); // extra line added to make scs
                    i--;
                }
            }
        }

        // extra code added to make scs
        while (i > 0) {
            scs.append(str1.charAt(i - 1));
            i--;
        }

        // extra line added to make scs
        while (j > 0) {
            scs.append(str2.charAt(j - 1));
            j--;
        }

        return scs.reverse().toString();
    }

    private static String getLCS(String str1, String str2, int[][] dp) {

        int i = str1.length();
        int j = str2.length();

        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {

            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }

        return lcs.reverse().toString();
    }

    private static String getLCS(String str1, String str2) {

        int m = str1.length();
        int n = str2.length();

        String[][] dp = new String[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {

                if (i == 0 || j == 0) {
                    dp[i][j] = "";
                    continue;
                }

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {

                    dp[i][j] = (dp[i - 1][j].length() > dp[i][j - 1].length()) ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    private static String getSCS(String str1, String str2, String lcs) {

        StringBuilder scs = new StringBuilder();
        int p1 = 0, p2 = 0;

        for (int i = 0; i < lcs.length(); i++) {

            while (p1 < str1.length() && str1.charAt(p1) != lcs.charAt(i)) {
                scs.append(str1.charAt(p1));
                p1++;
            }

            while (p2 < str2.length() && str2.charAt(p2) != lcs.charAt(i)) {
                scs.append(str2.charAt(p2));
                p2++;
            }

            scs.append(lcs.charAt(i));
            p1++;
            p2++;
        }

        return scs.append(str1.substring(p1)).append(str2.substring(p2)).toString();
    }
}
