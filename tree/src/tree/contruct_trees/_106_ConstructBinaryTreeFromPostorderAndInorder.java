package tree.contruct_trees;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * https://practice.geeksforgeeks.org/problems/tree-from-postorder-and-inorder/1
 * <p>
 * https://www.youtube.com/watch?v=LgLRTaEMRVc (Striver : best)
 */
public class _106_ConstructBinaryTreeFromPostorderAndInorder {

    public static void main(String[] args) {
        int[] postOrder = new int[]{9, 15, 7, 20, 3};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = buildTree(postOrder, inOrder);
        TreeNode.printLevelOrder(root);    // 3 | 9 20 | 15 7
    }

    private static final Map<Integer, Integer> inOrderValueIndexMap = new HashMap<>();
    private static int postOrderRootIndex = 0;

    private static TreeNode buildTree(int[] postOrder, int[] inOrder) {

        for (int i = 0; i < inOrder.length; i++) {
            inOrderValueIndexMap.put(inOrder[i], i);
        }

        /**
         * we start from the end cause in postOrder, root resides at the end
         */
        postOrderRootIndex = postOrder.length - 1;

        return build(postOrder, inOrder, 0, inOrder.length - 1);
    }

    private static TreeNode build(int[] postOrder, int[] inOrder, int inOrderStartIndex, int inOrderEndIndex) {

        if (inOrderStartIndex > inOrderEndIndex) return null;

        TreeNode root = new TreeNode(postOrder[postOrderRootIndex]);
        postOrderRootIndex--;

        if (root == null || inOrderStartIndex == inOrderEndIndex) return root;

        int inOrderRootIndex = inOrderValueIndexMap.get(root.val);

        /**
         * In case of PreOrder and InOrder,
         * we will first construct left child and then right child
         *
         * In case of PostOrder and InOrder,
         * we will first construct right child and then left child
         */
        root.right = build(postOrder, inOrder, inOrderRootIndex + 1, inOrderEndIndex);
        root.left = build(postOrder, inOrder, inOrderStartIndex, inOrderRootIndex - 1);

        return root;
    }
}
