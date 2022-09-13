package tree.basics;

import tree.TreeNode;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * https://practice.geeksforgeeks.org/problems/symmetric-tree/1
 * <p>
 * https://www.youtube.com/watch?v=nKggNAiEpBE  (Striver)
 */
public class _101_SymmetricBinaryTree {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("1 2 2 3 4 4 3");
        System.out.println(isSymmetric(root1)); // true

        TreeNode root2 = TreeNode.buildTree("1 2 2 N 3 N 3");
        System.out.println(isSymmetric(root2)); // false

        TreeNode root3 = TreeNode.buildTree("N");
        System.out.println(isSymmetric(root3)); // true
    }

    private static boolean isSymmetric(TreeNode root) {
        return root == null || checkSymmetric(root.left, root.right);
    }

    private static boolean checkSymmetric(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }

        if (left.val != right.val) {
            return false;
        }

        return checkSymmetric(left.left, right.right) &&
                checkSymmetric(left.right, right.left);
    }
}
