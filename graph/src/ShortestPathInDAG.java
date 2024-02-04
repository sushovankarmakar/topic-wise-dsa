package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/shortest-path-in-undirected-graph/1
 * https://takeuforward.org/data-structure/shortest-path-in-directed-acyclic-graph-topological-sort-g-27/
 *
 * https://www.youtube.com/watch?v=ZUFQfFaU-8U
 */
public class ShortestPathInDAG {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] edges = {{0, 1, 2}, {0, 4, 1}, {4, 5, 4}, {4, 2, 2}, {1, 2, 3}, {2, 3, 6}, {5, 3, 1}};
        //s.shortestPath(6, 7, edges);

        int[][] edges1 = {{0, 1, 2}, {0, 2, 1}};
        int[] values = s.shortestPath(4, 2, edges1);

        for (int val : values) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    static class Solution {
        class Pair {
            int adjNode;
            int distance;

            Pair(int adjNode, int distance) {
                this.adjNode = adjNode;
                this.distance = distance;
            }
        }

        public int[] shortestPath(int numOfNodes, int m, int[][] edges) { // N = 6, M = 7

            List<List<Pair>> adjList = getAdjList(numOfNodes, edges);

            /*System.out.println("ADJ LIST");
            for (int i = 0; i < adjList.size(); i++) {
                System.out.print("Node : " + i + " -> ");
                List<Pair> adjNodes = adjList.get(i);
                StringBuilder sb = new StringBuilder();
                for (Pair pair : adjNodes) {
                    sb.append("[")
                            .append(pair.adjNode)
                            .append(", ")
                            .append(pair.distance)
                            .append("], ");
                }
                System.out.println(sb);
            }*/

            boolean[] isVisited = new boolean[numOfNodes];
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < numOfNodes; i++) {
                if (!isVisited[i]) {
                    dfs(i, stack, isVisited, adjList);
                }
            }

            /*System.out.println("STACK");
            System.out.println(stack);*/

            int[] distanceFromStartingNode = new int[numOfNodes];
            Arrays.fill(distanceFromStartingNode, (int) 1e9);
            distanceFromStartingNode[0] = 0;   // because 0 is our starting node.

            while (!stack.isEmpty()) {
                int currNode = stack.pop();

                for (Pair pair : adjList.get(currNode)) {

                    if (pair == null) continue;

                    int newDistance = pair.distance + distanceFromStartingNode[currNode];
                    int oldDistance = distanceFromStartingNode[pair.adjNode];

                    if (newDistance < oldDistance) {
                        distanceFromStartingNode[pair.adjNode] = newDistance;
                    }
                }
            }

            for (int i = 0; i < distanceFromStartingNode.length; i++) {
                if (distanceFromStartingNode[i] == 1e9) {
                    distanceFromStartingNode[i] = -1;
                }
            }

            return distanceFromStartingNode;
        }

        /**
         * N = 6, M = 7
         * edge = [
         * [0,1,2],
         * [0,4,1],
         * [1,2,3],
         * [2,3,6],
         * [4,5,4],
         * [4,2,2],
         * [5,3,1]]
         * -------------------
         * adjList =
         * 0 -> {1, 2}, {4, 1}
         * 1 -> {2, 3}
         * 2 -> {3, 6}
         * 3 ->
         * 4 -> {2, 2}, {5, 4}
         * 5 -> {3, 1}
         */
        private void dfs(int node, Stack<Integer> stack, boolean[] isVisited, List<List<Pair>> adjList) { // topological sort

            isVisited[node] = true;

            for (Pair pair : adjList.get(node)) {

                int adjNode = pair.adjNode;
                if (!isVisited[adjNode]) {
                    isVisited[node] = true;
                    dfs(adjNode, stack, isVisited, adjList);
                }
            }

            stack.add(node);
        }

        // edge = [[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
        private List<List<Pair>> getAdjList(int numOfNodes, int[][] edges) {
            List<List<Pair>> adjList = new ArrayList<>(numOfNodes);

            for (int i = 0; i < numOfNodes; i++) {
                adjList.add(new ArrayList<>());
            }

            for (int i = 0; i < edges.length; i++) {

                int source = edges[i][0];
                int destination = edges[i][1];
                int distance = edges[i][2];

                adjList.get(source).add(new Pair(destination, distance));
            }

            return adjList;
        }
    }
}
