package src.detect_cycles;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=9tM7HkTONrw&ab_channel=AryanMittal
 */
public class _2360_LongestCycleInDirectedGraph {

    public static void main(String[] args) {
        _2360_LongestCycleInDirectedGraph obj = new _2360_LongestCycleInDirectedGraph();

        int[] edges = {3, 3, 4, 2, 3};

        int longestCycleLength = obj.longestCycle(edges);
        System.out.println("Length of longest cycle " + longestCycleLength);
    }

    public int longestCycleLength = -1;

    public int longestCycle(int[] edges) {

        int v = edges.length;

        boolean[] isVisited = new boolean[v];
        int[] isPathVisited = new int[v];
        Arrays.fill(isPathVisited, -1);

        for (int i = 0; i < edges.length; i++) {

            if (!isVisited[i]) {
                dfs(i, edges, isVisited, isPathVisited, 0);
            }
        }

        return longestCycleLength;
    }

    public void dfs(int currNode, int[] edges, boolean[] isVisited, int[] isPathVisited, int currDistance) {

        isVisited[currNode] = true;
        isPathVisited[currNode] = currDistance;

        int adjNode = edges[currNode]; // there can be only one adjNode, given in the question.

        if (adjNode != -1) { // if adjNode = -1, there is no outgoing edge from here, given in the question.

            if (isVisited[adjNode] && isPathVisited[adjNode] != -1) {
                // cycle found

                int dist = (currDistance - isPathVisited[adjNode]) + 1;
                longestCycleLength = Math.max(longestCycleLength, dist);
            }

            if (!isVisited[adjNode]) {
                dfs(adjNode, edges, isVisited, isPathVisited, currDistance + 1);
            }
        }

        isPathVisited[currNode] = -1; // will be used for backtracking
    }

    // [3, 3, 4, 2, 3] -> 3
    // [2, -1, 3, 1] -> -1 (no cycle)
    // [-1, 4, -1, 2, 0, 4] -> -1 (no cycle)
}
