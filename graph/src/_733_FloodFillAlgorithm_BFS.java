package src;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=C-2_uSRli8o (Striver) - He solved it using DFS. I did using BFS.
 *
 * https://leetcode.com/problems/flood-fill/
 * https://practice.geeksforgeeks.org/problems/flood-fill-algorithm1856/1
 */
public class _733_FloodFillAlgorithm_BFS {

    class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * time  : O(M * N)
     * space : O(M * N)
     */
    private final int[][] fourDirections = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {

        int n = image.length;
        int m = image[0].length;

        boolean[][] isVisited = new boolean[n][m];

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startRow, startCol));
        isVisited[startRow][startCol] = true;

        int startingColor = image[startRow][startCol];

        while (!queue.isEmpty()) {

            Pair currPixel = queue.poll();
            image[currPixel.row][currPixel.col] = newColor;

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = currPixel.row + fourDirections[i][0];
                int newCol = currPixel.col + fourDirections[i][1];

                if (isValidPixel(newRow, newCol, image, startingColor, isVisited)) {

                    queue.add(new Pair(newRow, newCol));
                    isVisited[newRow][newCol] = true;
                }
            }
        }
        return image;
    }

    private boolean isValidPixel(int newRow, int newCol, int[][] image, int startingColor,
                                 boolean[][] isVisited) {

        return newRow >= 0 && newCol >= 0 &&
                newRow < image.length && newCol < image[0].length &&
                image[newRow][newCol] == startingColor && !isVisited[newRow][newCol];
    }
}
