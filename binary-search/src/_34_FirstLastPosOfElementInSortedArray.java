package src;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * https://practice.geeksforgeeks.org/problems/first-and-last-occurrences-of-x3116/1
 *
 * Same question variant :
 * https://practice.geeksforgeeks.org/problems/number-of-occurrence2259/1
 *
 * https://www.youtube.com/watch?v=zr_AoTxzn0Y&t=98s&ab_channel=AdityaVerma
 */
public class _34_FirstLastPosOfElementInSortedArray {

  public static void main(String[] args) {
    int[] firstAndLastPos = searchRange(new int[]{5,7,7,8,8,10}, 8);
    System.out.println(firstAndLastPos[0] + " " + firstAndLastPos[1]);
  }

  public static int[] searchRange(int[] nums, int target) {
    int[] firstAndLastPos = new int[2];

    firstAndLastPos[0] = findFirstPosition(nums, target);
    firstAndLastPos[1] = findLastPosition(nums, target);

    return firstAndLastPos;
  }

  private static int findFirstPosition(int[] nums, int target) {

    int n = nums.length;
    int start = 0;
    int end = n - 1;
    int firstPos = -1;

    while (start <= end) {

      int mid = start + (end - start)/2;

      if (nums[mid] == target) {
        firstPos = mid; // saving possible first position
        end = mid - 1;  // and going more left to find the first position of target
      } else if (target < nums[mid]) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return firstPos;
  }

  private static int findLastPosition(int[] nums, int target) {

    int n = nums.length;
    int start = 0;
    int end = n - 1;
    int lastPos = - 1;

    while (start <= end) {

      int mid = start + (end - start)/2;

      if (nums[mid] == target) {
        lastPos = mid;    // saving possible last position
        start = mid + 1;  // and going more right to find the last position of target
      } else if (target < nums[mid]) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return lastPos;
  }

  /**
   * 2nd approach :
   * find any occurrences of the target using binary search,
   * and then from this target,
   * go left in linear fashion to find out the first occurrences and
   * go right in linear fashion to find out the last occurrences.
   */
  public int[] searchRange_2(int[] nums, int target) {

    int n = nums.length;
    int start = 0;
    int end = n - 1;
    int mid = start;
    boolean targetFound = false;

    // 1. find the target using normal binary search. ---------------------
    while (start <= end) {

      mid = start + (end - start)/2;

      if (nums[mid] == target) {
        targetFound = true;
        break;
      } else if (target < nums[mid]) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }

    int[] firstAndLastPos = new int[]{-1, -1};
    if (!targetFound) return firstAndLastPos;

    // 2. find the target's first position -------------------------------
    int firstPos = mid;
    while (firstPos > 0 && nums[firstPos] == nums[firstPos - 1]) {
      firstPos--;
    }
    firstAndLastPos[0] = firstPos;

    // 3. find the target's last position ------------------------------
    int lastPos = mid;
    while (lastPos < (n - 1) && nums[lastPos] == nums[lastPos + 1]) {
      lastPos++;
    }
    firstAndLastPos[1] = lastPos;

    return firstAndLastPos;
  }

}
