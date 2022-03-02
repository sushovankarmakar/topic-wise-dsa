package src;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class _167_TwoSum_II_SortedInputArray {

    public int[] twoSum(int[] nums, int target) {

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int[] indices = new int[2];

        while (left < right) {

            int sum = nums[left] + nums[right];
            if (sum == target) {
                indices[0] = left + 1;
                indices[1] = right + 1;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return indices;
    }
}
