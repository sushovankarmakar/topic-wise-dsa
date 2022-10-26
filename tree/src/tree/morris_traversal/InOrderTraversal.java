package tree.morris_traversal;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=80Zug6D1_r4 (Striver : BEST)
 * (Best Presentation to understand the algorithm.)
 * https://docs.google.com/presentation/d/11GWAeUN0ckP7yjHrQkIB0WT9ZUhDBSa-WR0VsPU38fg/edit#slide=id.g61bfb572cf_0_33
 * <p>
 * https://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion (Best textual explanation)
 * <p>
 * (I took help from this code.)
 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45628/Morris-Traversal-Time-O(n)-Space-O(1)-inorder-preorder-postorder/895356
 * <p>
 * Helpful videos :
 * https://www.youtube.com/watch?v=wGXB9OWhPTg (Tushar Roy)
 * https://www.youtube.com/watch?v=sbf9zi6M6ds (Algorists)
 */
public class InOrderTraversal {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree("1 N 2 3");
        List<Integer> inOrder = inOrderTraversal(root);
        System.out.println(inOrder);
    }

    private static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> inOrder = new ArrayList<>();
        if (root == null) return inOrder;

        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                inOrder.add(curr.val);
                curr = curr.right;  // move to next right node

            } else {    // has a left subtree, so find the predecessor
                TreeNode predecessor = curr.left;
                while (predecessor.right != null && predecessor.right != curr) {  // going to the right most node of left subtree to find the predecessor.
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = curr;  // CREATING the thread between predecessor and current node.
                    curr = curr.left;   // after creating thread, we are moving to LEFT subtree.

                } else { // here predecessor.right == curr

                    predecessor.right = null;// BREAKING the thread between predecessor and current node.
                    inOrder.add(curr.val);   // printing current node.
                    curr = curr.right;       // after breaking thread, we are moving to RIGHT subtree.
                }
            }
        }
        return inOrder;
    }
}
