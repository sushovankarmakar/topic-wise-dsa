package tree.tree_traversals;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static tree.TreeNode.buildTree;

/**
 * https://leetcode.com/problems/boundary-of-binary-tree/
 * https://practice.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
 * <p>
 * https://www.youtube.com/watch?v=0ca1nvR0be4 (Striver : Best Video to understand)
 */
public class _545_BoundaryTraversal {

    /**
     * Tree Visualizer :
     * https://generate-tree-from-gfg.vercel.app/
     */
    public static void main(String[] args) {

        TreeNode root1 = buildTree("1 2 3");    // [1, 2, 3]
        System.out.println(boundaryOfBinaryTree(root1));

        TreeNode root2 = buildTree("17 N 1 N 9 N 2 N 11 N 16 N 8 N 5 N 3 N 10 N 13 N 5 N 13 N 7 N 10 N 4 N 7 N 2 N 12 N 16");
        System.out.println(boundaryOfBinaryTree(root2)); // [17, 16, 12, 2, 7, 4, 10, 7, 13, 5, 13, 10, 3, 5, 8, 16, 11, 2, 9, 1]

        TreeNode root3 = buildTree("1 2 N 4 9 6 5 N 3 N N N N 7 8");
        System.out.println(boundaryOfBinaryTree(root3)); // [1, 2, 4, 6, 5, 7, 8]

    }

    private static List<Integer> boundaryNodes;

    /**
     * LOGIC :
     * 1. add root node.
     * 2. add left boundary nodes. (exclude leaf node)
     * 3. traverse INORDER and add leaf nodes. --------------------------- (do NOT do LEVEL order traversal cause all leaf node may not be in same level)
     * 4. add right boundary nodes in reverse order. (exclude leaf node)
     */
    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        boundaryNodes = new ArrayList<>();

        if (root == null) return boundaryNodes;

        boundaryNodes.add(root.val);

        if (root.left != null) traverseLeftBoundary(root.left);
        if (!isLeafNode(root)) traverseLeafNodes(root);
        if (root.right != null) traverseRightBoundary(root.right);

        return boundaryNodes;
    }

    private static void traverseLeftBoundary(TreeNode node) {

        while (node != null && !isLeafNode(node)) {

            boundaryNodes.add(node.val);
            node = (node.left != null) ? node.left : node.right;
        }
    }

    private static void traverseLeafNodes(TreeNode node) {

        if (node == null) return;

        traverseLeafNodes(node.left);
        if (isLeafNode(node)) {
            boundaryNodes.add(node.val);
        }
        traverseLeafNodes(node.right);
    }

    private static void traverseRightBoundary(TreeNode node) {

        LinkedList<Integer> rightBoundaryNodes = new LinkedList<>();

        while (node != null && !isLeafNode(node)) {

            rightBoundaryNodes.addFirst(node.val);
            node = (node.right != null) ? node.right : node.left;
        }

        boundaryNodes.addAll(rightBoundaryNodes);
    }

    private static boolean isLeafNode(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
