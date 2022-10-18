package tree;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * <p>
 * https://www.youtube.com/watch?v=u-yWemKGWO0 (Striver : Best Explanation)
 */
public class _222_CountCompleteTreeNodes {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("1 2 3 4 5 6");
        System.out.println(countNodes(root1));   // 6

        TreeNode root2 = TreeNode.buildTree("");
        System.out.println(countNodes(root2));   // 0

        TreeNode root3 = TreeNode.buildTree("1");
        System.out.println(countNodes(root3));   // 1
    }

    private static int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = findLeftHeight(root);
        int rightHeight = findRightHeight(root);

        // if left and right height is equal, that means tree is complete and
        // hence go for 2^h - 1;
        if (leftHeight == rightHeight) {
            return (int) Math.pow(2, leftHeight) - 1;
        }

        // else recursively calculate the number of nodes in left and right and add 1 for root.
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int findLeftHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + findLeftHeight(root.left);
    }

    private static int findRightHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + findRightHeight(root.right);
    }
}
