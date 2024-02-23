package src.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/problems/print-adjacency-list-1587115620/1
 * https://www.youtube.com/watch?v=OsNklbh9gYI&t=637s&ab_channel=takeUforward
 */
public class PrintAdjList {

    public static void main(String[] args) {
        int v = 5;
        //int[][] edges = new int[][]{{0, 1}, {0, 4}, {4, 1}, {4, 3}, {1, 3}, {1, 2}, {3, 2}};
        int[][] edges = new int[][]{{2, 3}, {4, 1}, {4, 0}, {2, 1}};
        List<List<Integer>> adjList = createAdjList(v, edges);
        printGraph(adjList);
    }

    public static List<List<Integer>> createAdjList(int v, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {    // BIG MISTAKE I made. I didn't initialise. Initialise the list for each vertex.
            adjList.add(new ArrayList<>());
        }

        for (int[] edge : edges) {

            int src = edge[0];  // source node
            int dst = edge[1];  // destination node

            adjList.get(src).add(dst);
            adjList.get(dst).add(src);
        }

        return adjList;
    }

    public static void printGraph(List<List<Integer>> adjList) {

        for (int i = 0; i < adjList.size(); i++) {

            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    // WRONG CODE. Here I didn't initialise the list for each vertex and hence got null pointer exception.
    /*public List<List<Integer>> createAdjList(int V, int edges[][]) {

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {

            int src = edges[i][0];
            int dst = edges[i][1];

            System.out.println(src + " " + dst);

            if (adjList.get(src) == null) {
                adjList.add(src, new ArrayList<>());
            } else {
                adjList.get(src).add(dst);
            }
        }

        return null;
    }*/
}
