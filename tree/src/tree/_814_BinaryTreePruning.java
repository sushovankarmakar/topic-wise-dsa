package tree;

import static tree.TreeNode.buildTree;
import static tree.TreeNode.printInorder;

/**
 * https://leetcode.com/problems/binary-tree-pruning/ (Solved it by myself)
 * https://www.geeksforgeeks.org/trim-given-binary-tree-for-any-subtree-containing-only-0s/
 * <p>
 * Similar question :
 * https://leetcode.com/problems/delete-leaves-with-a-given-value/
 * <p>
 * https://www.youtube.com/watch?v=Do1erCrvxr0
 */
public class _814_BinaryTreePruning {

    public static void main(String[] args) {
        TreeNode root1 = buildTree("1 0 1 0 0 0 1");
        printInorder(pruneTree(root1)); // 1 1 1

        System.out.println();

        tree.TreeNode root2 = buildTree("1 N 0 0 1");
        printInorder(pruneTree(root2)); // 1 0 1
    }

    /**
     * LOGIC :
     * Simply do a POST-ORDER Traversal and
     * check if current Node is 0 && BOTH :- left and right side of current Node is Null or not.
     * If its Null then this current Node must be Pruned as well if not then return current Node
     */
    private static TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        return isPrunable(root) ? null : root;
    }

    private static boolean isPrunable(TreeNode node) {
        return node.val == 0 && node.left == null && node.right == null;
    }
}



