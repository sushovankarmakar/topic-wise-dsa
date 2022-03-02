package src;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/3sum-closest/
 * https://www.callicoder.com/triplet-sum-closest-to-target/
 * https://practice.geeksforgeeks.org/problems/three-sum-closest/1/
 *
 * https://leetcode.com/problems/3sum-closest/discuss/7883/C%2B%2B-solution-O(n2)-using-sort
 */
public class _16_3SumClosest {

    /**
     * Very similar to normal 3 sum problem.
     */
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);
        int n = nums.length;
        int closest = nums[0] + nums[n - 2] + nums[n - 1];

        for (int i = n - 1; i >= 1; i--) {

            int left = 0;
            int right = i - 1;

            while (left < right) {

                int sum = nums[left] + nums[right] + nums[i];

                if (sum == target) {
                    return target;

                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }

                if (Math.abs(target - sum) < Math.abs(target - closest)) { // IMP :
                    closest = sum;
                }
            }
        }
        return closest;
    }
}
