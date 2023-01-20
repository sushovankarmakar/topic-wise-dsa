package bst.bst_iterator;

import bst.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * https://www.youtube.com/watch?v=ssL3sHwPeb4
 */
public class _653_TwoSumInBST {

    /**
     *  time: O(nlogn), space: O(1)
     *  https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106059/JavaC++-Three-simple-methods-choose-one-you-like/139201
     */

    /**
     * time complexity : O(n)
     * space complexity : O(h) - height of the tree.
     */
    public boolean findTarget(TreeNode root, int k) {

        BSTIterator l = new BSTIterator(root, false);
        BSTIterator r = new BSTIterator(root, true);

        int left = l.next();
        int right = r.next();

        while (left < right && l.hasNext() && r.hasNext()) {

            if (left + right == k) {
                return true;
            } else if (left + right < k) {
                left = l.next();
            } else {
                right = r.next();
            }
        }

        return false;
    }

    class BSTIterator {
        private final Stack<TreeNode> stack;
        private final boolean isReverse;

        public BSTIterator(TreeNode root, boolean isReverse) {
            this.stack = new Stack<>();
            this.isReverse = isReverse;
            pushNodes(root);
        }

        public int next() {
            TreeNode curr = stack.pop();

            if (isReverse) {
                pushNodes(curr.left);
            } else {
                pushNodes(curr.right);
            }

            return curr.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public void pushNodes(TreeNode curr) {
            while (curr != null) {
                stack.add(curr);
                curr = isReverse ? curr.right : curr.left;
            }
        }
    }
}


