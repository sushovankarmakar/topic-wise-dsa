package src;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. First store all the fractions in reduced form(i.e obtained after dividing both with their  gcd) in a map along with the index.
 * 2. Now Iterate over the array and find the req fraction for each number in array which will make it 1. Look up for this req fraction in the map if found then update the count.
 * 3. As this will count a pair (i,j) twice as (i,j) and (j,i) so the final ans will be count/2.
 *
 * https://discuss.geeksforgeeks.org/comment/5f6da2bf8a077ef636d89490c36d9ce7
 *
 * https://discuss.geeksforgeeks.org/comment/1dab004333d0033912445d3f53f22592 (with picture)
 *
 * https://discuss.geeksforgeeks.org/comment/3d0b94e66b4a33b41c98aa50f0a864c1
 * https://discuss.geeksforgeeks.org/comment/e153408e45ffe6a247929a4e9e2808c8
 */
public class FractionPairsWithSum1_TwoSumProblemVariant {

    /**
     * https://discuss.geeksforgeeks.org/comment/ad5a041442a2d3d570f732bfac0d749e (easy to understand) -- (I followed)
     * https://www.youtube.com/watch?v=V6yehqaZdms (Video) (I followed)
     *
     * https://practice.geeksforgeeks.org/problems/fraction-pairs-with-sum-1/1 (variation of two sum problem)
     * ---
     * https://www.youtube.com/watch?v=UXDSeD9mN-k (two sum problem)
     */
    public static void main(String[] args) {
        System.out.println(countFractions(4, new int[]{1, 2, 2, 8}, new int[]{2, 4, 6, 12}));
        System.out.println(countFractions(5, new int[]{3, 1, 12, 81, 2}, new int[]{9, 10, 18, 90, 5}));
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int countFractions(int n, int[] numerator, int[] denominator) {

        int count = 0;

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int gcd = gcd(numerator[i], denominator[i]);

            /**
             * LOGIC :
             * ---------------------------------------------------------
             * x/y + c/d = 1     (I've x/y, but we need to find out c/d)
             * now try to reduce c/d in form of x and y.
             *
             * ---------------------------------------------------------
             * base of above two fractions should be same. so y == d.
             * so,
             *     x/y + c/d = 1;
             *     --> x/y + c/y = 1;
             *     --> (x + c) / y = 1;
             *     --> x + c = y;
             *     --> c = y - x;
             * so,
             * x/y + (y - x / y) = 1.
             *
             * ---------------------------------------------------------
             * so, I've x/y, we need to find out (y - x / y).
             * (y - x / y)
             */

            int num = numerator[i] / gcd;
            int den = denominator[i] / gcd;

            String fraction = num + "/" + den;  // fraction = 1/3
            String diffFraction = (den - num) + "/" + (den);    // diffFraction = 2/3

            // now we're trying to find out how many 2/3 has been found so far.
            if (map.containsKey(diffFraction)) {
                count += map.get(diffFraction); // as we're storing fractions in reduced form, so we need to keep a counter also.
            }

            map.put(fraction, map.getOrDefault(fraction, 0) + 1);
        }

        return count;
    }
}
