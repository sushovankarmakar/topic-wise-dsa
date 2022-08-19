package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 * https://www.geeksforgeeks.org/minimize-deletions-in-array-by-deleting-all-occurrences-of-any-number-such-that-array-size-is-reduced-to-at-least-half/
 * <p>
 * check leetcode solution section for better approach using bucket sort.
 * https://leetcode.com/problems/reduce-array-size-to-the-half/discuss/1319416/C%2B%2BJavaPython-HashMap-and-Sort-then-Counting-Sort-O(N)-Clean-and-Concise
 */
public class _1338_ReduceArraySizeToHalf {

    public static void main(String[] args) {
        System.out.println(minSetSize_mySolutionUsingHashMap(new int[]{3, 3, 3, 3, 5, 5, 5, 2, 2, 7})); // 2
        System.out.println(minSetSize_mySolutionUsingHashMap(new int[]{7, 7, 7, 7, 7, 7})); // 1
    }

    /**
     * STEPS :
     * 1. Count the frequency of each integer in the array.
     * 2. Start with an empty set, add to the set the integer with the maximum frequency.
     * 3. Keep Adding the integer with the max frequency until you remove at least half of the integers.
     * <p>
     * time complexity : O(nlogn)
     * space complexity : O(n)
     */
    private static int minSetSize_mySolutionUsingHashMap(int[] arr) {

        // step 1 -------------------
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // step 2 -------------------
        int[] freqArr = new int[freqMap.size()];
        int i = 0;

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            freqArr[i++] = entry.getValue();
        }

        Arrays.sort(freqArr);

        // step 3 -------------------
        int halfSize = arr.length / 2;
        int newSize = 0;
        int count = 0;
        int n = freqArr.length;

        for (int j = n - 1; j >= 0; j--) {

            newSize += freqArr[j];
            count++;

            if (newSize >= halfSize) {
                break;
            }
        }
        return count;
    }


}
