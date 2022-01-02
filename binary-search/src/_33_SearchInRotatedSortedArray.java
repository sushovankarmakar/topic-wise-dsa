package src;

/**
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * https://workat.tech/problem-solving/practice/search-rotated-array
 * https://practice.geeksforgeeks.org/problems/search-in-a-rotated-array4618/1
 */
public class _33_SearchInRotatedSortedArray {

  /**
   * APPROACH - 1 : (2 traversal) - it is easy to remember because it is related to find min in roated sorted array
   *
   * https://www.youtube.com/watch?v=Id-DdcWb5AU&ab_channel=AdityaVerma
   *
   * here we find the minimum element first and break down it into two parts which are both sorted arrays
   *
   * The array can be treated as two different sorted arrays.
   * All we need to do is to find the point from where the array needs to be broken down into two different arrays.
   *
   * To find that point we can perform a binary search to find the smallest element.
   * After finding that point we can apply binary search in both parts of the array to find the index of the key.
   */
  public int search_1(int[] arr, int target) {

    /**
     1. find the min index and break arr into two sorted halves.
     2. find the target value in the first half.
     3. if target not found in first half, find in the second half.
     */
    int n = arr.length;
    // step 1.
    int minIndex = findMinValuedIndex(arr);

    // step 2.
    int targetIndex = binarySearch(arr, minIndex, n - 1, target);

    // step 3.
    return targetIndex != -1 ? targetIndex :
        binarySearch(arr, 0, minIndex - 1, target);
  }

  /**
   * https://www.youtube.com/watch?v=vF7gk4iaklA&ab_channel=Pepcoding
   * https://www.youtube.com/watch?v=CihX9yKR2xk&ab_channel=CodingDecoded
   *
   * Find Minimum in Rotated Sorted Array
   */
  private int findMinValuedIndex(int[] arr) {

    int n = arr.length;
    int start = 0;
    int end = n - 1;

    while (start < end) {

      if (arr[start] < arr[end]) return start;

      int mid = start + (end - start)/2;

      if (arr[mid] < arr[end]) {
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return start;
  }

  private int binarySearch(int[] arr, int start, int end, int target) {

    while (start <= end) {

      int mid = start + (end - start)/2;

      if (arr[mid] == target) {
        return mid;
      } else if (target < arr[mid]) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return -1;
  }


  /**
   * APPROACH - 2 (1 traversal)
   *
   * https://www.youtube.com/watch?v=oTfPJKGEHcc&ab_channel=TECHDOSE (Explain both approaches, explain the observation)
   * https://www.youtube.com/watch?v=1uu3g_uu8O0&ab_channel=Pepcoding (Explains the observation)
   * https://www.youtube.com/watch?v=r3pMQ8-Ad5s&ab_channel=takeUforward
   * https://www.youtube.com/watch?v=Le8bc8aHgBA&ab_channel=AnujBhaiya
   */
  public int search_2(int[] arr, int target) {

    /**
     * OBSERVATION : (mentioned here - https://www.youtube.com/watch?v=oTfPJKGEHcc&ab_channel=TECHDOSE)
     * standing at mid-index,
     * either left part or right part will always be sorted (strictly increasing).
     * so we always have at least one sorted part.
     *
     */

    int n = arr.length;
    int start = 0;
    int end = n - 1;

    while (start <= end) {

      int mid = start + (end - start)/2;

      if (arr[mid] == target) {
        return mid;
      }
      // OBSERVATION : either left or right part will be sorted.
      // check left part is sorted (it means left part is strictly increasing)
      else if (arr[start] <= arr[mid]) {

        if (arr[start] <= target && target <= arr[mid]) {
          end = mid - 1;
        } else {
          start = mid + 1;
        }
      }
      // right part is sorted (it means right part is strictly increasing)
      else {
        if (arr[mid] <= target && target <= arr[end]) {
          start = mid + 1;
        } else {
          end = mid - 1;
        }
      }

    }

    return -1;
  }


}
