package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
 * https://practice.geeksforgeeks.org/problems/k-largest-elements3736/1
 *
 * https://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
 */

public class KLargestElementsInArray {

  public static void main(String[] args) {
    int[] arr = new int[]{1, 23, 12, 9, 30, 2, 50};
    System.out.println(kLargestElements(arr, 3)); // 50 30 23

    int[] arr1 = new int[]{12,5,787,1,23};
    System.out.println(kLargestElements(arr1, 2)); // 787 23
  }

  /*
   * Use Max Heap :
   * 1) Build a Max Heap tree in O(n)
   * 2) Use Extract Max k times to get k maximum elements from the Max Heap O(k*log(n))
   * Time complexity: O(n + k*log(n))
   */
  public static List<Integer> kLargestElements(int nums[], int k) {

    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    for (int num : nums) {
      maxHeap.offer(num);
    }

    List<Integer> kLargestElements = new ArrayList<>();
    while (k-- > 0) {
      kLargestElements.add(maxHeap.poll());
    }
    return kLargestElements;
  }

}
