package src;

/**
 * https://leetcode.com/problems/domino-and-tromino-tiling/
 *
 * I followed the below link to understand:
 * https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3 (V.V.V. good explanation)
 * https://leetcode.com/problems/domino-and-tromino-tiling/discuss/1620610/Daily-LeetCoding-Challenge-December-Day-10/1176051 (Good java solution)
 *
 * https://www.youtube.com/watch?v=7cijrfUkQzc&ab_channel=CodingDecoded (Only this video solution I understood) <- Good explanation
 */
public class _790_DominoAndTrominoTiling {

  public static void main(String[] args) {
    System.out.println(numTilings(3)); // 5
    System.out.println(numTilings(1)); // 1
    System.out.println(numTilings(1000)); // 979232805
  }

  // DRAW FOR N = 1, 2, 3, 4 TO UNDERSTAND THE RELATION.
  /**
   * dp[n]  = dp[n-1] + dp[n-2] + 2*(dp[n-3]+...+d[0])
   *        = dp[n-1] + dp[n-2] + dp[n-3] + dp[n-3] +  2*(dp[n-4]+...+d[0])
   *        = dp[n-1] + dp[n-3] + (dp[n-2] + dp[n-3] + 2*(dp[n-4]+...+d[0]))
   *        = dp[n-1] + dp[n-3] + dp[n-1]
   *        = 2*dp[n-1] + dp[n-3]
   */
  /**
   * https://www.youtube.com/watch?v=7cijrfUkQzc&ab_channel=CodingDecoded
   */
  private static int numTilings(int n) {

    if (n < 3) {
      return n;
    }

    int mod = 1000000007; // modulo = 10^9 + 7.

    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 2;

    for (int i = 3; i <= n; i++) {
      dp[i] = ((2 * dp[i - 1]) % mod + dp[i - 3]) % mod;
    }
    return dp[n];
  }

}
