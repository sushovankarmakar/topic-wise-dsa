package src.topo_sort;

import java.util.List;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 *
 * https://www.youtube.com/watch?v=5lZ0iJMrUMk&ab_channel=takeUforward (Striver)
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_TopologicalSort.java
 */
public class TopologicalSort_DFS {

    private static int[] getTopologicalSort(int v, List<List<Integer>> adj) {

        Stack<Integer> stack = new Stack<>();

        boolean[] isVisited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!isVisited[i]) {
                dfs(i, adj, isVisited, stack);
            }
        }

        // converting stack to array
        int[] result = new int[v];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.pop();
        }

        return result;
    }

    private static void dfs(int currNode, List<List<Integer>> adj, boolean[] isVisited, Stack<Integer> stack) {

        isVisited[currNode] = true;

        for (int adjNode : adj.get(currNode)) {
            if (!isVisited[adjNode]) {
                dfs(adjNode, adj, isVisited, stack);
            }
        }

        stack.push(currNode);
    }
}
