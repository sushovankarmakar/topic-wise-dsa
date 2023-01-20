package bst.bst_iterator;

import bst.TreeNode;

import java.util.Stack;

public class _173_BSTIterator {

    private final Stack<TreeNode> stack;

    public _173_BSTIterator(TreeNode root) {
        stack = new Stack<>();
        putLeftNodes(root);
    }

    public int next() {
        TreeNode top = stack.pop();
        putLeftNodes(top.right);
        return top.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void putLeftNodes(TreeNode curr) {

        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }
}
