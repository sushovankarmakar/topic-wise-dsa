package fixed_size;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1#
 * <p>
 * https://www.youtube.com/watch?v=uUXXEgK2Jh8&ab_channel=AdityaVerma
 *
 * https://www.callicoder.com/first-negative-number-in-every-window-of-size-k/
 */
public class FirstNegativeNumInWindowOfSizeK {

    public static void main(String[] args) {
        long[] array = new long[]{-8, 2, 3, -6, 10};
        long[] negNums = getFirstNegativeNumInWindowOfSizeK(array, 5, 2);
        for (long num : negNums) {
            System.out.print(num + " ");
        }
    }

    private static long[] getFirstNegativeNumInWindowOfSizeK(long[] arr, int n, int k) {
        long[] negNums = new long[n - k + 1];
        Queue<Long> queue = new LinkedList<>();

        int left = 0, right = 0, index = 0;

        for (; right < n; right++) {

            if (arr[right] < 0) {
                queue.add(arr[right]);
            }

            int windowSize = right - left + 1;
            if (windowSize == k) {

                if (!queue.isEmpty()) {
                    long negNum = queue.peek();
                    negNums[index++] = negNum;

                    if (negNum == arr[left]) {
                        queue.remove();
                    }
                } else {
                    negNums[index++] = 0;
                }
                left++;
            }
        }
        return negNums;
    }
}
