package src;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/reordered-power-of-2/
 * https://practice.geeksforgeeks.org/problems/power-of-2-1587115620/1
 * <p>
 * I understood from below two videos.
 * https://www.youtube.com/watch?v=PBf1qzkN6z8
 * https://www.youtube.com/watch?v=6a4bDpcMylo
 */
public class _869_ReorderedPowerOf2 {

    public static void main(String[] args) {
        System.out.println(reorderedPowerOf2(1));   // true
        System.out.println(reorderedPowerOf2(10));  // false
        System.out.println(reorderedPowerOf2(16));  // true
        System.out.println(reorderedPowerOf2(24));  // false
        System.out.println(reorderedPowerOf2(46));  // true
    }

    private static boolean reorderedPowerOf2(int n) {

        int[] inputDigitFreq = countDigitFrq(n);

        int powerOfTwo = 1;
        for (int i = 0; i < 31; i++) {
            int[] powerOfTwoDigitFreq = countDigitFrq(powerOfTwo);

            if (Arrays.equals(powerOfTwoDigitFreq, inputDigitFreq)) {
                return true;
            }

            powerOfTwo = powerOfTwo << 1;
        }
        return false;
    }

    private static int[] countDigitFrq(int num) {
        int[] digitFreq = new int[10];

        while (num > 0) {
            digitFreq[num % 10]++;
            num /= 10;
        }

        return digitFreq;
    }
}
