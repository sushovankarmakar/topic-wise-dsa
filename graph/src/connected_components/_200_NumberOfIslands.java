package src.connected_components;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This question was asked to me in Google.
 *
 * https://www.youtube.com/watch?v=muncqlKJrH0 (Striver : Well explained)
 *
 * https://leetcode.com/problems/number-of-islands/
 * https://practice.geeksforgeeks.org/problems/find-the-number-of-islands/1
 */
public class _200_NumberOfIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}};

        char[][] grid1 = {
                {'0','1','1','0'},
                {'0','1','1','0'},
                {'0','0','1','0'},
                {'0','0','0','0'},
                {'1','1','0','1'}};

        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};

        System.out.println(numIslands(grid2));
    }

    static class Pair {
        int row;
        int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static boolean[][] isVisited;

    public static int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int numOfIslands = 0;

        isVisited = new boolean[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (grid[row][col] == '1' && !isVisited[row][col]) {
                    bfs(row, col, grid);
                    numOfIslands++;
                }
            }
        }

        return numOfIslands;
    }

    // all eight directions. clockwise. starting from top left corner.  (horizontally + vertically + diagonally)
    static int[][] eightDirections = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    // all four directions. (horizontally + vertically)
    static int[][] fourDirections = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static private void bfs(int row, int col, char[][] grid) {

        Queue<Pair> queue = new LinkedList<>();

        Pair startingNode = new Pair(row, col);
        queue.add(startingNode);
        isVisited[row][col] = true;

        while (!queue.isEmpty()) {

            Pair currNode = queue.poll();
            System.out.println(currNode.row + " " + currNode.col);

            for (int i = 0; i < eightDirections.length; i++) {

                int newRow = currNode.row + eightDirections[i][0];
                int newCol = currNode.col + eightDirections[i][1];

                if (isValidLand(newRow, newCol, grid, isVisited)) {

                    queue.add(new Pair(newRow, newCol));
                    isVisited[newRow][newCol] = true;
                }
            }
        }
    }

    static private boolean isValidLand(int row, int col, char[][] grid, boolean[][] isVisited) {

        // NOTE :
        // IMP : I didn't write this condition well, so was getting TLE
        return row >= 0 && col >= 0 &&
                row < grid.length && col < grid[0].length &&
                grid[row][col] == '1' && !isVisited[row][col];
    }

}
