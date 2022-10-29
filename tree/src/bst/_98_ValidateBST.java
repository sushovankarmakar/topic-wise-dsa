package bst;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 * https://practice.geeksforgeeks.org/problems/check-for-bst/1
 * <p>
 * https://www.youtube.com/watch?v=f-sj7I5oXEI (Striver : Best Explanation)
 */
public class _98_ValidateBST {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("13 10 15 7 12 14 17 N 9 N N N N 16 N 8 N");
        System.out.println(isValidBST(root1));  // true

        TreeNode root2 = TreeNode.buildTree("13 10 15 7 14 14 17 N 9 N N N N 16 N 8 N");
        System.out.println(isValidBST(root2));  // false
    }

    /**
     * A binary search tree (BST) is a node-based binary tree data structure that has the following properties.
     * <p>
     * -- The left subtree of a node contains only nodes with keys less than the node’s key.
     * -- The right subtree of a node contains only nodes with keys greater than the node’s key.
     * -- Both the left and right subtrees must also be binary search trees.
     * -- Each node (item in the tree) has a distinct key.
     */
    private static boolean isValidBST(TreeNode root) {

        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode root, long minRange, long maxRange) {

        if (root == null) return true;

        return (minRange < root.val)
                && (root.val < maxRange)
                && validate(root.left, minRange, root.val)
                && validate(root.right, root.val, maxRange);
    }
}
