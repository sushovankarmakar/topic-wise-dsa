package src.detect_cycles;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=BPlrALf1LDU
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 */
public class DetectCycleInUnDirectedGraph_BFS {

    static class Pair {
        int node;
        int parentNode;

        Pair(int node, int parentNode) {
            this.node = node;
            this.parentNode = parentNode;
        }
    }

    // Function to detect cycle in an undirected graph.
    public boolean isCycle(int v, ArrayList<ArrayList<Integer>> adj) {

        boolean[] isVisited = new boolean[v];

        for (int i = 0; i < v; i++) {

            if (!isVisited[i]) {

                if (bfs(i, isVisited, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(int currNode, boolean[] isVisited, ArrayList<ArrayList<Integer>> adj) {

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(currNode, -1));

        while (!queue.isEmpty()) {

            Pair currPair = queue.poll();
            isVisited[currPair.node] = true;

            for (int adjNode : adj.get(currPair.node)) {

                if (currPair.parentNode != adjNode) {

                    // if adjNode is not parentNode and still it is already visited, then cycle hain.
                    if (isVisited[adjNode]) {
                        return true;
                    } else {
                        queue.add(new Pair(adjNode, currPair.node));
                    }
                }
            }
        }
        return false;
    }
}
