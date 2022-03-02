package src;

import java.util.*;

/**
 * https://leetcode.com/problems/3sum/
 * https://workat.tech/problem-solving/practice/three-sum
 * <p>
 * https://www.callicoder.com/three-sum-problem/
 * https://practice.geeksforgeeks.org/problems/triplet-sum-in-array-1587115621/1
 * <p>
 * https://practice.geeksforgeeks.org/problems/find-triplets-with-zero-sum/1
 */
public class _15_3Sum {

    public static void main(String[] args) {
        /**
         * INPUT :
         *
         * [-1,0,1,2,-1,-4]
         * []
         * [0]
         * [1,1,0,-1,-2]
         */

        /**
         * OUTPUT :
         *
         * [[-1,-1,2],[-1,0,1]]
         * []
         * []
         * [[-2,1,1],[-1,0,1]]
         */
    }

    /**
     * https://www.youtube.com/watch?v=onLoX6Nhvmg&ab_channel=takeUforward
     * <p>
     * https://leetcode.com/problems/3sum/discuss/7380/Concise-O(N2)-Java-solution (I followed this)
     * https://www.geeksforgeeks.org/find-triplets-array-whose-sum-equal-zero/
     */

    // time : 19ms
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int z = n - 1; z > 1; z--) {

            // we want to skip equal elements to avoid duplicates in the answer without making a set.
            if ((z < n - 1) && nums[z] == nums[z + 1]) {
                continue;
            }

            int x = 0;
            int y = z - 1;
            while (x < y) {

                int sum = nums[x] + nums[y] + nums[z];

                if (sum == 0) {
                    output.add(Arrays.asList(nums[x], nums[y], nums[z]));

                    while (x < y && nums[x] == nums[x + 1]) x++;  // skip duplicates
                    while (x < y && nums[y] == nums[y - 1]) y--;  // skip duplicates

                    x++;
                    y--;

                } else if (sum > 0) {
                    y--;
                } else { // sum < 0
                    x++;
                }
            }
        }
        return output;
    }

    // You can avoid all the complex checks and edge cases by using a set.
    // https://leetcode.com/problems/3sum/discuss/7399/Easiest-Java-Solution/496919
    // time : 459ms
    public List<List<Integer>> threeSum_withSet(int[] nums) {

        Set<List<Integer>> output = new HashSet<>();

        Arrays.sort(nums);
        int n = nums.length;

        for (int z = n - 1; z > 1; z--) {

            int x = 0;
            int y = z - 1;

            while (x < y) {

                int sum = nums[x] + nums[y] + nums[z];

                if (sum == 0) {
                    output.add(Arrays.asList(nums[x], nums[y], nums[z]));

                    x++;    // don't use break here, it will miss a lot of combinations.
                    y--;
                } else if (sum > 0) {
                    y--;
                } else { // sum < 0
                    x++;
                }
            }
        }
        return new ArrayList<>(output);
    }

}
