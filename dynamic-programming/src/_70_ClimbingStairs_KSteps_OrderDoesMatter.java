package src;

/**
 * https://www.youtube.com/watch?v=UsPGd7DggRY&t=608s&ab_channel=AndreyGrehov
 * https://www.youtube.com/watch?v=7vM_RZEGUcw&t=32s&ab_channel=AndreyGrehov  (using less space)
 * <p>
 * https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
 * <p>
 * Find number of ways to climb N stairs by taking at most k steps.
 */
public class _70_ClimbingStairs_KSteps_OrderDoesMatter {

    public static void main(String[] args) {
        System.out.println(nthPointByKSteps(1, 1));  // 1
        System.out.println(nthPointByKSteps(1, 2));  // 1
        System.out.println(nthPointByKSteps(3, 2));  // 3
        System.out.println(nthPointByKSteps(5, 2));  // 8
        System.out.println(nthPointByKSteps(3, 3));  // 4
        System.out.println(nthPointByKSteps(5, 3));  // 13
        System.out.println(nthPointByKSteps(5000, 2));  // 1419609288
        System.out.println(nthPointByKSteps(1000000, 2));  // 1534400670
    }

    /**
     * time complexity : O(n * k)
     * space complexity : O(n)
     */
    public static int nthPointByKSteps(int n, int k) {

        if (n <= 1) return 1;

        int mod = 1000000007;
        int[] steps = new int[n + 1];
        steps[0] = 1;
        steps[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i >= j) {
                    steps[i] += (steps[i - j] % mod);     // f[i] = f[i - 1] + f[i - 2] + f[i - 3] + f[i - 4] + .... + f[i - k];
                }
            }
        }
        return steps[n];
    }

}
