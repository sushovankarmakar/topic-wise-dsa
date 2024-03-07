package src.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=C-2_uSRli8o (Striver) - He solved it using DFS. I did using BFS.
 *
 * https://leetcode.com/problems/flood-fill/
 * https://practice.geeksforgeeks.org/problems/flood-fill-algorithm1856/1
 */
public class _733_FloodFillAlgorithm_BFS {

    public static void main(String[] args) {
        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int[][] newImage = floodFill(image, 0, 1, 2);
        for (int i = 0; i < newImage.length; i++) {
            for (int j = 0; j < newImage[0].length; j++) {
                System.out.print(newImage[i][j] + " ");
            }
            System.out.println();
        }
        /**
         * OUTPUT :
         * 2 2 2
         * 2 2 0
         * 2 0 1
         */
    }


    /**
     * time  : O(M * N)
     * space : O(M * N)
     */
    private static int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {

        int n = image.length;
        int m = image[0].length;
        int startingColor = image[startRow][startCol];

        boolean[][] isVisited = new boolean[n][m];
        Queue<Pair> queue = new LinkedList<>();

        // INITIALIZING THE QUEUE
        image[startRow][startCol] = newColor;
        isVisited[startRow][startCol] = true;   // IMPORTANT : MARK IT AS VISITED
        queue.add(new Pair(startRow, startCol));

        // TRAVERSING THE QUEUE
        while (!queue.isEmpty()) {

            Pair currPixel = queue.poll();

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = currPixel.row + fourDirections[i][0];
                int newCol = currPixel.col + fourDirections[i][1];

                if (isValidPixel(newRow, newCol, image, startingColor, isVisited)) {

                    image[newRow][newCol] = newColor;
                    isVisited[newRow][newCol] = true;   // IMPORTANT : MARK IT AS VISITED
                    queue.add(new Pair(newRow, newCol));
                }
            }
        }
        return image;
    }

    private static boolean isValidPixel(int newRow, int newCol, int[][] image, int startingColor,
                                 boolean[][] isVisited) {

        return newRow >= 0 && newCol >= 0 &&
                newRow < image.length && newCol < image[0].length &&
                image[newRow][newCol] == startingColor && !isVisited[newRow][newCol];
    }

    private static final int[][] fourDirections = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private static class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
