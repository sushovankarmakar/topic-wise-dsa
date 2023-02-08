package src.detect_cycles;

import java.util.ArrayList;

/**
 * https://www.youtube.com/watch?v=zQ3zgFypzX4
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 */
public class DetectCycleInUnDirectedGraph_DFS {

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int v, ArrayList<ArrayList<Integer>> adj) {

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

    private boolean dfs(int currNode, int parentNode, boolean[] isVisited,
                        ArrayList<ArrayList<Integer>> adj) {

        isVisited[currNode] = true;

        for (int adjNode : adj.get(currNode)) {

            if (parentNode != adjNode) {

                // if adjNode is not parentNode and still it is already visited, then cycle hain.
                if (isVisited[adjNode]) {
                    return true;
                } else {
                    if (dfs(adjNode, currNode, isVisited, adj)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
