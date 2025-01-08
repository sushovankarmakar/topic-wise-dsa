package src.shortest_path_algo;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/problems/disjoint-set-union-find/1
 * https://takeuforward.org/data-structure/disjoint-set-union-by-rank-union-by-size-path-compression-g-46/
 * <p>
 * https://www.youtube.com/watch?v=aBxjDBC4M1U&ab_channel=takeUforward
 */
public class DisJoinSet {

    List<Integer> ranks = new ArrayList<>();
    List<Integer> sizes = new ArrayList<>();
    List<Integer> parents = new ArrayList<>();

    public DisJoinSet(int n) {
        for (int i = 0; i <= n; i++) { // if it is a 1 base indexing graph, then i <= n, NOT i < n
            ranks.add(0);
            sizes.add(0);
            parents.add(i);
        }
    }

    public int findUParent(int node) {
        if (node == parents.get(node)) {
            return node;
        }

        int ultimateParent = findUParent(parents.get(node));
        parents.set(node, ultimateParent); // re-assigning parent value helps us to do path compression.

        // IMPORTANT : first parents.set(), then parents.get()
        // don't return parents.set() directly because parents.set() return the PREVIOUS value stored in that index
        return parents.get(node);
    }

    public void unionByRank(int u, int v) {
        int uParentOfU = findUParent(u);
        int uParentOfV = findUParent(v);

        if (uParentOfU == uParentOfV) return;

        // bigger ranked node should be the parent of smaller ranked node.
        if (ranks.get(uParentOfU) < ranks.get(uParentOfV)) {
            parents.set(uParentOfU, uParentOfV);    // U's parent become V

        } else if (ranks.get(uParentOfU) > ranks.get(uParentOfV)) {
            parents.set(uParentOfV, uParentOfU);    // V's parent become U

        } else {
            parents.set(uParentOfV, uParentOfU);    // V's parent become U
            ranks.set(uParentOfU, ranks.get(uParentOfU) + 1);
        }
    }

    public void unionBySize(int u, int v) {
        int uParentOfU = findUParent(u);
        int uParentOfV = findUParent(v);

        if (uParentOfU == uParentOfV) return;

        // bigger sized node should be the parent of smaller sized node.

        int biggerSizedNode;
        int smallerSizedNode;

        if (sizes.get(uParentOfU) < sizes.get(uParentOfV)) {

            biggerSizedNode = uParentOfV;
            smallerSizedNode = uParentOfU;

        } else {
            biggerSizedNode = uParentOfU;
            smallerSizedNode = uParentOfV;
        }

        parents.set(smallerSizedNode, biggerSizedNode);
        int newSize = sizes.get(smallerSizedNode) + sizes.get(biggerSizedNode);
        sizes.set(biggerSizedNode, newSize);
    }

    public static void main(String[] args) {

        // Union by rank
        DisJoinSet ds = new DisJoinSet(7);
        ds.unionByRank(1, 2);
        ds.unionByRank(2, 3);
        ds.unionByRank(4, 5);
        ds.unionByRank(6, 7);
        ds.unionByRank(5, 6);

        // ------------------------------------------- Union by rank
        // if 3 and 7 same or not
        if (ds.findUParent(3) == ds.findUParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        ds.unionByRank(3, 7);
        if (ds.findUParent(3) == ds.findUParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        // ------------------------------------------- Union by size
        DisJoinSet ds1 = new DisJoinSet(7);
        ds1.unionBySize(1, 2);
        ds1.unionBySize(2, 3);
        ds1.unionBySize(4, 5);
        ds1.unionBySize(6, 7);
        ds1.unionBySize(5, 6);

        // if 3 and 7 same or not
        if (ds1.findUParent(3) == ds1.findUParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        ds1.unionBySize(3, 7);
        if (ds1.findUParent(3) == ds1.findUParent(7)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }
    }
}
