package tree;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * <p>
 * https://www.youtube.com/watch?v=zmPj_Ee3B8c&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go (Aditya Verma)
 */
public class _543_DiameterOfBinaryTree {

    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        diameter[0] = Integer.MIN_VALUE;

        solve(root, diameter);
        return diameter[0] - 1; // -1  because leetcode question asks 'number of edges between them'
    }

    private int solve(TreeNode node, int[] diameter) {

        if (node == null) return 0;

        int leftHeight = solve(node.left, diameter);
        int rightHeight = solve(node.right, diameter);

        int currMaxHeight = 1 + Math.max(leftHeight, rightHeight);
        int currDiameter = 1 + leftHeight + rightHeight;

        diameter[0] = Math.max(diameter[0], currDiameter);

        return currMaxHeight;
    }

}
