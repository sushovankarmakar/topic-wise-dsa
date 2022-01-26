package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://practice.geeksforgeeks.org/problems/mirror-tree/1
 * https://leetcode.com/problems/invert-binary-tree/
 */
public class _226_InvertBinaryTree {

  /**
   * Do BFS and each node, exchange it's left child with right child.
   */
  public TreeNode invertTree(TreeNode root) {

    if (root == null) return root;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {

      int levelSize = queue.size();

      for (int i = 0; i < levelSize; i++) {

        TreeNode currNode = queue.poll();

        if (currNode.left != null) {
          queue.add(currNode.left);
        }

        if (currNode.right != null) {
          queue.add(currNode.right);
        }

        TreeNode temp = currNode.left;
        currNode.left = currNode.right;
        currNode.right = temp;
      }
    }
    return root;
  }

}
