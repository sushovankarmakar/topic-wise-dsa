package src;

import java.util.*;

/**
 * https://leetcode.com/problems/clone-graph/description/
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_CloneConnectedUndirectedGraph.java
 * https://www.youtube.com/watch?v=vma9tCQUXk8
 */
public class _133_CloneGraph_BFS {

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

        _133_CloneGraph_BFS o = new _133_CloneGraph_BFS();
        Node cloned = o.cloneGraph(node1);
        System.out.println(cloned.val);
    }

    public Node cloneGraph(Node node) {

        if (node == null) return null;

        Map<Node, Node> clonedMap = new HashMap<>(); // works as visited array also.
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);
        clonedMap.put(node, new Node(node.val)); // cloning first node.

        // solving using bfs
        while (!queue.isEmpty()) {

            Node currNode = queue.poll();
            for (Node neighbor : currNode.neighbors) {

                Node clonedNeighbor;
                if (!clonedMap.containsKey(neighbor)) { // not visited
                    clonedNeighbor = new Node(neighbor.val);
                    clonedMap.put(neighbor, clonedNeighbor);
                    queue.add(neighbor);
                } else {
                    clonedNeighbor = clonedMap.get(neighbor);
                }

                // stitching together
                Node src = clonedMap.get(currNode);
                Node dst = clonedNeighbor;
                src.neighbors.add(dst); // dst.neighbors.add(src); will happen in next round
            }
        }

        return clonedMap.get(node);
    }

    static class Node {
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
