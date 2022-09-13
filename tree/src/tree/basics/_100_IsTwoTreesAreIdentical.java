package tree.basics;

import tree.TreeNode;

/**
 * https://leetcode.com/problems/same-tree/
 * https://practice.geeksforgeeks.org/problems/determine-if-two-trees-are-identical/1
 * <p>
 * https://www.youtube.com/watch?v=BhuvF_-PWS0 (Striver)
 */
public class _100_IsTwoTreesAreIdentical {

    /**
     * time  : O(n)
     * space : O(n)
     * <p>
     * traverse both the trees simultaneously.
     */
    boolean isIdentical(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;

        return root1.val == root2.val
                && isIdentical(root1.left, root2.left)
                && isIdentical(root1.right, root2.right);
    }
}
