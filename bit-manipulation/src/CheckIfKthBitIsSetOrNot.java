package src;

/**
 * https://practice.geeksforgeeks.org/problems/check-whether-k-th-bit-is-set-or-not-1587115620/1
 * https://www.geeksforgeeks.org/check-whether-bit-given-position-set-unset/ (A good explanation)
 *
 * https://www.youtube.com/watch?v=IO2D1g2QP6E&ab_channel=CodeNCode (A good explanation)
 * https://www.youtube.com/watch?v=ldhT2uVSdUQ&ab_channel=TECHDOSE
 * https://www.youtube.com/watch?v=b7AYbpM5YrE&ab_channel=takeUforward (Power Set | Print all Subsequences)
 */
public class CheckIfKthBitIsSetOrNot {

  public static void main(String[] args) {
    System.out.println(isKthBitSet(4, 0));  // false
    System.out.println(isKthBitSet(4, 2));  // true
    System.out.println(isKthBitSet(500, 3));  // false
    System.out.println(isKthBitSet(12, 2));  // true
  }

  /**
   * doing left shift by k time, will bring the kth bit to the LSB(least significant bit) place,
   * and doing 'and' operator with 1 will give some value.
   * if this value is has 1 in LSB, then return true, else false.
   */
  private static boolean isKthBitSet(int num, int k) {
    return ((num >> k) & 1) != 0;
  }

}
