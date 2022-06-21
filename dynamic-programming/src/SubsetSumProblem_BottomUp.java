package src;

/**
 * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 * https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/
 * <p>
 * https://www.youtube.com/watch?v=_gPcYovP7wc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=8&ab_channel=AdityaVerma
 *
 * https://practice.geeksforgeeks.org/problems/b6b608d4eb1c45f2b5cace77c4914f302ff0f80d/1/ (Similar concept wise question)
 */
public class SubsetSumProblem_BottomUp {

    public static void main(String[] args) {
        int[] arr1 = {3, 34, 4, 12, 5, 2};
        int sum1 = 30;
        System.out.println(isSubsetSum(arr1.length, arr1, sum1));

        int[] arr2 = {2, 3, 7, 8, 10};
        int sum2 = 11;
        System.out.println(isSubsetSum(arr2.length, arr2, sum2));
    }

    /**
     * Complexity Analysis:
     * <p>
     * Time Complexity: O(sum * n), where sum is the ‘target sum’ and ‘n’ is the size of array.
     * Auxiliary Space: O(sum * n), as the size of 2-D array is sum * n.
     */

    static boolean isSubsetSum(int N, int[] arr, int sum) {

        boolean[][] dp = new boolean[N + 1][sum + 1];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {

                // j == 0 means first column. so irrespective of number of input array size, if the sum is zero then
                // it is always possible to get a subset (which is empty) whose sum is zero.
                if (j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                // i == 0 means first row. so irrespective of sum, if there is no value in the array then
                // it is always impossible to get a subset whose sum is equal with given sum.
                if (i == 0) {
                    dp[i][j] = false;
                    continue;
                }

                // arr[i - 1] <= j means if the current value is smaller than the sum, then we have a choice to
                // either take that value or don't take that value.
                if (arr[i - 1] <= j) {
                    boolean includingCurrVal = dp[i - 1][j - arr[i - 1]];
                    boolean excludingCurrVal = dp[i - 1][j];

                    dp[i][j] = includingCurrVal || excludingCurrVal;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[N][sum];
    }

}
