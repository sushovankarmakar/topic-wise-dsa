package tree.basics;

import tree.TreeNode;

import static tree.TreeNode.buildTree;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * https://practice.geeksforgeeks.org/problems/height-of-binary-tree/1
 * <p>
 * https://www.youtube.com/watch?v=aqLTbtWh40E&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=eD3tmO66aBA (Striver)
 */

public class _104_MaxDepthOfBinaryTree {

    /**
     * time : O(N)
     * space : O(N)
     */
    private static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {
        TreeNode root1 = buildTree("1 4 N 4 2 ");
        System.out.println(maxDepth(root1)); // 3

        TreeNode root2 = buildTree("6 3 2 N 1 2 N");
        System.out.println(maxDepth(root2)); // 3

        TreeNode root3 = buildTree("");
        System.out.println(maxDepth(root3)); // 0
    }

}
