package src;

/**
 * https://practice.geeksforgeeks.org/problems/min-cost-climbing-stairs/1
 * https://leetcode.com/problems/min-cost-climbing-stairs/
 *
 * https://www.youtube.com/watch?v=hekG82t4U_M&ab_channel=AndreyGrehov (Great Explanation)
 * https://www.geeksforgeeks.org/minimum-cost-to-reach-the-top-of-the-floor-by-climbing-stairs/
 *
 * https://leetcode.com/discuss/general-discussion/712010/the-art-of-dynamic-programming-an-intuitive-approach-from-apprentice-to-master/
 */
public class _746_MinCostClimbingStairs {

  public static void main(String[] args) {
    System.out.println(minCostClimbingStairs(new int[]{10, 15, 20})); // 15
    System.out.println(minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1})); // 6
    System.out.println(minCostClimbingStairs(new int[]{16, 19, 10, 12, 18})); // 31
    System.out.println(minCostClimbingStairs(new int[]{2, 5, 3, 1, 7, 3, 4})); // 9

    System.out.println(minCostClimbingStairs_2(new int[]{10, 15, 20})); // 15
    System.out.println(minCostClimbingStairs_2(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1})); // 6
    System.out.println(minCostClimbingStairs_2(new int[]{16, 19, 10, 12, 18})); // 31
  }

  // time complexity : O(N)
  // space complexity : O(N)

  // APPROACH - 1 (My own intuitive approach)
  public static int minCostClimbingStairs(int[] cost) {

    int n = cost.length;
    int[] minCost = new int[n + 1];

    minCost[0] = 0; // 0 cost needed to reach 0th index
    minCost[1] = 0; // 0 cost needed to reach 1st index

    /**
     * recurrence relation :
     * minCost(i) = min(costToReachFromLastIndex, costToReachFrom2ndLastIndex);
     */
    for (int i = 2; i <= n; i++) {

      int costToReachFromLastIndex = minCost[i - 1] + cost[i - 1];
      int costToReachFrom2ndLastIndex = minCost[i - 2] + cost[i - 2];

      minCost[i] = Math.min(costToReachFromLastIndex, costToReachFrom2ndLastIndex);
    }

    return minCost[n];
  }

  // https://leetcode.com/problems/min-cost-climbing-stairs/discuss/476388/4-ways-or-Step-by-step-from-Recursion-greater-top-down-DP-greater-bottom-up-DP-greater-fine-tuning
  // https://leetcode.com/problems/min-cost-climbing-stairs/discuss/110111/The-ART-of-dynamic-programming
  // APPROACH - 2 (Leetcode and GFG article approach)
  public static int minCostClimbingStairs_2(int[] cost) {

    int n = cost.length;
    int[] minCost = new int[n];

    minCost[0] = cost[0];
    minCost[1] = cost[1];

    /**
     * Recurrence Relation:
     * minCost(i) = cost[i] + min(minCost(i-1), minCost(i-2))
     */
    for (int i = 2; i < n; i++) {
      minCost[i] = cost[i] + Math.min(minCost[i - 1], minCost[i - 2]);
    }

    // we can reach top, from either last index(n - 1) or 2nd last index(n -2).
    // and we need to take the minimum of these two.
    return Math.min(minCost[n - 1], minCost[n - 2]);

  }
}
