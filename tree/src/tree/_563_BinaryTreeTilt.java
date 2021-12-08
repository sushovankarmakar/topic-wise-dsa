package tree;

/**
 * https://leetcode.com/problems/binary-tree-tilt/
 *
 * https://leetcode.com/problems/binary-tree-tilt/solution/
 */

/**
 * to solve a tree problem in a recursion. 3 things to keep in mind.
 *
 * 1. the definition of recursion function (could be the same as the original one, or different)
 * 2. with the subtree returned results, what else you want to do for current level?
 * 3. what value to return to the upper level?
 *
 * 60% tree problem can be solved this way. After you have a very deep understanding of this, you can solve all such problem.
 */
public class _563_BinaryTreeTilt {

  /**
   * The overall idea is that
   * we traverse each node, and
   * calculate the tilt value for each node.
   * At the end, we sum up all the tilt values, which is the desired result of the problem.
   */
  /**
   * More specifically, we will traverse the tree in the post-order DFS,
   * i.e. we visit a node's left and right subtrees
   * before processing the value of the current node.
   */
  private int tiltValue = 0;

  public int findTilt(TreeNode root) {
    findSum(root);

    return tiltValue;
  }

  private int findSum(TreeNode root) {
    if (root == null) return 0;

    int leftSum = findSum(root.left);   // find left sub-tree's sum
    int rightSum = findSum(root.right); // find right sub-tree's sum

    tiltValue += Math.abs(leftSum - rightSum);  // update the tilt value.

    // return the sum of values starting from this node.
    return leftSum + rightSum + root.val;
  }

}
