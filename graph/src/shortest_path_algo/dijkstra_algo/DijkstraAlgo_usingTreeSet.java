package src.shortest_path_algo.dijkstra_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class DijkstraAlgo_usingTreeSet {

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

        // TreeSet is used as a priority queue to store the nodes with their distances.
        // We are using a TreeSet instead of a PriorityQueue because we need to update the distance of a node in the queue.
        // TreeSet allows us to update the distance of a node in O(logn) time.
        // We are using a custom comparator to compare the nodes based on their distances.
        // If the distance of two nodes is equal, then we compare them based on their node number.
        // This is done to ensure that the TreeSet does not contain duplicate nodes.
        TreeSet<Pair> set = new TreeSet<>((a, b) -> {
            if (a.dist != b.dist) {
                // if the distance of both elements is not equal
                // than the element with shorter distance will appear first
                return a.dist - b.dist;
            }
            // If the source of both elements is not equal then
            // the element with short source number appear first
            // but if both are equal than treeSet will return 0 and treat both arrays as equal
            return a.node - b.node;
        });

        set.add(new Pair(src, 0));

        // simple BFS traversal
        while (!set.isEmpty()) {

            Pair pair = set.pollFirst();

            if (pair == null) continue;

            int currNode = pair.node; // pollFirst() is equivalent to remove() in PriorityQueue
            int currDist = pair.dist;

            for (List<Integer> adjNodes : adjList.get(currNode)) {

                int adjNode = adjNodes.get(0);
                int adjNodeDist = adjNodes.get(1);

                // If the (distance till now + the distance to the adjacent node) is less than the distance to the adjacent node,
                // then we have found a shorter path to the adjacent node.
                // We update the distance to the adjacent node and add it to the queue.
                if (currDist + adjNodeDist < distArr[adjNode]) {

                    distArr[adjNode] = currDist + adjNodeDist;
                    set.add(new Pair(adjNode, distArr[adjNode])); // made a mistake here, should NOT be adjNodeDist instead of distArr[adjNode]
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
