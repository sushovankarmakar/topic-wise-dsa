package tree.basics;

import tree.TreeNode;

import static tree.TreeNode.buildTree;

/**
 * https://practice.geeksforgeeks.org/problems/full-binary-tree/1
 * <p>
 * https://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/
 */
public class CheckFullBinaryTree {

    public static void main(String[] args) {
        TreeNode node1 = buildTree("1 2 3 4 5");
        System.out.println(isFullTree(node1));  // true

        TreeNode node2 = buildTree("1 2 3 4 ");
        System.out.println(isFullTree(node2));  // false
    }

    /**
     * LOGIC :
     * if between two children, only one is present, then return false.
     */
    private static boolean isFullTree(TreeNode node) {

        if (node == null) return true;

        // if between two children, only one is present then return false.
        if ((node.left != null && node.right == null) || (node.left == null && node.right != null)) {
            return false;
        }

        return isFullTree(node.left) && isFullTree(node.right);
    }
}
