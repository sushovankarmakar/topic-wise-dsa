package bst;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/
 * https://practice.geeksforgeeks.org/problems/predecessor-and-successor/1
 *
 * https://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/
 *
 * https://www.youtube.com/watch?v=SXKAD2svfmI (Striver)
 */
public class _285_InorderSuccessorPredecessorInBST {

    private static TreeNode inorderSuccessor(TreeNode root, int key) {

        TreeNode successor = null;
        while (root != null) {

            if (key < root.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return successor;
    }

    private static TreeNode inorderPredecessor(TreeNode root, int key) {

        TreeNode predecessor = null;
        while (root != null) {

            if (key <= root.val) {  // <= is important
                root = root.left;
            } else {
                predecessor = root;
                root = root.right;
            }
        }
        return predecessor;
    }
}
