package src.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/problems/number-of-enclaves/1
 * https://leetcode.com/problems/number-of-enclaves/description/
 * <p>
 * <p>
 * https://www.youtube.com/watch?v=rxKcepXQgU4&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/graph/number-of-enclaves/
 */
public class _1020_NumberOfEnclaves_BFS {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(numberOfEnclaves(grid)); // 3

        int[][] grid1 = {
                {0, 0, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        System.out.println(numberOfEnclaves(grid1)); // 0
    }

    private static int numberOfEnclaves(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] isVisited = new boolean[n][m];

        Queue<Node> queue = new LinkedList<>();

        for (int j = 0; j < m; j++) {

            if (grid[0][j] == 1) {
                queue.add(new Node(0, j));
            }

            if (grid[n - 1][j] == 1) {
                queue.add(new Node(n - 1, j));
            }
        }

        for (int i = 0; i < n; i++) {

            if (grid[i][0] == 1) {
                queue.add(new Node(i, 0));
            }

            if (grid[i][m - 1] == 1) {
                queue.add(new Node(i, m - 1));
            }
        }

        while (!queue.isEmpty()) {

            int row = queue.peek().row;
            int col = queue.peek().col;

            queue.remove();

            isVisited[row][col] = true;

            for (int i = 0; i < directions.length; i++) {

                int newRow = row + directions[i][0];
                int newCol = col + directions[i][1];

                if (isVisited(newRow, newCol, isVisited, grid)) {

                    isVisited[newRow][newCol] = true;
                    queue.add(new Node(newRow, newCol));
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 1 && !isVisited[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isVisited(int row, int col, boolean[][] isVisited, int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        return row >= 0 && col >= 0 && row < n && col < m
                && !isVisited[row][col]
                && grid[row][col] == 1;
    }

    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
