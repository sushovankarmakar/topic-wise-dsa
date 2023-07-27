package src.binary_search_on_answers;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/koko-eating-bananas/description/
 * https://practice.geeksforgeeks.org/problems/koko-eating-bananas/1
 * https://www.codingninjas.com/studio/problems/minimum-rate-to-eat-bananas_7449064
 * <p>
 * <p>
 * https://www.youtube.com/watch?v=qyfekrNni90 (Striver)
 * https://leetcode.com/problems/koko-eating-bananas/solutions/1703687/java-c-a-very-very-well-detailed-explanation/ (hi-malik detailed solution)
 */
public class _875_KokoEatingBananas {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{805306368, 805306368, 805306368}, 1000000000));
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }

    public static int minEatingSpeed(int[] piles, int h) {

        int low = 1;
        int high = findMaxSpeed(piles);
        int minSpeed = Integer.MAX_VALUE;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            int totalTime = totalTimeTaken(piles, mid);
            if (totalTime < 0) {
                /**
                 values of piles are so big and current speed (mid) is so small that total time is overflowing integer range.
                 so no more calculation. break the while loop.

                 example test case :
                 piles = [805306368,805306368,805306368], mid = 1
                 if we pass above values in totalTimeTaken(), this method will be me overflow. (-ve values)
                 */
                break;
            }
            if (totalTime <= h) {
                // If it's possible to eat all bananas in h hours or less, try a smaller speed
                /**
                 my current speed is mid, but I need to take minize the speed more
                 so store mid as current min speed and move left to find smaller values.
                 */
                minSpeed = mid;
                high = mid - 1;
            } else {
                // If it's not possible to eat all bananas in h hours, try a bigger speed
                low = mid + 1;
            }
        }
        return minSpeed;
    }

    private static int findMaxSpeed(int[] piles) {

        return Arrays.stream(piles)
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    private static int totalTimeTaken(int[] piles, int speed) {

        /**
         * DON'T MAKE THIS MISTAKE.
         * we should give double inside ceil method. or wrong answers for below test case.
         * piles = [3, 6, 7, 11]
         * h = 8
         */
        return Arrays.stream(piles)
                .map(pile -> (int) Math.ceil((double) pile / speed))
                .sum();
    }
}
