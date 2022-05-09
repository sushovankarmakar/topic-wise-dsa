package src;

/**
 * https://practice.geeksforgeeks.org/problems/perfect-sum-problem5633/1
 * https://www.geeksforgeeks.org/count-of-subsets-with-sum-equal-to-x/
 *
 * https://www.youtube.com/watch?v=F7wqWbqYn9g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=10&ab_channel=AdityaVerma
 */
public class CountOfSubsetsSumWithAGivenSum_WithZeros {

    public static void main(String[] args) {
        System.out.println(perfectSum(new int[]{9, 7, 0, 3, 9, 8, 6, 5, 7, 6}, 10, 31));
    }

    /**
     * The test cases are not wrong.
     * If array contains zeros then remove those zeros and count number of subsets.
     * Then multiply this count with 2^(number of zeros in array)
     * because every zero in array has a choice of being included or not.
     *
     * We are only doing wrong in base case where we initialize dp[i][0]=1;
     */
    // https://discuss.geeksforgeeks.org/comment/698ab84d916d25c53e2ce40ee37ca396

    /**
     * Here extra we have to take care of zeroes also.
     *
     * When the input array has any zeros: For eg: n=3, sum=0.
     * We can have a set here as {0,1,2}, so there'll be subsets of {} and {0} possible hence count will be 2.
     *
     * This is true for multiple other input arrays where actually count >1 for sum=0,
     * but we initialized count=1 for all input arrays when sum=0.
     *
     * Here's a small fix I found to the issue:
     * We initialize the first column of the array acc to the formula: 2^(no of zeros in the array at that size).
     *
     * Hence, for eg: arr={0,0,1,0}, sum=0
     *
     * For n=0, value will be 2^0 = 1, i.e {}
     * For n=1, value will be 2^1 = 2, i.e. {} and {0}
     * For n=2, value will be 2^2= 4, i.e. {}, {0}, {0} and {0,0}
     * For n=3, value will be 2^2 = 4, i.e. {}, {0], {0}, and {0,0}
     * For n=4, value will be 2^3 = 8 i.e. {}, .............., {0,0,0}
     *
     * Reason: In the sum of subset problem, we simply needed to return whether a set exists for sum=0,
     * which was always True as empty set {} was always existing.
     * Here, we need to return count of subsets for sum=0 (for first column),
     * which will include all possible subsets in the array which add up to 0.
     */
    // https://discuss.geeksforgeeks.org/comment/dc6264a0d32086f3d03c4f7187a0ed97

    private static int perfectSum(int[] arr, int n, int sum) {

        int zeroCount = countZeroes(arr, n);
        arr = removeZeroes(arr, n, zeroCount);

        n = arr.length;

        int mod = 1000000007;

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

                    dp[i][j] = ((includingCurrVal % mod) + (excludingCurrVal % mod)) % mod;

                } else {
                    dp[i][j] = dp[i - 1][j] % mod;
                }
            }
        }

        return (dp[n][sum] * (int) Math.pow(2, zeroCount)) % mod;
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
