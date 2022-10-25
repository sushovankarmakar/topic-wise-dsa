package tree.morris_traversal;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Almost same code as preorder morris traversal.
 * Some changes :
 * 1. use LinkedList, not arraylist
 * 2. use addFirst(), not add().
 * 3. inside first while loop,
 * ------ i. use left pointer in place of right pointer
 * ------ ii. use right pointer in place of left pointer.
 */
public class PostOrderTraversal {

    private static List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> postOrder = new LinkedList<>();
        if (root == null) return postOrder;

        TreeNode curr = root;
        while (curr != null) {

            if (curr.right == null) {
                postOrder.addFirst(curr.val);
                curr = curr.left;
            } else {

                TreeNode prev = curr.right;
                while (prev.left != null && prev.left != curr) {
                    prev = prev.left;
                }

                if (prev.left == null) {
                    prev.left = curr;
                    postOrder.addFirst(curr.val);
                    curr = curr.right;
                } else {
                    prev.left = null;
                    curr = curr.left;
                }
            }
        }
        return postOrder;
    }
}
