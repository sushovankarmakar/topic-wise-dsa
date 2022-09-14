package tree.inplace_change;

import tree.TreeNode;

import static tree.TreeNode.printInorder;

/**
 * https://practice.geeksforgeeks.org/problems/mirror-tree/1
 * https://www.interviewbit.com/problems/invert-the-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=Yx3EmeEYuxY (Coding Simplified)
 * https://www.youtube.com/watch?v=nKggNAiEpBE (Striver) - Similar Concept.
 */
public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("1 3 2 N N 5 4");
        mirror(root1);
        printInorder(root1);    // 4 2 5 1 3
    }

    private static void mirror(TreeNode node) {

        if (node == null) return;

        exchangeChildren(node);

        mirror(node.left);
        mirror(node.right);
    }

    private static void exchangeChildren(TreeNode node) {
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}
