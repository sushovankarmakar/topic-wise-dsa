package src;

/**
 * https://leetcode.com/problems/jump-game/
 * https://practice.geeksforgeeks.org/problems/jump-game/1
 *
 * https://www.youtube.com/watch?v=muDPTDrpS28&t=31s&ab_channel=TECHDOSE (I followed this)
 *
 * (below link, first discussed recursive overlapping approach, and the solved it by greedy)
 * https://www.youtube.com/watch?v=Yan0cv2cLy8&ab_channel=NeetCode
 *
 * https://www.youtube.com/watch?v=A-Mc_0WsoaM&ab_channel=codeExplainer (explained jumped game 1, 2, 3)
 */
public class JumpGame1_55 {

  public static void main(String[] args) {
    System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));  // true
    System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));  // false
  }

  /**
   * Although it is under dp section, but here we've solved it by greedy approach.
   */
  public static boolean canJump(int[] nums) {
    int len = nums.length;
    int maxReachableDistance = 0;

    for (int i = 0; i < len; i++) {

      // this check should be at first, cause later maxReachableDistance value changes.
      if (i > maxReachableDistance) { // we are already beyond maxReachableDistance seen so far.
        return false;
      }

      int currReachableDistance = i + nums[i];

      if (maxReachableDistance < currReachableDistance) {
        maxReachableDistance = currReachableDistance;
      }
    }
    return true;
  }

}
