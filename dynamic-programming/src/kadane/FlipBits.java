package src.kadane;

/**
 * https://practice.geeksforgeeks.org/problems/flip-bits0240/1 - This problem comes in GFG POTD. 15th August 2023.
 * https://www.interviewbit.com/problems/flip/
 *
 * https://www.youtube.com/watch?v=Etkf8sRQdbU (The BEST Explanation. Make me understood why Kadane's algo is used here.)
 * https://www.youtube.com/watch?v=REX6m1CjZMA (Took the C++ code structure)
 */
public class FlipBits {

    public static void main(String[] args) {
        System.out.println(maxOnes(new int[]{1, 1, 1, 1}, 4));  // 4
        System.out.println(maxOnes(new int[]{0, 1, 1, 1}, 4));  // 4
        System.out.println(maxOnes(new int[]{1, 0, 0, 1, 0}, 5));  // 4
        System.out.println(maxOnes(new int[]{1, 0, 0, 1, 0, 0, 1}, 7));  // 6
    }

    public static int maxOnes(int[] arr, int n) {

        int[] leftAndRight = kadaneAlgo(arr);
        int left = leftAndRight[0];
        int right = leftAndRight[1];

        // CORNER CASE : when array contains no zeros.
        if (left == -1) return n;   // if there is no zero present in the array.

        for (int i = left; i <= right; i++) {

            arr[i] = arr[i] == 0 ? 1 : 0;
        }

        int oneCount = 0;
        for (int val : arr) {
            if (val == 1) {
                oneCount += 1;
            }
        }

        return oneCount;
    }

    private static int[] kadaneAlgo(int[] arr) {

        int curr = 0;
        int maxCurr = 0;
        int left = 0;
        int right = 0;

        int[] ans = new int[2]; // stores the value of maxLeft and maxRight.
        ans[0] = -1;    // storing default values
        ans[1] = -1;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 0) {
                curr++;
            } else {
                curr--;
            }

            if (curr > maxCurr) {
                maxCurr = curr;
                right = i;

                ans[0] = left;
                ans[1] = right;
            }

            if (curr < 0) {
                curr = 0;
                left = i + 1;   // start fresh from next index
            }
        }

        return ans;
    }
}
