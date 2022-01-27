package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/reverse-alternate-levels-of-a-perfect-binary-tree/1
 * <p>
 * (I took help from this link)
 * https://uploads.disquscdn.com/images/103cbbc02608a43e9fb1ed758642a8e5d9c24e6e3264d52eb301e3080d59e299.png
 */
public class _226_InvertBinaryTree_AlternateLevels {

  /**
   * Here we're not actually swapping nodes, we're swapping only the values.
   *  STEPS :
   *  -----
   * 1. Traverse just like level order traversal.
   * 2. if this is the alternate level, (IMPORTANT STEP)
   *     i.  first store values of this level nodes in a stack.
   *     ii. now if this is alternate level, pop value from stack and replace that with current node's value.
   * 3. if this is not the alternate level, do normal level order traversal only.
   */
  static void reverseAlternate(TreeNode root) {

    if (root == null) {
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    Stack<Integer> stack = new Stack<>();
    queue.add(root);

    boolean flip = false;

    while (!queue.isEmpty()) {

      int levelSize = queue.size();

      if (flip) { // step : 2.i
        storeValuesInReverseOrder(queue, stack, levelSize);
      }

      for (int i = 0; i < levelSize; i++) {
        TreeNode currNode = queue.poll();

        if (!stack.isEmpty()) {   // step : 2.ii
          currNode.val = stack.pop();
        }

        if (currNode.left != null) {
          queue.add(currNode.left);
        }

        if (currNode.right != null) {
          queue.add(currNode.right);
        }
      }
      flip = !flip;
    }
  }

  static void storeValuesInReverseOrder(Queue<TreeNode> queue, Stack<Integer> stack, int levelSize) {

    // 1. pull a node from queue.
    // 2. put this node's value into stack.
    // 3. add this node back into the queue.
    for (int i = 0; i < levelSize; i++) {
      TreeNode currNode = queue.poll();
      stack.push(currNode.val);
      queue.add(currNode);
    }
  }

}
