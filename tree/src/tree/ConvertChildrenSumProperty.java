package tree;

import static tree.TreeNode.printLevelOrder;

/**
 * https://www.youtube.com/watch?v=fnmisPM6cVo
 * https://takeuforward.org/data-structure/check-for-children-sum-property-in-a-binary-tree/
 * https://www.codingninjas.com/codestudio/problems/childrensumproperty_790723
 * <p>
 * Write a program that converts any binary tree to one that follows the children sum property.
 */
public class ConvertChildrenSumProperty {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("50 7 2 3 5 1 30");
        convertTree(root1);
        printLevelOrder(root1);
        System.out.println();

        TreeNode root2 = TreeNode.buildTree("40 10 20 2 5 30 40");
        convertTree(root2);
        printLevelOrder(root2);
        System.out.println();

        TreeNode root3 = TreeNode.buildTree("2 35 10 2 3 5 2");
        convertTree(root3);
        printLevelOrder(root3);
    }

    private static void convertTree(TreeNode root) {
        reOrder(root);
    }

    /**
     * 1. if both children sum is less than parent, make children's value to parent's value.
     * 2. if both children values sum is greater than or equal to parent, make parent's value to children's sum.
     * 3. recursively go left and right. Traversal type: DFS.
     * 4. when coming back up the tree, take children sum and replace it in parent.
     * 5. at any point we reach null, just return (base case)
     * <p>
     * 6. Intuition: while going down, increase the children values, so we make sure to never fall short,
     * then all we have to do is sum both children and replace it in parent.
     */
    private static void reOrder(TreeNode root) {
        if (root == null) return;

        int childrenSum = 0;

        if (root.left != null) {
            childrenSum += root.left.val;
        }
        if (root.right != null) {
            childrenSum += root.right.val;
        }

        if (childrenSum < root.val) {   // 1st point.
            if (root.left != null) root.left.val = root.val;
            if (root.right != null) root.right.val = root.val;
        } else {                        // 2nd point.
            root.val = childrenSum;
        }

        reOrder(root.left);     // 3rd point.
        reOrder(root.right);

        int total = 0;
        if (root.left != null) total += root.left.val;
        if (root.right != null) total += root.right.val;
        if (root.left != null && root.right != null) {
            root.val = total;  // 4th point.   when backtracked, re-update the root's value.
        }
    }
}
