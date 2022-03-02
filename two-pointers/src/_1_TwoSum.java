package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.callicoder.com/two-sum-problem/
 * https://leetcode.com/problems/two-sum/
 */
public class _1_TwoSum {

    public int[] twoSum_usingMap(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();
        int[] indices = new int[2];

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {
                indices[0] = map.get(target - nums[i]);
                indices[1] = i;
                break;
            }

            map.put(nums[i], i);
        }
        return indices;
    }

    // --------------------------------------------------------------------------------------------

    public int[] twoSum_twoPointers(int[] nums, int target) {

        int n = nums.length;
        Pair[] pairs = new Pair[n];

        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(nums[i], i);
        }

        Arrays.sort(pairs, (p1, p2) -> p1.val - p2.val);

        int left = 0;
        int right = n - 1;
        int[] indices = new int[2];

        while (left < right) {

            int sum = pairs[left].val + pairs[right].val;
            if (sum == target) {
                indices[0] = pairs[left].idx;
                indices[1] = pairs[right].idx;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return indices;
    }

    class Pair {
        int val;
        int idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}
