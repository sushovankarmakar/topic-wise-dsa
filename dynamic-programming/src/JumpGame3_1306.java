package src;

/**
 * https://leetcode.com/problems/jump-game-iii/
 *
 * https://leetcode.com/problems/jump-game-iii/discuss/1618905/JAVAC%2B%2B-or-DFS-or-BFS-or-Detailed-Explanation (Approach2: DFS)
 * https://www.youtube.com/watch?v=G-qjuWjqv4o&ab_channel=AyushiSharma (I followed this explanation)
 *
 * https://www.youtube.com/watch?v=iHF9DiTTkbA&ab_channel=NideeshTerapalli
 */
public class JumpGame3_1306 {

  public static void main(String[] args) {
    System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 5));  // true
    System.out.println(canReach(new int[]{4, 2, 3, 0, 3, 1, 2}, 0));  // true
    System.out.println(canReach(new int[]{3, 0, 2, 1, 2}, 2));  // false
  }

  public static boolean canReach(int[] arr, int start) {

    int len = arr.length;
    return isReachable(arr, start, len);
  }

  private static boolean isReachable(int[] arr, int start, int len) {

    if (start < 0 || len <= start || arr[start] < 0) {
      return false; // return false if out of array index bound OR if the node is already visited
    }

    if (arr[start] == 0) {
      return true;
    }

    arr[start] = -arr[start]; // marking this node is visited. if we don't do this, we fall in circular recursive loop.

    boolean backwardMove = isReachable(arr, start - arr[start], len);
    boolean forwardMove  = isReachable(arr, start + arr[start], len);

    return forwardMove || backwardMove;
  }

}
