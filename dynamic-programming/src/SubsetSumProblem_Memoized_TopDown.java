package src;

/**
 * https://www.geeksforgeeks.org/subset-sum-problem-dp-25/
 * https://practice.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1/
 * <p>
 * https://www.youtube.com/watch?v=_gPcYovP7wc&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=8&ab_channel=AdityaVerma
 */
public class SubsetSumProblem_Memoized_TopDown {

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
     * Time Complexity: O(sum * n), where sum is the ‘target sum’ and ‘n’ is the size of array. As we will recursive call will be maximum sum * n times.
     * Auxiliary Space: O(sum * n), as the size of 2-D array is sum * n.
     */
    static boolean isSubsetSum(int n, int[] arr, int sum) {

        boolean[][] dp = new boolean[n + 1][sum + 1];

        return func(arr, n, sum, dp);
    }

    private static boolean func(int[] arr, int n, int sum, boolean[][] dp) {

        if (sum == 0) return true;

        if (n == 0) return false;

        if (dp[n][sum]) return dp[n][sum];

        if (arr[n - 1] <= sum) {

            boolean includingCurrVal = func(arr, n - 1, sum - arr[n - 1], dp);
            boolean excludingCurrVal = func(arr, n - 1, sum, dp);

            return dp[n][sum] = includingCurrVal || excludingCurrVal;
        }

        return dp[n][sum] = func(arr, n - 1, sum, dp);
    }

}
