package src;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/
 * <p>
 * https://www.youtube.com/watch?v=s21et-TSkkk (Very good explanation)
 * https://www.youtube.com/watch?v=8-bvtKy6Dy4 - HOW TO WRITE CUSTOM COMPARATOR
 */

/**
 * https://www.youtube.com/watch?v=8-bvtKy6Dy4 - HOW TO WRITE CUSTOM COMPARATOR
 * ----------------------------------------------------------------
 * in compare method, always think,
 * between two input values, first value is smaller, second value bigger.
 * then,
 * if descending order sorting -> bigger_value - smaller_value.
 * if ascending  order sorting -> smaller_value - bigger_value.
 */
public class _1996_NumberOfWeakCharacters {

    /**
     * 1. decreasing (descending) order of attack.
     * 2. increasing (ascending)  order of defence in case of two attack values are same.
     */
    private static int numberOfWeakCharacters(int[][] properties) {

        Arrays.sort(properties, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {   // increasing (ascending)  order of defence in case of two attack values are same.
                    return o1[1] - o2[1];
                } else {
                    return o2[0] - o1[0];   // decreasing (descending) order of attack.
                }
            }
        });

        int weakCharacters = 0;
        int maxDefence = Integer.MIN_VALUE;
        for (int i = 0; i < properties.length; i++) {
            if (properties[i][1] < maxDefence) {
                weakCharacters++;
            }

            maxDefence = Math.max(maxDefence, properties[i][1]);
        }
        return weakCharacters;
    }

    public static void main(String[] args) {
        int[][] properties = {{7, 7}, {1, 2}, {9, 7}, {7, 3}, {3, 10}, {9, 8}, {8, 10}, {4, 3}, {1, 5}, {1, 5}};
        System.out.println(numberOfWeakCharacters(properties));
    }


    /**
     * O(n^2) approach : this was my naive bruteforce solution.
     */
    private static int numberOfWeakCharacters_1(int[][] properties) {

        int weakCharacters = 0;

        for (int i = 0; i < properties.length; i++) {
            for (int j = 0; j < properties.length; j++) {

                if (i != j) {

                    if (properties[i][0] < properties[j][0] &&
                            properties[i][1] < properties[j][1]) {

                        weakCharacters++;
                        break;
                    }
                }
            }
        }
        return weakCharacters;
    }
}
