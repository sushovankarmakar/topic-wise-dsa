package src;

/**
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/DP_ContiguousSubArrayMaxProduct.java
 * (All the important links are present above link)
 *
 * https://leetcode.com/problems/maximum-product-subarray/
 * https://practice.geeksforgeeks.org/problems/maximum-product-subarray3604/1
 *
 * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
 * above link's answer's intuition is well explained in this link (https://www.youtube.com/watch?v=tHNsZHXnYd4&ab_channel=CodeLibrary)
 */

public class _152_MaxProductSubarray {

  public static void main(String[] args) {
    System.out.println(maxProduct(new int[]{2,3,-2,4}));  // 6
    System.out.println(maxProduct(new int[]{-2,0,-1}));   // 0
    System.out.println(maxProduct(new int[]{6, -3, -10, 0, 2}));   // 180
    System.out.println(maxProduct(new int[]{2, 3, 4, 5, -1, 0}));   // 120
  }

  public static int maxProduct(int[] nums) {

    int max = nums[0];
    int min = nums[0];
    int ans = nums[0];  // store the result that is the max we have found so far

    for (int i = 1; i < nums.length; i++) {
      if (nums[i] < 0) {
        // multiplied by a negative makes big number smaller, small number bigger
        // so we redefine the extremums by swapping them
        int temp = max;
        max = min;
        min = temp;
      }

      // max/min product for the current number is either the current number itself
      // or the max/min by the previous number times the current one
      max = Math.max(nums[i], nums[i] * max);
      min = Math.min(nums[i], nums[i] * min);

      ans = Math.max(ans, max);
    }
    return ans;
  }

}
