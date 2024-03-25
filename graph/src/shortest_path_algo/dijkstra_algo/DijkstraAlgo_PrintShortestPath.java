package src.shortest_path_algo.dijkstra_algo;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1 (GFG has some problem in test cases. Not able to submit the code)
 * https://www.youtube.com/watch?v=rp1SMw7HSO8&ab_channel=takeUforward (Striver)
 *
 * https://takeuforward.org/data-structure/g-35-print-shortest-path-dijkstras-algorithm/
 */
public class DijkstraAlgo_PrintShortestPath {

    public static void main(String[] args) {

        int n = 5;
        int m = 6;
        int[][] edges = {
                {1, 2, 2},
                {2, 5, 5},
                {2, 3, 4},
                {1, 4, 1},
                {4, 3, 3},
                {3, 5, 1}
        };

        // source node and target node is always 1 and n respectively.
        // we need to find the shortest path from source to destination.
        List<Integer> shortestPath = shortestPath_usingDijkstra(n, m, edges);
        System.out.println("Shortest path from source to destination: " + shortestPath); // output -> [1, 4, 3, 5]
    }

    private static List<Integer> shortestPath_usingDijkstra(int n, int m, int[][] edges) {

        List<List<List<Integer>>> adjList = getAdjList(n, m, edges);

        int src = 1; // source node
        int dst = n; // destination node

        // This array will hold the shortest distance from the source vertex to each other vertex, and it's parent node.
        ParentAndDistance[] parentAndDist = new ParentAndDistance[n + 1];

        for (int i = 1; i < parentAndDist.length; i++) {
            parentAndDist[i] = new ParentAndDistance(-1, Integer.MAX_VALUE);
        }
        parentAndDist[src] = new ParentAndDistance(-1, 0);

        // min heap : The queue is ordered by distance, so the node with the smallest distance is always at the front.
        // This is the key to the algorithm.
        PriorityQueue<Pair> pQueue = new PriorityQueue<>((a, b) -> {
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
                if (currDist + adjNodeDist < parentAndDist[adjNode].distance) {

                    int parentNode = currNode;  // update the distance and parent of the adjacent node
                    int shorterDistance = currDist + adjNodeDist;

                    parentAndDist[adjNode] = new ParentAndDistance(parentNode, shorterDistance);
                    pQueue.add(new Pair(adjNode, shorterDistance));
                }
            }
        }

        // EDGE CASE : if we can't reach the destination node from the source node, return -1.
        if (parentAndDist[dst].distance == Integer.MAX_VALUE) {
            return new ArrayList<>(Arrays.asList(-1));
        }

        List<Integer> srcToDst = new ArrayList<>();
        for (int i = dst; i != -1; i = parentAndDist[i].parentNode) {
            srcToDst.add(i);
        }
        Collections.reverse(srcToDst);
        return srcToDst;
    }

    private static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    private static class ParentAndDistance {
        int parentNode;
        int distance;

        ParentAndDistance(int parentNode, int distance) {
            this.parentNode = parentNode;
            this.distance = distance;
        }
    }

    private static List<List<List<Integer>>> getAdjList(int n, int m, int[][] edges) {

        List<List<List<Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int src = edges[i][0];
            int dst = edges[i][1];
            int dist = edges[i][2];

            // for undirected graph, edges are bidirectional.
            adjList.get(src).add(Arrays.asList(dst, dist));  // add edge from src to dst.
            adjList.get(dst).add(Arrays.asList(src, dist));  // add edge from dst to src.
        }

        return adjList;
    }

    private boolean isValid(int row, int col, int[][] grid) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[0].length && grid[row][col] == 1;
    }
}
