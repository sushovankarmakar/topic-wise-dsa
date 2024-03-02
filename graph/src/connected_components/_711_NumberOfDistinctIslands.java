package src.connected_components;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This question was asked to me in Google.
 *
 * https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1
 * https://leetcode.com/problems/number-of-distinct-islands-ii/description/ (Premium)
 * <p>
 * https://www.youtube.com/watch?v=muncqlKJrH0&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/number-of-islands/ (Blog)
 */
public class _711_NumberOfDistinctIslands {

    public static void main(String[] args) {

        /**
         * 0 1 1 1 0 0 0
         * 0 0 1 1 0 1 0
         *
         * ans : 2
         */
        char[][] grid =  {
                {'0', '1', '1', '1', '0', '0', '0'},
                {'0', '0', '1', '1', '0', '1', '0'}
        };

        System.out.println(numIslands(grid)); // 2
    }

    private static int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int numOfIslands = 0;
        boolean[][] isVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == '1' && !isVisited[i][j]) {

                    numOfIslands++;
                    bfs(i, j, grid, isVisited);
                }
            }
        }

        return numOfIslands;
    }

    private static void bfs(int row, int col, char[][] grid, boolean[][] isVisited) {

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(row, col));
        isVisited[row][col] = true;

        while (!queue.isEmpty()) {

            int currRow = queue.peek().row;
            int currCol = queue.peek().col;

            queue.remove();

            for (int i = 0; i < 8; i++) {

                int newRow = currRow + directions[i][0];
                int newCol = currCol + directions[i][1];

                if (isValidCell(newRow, newCol, grid, isVisited)) {

                    isVisited[newRow][newCol] = true;
                    queue.add(new Pair(newRow, newCol));
                }
            }
        }
    }

    private static boolean isValidCell(int row, int col, char[][] grid, boolean[][] isVisited) {

        return row >= 0 && col >= 0 &&
                row < grid.length && col < grid[0].length &&
                grid[row][col] == '1' && !isVisited[row][col];
    }

    private static class Pair {

        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static final int[][] directions = {
            {-1, -1}, {-1, 0},
            {-1, 1}, {0, -1},
            {0, 1}, {1, -1},
            {1, 0}, {1, 1}
    };
}
