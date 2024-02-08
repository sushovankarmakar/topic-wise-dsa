package tree.linkChildWithParentNode;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/kth-ancestor-in-a-tree/1/
 * <p>
 * Very similar question :
 * https://practice.geeksforgeeks.org/problems/nodes-at-given-distance-in-binary-tree/1/
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */
public class KthAncestorInATree {

    /**
     * IMPORTANT RULE :
     * if we need to find the tree's parent or ancestor, we always have to store them in child parent map first.
     */

    public int kthAncestor(TreeNode root, int k, int target) {
        Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
        childParentMap.put(root, null);
        linkChildWithParent(root, childParentMap);

        TreeNode targetNode = findTargetNode(root, target);
        if (targetNode == null) {
            return -1;
        }

        return findKthAncestor(targetNode, childParentMap, k);
    }

    private void linkChildWithParent(TreeNode root, Map<TreeNode, TreeNode> childParentMap) {

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

    private TreeNode findTargetNode(TreeNode root, int target) {

        if (root == null || root.val == target) {
            return root;
        }

        TreeNode targetNode = findTargetNode(root.left, target);

        if (targetNode != null) {
            return targetNode;
        }

        return findTargetNode(root.right, target);
    }

    private int findKthAncestor(TreeNode target, Map<TreeNode, TreeNode> childParentMap, int k) {

        TreeNode ancestor = target;
        while (k-- > 0 && ancestor != null) {
            ancestor = childParentMap.get(ancestor);
        }
        return ancestor == null ? -1 : ancestor.val;
    }
}
