package src;

import java.util.HashSet;
import java.util.Set;

/**
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Arrays_BooleanSetMatrixZerosOnes.java
 * (I followed this my own method. Easy to understand by reading comments)
 *
 * https://leetcode.com/problems/set-matrix-zeroes/
 * https://workat.tech/problem-solving/practice/row-column-zero
 *
 * https://www.youtube.com/watch?v=T41rL0L3Pnw&ab_channel=NeetCode (A good video to understand the concept)
 * https://www.youtube.com/watch?v=M65xBewcqcI&t=13s&ab_channel=takeUforward
 *
 * https://www.geeksforgeeks.org/a-boolean-matrix-question/ (Same question)
 */
public class _73_SetMatrixZeroes {

  /**
   * time  complexity : O(m*n)
   * space complexity : O(1)
   *
   * this 1st approach is the improvement of 2nd approach.
   */
  public void setZeroes_BEST_METHOD(int[][] mat) {

    int row = mat.length;
    int col = mat[0].length;

    boolean make1stRowZero = false;
    boolean make1stColZero = false;

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        /* next two if conditions take special care for first row and first column */

        /* Scan the first row and set a variable make1stRowZero to indicate whether
        we need to set all 1s in first row or not. */
        if (i == 0 && mat[i][j] == 0) { /* if any values in first row is 0, set rowFlag true */
          make1stRowZero = true;
        }

        /* Scan the first column and set a variable make1stColZero to indicate whether
        we need to set all 1s in first column or not. */
        if (j == 0 && mat[i][j] == 0) { /* if any values in first col is 0, set colFlag true */
          make1stColZero = true;
        }

        /* Use first row and first column as the auxiliary arrays row[] and col[] respectively,
         * consider the matrix as sub matrix starting from second row and second column
         * */
        if (mat[i][j] == 0) {
          mat[0][j] = 0;
          mat[i][0] = 0;
        }
      }
    }

    /* Modify the given input matrix using the first row and first column of this matrix itself */
    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (mat[0][j] == 0 || mat[i][0] == 0) {
          mat[i][j] = 0;
        }
      }
    }

    /* modify first row if there was any 0 */
    if (make1stRowZero) {
      for (int j = 0; j < col; j++) {
        mat[0][j] = 0;
      }
    }

    /* modify first col if there was any 0 */
    if (make1stColZero) {
      for (int i = 0; i < row; i++) {
        mat[i][0] = 0;
      }
    }

  }

  /**
   * time  complexity : O(m*n)
   * space complexity : O(m + n)
   */
  public void setZeroes(int[][] mat) {

    Set<Integer> rows = new HashSet<>();
    Set<Integer> cols = new HashSet<>();

    int rowLen = mat.length;
    int colLen = mat[0].length;

    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        if (mat[i][j] == 0) {
          rows.add(i);
          cols.add(j);
        }
      }
    }

    for (int i = 0; i < rowLen; i++) {
      for (int j = 0; j < colLen; j++) {
        if (rows.contains(i) || cols.contains(j)) {
          mat[i][j] = 0;
        }
      }
    }

    /*Iterator<Integer> itr = rows.iterator();

    while (itr.hasNext()) {
      int i = itr.next();
      for (int j = 0; j < colLen; j++) {
        mat[i][j] = 0;
      }
    }

    itr = cols.iterator();

    while (itr.hasNext()) {
      int j = itr.next();
      for (int i = 0; i < rowLen; i++) {
        mat[i][j] = 0;
      }
    }*/

  }

}
