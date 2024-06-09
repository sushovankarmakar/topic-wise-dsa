package src.tree;

/**
 * This problem was asked by Google.
 * <p>
 * Given two non-empty binary trees s and t,
 * check whether tree t has exactly the same structure and node values with a subtree of s.
 * <p>
 * A subtree of s is a tree consists of a node in s and all of this node's descendants.
 * The tree s could also be considered as a subtree of itself.
 */

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 * https://www.youtube.com/watch?v=GZ8g8KdavUo&t=722s&ab_channel=NikhilLohia (A good explanation)
 */
public class _20240530_1411_IsSubtree {

    // https://chatgpt.com/share/a5533e88-b90b-4be0-8a23-2573474271fc

    /**
     * Time Complexity : O(N + M) = O(N) + O(N + M)
     *
     * O(N) = visiting each node in preorder time
     * O(N + M) = string matching algo.
     *
     * Space Complexity : O(N)
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {

        StringBuilder fullTree = new StringBuilder();
        preOrder(root, fullTree);

        StringBuilder subTree = new StringBuilder();
        preOrder(subRoot, subTree);

        return fullTree.toString().contains(subTree.toString()); // time complexity : O(N + M)
    }

    private static void preOrder(TreeNode root, StringBuilder sb) {

        /*
         * If the node is null, append 'N' to indicate a null node
         * This helps to preserve the structure of the tree in the string representation
         * 'N' acts as a placeholder for null nodes to distinguish between actual node values and missing nodes
         */
        if (root == null) {
            sb.append("N"); // IMPORTANT STEP to handle this test case : root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2], Output: false
            return;
        }

        /*
         * Add non-digit characters before and after the actual value to handle specific edge cases.
         *
         * Example edge case:
         * root : [12]
         * sub-root : [2]
         *
         * Without non-digit characters, '2' could be mistakenly matched as part of '12'.
         * By appending non-digit characters, we ensure '2' is correctly identified as a separate node.
         *
         * output : false (correctly indicates that [2] is not a subtree of [12])
         */
        sb.append("-").append(root.val).append("-");

        preOrder(root.left, sb);
        preOrder(root.right, sb);
    }


    public static void main(String[] args) {
        // Example usage:
        TreeNode s = new TreeNode(3);
        s.left = new TreeNode(4);
        s.right = new TreeNode(5);
        s.left.left = new TreeNode(1);
        s.left.right = new TreeNode(2);

        TreeNode t = new TreeNode(4);
        t.left = new TreeNode(1);
        t.right = new TreeNode(2);

        System.out.println(isSubtree(s, t)); // should print true
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
