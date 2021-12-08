package tree;

/**
 * to solve a tree problem in a recursion. 3 things to keep in mind.
 *
 * 1. the definition of recursion function (could be the same as the original one, or different)
 * 2. with the subtree returned results, what else you want to do for current level?
 * 3. what value to return to the upper level?
 *
 * 60% tree problem can be solved this way. After you have a very deep understanding of this, you can solve all such problem.
 */

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode() {
  }

  TreeNode(int val) {
    this.val = val;
  }

  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
