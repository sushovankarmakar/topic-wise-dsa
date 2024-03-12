package src.topo_sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 * <p>
 * https://www.youtube.com/watch?v=73sneFXuTEg&ab_channel=takeUforward (Striver)
 * https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/ (Kahn’s algorithm for Topological Sorting)
 */
public class TopologicalSort_BFS {

    /**
     * The idea is to use BFS to find the topological sort of the graph.
     * This is called Kahn's algorithm.
     */
    private static int[] getTopologicalSort(int v, List<List<Integer>> adj) {

        int[] inDegrees = getInDegrees(v, adj); // number of incoming edges to a node

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int[] result = new int[v];
        int i = 0;

        while (!queue.isEmpty()) {

            int currNode = queue.remove();
            result[i++] = currNode;

            for (int adjNode : adj.get(currNode)) {

                inDegrees[adjNode]--;

                if (inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        return result;
    }

    private static int[] getInDegrees(int v, List<List<Integer>> adj) {

        int[] inDegrees = new int[v];

        for (int i = 0; i < adj.size(); i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {

                int dst = adj.get(i).get(j);
                inDegrees[dst]++;
            }
        }
        return inDegrees;
    }
}
