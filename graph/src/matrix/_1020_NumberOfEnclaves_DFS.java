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
        for (int j = 0; j < m; j++) {

            if (grid[0][j] == 1) {
                dfs(0, j, grid, isVisited);      // first row
            }

            if (grid[n - 1][j] == 1) {
                dfs(n - 1, j, grid, isVisited);  // last row
            }
        }

        // visiting first col and last col
        for (int i = 0; i < n; i++) {

            if (grid[i][0] == 1) {
                dfs(i, 0, grid, isVisited);      // first col
            }

            if (grid[i][m - 1] == 1) {
                dfs(i, m - 1, grid, isVisited);  // last col
            }
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 1 && !isVisited[i][j]) {
                    count++;
                }
            }
        }

        return count;
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
