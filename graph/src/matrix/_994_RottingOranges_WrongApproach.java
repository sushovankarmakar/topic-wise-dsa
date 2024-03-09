package src.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class _994_RottingOranges_WrongApproach {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 2},
                {0, 1, 2},
                {2, 1, 1}
        };
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] isVisited = new boolean[n][m];

        int maxTime = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                /**
                 * 0 representing an empty cell,
                 * 1 representing a fresh orange, or
                 * 2 representing a rotten orange.
                 *
                 * example 1:
                 * ------------
                 * 0 1 2
                 * 0 1 2
                 * 2 1 1
                 *
                 * WHY THIS APPROACH IS WRONG :
                 *
                 * a grid can have multiple rotten oranges at start,
                 * like in the above example, there are 3 rotten oranges at start.
                 *
                 * we need to prioritize each rotten orange FIRST,
                 * so we need to add them into the queue first before calling the DFS method.
                 */
                if (grid[i][j] == 2 && !isVisited[i][j]) {

                    int currTime = bfs(i, j, isVisited, grid);

                    maxTime = Math.max(maxTime, currTime);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) return -1;
            }
        }

        return maxTime;
    }

    private static int bfs(int currRow, int currCol, boolean[][] isVisited, int[][] grid) {

        int currTime = 0;

        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(currRow, currCol, 0));
        isVisited[currRow][currCol] = true;

        while (!queue.isEmpty()) {

            int row = queue.peek().row;
            int col = queue.peek().col;
            int time = queue.peek().time;

            currTime = time;

            queue.remove();

            for (int i = 0; i < directions.length; i++) {
                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];
                int newTime = time + 1;

                if (isFresh(newRow, newCol, isVisited, grid)) {

                    grid[newRow][newCol] = 2;
                    isVisited[newRow][newCol] = true;
                    queue.add(new Tuple(newRow, newCol, newTime));
                }
            }
        }

        return currTime;
    }

    private static boolean isFresh(int i, int j, boolean[][] isVisited, int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        return i >= 0 && j >= 0 && i < n && j < m && !isVisited[i][j] && grid[i][j] == 1;
    }

    private static final int[][] directions = {
            {-1, 0}, {0, -1}, {0, 1}, {1, 0}
    };

    private static class Tuple {
        int row;
        int col;
        int time;

        Tuple(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
