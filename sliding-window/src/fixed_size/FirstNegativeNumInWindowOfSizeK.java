package fixed_size;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
 * https://www.geeksforgeeks.org/first-negative-integer-every-window-size-k/
 * <p>
 * https://www.youtube.com/watch?v=uUXXEgK2Jh8&ab_channel=AdityaVerma
 * <p>
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

    /**
     * Below code is much easier to visualize.
     */
    private static long[] printFirstNegativeInteger(long[] arr, int n, int k) {

        Queue<Long> queue = new LinkedList<>();
        int i = 0, j = 0;
        int windowSize = 0;
        long[] negNums = new long[n - k + 1];
        int idx = 0;

        while (j < n) {

            if (arr[j] < 0) {
                queue.add(arr[j]);
            }

            windowSize = j - i + 1;

            if (windowSize < k) {
                j++;

            } else if (windowSize == k) {

                if (!queue.isEmpty()) {

                    long currNum = queue.peek();
                    negNums[idx++] = currNum;

                    if (currNum == arr[i]) {
                        queue.remove();
                    }

                } else {
                    negNums[idx++] = 0;
                }
                i++;
                j++;
            }
        }

        return negNums;
    }
}
