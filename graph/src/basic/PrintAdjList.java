package src.basic;

import java.util.ArrayList;
import java.util.List;

public class PrintAdjList {
    public static void main(String[] args) {
        int v = 5;
        int[][] edges = new int[][]{{0, 1}, {0, 4}, {4, 1}, {4, 3}, {1, 3}, {1, 2}, {3, 2}};
        List<List<Integer>> adjList = printGraph(v, edges);
        System.out.println(adjList);
    }

    public static List<List<Integer>> printGraph(int v, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {    // BIG MISTAKE I made. I didn't initialise. Initialise the list for each vertex.
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

    // WRONG CODE. Here I didn't initialise the list for each vertex and hence got null pointer exception.
    /*public List<List<Integer>> printGraph(int V, int edges[][]) {

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
