package tree.tree_traversals;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static tree.TreeNode.buildTree;

/**
 * https://practice.geeksforgeeks.org/problems/level-order-traversal/1
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * <p>
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Tree_LevelOrderTraversal.java
 * <p>
 * https://www.youtube.com/watch?v=EoAsWbO7sqg (Striver)
 * <p>
 * Similar question
 * https://leetcode.com/problems/average-of-levels-in-binary-tree/
 */
public class _102_LevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode root1 = buildTree("1 4 N 4 2 ");
        System.out.println(levelOrder(root1)); // [[1], [4], [4, 2]]

        TreeNode root2 = buildTree("3 9 20 N N 15 7");
        System.out.println(levelOrder(root2)); // [[3], [9, 20], [15, 7]]
    }

    /**
     * Level order traversal, left view and right view has the same structure.
     * Follow this structure to remember all three easily.
     */
    private static List<List<Integer>> levelOrder(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) {
            queue.add(root);
        }

        List<List<Integer>> levelOrderTraversal = new ArrayList<>();

        while (!queue.isEmpty()) {

            int nodesInCurrentLevel = queue.size();

            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < nodesInCurrentLevel; i++) {

                TreeNode currNode = queue.remove();
                level.add(currNode.val);

                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
            levelOrderTraversal.add(level);
        }
        return levelOrderTraversal;
    }
}
