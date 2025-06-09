package src._4_detect_cycles;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-eventual-safe-states/description/
 * <p>
 * https://www.youtube.com/watch?v=uRbJ1OF9aYM&t=93s&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/find-eventual-safe-states-dfs-g-20/
 */
public class _802_FindEventualSafeStates_DFS {

    public static void main(String[] args) {

        int[][] graph = {
                {1, 2},
                {2, 3},
                {5},
                {0},
                {5},
                {},
                {}
        };

        List<Integer> result = eventualSafeNodes(graph);
        System.out.println(result); // [2, 4, 5, 6]
    }

    /**
     * - We’ve observed that, every node
     * - which is a part of a cycle OR
     * - which connected (incoming edges) to a cycle
     * will never be a safe node.
     * <p>
     * Other than above two conditions, everything will be eventual safe node.
     * <p>
     * - So this question boils down to → ‘Detect a cycle in a directed graph’
     */
    private static List<Integer> eventualSafeNodes(int[][] graph) {

        int numOfNodes = graph.length;
        List<List<Integer>> adjList = getAdjList(numOfNodes, graph);

        boolean[] isVisited = new boolean[numOfNodes];
        boolean[] isPathVisited = new boolean[numOfNodes];
        boolean[] isSafeNode = new boolean[numOfNodes];

        for (int i = 0; i < numOfNodes; i++) {

            if (!isVisited[i]) {
                isCyclePresent(i, adjList, isVisited, isPathVisited, isSafeNode);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < numOfNodes; i++) {
            if (isSafeNode[i]) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }

    /*
    * Nodes involved in a cycle or leading to a cycle are explicitly marked as unsafe (isSafeNode[currNode] = false).
    * Nodes not part of a cycle are marked as safe (isSafeNode[currNode] = true) after backtracking.
     */
    private static boolean isCyclePresent(int currNode, List<List<Integer>> adjList,
                                          boolean[] isVisited, boolean[] isPathVisited, boolean[] isSafeNode) {

        isVisited[currNode] = true;
        isPathVisited[currNode] = true;

        for (int adjNode : adjList.get(currNode)) {

            if (isVisited[adjNode] && isPathVisited[adjNode]) {
                // we've found a node which have been already visited and in the same path itself
                // Cycle detected, mark current node as unsafe
                isSafeNode[currNode] = false;
                return true;
            }

            if (!isVisited[adjNode]) {
                if (isCyclePresent(adjNode, adjList, isVisited, isPathVisited, isSafeNode)) {
                    // If a cycle is detected in the recursive call, mark current node as unsafe
                    isSafeNode[currNode] = false;
                    return true;
                }
            }
        }
        isPathVisited[currNode] = false; // this will help in backtracking
        isSafeNode[currNode] = true; // we've found on circles here, so it is a safe node.

        return false; // circle not found
    }

    private static List<List<Integer>> getAdjList(int numOfNodes, int[][] adjMatrix) {

        List<List<Integer>> adjListReversed = new ArrayList<>();
        for (int i = 0; i < numOfNodes; i++) {
            adjListReversed.add(new ArrayList<>());
        }

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {

                int src = i;
                int dst = adjMatrix[i][j];
                adjListReversed.get(src).add(dst);
            }
        }
        return adjListReversed;
    }
}
