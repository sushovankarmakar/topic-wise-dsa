package tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * https://practice.geeksforgeeks.org/problems/serialize-and-deserialize-a-binary-tree/1
 * <p>
 * https://www.youtube.com/watch?v=-YbXySKJsX8 (Striver)
 */
public class _297_SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildTree("1 2 3 N N 4 5");
        String serializedTree = serialize(root);
        System.out.println(serializedTree); // 1,2,3,#,#,4,5,#,#,#,#,
        TreeNode.printLevelOrder(deserialize(serializedTree));
    }

    // Encodes a tree to a single string.
    private static String serialize(TreeNode root) {

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {

                TreeNode currNode = queue.poll();

                if (currNode != null) {
                    sb.append(currNode.val).append(",");

                    queue.add(currNode.left);
                    queue.add(currNode.right);
                } else {
                    sb.append("#,");
                }
            }
        }

        //System.out.println(sb.toString());

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    private static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;

        String[] values = data.split(",");

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.add(root);

        int index = 1;

        while (!queue.isEmpty()) {

            TreeNode currNode = queue.poll();

            if (index < values.length) {
                if (!values[index].equals("#")) {
                    TreeNode leftChild = new TreeNode(Integer.parseInt(values[index]));
                    currNode.left = leftChild;
                    queue.add(leftChild);
                }
                index++;
            }

            if (index < values.length) {
                if (!values[index].equals("#")) {
                    TreeNode rightChild = new TreeNode(Integer.parseInt(values[index]));
                    currNode.right = rightChild;
                    queue.add(rightChild);
                }
                index++;
            }
        }
        return root;
    }
}
