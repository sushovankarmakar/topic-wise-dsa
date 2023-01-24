package src.traversal;

import java.util.ArrayList;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/depth-first-traversal-for-a-graph/1
 * <p>
 * https://www.youtube.com/watch?v=Qzf1a--rhp8 (Striver)
 */
public class DFS {

    /**
     * space : O(3N)
     * time : O(N) + O(2E)
     */
    public List<Integer> doDFS(int noOfNodes, List<List<Integer>> adj) {
        boolean[] isVisited = new boolean[noOfNodes];
        List<Integer> dfs = new ArrayList<>();

        int currNode = 0;
        isVisited[currNode] = true;

        dfsHelper(currNode, isVisited, adj, dfs);
        return dfs;
    }

    private void dfsHelper(int currNode, boolean[] isVisited, List<List<Integer>> adj, List<Integer> dfs) {

        isVisited[currNode] = true;
        dfs.add(currNode);

        for (int neighbourNode : adj.get(currNode)) {

            if (!isVisited[neighbourNode]) {
                dfsHelper(neighbourNode, isVisited, adj, dfs);
            }
        }
    }
}
