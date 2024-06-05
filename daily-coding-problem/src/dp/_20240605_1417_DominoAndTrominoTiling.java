package src.dp;

/**
 * https://leetcode.com/problems/domino-and-tromino-tiling/
 * <p>
 * I followed the below link to understand:
 * https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3 (V.V.V. good explanation)
 * https://leetcode.com/problems/domino-and-tromino-tiling/discuss/1620610/Daily-LeetCoding-Challenge-December-Day-10/1176051 (Good java solution)
 * <p>
 * https://www.youtube.com/watch?v=7cijrfUkQzc&ab_channel=CodingDecoded (Only this video solution I understood) <- Good explanation
 */
public class _20240605_1417_DominoAndTrominoTiling {

    // My handwritten diagram to understand the relation : https://drive.google.com/file/d/1Gc807G52iAsFHqFl3jXSLKumcsIuRY5X/view?usp=sharing
    public static void main(String[] args) {
        System.out.println(numTilings(1)); // 1
        System.out.println(numTilings(3)); // 5
        System.out.println(numTilings(4)); // 11
        System.out.println(numTilings(1000)); // 979232805
    }

    private static int numTilings(int n) {

        if (n < 3) {
            return n;
        }

        int mod = 1000000007;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (((2 * dp[i - 1]) % mod) + dp[i - 3]) % mod;
        }

        return dp[n] % mod;
    }
}
