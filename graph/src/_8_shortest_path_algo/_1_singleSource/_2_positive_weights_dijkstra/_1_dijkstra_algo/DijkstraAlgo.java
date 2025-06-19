package src._8_shortest_path_algo._1_singleSource._2_positive_weights_dijkstra._1_dijkstra_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
 * <p>
 * https://www.youtube.com/watch?v=V6H1qAeB-l4&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/dijkstras-algorithm-using-priority-queue-g-32/ (takeUforward)
 * <p>
 * https://www.youtube.com/watch?v=O5axxrvkbOQ&ab_channel=AdityaVerma
 */
public class DijkstraAlgo {

    private int[] dijkstra(int v, int[][] edges, int src) {

        List<List<List<Integer>>> adjList = getAdjList(v, edges);

        int[] distArr = new int[v]; // This array will hold the shortest distance from the source vertex to each other vertex.
        Arrays.fill(distArr, Integer.MAX_VALUE);
        distArr[src] = 0;

        // Min heap: The queue is ordered by distance, so the node with the smallest distance is always at the front.
        // This is the key to the algorithm.
        PriorityQueue<Pair> pQueue = new PriorityQueue<>((a, b) -> {
            if (a.dist != b.dist) {
                // if the distance of both elements is not equal
                // than the element with shorter distance will appear first
                return a.dist - b.dist;
            }
            // If the source of both elements is not equal, then
            // the element with short source number appear first
            // but if both are equal than treeSet will return 0 and treat both arrays as equal
            return a.node - b.node;
        });
        pQueue.add(new Pair(src, 0));

        // simple BFS traversal
        while (!pQueue.isEmpty()) {

            int currNode = pQueue.peek().node;
            int currDist = pQueue.peek().dist;

            pQueue.remove();

            for (List<Integer> adjNodes : adjList.get(currNode)) {

                int adjNode = adjNodes.get(0);
                int adjDist = adjNodes.get(1);

                // If the (distance till now + the distance to the adjacent node) is less than the distance to the adjacent node,
                // then we have found a shorter path to the adjacent node.
                // We update the distance to the adjacent node and add it to the queue.
                if (currDist + adjDist < distArr[adjNode]) { // this logic is actually working as isVisited[] check.

                    distArr[adjNode] = currDist + adjDist;
                    pQueue.add(new Pair(adjNode, distArr[adjNode])); // made a mistake here, should NOT be adjNodeDist instead of distArr[adjNode]
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

    private List<List<List<Integer>>> getAdjList(int v, int[][] edges) {

        List<List<List<Integer>>> adjList = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        // MISTAKE I MADE: You're iterating over i < V for edges, but you should iterate i < edges.length
        // Why: The number of edges is not necessarily the same as the number of vertices.

        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dst = edges[i][1];
            int dist = edges[i][2];

            adjList.get(src).add(Arrays.asList(dst, dist));
            adjList.get(dst).add(Arrays.asList(src, dist));
        }

        return adjList;
    }

    public static void main(String[] args) {

        /*int v = 6;
        int[][] edges = {
                // adjacency nodes for 0
                {0, 1, 4},
                {0, 2, 4},
                // adjacency nodes for 1
                {1, 0, 4},
                {1, 2, 2},
                // adjacency nodes for 2
                {2, 0, 4},
                {2, 1, 2},
                {2, 3, 3},
                {2, 4, 1},
                {2, 5, 6},
                // adjacency nodes for 3
                {3, 2, 3},
                {3, 5, 2},
                // adjacency nodes for 4
                {4, 2, 1},
                {4, 5, 3},
                // adjacency nodes for 5
                {5, 2, 6},
                {5, 3, 2},
                {5, 4, 3}
        };*/

        int v = 6;
        int[][] edges = {
                {0, 1, 2},
                {0, 4, 1},
                {4, 5, 4},
                {4, 2, 2},
                {1, 2, 3},
                {2, 3, 6},
                {5, 3, 1}
        };

        DijkstraAlgo obj = new DijkstraAlgo();

        int src = 0;
        int[] distArr = obj.dijkstra(v, edges, src);

        for (int i = 0; i < v; i++) {
            System.out.println("Shortest distance from " + src + " to " + i + " is " + distArr[i]); // output -> [0, 4, 4, 7, 5, 8]
        }
    }

}
