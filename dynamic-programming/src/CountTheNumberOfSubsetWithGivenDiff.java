package src;

/**
 * https://practice.geeksforgeeks.org/problems/perfect-sum-problem5633/1
 * https://www.youtube.com/watch?v=ot_XBHyqpFc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=12&ab_channel=AdityaVerma
 */
public class CountTheNumberOfSubsetWithGivenDiff {

    public static void main(String[] args) {

        System.out.println(perfectSum(new int[]{1, 0, 8, 5, 1, 4}, 6, 17));   // 4
        System.out.println(perfectSum(new int[]{6, 7, 0, 7, 3, 6}, 6, 13));   // 0

        System.out.println(perfectSum(new int[]{0, 0, 3, 6}, 4, 10));         // 0

        System.out.println(perfectSum(new int[]{2, 7, 2, 6}, 4, 12));         // 0
        System.out.println(perfectSum(new int[]{1, 3, 1, 3, 6, 2, 5, 0}, 8, 7));    // 16
        System.out.println(perfectSum(new int[]{2, 2}, 2, 0));         // 2
        System.out.println(perfectSum(new int[]{4, 3, 3, 7, 1, 0, 2, 4}, 8, 20));  // 2
        System.out.println(perfectSum(new int[]{8, 1}, 2, 21));     // 0
        System.out.println(perfectSum(new int[]{1}, 1, 4));         // 0
        System.out.println(perfectSum(new int[]{6, 8, 0}, 3, 9));   // 0

        System.out.println(perfectSum(new int[]{1, 2, 3}, 3, 1));   // 2
        System.out.println(perfectSum(new int[]{1, 1, 2, 3}, 4, 1));   // 3  ( here we have more than one 1)
    }

    /**
     * the input array may contain zero also.
     * <p>
     * this problem is very similar to CountOfSubsetsSumWithAGivenSum_WithZeros
     * https://practice.geeksforgeeks.org/problems/perfect-sum-problem5633/1
     */
    private static int perfectSum(int[] arr, int n, int diff) {

        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        if (diff > sum) return 0;   // corner case

        int s1 = (diff + sum) / 2;

        // from this point, the code is exactly similar to CountOfSubsetsSumWithAGivenSum_WithZeros
        int zeroCount = countZeroes(arr, n);
        arr = removeZeroes(arr, n, zeroCount);

        n = arr.length;

        int mod = 1000000007;

        int[][] dp = new int[n + 1][s1 + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= s1; j++) {

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

                    dp[i][j] = ((includingCurrVal % mod) + (excludingCurrVal % mod)) % mod;

                } else {
                    dp[i][j] = dp[i - 1][j] % mod;
                }
            }
        }

        return (dp[n][s1] * (int) Math.pow(2, zeroCount)) % mod;
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
