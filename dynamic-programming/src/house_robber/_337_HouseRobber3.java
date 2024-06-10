package src.house_robber;

import src.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * <p>
 * https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem (V.V.V well-explanation)
 * https://www.youtube.com/watch?v=nHR8ytpzz7c&ab_channel=NeetCode (VERY WELL Explanation)
 * https://www.youtube.com/watch?v=yYqShJj-ydA&ab_channel=codeExplainer
 */

public class _337_HouseRobber3 {

    public static void main(String[] args) {
    }

    // BEST solution.
    public int rob(TreeNode root) {

        int[] choices = robSub(root);
        return Math.max(choices[0], choices[1]);
    }

    private int[] robSub(TreeNode root) {

        if (root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);

        // choices[0] represents the choice where we choose to take a node.
        // choices[1] represents the choice where we choose not to take a node.

        int[] choices = new int[2];

        choices[0] = root.val + left[1] + right[1];
        choices[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return choices;
    }


    // second approach. Accepted solution. this is better solution than first approach.
    public int rob2(TreeNode root) {
        return robSub2(root, new HashMap<>());
    }

    private int robSub2(TreeNode root, Map<TreeNode, Integer> map) {

        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        int money = 0;

        if (root.left != null) {
            money += robSub2(root.left.left, map) + robSub2(root.left.right, map);
        }

        if (root.right != null) {
            money += robSub2(root.right.left, map) + robSub2(root.right.right, map);
        }

        int maxMoney = Math.max(
                money + root.val,
                robSub2(root.left, map) + robSub2(root.right, map));

        map.put(root, maxMoney);

        return maxMoney;
    }

    // first approach. this code give TLE.
    public int rob1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int money = 0;

        if (root.left != null) {
            money += (rob1(root.left.left) + rob1(root.left.right));
        }

        if (root.right != null) {
            money += (rob1(root.right.left) + rob1(root.right.right));
        }

        return Math.max((money + root.val), rob1(root.left) + rob1(root.right));
    }

}
