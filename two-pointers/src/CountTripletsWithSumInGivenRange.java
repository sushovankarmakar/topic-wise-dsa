package src;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/count-number-of-triplets-in-an-array-having-sum-in-the-range-a-b/
 * https://practice.geeksforgeeks.org/problems/triplets-with-sum-with-given-range/1
 *
 *
 * this question is based on the below question:
 * https://practice.geeksforgeeks.org/problems/count-triplets-with-sum-smaller-than-x5549/1
 */
public class CountTripletsWithSumInGivenRange {

  public static void main(String[] args) {
    System.out.println(countTriplets(new int[]{9, 4, 6, 1, 2, 3, 8}, 3, 14));  // 19
    System.out.println(countTriplets(new int[]{8 , 3, 5, 2}, 7, 11));  // 1
    System.out.println(countTriplets(new int[]{5, 1, 4, 3, 2}, 2, 7));  // 2
    System.out.println(countTriplets(new int[]{2, 7, 5, 3, 8, 4, 1, 9}, 8, 16));  // 36
  }

  /**
   * An efficient solution is to
   * first find the count of triplets having a sum less than or equal to upper limit b in the range [a, b].
   * This count of triplets will also include triplets having a sum less than the lower limit a.
   * Subtract the count of triplets having a sum less than a.
   * The final result is the count of triplets having a sum in the range [a, b].
   *
   * The algorithm is as follows:
   *
   * 1. Find count of triplets having a sum less than or equal to r. Let this count be x.
   * 2. Find count of triplets having a sum less than r. Let this count be y.
   * 3. Final result is (x - y).
   *
   *
   * Time complexity: O(n2)
   * Space complexity: O(1)
   */

  private static int countTriplets(int[] arr, int l, int r) {
    Arrays.sort(arr);
    int n = arr.length;

    int numbersLessOrEqualToRightLimit = countTripletsLessOrEqualToTargetSum(arr, n, r);
    int numbersLessThanLeftLimit = countTripletsLessOrEqualToTargetSum(arr, n, l - 1);

    return numbersLessOrEqualToRightLimit - numbersLessThanLeftLimit;
  }

  private static int countTripletsLessOrEqualToTargetSum(int[] arr, int n, int targetSum) {
    int count = 0;

    for (int z = n - 1; z >= 1; z--) {

      int x = 0;
      int y = z - 1;
      while (x < y) {
        int currentSum = arr[x] + arr[y] + arr[z];

        if (currentSum <= targetSum) {
          count += (y - x);
          x++;
        } else {
          y--;
        }
      }
    }
    return count;
  }

}
