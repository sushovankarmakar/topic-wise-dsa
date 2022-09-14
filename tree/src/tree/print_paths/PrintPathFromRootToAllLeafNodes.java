package tree.print_paths;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/root-to-leaf-paths/1
 * <p>
 * https://www.youtube.com/watch?v=4cvz2sfKNDA (Coding Simplified : Learn the concept)
 * https://discuss.geeksforgeeks.org/comment/48097b48e4de77dae34197475601ba61 (Followed this code)
 */
public class PrintPathFromRootToAllLeafNodes {

    public static void main(String[] args) {
        TreeNode root1 = TreeNode.buildTree("10 20 30 40 60 N N");  // [[10, 20, 40], [10, 20, 60], [10, 30]]
        System.out.println(getPaths(root1));

        TreeNode root2 = TreeNode.buildTree("1 2 3");   // [[1, 2], [1, 3]]
        System.out.println(getPaths(root2));

        TreeNode root3 = TreeNode.buildTree("7 2 N 3 5 N 4 4 N 3 6 N 2 5 N 3 7 N 7 8 N 1 6 N 5 2 N 6 7");
        System.out.println(getPaths(root3));
    }

    private static List<List<Integer>> paths;

    /**
     * Short and Crispy
     * https://discuss.geeksforgeeks.org/comment/48097b48e4de77dae34197475601ba61
     * <p>
     * ----------------------
     * Points to remember :
     * 1. IMPORTANT to keep the remove operation at last after visiting both the children.
     * MISTAKE I make : I kept remove operation after visiting left child only.
     * ----------------------
     * 2. IMPORTANT to add a copy of the current list, or else while removing it will remove from the same list.
     * ----------------------
     */
    private static List<List<Integer>> getPaths(TreeNode root) {
        paths = new ArrayList<>();
        findPaths(root, new ArrayList<>());
        return paths;
    }

    /**
     * PreOrder traversal : root, left, right
     */
    private static void findPaths(TreeNode root, List<Integer> path) {

        if (root == null) return;

        path.add(root.val);
        if (root.left == null && root.right == null) {
            // IMPORTANT to add a copy of the current list, or else while removing it will remove from the same list.
            paths.add(new ArrayList<>(path));

            // IMPORTANT : Don't add Return statement here.
        }

        findPaths(root.left, path);
        findPaths(root.right, path);

        // IMPORTANT to keep the remove operation at last after visiting both the children.
        // MISTAKE : I kept remove operation after visiting left child only.
        path.remove(path.size() - 1);   // while going back, remove last element from arrayList
    }

    /**
     * --------------------------------------------------------------------------------------------------------------
     * Lengthy code. First I wrote this.
     * This solution use index, but above solution don't use it.
     */
    public static List<List<Integer>> getPaths_Old(TreeNode root) {
        paths = new ArrayList<>();
        findPaths_Old(root, new ArrayList<>(), 0);
        return paths;
    }

    private static void findPaths_Old(TreeNode root, ArrayList<Integer> path, int index) {

        if (root == null) return;

        if (root.left == null && root.right == null) {
            path.add(root.val);
            paths.add(new ArrayList<>(path));
            return;
        }

        path.add(index, root.val);
        findPaths_Old(root.left, path, index + 1);

        for (int i = path.size() - 1; i > index; i--) {
            path.remove(i);
        }

        findPaths_Old(root.right, path, index + 1);
    }
}
