package tree;

/**
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/
 * https://practice.geeksforgeeks.org/problems/minimum-depth-of-a-binary-tree/1#
 * <p>
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/discuss/36045/My-4-Line-java-solution/34277
 */

/**
 * DFS Key point:
 * if a node only has one child ->  MUST return the depth of the side with child, i.e. MAX(left, right) + 1
 * if a node has two children on both side -> return min depth of two sides, i.e. MIN(left, right) + 1
 */

public class MinDepthOfBinaryTree {

    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        if (root.left != null && root.right == null) {
            return minDepth(root.left) + 1;
        }

        if (root.left == null && root.right != null) {
            return minDepth(root.right) + 1;
        }

        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}


