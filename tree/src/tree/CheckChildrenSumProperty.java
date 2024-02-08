package tree;

/**
 * https://practice.geeksforgeeks.org/problems/children-sum-parent/1
 */
public class CheckChildrenSumProperty {

    // Function to check whether all nodes of a tree have the value equal to the sum of their child nodes.
    public static int isSumProperty(TreeNode root) {

        if (root == null) return 1;
        if (root.left == null && root.right == null) return 1;

        int leftChildValue = root.left != null ? root.left.val : 0;
        int rightChildValue = root.right != null ? root.right.val : 0;

        return (leftChildValue + rightChildValue == root.val)
                && (isSumProperty(root.left) == 1)
                && (isSumProperty(root.right) == 1) ? 1 : 0;
    }
}
