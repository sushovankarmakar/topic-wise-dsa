package tree.morris_traversal;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=80Zug6D1_r4 (Striver : BEST)
 * <p>
 * In Moris traversal,
 * first read inorder, then preorder(line change from inorder) and then postorder.
 */
public class PreOrderTraversal {

    private static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preOrder = new LinkedList<>();
        if (root == null) return preOrder;

        TreeNode curr = root;
        while (curr != null) {

            if (curr.left == null) {
                preOrder.add(curr.val);
                curr = curr.right;
            } else {

                TreeNode prev = curr.left;
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }

                if (prev.right == null) {
                    prev.right = curr;
                    preOrder.add(curr.val);
                    curr = curr.left;
                } else {
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        return preOrder;
    }
}
