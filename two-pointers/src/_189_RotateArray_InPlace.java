package src;

/**
 * https://leetcode.com/problems/rotate-array/
 *
 * https://practice.geeksforgeeks.org/problems/rotate-array-by-n-elements-1587115621/1/
 * https://practice.geeksforgeeks.org/problems/reversal-algorithm5340/1
 * https://practice.geeksforgeeks.org/problems/rotate-array-by-n-elements/0
 *
 * https://www.youtube.com/watch?v=BHr381Guz3Y&ab_channel=NeetCode
 */
public class _189_RotateArray_InPlace {

  public static void main(String[] args) {
    int[] arr1 = {1, 2, 3, 4, 5};
    rotateRight(arr1, 2); // 4 5 1 2 3

    for (int num : arr1) {
      System.out.print(num + " ");
    }
    System.out.println();

    int[] arr2 = {1, 2, 3, 4, 5};
    rotateLeft(arr2, 2);  // 3 4 5 1 2

    for (int num : arr2) {
      System.out.print(num + " ");
    }
  }

  /**
   * 3 STEPS :
   *    1. rotate the whole array.
   *    2. rotate first k elements.
   *    3. rotate rest of the elements.
   *
   * Time Complexity: O(N)
   * Auxiliary Space: O(1)
   */
  public static void rotateRight(int[] nums, int k) {
    int n = nums.length;
    k = (k % n);        // k can be greater than the actual length of the array.
    reverse(nums, 0, n - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, n - 1);
  }

  public static void rotateLeft(int[] nums, int k) {
    int n = nums.length;
    k = (k % n);        // k can be greater than the actual length of the array.
    reverse(nums, 0, n - 1);
    reverse(nums, 0, n - k - 1);
    reverse(nums, n - k, n - 1);
  }

  private static void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;

      start++;
      end--;
    }
  }

}
