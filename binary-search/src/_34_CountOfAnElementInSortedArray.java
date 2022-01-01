package src;

/**
 * https://practice.geeksforgeeks.org/problems/number-of-occurrence2259/1
 *
 * (Count of an Element in a Sorted Array)
 * this problem is based upon
 * (First and Last Position of an element in a sorted array)
 *
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * https://practice.geeksforgeeks.org/problems/first-and-last-occurrences-of-x3116/1
 *
 *
 * https://www.youtube.com/watch?v=Ru_HhBFV3Xo&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=zr_AoTxzn0Y&t=98s&ab_channel=AdityaVerma
 *
 */
public class _34_CountOfAnElementInSortedArray {


  int count(int[] arr, int target) {

    int firstPos = findFirstPosition(arr, target);

    if (firstPos == -1) {
      return 0;
    }

    int lastPos = findLastPosition(arr, target);

    return (lastPos - firstPos + 1);
  }

  private int findFirstPosition(int[] arr, int target) {
    int n = arr.length;
    int start = 0;
    int end = n - 1;
    int firstPos = -1;

    while (start <= end) {
      int mid = start + (end - start)/2;

      if (arr[mid] == target) {
        firstPos = mid;
        end = mid - 1;
      } else if (target < arr[mid]) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return firstPos;
  }

  private int findLastPosition(int[] arr, int target) {
    int n = arr.length;
    int start = 0;
    int end = n - 1;
    int lastPos = -1;

    while (start <= end) {
      int mid = start + (end - start)/2;

      if (arr[mid] == target) {
        lastPos = mid;
        start = mid + 1;
      } else if (target < arr[mid]) {
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    return lastPos;
  }

}
