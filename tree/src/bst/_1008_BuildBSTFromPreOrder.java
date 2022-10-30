package bst;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * https://practice.geeksforgeeks.org/problems/preorder-to-postorder4423/1
 * <p>
 * https://www.youtube.com/watch?v=UmJT3j26t1I (Striver : explains all 3 methods.)
 */
public class _1008_BuildBSTFromPreOrder {

    /**
     * BEST OPTIMAL METHOD.
     */
    private int preOrderIndex = 0;

    public TreeNode bstFromPreorder(int[] preOrder) {

        return buildBST(preOrder, Integer.MAX_VALUE);
    }

    /**
     * at max, we can visit a node 3 times.
     * so time complexity : O(n)
     * space complexity : O(n) - recursion space only.
     */
    private TreeNode buildBST(int[] preOrder, int upperBound) {

        if (preOrderIndex >= preOrder.length || preOrder[preOrderIndex] > upperBound) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preOrderIndex++]);

        /**
         * if we want to go left, go with upper bound as current node's value.
         * if we want to go right, go with the same upper bound we got from above.
         */
        root.left = buildBST(preOrder, root.val);
        root.right = buildBST(preOrder, upperBound);

        return root;
    }


    // --------------------------------------------------------------------------------------------------------
    // 2nd best method.
    /**
     * preorder is given.
     * sort it and get the inorder.
     * now using preorder and inorder, build the tree.
     */
    private static int preOrderIdx = 0;
    private static final Map<Integer, Integer> inOrderValueIndexMap = new HashMap<>();

    public static TreeNode post_order(int pre[], int size) {

        int[] preOrder = Arrays.copyOf(pre, pre.length);
        Arrays.sort(pre);   // after the sorting, the preorder becomes inorder.
        int[] inOrder = pre;

        for (int i = 0; i < inOrder.length; i++) {
            inOrderValueIndexMap.put(inOrder[i], i);
        }

        TreeNode root = buildBST(preOrder, inOrder, 0, inOrder.length - 1);

        preOrderIdx = 0;  // resetting things for next round.

        return root;
    }

    private static TreeNode buildBST(int[] preOrder, int[] inOrder, int inOrderStartIndex, int inOrderEndIndex) {

        if (inOrderStartIndex > inOrderEndIndex) return null;

        TreeNode root = new TreeNode(preOrder[preOrderIdx]);
        preOrderIdx++;

        if (inOrderStartIndex == inOrderEndIndex) return root;

        int inOrderRootIndex = inOrderValueIndexMap.get(root.val);

        root.left = buildBST(preOrder, inOrder, inOrderStartIndex, inOrderRootIndex - 1);
        root.right = buildBST(preOrder, inOrder, inOrderRootIndex + 1, inOrderEndIndex);

        return root;
    }
}
