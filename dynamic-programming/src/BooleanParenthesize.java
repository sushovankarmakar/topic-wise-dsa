package src;

/**
 * https://practice.geeksforgeeks.org/problems/boolean-parenthesization5610/1/
 * https://www.geeksforgeeks.org/boolean-parenthesization-problem-dp-37/
 * <p>
 * https://www.youtube.com/watch?v=pGVguAcWX4g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=39 (Recursive)
 * https://www.youtube.com/watch?v=bzXM1Zond9U&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=40 (Memoized)
 */
public class BooleanParenthesize {

    public static void main(String[] args) {
        System.out.println(countWays("T|T&F^T"));   // 4
        System.out.println(countWays("T^F|F"));     // 2
        System.out.println(countWays("T&T|F|F^F^T^T^T&T^F^T&F|F^F^F&F&F|F|F^F^T|T&T"));     // 718
    }

    static int[][][] memo;

    private static int countWays(String s) {

        int n = s.length();
        int i = 0;
        int j = n - 1;

        memo = new int[n + 1][n + 1][2];    // doing this for memoization
        for (int a = 0; a <= n; a++) {
            for (int b = 0; b <= n; b++) {
                memo[a][b][0] = -1;
                memo[a][b][1] = -1;
            }
        }

        return numOfWays(s, i, j, true) % 1003; // returns number of possible ways modulo 1003
    }

    private static int numOfWays(String s, int i, int j, boolean isTrue) {

        // base condition
        if (i > j) {
            return 0;
        }

        // base condition
        if (i == j) {
            if (isTrue) {
                if (s.charAt(i) == 'T') {
                    return 1;
                }
            } else {
                if (s.charAt(i) == 'F') {
                    return 1;
                }
            }
        }

        if (isTrue && memo[i][j][0] != -1) { // memoization check
            return memo[i][j][0];
        }

        if (!isTrue && memo[i][j][1] != -1) { // memoization check
            return memo[i][j][1];
        }

        int ways = 0;
        for (int k = i + 1; k <= j - 1; k = k + 2) {

            int leftTrue = numOfWays(s, i, k - 1, true);
            int rightTrue = numOfWays(s, k + 1, j, true);
            int leftFalse = numOfWays(s, i, k - 1, false);
            int rightFalse = numOfWays(s, k + 1, j, false);

            ways += evaluateExpression(s, k, isTrue, leftTrue, rightTrue, leftFalse, rightFalse);
        }

        return memo[i][j][isTrue ? 0 : 1] = (ways % 1003); //  returns number of possible ways modulo 1003
    }

    private static int evaluateExpression(String s, int k, boolean isTrue, int leftTrue, int rightTrue,
                                          int leftFalse, int rightFalse) {

        int ways = 0;
        if (s.charAt(k) == '&') {
            if (isTrue) {
                ways += (leftTrue * rightTrue);

            } else {
                ways += (leftTrue * rightFalse)
                        + (leftFalse * rightTrue)
                        + (leftFalse * rightFalse);
            }
        } else if (s.charAt(k) == '|') {
            if (isTrue) {
                ways += (leftTrue * rightTrue)
                        + (leftTrue * rightFalse)
                        + (leftFalse * rightTrue);

            } else {
                ways += (leftFalse * rightFalse);
            }
        } else if (s.charAt(k) == '^') {
            if (isTrue) {
                ways += (leftTrue * rightFalse) + (leftFalse * rightTrue);
            } else {
                ways += (leftTrue * rightTrue)
                        + (leftFalse * rightFalse);
            }
        }
        return ways;
    }
}
