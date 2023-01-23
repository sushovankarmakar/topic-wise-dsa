package bst.bst_in_binary_tree;

import bst.TreeNode;

/**
 * https://www.youtube.com/watch?v=X0oXMdtUDwo (Striver : Best explanation)
 *
 * https://leetcode.com/problems/largest-bst-subtree/
 * https://practice.geeksforgeeks.org/problems/largest-bst/1
 *
 * https://github.com/striver79/FreeKaTreeSeries/blob/main/largestBSTinBTJava
 */
public class _333_LargestBSTInBinaryTree {

    static class Tuple {
        private int maxVal;
        private int minVal;
        private int size;

        Tuple(int maxVal, int minVal, int size) {
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.size = size;
        }
    }

    private static Tuple largestBstHelper(TreeNode root) {

        if (root == null) {
            // tricky part.
            // we set max value of this node as Integer.MIN_VALUE
            // and min value of this node as Integer.MAX_VALUE.

            return new Tuple(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);  // An empty tree is a BST of size 0.
        }

        // post order traversal. [left - right - root]
        // Get values from left and right subtree of current tree.
        Tuple left = largestBstHelper(root.left);
        Tuple right = largestBstHelper(root.right);

        int maxVal;
        int minVal;
        int size;

        // if current node is greater than max in left AND smaller than min in right, it is a BST.
        if (left.maxVal < root.val && root.val < right.minVal) {
            // this is a root of valid BST

            maxVal = Math.max(root.val, right.maxVal);  // bigger nodes will be on the right side, so to find maxVal, compare current val with right side maxVal
            minVal = Math.min(left.minVal, root.val);   // smaller nodes will be on the left side, so to find minVal, compare current val with left side minVal

            size = left.size + right.size + 1;

        } else {

            // as we're building from bottom to top...
            // Otherwise, return [-inf, inf] so that parent can't be a valid BST
            maxVal = Integer.MAX_VALUE;
            minVal = Integer.MIN_VALUE;

            size = Math.max(left.size, right.size);
        }
        return new Tuple(maxVal, minVal, size);
    }

    // Return the size of the largest subtree which is also a BST
    static int largestBst(TreeNode root) {
        return largestBstHelper(root).size;
    }
}
