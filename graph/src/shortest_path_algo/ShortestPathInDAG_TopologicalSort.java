package src.shortest_path_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1
 * <p>
 * https://www.youtube.com/watch?v=ZUFQfFaU-8U&ab_channel=takeUforward (Striver)
 */
public class ShortestPathInDAG_TopologicalSort {

    public static void main(String[] args) {

            int N = 6;
            int M = 9;
            int[][] edges = {
                    {0, 1, 2},
                    {1, 2, 3},
                    {1, 3, 6},
                    {2, 3, 7},
                    {2, 4, 5},
                    {3, 4, 1},
                    {3, 5, 11},
                    {4, 5, 8},
                    {5, 0, 9}
            };

            int[] distArr = shortestPath(N, M, edges);
            System.out.println(Arrays.toString(distArr));
    }

    private static int[] shortestPath(int N, int M, int[][] edges) {

        List<List<List<Integer>>> adjList = getAdjList(N, edges);

        Stack<Integer> stack = new Stack<>();
        boolean[] isVisited = new boolean[N];
        int src = 0;
        for (int i = src; i < N; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                dfs(i, isVisited, adjList, stack);
            }
        }

        int[] distArr = new int[N];
        Arrays.fill(distArr, 999999); // 999999 is used to represent infinity
        distArr[src] = 0;

        while (!stack.isEmpty()) {
            int currNode = stack.pop();
            int currWeight = distArr[currNode];

            for (List<Integer> adjNodeWithWeight : adjList.get(currNode)) {

                int adjNode = adjNodeWithWeight.get(0);
                int adjWeight = adjNodeWithWeight.get(1);

                if (currWeight + adjWeight < distArr[adjNode]) {
                    distArr[adjNode] = currWeight + adjWeight;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (distArr[i] == 999999) { // 999999 is used to represent infinity
                distArr[i] = -1;
            }
        }

        return distArr;
    }

    private static void dfs(int currNode, boolean[] isVisited, List<List<List<Integer>>> adjList, Stack<Integer> stack) {

        isVisited[currNode] = true;

        for (List<Integer> adjNodeWithWeight : adjList.get(currNode)) {

            int adjNode = adjNodeWithWeight.get(0);

            if (!isVisited[adjNode]) {
                dfs(adjNode, isVisited, adjList, stack);
            }
        }
        stack.push(currNode);
    }

    private static List<List<List<Integer>>> getAdjList(int N, int[][] edges) {

        List<List<List<Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {

            int src = edges[i][0];
            int dst = edges[i][1];
            int wt = edges[i][2];

            adjList.get(src).add(Arrays.asList(dst, wt));
        }

        return adjList;
    }
}
