package src._6_matrix._2_NumberOfIslands;

public class _200_NumberOfIslands {

    public static void main(String[] args) {
        _200_NumberOfIslands obj = new _200_NumberOfIslands();

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        int islands = obj.numIslands(grid);
        System.out.println(islands);
    }

    public int numIslands(char[][] grid) {

        int numOfIslands = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[i].length; ++j) {

                if (grid[i][j] == '1') {
                    ++numOfIslands;
                    dfs(i, j, grid);
                }
            }
        }

        return numOfIslands;
    }

    private void dfs(int row, int col, char[][] grid) {

        grid[row][col] = '2'; // marking it as visited in the same matrix, '2' means visited

        for (int i = 0; i < fourDirections.length; ++i) {

            int newRow = row + fourDirections[i][0];
            int newCol = col + fourDirections[i][1];

            if (isValid(newRow, newCol, grid)) {
                dfs(newRow, newCol, grid);
            }
        }
    }

    private boolean isValid(int row, int col, char[][] grid) {

        return row >= 0 && col >= 0 &&
                row < grid.length && col < grid[0].length &&
                grid[row][col] == '1'; // this node should be a land
                // grid[row][col] != '2'; // this node should NOT be visited earlier
    }

    private final int[][] fourDirections = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
}
