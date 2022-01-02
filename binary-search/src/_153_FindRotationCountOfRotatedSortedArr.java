package src;

/**
 * https://practice.geeksforgeeks.org/problems/rotation4723/1
 *
 * https://www.youtube.com/watch?v=4WmTRFZilj8&ab_channel=AdityaVerma
 * (approach is correct but solution is slightly wrong, check comment for right code)
 *
 *
 */
public class _153_FindRotationCountOfRotatedSortedArr {

  /**
   * https://www.youtube.com/watch?v=vF7gk4iaklA&ab_channel=Pepcoding
   * https://www.youtube.com/watch?v=CihX9yKR2xk&ab_channel=CodingDecoded
   */
  /**
   * APPROACH - 1
   * Intuition :
   * After diving, we'll always find the minimum element in the unsorted side,
   * so discard the sorted side and go the unsorted side.
   */
  public int findKRotation(int[] nums) {

    int n = nums.length;
    int start = 0;
    int end = n - 1;

    while (start < end) {

      if (nums[start] < nums[end]) return start;  // if the array is not rotated.

      int mid = start + (end - start)/2;

      if (nums[mid] < nums[end]) {
        end = mid;              // end = mid - 1; <- wrong
      } else {
        start = mid + 1;
      }
    }
    return start;
  }


  /**
   * we need to find the smallest value in the rotated sorted array.
   */
  /**
   * APPROACH - 2 :
   * slightly bigger code.
   */
  public int findKRotation_2(int arr[], int n) {

    int start = 0;
    int end = n - 1;

    while (start <= end) {

      int mid = start + (end - start) / 2;

      int midPrev = (mid + n - 1) % n;
      int midNext = (mid + 1) % n;

      /**
       * if mid-value is smaller than the previous value and smaller than the next value,
       * then this is the minimum value.
       */
      if (arr[midPrev] >= arr[mid] && arr[mid] <= arr[midNext]) {
        return mid;
      } else if (arr[0] <= arr[mid]) {
        start = mid + 1;  // we need to go the unsorted array side.
      } else if (arr[mid] <= arr[n - 1]) {
        end = mid - 1;  // going to the unsorted array side.
      }

      /**
       * at each step, we are checking mid-value's which side is sorted and which is unsorted,
       * and we're going to the unsorted side to find the smallest value.
       */
    }
    return 0;
  }

  /**
   * for who don't understand modulo parts
   * case 1 if mid = 0 or the first index
   * for instance arr = [1, 2]
   * prev = mid - 1 = -1 which thrown an error because arr[-1] is out of range
   * prev = (mid - 1 + n) % n = 1, it prevents an error from negative index
   *
   * case 2 if mid = n - 1 or the last index
   * such as arr = [2, 3, 1] , assume that mid = 2
   * next = mid + 1 = 3, which arr[3] is out of range
   * next = (mid + 1) % n = 0, loop index is initialized to the first index, think like a circular array
   */

}
