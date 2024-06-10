package src.house_robber;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber-ii/
 * <p>
 * Below diagram is enough to clear the concept. I solved it after seeing the below diagram.
 * https://raw.githubusercontent.com/hot13399/leetcode-graphic-answer/master/213.%20House%20Robber%20II.jpg
 * <p>
 * We can divide this problem to two sub problems:
 * Let's take following example: [1, 2, 3, 4, 5, 6, 7, 8, 9]
 * Sub problem 1: rob1 house 1 ~ 8  (1st house to 8th house)
 * Sub problem 2: rob1 house 2 ~ 9  (2nd house to 9th house)
 * And find the bigger one of these two sub problems.
 * <p>
 * <p>
 * https://www.youtube.com/watch?v=yYqShJj-ydA&ab_channel=codeExplainer
 */

public class _213_HouseRobber2 {

    public static void main(String[] args) {
        System.out.println(robMaxMoneyFromCircle(new int[]{2, 3, 2}));  // 3
        System.out.println(robMaxMoneyFromCircle(new int[]{1, 2, 3, 1}));  // 4
        System.out.println(robMaxMoneyFromCircle(new int[]{1, 2, 3}));  // 3
        System.out.println(robMaxMoneyFromCircle(new int[]{2, 1, 3, 5}));  // 6
    }

    private static int robMaxMoneyFromCircle(int[] houses) {

        int length = houses.length;
        if (length == 1) {
            return houses[0];
        }
        if (length == 2) {
            return Math.max(houses[0], houses[1]);
        }

        int[] part1 = Arrays.copyOfRange(houses, 0, length - 1);  // chose to rob1 the first house
        int moneyRobbedFromPart1 = robMaxMoney(part1);

        int[] part2 = Arrays.copyOfRange(houses, 1, length);          // chose not to rob1 the first house
        int moneyRobbedFromPart2 = robMaxMoney(part2);

        return Math.max(moneyRobbedFromPart1, moneyRobbedFromPart2);      // take max of two options.
    }

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

}

/**
 * public static int[] copyOfRange(int[] original_array, int from_index, int to_index)
 * <p>
 * original_array : Array to be copied from
 * from_index : This is the initial index of the range to be copied, INCLUSIVE.
 * to_end : This is the final index of the range to be copied, EXCLUSIVE.
 */
