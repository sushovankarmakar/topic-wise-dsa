package src.shortest_path_algo.dijkstra_algo;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/path-with-minimum-effort/
 * https://www.geeksforgeeks.org/problems/path-with-minimum-effort/1
 * <p>
 * https://www.youtube.com/watch?v=0ytpZyiZFhA&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/g-37-path-with-minimum-effort/
 */
public class _1631_PathWithMinEffort {

    public static void main(String[] args) {

        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };

        int minEffortPath = minimumEffortPath(heights);
        System.out.println("Minimum effort path: " + minEffortPath); // output -> 2
    }

    private static int minimumEffortPath(int[][] heights) {

        int n = heights.length;
        int m = heights[0].length;

        int[][] effortArr = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(effortArr[i], Integer.MAX_VALUE);
        }
        effortArr[0][0] = 0; // source node, we always start from source node [0,0] with 0 effort.

        PriorityQueue<Tuple> pQueue = new PriorityQueue<>((a, b) -> a.effort - b.effort); // min heap based on effort value.
        pQueue.add(new Tuple(0, 0, 0));

        while (!pQueue.isEmpty()) {

            int currRow = pQueue.peek().row;
            int currCol = pQueue.peek().col;
            int currEffort = pQueue.peek().effort;

            pQueue.remove();

            // check for destination node while taking out the node from the priority queue,
            // NOT while inserting into the priority queue.
            if (currRow == n - 1 && currCol == m - 1) {
                return currEffort;
            }

            // here all four directions are working as adjacent nodes.
            for (int k = 0; k < fourDirections.length; k++) {

                int adjRow = currRow - fourDirections[k][0];
                int adjCol = currCol - fourDirections[k][1];

                if (isValid(adjRow, adjCol, heights)) {

                    // A route's effort is
                    // the maximum absolute difference in heights
                    // between two consecutive cells of the route.
                    int adjEffort = Math.abs(heights[currRow][currCol] - heights[adjRow][adjCol]); // MADE A MISTAKE HERE
                    int maxEffort = Math.max(adjEffort, currEffort);

                    // if the maxEffort is less than the effort value of the adjacent node,
                    // then update the effort value of the adjacent node.
                    if (maxEffort < effortArr[adjRow][adjCol]) {

                        effortArr[adjRow][adjCol] = maxEffort;
                        pQueue.add(new Tuple(adjRow, adjCol, maxEffort));
                    }
                }
            }
        }

        return effortArr[n - 1][m - 1];
    }

    private static class Tuple {
        int row;
        int col;
        int effort;

        Tuple(int row, int col, int effort) {
            this.row = row;
            this.col = col;
            this.effort = effort;
        }
    }

    private static final int[][] fourDirections = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static boolean isValid(int row, int col, int[][] heights) {
        return row >= 0 && col >= 0
                && row < heights.length
                && col < heights[0].length;
    }
}
