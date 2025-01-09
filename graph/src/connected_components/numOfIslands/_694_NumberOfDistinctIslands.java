package src.connected_components.numOfIslands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://www.geeksforgeeks.org/problems/number-of-distinct-islands/1
 * https://takeuforward.org/plus/data-structures-and-algorithm/graphs/traversal-problems/number-of-distinct-islands
 *
 * https://takeuforward.org/data-structure/number-of-distinct-islands/ (Solve it by reading this blog)
 *
 * https://www.youtube.com/watch?v=7zmgQSJghpo (Striver) - Explained really well.
 *
 * https://leetcode.ca/2017-10-24-694-Number-of-Distinct-Islands/
 */
public class _694_NumberOfDistinctIslands {

    public static void main(String[] args) {
        _694_NumberOfDistinctIslands obj = new _694_NumberOfDistinctIslands();
        int[][] grid = {
                {1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0}
        };
        System.out.println(obj.countDistinctIslands(grid));
    }

    public int countDistinctIslands(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] isVisited = new boolean[n][m];

        Set<List<String>> distinctIslands = new HashSet<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {

                if (grid[row][col] == 1 && !isVisited[row][col]) {

                    List<String> cells = new ArrayList<>();
                    dfs(row, col, row, col, cells, grid, isVisited);
                    distinctIslands.add(cells);
                }
            }
        }

        return distinctIslands.size();
    }

    private void dfs(int row, int col, int baseRow, int baseCol, List<String> cells, int[][] grid, boolean[][] isVisited) {

        isVisited[row][col] = true;
        String cell = (row - baseRow) + "," + (col - baseCol);
        cells.add(cell);

        for (int[] fourDirection : fourDirections) {

            int newRow = row + fourDirection[0];
            int newCol = col + fourDirection[1];

            if (isValid(newRow, newCol, grid, isVisited)) {
                isVisited[newRow][newCol] = true;
                dfs(newRow, newCol, baseRow, baseCol, cells, grid, isVisited);
            }
        }
    }

    private boolean isValid(int row, int col, int[][] grid, boolean[][] isVisited) {
        int n = grid.length;
        int m = grid[0].length;
        return row >= 0 && row < n && col >= 0 && col < m
                && grid[row][col] == 1
                && !isVisited[row][col];
    }

    private final int[][] fourDirections = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

}
