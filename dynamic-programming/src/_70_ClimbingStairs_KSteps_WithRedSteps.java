package src;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.youtube.com/watch?v=7vM_RZEGUcw&t=32s&ab_channel=AndreyGrehov
 * <p>
 * You are climbing a staircase. It takes n steps to reach to the top.
 * Each time you can climb 1..k steps. You are not allowed to step on red stairs.
 */
public class _70_ClimbingStairs_KSteps_WithRedSteps {

    public static void main(String[] args) {
        System.out.println(nthPointByKSteps(7, 3, new int[]{1, 3, 4})); // we can't jump in 1st, 3rd and 4th stair.
        // if there is 7 stairs and we can take maximum 3 steps and we can't jump in 1st, 3rd and 4th stair
        // then there is only 2 ways we can go to the top.

        // stair :  0 | 1 | 2 | 3 | 4 | 5 | 6 | 7    :: we can take 1, 2 or 3 steps at a time.
        // steps :  1 | 0 | 1 | 0 | 0 | 1 | 1 | 2
    }

    public static int nthPointByKSteps(int n, int k, int[] redSteps) {

        Set<Integer> canNotJumpStairs = new HashSet<>();
        Arrays.stream(redSteps).forEach(redStep -> canNotJumpStairs.add(redStep));

        if (n <= 1) return 1;

        int[] steps = new int[n + 1];

        steps[0] = 1;
        //steps[1] = 0; // zero cause we can't step into 1st stair. so there is zero ways we can step into 1st stair.

        for (int i = 1; i <= n; i++) {

            if (canNotJumpStairs.contains(i)) { // checking if we can ith stair, if can't then mark it as zero ways.
                steps[i] = 0;
                continue;
            }

            for (int j = 1; j <= k; j++) {
                if (i >= j) {
                    steps[i] += steps[i - j];
                }
            }
        }
        return steps[n];
    }
}
