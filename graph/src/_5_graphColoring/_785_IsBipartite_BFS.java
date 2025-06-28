package src._5_graphColoring;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/is-graph-bipartite/description/
 * https://leetcode.com/discuss/post/655708/graph-for-beginners-problems-pattern-sam-06fb/
 */
public class _785_IsBipartite_BFS {

    public boolean isBipartite(int[][] graph) {

        // here, given graph[][] is itself an adjacency list, so no need to calculate an adjacency list separately

        int n = graph.length;

        int[] colorArr = new int[n];
        Arrays.fill(colorArr, -1);

        for (int i = 0; i < n; i++) {

            if (colorArr[i] == -1 && !bfs(i, graph, colorArr)) {
                return false; // bipartite not possible
            }
        }

        return true; // bipartite possible
    }

    private boolean bfs(int src, int[][] graph, int[] colorArr) {

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(src, 0));
        colorArr[src] = 0;

        while (!queue.isEmpty()) {

            Pair currPair = queue.poll();
            int currNode = currPair.node;
            int currColor = currPair.color;

            for (int adjNode : graph[currNode]) {

                if (colorArr[adjNode] == -1) { // still un-visited

                    int newColor = currColor == 0 ? 1 : 0; // putting the opposite color

                    colorArr[adjNode] = newColor;
                    queue.add(new Pair(adjNode, newColor));

                } else if (colorArr[adjNode] == currColor) {
                    // two neighbors have the same color
                    return false; // bipartite NOT possible.
                }
            }
        }

        return true; // bipartite possible.
    }

    private static class Pair {

        int node;
        int color;

        Pair(int node, int color) {
            this.node = node;
            this.color = color;
        }
    }
}
