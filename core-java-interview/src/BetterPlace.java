package src;

public class BetterPlace {

  public static void main(String[] args) {
    /*int[] nums = {-12, 11, -13, 12, 30, -5, -7};
    rearrangeNegativeFirst(nums);

    for (int num : nums) {
      System.out.print(num + " ");
    }
    System.out.println();

    int[] nums1 = {1, 12, 13, -5};
    rearrangeNegativeFirst(nums1);

    for (int num : nums1) {
      System.out.print(num + " ");
    }
    System.out.println();*/

    /*int[][] mat = {
        {10, 20, 30, 40},
        {15, 25, 35, 45},
        {27, 29, 37, 48},
        {32, 33, 39, 50}
    };*/
    int[][] mat = {
        {1,   3,  5,  7},
        {10, 11, 16, 20},
        {23, 30, 34, 60}
    };

    System.out.println(findInMatrix(mat, 3) ? "FOUND" : "NOT FOUND");
    //System.out.println(findInMatrix(mat, 39) ? "FOUND" : "NOT FOUND");
  }

  private static void rearrangeNegativeFirst(int[] nums) {

    int n = nums.length;
    if (n < 1) {
      return;
    }

    int i = 0;
    int j = 1;

    while (j < n) {
      if (nums[i] < 0 && nums[j] > 0) {
        i++;
        j++;
      } else if (nums[i] > 0 && nums[j] < 0) {
        swap(nums, i, j);
        i++;
        j++;
      } else if (nums[i] > 0 && nums[j] > 0) {
        j++;
      }
    }
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  private static boolean findInMatrix(int[][] mat, int target) {

    int n = mat.length;

    for (int i = 0; i < n; i++) {

      if (isTargetInThisColumn(mat, i, n, target)) {
        if (doesFindTarget(mat, i, n, target)) return true;
      }
    }

    return false;
  }

  private static boolean isTargetInThisColumn(int[][] mat, int i, int n, int target) {
    int firstVal = mat[0][i];
    int lastVal = mat[n - 1][i];
    return (firstVal <= target && target <= lastVal) ? true : false;
  }

  private static boolean doesFindTarget(int[][] mat, int i, int n, int target) {

    //int left = 0, right = n - 1;

    for (int j = 0; j < n; j++) {
      if (mat[j][i] == target) {
        System.out.println(i + " " + j);
        return true;
      }
    }
    return false;
  }

}
