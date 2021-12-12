package src;

/**
 * https://leetcode.com/problems/house-robber/
 *
 * similar problems :
 * https://practice.geeksforgeeks.org/problems/stickler-theif-1587115621/1
 *
 * (MY OWN CODE and DETAILED EXPLANATION)
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/DP_SticklerThief.java
 *
 * https://www.youtube.com/watch?v=yYqShJj-ydA&ab_channel=codeExplainer
 */

public class HouseRobber1_198 {

  public static void main(String[] args) {
    System.out.println(robMaxMoney(new int[]{1, 2, 3, 1})); // 4
    System.out.println(robMaxMoney(new int[]{2, 7, 9, 3, 1}));  // 12
    System.out.println(robMaxMoney(new int[]{5, 5, 10, 100, 10, 5})); // 110
    System.out.println(robMaxMoney(new int[]{1, 2, 3}));  // 4
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
