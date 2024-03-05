package src.detect_cycles;

import java.util.List;

/**
 * https://www.youtube.com/watch?v=zQ3zgFypzX4
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 */
public class DetectCycleInUnDirectedGraph_DFS {

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int v, List<List<Integer>> adj) {

        boolean[] isVisited = new boolean[v];

        for (int i = 0; i < v; i++) {

            if (!isVisited[i]) {

                if (dfs(i, -1, isVisited, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int currNode, int parentNode, boolean[] isVisited, List<List<Integer>> adj) {

        isVisited[currNode] = true;

        for (int adjNode : adj.get(currNode)) {

            // if adjNode is not parentNode and still it is already visited, then cycle hain.

            if (isVisited[adjNode] && adjNode != parentNode) {
                // if any adj node is already visited but this node is not parent node
                // then this adj node has been touched by other nodes
                // that means we can reach this adj node from two different directions
                // so there is a cycle.
                return true;    // cycle is present, so return true;
            }

            if (!isVisited[adjNode]) {
                isVisited[adjNode] = true;

                if (dfs(adjNode, currNode, isVisited, adj)) {
                    return true;
                }
            }
        }
        return false;
    }
}
