package src.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=edXdVwkYHF8 (Striver)
 * <p>
 * https://leetcode.com/problems/01-matrix/
 * https://practice.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1-1587115620/1
 */
public class _542_DistanceOfNearestCellHaving0 {

    public static void main(String[] args) {

        int[][] grid = {
                {0, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1}};
        int[][] op = nearest(grid);

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(op[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * for those cell consist of 1, we need to find the distance of nearest 0
     * 	-> so in isValid method, check for only those cell which are not visited before and having 1
     *
     * for those cell consist of 0, distance of nearest cell is already 0 and we've put those cells first into the queue.
     */
    private static int[][] nearest(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] nearestDistance = new int[n][m];
        boolean[][] isVisited = new boolean[n][m];

        Queue<Tuple> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 0) {

                    isVisited[i][j] = true;
                    nearestDistance[i][j] = 0;
                    queue.add(new Tuple(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {

            int row = queue.peek().row;
            int col = queue.peek().col;
            int dist = queue.peek().dist;

            queue.remove();

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = row + fourDirections[i][0];
                int newCol = col + fourDirections[i][1];
                int newDist = dist + 1;

                if (isValidCell(newRow, newCol, grid, isVisited)) {

                    isVisited[newRow][newCol] = true;
                    nearestDistance[newRow][newCol] = newDist;
                    queue.add(new Tuple(newRow, newCol, newDist));
                }
            }
        }
        return nearestDistance;
    }

    /**
     *            (-1, 0)
     *              |
     * (0, -1) -- (0,0) -- (0, +1)
     *              |
     *           (+1, 0)
     *
     * {0, -1} - left column.
     * {0, +1} - right column.
     *
     * {-1, 0} - top row.
     * {+1, 0} - bottom row.
     */
    private static final int[][] fourDirections = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private static boolean isValidCell(int row, int col, int[][] grid, boolean[][] isVisited) {

        return row >= 0 && col >= 0
                && row < grid.length && col < grid[0].length
                && !isVisited[row][col] && grid[row][col] == 1;
    }

    private static class Tuple {
        int row;
        int col;
        int dist;

        Tuple(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
