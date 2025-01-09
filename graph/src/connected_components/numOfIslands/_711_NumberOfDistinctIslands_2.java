package src.connected_components.numOfIslands;

import java.util.*;

/**
 * This question was asked to me in Google.
 * <p>
 * https://www.geeksforgeeks.org/problems/find-the-number-of-islands/1
 * https://leetcode.com/problems/number-of-distinct-islands-ii/description/ (Premium)
 * <p>
 * https://www.youtube.com/watch?v=muncqlKJrH0&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/number-of-islands/ (Blog)
 */
public class _711_NumberOfDistinctIslands_2 {

    public static void main(String[] args) {

        /**
         * 0 1 1 1 0 0 0
         * 0 0 1 1 0 1 0
         *
         * ans : 2
         */
        char[][] grid = {
                {'0', '1', '1', '1', '0', '0', '0'},
                {'0', '0', '1', '1', '0', '1', '0'}
        };

        //System.out.println(numIslands(grid)); // 2
    }

    class Solution {
        // Dimensions of the grid
        private int rows;
        private int cols;
        // The 2D grid itself
        private int[][] grid;

        // Method to count the number of distinct islands
        public int numDistinctIslands2(int[][] grid) {
            rows = grid.length;
            cols = grid[0].length;
            this.grid = grid;
            Set<List<List<Integer>>> uniqueIslands = new HashSet<>();

            // Iterate over each cell in the grid
            for (int i = 0; i < rows; ++i) {
                for (int j = 0; j < cols; ++j) {
                    // If the cell is a part of an island (land)
                    if (grid[i][j] == 1) {
                        List<Integer> islandShape = new ArrayList<>();
                        // Use DFS to mark the visited cells and capture the island's shape
                        dfs(i, j, islandShape);
                        // Normalize the shape to its canonical form and add it to the set
                        uniqueIslands.add(normalize(islandShape));
                    }
                }
            }
            // Returns the number of unique islands
            return uniqueIslands.size();
        }

        // Normalizes the shape of an island to identify it uniquely
        private List<List<Integer>> normalize(List<Integer> shape) {
            List<int[]>[] allTransformations = new List[8];

            // Initialize lists for all the transformations
            for (int i = 0; i < 8; ++i) {
                allTransformations[i] = new ArrayList<>();
            }

            // Generate all possible transformations for the island shape
            for (int code : shape) {
                int row = code / cols;
                int col = code % cols;
                allTransformations[0].add(new int[]{row, col});
                allTransformations[1].add(new int[]{row, -col});
                allTransformations[2].add(new int[]{-row, col});
                allTransformations[3].add(new int[]{-row, -col});
                allTransformations[4].add(new int[]{col, row});
                allTransformations[5].add(new int[]{col, -row});
                allTransformations[6].add(new int[]{-col, row});
                allTransformations[7].add(new int[]{-col, -row});
            }

            // Normalize each transformation by sorting and repositioning
            for (List<int[]> transformation : allTransformations) {
                // Sort by coordinates
                transformation.sort((a, b) ->
                        a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
                );

                // Reposition the transformation so it starts at the origin (0,0)
                int baseRow = transformation.get(0)[0];
                int baseCol = transformation.get(0)[1];
                for (int k = transformation.size() - 1; k >= 0; --k) {
                    int[] coords = transformation.get(k);
                    coords[0] -= baseRow;
                    coords[1] -= baseCol;
                    transformation.set(k, coords);
                }
            }

            // Sort the transformations to find the canonical form (the smallest)
            Arrays.sort(allTransformations, (a, b) -> {
                for (int k = 0; k < a.size(); ++k) {
                    int[] coordsA = a.get(k);
                    int[] coordsB = b.get(k);
                    if (coordsA[0] != coordsB[0]) {
                        return coordsA[0] - coordsB[0];
                    }
                    if (coordsA[1] != coordsB[1]) {
                        return coordsA[1] - coordsB[1];
                    }
                }
                return 0;
            });

            // Convert the smallest transformation to a List of Lists and return
            List<List<Integer>> canonicalShape = new ArrayList<>();
            for (int[] coords : allTransformations[0]) {
                canonicalShape.add(Arrays.asList(coords[0], coords[1]));
            }
            return canonicalShape;
        }

        // Helper DFS method to explore the island
        private void dfs(int row, int col, List<Integer> islandShape) {
            // Encode the current cell as a single integer and add to island shape
            islandShape.add(row * cols + col);
            // Mark the cell as visited by setting it to 0
            grid[row][col] = 0;
            // Directions for exploring adjacent cells
            int[] dirs = {-1, 0, 1, 0, -1};

            // Explore all four directions
            for (int k = 0; k < 4; ++k) {
                int nextRow = row + dirs[k];
                int nextCol = col + dirs[k + 1];
                // Continue DFS if the next cell is valid and part of the island
                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && grid[nextRow][nextCol] == 1) {
                    dfs(nextRow, nextCol, islandShape);
                }
            }
        }
    }
}
