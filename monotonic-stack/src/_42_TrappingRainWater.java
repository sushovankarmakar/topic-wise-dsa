package src;

/**
 * https://practice.geeksforgeeks.org/problems/trapping-rain-water-1587115621/1
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * https://www.geeksforgeeks.org/trapping-rain-water/
 * https://workat.tech/problem-solving/practice/trapped-rain-water/editorial
 *
 * https://www.youtube.com/watch?v=m18Hntz4go8&ab_channel=takeUforward
 * https://www.youtube.com/watch?v=FbGG2qpNp4U&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=C8UjlJZsHBw&ab_channel=TECHDOSE
 */
public class _42_TrappingRainWater {

  public static void main(String[] args) {
    System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6
    System.out.println(trap(new int[]{4,2,0,3,2,5})); // 9
    System.out.println(trap(new int[]{2,3})); // 0
    System.out.println(trap(new int[]{7,4,0,9})); // 10
    System.out.println(trap(new int[]{6,9,9})); // 0
    System.out.println(trap(new int[]{3,0,0,2,0,4})); // 10

    System.out.println(trap_space_optimized_solution(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})); // 6
  }

  /**
   * Time  complexity : O(n)
   * Space complexity : O(n)
   */
  public static int trap(int[] heights) {

    if (heights.length <= 2) {
      return 0;
    }

    int[] leftMax = findLeftMax(heights);
    int[] rightMax = findRightMax(heights);

    return totalAmountOfWater(heights, leftMax, rightMax);
  }

  private static int[] findLeftMax(int[] heights) {

    int n = heights.length;
    int[] leftMax = new int[n];
    int max = Integer.MIN_VALUE;

    for (int i = 0; i < n; i++) {
      max = Math.max(max, heights[i]);
      leftMax[i] = max;
    }
    return leftMax;
  }

  private static int[] findRightMax(int[] heights) {

    int n = heights.length;
    int[] rightMax = new int[n];
    int max = Integer.MIN_VALUE;

    for (int i = n - 1; i >= 0; i--) {
      max = Math.max(max, heights[i]);
      rightMax[i] = max;
    }
    return rightMax;
  }

  private static int totalAmountOfWater(int[] heights, int[] leftMax, int[] rightMax) {

    int amount = 0;
    int n = heights.length;

    for (int i = 0; i < n; i++) {
      amount += (Math.min(leftMax[i], rightMax[i]) - heights[i]);
    }

    return amount;
  }

  // https://www.youtube.com/watch?v=m18Hntz4go8&t=273s&ab_channel=takeUforward
  // (GREAT EXPLANATION- i understood from this)
  /**
   * Time  complexity : O(n)
   * Space complexity : O(1)
   */
  public static int trap_space_optimized_solution(int[] heights) {

    int n = heights.length;
    int left = 0;
    int right = n - 1;
    int leftMax = 0;
    int rightMax = 0;
    int totalWater = 0;

    /**
     * between left height and right height, take smaller value.
     * and each height, always do two things.
     * step 1. either it is the max, then update it
     * step 2. if it not max, subtract from max and add to the total water.
     */
    while (left <= right) {


      if (heights[left] <= heights[right]) {

        if (heights[left] >= leftMax) {
          leftMax = heights[left];  // step 1
        } else {
          totalWater += (leftMax - heights[left]); // step 2
        }
        left++;
      } else {
        if (heights[right] >= rightMax) {
          rightMax = heights[right]; // step 1
        } else {
          totalWater += (rightMax - heights[right]); // step 2
        }
        right--;
      }
    }
    return totalWater;
  }
}
