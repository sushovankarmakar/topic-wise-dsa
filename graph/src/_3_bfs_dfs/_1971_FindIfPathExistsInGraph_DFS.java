package src._3_bfs_dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/find-if-path-exists-in-graph/">Find if Path Exists in Graph</a>
 * <a href="https://www.youtube.com/watch?v=ZwGC60Ao6bQ&t=638s&ab_channel=AdityaVerma">8 Find if path exists | Graph</a>
 */
public class _1971_FindIfPathExistsInGraph_DFS {

    public static void main(String[] args) {
        _1971_FindIfPathExistsInGraph_DFS obj = new _1971_FindIfPathExistsInGraph_DFS();

        int[][] edges = {{4, 3}, {1, 4}, {4, 8}, {1, 7}, {6, 4}, {4, 2}, {7, 4}, {4, 0}, {0, 9}, {5, 4}};

        boolean isPathExists = obj.validPath(10, edges, 5, 9);
        System.out.println(isPathExists);
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        if (n == 1 || (source == destination)) return true;

        List<List<Integer>> adjList = getAdjList(n, edges);

        boolean[] isVisited = new boolean[n];
        isVisited[source] = true;

        return doDFS(source, destination, adjList, isVisited);
    }

    private boolean doDFS(int currNode, int destination, List<List<Integer>> adjList, boolean[] isVisited) {

        isVisited[currNode] = true;

        for (int adjNode : adjList.get(currNode)) {

            if (adjNode == destination) {
                return true; // we've found a path between source to destination
            }

            if (!isVisited[adjNode]) {
                if (doDFS(adjNode, destination, adjList, isVisited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private List<List<Integer>> getAdjList(int n, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {

            int src = edges[i][0];
            int dst = edges[i][1];

            adjList.get(src).add(dst);
            adjList.get(dst).add(src);
        }

        return adjList;
    }
}
