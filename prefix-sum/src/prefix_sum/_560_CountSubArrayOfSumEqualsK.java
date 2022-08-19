package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * <p>
 * https://www.youtube.com/watch?v=20v8zSo2v18&t=7s&ab_channel=Pepcoding
 * <p>
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap
 * <p>
 * similar : LongestSubArrayWithSumK, https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
 */

public class _560_CountSubArrayOfSumEqualsK {

    public int subArraySum(int[] nums, int k) {

        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefSumWithFreq = new HashMap<>(); // key : prefix sum, value : number of times this prefix sum was seen.

        // prefSumWithFreq.put(0, 1); // important : this line is SIMILAR to first if condition in 34th line.

        for (int num : nums) {
            prefixSum += num;

            /**
             * Two cases where we can update maxLen
             * CASE 1 :
             * Check if all the numbers seen so far sum to k.
             * If cumulative sum is k, then increment count.
             */
            if (prefixSum == k) {
                count++;
            }

            /**
             * CASE 2 :
             * If any sub array seen so far sums to k, then increment count.
             * If cumulative sum is more than k, but we can truncate a prefix of the array
             */
            if (prefSumWithFreq.containsKey(prefixSum - k)) {
                count += prefSumWithFreq.get(prefixSum - k);
            }

            prefSumWithFreq.put(prefixSum, prefSumWithFreq.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
