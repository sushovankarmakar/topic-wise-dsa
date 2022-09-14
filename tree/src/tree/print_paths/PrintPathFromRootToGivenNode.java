package tree.print_paths;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.interviewbit.com/problems/path-to-given-node/
 * https://discuss.geeksforgeeks.org/comment/48097b48e4de77dae34197475601ba61 (Followed this code)
 * <p>
 * Similar Question :
 * https://practice.geeksforgeeks.org/problems/root-to-leaf-paths/1
 * <p>
 * https://www.geeksforgeeks.org/print-path-root-given-node-binary-tree/ (A bit different explanation - I didn't follow this)
 * https://www.youtube.com/watch?v=fmflMqVOC7k (Striver : Same explanation as GFG article)
 */
public class PrintPathFromRootToGivenNode {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("10 20 30 40 60 N N");
        System.out.println(getTargetPath(root1, 60));    // [10, 20, 60]

        TreeNode root2 = TreeNode.buildTree("1 2 3 4 5 6 7");
        System.out.println(getTargetPath(root2, 5));    // [1, 2, 5]
    }

    private static List<Integer> targetPath;

    private static List<Integer> getTargetPath(TreeNode root, int target) {
        targetPath = new ArrayList<>();
        findPath(root, new ArrayList<>(), target);
        return targetPath;
    }

    /**
     * PreOrder traversal : root, left, right
     */
    private static void findPath(TreeNode root, List<Integer> path, int target) {

        if (root == null) return;

        path.add(root.val);
        if (root.val == target) {
            // IMPORTANT to add a copy of the current list, or else while removing it will remove from the same list.
            targetPath.addAll(path);

            // IMPORTANT : Don't add Return statement here.
        }

        findPath(root.left, path, target);
        findPath(root.right, path, target);

        // IMPORTANT to keep the remove operation at last after visiting both the children.
        // MISTAKE : I kept remove operation after visiting left child only.
        path.remove(path.size() - 1);   // while going back remove, last element from arrayList
    }
}
