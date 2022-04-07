package src;

/**
 * https://leetcode.com/problems/minimum-path-sum/
 * https://www.youtube.com/watch?v=YcrXBDAeTCs&ab_channel=AndreyGrehov
 * https://github.com/andreygrehov/dp/tree/master/lecture9
 */

/*
Problem:
	Minimum Profit in a Grid
	A robot is located in the top-left corner of a m x n grid (marked 'S' in the diagram below).
	The robot can only move either down or right at any point in time.
	The robot is trying to reach the bottom-right corner of the grid (marked 'E' in the diagram below).
	Each cell contains a coin the robot can collect.
	What is the minimum profit the robot can accumulate?
	+---+---+---+---+
	| S | 2 | 2 | 1 |
	+---+---+---+---+
	| 3 | 1 | 1 | 1 |
	+---+---+---+---+
	| 4 | 4 | 2 | E |
	+---+---+---+---+
*/

// f(i,j) = min(f(i-1, j), f(i, j-1)) + grid(i,j)
public class _64_MinimumPathSum {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        System.out.println(minPathSum_1(grid));
        System.out.println(minPathSum_2(grid));
    }

    public static int minPathSum_1(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        int[][] minCost = new int[m][n];
        minCost[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            minCost[i][0] = minCost[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            minCost[0][j] = minCost[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                minCost[i][j] = Math.min(minCost[i][j - 1], minCost[i - 1][j]) + grid[i][j];
            }
        }

        return minCost[m - 1][n - 1];
    }

    // same code above, only shorter code.
    public static int minPathSum_2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] minCost = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                minCost[i][j] = grid[i][j];

                if (i > 0 && j > 0) {
                    minCost[i][j] += Math.min(minCost[i][j - 1], minCost[i - 1][j]);
                } else if (i > 0) {
                    minCost[i][j] += minCost[i - 1][j];
                } else if (j > 0) {
                    minCost[i][j] += minCost[i][j - 1];
                }
            }
        }
        return minCost[m - 1][n - 1];
    }
}
