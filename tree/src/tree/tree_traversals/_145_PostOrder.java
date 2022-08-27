package tree.tree_traversals;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * My old solution :
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/1.%20Basic/src/Tree_BinaryTreePostOrderTraversal.java
 * <p>
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * https://practice.geeksforgeeks.org/problems/postorder-traversal/1
 * https://practice.geeksforgeeks.org/problems/postorder-traversal-iterative/1
 * <p>
 * https://www.youtube.com/watch?v=COQOU6klsBg (Striver : Recursive)
 * <p>
 * (Almost Same logic is written in my notebook)
 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45621/preorder-inorder-and-postorder-traversal-iterative-java-solution
 * <p>
 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45648/3-ways-of-iterative-postorder-traversing-morris-traversal
 */
public class _145_PostOrder { // left - right - root

    private static List<Integer> postOrderIterative(TreeNode root) {

        LinkedList<Integer> list = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {

            root = stack.pop();
            list.addFirst(root.val);

            if (root.left != null) {
                stack.push(root.left);
            }

            if (root.right != null) {
                stack.push(root.right);
            }
        }
        return list;
    }

    private static List<Integer> postOrderRecursive(TreeNode root) {

        List<Integer> list = new LinkedList<>();
        postOrder(root, list);
        return list;
    }

    private static void postOrder(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        postOrder(root.left, list);
        postOrder(root.right, list);
        list.add(root.val);
    }
}
