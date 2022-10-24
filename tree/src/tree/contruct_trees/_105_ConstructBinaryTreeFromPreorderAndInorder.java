package tree.contruct_trees;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * https://practice.geeksforgeeks.org/problems/construct-tree-1/1
 * <p>
 * https://www.youtube.com/watch?v=aZNaLrVebKQ (Striver : best)
 */
public class _105_ConstructBinaryTreeFromPreorderAndInorder {

    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = buildTree(preOrder, inOrder);
        TreeNode.printLevelOrder(root);    // 3 | 9 20 | 15 7
    }

    private static final Map<Integer, Integer> inOrderValueIndexMap = new HashMap<>();
    private static int preOrderRootIndex = 0;

    private static TreeNode buildTree(int[] preOrder, int[] inOrder) {

        for (int i = 0; i < inOrder.length; i++) {
            inOrderValueIndexMap.put(inOrder[i], i);
        }

        return build(preOrder, inOrder, 0, inOrder.length - 1);
    }

    private static TreeNode build(int[] preOrder, int[] inOrder, int inOrderStartIndex, int inOrderEndIndex) {

        if (inOrderStartIndex > inOrderEndIndex) return null;

        TreeNode root = new TreeNode(preOrder[preOrderRootIndex]);
        preOrderRootIndex++;

        if (root == null || inOrderStartIndex == inOrderEndIndex) return root;

        int inOrderRootIndex = inOrderValueIndexMap.get(root.val);

        /**
         * In case of PreOrder and InOrder,
         * we will first construct left child and then right child
         *
         * In case of PostOrder and InOrder,
         * we will first construct right child and then left child
         */
        root.left = build(preOrder, inOrder, inOrderStartIndex, inOrderRootIndex - 1);
        root.right = build(preOrder, inOrder, inOrderRootIndex + 1, inOrderEndIndex);

        return root;
    }
}
