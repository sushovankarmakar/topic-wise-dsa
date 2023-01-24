package src;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private static List<List<Integer>> getAdjacencyList(int noOfNodes, String[] edges, boolean isUndirected) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < noOfNodes; i++) {
            adjList.add(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i += 2) {
            int sourceNode = Integer.parseInt(edges[i]);
            int destinationNode = Integer.parseInt(edges[i + 1]);
            adjList.get(sourceNode).add(destinationNode);
            if (isUndirected) {
                adjList.get(destinationNode).add(sourceNode);
            }
        }
        return adjList;
    }

    private static void print(List<Integer> result) {
        StringBuilder sb = new StringBuilder();
        for (int node : result) {
            sb.append(node).append(" ");
        }
        System.out.println(sb);
    }
}
