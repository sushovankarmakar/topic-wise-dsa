package src;

/**
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/
 * <p>
 * best explanation :
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/discuss/398342/Python-simple-oddeven-approach-with-explanation.
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/discuss/1613981/C%2B%2BPython-Simple-Solution-w-Explanation-or-Count-Even-and-Odd-Positions-or-Greedy-Approach
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/discuss/1613896/JAVA-or-Detailed-Explanation-of-Question-and-Intution-or-Greedy
 */
public class _1217_MinCostToMoveChipsToSamePos {

  public static void main(String[] args) {

    System.out.println(minCostToMoveChips(new int[]{1, 1000000000})); // 1
    System.out.println(minCostToMoveChips(new int[]{2, 2, 2, 3, 3})); // 2
    System.out.println(minCostToMoveChips(new int[]{1, 2, 3})); // 1

  }

  /**
   * Observation
   * each and every chips at odd position can be taken to position 1 at 0 cost and
   * each and every chips at even position can be taken to position 2 at 0 cost.
   */

  private static int minCostToMoveChips(int[] positions) {
    int oddPosChips = 0;
    int evenPosChips = 0;

    for (int position : positions) {
      if (position % 2 == 0) {
        evenPosChips++;
      } else {
        oddPosChips++;
      }
    }
    return Math.min(evenPosChips, oddPosChips);
  }
}
