package bst.successor_predecessor;

/*
 * Given a node in a binary search tree, return the next bigger element, also known as the inorder successor.
 * You can assume each node has a parent pointer.
 *
 * For example, the inorder successor of 22 is 30.
 *
 *    10
 *   /  \
 *  5    30
 *      /  \
 *    22    35
 */

public class _510_InorderSuccessorWithParentPointer {

    /**
     * Here root of the tree is not given, the actual node is given.
     */

    // https://chatgpt.com/share/003a2506-10f0-4edd-b7fc-2be50121e461
    /*
     * current == parent.right
     * -----------------------
     * The condition current == parent.right is used to determine
     * whether we need to continue moving up the tree to find the inorder successor.
     * It helps us find the nearest ancestor for which the current node is part of its left subtree.
     *
     *        20
     *       /  \
     *     10    30
     *     / \   / \
     *    5  15 25 35
     *
     * Let's find the inorder successor for the node with value 15:
     *
     * Node 15:
     * 1. Node 15 does not have a right subtree.
     * 2. We need to go up to the parent nodes to find the nearest ancestor for which node 15 would be in the left subtree.
     *
     * The steps would be:
     *
     * 1. Node 15's parent is node 10.
     * 2. Node 15 is the right child of node 10, so we need to continue moving up the tree.
     * 3. The parent of node 10 is node 20.
     * 4. Node 10 is the left child of node 20, so node 20 is the inorder successor of node 15.
     * 5. Thus, the inorder successor of node 15 is node 20.
     */
    public static TreeNode inorderSuccessor(TreeNode node) {

        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return findMin(node.right);
        }

        TreeNode currNode = node;
        TreeNode parentNode = node.parent;

        while (parentNode != null && currNode == parentNode.right) {
            currNode = parentNode;
            parentNode = currNode.parent;
        }

        return parentNode;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
}
