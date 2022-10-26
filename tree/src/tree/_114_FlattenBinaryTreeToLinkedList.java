package tree;

import java.util.Stack;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * https://practice.geeksforgeeks.org/problems/flatten-binary-tree-to-linked-list/1
 */
public class _114_FlattenBinaryTreeToLinkedList {

    /**
     * This question has 3 solutions :
     * 1. recursive.
     * 2. iterative. using stack.
     * 3. iterative. kind of morris traversal.
     */
    // SOLUTION - 1 : time : O(N), space : O(N)
    TreeNode prev;

    private void flatten(TreeNode root) {
        /**
         * right -> left -> root.
         */
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }

    // ------------------------------------------------------------------------------------------------------
    // SOLUTION - 2 : time : O(N), space : O(N)
    private void flatten_iterative(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            TreeNode curr = stack.pop();

            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);

            if (!stack.isEmpty()) {
                curr.right = stack.peek();
            }
            curr.left = null;
        }
    }

    // ------------------------------------------------------------------------------------------------------
    // SOLUTION - 3 : time : O(N), space : O(1)

    /**
     * Intuition is similar to MORRIS traversal.
     * <p>
     * On the left subtree, find the right most node of preorder
     * and connect that node with current root node's right child.
     */
    private void flatten_solution3(TreeNode root) {
        if (root == null) return;

        TreeNode curr = root;
        while (curr != null) {

            // If the node has a left child
            if (curr.left != null) {

                // Find the rightmost node
                TreeNode rightmost = curr.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                // rewire the connections
                rightmost.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }

            // move on to the right side of the tree
            curr = curr.right;
        }
    }
}
