package tree.linkChildWithParentNode;

import tree.TreeNode;

import java.util.*;

/**
 * https://practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1/
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * <p>
 * https://practice.geeksforgeeks.org/problems/burning-tree/1 (Similar problem)
 * https://www.interviewbit.com/problems/burn-a-tree/
 * <p>
 * https://www.youtube.com/watch?v=i9ORlEy6EsI&t=337s&ab_channel=takeUforward (BEST Explanation)
 * <p>
 * I was asked this question in Druva and I couldn't answer it.
 */
public class _863_NodesDistanceKInBinaryTree {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("3 5 1 6 2 0 8 N N 7 4");
        List<Integer> nodesAtDistanceK = getKDistanceTreeNodes(root1, 5, 2);
        System.out.println(nodesAtDistanceK);
    }


    private static Map<TreeNode, TreeNode> childParentMap = new HashMap<>();

    /**
     * IMPORTANT RULE :
     * if we need to find the tree's parent or ancestor, we always have to store them in child parent map first.
     */
    public static List<Integer> getKDistanceTreeNodes(TreeNode root, int target, int k) {

        if (root == null) {
            return new ArrayList<>();
        }

        childParentMap.put(root, null);
        //linkChildWithParent_DFS(root);
        linkChildWithParent_BFS(root);

        /*for (Map.Entry<TreeNode, TreeNode> entry : childParentMap.entrySet()) {

            if (entry.getKey() != null && entry.getValue() != null) {
                System.out.println(entry.getKey().val + " " + entry.getValue().val);
            }
        }*/

        TreeNode targetNode = findTargetNode(root, target);
        //System.out.println("TARGET : " + targetNode.data);

        return findNodesInKDistance(targetNode, k);
    }

    // storing child and parent in the map
    private static void linkChildWithParent_DFS(TreeNode root) {

        if (root == null) {
            return;
        }

        if (root.left != null) {
            childParentMap.put(root.left, root);
            linkChildWithParent_DFS(root.left);
        }

        if (root.right != null) {
            childParentMap.put(root.right, root);
            linkChildWithParent_DFS(root.right);
        }
    }

    private static void linkChildWithParent_BFS(TreeNode root) {

        childParentMap.put(root, null);

        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) queue.add(root);

        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode currNode = queue.poll();

                TreeNode left = currNode.left;
                if (left != null) {
                    childParentMap.put(left, currNode);
                    queue.add(left);
                }

                TreeNode right = currNode.right;
                if (right != null) {
                    childParentMap.put(right, currNode);
                    queue.add(right);
                }
            }
        }
    }

    private static TreeNode findTargetNode(TreeNode root, int target) {

        if (root == null || root.val == target) {
            return root;
        }

        TreeNode targetNode = findTargetNode(root.left, target);

        if (targetNode != null) {
            return targetNode;
        }
        return findTargetNode(root.right, target);
    }

    private static List<Integer> findNodesInKDistance(TreeNode targetNode, int k) {

        int distance = 0;
        Set<TreeNode> isVisited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();

        if (targetNode != null) {
            isVisited.add(targetNode);
            queue.add(targetNode);
        }

        while (!queue.isEmpty() && distance != k) {

            distance += 1;

            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currNode = queue.poll();

                TreeNode left = currNode.left;
                addNode(left, isVisited, queue);

                TreeNode right = currNode.right;
                addNode(right, isVisited, queue);

                TreeNode parent = childParentMap.get(currNode);
                addNode(parent, isVisited, queue);
            }
        }

        List<Integer> nodesAtDistanceK = new ArrayList<>();
        while (!queue.isEmpty()) {
            nodesAtDistanceK.add(queue.poll().val);
        }
        Collections.sort(nodesAtDistanceK);
        return nodesAtDistanceK;
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
