package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://practice.geeksforgeeks.org/problems/reverse-level-order-traversal/1/
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class _107_LevelOrderTraversal_BottomUp {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {

        List<List<Integer>> levelOrderBottomUp = new LinkedList<>();
        if (root == null) {
            return levelOrderBottomUp;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = queue.poll();
                level.add(currNode.val);

                if (currNode.left != null) {
                    queue.add(currNode.left);
                }

                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }

            // Only difference :
            // in top-down level order, we store values at the end. but
            // in bottom-up level order, we store values at the front. that's why we're storing values in 0th index always.

            levelOrderBottomUp.add(0, level);
        }
        return levelOrderBottomUp;
    }

}
