package bst;

/**
 *
 * https://www.youtube.com/watch?v=ZWGW7FminDM (Striver : Best explanation)
 *
 * https://leetcode.com/problems/recover-binary-search-tree/
 * https://practice.geeksforgeeks.org/problems/6c4053871794c5e7a0817d7eaf88d71c4bb4c2bc/1
 */
public class _99_RecoverBST {

    private TreeNode first;
    private TreeNode mid;
    private TreeNode last;
    private TreeNode prev;

    private void inOrder(TreeNode curr) {
        if (curr == null) return;

        inOrder(curr.left);

        if (prev != null && (prev.val > curr.val)) {

            // if this is the first violation, then mark these two nodes
            // as 'first' and 'mid'
            if (first == null) {

                first = prev;
                mid = curr;

            } else {
                // if this is the second violation, then mark this node
                // as 'last' node.
                last = curr;
            }
        }

        // mark the current node as previous
        prev = curr;

        inOrder(curr.right);
    }

    /**
     * time complexity : O(n)
     * space complexity : O(1)
     */
    public void recoverTree(TreeNode root) {

        first = mid = last = null;
        prev = new TreeNode(Integer.MIN_VALUE);

        inOrder(root);

        if (first != null && last != null) {

            // two violations found. previously swapped nodes were not adjacent.
            swap(first, last);

        } else if (first != null && mid != null) {

            // single violation found. previously swapped nodes were adjacent.
            swap(first, mid);
        }
    }

    private void swap(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
}
