package src.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=-tgVpUgsQ5k (Striver)
 * https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
 *
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Graph_BFSTraversalOfGraph.java
 */
public class BFS {

    /**
     * space : O(3N)        -> queue + visited array + output array.
     * time : O(N) + O(2E)
     */
    private static List<Integer> doBFS(int numOfNodes, List<List<Integer>> adjList) {
        boolean[] isVisited = new boolean[numOfNodes];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> bfs = new ArrayList<>();

        int currNode = 0;
        isVisited[currNode] = true;
        queue.add(currNode);

        while (!queue.isEmpty()) {      // time : O(N)
            currNode = queue.poll();
            bfs.add(currNode);

            for (int neighbourNode : adjList.get(currNode)) {   // space : O(2E). total degree = 2 * edges.
                if (!isVisited[neighbourNode]) {
                    isVisited[neighbourNode] = true;
                    queue.add(neighbourNode);
                }
            }
        }

        return bfs;
    }
}
