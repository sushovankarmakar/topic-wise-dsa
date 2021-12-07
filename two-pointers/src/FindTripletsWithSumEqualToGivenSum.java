package src;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/triplet-sum-in-array-1587115621/1
 *
 * https://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
 */
public class FindTripletsWithSumEqualToGivenSum {

  public static void main(String[] args) {
    System.out.println(find3Numbers(new int[]{1, 4, 45, 6, 10, 8}, 13));  // true
    System.out.println(find3Numbers(new int[]{1, 2, 4, 3, 6}, 15));  // false
  }

  public static boolean find3Numbers(int[] arr, int sum) {
    int n = arr.length;
    Arrays.sort(arr);

    for (int z = n - 1; z >= 1; z--) {

      int x = 0;
      int y = z - 1;

      while (x < y) {
        int currentSum = arr[x] + arr[y] + arr[z];
        if (currentSum == sum) {
          return true;
        } else if (currentSum < sum) {
          x++;
        } else {
          y--;
        }
      }
    }
    return false;
  }

}
