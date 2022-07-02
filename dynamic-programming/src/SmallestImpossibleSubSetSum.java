package src;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/b6b608d4eb1c45f2b5cace77c4914f302ff0f80d/1/
 * https://medium.com/dexters-lab/eli5-find-the-smallest-positive-integer-value-that-cannot-be-represented-as-sum-of-any-subset-of-f8ea2488184b (Best explanation)
 * <p>
 * https://www.youtube.com/watch?v=1h2eFrNFSSw&ab_channel=ProbingwithLalitKundu (A good explanation. Mentioned both approaches)
 * <p>
 * <p>
 * https://www.geeksforgeeks.org/find-smallest-value-represented-sum-subset-given-array/
 * https://www.codingninjas.com/codestudio/problems/find-smallest-integer_973253
 * https://stackoverflow.com/questions/21077763/smallest-number-that-can-not-be-formed-from-sum-of-numbers-from-array
 */
public class SmallestImpossibleSubSetSum {

    public static void main(String[] args) {
        System.out.println(smallestPositive_1(new long[]{1, 10, 3, 11, 6, 15}));    // 2
        System.out.println(smallestPositive_1(new long[]{1, 1, 1}));    // 4
        System.out.println(smallestPositive_1(new long[]{1, 2, 6, 10, 11, 15}));    // 4
        System.out.println(smallestPositive_1(new long[]{1, 2}));    // 4
    }

    /**
     * time complexity : O(nLogN)
     * space complexity : O(1)
     */
    private static long smallestPositive_1(long[] arr) {

        int n = arr.length;
        Arrays.sort(arr);

        if (n == 0 || arr[0] != 1) return 1;  // base case.     // if array contains zero, then omit this base case.

        long maxSum = arr[0];
        for (int i = 1; i < n; i++) {

            if (arr[i] - maxSum > 1) {
                break;
            }

            maxSum += arr[i];
        }

        return maxSum + 1;
    }

    // first approach :
    private static long smallestPositive_DP_approach(long[] arr, int n) {

        long sum = 1;

        while (isSubsetSumPossible(n, arr, sum)) {
            sum++;
        }

        return sum;
    }

    private static boolean isSubsetSumPossible(int n, long[] arr, long sum) {

        int sum1 = (int) sum;

        boolean[][] dp = new boolean[n + 1][sum1 + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum1; j++) {

                if (j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                if (i == 0) {
                    dp[i][j] = false;
                    continue;
                }

                if (arr[i - 1] <= j) {
                    boolean includingCurrVal = dp[i - 1][j - (int) arr[i - 1]];
                    boolean excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = includingCurrVal || excludingCurrVal;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum1];
    }

}
