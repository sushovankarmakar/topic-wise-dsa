package src;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 * https://practice.geeksforgeeks.org/problems/search-in-a-matrix17201720/1
 * (all three are same questions. For all questions, same solutions works)
 *
 * https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/ (A good explanation)
 *
 * https://www.youtube.com/watch?v=VS0BcOiKaGI&ab_channel=AdityaVerma (Good video explanation) - starts from top-right
 * https://www.youtube.com/watch?v=FOa55B9Ikfg&ab_channel=BackToBackSWE (Good video explanation) - starts from bottom-left
 *
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Searching_SearchInAMatrix.java (Solved once)
 *
 * This question was asked.
 */
public class _74_240_SearchIn2DMatrix_RowColSorted_1_2 {

  public boolean searchMatrix(int[][] matrix, int target) {

    int n = matrix.length;
    int m = matrix[0].length;

    // Start searching from the top-right corner of the matrix
    int i = 0;
    int j = m - 1;

    while (i < n && j >= 0) {

      if (matrix[i][j] == target) {
        return true;
      } else if (target < matrix[i][j]) {
        j--;
      } else {
        i++;
      }
    }
    return false;
  }

  /**
   * Time Complexity: O(n)
   * Only one traversal is needed, i.e, i from 0 to n and j from n-1 to 0 with at most 2*n steps.
   * The above approach will also work for m x n matrix (not only for n x n). Complexity would be O(m + n).
   *
   * Space Complexity: O(1)
   */
}
