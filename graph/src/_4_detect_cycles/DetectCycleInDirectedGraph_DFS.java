package src._4_detect_cycles;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 *
 * https://www.youtube.com/watch?v=9twcmtQj4DU&ab_channel=takeUforward (Striver)
 */
public class DetectCycleInDirectedGraph_DFS {

    private boolean isCyclic(int v, int[][] edges) {

        List<List<Integer>> adjList = getAdjList(v, edges);

        /**
         * isVisited 		= to check if this current node has been visited or not.
         * isPathVisited 	= to check if this current node has been visited on same path or not.
         */
        boolean[] isVisited = new boolean[v];
        boolean[] isPathVisited = new boolean[v];

        for (int i = 0; i < v; i++) {

            if (!isVisited[i]) {
                if (dfs(i, isVisited, isPathVisited, adjList)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int currNode, boolean[] isVisited, boolean[] isPathVisited, List<List<Integer>> adj) {

        isVisited[currNode] = true;
        isPathVisited[currNode] = true;

        for (int adjNode : adj.get(currNode)) {

            if (isVisited[adjNode] && isPathVisited[adjNode]) {
                return true;
            }

            if (!isVisited[adjNode]) {
                if (dfs(adjNode, isVisited, isPathVisited, adj)) {
                    return true;
                }
            }
        }

        /**
         * We're backtracking. here.
         * ------------------------
         * IMPORTANT :
         * we've explored all the paths start from currNode.
         * so before exploring different path, we need to mark this node as false.
         *
         * although we may come back to this node from different path
         */
        isPathVisited[currNode] = false;

        return false;
    }

    private List<List<Integer>> getAdjList(int v, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {

            int src = edges[i][0];
            int dst = edges[i][1];

            adjList.get(src).add(dst); // this is a directed graph.
        }

        return adjList;
    }


}
