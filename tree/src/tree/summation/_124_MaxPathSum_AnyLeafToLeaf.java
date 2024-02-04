package tree.summation;

import tree.TreeNode;

/**
 * https://practice.geeksforgeeks.org/problems/maximum-path-sum/1
 * https://www.geeksforgeeks.org/find-maximum-path-sum-two-leaves-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=ArNyupe-XH0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go (Aditya Verma)
 * <p>
 * https://www.youtube.com/watch?v=FxgpgxH2k8o (I understood from here)
 */
public class _124_MaxPathSum_AnyLeafToLeaf {

    /**
     * -9 6 -10
     * <p>
     * <p>
     * https://discuss.geeksforgeeks.org/comment/d75d240bf511f93e1bfda38127c53061
     * https://discuss.geeksforgeeks.org/comment/bb9547eba14239d62cd52a0047c6b6b0
     * <p>
     * <p>
     * 5 N 6 -5 5 - skewed tree.
     * <p>
     * <p>
     * 5
     * N    6
     * -5  5
     */

    int maxPathSum(TreeNode root) {

        if (root.left == null && root.right == null) {  // only root is present
            return root.val;
        }

        // If the tree is right-most or left-most tree 
        // then first we have to adjust the tree such that both the right and left are not null. 
        // Left-most means if the right of super root of the tree is null and 
        // right-most tree means if left of super root of the tree is null.
        if (root.left == null || root.right == null) {
            root = setTree(root);
        }

        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        solve(root, maxSum);
        return maxSum[0];
    }

    private int solve(TreeNode root, int[] maxSum) {

        if (root == null) return 0;

        int leftHeightSum = solve(root.left, maxSum);
        int rightHeightSum = solve(root.right, maxSum);

        // if the tree contains -ve values as well.
        if (root.left != null && root.right != null) {  // why this check ? ans : https://www.youtube.com/watch?v=FxgpgxH2k8o (9:23)

            int currDiameterSum = root.val + leftHeightSum + rightHeightSum;
            maxSum[0] = Math.max(maxSum[0], currDiameterSum);

            int currHeightMaxSum = root.val + Math.max(leftHeightSum, rightHeightSum);
            return currHeightMaxSum;
        }

        return root.left == null ? root.val + rightHeightSum : root.val + leftHeightSum;
    }

    private TreeNode setTree(TreeNode root) {
        TreeNode temp = new TreeNode(0);
        if (root.left == null) {
            root.left = temp;
        } else {
            root.right = temp;
        }

        return root;
    }
}
