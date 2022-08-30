package tree.summation;

import tree.TreeNode;

/**
 * https://practice.geeksforgeeks.org/problems/maximum-path-sum-from-any-node/1
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * <p>
 * https://www.youtube.com/watch?v=WszrfSwMz58 (Striver)
 * https://www.youtube.com/watch?v=Osz-Vwer6rw (Aditya Verma)
 * <p>
 * https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39775/Accepted-short-solution-in-Java
 */
public class _124_MaxPathSum_AnyNodeToAny {

    /**
     * Striver Approach : Code is very similar to 'find height of the tree'.
     */
    public int maxPathSum(TreeNode root) {

        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        solve(root, maxSum);

        return maxSum[0];
    }

    private int solve(TreeNode root, int[] maxSum) {

        if (root == null) return 0;

        int leftHeightSum = Math.max(0, solve(root.left, maxSum));
        int rightHeightSum = Math.max(0, solve(root.right, maxSum));

        maxSum[0] = Math.max(maxSum[0], root.val + leftHeightSum + rightHeightSum);

        return root.val + Math.max(leftHeightSum, rightHeightSum);
    }


    /**
     * -----------------------------------------------
     * Aditya Verma Approach
     */
    public int maxPathSum_1(TreeNode root) {

        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        solve_1(root, maxSum);
        return maxSum[0];
    }

    /**
     * For each node there can be four ways that the max path goes through the node:
     * 1. Node only
     * 2. Max path through Left Child + Node
     * 3. Max path through Right Child + Node
     * 4. Max path through Left Child + Node + Max path through Right Child
     * <p>
     * The idea is to keep trace of four paths and pick up the max one in the end.
     */
    private int solve_1(TreeNode root, int[] maxSum) {

        if (root == null) return 0;

        int leftHeightSum = solve_1(root.left, maxSum);
        int rightHeightSum = solve_1(root.right, maxSum);

        int currHeightMaxSum = Math.max(root.val + Math.max(leftHeightSum, rightHeightSum), root.val); // case 1, 2, 3
        int currDiameterSum = root.val + leftHeightSum + rightHeightSum;    // case 4

        maxSum[0] = Math.max(maxSum[0], Math.max(currDiameterSum, currHeightMaxSum));   // taking the max out of all 4

        return currHeightMaxSum;
    }

}
