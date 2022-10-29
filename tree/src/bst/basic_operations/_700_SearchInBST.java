package bst.basic_operations;

import bst.TreeNode;

/**
 * https://leetcode.com/problems/search-in-a-binary-search-tree/
 * https://practice.geeksforgeeks.org/problems/search-a-node-in-bst/1
 * <p>
 * https://www.youtube.com/watch?v=KcNt6v_56cc
 */
public class _700_SearchInBST {

    private TreeNode searchBST_iterative(TreeNode root, int val) {

        while (root != null && root.val != val) {
            root = val < root.val ? root.left : root.right;
        }

        return root;
    }

    private TreeNode searchBST(TreeNode root, int val) {

        if (root == null || root.val == val) return root;

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }

    // GFG Solution.
    boolean search(TreeNode root, int x) {

        while (root != null && root.val != x) {

            root = x < root.val ? root.left : root.right;
        }

        return root != null;
    }
}
