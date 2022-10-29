package bst;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * https://practice.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1
 * <p>
 * https://www.youtube.com/watch?v=cX_kPV_foZc (Striver : the Best explanation)
 */
public class _235_LCAOfBST {

    /**
     * Standing at a node, there are 4 possibilities.
     * 1. both the nodes lie to the left.   - in this case, go left subtree.
     * 2. both the nodes lie to the right.  - in this case, go right subtree.
     * <p>
     * 3. first node to the left and second one to the right
     * or vice versa.
     * 4. node itself is one of them.
     * <p>
     * in the 3rd and 4th case, the current node is the LCA. return it.
     */
    public TreeNode findLCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.val < root.val && q.val < root.val) { // 1st case.
            return findLCA(root.left, p, q);
        }

        if (root.val < p.val && root.val < q.val) { // 2nd case.
            return findLCA(root.right, p, q);
        }

        return root;    // 3rd and 4th case.
    }
}
