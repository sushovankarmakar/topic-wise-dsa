package src.dp;

public class _20240611_1423_JumpGame1 {

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
