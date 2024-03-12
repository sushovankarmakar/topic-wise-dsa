package src.topo_sort;

import java.util.*;

/**
 * https://leetcode.com/problems/find-eventual-safe-states/description/
 * <p>
 * https://www.youtube.com/watch?v=2gtg3VsDGyc&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/find-eventual-safe-states-bfs-topological-sort-g-25/
 */
public class _802_FindEventualSafeStates_BFS {

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

    // In given graph, a node’s out-degree == In the reversed graph, a node’s in-degree

    /**
     * The concept behind the solution is as follows:
     * Reverse the graph:
     * -----------------
     * The original graph is given in the form of an adjacency matrix where each node points to its adjacent nodes.
     * The reversed graph is created such that each node points to its parent nodes.
     * For example, if the original graph has an edge from node 1 to node 2, the reversed graph will have an edge from node 2 to node 1.
     * The reversed graph is used to calculate the in-degrees of the nodes.
     * <p>
     * <p>
     * Calculate in-degrees:
     * --------------------
     * The in-degree of a node is the number of incoming edges to that node.
     * In the reversed graph, the in-degree of a node is the number of nodes it can reach.
     * The in-degrees are used to identify the terminal nodes (i.e., nodes with in-degree 0).
     * The in-degrees are also used to decrease the in-degrees of the adjacent nodes when a terminal node is removed from the queue.
     * The in-degrees are used to identify the safe nodes (i.e., nodes that can reach a terminal node).
     * <p>
     * BFS and topological sort:
     * -------------------------
     * Nodes with in-degree 0 (i.e., terminal nodes) are added to a queue.
     * Then, BFS is performed.
     * In each iteration, a node is removed from the queue, added to the result list, and
     * the in-degrees of its adjacent nodes are decreased by 1.
     * If an adjacent node's in-degree becomes 0, it is added to the queue.
     * This process continues until the queue is empty.
     * <p>
     * The result list is then sorted and returned. The sorted list contains all the safe nodes in the graph.
     * <p>
     * This approach ensures that all nodes that can reach a terminal node are included in the result.
     * The use of BFS ensures that all nodes are visited, and
     * the use of in-degrees ensures that only safe nodes are added to the result.
     */
    private static List<Integer> eventualSafeNodes(int[][] graph) {

        int numOfNodes = graph.length;

        List<List<Integer>> adjListReversed = getReversedAdjList(numOfNodes, graph);
        int[] inDegrees = getInDegree(numOfNodes, adjListReversed);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numOfNodes; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> list = new LinkedList<>();

        while (!queue.isEmpty()) {

            int currNode = queue.remove();
            list.add(currNode);

            for (int adjNode : adjListReversed.get(currNode)) {

                inDegrees[adjNode]--;

                if (inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        Collections.sort(list);
        return list;
    }

    private static List<List<Integer>> getReversedAdjList(int numOfNodes, int[][] adjMatrix) {

        List<List<Integer>> adjListReversed = new ArrayList<>();
        for (int i = 0; i < numOfNodes; i++) {
            adjListReversed.add(new ArrayList<>());
        }

        for (int i = 0; i < adjMatrix.length; i++) {
            for (int j = 0; j < adjMatrix[i].length; j++) {

                int src = i;
                int dst = adjMatrix[i][j];

                adjListReversed.get(dst).add(src);
            }
        }

        return adjListReversed;
    }

    private static int[] getInDegree(int numOfNodes, List<List<Integer>> adjList) {

        int[] inDegrees = new int[numOfNodes];

        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {

                int dst = adjList.get(i).get(j);
                inDegrees[dst]++;
            }
        }

        return inDegrees;
    }
}
