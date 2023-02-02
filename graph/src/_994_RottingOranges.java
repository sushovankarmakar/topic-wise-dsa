package src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=yf3oUhkvqA0 (Striver : Best Explanation)
 * https://www.youtube.com/watch?v=CxrnOTUlNJE (Tech Dose)
 *
 * https://practice.geeksforgeeks.org/problems/rotten-oranges2536/1
 * https://leetcode.com/problems/rotting-oranges/
 *
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_RottenOranges.java
 */
public class _994_RottingOranges {

    static class Tuple {
        int row;
        int col;
        int time;

        Tuple(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    private final int[][] fourDirections = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int orangesRotting(int[][] grid) {

        int freshOrange = 0;
        int rottenOrange = 0;

        Queue<Tuple> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 1) {
                    freshOrange++;
                }

                if (grid[i][j] == 2) {
                    rottenOrange++;
                    queue.add(new Tuple(i, j, 0));
                }
            }
        }

        int totalTime = 0;

        while (!queue.isEmpty()) {

            Tuple currOrange = queue.poll();

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = currOrange.row - fourDirections[i][0];
                int newCol = currOrange.col - fourDirections[i][1];
                int newTime = currOrange.time + 1;

                if (isFreshOrange(newRow, newCol, grid)) {

                    freshOrange--;
                    grid[newRow][newCol] = 2; // mark it as rotten - IMP : I forgot this.

                    totalTime = Math.max(totalTime, newTime);

                    queue.add(new Tuple(newRow, newCol, newTime));
                }
            }
        }

        return freshOrange == 0 ? totalTime : -1;
    }

    private boolean isFreshOrange(int row, int col, int[][] grid) {

        return row >= 0 && col >= 0
                && row < grid.length && col < grid[0].length
                && grid[row][col] == 1;
    }
}
