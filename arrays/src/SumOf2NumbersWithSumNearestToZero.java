package src;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/two-numbers-with-sum-closest-to-zero1737/1
 * <p>
 * https://www.youtube.com/watch?v=B12N9fq03sM&ab_channel=StudywithSaumya (a good explanation)
 * https://www.geeksforgeeks.org/two-elements-whose-sum-is-closest-to-zero/
 */
public class SumOf2NumbersWithSumNearestToZero {

  public static void main(String[] args) {
    System.out.println(closestToZero(new int[]{-8, -66, -60}));               // -68
    System.out.println(closestToZero(new int[]{-21, -67, -37, -18, 4, -65})); // -14
    System.out.println(closestToZero(new int[]{-2, -3, -8, -9, -10}));        // -5
  }

  private static int closestToZero(int[] arr) {
    int n = arr.length;
    Arrays.sort(arr);

    // corner cases.
    if (n == 1) {
      return arr[0];
    }
    if (arr[0] > 0) {
      return arr[0] + arr[1];         // if all the values are +ve, return first sum of first two nums
    }
    if (arr[n - 1] < 0) {
      return arr[n - 2] + arr[n - 1]; // if all the values are -ve, return first sum of last two nums
    }

    int sumClosestToZero = Integer.MAX_VALUE;
    int left = 0;
    int right = n - 1;

    while (left < right) {

      int currSum = arr[left] + arr[right];

      if (currSum == 0) {
        return 0;  // if the currSum is already zero, just return it.
      }
      if (Math.abs(currSum) < Math.abs(sumClosestToZero)) { // if we find sum closer to zero, store it.
        sumClosestToZero = currSum;
      }
      if (Math.abs(currSum) == Math.abs(sumClosestToZero)) {  // if both are equal, take the maximum
        sumClosestToZero = Math.max(currSum, sumClosestToZero);
      }

      if (currSum < 0) {  // if currSum is -ve, we try to bring it closer to zero by incrementing left pointer.
        left++;
      } else {            // if currSum is +ve, we try to bring it closer to zero by decrementing right pointer.
        right--;
      }
    }
    return sumClosestToZero;
  }

}
