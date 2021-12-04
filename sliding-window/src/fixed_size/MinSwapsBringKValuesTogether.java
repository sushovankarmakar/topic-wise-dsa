package fixed_size;

/**
 * https://practice.geeksforgeeks.org/problems/minimum-swaps-required-to-bring-all-elements-less-than-or-equal-to-k-together4847/1
 *
 * https://www.youtube.com/watch?v=6_-zOrJlTDo&ab_channel=ShashwatTiwari (Good explanation)
 * https://www.youtube.com/watch?v=yXjVfeWzyAM&ab_channel=AyushiSharma
 * https://www.youtube.com/watch?v=PLu6uvp9l1k&ab_channel=DhruvGoyal
 */

public class MinSwapsBringKValuesTogether {

  public static void main(String[] args) {
    System.out.println(minSwap(new int[]{2, 1, 5, 6, 3}, 3));
  }

  /**
   * Steps :
   * 1. find the window size.
   * 2. traverse the window and with left and right pointer.
   */
  public static int minSwap(int arr[], int k) {
    int n = arr.length;
    int numberOfSmallerValues = 0;

    for (int i = 0; i < n; i++) {
      if (arr[i] <= k) {
        numberOfSmallerValues++;  // this is the window size.
      }
    }

    int left = 0;
    int right = 0;
    int numberOfSwaps = 0;
    int minSwaps = Integer.MAX_VALUE; // final output.

    while (right < n) {

      if (arr[right] > k) { // if the right index element is greater than k, then need to swap, so increase the number
        numberOfSwaps++;
      }

      if (right - left + 1 == numberOfSmallerValues) {  // when window size is hit

        minSwaps = Math.min(minSwaps, numberOfSwaps);

        if (arr[left] > k) {  // if the left index element is lesser than k, then need to swap, so decrease the number
          numberOfSwaps--;
        }
        left++;
      }

      right++;
    }
    return numberOfSmallerValues > 0 ? minSwaps : 0;
  }

}
