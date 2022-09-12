package tree.tree_view;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://practice.geeksforgeeks.org/problems/left-view-of-binary-tree/1
 * <p>
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Tree_LeftViewOfBinaryTree.java
 */
public class LeftView {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("1 3 2");
        System.out.println(leftView_usingRecursion(root1));    // 1, 3
    }

    /**
     * Using Recursion
     * <p>
     * time     : O(n)
     * space    : O(h) where h is height of the tree.
     */
    private static List<Integer> leftViewNodes;

    private static List<Integer> leftView_usingRecursion(TreeNode root) {

        leftViewNodes = new ArrayList<>();
        traverse(root, 0);
        return leftViewNodes;
    }

    private static void traverse(TreeNode root, int currLevel) {
        if (root == null) return;

        if (currLevel == leftViewNodes.size()) {
            leftViewNodes.add(root.val);
        }

        traverse(root.left, currLevel + 1);
        traverse(root.right, currLevel + 1);
    }

    /**
     * Level order traversal, left view and right view has the same structure.
     * Follow this structure to remember all three easily.
     */
    private List<Integer> rightView_usingRecursion(TreeNode root) {

        List<Integer> values = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {

            int nodesInCurrentLevel = queue.size();

            for (int i = 0; i < nodesInCurrentLevel; i++) {

                TreeNode currNode = queue.remove();

                if (i == 0) {
                    values.add(currNode.val);
                }

                if (currNode.left != null) queue.add(currNode.left);  // left child will be stored first for left view
                if (currNode.right != null) queue.add(currNode.right);
            }
        }
        return values;
    }
}
