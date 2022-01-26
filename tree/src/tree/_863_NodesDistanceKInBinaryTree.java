package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1/
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=i9ORlEy6EsI&t=337s&ab_channel=takeUforward (BEST Explanation)
 * <p>
 * I was asked this question in Druva and I couldn't answer it.
 */
public class _863_NodesDistanceKInBinaryTree {

  public static List<Integer> KDistanceTreeNodes(TreeNode root, int target, int k) {

    if (root == null) {
      return new ArrayList<>();
    }

    Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
    childParentMap.put(root, null);
    linkChildWithParent(root, childParentMap);

    /*for (Map.Entry<TreeNode, TreeNode> entry : childParentMap.entrySet()) {

      if (entry.getKey() != null && entry.getValue() != null) {
        System.out.println(entry.getKey().val + " " + entry.getValue().val);
      }
    }*/

    TreeNode targetTreeNode = findTargetTreeNode(root, target);
    //System.out.println("TARGET : " + targetTreeNode.data);

    return findTreeNodesInKDistance(targetTreeNode, childParentMap, k);
  }

  // storing child and parent in the map
  private static void linkChildWithParent(TreeNode root, Map<TreeNode, TreeNode> childParentMap) {

    if (root == null) {
      return;
    }

    if (root.left != null) {
      childParentMap.put(root.left, root);
      linkChildWithParent(root.left, childParentMap);
    }

    if (root.right != null) {
      childParentMap.put(root.right, root);
      linkChildWithParent(root.right, childParentMap);
    }
  }

  private static TreeNode findTargetTreeNode(TreeNode root, int target) {

    if (root == null || root.val == target) {
      return root;
    }

    TreeNode targetTreeNode = findTargetTreeNode(root.left, target);

    if (targetTreeNode != null) {
      return targetTreeNode;
    }
    return findTargetTreeNode(root.right, target);
  }

  private static List<Integer> findTreeNodesInKDistance(TreeNode targetTreeNode,
      Map<TreeNode, TreeNode> childParentMap, int k) {

    int distance = 0;
    Set<TreeNode> isVisited = new HashSet<>();
    Queue<TreeNode> queue = new LinkedList<>();

    if (targetTreeNode != null) {
      isVisited.add(targetTreeNode);
      queue.add(targetTreeNode);
    }

    while (distance != k && !queue.isEmpty()) {

      distance += 1;

      int levelSize = queue.size();
      for (int i = 0; i < levelSize; i++) {
        TreeNode currNode = queue.remove();

        TreeNode left = currNode.left;
        addNode(left, isVisited, queue);

        TreeNode right = currNode.right;
        addNode(right, isVisited, queue);

        TreeNode parent = childParentMap.get(currNode);
        addNode(parent, isVisited, queue);
      }
    }

    List<Integer> nodesInKDistance = new ArrayList<>();
    while (!queue.isEmpty()) {
      nodesInKDistance.add(queue.remove().val);
    }
    Collections.sort(nodesInKDistance);
    return nodesInKDistance;
  }

  private static void addNode(TreeNode node, Set<TreeNode> isVisited, Queue<TreeNode> queue) {

    /**
     * if the node is not null and this node has not been visited before then add this node.
     */
    if (node != null && !isVisited.contains(node)) {
      isVisited.add(node);
      queue.add(node);
    }
  }
}
