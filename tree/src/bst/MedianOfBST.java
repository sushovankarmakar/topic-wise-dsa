package bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/median-of-bst/1
 *
 * https://www.youtube.com/watch?v=XIjko1AgEmM (Understood just the concept)
 */
public class MedianOfBST {

    public static void main(String[] args) {
        //Solution_1.findMedian(null);
    }

    /**
     * Did it by myself.
     * store the inorder values and return the median values.
     *
     * time : O(n)
     * space : O(n) -- because storing all the values
     */
    static class Solution_1 {
        public static float findMedian(TreeNode root) {
            List<Integer> nodes = inorder(root);

            int n = nodes.size();
            if (n == 1) return nodes.get(0); // base case check.

            int mid = nodes.get(n / 2);
            int midPrev = nodes.get(n / 2 - 1);

            return n % 2 == 0 ? ((float) midPrev + mid) / 2 : mid;
        }

        private static List<Integer> inorder(TreeNode root) {
            List<Integer> nodes = new ArrayList<>();

            Stack<TreeNode> stack = new Stack<>();

            while (!stack.isEmpty() || root != null) {

                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    nodes.add(root.val);
                    root = root.right;
                }
            }
            return nodes;
        }
    }

    /**
     * BETTER SOLUTION :
     *
     * first iteration : count the total number of nodes.
     * second iteration : go through the tree and stop the place  
     *
     * time : O(n)
     * space : O(h) -- h = height of the tree. because storing half the values only.
     */
    static class Solution_2 {
        public static float findMedian(TreeNode root) {
            int numOfNodes = countNodesByInorder(root);
            return findMedianByInorder(root, numOfNodes);
        }

        private static int countNodesByInorder(TreeNode root) {

            int count = 0;
            Stack<TreeNode> stack = new Stack<>();

            while (!stack.isEmpty() || root != null) {

                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();
                    count++;
                    root = root.right;
                }
            }
            return count;
        }

        private static float findMedianByInorder(TreeNode root, int numOfNodes) {

            int prev = root.val;
            int midNodeIndex = numOfNodes / 2;
            int currNodeIndex = -1; // DON'T MISTAKE. this should be -1 not 0, as we're standing at -1 index before traversing the tree.

            Stack<TreeNode> stack = new Stack<>();

            while (!stack.isEmpty() || root != null) {

                if (root != null) {
                    stack.push(root);
                    root = root.left;
                } else {
                    root = stack.pop();

                    currNodeIndex++;
                    if (currNodeIndex == midNodeIndex) {
                        break;
                    }
                    prev = root.val;
                    root = root.right;
                }
            }

            return numOfNodes % 2 == 0 ? ((float)prev + root.val)/2 : (float)root.val;
        }
    }
}
