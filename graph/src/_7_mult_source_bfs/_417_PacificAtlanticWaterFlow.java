package src._7_mult_source_bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/description/
 */

public class _417_PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        _417_PacificAtlanticWaterFlow o = new _417_PacificAtlanticWaterFlow();
        //int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        //int[][] heights = {{1}};
        int[][] heights = {{1,1},{1,1},{1,1}};
        List<List<Integer>> cells = o.pacificAtlantic(heights);
        System.out.println(cells);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        boolean[][] isVisitedPacificOcean = waterFlows(heights, true); // Pacific Ocean
        boolean[][] isVisitedAtlanticOcean = waterFlows(heights, false); //  Atlantic Ocean

        List<List<Integer>> cells = new ArrayList<>();

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (isVisitedPacificOcean[i][j] && isVisitedAtlanticOcean[i][j]) {
                    cells.add(Arrays.asList(i, j));
                }
            }
        }
        return cells;
    }

    private boolean[][] waterFlows(int[][] heights, boolean isPacific) {

        int m = heights.length;
        int n = heights[0].length;

        Queue<Pair> queue = new LinkedList<>();
        boolean[][] isVisited = new boolean[m][n];
        if (isPacific) {
            for (int j = 0; j < n; j++) {
                queue.add(new Pair(0, j));
                isVisited[0][j] = true;
            }
            for (int i = 0; i < m; i++) {
                queue.add(new Pair(i, 0));
                isVisited[i][0] = true;
            }
        } else {
            for (int j = 0; j < n; j++) {
                queue.add(new Pair(m - 1, j));
                isVisited[m - 1][j] = true;
            }
            for (int i = 0; i < m; i++) {
                queue.add(new Pair(i, n - 1));
                isVisited[i][n - 1] = true;
            }
        }

        while (!queue.isEmpty()) {

            Pair pair = queue.poll();
            for (int[] direction : directions) {
                int newRow = pair.row + direction[0];
                int newCol = pair.col + direction[1];
                if (isValid(heights, isVisited, pair, newRow, newCol)) {
                    queue.add(new Pair(newRow, newCol));
                    isVisited[newRow][newCol] = true;
                }
            }
        }

        return isVisited;
    }

    private boolean isValid(int[][] heights, boolean[][] isVisited, Pair pair, int row, int col) {
        return 0 <= row && row < heights.length
                && 0 <= col && col < heights[0].length
                && !isVisited[row][col]
                && heights[pair.row][pair.col] <= heights[row][col];
    }

    private int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    private class Pair {
        int row;
        int col;
        Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
