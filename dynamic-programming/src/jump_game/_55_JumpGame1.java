package src.jump_game;

/**
 * https://leetcode.com/problems/jump-game/
 * https://practice.geeksforgeeks.org/problems/jump-game/1
 * <p>
 * https://www.youtube.com/watch?v=muDPTDrpS28&t=31s&ab_channel=TECHDOSE (I followed this)
 * <p>
 * (below link, first discussed recursive overlapping approach, and the solved it by greedy)
 * https://www.youtube.com/watch?v=Yan0cv2cLy8&ab_channel=NeetCode
 * <p>
 * https://www.youtube.com/watch?v=A-Mc_0WsoaM&ab_channel=codeExplainer (explained jumped game 1, 2, 3)
 */
public class _55_JumpGame1 {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));  // true
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));  // false
    }

    // https://chatgpt.com/share/1553a977-f34b-4c48-a613-1fb57a4c0f41
    /**
     * INTUITION :
     * ----------
     * The basic idea is to
     * keep track of the farthest index we can reach
     * as we iterate through the array.
     *
     * If at any point
     * the farthest index we can reach is less than the current index,
     * we know we can't proceed further.
     */

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
