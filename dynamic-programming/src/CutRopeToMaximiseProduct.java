package src;

/**
 * https://practice.geeksforgeeks.org/problems/max-rope-cutting1312/1
 * <p>
 * https://www.geeksforgeeks.org/maximum-product-cutting-dp-36/ (Took help from this)
 * <p>
 * Similar Problem :
 * https://practice.geeksforgeeks.org/problems/rod-cutting0840/1
 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 */
public class CutRopeToMaximiseProduct {

    public static void main(String[] args) {
        System.out.println(maxProduct_usingDP(4));   // 4
        System.out.println(maxProduct_usingDP(100)); // 7412080755407364
        System.out.println(maxProduct_UsingTrick(10)); // 36
    }

    static long maxProduct_usingDP(int n) {

        int[] cuts = new int[n];
        for (int i = 0; i < n; i++) {
            cuts[i] = i + 1;
        }

        long[] dp = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }

        return solve(cuts, n, n, dp);
    }

    private static long solve(int[] cuts, int len, int N, long[] dp) {

        if (N <= 1) return 0;
        if (N == 2) return 1;
        if (N == 3) return 2;

        if (len == 0) return 0;

        if (dp[N] != -1) return dp[N];

        if (cuts[len - 1] <= N) {
            long include =
                    Math.max((N - cuts[len - 1]) * cuts[len - 1],   // current cut
                            solve(cuts, len, N - cuts[len - 1], dp) * cuts[len - 1] // future cut
                    );
            long exclude = solve(cuts, len - 1, N, dp);

            return dp[N] = Math.max(include, exclude);
        }

        return dp[N] = solve(cuts, len - 1, N, dp);
    }

    /*
     * A Tricky Solution:
     *
     * If we see some examples of these problems, we can easily observe following pattern.
     * The maximum product can be obtained be repeatedly cutting parts of size 3 while size is greater than 4,
     * keeping the last part as size of 2 or 3 or 4. For example, n = 10,
     * the maximum product is obtained by 3, 3, 4. For n = 11, the maximum product is obtained by 3, 3, 3, 2.
     *
     */
    static long maxProduct_UsingTrick(int n) {

        if (n == 2 || n == 3 || n == 1) return (n - 1);
        if (n == 4) return n;

        // Keep removing parts of size 3 while n is greater than 4
        long res = 1;
        while (n > 4) {
            n -= 3;
            res *= 3; // Keep multiplying 3 to res
        }
        return (n * res);
    }
}
