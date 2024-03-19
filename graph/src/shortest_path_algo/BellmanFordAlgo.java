package src.shortest_path_algo;

import java.util.Arrays;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1 (GFG)
 * https://www.youtube.com/watch?v=0vVofAhAYjc&ab_channel=takeUforward (Striver)
 */
public class BellmanFordAlgo {

    public static void main(String[] args) {

        int v = 6;
        List<List<Integer>> edges = Arrays.asList(
                Arrays.asList(3, 2, 6),
                Arrays.asList(5, 3, 1),
                Arrays.asList(0, 1, 5),
                Arrays.asList(1, 5, -3),
                Arrays.asList(1, 2, -2),
                Arrays.asList(3, 4, -2),
                Arrays.asList(2, 4, 3)
        );

        int[] dist = bellmanFordAlgo(v, edges, 0); // output -> dist [0, 5, 3, 1, 6, 4]

        if (dist[0] == -1) {
            System.out.println("Negative cycle detected");
        } else {
            for (int i = 0; i < v; i++) {
                System.out.println("Shortest distance from 0 to " + i + " is " + dist[i]);
            }
        }
    }

    /**
     * Bellman Ford Algorithm
     * This algorithm is used to find the shortest path from a single source vertex to all other vertices in a weighted graph.
     * It depends on the relaxation principle
     * where the shortest distance for all vertices is gradually replaced by more accurate values
     * until eventually reaching the optimum solution.
     * <p>
     * It is similar to Dijkstra's algorithm, but it can work with graphs in which edges can have negative weights.
     * <p>
     * <p>
     * Negative Cycle Detection:
     * If we are able to relax the edges even after the nth pass, then there is a negative cycle in the graph.
     * <p>
     * Time Complexity: O(V * E)
     * Space Complexity: O(V)
     */
    private static int[] bellmanFordAlgo(int v, List<List<Integer>> edges, int start) {

        int[] dist = new int[v];
        Arrays.fill(dist, 100000000);
        dist[start] = 0;

        /**
         * V-1 times relaxation is enough to find the shortest path
         * because the shortest path can have at most V-1 edges
         * so, if we relax all the edges V-1 times, then we will get the shortest path
         *
         * if we relax the edges V times and still the distance is getting updated, then there is a negative cycle
         */

        // relaxation of edges start here
        for (int i = 0; i < v - 1; i++) {

            for (List<Integer> edge : edges) {

                int src = edge.get(0);
                int dst = edge.get(1);
                int wt = edge.get(2);

                // For each edge (u, v) with weight w,
                // if the distance to v through u is shorter than the current distance to v,
                // update the distance to v.
                if (dist[src] != 100000000 && (dist[src] + wt < dist[dst])) {

                    dist[dst] = dist[src] + wt;
                }
            }
        }

        // Nth relaxation to check negative cycle
        for (List<Integer> edge : edges) {

            int src = edge.get(0);
            int dst = edge.get(1);
            int wt = edge.get(2);

            if (dist[src] != 100000000 && (dist[src] + wt < dist[dst])) {

                return new int[]{-1};
            }
        }

        return dist;
    }
}
