package src._3_bfs_dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/find-if-path-exists-in-graph/">Find if Path Exists in Graph</a>
 * <a href="https://www.youtube.com/watch?v=ZwGC60Ao6bQ&t=638s&ab_channel=AdityaVerma">8 Find if path exists | Graph</a>
 */
public class _1971_FindIfPathExistsInGraph_BFS {

    public static void main(String[] args) {
        _1971_FindIfPathExistsInGraph_BFS obj = new _1971_FindIfPathExistsInGraph_BFS();

        int[][] edges = {{4, 3}, {1, 4}, {4, 8}, {1, 7}, {6, 4}, {4, 2}, {7, 4}, {4, 0}, {0, 9}, {5, 4}};

        boolean isPathExists = obj.validPath(10, edges, 5, 9);
        System.out.println(isPathExists);
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        if (n == 1 || (source == destination)) return true;

        List<List<Integer>> adjList = getAdjList(n, edges);

        return doBFS(source, destination, n, adjList);
    }

    private boolean doBFS(int source, int destination, int n, List<List<Integer>> adjList) {

        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[n];

        queue.offer(source);
        isVisited[source] = true;

        while (!queue.isEmpty()) {

            int currNode = queue.poll();
            isVisited[currNode] = true;

            for (int adjNode : adjList.get(currNode)) {

                if (adjNode == destination) {
                    return true; // we've found a path between source to destination
                }

                if (!isVisited[adjNode]) {

                    isVisited[adjNode] = true;
                    queue.offer(adjNode);
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
