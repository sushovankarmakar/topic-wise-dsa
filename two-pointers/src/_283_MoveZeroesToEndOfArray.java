package src;

/**
 * https://leetcode.com/problems/move-zeroes/
 * https://practice.geeksforgeeks.org/problems/move-all-zeroes-to-end-of-array0751/1/
 *
 * https://www.youtube.com/watch?v=lGFX1gY_zUg&t=544s&ab_channel=Stream2Learn
 * (Was asked here, was asked to me also in Tearadata interview)
 */
public class _283_MoveZeroesToEndOfArray {

  public static void main(String[] args) {

    // move all the zeros at the end.
    int[] nums1 = new int[]{1, 2, 0, 0, 0, 3, 6};
    moveAllZeroesAtTheEnd(nums1);
    for (int num : nums1) {
      System.out.print(num + " ");
    }
    System.out.println();

    // move all the zeros at the start.
    int[] nums2 = new int[]{1, 2, 0, 0, 0, 3, 6};
    moveAllZeroesAtTheStart(nums2);
    for (int num : nums2) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  /**
   * https://www.youtube.com/watch?v=PNJoyRaIW7U&ab_channel=TECHDOSE (Very easy two pointer algo.)
   */
  public static void moveAllZeroesAtTheEnd(int[] nums) {
    int n = nums.length;
    if (n == 0 || n == 1) return;

    int left = 0;
    int right = 0;

    while (right < n) {

      /**
       * LOGIC :
       * 1. nums[right] is zero, then only move the right point
       * 2. nums[right] is non-zero, then
       *    i.  swap left and right
       *    ii. and then move both the pointers
       */
      if (nums[right] != 0) {
        swap(nums, left, right);
        left++;
      }
      right++;
    }
  }

  /**
   * Same logic as moving all zeroes at the end,
   * only difference is, here we need to traverse from end.
   *
   * https://www.educative.io/m/move-zeros-left
   */
  public static void moveAllZeroesAtTheStart(int[] nums) {
    int n = nums.length;
    if (n == 0 || n == 1) return;

    int left = n - 1;
    int right = n - 1;

    while (left >= 0) {

      if (nums[left] != 0) {
        swap(nums, left, right);
        right--;
      }
      left--;
    }
  }

  private static void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }

}
