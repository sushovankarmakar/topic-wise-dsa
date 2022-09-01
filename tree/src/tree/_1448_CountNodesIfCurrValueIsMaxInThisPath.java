package tree;

import static tree.TreeNode.buildTree;

/**
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/ (Solved by myself)
 * <p>
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/discuss/635258/JavaPython-3-Simple-recursive-and-iterative-DFS-codes-w-brief-explanation-and-analysis./839933 (Nice diagram)
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/discuss/635259/JavaC%2B%2BPython-One-line
 * https://leetcode.com/problems/count-good-nodes-in-binary-tree/discuss/635258/JavaPython-3-Simple-recursive-and-iterative-DFS-codes-w-brief-explanation-and-analysis.
 */
public class _1448_CountNodesIfCurrValueIsMaxInThisPath {

    public static void main(String[] args) {
        TreeNode root1 = buildTree("1 4 N 4 2 ");
        System.out.println(goodNodes(root1)); // 3

        TreeNode root2 = buildTree("6 3 2 N 1 2 N");
        System.out.println(goodNodes(root2)); // 1

        TreeNode root3 = buildTree("");
        System.out.println(goodNodes(root3)); // 0
    }

    private static int goodNodes(TreeNode root) {
        return countGoodNodes(root, Integer.MIN_VALUE);
    }

    private static int countGoodNodes(TreeNode root, int maxValue) {
        if (root == null) return 0;

        int count = 0;
        if (root.val >= maxValue) {
            maxValue = root.val;
            count = 1;
        }

        count += countGoodNodes(root.left, maxValue);
        count += countGoodNodes(root.right, maxValue);

        return count;
    }
}
