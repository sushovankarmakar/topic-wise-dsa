package src._8_shortest_path_algo;

import java.util.Arrays;

public class DSU {

    private int[] rank; // To store the rank of each node
    private int[] size; // To store the size of components that each node belongs to
    private int[] root; // To store the ultimate root of each node

    public DSU(int n) {
        rank = new int[n + 1];
        size = new int[n + 1];
        root = new int[n + 1];

        // Initialise size array with 1
        Arrays.fill(size, 1);

        // Initialise each node with its own value
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    // Helper function to find ultimate parent along with path compression
    public int findRoot(int node) {

        if (node == root[node]) { // base case
            return node;
        }
        return root[node] = findRoot(root[node]); // Backtracking step for path compression
    }

    // Function to determine if two nodes are in the same component or not
    public boolean find(int u, int v) {
        
        return findRoot(u) == findRoot(v); // Return true if both have same ultimate parent
    }

    // Function to union of two nodes based on ranks
    public void unionByRank(int u, int v) {

        // Get the root of both nodes
        int rootU = findRoot(u);
        int rootV = findRoot(v);

        // Return if nodes already belong to the same component
        if (rootU == rootV) return;

        // Otherwise, join the node belonging to the smaller rank component to the node belonging to bigger rank component
        if (rank[rootU] > rank[rootV]) {
            root[rootV] = rootU;    // update root
        } else if (rank[rootU] < rank[rootV]) {
            root[rootU] = rootV;    // update root
        } else {
            // If both have same rank, join any node to the other and increment the rank of the parent node
            root[rootV] = rootU;    // update root
            rank[rootU]++;          // update rank
        }
    }

    public void unionBySize(int u, int v) {

        // Get the root of both nodes
        int rootU = findRoot(u);
        int rootV = findRoot(v);

        // Return if nodes already belong to the same component
        if (rootU == rootV) return;

        // Otherwise, join the node belonging to the smaller component to the node belonging to bigger component
        if (size[rootU] > size[rootV]) {
            root[rootV] = rootU;         // update root
            size[rootU] += size[rootV];  // update size 
        } else {
            root[rootU] = rootV;         // update root
            size[rootV] += size[rootU];  // update size
        }
    }

}
