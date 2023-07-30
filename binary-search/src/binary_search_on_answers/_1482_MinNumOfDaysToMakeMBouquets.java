package src.binary_search_on_answers;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
 * https://www.codingninjas.com/studio/problems/rose-garden_2248080
 * https://practice.geeksforgeeks.org/problems/minimum-days-to-make-m-bouquets/1
 * <p>
 * https://takeuforward.org/arrays/minimum-days-to-make-m-bouquets/
 * https://www.youtube.com/watch?v=TXAuxeYBTdg (Striver)
 */
public class _1482_MinNumOfDaysToMakeMBouquets {

    public static void main(String[] args) {
        System.out.println(solve(2, 3, new int[]{5, 5, 5, 5, 10, 5, 5})); // 10
        System.out.println(solve(3, 2, new int[]{1, 10, 3, 10, 2})); // -1
    }

    public static int solve(int m, int k, int[] bloomDay) {
        if (bloomDay.length < m * k) return -1; // base case.

        int low = getMinBloomDay(bloomDay);
        int high = getMaxBloomDay(bloomDay);

        int count = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (isPossible(bloomDay, mid, m, k)) {
                count = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return count;
    }

    private static int getMinBloomDay(int[] bloomDay) {
        return Arrays.stream(bloomDay)
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    private static int getMaxBloomDay(int[] bloomDay) {
        return Arrays.stream(bloomDay)
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    private static boolean isPossible(int[] bloomDays, int currDay, int m, int k) {

        //currDay = ...; // initialize the current day
        //k = ...; // initialize the number of flowers per bouquet
        //m = ...; // initialize the required number of bouquets

        int consecutiveBloomedFlowers = 0;
        int bouquet = 0;

        for (int bloomDay : bloomDays) {

            if (bloomDay <= currDay) {
                consecutiveBloomedFlowers++;
            } else {
                bouquet += (consecutiveBloomedFlowers / k);
                consecutiveBloomedFlowers = 0;
            }
        }

        bouquet += (consecutiveBloomedFlowers / k);

        return bouquet >= m;
    }
}
