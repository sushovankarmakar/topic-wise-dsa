package src._6_matrix;

import java.util.LinkedList;
import java.util.Queue;

public class _130_SurroundedRegions_BFS {

    /**
     * multi-source BFS
     */
    public char[][] fill(char[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        boolean[][] isVisited = new boolean[n][m];

        Queue<Pair> queue = new LinkedList<>();

        visitingFirstAndLastRow(mat, isVisited, queue);
        visitingFirstAndLastCol(mat, isVisited, queue);

        bfs(mat, isVisited, queue);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (mat[i][j] == 'O' && !isVisited[i][j]) {
                    mat[i][j] = 'X';
                }
            }
        }

        return mat;
    }

    private void visitingFirstAndLastRow(char[][] mat, boolean[][] isVisited, Queue<Pair> queue) {

        int n = mat.length;
        int m = mat[0].length;

        int firstRow = 0;
        int lastRow = n - 1;

        for (int j = 0; j < m; j++) {

            if (mat[firstRow][j] == 'O' && !isVisited[firstRow][j]) {
                queue.add(new Pair(firstRow, j));
            }

            if (mat[lastRow][j] == 'O' && !isVisited[lastRow][j]) {
                queue.add(new Pair(lastRow, j));
            }
        }
    }

    private void visitingFirstAndLastCol(char[][] mat, boolean[][] isVisited, Queue<Pair> queue) {

        int n = mat.length;
        int m = mat[0].length;

        int firstCol = 0;
        int lastCol = m - 1;

        for (int i = 0; i < n; i++) {

            if (mat[i][firstCol] == 'O' && !isVisited[i][firstCol]) {
                queue.add(new Pair(i, firstCol));
            }

            if (mat[i][lastCol] == 'O' && !isVisited[i][lastCol]) {
                queue.add(new Pair(i, lastCol));
            }
        }
    }

    private void bfs(char[][] mat, boolean[][] isVisited, Queue<Pair> queue) {

        while(!queue.isEmpty()) {

            Pair currPair = queue.poll();
            int currRow = currPair.row;
            int currCol = currPair.col;

            isVisited[currRow][currCol] = true;

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = currRow + fourDirections[i][0];
                int newCol = currCol + fourDirections[i][1];

                if (isValidCell(newRow, newCol, mat, isVisited)) {
                    isVisited[newRow][newCol] = true;
                    queue.add(new Pair(newRow, newCol));
                }
            }
        }
    }

    private class Pair {
        int row;
        int col;

        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private boolean isValidCell(int row, int col, char[][] mat, boolean[][] isVisited) {

        int n = mat.length;
        int m = mat[0].length;

        return row >= 0 && col >= 0 && row < n && col < m
                && mat[row][col] == 'O'
                && !isVisited[row][col];
    }

    private final int[][] fourDirections = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
}
