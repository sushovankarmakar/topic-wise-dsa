package src.matrix;

/**
 * https://www.geeksforgeeks.org/problems/number-of-enclaves/1
 * https://leetcode.com/problems/number-of-enclaves/description/
 * <p>
 * <p>
 * https://www.youtube.com/watch?v=rxKcepXQgU4&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/graph/number-of-enclaves/
 */
public class _1020_NumberOfEnclaves_DFS {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(numberOfEnclaves(grid)); // 3

        int[][] grid1 = {
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(numberOfEnclaves(grid1)); // 0
    }

    private static int numberOfEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] isVisited = new boolean[n][m];

        // visiting first row and last row
        visitingFirstAndLastRow(grid, n, m, isVisited);

        // visiting first col and last col
        visitingFirstAndLastCol(grid, m, n, isVisited);

        int enclaves = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 1 && !isVisited[i][j]) {
                    enclaves++;
                }
            }
        }

        return enclaves;
    }

    private static void visitingFirstAndLastRow(int[][] grid, int n, int m, boolean[][] isVisited) {
        int firstRow = 0;
        int lastRow = n - 1;

        for (int j = 0; j < m; j++) {

            if (grid[firstRow][j] == 1 && !isVisited[firstRow][j]) {
                dfs(firstRow, j, grid, isVisited);
            }

            if (grid[lastRow][j] == 1 && !isVisited[lastRow][j]) {
                dfs(lastRow, j, grid, isVisited);
            }
        }
    }

    private static void visitingFirstAndLastCol(int[][] grid, int m, int n, boolean[][] isVisited) {
        int firstCol = 0;
        int lastCol = m - 1;

        for (int i = 0; i < n; i++) {

            if (grid[i][firstCol] == 1 && !isVisited[i][firstCol]) {
                dfs(i, firstCol, grid, isVisited);
            }

            if (grid[i][lastCol] == 1 && !isVisited[i][lastCol]) {
                dfs(i, lastCol, grid, isVisited);
            }
        }
    }

    private static void dfs(int row, int col, int[][] grid, boolean[][] isVisited) {

        isVisited[row][col] = true;

        for (int i = 0; i < fourDirections.length; i++) {

            int newRow = row + fourDirections[i][0];
            int newCol = col + fourDirections[i][1];

            if (isValidCell(newRow, newCol, grid, isVisited)) {

                isVisited[newRow][newCol] = true;
                dfs(newRow, newCol, grid, isVisited);
            }
        }
    }

    private static final int[][] fourDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static boolean isValidCell(int row, int col, int[][] grid, boolean[][] isVisited) {

        return row >= 0 && col >= 0 &&
                row < grid.length && col < grid[0].length &&
                !isVisited[row][col] &&
                grid[row][col] == 1;
    }
}
