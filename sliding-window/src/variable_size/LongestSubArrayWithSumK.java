package variable_size;

import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
 *
 * https://www.youtube.com/watch?v=xmguZ6GbatA&t=714s&ab_channel=takeUforward
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap
 */

public class LongestSubArrayWithSumK {

  public static void main (String[] args) {

    int[] arr = {1, 1, 1};
    int k = 3;

    System.out.println(getLongestSubArrayWithSumK(arr, k));
  }

  // prefix sum algorithm
  private static int getLongestSubArrayWithSumK(int[] arr, int k) {

    int n = arr.length;
    long prefixSum = 0;
    int maxLength = 0;
    Map<Long, Integer> sumWithIndex = new HashMap<>();

    for (int index = 0; index < n; index++) {
      prefixSum += arr[index];

      if (prefixSum == k) {
        maxLength = index + 1;
      }

      if (!sumWithIndex.containsKey(prefixSum)) {
        sumWithIndex.put(prefixSum, index);
      }

      if (sumWithIndex.containsKey(prefixSum - k)) {
        int prevIndex = sumWithIndex.get(prefixSum - k);
        int curLength = index - prevIndex;
        maxLength = Math.max(maxLength, curLength);
      }
    }
    return maxLength;
  }

  // below solution works fine if all the values in the given array are positive
  // if some values are negative then below sliding window algorithm won't work.
  private static int getLongestSubArrayWithSumK_OnlyForPositiveValues(int[] arr, int k) {
    int n = arr.length;
    int currSum = 0;
    int left = 0, right = 0;
    int maxLength = 0;

    for (; right < n; right++) {
      currSum += arr[right];

      if (currSum == k) {
        int windowSize = right - left + 1;
        maxLength = Math.max(maxLength, windowSize);
      }

      while (currSum > k) {
        currSum -= arr[left];
        left++;
      }
    }
    return maxLength;
  }

}
