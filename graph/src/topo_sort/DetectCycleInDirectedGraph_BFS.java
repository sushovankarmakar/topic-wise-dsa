package src.topo_sort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
 * <p>
 * https://www.youtube.com/watch?v=iTBaI90lpDQ&ab_channel=takeUforward (Striver)
 */
public class DetectCycleInDirectedGraph_BFS {

    private static boolean isCyclic(int v, List<List<Integer>> adj) {

        int[] inDegrees = getInDegrees(v, adj);

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> topologicalSort = new ArrayList<>();

        while (!queue.isEmpty()) {

            int currNode = queue.remove();
            topologicalSort.add(currNode);

            for (int adjNode : adj.get(currNode)) {

                inDegrees[adjNode]--;

                if (inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        return !(topologicalSort.size() == v); // if equal then cycle not present, so return false
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
