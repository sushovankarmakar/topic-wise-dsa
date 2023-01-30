package src.connected_components;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=ACzkVtewUYA (Striver)
 *
 * https://leetcode.com/problems/number-of-provinces/
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * https://practice.geeksforgeeks.org/problems/number-of-provinces/1
 */
public class _323_547_NumOfConnectedComponents {

    public int countComponents(int n, int[][] edges) {

        List<List<Integer>> adjList = getAdjList(n, edges);

        boolean[] isVisited = new boolean[n];

        int numOfConnectedComponents = 0;

        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                numOfConnectedComponents++;
                dfs(i, adjList, isVisited);
            }
        }

        return numOfConnectedComponents;
    }

    private void dfs(int currNode, List<List<Integer>> adjList, boolean[] isVisited) {

        isVisited[currNode] = true;

        for (int adjNode : adjList.get(currNode)) {
            if (!isVisited[adjNode]) {
                dfs(adjNode, adjList, isVisited);
            }
        }
    }

    private List<List<Integer>> getAdjList(int n, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {

            int src = edge[0];
            int dst = edge[1];

            adjList.get(src).add(dst);
            adjList.get(dst).add(src);
        }

        return adjList;
    }
}
