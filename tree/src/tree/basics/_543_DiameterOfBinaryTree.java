package tree.basics;

import tree.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * https://practice.geeksforgeeks.org/problems/diameter-of-binary-tree/1
 * <p>
 * https://www.youtube.com/watch?v=Rezetez59Nk (Striver) || O(n^2) solution -> O(N) solution
 * https://www.geeksforgeeks.org/diameter-of-a-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=zmPj_Ee3B8c&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go (Aditya Verma)
 */
public class _543_DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        diameter[0] = Integer.MIN_VALUE;

        solve(root, diameter);
        return diameter[0];
    }

    private int solve(TreeNode node, int[] diameter) {

        if (node == null) return 0;

        int leftHeight = solve(node.left, diameter);
        int rightHeight = solve(node.right, diameter);

        /**
         * if diameter = length of a path between two nodes is represented by the number of EDGES between them. (LEETCODE)
         *      then, leftHeight + rightHeight;
         *
         * if diameter = the number of NODES on the longest path between two end nodes (GFG)
         *      then, 1 + leftHeight + rightHeight;
         */
        int currDiameter = 1 + leftHeight + rightHeight;
        diameter[0] = Math.max(diameter[0], currDiameter);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
