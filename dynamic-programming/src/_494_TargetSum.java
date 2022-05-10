package src;

/**
 * https://leetcode.com/problems/target-sum/
 * https://practice.geeksforgeeks.org/problems/target-sum-1626326450/1
 *
 * https://www.youtube.com/watch?v=Hw6Ygp3JBYw&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=13&ab_channel=AdityaVerma
 */
public class _494_TargetSum {

    /**
     * N :      3
     * arr :    9 2 9
     * target : 3
     *
     * - this example shows that sum + target shouldn't be odd.
     */

    /**
     * 9
     * 0 0 0 0 0 0 0 0 1
     * 1
     *
     * output : 256
     *
     * - this example shows that how to handle inputs where there is zeros.
     */
    public static void main(String[] args) {
        System.out.println(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 5, 3)); // 5
        System.out.println(findTargetSumWays(new int[]{1}, 1, 1)); // 1
        System.out.println(findTargetSumWays(new int[]{9, 2, 9}, 3, 3)); // 0
        System.out.println(findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 9, 1)); // 256
    }

    static int findTargetSumWays(int[] arr, int n, int target) {

        int sum = 0;
        for (int val : arr) {
            sum += val;
        }

        int s1 = (sum + target) / 2;

        // target shouldn't be greater than one subset's sum.
        // (sum + target) should not be odd.
        if (target > s1 || (sum + target) % 2 != 0) {   // corner cases.
            return 0;
        }

        return count(arr, n, s1);
    }

    static int count(int[] arr, int n, int sum) {

        int zeroCount = countZeroes(arr, n);

        arr = removeZeroes(arr, n, zeroCount);
        n = arr.length;

        int[][] dp = new int[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {

                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (arr[i - 1] <= j) {

                    int includingCurrVal = dp[i - 1][j - arr[i - 1]];
                    int excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = includingCurrVal + excludingCurrVal;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum] * (int) Math.pow(2, zeroCount);
    }

    private static int countZeroes(int[] arr, int n) {
        int zeroCount = 0;
        for (int num : arr) {
            if (num == 0) zeroCount++;
        }
        return zeroCount;
    }

    private static int[] removeZeroes(int[] arr, int n, int zeroCount) {

        int[] arrWithOutZero = new int[n - zeroCount];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                arrWithOutZero[k] = arr[i];
                k++;
            }
        }

        return arrWithOutZero;
    }

}
