package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Tree_LevelOrderTraversal.java
 *
 * https://practice.geeksforgeeks.org/problems/level-order-traversal/1
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class LevelOrderTraversal {

  /**
   * Level order traversal, left view and right view has the same structure.
   * Follow this structure to remember all three easily.
   */
  static ArrayList<Integer> levelOrder(TreeNode root) {

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    ArrayList<Integer> values = new ArrayList<>();

    while (!queue.isEmpty()) {

      int nodesInCurrentLevel = queue.size();

      for (int i = 0; i < nodesInCurrentLevel; i++) {

        TreeNode currNode = queue.remove();
        values.add(currNode.val);

        if (currNode.left != null) {
          queue.add(currNode.left);
        }
        if (currNode.right != null) {
          queue.add(currNode.right);
        }
      }
    }
    return values;
  }

}
