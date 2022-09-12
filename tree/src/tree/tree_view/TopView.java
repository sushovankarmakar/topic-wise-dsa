package tree.tree_view;

import tree.TreeNode;

import java.util.*;

import static tree.TreeNode.buildTree;

/**
 * https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
 * <p>
 * https://www.youtube.com/watch?v=Et9OCDNvJ78 (Striver)
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Tree_TopViewOfBinaryTree.java
 */
public class TopView {

    public static void main(String[] args) {
        TreeNode root1 = buildTree("10 20 30 40 60 90 100");
        System.out.println(getTopView(root1));  // 40, 20, 10, 30, 100
    }

    /**
     * time : O(N)
     * space : O(N)
     */
    private static List<Integer> getTopView(TreeNode root) {

        Queue<Pair> queue = new LinkedList<>();
        if (root != null) queue.add(new Pair(root, 0));

        Map<Integer, Integer> map = new TreeMap<>();

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                Pair pair = queue.poll();
                TreeNode node = pair.node;
                int horizontal = pair.horizontal;

                if (!map.containsKey(horizontal)) {
                    map.put(horizontal, node.val);
                }

                if (node.left != null) queue.add(new Pair(node.left, horizontal - 1));
                if (node.right != null) queue.add(new Pair(node.right, horizontal + 1));
            }
        }

        return new ArrayList<>(map.values());
    }

    static class Pair {
        TreeNode node;
        int horizontal;

        Pair(TreeNode node, int horizontal) {
            this.node = node;
            this.horizontal = horizontal;
        }
    }
}
