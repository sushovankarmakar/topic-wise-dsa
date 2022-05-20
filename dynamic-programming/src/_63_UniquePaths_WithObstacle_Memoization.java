package src;

/**
 * https://leetcode.com/problems/unique-paths-ii/
 * https://www.geeksforgeeks.org/unique-paths-in-a-grid-with-obstacles/ (second solution is the recursive one)
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
public class _63_UniquePaths_WithObstacle_Memoization {

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


        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int m = 0; m < dp.length; m++) {
            for (int n = 0; n < dp[0].length; n++) {
                dp[m][n] = -1;
            }
        }

        return paths(obstacleGrid, i, j, dp);
    }

    /**
     * difference between recursive and memoization
     * --------------------------------------------
     *
     * in memoization we keep a matrix
     * and store the calculated value in that matrix before returning it.
     *
     * and before doing any recursive call, we first check in the matrix that value is already computed or not.
     * if value is present then just return it
     * or else if value is not present, then go for further recursive calls.
     */
    private static int paths(int[][] obstacleGrid, int i, int j, int[][] dp) {

        if (i == 0 && j == 0) {

            dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : 1;
            return dp[i][j];
        }

        if (obstacleGrid[i][j] == 1) {
            dp[i][j] = 0;
            return dp[i][j];
        }

        // this below if condition is the actual memoization. this helps to reduce repetitive calls.
        if (dp[i][j] != -1) {   // if value is already calculated, don't re-calculate. just return it.
            return dp[i][j];
        }

        if (i == 0) {
            dp[i][j] = paths(obstacleGrid, i, j - 1, dp);
            return dp[i][j];
        }

        if (j == 0) {
            dp[i][j] = paths(obstacleGrid, i - 1, j, dp);
            return dp[i][j];
        }

        dp[i][j] = paths(obstacleGrid, i, j - 1, dp) + paths(obstacleGrid, i - 1, j, dp);
        return dp[i][j];
    }

}
