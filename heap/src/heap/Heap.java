package heap;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/heap-sort/1
 *
 * https://www.youtube.com/watch?v=HqPJF2L5h9U (Abdul Bari - Best lecture on Heap) - I watched this single video to clear my concepts.
 * https://www.youtube.com/watch?v=UVW0NfG_YWA (Anuj Bhaiya) - took help from here to code it out.
 */
public class Heap {

    public static void main(String[] args) {
        int[] arr = {5, 7, 2, 3, 5, 5};
        Solution s = new Solution();
        s.heapSort(arr, arr.length);

        Arrays.stream(arr)
                .forEach(val -> System.out.println(val + " "));
    }
}

class Solution {

    //Function to build a Heap from array.
    void buildHeap(int[] arr, int n) {

        // we start from n/2 value, because values after these are in the leaves and already heapfied.
        for (int i = (n / 2 - 1); i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    //Heapify function to maintain heap property.

    // heapify function assumes that all the below nodes under i, are already heapified.
    void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;     // i is zero based indexing
        int right = 2 * i + 2;    // i is zero based indexing

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, largest, i);
            heapify(arr, n, largest);
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //Function to sort an array using Heap Sort.
    public void heapSort(int[] arr, int n) {
        buildHeap(arr, n);

        for (int i = n - 1; i >= 0; i--) {
            swap(arr, i, 0);        // swap with the last value and first value.
            heapify(arr, i, 0);     // don't pass i - 1, pass i. as this is considered as length, not index in heapify method's argument. (MISTAKE I MADE)
        }
    }
}