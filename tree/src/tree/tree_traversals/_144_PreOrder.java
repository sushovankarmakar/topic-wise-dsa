package tree.tree_traversals;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static tree.TreeNode.buildTree;

/**
 * My old solution :
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/1.%20Basic/src/Tree_BinaryTreePreOrderTraversal.java
 * <p>
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * https://practice.geeksforgeeks.org/problems/preorder-traversal/1
 * https://practice.geeksforgeeks.org/problems/preorder-traversal-iterative/1
 * <p>
 * https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 * https://takeuforward.org/data-structure/preorder-traversal-of-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=RlUu72JrOCQ (Striver : Recursive)
 * https://www.youtube.com/watch?v=Bfqd8BsPVuw (Striver : Iterative)
 * <p>
 * (Almost Same logic is written in my notebook)
 * https://leetcode.com/problems/binary-tree-postorder-traversal/discuss/45621/preorder-inorder-and-postorder-traversal-iterative-java-solution
 * <p>
 * https://leetcode.com/problems/binary-tree-preorder-traversal/discuss/45312/three-ways-of-iterative-preorder-traversing-easy-explanation
 */
public class _144_PreOrder { // root - left - right

    public static void main(String[] args) {
        TreeNode root1 = buildTree("1 4 N 4 2 ");
        System.out.println(preOrderRecursive(root1)); // 1 4 4 2

        TreeNode root2 = buildTree("6 3 2 N 1 2 N");
        System.out.println(preOrderIterative(root2)); // 6 3 1 2 2

        TreeNode root3 = buildTree("");
        System.out.println(preOrderIterative(root3)); //
    }

    private static List<Integer> list;

    static List<Integer> preOrderIterative(TreeNode root) {
        list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {

            root = stack.pop();
            list.add(root.val);

            /**
             * below two if conditions' sequence is very important.
             * we first put right node at stack and then left node.
             *
             * so when popping out we'll get left node first and then right node
             */
            if (root.right != null) {
                stack.push(root.right);
            }

            if (root.left != null) {
                stack.push(root.left);
            }
        }

        return list;
    }

    private static List<Integer> preOrderRecursive(TreeNode root) {

        list = new ArrayList<>();
        preOrderRecursive_help(root);
        return list;
    }

    private static void preOrderRecursive_help(TreeNode root) {

        if (root == null) {
            return;
        }

        list.add(root.val);
        preOrderRecursive_help(root.left);
        preOrderRecursive_help(root.right);
    }
}
