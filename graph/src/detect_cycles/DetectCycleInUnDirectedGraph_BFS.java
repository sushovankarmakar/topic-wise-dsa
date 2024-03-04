package src.detect_cycles;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://practice.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
 *
 * https://www.youtube.com/watch?v=BPlrALf1LDU&ab_channel=takeUforward
 */
public class DetectCycleInUnDirectedGraph_BFS {

    public boolean isCycle(int v, List<List<Integer>> adj) {

        boolean[] isVisited = new boolean[v];

        // MISTAKE I MADE HERE : a graph can have multiple components, so we need to cover all the nodes. I didn't check this condition.
        for (int i = 0; i < v; i++) {
            if (!isVisited[i]) {
                if (bfs(i, isVisited, adj)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(int startNode, boolean[] isVisited, List<List<Integer>> adj) {

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startNode, -1));

        while (!queue.isEmpty()) {

            int currNode = queue.peek().node;
            int parentNode = queue.peek().parentNode;

            isVisited[currNode] = true;
            queue.remove();

            for (int adjNode : adj.get(currNode)) {

                // if adjNode is not parentNode and still it is already visited, then cycle hain.

                if (isVisited[adjNode] && adjNode != parentNode) {
                    // if any adj node is already visited but this node is not parent node
                    // then this adj node has been touched by other nodes
                    // that means we can reach this adj node from two different directions
                    // so there is a cycle.
                    return true;
                }

                if (!isVisited[adjNode]) {

                    isVisited[adjNode] = true;
                    queue.add(new Pair(adjNode, currNode));
                }
            }
        }
        return false;
    }

    private static class Pair {
        int node;
        int parentNode;

        Pair(int node, int parentNode) {
            this.node = node;
            this.parentNode = parentNode;
        }
    }
}
