package src;

/**
 * https://leetcode.com/problems/super-egg-drop/
 * https://practice.geeksforgeeks.org/problems/egg-dropping-puzzle-1587115620/1
 * https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors/
 * <p>
 * https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/ (A very good explanation)
 * https://www.youtube.com/watch?v=S49zeUjeUL0&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go (Recursive)
 * https://www.youtube.com/watch?v=gr2NtY-2QUY&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go (Memoized - Top Down)
 * https://www.youtube.com/watch?v=jkygQmOiCCI&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go
 */
public class _887_EggDropping_TopDownMemoization {

    public static void main(String[] args) {
        System.out.println(eggDrop(1, 2));  // 2
        System.out.println(eggDrop(1, 3));  // 3
        System.out.println(eggDrop(2, 10)); // 4
        System.out.println(eggDrop(2, 6));  // 3
        System.out.println(eggDrop(3, 14)); // 4
        System.out.println(eggDrop(100, 100)); // 7
        System.out.println(eggDrop(100, 200)); // 8
        System.out.println(eggDrop(200, 200)); // 8
        System.out.println(eggDrop(100, 1000)); // 10
        System.out.println(eggDrop(5, 10000)); // 18

        // leetcode input constrains won't work without using binary search.
        System.out.println(eggDrop_WithBinarySearch(5, 200)); // 8
        System.out.println(eggDrop_WithBinarySearch(100, 10000)); // 14
    }

    static int[][] memo;

    private static int eggDrop(int eggs, int floors) {

        memo = new int[eggs + 1][floors + 1];

        for (int i = 0; i <= eggs; i++) {
            for (int j = 0; j <= floors; j++) {
                memo[i][j] = -1;
            }
        }
        return findMinAttempt(eggs, floors);
    }

    private static int findMinAttempt(int eggs, int floors) {
        if (floors == 0 || floors == 1) return floors;
        if (eggs == 1) {
            /**
             * if a single egg is present, then at WORST CASE,
             * we have to go to the top floor to find out the critical floor for surety.
             */
            return floors;
        }

        if (memo[eggs][floors] != -1) return memo[eggs][floors];

        int minAttempt = Integer.MAX_VALUE;
        for (int k = 1; k <= floors; k++) {
            // Math.max() is being used because we're trying to find out the WORST CASE. Because in worst case we can
            // critical floor for surety.
            int attempt = 1 + Math.max(
                    findMinAttempt(eggs - 1, k - 1),  // egg breaks at kth floor, so find out the critical floor in the below (k - 1) floors with (eggs - 1)
                    findMinAttempt(eggs, floors - k)); // egg don't break at kth floor, so find out the critical floor in the above (f - k) floors with same number of eggs

            // Math.min() is being used because we need to find out the MINIMUM attempts taken among all the WORST CASE possibilities.
            minAttempt = Math.min(minAttempt, attempt);
        }
        return memo[eggs][floors] = minAttempt;
    }

    // ---------------------------------------------------------------------------------------------------------------

    private static int eggDrop_WithBinarySearch(int eggs, int floors) {

        memo = new int[eggs + 1][floors + 1];

        for (int i = 0; i <= eggs; i++) {
            for (int j = 0; j <= floors; j++) {
                memo[i][j] = -1;
            }
        }
        return findMinAttempt_WithBS(eggs, floors);
    }

    private static int findMinAttempt_WithBS(int eggs, int floors) {
        if (floors == 0 || floors == 1) return floors;
        if (eggs == 1) {
            return floors;
        }

        if (memo[eggs][floors] != -1) return memo[eggs][floors];

        int minAttempt = Integer.MAX_VALUE;

        int low = 1;
        int high = floors;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            int lowerFloorsAttempts = findMinAttempt_WithBS(eggs - 1, mid - 1);
            int upperFloorsAttempts = findMinAttempt_WithBS(eggs, floors - mid);

            int attempt = 1 + Math.max(lowerFloorsAttempts, upperFloorsAttempts);
            minAttempt = Math.min(minAttempt, attempt);

            if (lowerFloorsAttempts < upperFloorsAttempts) {    // we need to go to the higher side.
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return memo[eggs][floors] = minAttempt;
    }

}
