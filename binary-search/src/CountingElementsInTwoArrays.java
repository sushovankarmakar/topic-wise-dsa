package src;

import java.util.ArrayList;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/counting-elements-in-two-arrays/1
 * <p>
 * Merge Sort + Binary Search (Find values less than or equals to)
 */
public class CountingElementsInTwoArrays {

    // Merge Sort + Binary Search (Find values less than or equals to)
    public static void main(String[] args) {
        System.out.println(countEleLessThanOrEqual(
                new int[]{1, 2, 3, 4, 7, 9},
                new int[]{2, 2, 2, 2, 2, 10}, 6, 6)); // [0, 5, 5, 5, 5, 5]

        System.out.println(countEleLessThanOrEqual(
                new int[]{1, 2, 3, 4, 7, 9},
                new int[]{0, 1, 2, 1, 1, 4}, 6, 6)); // [4, 5, 5, 6, 6, 6]
    }

    private static List<Integer> countEleLessThanOrEqual(int[] arr1, int[] arr2, int m, int n) {

        List<Integer> op = new ArrayList<>();

        // sorting the 2nd array
        quickSort(arr2, 0, n - 1);

        // apply binary search on 2nd array for each value of 1st array
        for (int i = 0; i < m; i++) {
            int pos = binarySearch(arr1[i], arr2, 0, n - 1);
            op.add(pos + 1);
        }
        return op;
    }

    private static int binarySearch(int val, int[] arr, int left, int right) {

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pivot = partition(a, low, high);
            quickSort(a, low, pivot - 1);
            quickSort(a, pivot + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int val = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j] <= val) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
