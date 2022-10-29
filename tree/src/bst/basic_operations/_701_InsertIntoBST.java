package bst.basic_operations;

import bst.TreeNode;

/**
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/
 * https://practice.geeksforgeeks.org/problems/insert-a-node-in-a-bst/1
 * https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/
 * <p>
 * https://www.youtube.com/watch?v=FiFiNvM29ps (Striver)
 *
 * (A good solution article. Well described.)
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/discuss/1683942/Well-Detailed-Explaination-Java-C%2B%2B-Python-oror-Easy-for-mind-to-Accept-it
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/discuss/150757/java-iterative-100
 */
public class _701_InsertIntoBST {

    /**
     * [5,null,14,10,77,null,null,null,95,null,null]
     * 4
     * - this is the corner case which I failed.
     *      5
     *       \
     *       14
     *       /  \
     *       10  77
     *            \
     *             95
     */
    private static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        TreeNode curr = root;
        while (true) {

            if (val < curr.val) {   // given value is less than current node value, go to left subtree

                if (curr.left != null) {
                    curr = curr.left;   // explore left subtree.

                } else {    // here is a space left, so now insert the new node here.
                    curr.left = new TreeNode(val);
                    break;
                }
            } else {    // given value is bigger than current node value, go to right subtree

                if (curr.right != null) {
                    curr = curr.right;  // explore right subtree.

                } else {    // here is a space left, so now insert the new node here.
                    curr.right = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
}
