package tree;

/**
 * https://practice.geeksforgeeks.org/problems/check-for-bst/1
 * https://leetcode.com/problems/validate-binary-search-tree/
 * <p>
 * https://www.youtube.com/watch?v=f-sj7I5oXEI (Striver)
 * https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
 */
public class _98_ValidateBST {

    /**
     * [5,4,6,null,null,3,7] - I made this mistake.
     */
    public boolean isValidBST(TreeNode root) {

        // Intuition :
        // it's all about checking each value within a given range or not.
        // if it is, then recursively call check for the left and right children.

        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode root, long minRange, long maxRange) {

        if (root == null) return true;

        return (minRange < root.val)
                && (root.val < maxRange)
                && validate(root.left, minRange, root.val)
                && validate(root.right, root.val, maxRange);
    }
}
