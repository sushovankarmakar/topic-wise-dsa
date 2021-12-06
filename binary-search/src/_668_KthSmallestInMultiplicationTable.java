package src;

/**
 * https://practice.geeksforgeeks.org/problems/kth-smallest-number-in-multiplication-table/1
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 * https://www.lintcode.com/problem/1097/description
 *
 * (Below link is the best resource)
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/discuss/1580357/C%2B%2BPython-Clean-and-Simple-Solution-w-Detailed-Explanation-or-Binary-Search-with-Proof
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/solution/
 *
 * https://www.youtube.com/watch?v=N-7biZf7a0I&ab_channel=probability_coding_is_fun_is_1 ( A very good explanation )
 * https://www.youtube.com/watch?v=xrOwjoW0n8s&ab_channel=CodingDecoded
 * https://www.youtube.com/watch?v=j2rFt1pDcVg&ab_channel=TanishqChaudhary
 */
public class _668_KthSmallestInMultiplicationTable {

  public static void main(String[] args) {
    System.out.println(findKthSmallest(3, 3, 5)); // 3
    System.out.println(findKthSmallest(2, 3, 6)); // 6
    System.out.println(findKthSmallest(3, 5, 9)); // 6
  }

  /**
   * Time Complexity : O(m*log(m*n))
   * where m and n are the given input dimensions of multiplication table.
   * We are doing binary search over [1, m*n] which requires O(log(m*n))
   * and each iteration of binary search takes O(m) time for the count function.
   *
   * Space Complexity : O(1)
   */
  private static int findKthSmallest(int m, int n, int k) {
    int left = 1;
    int right = m * n;

    while (left < right) {
      int mid = left + (right - left) / 2;

      int count = countNumsLessThanMid(mid, m, n);
      if (k <= count) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return right;
  }

  private static int countNumsLessThanMid(int target, int m, int n) {
    int count = 0;
    for (int i = 1; i <= m; i++) {
      count += Math.min(target / i, n);
    }
    return count;
  }
}
