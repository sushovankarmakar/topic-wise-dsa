package variable_size;

import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1
 * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
 * <p>
 * https://www.geeksforgeeks.org/longest-sub-array-sum-k/
 * <p>
 * Similar question
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * https://www.enjoyalgorithms.com/blog/largest-subarray-with-zero-sum
 * https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1 (here k == 0)
 * <p>
 * https://www.youtube.com/watch?v=xmguZ6GbatA&t=714s&ab_channel=takeUforward (BEST Video - I understood from this)
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/Java-Solution-PreSum-%2B-HashMap
 * <p>
 * https://www.youtube.com/watch?v=cyu_nuW5utA&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=8 (Aditya Verma) - won't work if array contains negative numbers.
 * https://www.youtube.com/watch?v=TfQPoaRDeMQ&list=PL_z_8CaSLPWeM8BDJmIYDaoQ5zuwyxnfj&index=9 (Aditya Verma)
 * <p>
 * https://www.youtube.com/watch?v=ReZpa5vxRKc
 */

public class _325_LongestSubArrayOfSumK_PrefixSum {

    public static void main(String[] args) {

        int[] arr = {1, 1, 1};
        int k = 3;

        System.out.println(getLongestSubArrayWithSumK(arr, k));
    }

    /**
     * PREFIX SUM ALGORITHM
     * <p>
     * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/solution/
     * https://www.youtube.com/watch?v=xmguZ6GbatA (striver)
     * https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/discuss/77784/O(n)-clean-short-JavaPython-solution-with-HashMap-(with-code-comments)
     */
    private static int getLongestSubArrayWithSumK(int[] arr, int k) {

        int n = arr.length;
        long prefixSum = 0;
        int maxLength = 0;
        Map<Long, Integer> sumWithIndex = new HashMap<>(); // key : prefix sum, value : the first index where this prefix sum was seen.

        for (int index = 0; index < n; index++) {
            prefixSum += arr[index];

            /**
             * Two cases where we can update maxLen
             * CASE 1 :
             * Check if all the numbers seen so far sum to k.
             * cumulative sum is k, update maxLen for sure
             */
            if (prefixSum == k) {
                maxLength = index + 1;
            }
            /**
             * CASE 2 :
             * If any sub array seen so far sums to k, then update the length of the longest sub array.
             * cumulative sum is more than k, but we can truncate a prefix of the array
             */
            if (sumWithIndex.containsKey(prefixSum - k)) {

                int prevIndex = sumWithIndex.get(prefixSum - k);
                int curLength = index - prevIndex;

                maxLength = Math.max(maxLength, curLength);
            }

            /**
             * Only add the current prefix_sum index pair to the map if the prefix_sum is not already in the map.
             *
             * store cumulative sum in map, only if it is not seen
             * because only the earlier (thus shorter) sub array is valuable,
             * when we want to get the maxLen after truncation
             */
            if (!sumWithIndex.containsKey(prefixSum)) {
                // also we're only storing first index of the prefixSum, so we're not updating the index later even if
                // we found same prefixSum later.
                // Storing the only first index gives us the longest value.
                sumWithIndex.put(prefixSum, index);
            }
        }
        return maxLength;
    }

    /**
     * SLIDING WINDOW TECHNIQUE
     * <p>
     * below solution works fine if all the values in the given array are positive
     * if some values are negative then below sliding window algorithm won't work.
     */
    //
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

    /**
     * above method and below method are same
     * but
     * below code is easy to visualize.
     */
    private static int getLongestSubArrayWithSumK_OnlyForPositiveValues_UsingSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        int currSum = 0;
        int left = 0, right = 0;
        int maxLength = 0;

        while (right < n) {
            currSum += arr[right];

            if (currSum < k) {  // condition 1
                right++;

            } else if (currSum == k) { // condition 2

                int windowSize = right - left + 1;
                maxLength = Math.max(maxLength, windowSize);
                right++;

            } else if (currSum > k) { // condition 3

                while (currSum > k) {
                    currSum -= arr[left];
                    left++;
                }
            }
        }
        return maxLength;
    }

}
