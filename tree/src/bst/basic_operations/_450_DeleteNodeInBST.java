package bst.basic_operations;

import bst.TreeNode;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/
 * https://practice.geeksforgeeks.org/problems/delete-a-node-from-bst/1
 * <p>
 * https://www.youtube.com/watch?v=kouxiP_H5WE (Striver : Best explanation)
 */
public class _450_DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) return null;

        // special handling when root node has to be deleted.
        if (root.val == key) {
            return deleteKeyNode(root);
        }

        TreeNode curr = root;   // need to preserve root node, so that we can return it later.
        while (curr != null) {

            if (key < curr.val) {

                if (curr.left != null && curr.left.val == key) {

                    TreeNode keyNode = curr.left;
                    curr.left = deleteKeyNode(keyNode); // delete keyNode and return the root of the subtree formed after the deletion.
                    break;
                } else {
                    curr = curr.left;
                }
            } else {

                if (curr.right != null && curr.right.val == key) {

                    TreeNode keyNode = curr.right;
                    curr.right = deleteKeyNode(keyNode);
                    break;
                } else {
                    curr = curr.right;
                }
            }
        }

        return root;
    }

    /**
     * this method deletes the keyNode and
     * returns new root of the subtree after deletion. basically it always returns the keyNode's left child.
     * <p>
     * STEPS :
     * 1. find the rightmost node of keyNode's left child.
     * 2. make the keyNode's right child as previously found rightmost node's right child.
     * 3. now return the keyNode's left child as the root of new subtree.
     */
    private TreeNode deleteKeyNode(TreeNode keyNode) {
        if (keyNode.left == null) return keyNode.right;
        if (keyNode.right == null) return keyNode.left;

        TreeNode rightMostNodeOnLeft = getRightMostNode(keyNode.left);  // step 1
        rightMostNodeOnLeft.right = keyNode.right;                      // step 2

        return keyNode.left;    // step 3
    }

    private TreeNode getRightMostNode(TreeNode curr) {

        while (curr != null && curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }
}
