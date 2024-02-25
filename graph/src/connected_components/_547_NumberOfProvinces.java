package src.connected_components;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-provinces/description/
 * https://www.youtube.com/watch?v=ACzkVtewUYA&t=553s&ab_channel=takeUforward (Striver)
 */
public class _547_NumberOfProvinces {

    /**
     * CONCEPT :
     * --------
     *
     * 1. We've given a graph with multiple sub graphs. Need to find out the total number of sub-graphs is there.
     *
     * 2. Convert the given adjacency matrix to adjacency list
     * 3. Take a boolean visited array
     * 4. Traverse through the visited array and call either BFS or DFS.
     * 5. The number of times the BFS or DFS function will happen, that is the number of provinces.
     */
    public static void main(String[] args) {
        int[][] adjMatrix = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        /**
         * 1 -- 2
         *   3
         */
        System.out.println(getNumberOfProvinces(adjMatrix));    // 2
    }

    /**
     * Time complexity :  O(N) + O(V + 2E),
     *                      Where O(N) is for outer loop and inner loop runs in total a single DFS over entire graph,
     *                      and we know DFS takes a time of O(V+2E).
     *
     * Space complexity : O(2N), N → visited array, N → recursion stack space
     */
    private static int getNumberOfProvinces(int[][] isConnected) {

        int numberOfNodes = isConnected.length;

        List<List<Integer>> adjList = getAdjList(isConnected); // converting adj matrix to adj list.
        boolean[] isVisited = new boolean[numberOfNodes];

        int numOfProvinces = 0;

        /*for (int i = 0; i < isVisited.length; i++) {

            if (!isVisited[i]) {
                numOfProvinces++;
                dfs(i, isVisited, adjList);
            }
        }*/

        for (int i = 0; i < isVisited.length; i++) {

            if (!isVisited[i]) {
                numOfProvinces++;
                bfs(i, isVisited, adjList);
            }
        }

        return numOfProvinces;
    }

    private static void bfs(int currNode, boolean[] isVisited, List<List<Integer>> adjList) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(currNode);
        isVisited[currNode] = true;

        while (!queue.isEmpty()) {

            currNode = queue.poll();

            for (int neighbour : adjList.get(currNode)) {

                if (!isVisited[neighbour]) {
                    isVisited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    private static void dfs(int currNode, boolean[] isVisited, List<List<Integer>> adjList) {

        isVisited[currNode] = true;

        for (int neighbour : adjList.get(currNode)) {

            if (!isVisited[neighbour]) {

                isVisited[neighbour] = true;
                dfs(neighbour, isVisited, adjList);
            }
        }
    }

    /**
     * converting adjacency matrix to adjacency list
     */
    private static List<List<Integer>> getAdjList(int[][] isConnected) {

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < isConnected.length; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {

                if (isConnected[i][j] == 1 && i != j) {

                    adjList.get(i).add(j);
                    adjList.get(j).add(i);
                }
            }
        }

        return adjList;
    }
}
