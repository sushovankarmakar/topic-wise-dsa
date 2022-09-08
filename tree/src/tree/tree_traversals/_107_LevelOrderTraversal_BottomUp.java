package tree.tree_traversals;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 */
public class _107_LevelOrderTraversal_BottomUp {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<>();

        //Stack<List<Integer>> stack = new Stack<>();   // we can use stack also to store values in reverse order.
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < queueSize; i++) {
                TreeNode currNode = queue.poll();

                level.add(currNode.val);

                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }
            levels.add(0, level);
        }
        return levels;
    }
}
