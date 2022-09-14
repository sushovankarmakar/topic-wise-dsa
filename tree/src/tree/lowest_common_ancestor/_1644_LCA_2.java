package tree.lowest_common_ancestor;

import tree.TreeNode;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 * <p>
 * BEST Resource to understand : I understood from below post.
 * Understand the difference of LCA_1 and LCA_2
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/933835/Java.-Difference-from-236-is-you-need-to-search-the-entire-tree.
 */
public class _1644_LCA_2 {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("3 5 1 6 2 0 8 N N 7 4");
        TreeNode p = root1.left;
        TreeNode q = root1.left.right.right;

        System.out.println(lowestCommonAncestor(root1, p, q).val);  // 5
    }

    private static boolean isPFound = false;
    private static boolean isQFound = false;

    /**
     * IMPORTANT :
     * It is NOT guaranteed that both p and q are in the tree.
     * Hence,
     * i.   We need a way to record if we've seen both p and q
     * ii.  We need to traverse the entire tree even after we've found one of them.
     */
    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode node = findLCA(root, p, q);

        return (isPFound && isQFound) ? node : null;
    }

    private static TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        // IMPORTANT : The position of the below two recursive calls really matters.
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);

        if (root == p) {
            isPFound = true;
            return root;
        }

        if (root == q) {
            isQFound = true;
            return root;
        }

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
