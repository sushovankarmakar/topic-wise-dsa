package src;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * https://www.geeksforgeeks.org/unique-paths-in-a-grid-with-obstacles/ (first solution is the recursive one)
 *
 * Solved it by myself.
 * first draw the recursive choice diagram.
 * then solution can be derived from that.
 *
 * 1. recursive.
 * 2. memoization top down DP.
 * 3. bottom up DP.
 *
 * choice diagram : https://drive.google.com/file/d/1LIvUZIqPd3plUkNXxZmwz3hU03SrEkaH/view?usp=sharing
 */
public class _63_UniquePaths_WithObstacle_Recursive {

    public static void main(String[] args) {

        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};

        System.out.println(uniquePathsWithObstacles(obstacleGrid)); // 2
        System.out.println(uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}})); // 1
        System.out.println(uniquePathsWithObstacles(new int[][]{{1}})); // 0
        System.out.println(uniquePathsWithObstacles(new int[][]{{0}})); // 1
    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int i = obstacleGrid.length - 1;
        int j = obstacleGrid[0].length - 1;

        return paths(obstacleGrid, i, j);
    }

    private static int paths(int[][] obstacleGrid, int i, int j) {

        if (i == 0 && j == 0) {
            return obstacleGrid[i][j] == 1 ? 0 : 1;
        }

        if (obstacleGrid[i][j] == 1) {
            return 0;
        }

        if (i == 0) {
            return paths(obstacleGrid, i, j - 1);
        }

        if (j == 0) {
            return paths(obstacleGrid, i - 1, j);
        }

        return paths(obstacleGrid, i, j - 1) + paths(obstacleGrid, i - 1, j);
    }

}
