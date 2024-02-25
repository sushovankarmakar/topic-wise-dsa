package src.traversal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
 * <p>
 * https://www.youtube.com/watch?v=Qzf1a--rhp8 (Striver)
 */
public class DFS {

    public static void main(String[] args) {

        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(Arrays.asList());
        adjList.add(Arrays.asList(2, 6));
        adjList.add(Arrays.asList(1, 3, 4));
        adjList.add(Arrays.asList(2));
        adjList.add(Arrays.asList(2, 5));
        adjList.add(Arrays.asList(4, 7));
        adjList.add(Arrays.asList(1, 7, 8));
        adjList.add(Arrays.asList(6, 5));
        adjList.add(Arrays.asList(6));

        /**
         * Graph :
         *          1
         *        /   \
         *       2     6
         *      / \   / \
         *     3   4 7  8
         *         \ /
         *          5
         */

        /**
         * Adjacency List  (1 based indexing)
         * --------------
         * 0 -> []
         * 1 -> [2, 6]
         * 2 -> [1, 3, 4]
         * 3 -> [2]
         * 4 -> [2, 5]
         * 5 -> [4, 7]
         * 6 -> [1, 7, 8]
         * 7 -> [6, 5]
         * 8 -> [6]
         */

        List<Integer> dfs = doDFS(8, adjList);
        System.out.println(dfs);

        /**
         * dfs :
         * ---
         * 1 2 3 4 5 7 6 8
         */
    }

    /**
     * space : O(3N)    N -> n nodes, N -> visited array, N -> recursion stack space (worst case)
     * time : O(N) + O(2E)
     */
    public static List<Integer> doDFS(int noOfNodes, List<List<Integer>> adj) {
        boolean[] isVisited = new boolean[noOfNodes + 1];
        List<Integer> dfs = new ArrayList<>();

        int currNode = 1;   // initialization can be start from 0 or 1
        isVisited[currNode] = true;

        dfsHelper(currNode, isVisited, adj, dfs);
        return dfs;
    }

    private static void dfsHelper(int currNode, boolean[] isVisited, List<List<Integer>> adj, List<Integer> dfs) {

        isVisited[currNode] = true;
        dfs.add(currNode);

        for (int neighbourNode : adj.get(currNode)) {

            if (!isVisited[neighbourNode]) {
                isVisited[currNode] = true;
                dfsHelper(neighbourNode, isVisited, adj, dfs);
            }
        }
    }
}
