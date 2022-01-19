package fixed_size;

/**
 * there are total n days and each day's consumed calorie is given.
 * also max calorie and min calorie is given.
 *
 * we need to check for last k days, total consumed calorie
 *  - if less than min calorie, weight is reduced by 1
 *  - if greater than max calorie, weight is increased by 1
 *  - or else weight remains same.
 *
 *  check after n days, give the weight difference.
 */
public class WeightChecker {

  /**
   * This question was asked in DRUVA CODING CONTEST.
   */
  public static void main(String[] args) {
    System.out.println(checkWeight(5, 2, 10, 20, new int[]{9, 8, 7, 6, 5}));
    System.out.println(checkWeight(5, 1, 10, 20, new int[]{9, 8, 7, 6, 5}));
  }

  private static int checkWeight(int noOfDays, int k, int minCalorie, int maxCalorie, int[] calories) {

    int weightDiff = 0;

    int left = 0;
    int right = 0;
    int calorie = 0;

    while (right < noOfDays) {

      calorie += calories[right];

      if (right - left + 1 == k) {

        weightDiff = updateWeightDiff(calorie, minCalorie, maxCalorie, weightDiff);

        calorie -= calories[left];
        left++;
      }
      right++;
    }
    return weightDiff;
  }

  private static int updateWeightDiff(int calorie, int minCalorie, int maxCalorie, int weightDiff) {

    if (calorie < minCalorie) {
      return weightDiff - 1;  // made mistake here in druva coding contest. was doing  weightDiff--;
    } else if (calorie > maxCalorie) {
      return weightDiff + 1;  // made mistake here in druva coding contest.
    }
    return weightDiff;
  }

}
