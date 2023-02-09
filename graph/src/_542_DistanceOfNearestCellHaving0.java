package src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=edXdVwkYHF8 (Striver)
 *
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

    static class Tuple {
        int row;
        int col;
        int dist;

        Tuple(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    //Function to find distance of nearest 1 in the grid for each cell.
    public static int[][] nearest(int[][] grid) {

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
            Tuple currCell = queue.poll();

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = currCell.row + fourDirections[i][0];
                int newCol = currCell.col + fourDirections[i][1];
                int newDist = currCell.dist + 1;

                if (isValidCell(newRow, newCol, grid, isVisited)) {

                    isVisited[newRow][newCol] = true;
                    nearestDistance[newRow][newCol] = newDist;
                    queue.add(new Tuple(newRow, newCol, newDist));
                }
            }
        }
        return nearestDistance;
    }

    private static final int[][] fourDirections = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private static boolean isValidCell(int row, int col, int[][] grid, boolean[][] isVisited) {

        return row >= 0 && col >= 0
                && row < grid.length && col < grid[0].length
                && !isVisited[row][col] && grid[row][col] == 1;
    }
}
