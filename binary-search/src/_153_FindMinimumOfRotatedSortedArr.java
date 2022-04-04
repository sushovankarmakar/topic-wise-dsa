package src;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class _153_FindMinimumOfRotatedSortedArr {

  public static void main(String[] args) {
    System.out.println(findMin(new int[]{1, 2, 3}));
    System.out.println(findMin(new int[]{3, 1, 2}));
    System.out.println(findMin(new int[]{4, 3, 1, 2}));
    System.out.println(findMin(new int[]{7, 6, 5, 1, 10}));
    System.out.println(findMin(new int[]{10, 6, 5, 1, 7}));
    System.out.println(findMin(new int[]{7, 1, 5, 6, 10}));
    System.out.println(findMin(new int[]{2, 1, 3}));
    //System.out.println(findMin(new int[]{}));
  }

  /**
   * https://www.youtube.com/watch?v=vF7gk4iaklA&ab_channel=Pepcoding (A good understanding from diagram)
   * https://www.youtube.com/watch?v=CihX9yKR2xk&ab_channel=CodingDecoded (A good solution with example)
   */
  /**
   * Intuition :
   * After diving, we'll always find the minimum element in the unsorted side,
   * so discard the sorted side and go the unsorted side.
   */
  public static int findMin(int[] nums) {

    int n = nums.length;
    int start = 0;
    int end = n - 1;

    while (start < end) {

      if (nums[start] < nums[end]) return nums[start];  // if the array is not rotated.

      int mid = start + (end - start)/2;

      if (nums[mid] < nums[end]) {
        end = mid;              // end = mid - 1; <- this will give wrong answer.
      } else {
        start = mid + 1;
      }
    }
    return nums[start];
  }

  /**
   * Eg- [3,1,2]
   * mid = (0+2)/2 = 1, nums[mid] = 1,
   * which is the smallest so if you do mid = end-1
   * then you're excluding it from the search space.
   */

}
