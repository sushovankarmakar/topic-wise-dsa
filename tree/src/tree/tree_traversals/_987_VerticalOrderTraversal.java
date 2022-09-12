package tree.tree_traversals;

import tree.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 * https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
 * <p>
 * https://www.youtube.com/watch?v=q_a6lpbKJdw (Striver)
 * <p>
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Tree_VerticalTraversalOfBinaryTree.java
 */
public class _987_VerticalOrderTraversal {

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(root, 0, 0));

        /**
         * In Leetcode question it is asked that,
         * There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
         * That's why Inner TreeMap is needed.
         */
        TreeMap<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();

        while (!queue.isEmpty()) {

            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {

                Tuple currTuple = queue.poll();
                TreeNode currNode = currTuple.node;
                int vertical = currTuple.vertical;
                int horizontal = currTuple.horizontal;

                if (!map.containsKey(vertical)) {
                    map.put(vertical, new TreeMap<>());
                }
                if (!map.get(vertical).containsKey(horizontal)) {
                    map.get(vertical).put(horizontal, new ArrayList<>());
                }

                List<Integer> currList = map.get(vertical).get(horizontal);
                currList.add(currNode.val);
                map.get(vertical).put(horizontal, currList);

                if (currNode.left != null) queue.add(new Tuple(currNode.left, vertical - 1, horizontal + 1));
                if (currNode.right != null) queue.add(new Tuple(currNode.right, vertical + 1, horizontal + 1));
            }
        }

        List<List<Integer>> verticalOrder = new ArrayList<>();
        for (TreeMap<Integer, List<Integer>> innerMap : map.values()) {

            List<Integer> list = new ArrayList<>();
            for (List<Integer> values : innerMap.values()) {
                Collections.sort(values);
                list.addAll(values);
            }
            verticalOrder.add(list);
        }
        return verticalOrder;
    }

    static class Tuple {
        TreeNode node;
        int vertical;
        int horizontal;

        Tuple(TreeNode node, int vertical, int horizontal) {
            this.node = node;
            this.vertical = vertical;
            this.horizontal = horizontal;
        }
    }
}
