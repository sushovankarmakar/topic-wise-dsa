package src.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=yf3oUhkvqA0 (Striver : Best Explanation)
 * https://www.youtube.com/watch?v=CxrnOTUlNJE (Tech Dose)
 * <p>
 * https://practice.geeksforgeeks.org/problems/rotten-oranges2536/1
 * https://leetcode.com/problems/rotting-oranges/
 * <p>
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_RottenOranges.java
 */
public class _994_RottingOranges {

    public static void main(String[] args) {
        int[][] grid1 = {
                {0, 1, 2},
                {0, 1, 2},
                {2, 1, 1}
        };
        System.out.println(orangesRotting(grid1));   // 1

        int[][] grid2 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}};
        System.out.println(orangesRotting(grid2));   // 4

        int[][] grid3 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}};
        System.out.println(orangesRotting(grid3));   // -1

        int[][] grid4 = {{0, 2}};
        System.out.println(orangesRotting(grid4));  // 0
    }

    /**
     * time : O(n * m)
     * space ; O(n * m)
     */
    private static int orangesRotting(int[][] grid) {

        int freshOrange = 0;

        Queue<Tuple> queue = new LinkedList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

                if (grid[i][j] == 1) {
                    freshOrange++;
                }

                /**
                 * we need to prioritize each rotten orange FIRST,
                 * so we need to add them into the queue first before calling the DFS method.
                 */
                if (grid[i][j] == 2) {
                    queue.add(new Tuple(i, j, 0));  // MOST IMPORTANT STEP : Need to add all the rotten oranges first in the queue.
                }
            }
        }

        // don't keep maxTime = Integer.MAX_VALUE,
        // because if there is no oranges, then we need to return 0, not Integer.MAX_VALUE
        int maxTime = 0;

        while (!queue.isEmpty()) {

            Tuple currOrange = queue.poll();

            int currRow = currOrange.row;
            int currCol = currOrange.col;
            int currTime = currOrange.time;

            maxTime = Math.max(maxTime, currTime); // IMPORTANT to check the time here only. DON't check inside the below for loop. I did this mistake.

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = currRow + fourDirections[i][0];
                int newCol = currCol + fourDirections[i][1];
                int newTime = currTime + 1;

                if (isFreshOrange(newRow, newCol, grid)) {

                    freshOrange--;
                    grid[newRow][newCol] = 2; // mark it as rotten - IMPORTANT : I forgot this.

                    queue.add(new Tuple(newRow, newCol, newTime));
                }
            }
        }

        return freshOrange == 0 ? maxTime : -1;
    }

    private static boolean isFreshOrange(int row, int col, int[][] grid) {

        return row >= 0 && col >= 0
                && row < grid.length && col < grid[0].length
                && grid[row][col] == 1;
    }

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

    private static final int[][] fourDirections = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
}
