package src.shortest_path_algo.dijkstra_algo;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
 * https://www.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1
 * <p>
 * https://www.youtube.com/watch?v=U5Mw4eyUmw4&t=1s&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/g-36-shortest-distance-in-a-binary-maze/
 */
public class _1091_ShortestDistanceInBinaryMaze {

    public static void main(String[] args) {

        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}
        };

        int[] source = {0, 1};
        int[] destination = {2, 2};

        int shortestPath = shortestPath(grid, source, destination);
        System.out.println("Shortest path from source to destination: " + shortestPath); // output -> 3
    }

    private static int shortestPath(int[][] grid, int[] source, int[] destination) {

        // edge case :
        if (source[0] == destination[0] && source[1] == destination[1]) {
            // source node and destination node is same.
            return 0;
        }

        int n = grid.length;
        int m = grid[0].length;

        int[][] distArr = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distArr[i], Integer.MAX_VALUE);
        }

        distArr[source[0]][source[1]] = 0;

        PriorityQueue<Tuple> pQueue = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pQueue.add(new Tuple(source[0], source[1], 0));

        while (!pQueue.isEmpty()) {

            int currRow = pQueue.peek().row;
            int currCol = pQueue.peek().col;
            int currDist = pQueue.peek().dist;

            pQueue.remove();

            // here all four directions are working as adjacent nodes.
            for (int k = 0; k < fourDirections.length; k++) {

                int adjRow = currRow - fourDirections[k][0];
                int adjCol = currCol - fourDirections[k][1];

                if (isValid(adjRow, adjCol, grid)) {

                    if (1 + currDist < distArr[adjRow][adjCol]) {

                        if (adjRow == destination[0] && adjCol == destination[1]) {
                            return 1 + currDist;
                        }

                        distArr[adjRow][adjCol] = 1 + currDist;
                        pQueue.add(new Tuple(adjRow, adjCol, 1 + currDist));
                    }
                }
            }
        }

        return -1;
    }

    private static class Tuple {
        int row;
        int col;
        int dist;

        Tuple(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    private static final int[][] fourDirections = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static boolean isValid(int row, int col, int[][] grid) {
        return row >= 0 && col >= 0
                && row < grid.length
                && col < grid[0].length
                && grid[row][col] == 1;
    }
}
