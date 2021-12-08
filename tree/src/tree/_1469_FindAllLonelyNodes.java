package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/print-all-nodes-that-dont-have-sibling/1
 *
 * https://leetcode.ca/all/1469.html
 * https://leetcode.ca/2019-12-08-1469-Find-All-The-Lonely-Nodes/
 *
 * https://walkccc.me/LeetCode/problems/1469/   (A very beautiful solution)
 */
public class _1469_FindAllLonelyNodes {

  // SOLUTION - 1
  public List<Integer> noSibling(TreeNode root) {
    List<Integer> nodes = new ArrayList<>();
    find(root, nodes);

    if (nodes.isEmpty()) {
      nodes.add(-1);
    }

    Collections.sort(nodes);  // needed to return sorted list.
    return nodes;
  }

  private void find(TreeNode root, List<Integer> nodes) {
    if (root == null) {
      return;
    }

    // In a binary tree, a lonely node is a node that is the only child of its parent node
    if (root.left != null && root.right == null) {
      nodes.add(root.left.val);
    }
    if (root.left == null && root.right != null) {
      nodes.add(root.right.val);
    }

    find(root.left, nodes);
    find(root.right, nodes);
  }

  // https://walkccc.me/LeetCode/problems/1469/   (A very beautiful solution)
  // SOLUTION - 2
  public List<Integer> getLonelyNodes(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    dfs(root, false, ans);
    return ans;
  }

  private void dfs(TreeNode root, boolean isLonely, List<Integer> ans) {
    if (root == null)
      return;
    if (isLonely)
      ans.add(root.val);

    dfs(root.left, root.right == null, ans);
    dfs(root.right, root.left == null, ans);
  }

}
