package src.detect_cycles;

import java.util.ArrayList;
import java.util.Collections;
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

        List<Integer> safeNodes = new ArrayList<>();

        for (int i = 0; i < numOfNodes; i++) {

            if (!isVisited[i]) {
                isCyclePresent(i, isVisited, isPathVisited, adjList, safeNodes);
            }
        }
        Collections.sort(safeNodes);
        return safeNodes;
    }

    private static boolean isCyclePresent(int currNode, boolean[] isVisited, boolean[] isPathVisited,
                                          List<List<Integer>> adjList, List<Integer> safeNodes) {

        isVisited[currNode] = true;
        isPathVisited[currNode] = true;

        for (int adjNode : adjList.get(currNode)) {

            if (isVisited[adjNode] && isPathVisited[adjNode]) {
                return true;
            }

            if (!isVisited[adjNode]) {
                if (isCyclePresent(adjNode, isVisited, isPathVisited, adjList, safeNodes)) {
                    return true;
                }
            }
        }
        isPathVisited[currNode] = false;
        safeNodes.add(currNode);
        return false;
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
