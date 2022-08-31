package src.knapsack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/find-all-distinct-subset-or-subsequence-sums4424/1
 * https://www.geeksforgeeks.org/flipkart-interview-experience-set-46-campus-sde-1/ (Second question. Asked in Flipkart.)
 */
public class FindAllDistinctSubSetSums {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(distinctSum(new int[]{1, 2}))); // [0, 1, 2, 3]
        System.out.println(Arrays.toString(distinctSum(new int[]{1, 2, 2})));   // for such input, we need to do memoization. // [0, 1, 2, 3, 4, 5]
    }

    private static Set<Integer> sums;
    private static int[][] dp;

    /**
     * LOGIC : recursion + memoization
     * ------------------
     * 1. recursion :
     * question based on knapsack.
     * go from last index and at each number we have two options, either we take it or don't take it.
     * ------------------
     * (why we need memoization ? ans : draw a recursion tree for input {1, 2, 2}. )
     * 2. memoization :
     * we need to find the maxSum possible from given input array.
     * and then create a dp matrix of [n + 1][maxSum + 1];
     */
    private static int[] distinctSum(int[] nums) {

        int n = nums.length;

        int maxSum = 0;
        for (int num : nums) {
            maxSum += num;
        }

        dp = new int[n + 1][maxSum + 1];
        sums = new HashSet<>();

        findSum(nums, n, 0);

        int[] distinctSums = new int[sums.size()];
        int i = 0;
        for (int sum : sums) {
            distinctSums[i++] = sum;
        }
        Arrays.sort(distinctSums);
        return distinctSums;
    }

    private static void findSum(int[] nums, int n, int sum) {

        if (n == 0) {
            sums.add(sum);
            return;
        }

        if (dp[n][sum] == 1) {  // memoization
            return;
        }

        findSum(nums, n - 1, sum + nums[n - 1]);
        findSum(nums, n - 1, sum);

        dp[n][sum] = 1; // memoization.
    }
}
