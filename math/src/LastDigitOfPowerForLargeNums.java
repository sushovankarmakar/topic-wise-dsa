package src;

/**
 * https://practice.geeksforgeeks.org/problems/find-last-digit-of-ab-for-large-numbers1936/1
 *
 * some helpful links :
 * https://massivealgorithms.blogspot.com/2016/08/find-last-digit-of-ab-for-large-numbers.html
 *
 */
public class LastDigitOfPowerForLargeNums {

  public static void main(String[] args) {

    System.out.println(getLastDigit("3", "10"));
    System.out.println(getLastDigit("6", "2"));
  }

  /**
   * https://brilliant.org/wiki/finding-the-last-digit-of-a-power/
   *
   * Number  |  Last digits that repeat in cycle
   *   1     |     1
   *   2     |  4, 8, 6, 2
   *   3     |  9, 7, 1, 3
   *   4     |  6, 4
   *   5     |  5
   *   6     |  6
   *   7     |  9, 3, 1, 7
   *   8     |  4, 2, 6, 8
   *   9     |  1, 9
   */

  /**
   * https://www.youtube.com/watch?v=cQm__9qnXIw&ab_channel=Experts%27Global (Solved after seeing this trick)
   * https://www.youtube.com/watch?v=81pwuMJ8OIU&ab_channel=Freshersworld.com
   *
   * Steps :
   * 1. take last digit of base and store it in num1
   * 2. take the last two digits of given power and store it in num2. (instead of whole, take only last two digits) : trick
   * 3. module num2 by 4, and store that in newPower
   * 4. now calculate Math.pow(num1, newPower) and take the last digit of it.
   */
  private static int getLastDigit(String base, String power) {

    // step 1
    int num1 = Integer.parseInt(base.charAt(base.length() - 1) + "");

    // step 1
    if(power.length() < 2) {
      power = "00" + power;
    }
    int num2 = Integer.parseInt(power.substring(power.length() - 2));

    if (num2 == 0) return 1;  // edge case : if num2 is zero, then last digit is 1

    if (num1 == 0 || num1 == 1 || num1 == 5 || num1 == 6) {   // additional step, this is optional.
      return num1;
    }

    // step 3
    int newPower = (num2 % 4 == 0) ? 4 : (num2 % 4);    // value of newPower can be only among 1,2,3,4

    // step 4
    int lastDigit = (int) Math.pow(num1, newPower) % 10;
    return lastDigit;
  }

}
