package src.house_robber;

/**
 * https://leetcode.com/problems/house-robber/
 * https://practice.geeksforgeeks.org/problems/stickler-theif-1587115621/1
 * <p>
 * https://chatgpt.com/share/ab2fc88e-8329-4f2c-8519-b9810716a155
 * https://leetcode.com/problems/house-robber/solutions/1753812/maximum-sum-of-non-adjacent-elements-o1/ (Very good explanation)
 * <p>
 * (MY OWN CODE and DETAILED EXPLANATION)
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/DP_SticklerThief.java
 * <p>
 * https://www.youtube.com/watch?v=yYqShJj-ydA&ab_channel=codeExplainer
 * <p>
 */
public class _198_HouseRobber1 {

    //  finding the largest sum of non-adjacent numbers in O(N) time

    public static void main(String[] args) {
        System.out.println(robMaxMoney(new int[]{1, 2, 3, 1})); // 4
        System.out.println(robMaxMoney(new int[]{2, 7, 9, 3, 1}));  // 12
        System.out.println(robMaxMoney(new int[]{5, 5, 10, 100, 10, 5})); // 110
        System.out.println(robMaxMoney(new int[]{1, 2, 3}));  // 4
        System.out.println(robMaxMoney(new int[]{2, 4, 6, 2, 5}));  // 13
        System.out.println(robMaxMoney(new int[]{5, 1, 1, 5}));  // 10

        // handles -ve cases also
        System.out.println(robMaxMoney(new int[]{3, 2, 5, 10, 7, -2, 4, -1, 1}));  // 20
        // handles zero values also
        System.out.println(robMaxMoney(new int[]{3, 2, 0, 10, 0, 5}));  // 18
    }

    // https://chatgpt.com/share/ab2fc88e-8329-4f2c-8519-b9810716a155
    private static int robMaxMoney(int[] houses) {

        if (houses.length == 1) {
            return houses[0];
        }
        if (houses.length == 2) {
            return Math.max(houses[0], houses[1]);
        }

        int moneyRobbedFromSecondLastHouse = houses[0];
        int moneyRobbedFromLastHouse = Math.max(houses[0], houses[1]);
        int moneyRobbedFromCurrentHouse = 0;

        for (int i = 2; i < houses.length; i++) {
            moneyRobbedFromCurrentHouse = Math.max(
                    houses[i] + moneyRobbedFromSecondLastHouse,
                    moneyRobbedFromLastHouse);

            moneyRobbedFromSecondLastHouse = moneyRobbedFromLastHouse;
            moneyRobbedFromLastHouse = moneyRobbedFromCurrentHouse;
        }

        return moneyRobbedFromCurrentHouse;
    }

    /*
    public int rob(int[] arr) {
        int n = arr.length;

        // base cases
        if (n == 1) return arr[0];
        if (n == 2) return Math.max(arr[0], arr[1]);

        // dynamic programming solution
        int[] maxSum = new int[n];
        maxSum[0] = arr[0];
        maxSum[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            maxSum[i] = Math.max(arr[i] + maxSum[i - 2], maxSum[i - 1]);
        }

        return maxSum[n - 1];
    }
    */
}
