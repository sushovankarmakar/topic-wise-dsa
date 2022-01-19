package src;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-triangle-number/
 * https://practice.geeksforgeeks.org/problems/form-a-triangle5935/1/
 * https://practice.geeksforgeeks.org/problems/count-possible-triangles-1587115620/1
 *
 * https://www.youtube.com/watch?v=PqEiJDdt3S4&ab_channel=Pepcoding (Best Explanation)
 * https://www.youtube.com/watch?v=pmsex9gj1PI&ab_channel=CodingDecoded
 *
 * https://www.geeksforgeeks.org/find-number-of-triangles-possible/
 *
 * https://practice.geeksforgeeks.org/problems/maximum-perimeter-of-triangle-from-array4322/1/ (ALMOST SIMILAR)
 * https://www.geeksforgeeks.org/maximum-perimeter-triangle-from-array/
 */
public class _611_ValidTriangleNumber {

  public static void main(String[] args) {
    System.out.println(triangleNumber(new int[]{6, 4, 9, 7, 8})); // 10
    System.out.println(triangleNumber(new int[]{10, 21, 22, 100, 101, 200, 300})); // 6
    System.out.println(triangleNumber(new int[]{5, 4, 3, 1, 2})); // 3
    System.out.println(triangleNumber(new int[]{2, 2, 3, 4})); // 3
    System.out.println(triangleNumber(new int[]{4, 2, 3, 4})); // 4

    // Maximum Perimeter of Triangle from array (Almost similar concept used here too)
    System.out.println(maxPerimeter(new int[]{6, 4, 9, 7, 8})); // 24
    System.out.println(maxPerimeter(new int[]{10, 21, 22, 100, 101, 200, 300})); // 601
    System.out.println(maxPerimeter(new int[]{5, 4, 3, 1, 2})); // 12
    System.out.println(maxPerimeter(new int[]{2, 2, 3, 4})); // 9
    System.out.println(maxPerimeter(new int[]{4, 2, 3, 4})); // 11
  }

  /**
   * Two pointer approach. Almost similar to 3 sum problem.
   *
   * time  complexity : O(N^2)
   * space complexity : O(1)
   */
  public static int triangleNumber(int[] nums) {

    int n = nums.length;
    if (n <= 2) return 0;

    int count = 0;
    Arrays.sort(nums);

    // [i...........j k]

    for (int k = n - 1; k >= 2; k--) {  // k is the 3rd pointer, starting from the end of the array

      int i = 0;  // i is 1st pointer, starting from start of the array
      int j = k - 1;  // j is the 2nd pointer, starting from one behind the 3rd pointer

      while (i < j) {
        if (nums[i] + nums[j] > nums[k]) {
          count += (j - i);
          j--;    // i made a mistake here, directly put break. if I put break, (6, 4, 9, 7, 8) will give wrong result.
                  // j-- means, we've found result for i, j, k now check for smaller j
        } else {
          i++;  // we've didn't found result for i,j,k, now check for bigger i
        }
      }
    }
    return count;
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
