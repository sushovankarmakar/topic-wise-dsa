package src.shortest_path_algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.geeksforgeeks.org/problems/minimum-multiplications-to-reach-end/1
 *
 * https://www.youtube.com/watch?v=_BvEJ3VIDWw&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/graph/g-39-minimum-multiplications-to-reach-end/
 */
public class MinMultiplicationsToReachEnd {

    public static void main(String[] args) {
        System.out.println(minimumMultiplications(new int[]{2, 5, 7}, 3, 30)); // 2
    }

    private static int minimumMultiplications(int[] arr, int start, int end) {

        if (start == end) return 0; // BASE CONDITION

        int[] numbers = new int[100000];
        Arrays.fill(numbers, Integer.MAX_VALUE);

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(start, 0));
        numbers[start] = 0;

        while (!queue.isEmpty()) {

            int currNum = queue.peek().num;
            int currStep = queue.peek().step;

            queue.remove();

            for (int multiplier : arr) {

                int newNum = (currNum * multiplier) % 100000;
                int newStep = currStep + 1;

                if (newNum == end) return newStep;

                if (numbers[newNum] == Integer.MAX_VALUE) {

                    numbers[newNum] = newStep;
                    queue.add(new Pair(newNum, newStep));

                } else {
                    numbers[newNum] = Math.min(numbers[newNum], newStep);
                }
            }
        }
        return -1;
    }

    private static class Pair {
        int num;
        int step;

        Pair(int num, int step) {
            this.num = num;
            this.step = step;
        }
    }
}
