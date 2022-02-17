package src;

public class TartanHQInterview {

  public static void main(String[] args) {

    int[] input = {-10, 15, 25, 36, 47, 59, 64, 73, 86, 99, 1001};

    /*int[][] mat = {
        {10, 20, 30, 40},
        {15, 25, 35, 45},
        {27, 29, 37, 48},
        {32, 33, 39, 50}};*/

    // 1. traverse the BST and connect left child with right child.
    // 2. use a single variable.

  }

  private boolean binarySearch(int[] input, int target) {

    int n = input.length;
    int left = 0;
    int right = n - 1;

    while (left < right) {

      int mid = left + (right - left) / 2;

      if (input[mid] == target) {
        return true;
      } else if (target < input[mid]) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return false;
  }

}
