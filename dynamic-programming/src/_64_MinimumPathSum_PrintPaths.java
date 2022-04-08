package src;

/**
 * https://www.youtube.com/watch?v=MSRTLzJDfag&ab_channel=AndreyGrehov (Print the path solution)
 * https://github.com/andreygrehov/dp/tree/master/lecture10
 */

/*
Problem:
	Minimum Profit in a Grid (homework for lecture9)
	A robot is located in the top-left corner of a m x n grid (marked 'S' in the diagram below).
	The robot can only move either down or right at any point in time.
	The robot is trying to reach the bottom-right corner of the grid (marked 'E' in the diagram below).
	Each cell contains a coin the robot can collect.
	What is the path that lead to the minimum profit the robot can accumulate?
	+---+---+---+---+
	| S | 2 | 2 | 1 |
	+---+---+---+---+
	| 3 | 1 | 1 | 1 |
	+---+---+---+---+
	| 4 | 4 | 2 | E |
	+---+---+---+---+
*/

// Time complexity: O(mn)
// Space complexity: O(mn)

public class _64_MinimumPathSum_PrintPaths {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        int[][] paths = minPathSum_printPath(grid);
        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < paths[0].length; j++) {
                System.out.print(paths[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------------------");

        int[][] grid2 = {
                {0, 2, 2, 50},
                {3, 1, 1, 100},
                {4, 4, 2, 0}};
        int[][] paths2 = minPathSum_printPath(grid2);
        for (int i = 0; i < paths2.length; i++) {
            for (int j = 0; j < paths2[0].length; j++) {
                System.out.print(paths2[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] minPathSum_printPath(int[][] grid) {
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

        int[][] paths = new int[m][n];
        return getPath(minCost, paths, m - 1, n - 1);   // backtrack the path from the last cell.
    }

    // RECURSIVE and BACKTRACKING
    private static int[][] getPath(int[][] minCost, int[][] paths, int i, int j) {

        paths[i][j] = 1;

        if (i == 0 && j == 0) {
            return paths;
        } else if (i == 0) { // came from left
            paths = getPath(minCost, paths, i, j - 1);
        } else if (j == 0) { // came from top
            paths = getPath(minCost, paths, i - 1, j);
        } else {
            if (minCost[i][j - 1] > minCost[i - 1][j]) {    // came from top
                paths = getPath(minCost, paths, i - 1, j);
            } else {                                        // came from left
                paths = getPath(minCost, paths, i, j - 1);
            }
        }

        return paths;
    }
}
