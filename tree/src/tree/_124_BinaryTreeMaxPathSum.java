package tree;

/**
 * https://practice.geeksforgeeks.org/problems/maximum-path-sum-from-any-node/1
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 * https://www.youtube.com/watch?v=Osz-Vwer6rw&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go
 *
 * https://www.geeksforgeeks.org/find-maximum-path-sum-in-a-binary-tree/
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39775/Accepted-short-solution-in-Java
 */
public class _124_BinaryTreeMaxPathSum {

    public int maxPathSum(TreeNode root) {

        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        solve(root, maxSum);
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
    private int solve(TreeNode root, int[] maxSum) {

        if (root == null) return 0;

        int leftHeightSum = solve(root.left, maxSum);
        int rightHeightSum = solve(root.right, maxSum);

        int currHeightMaxSum = Math.max(root.val + Math.max(leftHeightSum, rightHeightSum), root.val); // case 1, 2, 3
        int currDiameterSum = root.val + leftHeightSum + rightHeightSum;    // case 4

        /*if (root.val >= 0) {
            currDiameterSum = root.val + leftHeightSum + rightHeightSum;
            currHeightMaxSum = root.val + Math.max(leftHeightSum, rightHeightSum);
        } else {
            currHeightMaxSum = Math.max(leftHeightSum, rightHeightSum);
            currDiameterSum = currHeightMaxSum;
        }*/

        maxSum[0] = Math.max(maxSum[0], Math.max(currDiameterSum, currHeightMaxSum));   // taking the max out of all 4

        return currHeightMaxSum;
    }

}
