package src;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * https://practice.geeksforgeeks.org/problems/palindromic-patitioning4845/1/
 * <p>
 * https://leetcode.com/problems/palindrome-partitioning/
 * <p>
 * https://www.geeksforgeeks.org/palindrome-partitioning-dp-17/
 * <p>
 * https://leetcode.com/problems/palindrome-partitioning-ii/solution/
 * <p>
 * Aditya Verma playlist :
 * https://www.youtube.com/watch?v=szKVpQtBHh8&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=35 (Top Down Recursive)
 * https://www.youtube.com/watch?v=fOUlNlawdAU&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=36 (Top Down + Memoization)
 * https://www.youtube.com/watch?v=9h10fqkI7Nk&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=38
 */

/**
 * TODO : https://www.youtube.com/results?search_query=Palindromic+Partitioning (Read more about other approaches)
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/DP_MinCutsForPalindromicPartitioning.java
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/4.%20Hard/src/Backtracking_PalindromePartitioning.java
 */
public class PalindromicPartitioning_TopDown_Memoization {

    public static void main(String[] args) {
        System.out.println(minCut("aab"));  // 1
        System.out.println(minCut("ababbbabbababa")); // 3
        System.out.println(minCut("aaabba")); // 1
    }

    /**
     * top down + memoization approach
     * this approach will work in GFG where :  1 ≤ length of str ≤ 500
     */
    static int[][] minCutMemo; // making minCut() method call memoized.
    static int[][] isPalindromeMemo; // making isPalindrome() method call memoized.

    private static int minCut(String s) {

        int n = s.length();
        int i = 0;
        int j = n - 1;

        minCutMemo = new int[n + 1][n + 1];
        isPalindromeMemo = new int[n + 1][n + 1];
        for (int a = 0; a <= n; a++) {
            for (int b = 0; b <= n; b++) {
                minCutMemo[a][b] = -1;
                isPalindromeMemo[a][b] = -1;
            }
        }

        return findMinimumCuts(s, i, j);
    }

    private static int findMinimumCuts(String s, int i, int j) {

        // I think for optimization, you just need to shift the code of checking the already existing value,
        // to before checking for palindrome!
        // No change required in the for loop.

        // this check should be before than the base case checks because that will reduce calling the isPalindrome function.
        if (minCutMemo[i][j] != -1) {
            return minCutMemo[i][j];
        }

        if (i >= j || isPalindrome(s, i, j)) { // base cases.
            return 0;
        }

        int count = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
            int tempCount = 1 + findMinimumCuts(s, i, k) + findMinimumCuts(s, k + 1, j);
            count = Math.min(count, tempCount);
        }
        return minCutMemo[i][j] = count;
    }

    private static boolean isPalindrome(String s, int i, int j) {

        while (i <= j) {

            if (isPalindromeMemo[i][j] != -1) return isPalindromeMemo[i][j] != 0; // memoized

            if (s.charAt(i) != s.charAt(j)) {
                isPalindromeMemo[i][j] = 0;
                return false;
            }
            i++;
            j--;
        }

        isPalindromeMemo[i][j] = 1; // memoized
        return true;
    }
}
