package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1466_ReorderRoutes {

    public static void main(String[] args) {

        _1466_ReorderRoutes obj = new _1466_ReorderRoutes();
        int n = 6;
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        int minReorder = obj.minReorder(n, connections);
        System.out.println(minReorder);
    }

    public int minReorder(int n, int[][] connections) {

        Map<Integer, List<Edge>> adjList = getAdjList(n, connections);

        boolean[] isVisited = new boolean[n];

        return dfs(0, adjList, isVisited);
    }

    /*
    Mistake I was making:
    You are passing count as a parameter, but since Java is pass-by-value for primitives,
    the incremented value in recursive calls does not propagate back up the call stack.
    As a result, only the initial value of count is returned, and increments in deeper calls are lost.

    Fix:
    Instead of passing count as a parameter,
    accumulate the result from recursive calls and return the total.
     */

    private int dfs(int currNode, Map<Integer, List<Edge>> adjList, boolean[] isVisited) {

        isVisited[currNode] = true;
        int count = 0;

        for (Edge edge : adjList.get(currNode)) {

            int adjNode = edge.node;

            if (!isVisited[adjNode]) {

                /*
                It is important to check this after checking !isVisited[adjNode]

                I had a doubt:
                if I'm having isDirected true 5 times, and if (edge.isDirected) count++, then why count is not 5 all the time ?

                count is not always 5 because the DFS only increments count
                when it traverses an edge with isDirected == true and the adjacent node has not been visited yet.

                If some directed edges are not part of the traversal from the starting node (node 0),
                or if a node is already visited via another path, those edges are not counted.
                 */
                if (edge.isDirected) {
                    count++;
                }

                isVisited[adjNode] = true;
                count += dfs(adjNode, adjList, isVisited);
            }
        }

        return count;
    }

    /*
    Why it is important to reach every node ?
    - As the given connections have direction,
    - so if we go through the actual connections given, we might not able to reach each and every node.
    - But it is important to reach every node because
    - it might happen that the those neighbours which are unaccessible from source, can be neighbours whose path leads to source node.
     */
    private Map<Integer, List<Edge>> getAdjList(int n, int[][] connections) {

        Map<Integer, List<Edge>> adjList = new HashMap<>();

        for (int i = 0; i < connections.length; i++) {

            int src = connections[i][0];
            int dst = connections[i][1];

            adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(dst, true));
            adjList.computeIfAbsent(dst, k -> new ArrayList<>()).add(new Edge(src, false));
        }

        return adjList;
    }

    private static class Edge {

        int node;
        boolean isDirected;

        Edge(int node, boolean isDirected) {
            this.node = node;
            this.isDirected = isDirected;
        }
    }
}
