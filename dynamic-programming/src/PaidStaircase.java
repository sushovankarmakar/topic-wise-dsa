package src;

/*
Problem:
	Paid Staircase
	You are climbing a paid staircase. It takes n steps to reach to the top and you have to
	pay p[i] to step on the i-th stair. Each time you can climb 1 or 2 steps.
	What's the cheapest amount you have to pay to get to the top of the staircase?
*/

// Time complexity: O(n)
// Space complexity: O(n)
// https://github.com/andreygrehov/dp/tree/master/lecture7
// https://www.youtube.com/watch?v=hekG82t4U_M&ab_channel=AndreyGrehov
public class PaidStaircase {

    public static void main(String[] args) {
        System.out.println(paidStaircase(3, new int[]{0, 3, 2, 4})); // 6
    }

    private static int paidStaircase(int n, int[] prices) {
        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = prices[1];

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + prices[i];
        }
        return dp[n];
    }

}
