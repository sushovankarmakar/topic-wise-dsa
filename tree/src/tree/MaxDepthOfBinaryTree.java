package tree;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * https://practice.geeksforgeeks.org/problems/height-of-binary-tree/1#
 * https://www.youtube.com/watch?v=aqLTbtWh40E&ab_channel=AdityaVerma
 */

public class MaxDepthOfBinaryTree {

  public int maxDepth(TreeNode root) {
    if (root == null) return 0;

    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
  }

}
