package src._4_detect_cycles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _261_GraphValidTree_DetectCycleInUnDirectedGraph {

    public boolean validTree(int n, int[][] edges) {

        List<List<Integer>> adjList = getAdjList(n, edges);
        Set<Integer> isVisited = new HashSet<>();
        int startingNode = 0;
        isVisited.add(startingNode);

        // cycle presence check
        if (isCyclePresent(startingNode, -1, adjList, isVisited)) {
            return false;
        }

        // if cycle not present,
        // then all the nodes should be reachable from starting node
        return isVisited.size() == n;
    }

    private boolean isCyclePresent(int currNode, int parentNode,
                                   List<List<Integer>> adjList, Set<Integer> isVisited) {

        isVisited.add(currNode);

        for (int neighbour : adjList.get(currNode)) {

            // cycle present if the neighbour is already visited
            // but it is not the parent node
            if (isVisited.contains(neighbour) && parentNode != neighbour) {
                return true;
            }

            if (!isVisited.contains(neighbour)) {
                isVisited.add(neighbour);

                // passing current node as parent node in further calls
                if (isCyclePresent(neighbour, currNode, adjList, isVisited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<List<Integer>> getAdjList(int n, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int row = edge[0];
            int col = edge[1];

            adjList.get(row).add(col);
            adjList.get(col).add(row);
        }

        return adjList;
    }
}
