package src;

/**
 * https://leetcode.com/problems/unique-paths/
 * https://www.youtube.com/watch?v=YcrXBDAeTCs&ab_channel=AndreyGrehov
 * https://github.com/andreygrehov/dp/tree/master/lecture9
 */

/*
Problem:
	Unique Paths
	A robot is located in the top-left corner of a m x n grid (marked 'S' in the diagram below).
	The robot can only move either down or right at any point in time.
	The robot is trying to reach the bottom-right corner of the grid (marked 'E' in the diagram below).
	How many possible unique paths are there?
	+---+---+---+---+
	| S |   |   |   |
	+---+---+---+---+
	|   |   |   |   |
	+---+---+---+---+
	|   |   |   | E |
	+---+---+---+---+
	Above is a 3 x 4 grid. How many possible unique paths are there?
*/

// Time complexity: O(n^2)
// Space complexity: O(n^2)

// F(i,j) = F(i-1,j) + F(i,j-1) <- transition function
public class _62_UniquePaths {

    public static void main(String[] args) {
        System.out.println(uniquePaths_1(3, 7)); // 28
        System.out.println(uniquePaths_2(3, 2)); // 3
    }

    public static int uniquePaths_1(int m, int n) {

        int[][] grids = new int[m][n];

        for (int i = 1; i < m; i++) {
            grids[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            grids[0][j] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grids[i][j] = grids[i][j - 1] + grids[i - 1][j];
            }
        }

        return grids[m - 1][n - 1];
    }

    // same code above, only shorter code.
    public static int uniquePaths_2(int m, int n) {

        int[][] grids = new int[m][n];
        grids[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i > 0 && j > 0) {
                    grids[i][j] = grids[i][j - 1] + grids[i - 1][j];
                } else if (i > 0) {
                    grids[i][0] = grids[i - 1][j];
                } else if (j > 0) {
                    grids[0][j] = grids[i][j - 1];
                }
            }
        }

        return grids[m - 1][n - 1];
    }

}
