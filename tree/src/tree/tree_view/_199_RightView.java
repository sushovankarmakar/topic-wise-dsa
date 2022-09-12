package tree.tree_view;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Tree_RightViewOfBinaryTree.java
 * <p>
 * https://practice.geeksforgeeks.org/problems/right-view-of-binary-tree/1
 * https://leetcode.com/problems/binary-tree-right-side-view/
 * <p>
 * https://leetcode.com/problems/binary-tree-right-side-view/discuss/56076/Reverse-Level-Order-Traversal-java
 */
public class _199_RightView {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("1 3 2");
        System.out.println(rightView_usingRecursion(root1));    // 1, 2
    }

    /**
     * Using Recursion
     * <p>
     * time     : O(n)
     * space    : O(h) where h is height of the tree.
     */
    private static List<Integer> rightViewNodes;

    private static List<Integer> rightView_usingRecursion(TreeNode root) {

        rightViewNodes = new ArrayList<>();
        traverse(root, 0);
        return rightViewNodes;
    }

    private static void traverse(TreeNode root, int currLevel) {
        if (root == null) return;

        if (currLevel == rightViewNodes.size()) {
            rightViewNodes.add(root.val);
        }

        traverse(root.right, currLevel + 1);
        traverse(root.left, currLevel + 1);
    }

    /**
     * Level order traversal, left view and right view has the same structure.
     * Follow this structure to remember all three easily.
     * <p>
     * time     : O(n)
     * space    : O(n)
     */
    private ArrayList<Integer> rightView_usingLevelOrder(TreeNode root) {

        ArrayList<Integer> values = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {

            int nodesInCurrentLevel = queue.size();

            for (int i = 0; i < nodesInCurrentLevel; i++) {

                TreeNode currNode = queue.remove();

                if (i == 0) {
                    values.add(currNode.val);
                }

                if (currNode.right != null) {
                    queue.add(currNode.right); // right child will be stored first for right view
                }
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
            }
        }
        return values;
    }
}
