package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * https://workat.tech/problem-solving/practice/populating-next-right-pointers-in-each-node
 * https://workat.tech/problem-solving/practice/populating-next-right-pointers-in-each-node/editorial (1st approach)
 * <p>
 * https://www.youtube.com/watch?v=KX6gz1-pjg0&t=136s&ab_channel=jayatitiwari
 * https://www.youtube.com/watch?v=1KgxLOlmNmw&ab_channel=CodingDecoded
 */
public class _116_PopulateNextRightPointer {

    // METHOD - 2  : https://www.youtube.com/watch?v=1KgxLOlmNmw&ab_channel=CodingDecoded (Most optimal solution in BFS)
    public TreeNode connect(TreeNode root) {

        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            TreeNode prev = null;

            for (int i = 0; i < size; i++) {

                TreeNode currNode = queue.poll();

                currNode.next = prev;
                prev = currNode;

                if (currNode.right != null) queue.add(currNode.right);
                if (currNode.left != null) queue.add(currNode.left);
            }
        }
        return root;
    }

    // METHOD - 1  : https://www.youtube.com/watch?v=KX6gz1-pjg0&t=136s&ab_channel=jayatitiwari
    public TreeNode connect_1(TreeNode root) {

        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {

                TreeNode currNode = queue.poll();

                if (currNode == null && queue.isEmpty()) {
                    return root;
                } else if (currNode == null) {
                    queue.add(null);
                    break;
                } else {
                    currNode.next = queue.peek();
                }

                if (currNode.left != null) queue.add(currNode.left);
                if (currNode.right != null) queue.add(currNode.right);
            }
        }
        return root;
    }

    class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode next;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right, TreeNode next) {
            this.val = val;
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }
}
