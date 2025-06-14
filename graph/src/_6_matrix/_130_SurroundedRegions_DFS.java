package src._6_matrix;

/**
 * https://leetcode.com/problems/surrounded-regions/description/
 * https://practice.geeksforgeeks.org/problems/replace-os-with-xs0052/1
 * https://takeuforward.org/graph/distance-of-nearest-cell-having-1/
 *
 * https://www.youtube.com/watch?v=BtdgAys4yMk (Striver)
 */
public class _130_SurroundedRegions_DFS {

    public static void main(String[] args) {
    }

    /**
     * 1. go through all the four boundaries and check if there is any zero in the boundary
     * 2. if there is a zero in the boundary then we need to add mark all the zero associate with it. (do it using DFS)
     *
     * 3. now after this, we have only those zeros left which are surrounded by X in all four directions. so replace those zeros with X.
     */
    private static char[][] fill(int n, int m, char[][] mat) {

        boolean[][] isVisited = new boolean[n][m];

        visitingFirstAndLastRow(mat, isVisited);    // visiting first row and last row
        visitingFirstAndLastCol(mat, isVisited);    // visiting first col and last col

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                // now I've such cells which is purely surrounded by X in all four directions
                // mark these cells as 'X'
                if (mat[i][j] == 'O' && !isVisited[i][j]) {
                    mat[i][j] = 'X';
                }
                //System.out.print(isVisited[i][j] + " ");
            }
            //System.out.println();
        }

        return mat;
    }

    private static void visitingFirstAndLastRow(char[][] mat, boolean[][] isVisited) {

        int n = mat.length;
        int m = mat[0].length;

        int firstRow = 0;
        int lastRow = n - 1;

        for (int j = 0; j < m; j++) {

            if (mat[firstRow][j] == 'O' && !isVisited[firstRow][j]) {
                dfs(firstRow, j, mat, isVisited);
            }

            if (mat[lastRow][j] == 'O' && !isVisited[lastRow][j]) {
                dfs(lastRow, j, mat, isVisited);
            }
        }
    }

    private static void visitingFirstAndLastCol(char[][] mat, boolean[][] isVisited) {

        int n = mat.length;
        int m = mat[0].length;

        int firstCol = 0;
        int lastCol = m - 1;

        for (int i = 0; i < n; i++) {

            if (mat[i][firstCol] == 'O' && !isVisited[i][firstCol]) {
                dfs(i, firstCol, mat, isVisited);
            }

            if (mat[i][lastCol] == 'O' && !isVisited[i][lastCol]) {
                dfs(i, lastCol, mat, isVisited);
            }
        }
    }

    private static void dfs(int row, int col, char[][] mat, boolean[][] isVisited) {

        isVisited[row][col] = true;

        for (int i = 0; i < fourDirections.length; i++) {

            int newRow = row + fourDirections[i][0];
            int newCol = col + fourDirections[i][1];

            if (isValidCell(newRow, newCol, mat, isVisited)) {

                isVisited[newRow][newCol] = true;
                dfs(newRow, newCol, mat, isVisited);
            }
        }
    }

    private static final int[][] fourDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static boolean isValidCell(int row, int col, char[][] mat, boolean[][] isVisited) {

        // 1. a valid cell should be in the boundaries
        // 2. should not be visited earlier
        // 3. should contain zero as character
        return row >= 0 && col >= 0 &&
                row < mat.length && col < mat[0].length &&
                !isVisited[row][col] &&
                mat[row][col] == 'O';
    }
}
