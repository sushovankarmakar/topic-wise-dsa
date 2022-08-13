package src;

/**
 * https://practice.geeksforgeeks.org/problems/count-number-of-hops-1587115620/1
 * https://www.geeksforgeeks.org/count-number-of-ways-to-cover-a-distance/
 * <p>
 * https://www.geeksforgeeks.org/coin-change-dp-7/
 * <p>
 * https://www.youtube.com/watch?v=IqAdhHpRu3Y&t=34s&ab_channel=AndreyGrehov (I followed this)
 * https://github.com/andreygrehov/dp/tree/master/lecture12
 * <p>
 * https://www.geeksforgeeks.org/understanding-the-coin-change-problem-with-dynamic-programming/ (A very good post)
 * <p>
 * https://www.techiedelight.com/coin-change-problem-find-total-number-ways-get-denomination-coins/ (Coin change all variations)
 * https://www.geeksforgeeks.org/understanding-the-coin-change-problem-with-dynamic-programming/
 */
/*
Problem:
	Coin change
	Given an unlimited supply of coins of given denominations,
	find the total number of ways to make a change of size n.

	Transition function:
	f(n) = f(n - d_1) + f(n - d_2) + f(n - d_3) + ... + f(n - d_k);
	where d_1, d_2, d_3, ..., d_k are provided coin denominations.
*/
public class CoinChange_OrderOfCoinDoesNotMatter_WithAllPermutation {

    public static void main(String[] args) {

        int[] denominations1 = {1, 3, 5, 10};
        int change1 = 0;
        System.out.println(count(denominations1, change1)); // 1

        int[] denominations2 = {1, 3, 5, 10};
        int change2 = 3;
        System.out.println(count(denominations2, change2)); // 2

        int[] denominations3 = {1, 3, 5, 10};
        int change3 = 4;
        System.out.println(count(denominations3, change3)); // 3

        int[] denominations4 = {1, 2, 3};
        int change4 = 4;
        System.out.println(count(denominations4, change4)); // 7

        System.out.println(count(new int[]{1, 2, 5}, 12));   // 372
    }

    /**
     * time complexity : O(M * N) where M = size of denominations array, N = size of change
     * space complexity : O(N)
     */
    private static int count(int[] denominations, int change) {

        int[] dp = new int[change + 1];
        dp[0] = 1;

        for (int i = 1; i <= change; i++) {

            for (int denomination : denominations) {

                if (i >= denomination) {
                    dp[i] += dp[i - denomination];  // this is the Transition function. f(n) = f(n - d_1) + f(n - d_2) + f(n - d_3) + ... + f(n - d_k);
                } else {
                    break;  //we can break by assuming denominations array is strictly increasing order.
                }
            }
        }
        return dp[change];
    }
}
