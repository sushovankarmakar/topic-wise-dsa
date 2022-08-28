package src;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Weekly Contest 308
 * <p>
 * https://leetcode.com/problems/longest-subsequence-with-limited-sum/
 * <p>
 * https://www.youtube.com/watch?v=SjGqEq0_Q5A
 * https://www.youtube.com/watch?v=vpWBTmazGjU
 * https://leetcode.com/problems/longest-subsequence-with-limited-sum/discuss/2493076/Java-solution (A good solution using TreeMap)
 */
public class _2389_LongestSubsequenceWithLimitedSum {

    /**
     * Mistake I made :
     * I used recursion + memo but that result in TLE may be because of constraints.
     * https://leetcode.com/contest/weekly-contest-308/submissions/detail/785206656/ (My recursion + memo solution)
     */

    /**
     * APPROACH - 1
     * greedy + prefix sum + treemap
     * <p>
     * https://leetcode.com/problems/longest-subsequence-with-limited-sum/discuss/2493076/Java-solution
     * https://www.geeksforgeeks.org/java-util-treemap-floorentry-floorkey-java/
     */
    private static int[] answerQueries_usingTreeMap(int[] nums, int[] queries) {

        Arrays.sort(nums);

        int m = nums.length;
        int n = queries.length;
        int[] op = new int[n];

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int prefSum = 0;

        for (int i = 0; i < m; i++) {

            prefSum += nums[i];
            map.put(prefSum, i + 1);
        }

        for (int i = 0; i < n; i++) {
            op[i] = map.floorEntry(queries[i]) == null ? 0 : map.floorEntry(queries[i]).getValue();
        }

        return op;
    }

    /**
     * APPROACH - 2
     * greedy + prefix sum + binary search
     * <p>
     * order actually does NOT matter. Sort list and take prefix sum. Apply binary search to find length.
     * <p>
     * https://leetcode.com/problems/longest-subsequence-with-limited-sum/discuss/2492786/sort-and-count-java (took help from this)
     */
    private static int[] answerQueries_usingBinarySearch(int[] nums, int[] queries) {

        Arrays.sort(nums);

        int m = nums.length;
        int n = queries.length;
        int[] op = new int[n];

        for (int i = 1; i < m; i++) {
            nums[i] += nums[i - 1];
        }

        for (int i = 0; i < n; i++) {
            op[i] = binarySearch(nums, queries[i]);
        }

        return op;
    }

    private static int binarySearch(int[] nums, int target) {

        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[left] <= target ? left + 1 : left;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(answerQueries_usingBinarySearch(new int[]{4, 5, 2, 1}, new int[]{3, 10, 21})));    // [2, 3, 4]
        System.out.println(Arrays.toString(answerQueries_usingBinarySearch(new int[]{7, 1, 9, 3, 2}, new int[]{7}))); // [3]
        System.out.println(Arrays.toString(answerQueries_usingTreeMap(new int[]{2, 3, 4, 5}, new int[]{1})));    // [0]
    }
}
