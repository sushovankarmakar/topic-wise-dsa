package src.shortest_path_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
 * <p>
 * https://www.youtube.com/watch?v=V6H1qAeB-l4&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/dijkstras-algorithm-using-priority-queue-g-32/ (takeUforward)
 */
public class DijkstraAlgo {

    public static void main(String[] args) {

        int V = 6;
        List<List<List<Integer>>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        // adjacency nodes for 0
        addEdge(adjList, 0, 1, 4);
        addEdge(adjList, 0, 2, 4);
        // adjacency nodes for 1
        addEdge(adjList, 1, 0, 4);
        addEdge(adjList, 1, 2, 2);
        // adjacency nodes for 2
        addEdge(adjList, 2, 0, 4);
        addEdge(adjList, 2, 1, 2);
        addEdge(adjList, 2, 3, 3);
        addEdge(adjList, 2, 4, 1);
        addEdge(adjList, 2, 5, 6);
        // adjacency nodes for 3
        addEdge(adjList, 3, 2, 3);
        addEdge(adjList, 3, 5, 2);
        // adjacency nodes for 4
        addEdge(adjList, 4, 2, 1);
        addEdge(adjList, 4, 5, 3);
        // adjacency nodes for 5
        addEdge(adjList, 5, 2, 6);
        addEdge(adjList, 5, 3, 2);
        addEdge(adjList, 5, 4, 3);

        int src = 0;
        int[] distArr = dijkstra(V, adjList, src);

        for (int i = 0; i < V; i++) {
            System.out.println("Shortest distance from " + src + " to " + i + " is " + distArr[i]); // output -> [0, 4, 4, 7, 5, 8]
        }
    }

    private static int[] dijkstra(int V, List<List<List<Integer>>> adjList, int src) {

        int[] distArr = new int[V]; // This array will hold the shortest distance from the source vertex to each other vertex.
        Arrays.fill(distArr, Integer.MAX_VALUE);
        distArr[src] = 0;

        // min heap : The queue is ordered by distance, so the node with the smallest distance is always at the front.
        // This is the key to the algorithm.
        PriorityQueue<Pair> pQueue = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pQueue.add(new Pair(src, 0));

        // simple BFS traversal
        while (!pQueue.isEmpty()) {

            int currNode = pQueue.peek().node;
            int currDist = pQueue.peek().dist;

            pQueue.remove();

            for (List<Integer> adjNodes : adjList.get(currNode)) {

                int adjNode = adjNodes.get(0);
                int adjNodeDist = adjNodes.get(1);

                // If the (distance till now + the distance to the adjacent node) is less than the distance to the adjacent node,
                // then we have found a shorter path to the adjacent node.
                // We update the distance to the adjacent node and add it to the queue.
                if (currDist + adjNodeDist < distArr[adjNode]) {

                    distArr[adjNode] = currDist + adjNodeDist;
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

    private static void addEdge(List<List<List<Integer>>> adjList, int src, int dst, int dist) {
        adjList.get(src).add(new ArrayList<>(Arrays.asList(dst, dist)));
        adjList.get(dst).add(new ArrayList<>(Arrays.asList(src, dist)));
    }
}
