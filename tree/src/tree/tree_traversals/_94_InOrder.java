package tree.tree_traversals;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * My old solution :
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/1.%20Basic/src/Tree_BinaryTreeInOrderTraversal.java
 * <p>
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * <p>
 * https://www.youtube.com/watch?v=Z_NEgBgbRVI (Striver : Recursive)
 * <p>
 * (Almost Same logic is written in my notebook)
 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45621/preorder-inorder-and-postorder-traversal-iterative-java-solution
 * <p>
 * https://leetcode.com/problems/binary-tree-inorder-traversal/discuss/31313/three-ways-of-iterative-inorder-traversing-easy-explanation
 */
public class _94_InOrder {

    /**
     * iterative solution approach :
     * <p>
     * PreOrder  : preorder and postorder iterative solution are almost same.
     * ---- linkedList.add(root.val); // adds element at the end
     * ---- stack.push(root.right);
     * ---- stack.push(root.left);
     * <p>
     * PostOrder :
     * ----- linkedList.addFirst(root.val); // adds element at the front.
     * ----- stack.push(root.left);
     * ----- stack.push(root.right);
     * <p>
     * InOrder   :
     * ----- add element to the stack and go left until it is null.
     * ----- when null, pop from stack, print and go right
     */
    private static List<Integer> inOrderIterative(TreeNode root) {

        List<Integer> list = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {

            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }

    private static List<Integer> inOrderTraversal(TreeNode root) {

        List<Integer> list = new LinkedList<>();
        inOrder(root, list);
        return list;
    }

    private static void inOrder(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }

        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}
