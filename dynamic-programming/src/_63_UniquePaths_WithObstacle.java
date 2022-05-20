package src;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * https://www.youtube.com/watch?v=YcrXBDAeTCs&ab_channel=AndreyGrehov
 * https://github.com/andreygrehov/dp/tree/master/lecture9
 *
 * https://www.geeksforgeeks.org/unique-paths-in-a-grid-with-obstacles/
 *
 * 1. recursive.
 * 2. memoization top down DP.
 * 3. bottom up DP.
 */

/*
Problem:
	Unique Paths with Obstacles
	A robot is located in the top-left corner of a m x n grid (marked 'S' in the diagram below).
	The robot can only move either down or right at any point in time.
	The robot is trying to reach the bottom-right corner of the grid (marked 'E' in the diagram below).
	Now consider if some obstacles are added to the grids.
	How many unique paths would there be?
	+---+---+---+---+
	| S |   |   |   |
	+---+---+---+---+
	|   | 1 | 1 | 1 |
	+---+---+---+---+
	|   |   |   | E |
	+---+---+---+---+
	An obstacle and empty space is marked as 1 and 0 respectively in the grid.
*/

// Time complexity: O(n^2)
// Space complexity: O(n^2)

public class _63_UniquePaths_WithObstacle {

    public static void main(String[] args) {

        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};

        System.out.println(uniquePathsWithObstacles_1(obstacleGrid)); // 2
        System.out.println(uniquePathsWithObstacles_2(obstacleGrid)); // 2
    }

    public static int uniquePathsWithObstacles_1(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] paths = new int[m][n];

        for (int i = 0; i < m; i++) {

            if (obstacleGrid[i][0] == 1) {
                break;
            }

            paths[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {

            if (obstacleGrid[0][j] == 1) {
                break;
            }

            paths[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    paths[i][j] = 0;
                } else {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                }
            }
        }
        return paths[m - 1][n - 1];
    }

    // same code above, only shorter code.
    public static int uniquePathsWithObstacles_2(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] paths = new int[m][n];
        paths[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    paths[i][j] = 0;
                    continue;
                }

                if (i > 0 && j > 0) {
                    paths[i][j] = paths[i - 1][j] + paths[i][j - 1];
                } else if (i > 0) {
                    paths[i][j] = paths[i - 1][j];
                } else if (j > 0) {
                    paths[i][j] = paths[i][j - 1];
                }
            }
        }
        return paths[m - 1][n - 1];
    }

}
