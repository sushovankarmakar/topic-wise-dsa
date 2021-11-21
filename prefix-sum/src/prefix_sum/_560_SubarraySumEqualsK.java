package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * https://www.youtube.com/watch?v=20v8zSo2v18&t=7s&ab_channel=Pepcoding
 *
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap
 *
 * similar : LongestSubArrayWithSumK, https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
 */

public class _560_SubarraySumEqualsK {

  public int subarraySum(int[] nums, int k) {

    int count = 0;
    int prefixSum = 0;
    Map<Integer, Integer> prefSumFreqMap = new HashMap<>();
    prefSumFreqMap.put(0, 1); // imp

    for (int num : nums) {
      prefixSum += num;

      if (prefSumFreqMap.containsKey(prefixSum - k)) {
        count += prefSumFreqMap.get(prefixSum - k);
      }

      prefSumFreqMap.put(prefixSum, prefSumFreqMap.getOrDefault(prefixSum, 0) + 1);
    }
    return count;
  }
}
