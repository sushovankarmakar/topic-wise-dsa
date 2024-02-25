package src.traversal;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=-tgVpUgsQ5k (Striver)
 * https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
 *
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Graph_BFSTraversalOfGraph.java
 */
public class BFS {

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

        List<Integer> bfs = doBFS(8, adjList);
        System.out.println(bfs);

        /**
         * bfs :
         * ---
         * 1
         * 2 6
         * 3 4 7 8
         * 5
         */
    }

    /**
     * Concept
     *
     * - initialization 3 things :
     *     - a boolean visited array,
     *     - a queue and
     *     - a array-list to store the bfs
     * - start from a node,
     *     - mark it visited and
     *     - add it to bfs list
     * - now explore it neighbours adjacency list and
     *     - add them into queue and
     *     - mark them visited.
     */

    /**
     * space : O(3N)        -> queue + visited array + output array.
     * time : O(N) + O(2E)
     */
    private static List<Integer> doBFS(int numOfNodes, List<List<Integer>> adjList) {
        boolean[] isVisited = new boolean[numOfNodes + 1];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> bfs = new ArrayList<>();

        int currNode = 1;   // starting node can be 0 or 1
        isVisited[currNode] = true;
        queue.add(currNode);

        while (!queue.isEmpty()) {      // time : O(N)
            currNode = queue.poll();
            bfs.add(currNode);

            for (int neighbourNode : adjList.get(currNode)) {   // space : O(2E). total degree = 2 * edges.
                if (!isVisited[neighbourNode]) {
                    isVisited[neighbourNode] = true;
                    queue.add(neighbourNode);
                }
            }
        }

        return bfs;
    }
}
