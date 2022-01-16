package src;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/largest-perimeter-triangle/
 * https://practice.geeksforgeeks.org/problems/maximum-perimeter-of-triangle-from-array4322/1/
 *
 * Similar problem :
 * https://leetcode.com/problems/valid-triangle-number/
 * https://practice.geeksforgeeks.org/problems/form-a-triangle5935/1/
 * https://practice.geeksforgeeks.org/problems/count-possible-triangles-1587115620/1
 *
 * https://www.geeksforgeeks.org/maximum-perimeter-triangle-from-array/
 */
public class _976_LargestPerimeterTriangle {

  public static void main(String[] args) {
    // Maximum Perimeter of Triangle from array
    System.out.println(maxPerimeter(new int[]{6, 4, 9, 7, 8})); // 24
    System.out.println(maxPerimeter(new int[]{10, 21, 22, 100, 101, 200, 300})); // 601
    System.out.println(maxPerimeter(new int[]{5, 4, 3, 1, 2})); // 12
    System.out.println(maxPerimeter(new int[]{2, 2, 3, 4})); // 9
    System.out.println(maxPerimeter(new int[]{4, 2, 3, 4})); // 11
  }

  /**
   * Two pointer approach.
   *
   * time  complexity : O(N^2)
   * space complexity : O(1)
   */
  public static int maxPerimeter(int[] arr) {

    int n = arr.length;
    int maxPerimeter = -1;
    if (n <= 2) return maxPerimeter;

    Arrays.sort(arr);

    for (int k = n - 1; k >= 2; k--) {

      int i = 0;
      int j = k - 1;

      while (i < j) {

        if (arr[i] + arr[j] > arr[k]) {
          int currMax = arr[j - 1] + arr[j] + arr[k];
          maxPerimeter = Math.max(maxPerimeter, currMax);

          break;  // here we need to break, cause once we found bigger values, no need to go for smaller values of j
        } else {
          i++;
        }
      }
    }
    return maxPerimeter;
  }

}
