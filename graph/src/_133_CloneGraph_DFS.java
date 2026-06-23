package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/clone-graph/description/
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_CloneConnectedUndirectedGraph.java
 * https://www.youtube.com/watch?v=vma9tCQUXk8
 */
public class _133_CloneGraph_DFS {

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        _133_CloneGraph_DFS o = new _133_CloneGraph_DFS();
        Node cloned = o.cloneGraph(node1);
        System.out.println(cloned.val);
    }

    private final Map<Node, Node> clonedMap = new HashMap<>(); // works as visited array also.

    public Node cloneGraph(Node node) {

        if (node == null) return null;

        // If the node was already visited before.
        // Return the clone from the visited dictionary.
        if (clonedMap.containsKey(node)) {
            return clonedMap.get(node);
        }

        // Create a clone for the given node.
        Node clonedNode = new Node(node.val);
        // The key is original node and value being the clone node.
        clonedMap.put(node, clonedNode);

        // solving using dfs
        // Iterate through the neighbors to generate their clones
        // and prepare a list of cloned neighbors to be added to the cloned node.
        for (Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(cloneGraph(neighbor));
        }

        return clonedNode;
    }

    private static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
