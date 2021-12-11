package src;

/**
 * https://practice.geeksforgeeks.org/problems/maximum-subset-xor/1
 * https://practice.geeksforgeeks.org/problems/maximum-xor-subset0715/1
 *
 * https://stackoverflow.com/questions/27470592/maximum-xor-among-all-subsets-of-an-array# (Good article)
 * https://math.stackexchange.com/questions/48682/maximization-with-xor-operator
 * https://www.geeksforgeeks.org/find-maximum-subset-xor-given-set/
 *
 * https://www.youtube.com/watch?v=u4Xid1qHZxs&ab_channel=StudywithSaumya ( Good video explanation )
 *
 *
 * https://www.youtube.com/watch?v=IO2D1g2QP6E&ab_channel=CodeNCode ( how to check if ith bit is set)
 * https://www.youtube.com/watch?v=ldhT2uVSdUQ&ab_channel=TECHDOSE
 */
public class MaximumXORSubset {

  public static void main(String[] args) {
    System.out.println(getMaxXor(new int[]{9, 8, 5})); // 13
    System.out.println(getMaxXor(new int[]{2, 4, 5})); // 7
  }

  /**
   * Gaussian Elimination Algorithm
   *
   * the basic idea is, that if all the bits of all the elements in the array is mutually exclusive,
   * we would need to just XOR all the elements to have the answer.
   *
   * Since at a bit, multiple elements of array can be set, we choose the maximum element with the set bit,
   * and change all the rest of the element with the same bit set as in gaussian elimination method
   *
   * we have to somehow modify the Array element as
   * something like this->
   *
   * 1 0 0 0 0 0
   * 0 1 0 0 0 0
   * 0 0 1 0 0 0
   * 0 0 0 1 0 0
   * 0 0 0 0 1 0
   * 0 0 0 0 0 1
   */
  private static int getMaxXor(int[] arr) {

    int n = arr.length;
    int index = 0;  // Initialize index of chosen elements

    // Traverse through all bits of integer starting from the most significant bit (MSB)
    for (int msb = 31; msb >= 0; msb--) {

      // Initialize index of maximum element and the maximum element
      int maxVal = Integer.MIN_VALUE;
      int maxValIndex = index;

      for (int j = index; j < n; j++) {

        int currVal = arr[j];
        int setBit = (currVal >> msb) & 1;

        // If i'th bit of set[j] is set and set[j] is greater than max so far.
        if (setBit != 0 && maxVal < currVal) {
          maxVal = currVal;
          maxValIndex = j;
        }
      }

      // If there was no element with i'th bit set, move to smaller i
      if (maxVal == Integer.MIN_VALUE) {
        continue;
      }

      // Put maximum element with i'th bit set at index 'index'
      int temp = arr[index];
      arr[index] = arr[maxValIndex];
      arr[maxValIndex] = temp;

      // Update maxInd
      maxValIndex = index;

      // Do XOR of arr[maxValIndex] with all numbers having i'th bit as set.
      // here i = msb(most significant bit)
      doXORWithNumsHavingIthBitAsSet(arr, msb, maxValIndex);

      // Increment index of chosen elements
      index++;
    }

    // Final result is XOR of all elements
    return getXorOfWholeArray(arr);
  }

  private static void doXORWithNumsHavingIthBitAsSet(int[] arr, int msb, int maxValIndex) {
    int n = arr.length;
    for (int j = 0; j < n; j++) {

      int currVal = arr[j];
      int setBit = (currVal >> msb) & 1;

      // XOR arr[maxInd] those numbers which have the i'th bit set.
      // here i = msb(most significant bit)
      if (setBit != 0 && j != maxValIndex) {
        arr[j] = arr[j] ^ arr[maxValIndex];
      }
    }
  }

  private static int getXorOfWholeArray(int[] arr) {
    int xor = 0;
    for(int val : arr) {
      xor ^= val;
    }
    return xor;
  }

}
