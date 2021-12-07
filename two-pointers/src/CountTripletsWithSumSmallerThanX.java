package src;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x5549/1
 *
 * https://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/
 *
 * Similar question:
 * ----------------
 * https://www.geeksforgeeks.org/count-number-of-triplets-in-an-array-having-sum-in-the-range-a-b/
 */

public class CountTripletsWithSumSmallerThanX {

  public static void main(String[] args) {
    System.out.println(
        countTriplets(new long[]{30, 8, 23, 6, 10, 9, 31, 7, 19, 20, 1, 33, 21, 27, 28, 3, 25, 26}, 86));  // 796
    System.out.println(countTriplets(new long[]{1, 3, 4, 5, 7}, 13)); // 6
    System.out.println(countTriplets(new long[]{-2, 0, 1, 3}, 2)); // 2
    System.out.println(countTriplets(new long[]{5, 1, 3, 4, 7}, 12)); // 4
    System.out.println(countTriplets(new long[]{2, 7, 5, 3, 8, 4, 1, 9}, 16)); // 33
  }

  private static int countTriplets(long arr[], int targetSum) {

    int n = arr.length;
    int count = 0;
    Arrays.sort(arr);

    for (int z = n - 1; z >= 1; z--) {

      int x = 0;
      int y = z - 1;

      while (x < y) {
        long currentSum = arr[x] + arr[y] + arr[z];

        if (currentSum < targetSum) {
          count += (y - x);
          x++;  // don't put break, will lose some of combination of greater x values
        } else {
          y--;
        }
      }
    }
    return count;
  }

  /**
   * 5 13
   * 1 3 4 5 7
   *
   * if we put break after count += (y - x), we loose the (3, 4, 5) triplet
   * so answer becomes 5.
   * but actual right answer is 6.
   */

}
