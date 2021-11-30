package heap;

/*
 * https://practice.geeksforgeeks.org/problems/kth-smallest-element5635/1
 *
 * https://www.youtube.com/watch?v=iiYmgrD4h6M&ab_channel=CodingSimplified
 * https://www.youtube.com/watch?v=aXJ-p3Qa4TY&ab_channel=TECHDOSE
 *
 * what is heap : https://www.youtube.com/watch?v=t0Cq6tVNRBA&ab_channel=HackerRank
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElementInArray {

  public static void main(String[] args) {
    int[] arr = new int[]{7, 10, 4, 3, 20, 15};
    System.out.println(kthSmallestElement(arr, 3)); // 7
    System.out.println(kthSmallestElement(arr, 4)); // 10
  }

  /*
   * Use max heap.
   * https://www.geeksforgeeks.org/max-heap-in-java/
   */
  public static int kthSmallestElement(int[] arr, int k) {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    for (int i = 0; i < k; i++) {
      maxHeap.offer(arr[i]);
    }

    for (int i = k; i < arr.length; i++) {

      // top of the max heap represents the largest element in the heap
      // if top of heap is greater than current element,
      // then this element can be a possible candidate for kth small element,
      // so put that into the max heap.
      if (maxHeap.peek() > arr[i]) {
        maxHeap.poll();
        maxHeap.offer(arr[i]);
      }
    }

    return maxHeap.peek();
  }

}
