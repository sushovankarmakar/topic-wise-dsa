package src._5_topo_sort._1_basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/problems/topological-sort/1
 * <p>
 * https://www.youtube.com/watch?v=73sneFXuTEg&ab_channel=takeUforward (Striver)
 * https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/ (Kahn’s algorithm for Topological Sorting)
 */
public class _1_TopologicalSort_BFS {

    public static void main(String[] args) {

        _1_TopologicalSort_BFS obj = new _1_TopologicalSort_BFS();

        int[][] edges = {{3, 0}, {1, 0}, {2, 0}};

        List<Integer> topoSort = obj.topoSort(4, edges);
        for (int i : topoSort) {
            System.out.println(i + " ");
        }
    }

    /*
        The idea is to use BFS to find the topological sort of the graph.
        This is called Kahn's algorithm.
     */
    public List<Integer> topoSort(int v, int[][] edges) {

        int[] inDegrees = new int[v];
        List<List<Integer>> adjList = getAdjList(v, edges, inDegrees);

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < inDegrees.length; ++i) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> topoSortedNodes = new ArrayList<>();

        while (!queue.isEmpty()) {

            int currNode = queue.poll();
            topoSortedNodes.add(currNode);

            for (int adjNode : adjList.get(currNode)) {

                if (--inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        return topoSortedNodes;
    }

    /*
        This method
        1. will create adjacency list and
        2. will populate inDegrees array
     */
    private List<List<Integer>> getAdjList(int v, int[][] edges, int[] inDegrees) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; ++i) {

            int src = edges[i][0];
            int dst = edges[i][1];

            adjList.get(src).add(dst);

            ++inDegrees[dst];
        }

        return adjList;
    }
}
