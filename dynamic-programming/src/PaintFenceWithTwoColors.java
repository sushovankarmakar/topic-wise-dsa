package src;

/*
Problem:
	Paint Fence With Two Colors
	There is a fence with n posts, each post can be painted with either green or blue color.
	You have to paint all the posts such that no more than two adjacent fence posts have the same color.
	Return the total number of ways you can paint the fence.
*/
// https://www.geeksforgeeks.org/painting-fence-algorithm/
public class PaintFenceWithTwoColors {

    // HARD PROBLEM ON LEETCODE
    public static void main(String[] args) {
        System.out.println(numWays(3)); // 6
        System.out.println(numWays(4)); // 10
        System.out.println(numWays(5)); // 16
    }

    /**
     * transition function :
     * f(i, j) = f(i - 1, 1 - j) + f(i - 2, 1- j);
     * where f(i, j) represents total number of ways to paint i posts ending with a post painted in j.
     * i = ith post
     * j = jth color
     */
    private static int numWays(int n) {
        int[][] dp = new int[n + 1][2];

        // columns represent the color
        // blue  - 0
        // green - 1

        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[2][0] = 2;   // 1 0,  0 0
        dp[2][1] = 2;   // 0 1,  1 1

        for (int post = 3; post <= n; post++) {
            for (int color = 0; color <= 1; color++) {
                dp[post][color] = dp[post - 1][1 - color] + dp[post - 2][1 - color];
            }
        }
        return dp[n][0] + dp[n][1];
    }
}
