package tree.basics;

import tree.TreeNode;

public class _111_MinimumDepthOfBinaryTree {

    /*
    - One tricky thing that
        we need to consider is when one of the children is NULL and the other one isn't.
    - We shouldn't move forward with recursion on the NULL child;
        if we do, we would return 0 due to the base condition
        and the count of nodes from the leaf node on the other side would be discarded as we are taking the minimum of the two.
    - In case both children are NULL,
        it's fine to go into recursion as both would return 0,
        and the minimum of the two won't cause an issue.
     */

    public int minDepth(TreeNode root) {

        // base case handling
        if (root == null) return 0;

        // If only one of child is non-null, then go into that recursion.
        if (root.left == null && root.right != null) return 1 + minDepth(root.right);
        if (root.left != null && root.right == null) return 1 + minDepth(root.left);

        // Both children are non-null, hence call for both children.
        return 1 + Math.min(
                minDepth(root.left),
                minDepth(root.right)
        );
    }
}
