package bst.smallest_largest;

import bst.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/kth-largest-element-in-bst/1
 * <p>
 * https://www.youtube.com/watch?v=9TJYWh0adfk
 */
public class KthLargestElementInBST {

    private List<Integer> list;

    public int kthLargest(TreeNode root, int k) {

        /**
         * we don't need to sort the list later,
         * cause arraylist maintains insertion order,
         * and we are inserting in ascending order.
         */
        list = new ArrayList<>();

        inOrder(root);

        int kthSmallest = list.size() - k;  // no need to do -1 cause k value was given in 1 based indexing.
        return list.get(kthSmallest);
    }

    private void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}
