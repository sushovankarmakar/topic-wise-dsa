package src.matrix;

/**
 * https://www.youtube.com/watch?v=C-2_uSRli8o (Striver) - He solved it using DFS. I did using BFS.
 *
 * https://leetcode.com/problems/flood-fill/
 * https://practice.geeksforgeeks.org/problems/flood-fill-algorithm1856/1
 */
public class _733_FloodFillAlgorithm_DFS {

    private final int[][] fourDirections = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    /**
     * time  : O(M * N)
     * space : O(M * N)
     */
    public int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {

        int n = image.length;
        int m = image[0].length;

        boolean[][] isVisited = new boolean[n][m];

        int startingColor = image[startRow][startCol];

        dfs(image, startRow, startCol, startingColor, newColor, isVisited);
        return image;
    }

    private void dfs(int[][] image, int currRow, int currCol, int startingColor, int newColor,
                     boolean[][] isVisited) {

        image[currRow][currCol] = newColor;

        for (int i = 0; i < fourDirections.length; i++) {

            int newRow = currRow + fourDirections[i][0];
            int newCol = currCol + fourDirections[i][1];

            if (isValidPixel(newRow, newCol, image, startingColor, isVisited)) {

                isVisited[newRow][newCol] = true;
                dfs(image, newRow, newCol, startingColor, newColor, isVisited);
            }
        }
    }

    private boolean isValidPixel(int newRow, int newCol, int[][] image, int startingColor,
                                 boolean[][] isVisited) {

        return newRow >= 0 && newCol >= 0 &&
                newRow < image.length && newCol < image[0].length &&
                image[newRow][newCol] == startingColor && !isVisited[newRow][newCol];
    }
}
