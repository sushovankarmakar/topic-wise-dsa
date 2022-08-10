package src;

import java.util.*;

/**
 * https://practice.geeksforgeeks.org/problems/print-all-lcs-sequences3413/1
 * https://www.youtube.com/watch?v=x5hQvnUcjiM&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go (Aditya Verma)
 * https://www.youtube.com/watch?v=-zI4mrF2Pb4 (take U forward)
 * <p>
 * https://www.geeksforgeeks.org/printing-longest-common-subsequence/
 * https://www.geeksforgeeks.org/printing-longest-common-subsequence-set-2-printing/ ( I followed this. Very Helpful)
 * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem#Reading_out_all_LCSs
 */
public class LongestCommonSubsequence_PrintAll {

    public static void main(String[] args) {
        System.out.println(printAllLCS("abaaa", "baabaca")); //  [aaaa, abaa, baaa]
        System.out.println(printAllLCS("AGTGATG", "GTTAG")); //  [GTAG, GTTG]
        System.out.println(printAllLCS("AATCC", "ACACG")); //    [AAC, ACC]
        System.out.println(printAllLCS("ABCBDAB", "BDCABA")); // [BCAB, BCBA, BDAB]
        System.out.println(printAllLCS("aaa", "a")); // [a]
    }

    static int[][] dp;

    private static List<String> printAllLCS(String text1, String text2) {
        dp = longestCommonSubsequence(text1, text2);
        List<String> allLCS = new ArrayList<>(printLCS(text1, text2, text1.length(), text2.length()));
        //Collections.sort(allLCS);
        return allLCS;
    }

    /* Returns set containing all LCS for X[0..m-1], Y[0..n-1] */
    private static TreeSet<String> printLCS(String text1, String text2, int len1, int len2) {
        TreeSet<String> set = new TreeSet<>();

        // If we reaches end of either String, return a empty set
        if (len1 == 0 || len2 == 0) {
            set.add("");    // IMPORTANT LINE : we need to add this blank string, as later we are doing concatenation in line 43.
            return set;
        }

        /*
         * If the last characters of X and Y are same (i.e. X[i-1] == Y[j-1]),
         * then the character must be present in all LCS of substring X[0…i-1] and Y[0..j-1].
         * We simply recurse for L[i-1][j-1] in the matrix and
         * append current character to all LCS possible of substring X[0…i-2] and Y[0..j-2].
         */
        if (text1.charAt(len1 - 1) == text2.charAt(len2 - 1)) {
            Set<String> temp = printLCS(text1, text2, len1 - 1, len2 - 1);

            for (String str : temp) {
                set.add(str + text1.charAt(len1 - 1));
            }

        } else {
            /*
             * If the last characters of X and Y are not same (i.e. X[i-1] != Y[j-1]),
             * then LCS can be constructed from either top side of the matrix (i.e. L[i-1][j]) or
             * from left side of matrix (i.e. L[i][j-1]) depending upon which value is greater.
             *
             * If both the values are equal(i.e. L[i-1][j] == L[i][j-1]),
             * then it will be constructed from both sides of matrix. So based on values at L[i-1][j] and L[i][j-1],
             * we go in direction of greater value or go in both directions if the values are equal.
             */
            if (dp[len1 - 1][len2] >= dp[len1][len2 - 1]) {
                set = printLCS(text1, text2, len1 - 1, len2);
            }

            if (dp[len1 - 1][len2] <= dp[len1][len2 - 1]) {
                Set<String> temp = printLCS(text1, text2, len1, len2 - 1);
                set.addAll(temp);
            }
        }

        return set;
    }

    private static int[][] longestCommonSubsequence(String text1, String text2) {

        int m = text1.length();
        int n = text2.length();

        dp = new int[m + 1][n + 1];

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
        return dp;
    }


    // above solution was easy to understand, but it was giving TLE
    // but below code was using DFS and it is successfully submitted.
    // https://practice.geeksforgeeks.org/viewSol.php?subId=1ac44e3a9d929b9898678669c2aa990c&pid=703749&user=saxenavaibhavias
    class Solution_2 {
        List<String> res;
        HashSet<String> set;
        int n, m;
        int dp[][];
        int max;

        public List<String> all_longest_common_subsequences(String s, String t) {
            // Code here
            n = s.length();
            m = t.length();
            res = new ArrayList<>();
            set = new HashSet<>();
            dp = new int[n][m];
            for (int x[] : dp)
                Arrays.fill(x, -1);
            max = recur(0, 0, s, t);
            // System.out.println(max);
            dfs(s, t, 0, 0, "");
            Collections.sort(res);
            return res;
        }

        public void dfs(String s, String t, int i, int j, String curr) {
            if (curr.length() == max) {
                if (!set.contains(curr)) {
                    res.add(curr);
                    set.add(curr);
                }
            }

            if (i >= n || j >= m)
                return;
            for (int k = i; k < n; k++) {
                for (int l = j; l < m; l++) {
                    if (s.charAt(k) == t.charAt(l)) {
                        dfs(s, t, k + 1, l + 1, curr + s.charAt(k));
                    }
                }
            }
        }

        public int recur(int i, int j, String s, String t) {
            if (i >= n || j >= m)
                return 0;
            else if (dp[i][j] != -1)
                return dp[i][j];
            int res = 0;
            if (s.charAt(i) == t.charAt(j))
                res = 1 + recur(i + 1, j + 1, s, t);
            else
                res = Math.max(recur(i + 1, j, s, t), recur(i, j + 1, s, t));
            return dp[i][j] = res;
        }
    }

}
