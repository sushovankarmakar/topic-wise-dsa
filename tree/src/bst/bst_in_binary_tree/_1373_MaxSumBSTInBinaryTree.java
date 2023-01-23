package bst.bst_in_binary_tree;

import bst.TreeNode;

/**
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 *
 * https://www.youtube.com/watch?v=X0oXMdtUDwo (Striver : Best explanation)
 */
public class _1373_MaxSumBSTInBinaryTree {

    private static int maxSum = 0;

    static class Tuple {
        private int maxVal;
        private int minVal;
        private int sum;

        Tuple(int maxVal, int minVal, int sum) {
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.sum = sum;
        }
    }

    private static Tuple maxSumBSTHelper(TreeNode curr) {

        if (curr == null) {
            return new Tuple(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        }

        Tuple left = maxSumBSTHelper(curr.left);
        Tuple right = maxSumBSTHelper(curr.right);

        int maxVal;
        int minVal;
        int sum;
        if (left.maxVal < curr.val && curr.val < right.minVal) {

            maxVal = Math.max(curr.val, right.maxVal);
            minVal = Math.min(left.minVal, curr.val);
            sum = left.sum + right.sum + curr.val;

        } else {

            maxVal = Integer.MAX_VALUE;
            minVal = Integer.MIN_VALUE;
            sum = Math.max(left.sum, right.sum);

        }
        maxSum = Math.max(maxSum, sum);
        return new Tuple(maxVal, minVal, sum);
    }

    public static int maxSumBST(TreeNode root) {
        int oldMax = maxSumBSTHelper(root).sum;
        return Math.max(maxSum, oldMax);
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree("4 8 N 6 1 9 N -5 4 N N N -3 N 10");
        //TreeNode.printInorder(root);
        System.out.println(maxSumBST(root));
    }
}
