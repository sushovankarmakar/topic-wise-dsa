package bst.smallest_largest;

import bst.TreeNode;

import java.util.ArrayList;
import java.util.List;

import static bst.TreeNode.buildTree;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * https://practice.geeksforgeeks.org/problems/sum-of-k-smallest-elements-in-bst3029/1
 * <p>
 * https://www.youtube.com/watch?v=9TJYWh0adfk (Striver)
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/discuss/63783/Two-Easiest-In-Order-Traverse-(Java)
 */
public class _230_KthSmallestElementInBST {

    public static void main(String[] args) {
        TreeNode root1 = buildTree("20 8 22 4 12 N N N N 10 14");
        getKthSmallest_1(root1, 2);
        System.out.println("K th smallest element is : " + kthSmallestElement); // 8

        count = 0;
        TreeNode root2 = buildTree("10 5 11 4 7 N N N N N 8");
        getKthSmallest_1(root2, 4);
        System.out.println("K th smallest element is : " + kthSmallestElement); // 8

        count = 0;
        TreeNode root3 = buildTree("5 3 4 1 4 6 8 N 2");
        getKthSmallest_1(root3, 3);
        System.out.println("K th smallest element is : " + kthSmallestElement); // 3

        TreeNode root4 = buildTree("5 3 4 1 4 6 8 N 2");
        System.out.println("K th smallest element is : " + getKthSmallest_2(root4, 3)); // 3
    }

    /**
     * SINGLE TRAVERSAL
     * Using no extra space.
     */
    private static int count = 0;
    private static int kthSmallestElement = 0;

    private static void getKthSmallest_1(TreeNode root, int k) {
        inOrder_1(root, k);
    }

    private static void inOrder_1(TreeNode root, int k) {

        if (root == null) return;

        inOrder_1(root.left, k);

        count++;
        if (count == k) {   // IMPORTANT : it is important to place this condition just after count++;
            kthSmallestElement = root.val;
            return;
        }

        inOrder_1(root.right, k);
    }

    // ----------------------------------------------------------------------------------------------------
    /**
     * SINGLE TRAVERSAL
     * Using extra O(N) space.
     */
    private static List<Integer> list;

    private static int getKthSmallest_2(TreeNode root, int k) {
        /**
         * we don't need to sort the list later,
         * cause arraylist maintains insertion order,
         * and we are inserting in ascending order.
         */
        list = new ArrayList<>();
        inOrder_2(root);
        return list.get(k - 1);
    }

    private static void inOrder_2(TreeNode root) {
        if (root == null) return;

        inOrder_2(root.left);
        list.add(root.val);
        inOrder_2(root.right);
    }
}
