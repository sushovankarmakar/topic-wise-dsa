package tree.linkChildWithParentNode;

import tree.TreeNode;

import java.util.*;

/**
 * <p>
 * https://practice.geeksforgeeks.org/problems/burning-tree/1
 * https://www.interviewbit.com/problems/burn-a-tree/
 * <p>
 * https://practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1/ (Similar problem)
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=2r5wLmQfD6g (Striver : BEST Explanation)
 * https://www.youtube.com/watch?v=i9ORlEy6EsI&t=337s&ab_channel=takeUforward
 */
public class MinimumTimeToBurnBinaryTree {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("3 5 1 6 2 0 8 N N 7 4");
        System.out.println(minTime(root1, 5));  // 3
    }

    private static Map<TreeNode, TreeNode> childParentMap = new HashMap<>();

    /**
     * 1. Mark each node to its parent to traverse upwards in a binary tree
     * 2. We will do a BFS traversal from our starting node.
     * 3. Traverse up, left, right until 1 radial level (adjacent nodes) are burned and increment our timer
     */
    private static int minTime(TreeNode root, int target) {
        buildChildParentMap(root);
        TreeNode targetTreeNode = findTargetTreeNode(root, target);

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (targetTreeNode != null) {
            queue.add(targetTreeNode);
            visited.add(targetTreeNode);
        }

        int timeToBurn = 0;

        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {

                TreeNode currTreeNode = queue.poll();

                TreeNode left = currTreeNode.left;
                addTreeNode(left, queue, visited);

                TreeNode right = currTreeNode.right;
                addTreeNode(right, queue, visited);

                TreeNode parent = childParentMap.get(currTreeNode);
                addTreeNode(parent, queue, visited);
            }
            timeToBurn++;
        }
        return timeToBurn - 1;
    }

    private static void buildChildParentMap(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            childParentMap.put(root, null);
        }

        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode currTreeNode = queue.poll();

                TreeNode left = currTreeNode.left;
                if (left != null) {
                    queue.add(left);
                    childParentMap.put(left, currTreeNode);
                }

                TreeNode right = currTreeNode.right;
                if (right != null) {
                    queue.add(right);
                    childParentMap.put(right, currTreeNode);
                }
            }
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

    private static void addTreeNode(TreeNode TreeNode, Queue<TreeNode> queue, Set<TreeNode> visited) {

        if (TreeNode != null && !visited.contains(TreeNode)) {
            queue.add(TreeNode);
            visited.add(TreeNode);
        }
    }
}
