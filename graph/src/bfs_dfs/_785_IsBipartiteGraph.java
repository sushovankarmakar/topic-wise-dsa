package src.bfs_dfs;

import java.util.ArrayList;
import java.util.Arrays;

public class _785_IsBipartiteGraph {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>(Arrays.asList()));
        adjList.add(new ArrayList<>(Arrays.asList(2)));
        adjList.add(new ArrayList<>(Arrays.asList(1, 3)));
        adjList.add(new ArrayList<>(Arrays.asList(2, 4)));
        adjList.add(new ArrayList<>(Arrays.asList(3, 5, 7)));
        adjList.add(new ArrayList<>(Arrays.asList(4, 6)));
        adjList.add(new ArrayList<>(Arrays.asList(2, 5)));
        adjList.add(new ArrayList<>(Arrays.asList(4, 8)));
        adjList.add(new ArrayList<>(Arrays.asList(7)));

        System.out.println(isBipartite(8, adjList)); // false
    }

    // If a graph has an odd length cycle, it can never be bipartite → this is the only condition
    /**
     * CONCEPT:
     * -------
     * A graph is bipartite if we can split the graph's nodes into two sets A and B such that
     *
     * 1. Take a color array filled with -1.
     * 2. This color array will work as visited array also. where -1 is not visited and 0/1 is visited.
     * 3. Now call a DFS and pass this color array.
     * 4. Inside DFS call, traverse current node's all the neighbour nodes check
     *     1. if this node is not visited, call DFS again with alternate color.
     *     2. if this node is already visited,
     *         1. then check that **neighbour node's color and current color is same or not.**
     *         2. if same this graph is NOT bipartite, return false.
     */
    private static boolean isBipartite(int numOfNodes, ArrayList<ArrayList<Integer>> adjList) {

        int[] colorArr = new int[numOfNodes];   // this color array will work as visited array also.
        Arrays.fill(colorArr, -1);          // -1 = not visited, 0/1 = visited and colored.

        for (int i = 0; i < numOfNodes; i++) { // this graph can have multiple components, we need cover everyone.

            if (colorArr[i] == -1) {        // MADE A SILLY MISTAKE HERE. colorArr[i] != -1
                if (!dfs(i, 0, colorArr, adjList)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean dfs(int currNode, int currColor, int[] colorArr, ArrayList<ArrayList<Integer>> adjList) {

        colorArr[currNode] = currColor;

        for (int adjNode : adjList.get(currNode)) {

            if (colorArr[adjNode] == -1) {

                // currColor = (currColor == 0) ? 1 : 0;

                if (!dfs(adjNode, 1 - currColor, colorArr, adjList)) {
                    return false; // if adj node's DFS calls return false, then we should return false also
                }
            } else if (colorArr[adjNode] == currColor) {
                // if two adj nodes have same color, then this graph is NOT bipartite, so return false
                return false;
            }
        }

        return true;
    }
}
