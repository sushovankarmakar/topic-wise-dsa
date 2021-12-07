package src;

import java.util.Arrays;

// https://practice.geeksforgeeks.org/problems/pythagorean-triplet3018/1

public class FindPythagoreanTripletInArray {

  public static void main(String[] args) {
    System.out.println(checkTriplet(new int[]{3, 2, 4, 6, 5})); // true
    System.out.println(checkTriplet(new int[]{3, 8, 5}));       // false
  }

  // https://www.geeksforgeeks.org/find-pythagorean-triplet-in-an-unsorted-array/
  // time complexity : O(N^2)
  // space complexity : O(1)

  /**
   * Steps :
   * 1) Do the square of every element in the input array. This step takes O(n) time.
   * 2) Sort the squared array in increasing order. This step takes O(nLogn) time.
   * 3) To find a triplet (a, b, c) such that a2 = b2 + c2, do following.
   *    a) Fix ‘z’ as the last element of the sorted array.
   *    b) Now search for pair (x, y) in subarray between the first element and ‘y’.
   *       A pair (x, y) with a given sum can be found in O(n) time using the meet in middle algorithm.
   *    c) If no pair is found for current ‘z’, then move ‘z’ one position back and repeat last two steps (3.b and 3.c).
   */

  private static boolean checkTriplet(int[] arr) {
    int n = arr.length;

    for (int i = 0; i < n; i++) {
      arr[i] = arr[i] * arr[i];
    }

    Arrays.sort(arr);

    for (int z = n - 1; z >= 2; z--) {

      int x = 0;
      int y = z - 1;

      // below is the meet in middle algorithm.
      while (x < y) {
        if (arr[x] + arr[y] == arr[z]) {
          return true;
        } else if (arr[x] + arr[y] < arr[z]) {
          x++;
        } else {
          y--;
        }
      }
    }
    return false;
  }

}
