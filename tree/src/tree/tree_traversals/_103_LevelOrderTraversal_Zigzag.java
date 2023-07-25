package tree.tree_traversals;

import tree.TreeNode;

import java.util.*;

/**
 * https://practice.geeksforgeeks.org/problems/zigzag-tree-traversal/1
 * https://practice.geeksforgeeks.org/problems/level-order-traversal-in-spiral-form/1
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
 * <p>
 * https://workat.tech/problem-solving/practice/zigzag-level-order-traversal/editorial (Last java solution. - usage of stream)
 * https://www.youtube.com/watch?v=3OXWEdlIGl4&ab_channel=takeUforward (Striver)
 */
public class _103_LevelOrderTraversal_Zigzag {

    /**
     * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/discuss/559036/Simple-java-solution-0ms100-Faster-using-queue-bfs
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> zigzag = new ArrayList<>();

        if (root == null) {
            return zigzag;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // reverse = false if we have to reverse first and third column. (odd columns) -- LC version
        // reverse = true if we have to reverse second and fourth column and so on (even columns) -- GFG version
        boolean reverse = false;

        while (!queue.isEmpty()) {

            int size = queue.size();
            LinkedList<Integer> levelNodes = new LinkedList<>();

            for (int i = 0; i < size; i++) {

                TreeNode currNode = queue.poll();

                if (reverse) {
                    levelNodes.addFirst(currNode.val);
                } else {
                    levelNodes.add(currNode.val);
                }

                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }
            reverse = !reverse;

            zigzag.add(levelNodes);
        }

        return zigzag;
    }

    /**
     * time complexity  : O(n)
     * space complexity : O(n), extra stack is being used.
     */
    public List<List<Integer>> zigzagLevelOrder_usingExtraStack(TreeNode root) {

        List<List<Integer>> zigzag = new ArrayList<>();

        if (root == null) {
            return zigzag;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        Stack<Integer> stack = new Stack<>();
        boolean reverse = false;

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> levelNodes = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode currNode = queue.poll();

                if (reverse) {
                    stack.push(currNode.val);
                } else {
                    levelNodes.add(currNode.val);
                }

                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }
            reverse = !reverse;

            while (!stack.isEmpty()) {
                levelNodes.add(stack.pop());
            }

            zigzag.add(levelNodes);
        }

        return zigzag;
    }
}
