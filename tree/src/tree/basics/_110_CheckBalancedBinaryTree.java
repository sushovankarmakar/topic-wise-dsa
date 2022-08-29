package tree.basics;

import tree.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * https://practice.geeksforgeeks.org/problems/check-for-balanced-tree/1
 * <p>
 * https://www.youtube.com/watch?v=Yt50Jfbd8Po (Striver)
 * https://www.geeksforgeeks.org/how-to-determine-if-a-binary-tree-is-balanced/
 */
public class _110_CheckBalancedBinaryTree {

    /**
     * Time Complexity: O(n)
     * Auxiliary Space Space: O(n)
     */
    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    /**
     * height of the binary tree code + 3 lines.
     */
    private int checkHeight(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) return -1;    // line 1

        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) return -1;   // line 2

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;  // line 3

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
