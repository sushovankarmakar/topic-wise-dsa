package src.detect_cycles;

import java.util.List;

/**
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 *
 * https://www.youtube.com/watch?v=9twcmtQj4DU&ab_channel=takeUforward (Striver)
 */
public class DetectCycleInDirectedGraph_DFS {

    private static boolean isCyclic(int v, List<List<Integer>> adj) {

        /**
         * isVisited 		= to check if this current node has been visited or not.
         * isPathVisited 	= to check if this current node has been visited on same path or not.
         */
        boolean[] isVisited = new boolean[v];
        boolean[] isPathVisited = new boolean[v];

        for (int i = 0; i < v; i++) {

            if (!isVisited[i]) {
                if (dfs(i, isVisited, isPathVisited, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int currNode, boolean[] isVisited, boolean[] isPathVisited, List<List<Integer>> adj) {

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
         * IMPORTANT :
         * we've explored all the paths start from currNode.
         * so before exploring different path, we need to mark this node as false.
         *
         * although we may come back to this node from different path
         */
        isPathVisited[currNode] = false;

        return false;
    }


}
