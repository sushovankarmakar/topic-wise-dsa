package src.shortest_path_algo;

import java.util.*;

/**
 * Shortest path in undirected graph using BFS.
 * https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
 * <p>
 * https://www.youtube.com/watch?v=C4gxoTaI71U&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/shortest-path-in-undirected-graph-with-unit-distance-g-28/
 *
 * https://github.com/sushovankarmakar/topic-wise-dsa/blob/master/graph/src/ShortestPathInDAG.java (Previously solved by me)
 */
public class ShortestPathInUndirectedGraph {

    public static void main(String[] args) {

        int n = 8;
        int m = 8;
        int[][] edges = {
                {0, 1},
                {0, 2},
                {1, 3},
                {1, 4},
                {2, 5},
                {2, 6},
                {3, 7},
                {4, 7}
        };

        int src = 0;
        int[] distArr = shortestPath(edges, n, m, src);
        for (int i = 0; i < n; i++) {
            System.out.println("Shortest distance from " + src + " to " + i + " is: " + distArr[i]);
        }
    }

    private static int[] shortestPath(int[][] edges, int n, int m, int src) {

        List<List<Integer>> adjList = getAdjList(edges, n, m);  // get adjacency list from edges.

        Queue<Pair> queue = new LinkedList<>(); // queue for BFS.
        queue.add(new Pair(src, 0));

        int[] distArr = new int[n]; // this distance array also works as visited array.
        Arrays.fill(distArr, -1);
        distArr[src] = 0;

        while (!queue.isEmpty()) {

            int currNode = queue.peek().node;
            int currDist = queue.peek().dist;
            queue.remove();

            for (int adjNode : adjList.get(currNode)) {

                int newDist = currDist + 1;

                if (distArr[adjNode] == -1) {   // if node is not visited yet.
                    distArr[adjNode] = newDist;
                    queue.add(new Pair(adjNode, newDist));
                } else {
                    // if node is already visited then update the distance if it is less than previous distance.
                    distArr[adjNode] = Math.min(newDist, distArr[adjNode]);
                }
            }
        }

        return distArr;
    }

    private static class Pair {

        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    private static List<List<Integer>> getAdjList(int[][] edges, int n, int m) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int src = edges[i][0];
            int dst = edges[i][1];

            // for undirected graph, edges are bidirectional.
            adjList.get(src).add(dst);  // add edge from src to dst.
            adjList.get(dst).add(src);  // add edge from dst to src.
        }

        return adjList;
    }
}
