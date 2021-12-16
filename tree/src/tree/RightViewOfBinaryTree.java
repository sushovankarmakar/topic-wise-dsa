package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/Tree_RightViewOfBinaryTree.java
 *
 * https://practice.geeksforgeeks.org/problems/right-view-of-binary-tree/1
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * https://leetcode.com/problems/binary-tree-right-side-view/discuss/56076/Reverse-Level-Order-Traversal-java
 */
public class RightViewOfBinaryTree {

  /**
   * Level order traversal, left view and right view has the same structure.
   * Follow this structure to remember all three easily.
   */
  private ArrayList<Integer> rightView(TreeNode root) {

    ArrayList<Integer> values = new ArrayList<>();
    if (root == null) return values;  // corner case

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    while (!queue.isEmpty()) {

      int nodesInCurrentLevel = queue.size();

      for (int i = 0; i < nodesInCurrentLevel; i++) {

        TreeNode currNode = queue.remove();

        if (i == 0) {
          values.add(currNode.val);
        }

        if (currNode.right  != null) queue.add(currNode.right); // right child will be stored first for right view
        if (currNode.left   != null) queue.add(currNode.left);
      }
    }
    return values;
  }
}
