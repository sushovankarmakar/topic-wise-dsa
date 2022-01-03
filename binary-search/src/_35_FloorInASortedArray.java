package src;

/**
 * https://leetcode.com/problems/search-insert-position/ (Actually we need to find the floor)
 *
 * https://practice.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1 (Almost similar question)
 */
public class _35_FloorInASortedArray {

  /**
   * Find the floor of the target value.
   * https://leetcode.com/problems/search-insert-position/discuss/680006/to-find-the-floor-of-target-in-a-given-sorted-array
   *
   * OBSERVATION : Always return low if you don't find the target element.
   * https://www.youtube.com/watch?v=0A40XJH_VvE&ab_channel=TECHDOSE
   *
   * Why return low ? Explanation in below links.
   * https://www.youtube.com/watch?v=K-RYzDZkzCI&ab_channel=NeetCode
   * https://leetcode.com/problems/search-insert-position/discuss/1596628/JAVA-or-Binary-Search-or-Detailed-Explanation-Using-Image
   */
  public int searchInsert(int[] nums, int target) {

    int n = nums.length;
    int start = 0;
    int end = n - 1;

    while (start <= end) {

      int mid = start + (end - start)/2;

      if (nums[mid] == target) {
        return mid;
      } else if (target < nums[mid]) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }

    return start;
  }

}
