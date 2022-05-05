package src;

/**
 * https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * https://practice.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1/
 * <p>
 * https://www.youtube.com/watch?v=kvyShbFVaY8&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=3&ab_channel=AdityaVerma
 */

/**
 * the recursive solution is top-down approach as we started at the top and kept making recursive calls
 * until we reached the base case and then started returning values
 */
public class _01Knapsack_Recursive_TopDown {

    public static void main(String[] args) {

        int[] val = new int[]{60, 100, 120};
        int[] wt = new int[]{10, 20, 30};
        int W = 50;
        int n = val.length;
        System.out.println(knapsack_recursive(W, wt, val, n));  // 220
    }

    static int knapsack_recursive(int W, int[] wt, int[] val, int n) {

        if (n == 0 || W == 0) {
            return 0;
        }

        if (wt[n - 1] <= W) {

            int profitWithInclusion = val[n - 1] + knapsack_recursive(W - wt[n - 1], wt, val, n - 1);
            int profitWithExclusion = knapsack_recursive(W, wt, val, n - 1);

            return Math.max(profitWithInclusion, profitWithExclusion);
        } else {
            return knapsack_recursive(W, wt, val, n - 1);
        }
    }
}
