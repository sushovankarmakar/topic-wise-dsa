package src;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * https://practice.geeksforgeeks.org/problems/minimum-number-of-jumps-1587115620/1
 *
 * (Minimum number of jumps)
 */
public class JumpGame2_45 {

  public static void main(String[] args) {
    System.out.println(minimumJumps_dp(new int[]{2, 3, 1, 1, 2, 4, 2, 0, 1, 1})); // 4
    System.out.println(minimumJumps_dp(new int[]{0, 1, 2, 3})); // -1
    System.out.println(minimumJumps_dp(new int[]{0})); // 0

    System.out.println(minimumJumps(new int[]{2, 3, 1, 1, 2, 4, 2, 0, 1, 1})); // 4
    System.out.println(minimumJumps(new int[]{0, 1, 2, 3})); // -1
    System.out.println(minimumJumps(new int[]{0})); // 0
    System.out.println(minimumJumps(new int[]{3, 2, 1, 0, 4})); // -1
    System.out.println(minimumJumps(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9})); // 3
  }

  // LINEAR SOLUTION
  /**
   * https://afteracademy.com/blog/minimum-number-of-jumps-to-reach-end
   *
   * Similar concepts
   * 1. https://www.youtube.com/watch?v=vBdo7wtwlXs&ab_channel=IDeserve
   * 2. https://www.youtube.com/watch?v=CqgK_qi4SKQ&ab_channel=CodeLibrary
   * 3. https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Greedy_MinNumOfJumpsToReachEnd.java
   */
  /**
   * (I followed the below code)
   * https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy/287433
   */
  public static int minimumJumps(int[] nums) {
    int len = nums.length;

    int currReach = 0;
    int maxReach = 0;
    int jumps = 0;
    int lastIndex = nums.length - 1;

    for (int i = 0; i < len; i++) {

      if (i > maxReach) return -1;

      maxReach = Math.max(maxReach, i + nums[i]);

      /**
       * i < lastIndex ensures that we only increment jumps until the last index,
       * if we are last index i.e i == lastIndex, we don't need to jump further, so don't increment jumps.
       */
      if (i < lastIndex && i == currReach) {
        jumps++;

        currReach = maxReach;
      }
    }

    return jumps;
  }

  // DP SOLUTION
  /**
   * https://www.youtube.com/watch?v=cETfFsSTGJI&ab_channel=TusharRoy-CodingMadeSimple
   * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MinJumpToReachEnd.java
   * https://www.youtube.com/watch?v=IKxezpfH4aw&ab_channel=CodingSimplified
   *
   * Time complexity : O(n^2)
   * Space complexity : O(n)
   */
  public static int minimumJumps_dp(int[] nums) {

    int len = nums.length;
    int[] jumps = new int[len];
    jumps[0] = 0;   // corner case, when there is one number, no need to jump
    for (int i = 1; i < len; i++) {
      jumps[i] = Integer.MAX_VALUE;
    }

    for (int end = 1; end < len; end++) {

      for (int start = 0; start < end; start++) {

        /**
         * if jumps[start] == Integer.MAX_VALUE, then invalid check.
         */
        if (start + nums[start] >= end && jumps[start] != Integer.MAX_VALUE) {
          jumps[end] = Math.min(jumps[end], jumps[start] + 1);
        }
      }
    }

    int minJumpsToReachEnd = jumps[len - 1];
    if (minJumpsToReachEnd == Integer.MAX_VALUE) { //if this condition true then we couldn't reach end.
      minJumpsToReachEnd = -1;
    }

    return minJumpsToReachEnd;
  }

}
