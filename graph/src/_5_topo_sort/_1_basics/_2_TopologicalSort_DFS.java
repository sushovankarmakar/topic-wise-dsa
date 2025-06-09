package src._5_topo_sort._1_basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 * <p>
 * https://www.youtube.com/watch?v=5lZ0iJMrUMk&ab_channel=takeUforward (Striver)
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_TopologicalSort.java
 */
public class _2_TopologicalSort_DFS {

    public static void main(String[] args) {

        _2_TopologicalSort_DFS obj = new _2_TopologicalSort_DFS();

        int[][] edges = {{3, 0}, {1, 0}, {2, 0}};

        List<Integer> topoSort = obj.topoSort(4, edges);
        for (int i : topoSort) {
            System.out.println(i + " ");
        }
    }

    public ArrayList<Integer> topoSort(int v, int[][] edges) {

        List<List<Integer>> adjList = getAdjList(v, edges);

        Stack<Integer> stack = new Stack<>();
        boolean[] isVisited = new boolean[v];

        for (int i = 0; i < v; i++) {

            if (!isVisited[i]) {
                dfs(i, adjList, isVisited, stack);
            }
        }

        ArrayList<Integer> topoSortedNodes = new ArrayList<>();

        while (!stack.isEmpty()) {
            topoSortedNodes.add(stack.pop());
        }

        return topoSortedNodes;
    }

    private void dfs(int currNode, List<List<Integer>> adjList, boolean[] isVisited, Stack<Integer> stack) {

        isVisited[currNode] = true;

        for (int adjNode : adjList.get(currNode)) {

            if (!isVisited[adjNode]) {

                dfs(adjNode, adjList, isVisited, stack);
            }
        }

        stack.push(currNode);
    }

    private List<List<Integer>> getAdjList(int v, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {

            int src = edges[i][0];
            int dst = edges[i][1];

            adjList.get(src).add(dst);
        }

        return adjList;
    }
}
