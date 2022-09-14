package tree.lowest_common_ancestor;

import tree.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-binary-tree/1
 * <p>
 * https://www.youtube.com/watch?v=_-QHfMDde90 (Striver)
 * <p>
 * Understand the difference of LCA_1 and LCA_2
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/933835/Java.-Difference-from-236-is-you-need-to-search-the-entire-tree.
 */
public class _236_LCA_1 {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("3 5 1 6 2 0 8 N N 7 4");
        TreeNode p = root1.left;
        TreeNode q = root1.left.right.right;

        System.out.println(lowestCommonAncestor(root1, p, q).val);  // 5
    }

    /**
     * IMPORTANT :
     * It is guaranteed that both p and q are in the tree.
     * Because of this, we can return either p OR q as soon as we find one of them.
     */
    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }
}
