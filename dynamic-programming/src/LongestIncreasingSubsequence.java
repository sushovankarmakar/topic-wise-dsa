package src;

/**
 * https://practice.geeksforgeeks.org/problems/longest-increasing-subsequence-1587115620/1
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 * 
 * https://www.youtube.com/watch?v=ekcwMsSIzVc (Striver)
 */
public class LongestIncreasingSubsequence {

    // https://discuss.geeksforgeeks.org/comment/f546691e891828adeb450d46d915d33e -- Using Binary Search.

    public static void main(String[] args) {
        System.out.println(longestSubsequence(6, new int[]{5, 8, 3, 7, 9, 1})); // 3
        System.out.println(longestSubsequence(16, new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15})); //6
    }

    static int longestSubsequence(int size, int[] arr) {

        int[][] dp = new int[size][size + 1]; // coordinate shifting to accommodate prev index (which initially is -1).
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }

        return func(0, -1, arr, dp);
    }

    private static int func(int curr, int prev, int[] arr, int[][] dp) {

        if (arr.length == curr) {
            return 0;
        }

        if (dp[curr][prev + 1] != -1) return dp[curr][prev + 1];

        if (prev == -1 || arr[prev] < arr[curr]) {

            return dp[curr][prev + 1] = Math.max(
                    1 + func(curr + 1, curr, arr, dp),  // take it, so curr become prev in next call.
                    func(curr + 1, prev, arr, dp)       // don't take it, so prev stays prev in next call.
            );
        }

        return dp[curr][prev + 1] = func(curr + 1, prev, arr, dp);  // can't take this, so prev stays prev in next call.
    }


    static int longestSubsequence1(int n, int[] arr) {

        int[][] dp = new int[n + 1][n + 1];

        for (int curr = n - 1; curr >= 0; curr--) {
            for (int prev = curr - 1; prev >= -1; prev--) {

                if (prev == -1 || arr[prev] < arr[curr]) {

                    dp[curr][prev + 1] = Math.max(
                            1 + dp[curr + 1][curr + 1],
                            dp[curr + 1][prev + 1]
                    );

//                    return dp[curr][prev + 1] = Math.max(
//                            1 + func(curr + 1, curr, arr, dp),  // take it, so curr become prev in next call.
//                            func(curr + 1, prev, arr, dp)       // don't take it, so prev stays prev in next call.
//                    );
                } else {
                    dp[curr][prev + 1] = dp[curr + 1][prev + 1];
                }



                //return dp[curr][prev + 1] = func(curr + 1, prev, arr, dp);  // can't take this, so prev stays prev in next call.
            }
        }

        return dp[0][-1 + 1];
    }
}
