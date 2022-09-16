package tree.basics;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=ZbybYvcVLks (Striver : Best Explanation. With prevention of integer overflow)
 * <p>
 * https://www.geeksforgeeks.org/maximum-width-of-a-binary-tree-with-null-value/
 */
public class _662_MaximumWidthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode roo1 = TreeNode.buildTree("6 7 3 2 8 N 4 3 3 1 8 N 4");
        System.out.println(widthOfBinaryTree(roo1));    // 8
    }

    /**
     * Level Order Traversal.
     */
    private static int widthOfBinaryTree(TreeNode root) {

        Queue<Pair> queue = new LinkedList<>();
        if (root != null) queue.add(new Pair(root, 0));

        int maxWidth = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            int startingIndex = 0;
            int endingIndex = 0;
            int minIndex = queue.peek().index;  // minIndex is the minimum index of that level.

            for (int i = 0; i < levelSize; i++) {
                Pair pair = queue.poll();
                TreeNode node = pair.node;
                int index = pair.index - minIndex;  // minIndex is required to prevent integer overflow.

                if (i == 0) {
                    startingIndex = index;
                }
                if (i == (levelSize - 1)) {
                    endingIndex = index;
                }

                if (node.left != null) queue.add(new Pair(node.left, (index * 2) + 1));
                if (node.right != null) queue.add(new Pair(node.right, (index * 2) + 2));
            }

            int currLevelWidth = endingIndex - startingIndex + 1;
            maxWidth = Math.max(maxWidth, currLevelWidth);
        }
        return maxWidth;
    }

    static class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
