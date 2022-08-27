package tree.tree_traversals;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static tree.TreeNode.buildTree;

/**
 * EASIEST ITERATIVE TRAVERSAL
 * <p>
 * https://www.youtube.com/watch?v=ySp2epYvgTE (Striver)
 * https://www.geeksforgeeks.org/preorder-postorder-and-inorder-traversal-of-a-binary-tree-using-a-single-stack/
 * <p>
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 */
public class PreOrderInOrderPostOrderInOneTraversal {

    public static void main(String[] args) {
        TreeNode root1 = buildTree("1 4 N 4 2 ");
        allTraversal(root1);
        System.out.println(preOrder);   // [1, 4, 4, 2]
        System.out.println(inOrder);    // [4, 4, 2, 1]
        System.out.println(postOrder);  // [4, 2, 4, 1]
    }

    static List<Integer> preOrder;
    static List<Integer> inOrder;
    static List<Integer> postOrder;

    private static void allTraversal(TreeNode root) {

        preOrder = new ArrayList<>();
        inOrder = new ArrayList<>();
        postOrder = new ArrayList<>();

        Stack<Pair> stack = new Stack<>();

        if (root != null) {
            stack.push(new Pair(root, 1));
        }

        while (!stack.isEmpty()) {

            Pair pair = stack.pop();

            if (pair.num == 1) {
                preOrder.add(pair.node.val);
                pair.num++;

                stack.push(pair);

                if (pair.node.left != null) {
                    stack.push(new Pair(pair.node.left, 1));
                }

            } else if (pair.num == 2) {

                inOrder.add(pair.node.val);
                pair.num++;

                stack.push(pair);

                if (pair.node.right != null) {
                    stack.push(new Pair(pair.node.right, 1));
                }

            } else {
                postOrder.add(pair.node.val);
            }
        }
    }

    private static class Pair {
        TreeNode node;
        int num;

        Pair(TreeNode node, int num) {
            this.node = node;
            this.num = num;
        }
    }
}
