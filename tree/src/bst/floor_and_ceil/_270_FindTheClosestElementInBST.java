package bst.floor_and_ceil;

import bst.TreeNode;

/**
 * https://practice.geeksforgeeks.org/problems/find-the-closest-element-in-bst/1  (asks the absolute difference)
 * https://leetcode.com/problems/closest-binary-search-tree-value/ (asks the actual value)
 * <p>
 * https://practice.geeksforgeeks.org/problems/implementing-ceil-in-bst/1 (Find ceil)
 * <p>
 * https://www.geeksforgeeks.org/floor-and-ceil-from-a-bst/ (BEST Article. I followed the iterative solution.)
 * <p>
 * https://www.youtube.com/watch?v=KSsk8AhdOZA (Find Ceil - Striver)
 * https://www.youtube.com/watch?v=xm_W1ub-K-w (Find Floor - Striver)
 */
public class _270_FindTheClosestElementInBST {

    /**
     * Floor Value Node:
     * ----------------
     * Node with the greatest data lesser than or equal to the key value.
     *
     * Ceil Value Node:
     * ---------------
     * Node with the smallest data larger than or equal to the key value.
     *
     * Find the floor and ceil for the given target.
     */


    /**
     * GFG Variation.
     */
    private static int minDiff(TreeNode root, int target) {

        int floor = -1;
        int ceil = -1;

        while (root != null) {

            if (root.val == target) {
                return 0;
            }

            if (target < root.val) {
                ceil = root.val;   // as of now, this is the nearest bigger value I've seen. so this is a possible candidate for ceil.
                root = root.left;   // let's explore more on the left subtree, because on the right, I'll get only bigger values which is useless for me.

            } else {    // root.data < target

                floor = root.val;  // as of now, this is the nearest smaller value I've seen. so this is a possible candidate for floor.
                root = root.right;  // let's explore more on the right subtree, because on the left, I'll get only smaller values which is useless for me.
            }
        }

        if (floor == -1) return Math.abs(target - ceil);
        if (ceil == -1) return Math.abs(target - floor);

        return Math.min(Math.abs(target - ceil), Math.abs(target - floor));
    }


    /**
     * Leetcode Variation.
     */
    public int closestValue(TreeNode root, double target) {

        int floor = -1;
        int ceil = -1;

        while (root != null) {

            if (root.val == target) {
                return root.val;
            }

            if (target < root.val) {
                ceil = root.val;   // as of now, this is the nearest bigger value I've seen.
                root = root.left;   // let's explore more on the left subtree, because on the right, I'll get only bigger values which is useless for me.

            } else {    // root.data < target

                floor = root.val;  // as of now, this is the nearest smaller value I've seen.
                root = root.right; // let's explore more on the right subtree, because on the left, I'll get only smaller values which is useless for me.
            }
        }

        if (floor == -1) return ceil;
        if (ceil == -1) return floor;

        return Math.abs(target - ceil) > Math.abs(target - floor) ? floor : ceil;
    }
}
