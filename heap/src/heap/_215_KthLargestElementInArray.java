package heap;

import java.util.PriorityQueue;

/*
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * https://www.youtube.com/watch?v=iiYmgrD4h6M&ab_channel=CodingSimplified
 * https://www.youtube.com/watch?v=aXJ-p3Qa4TY&ab_channel=TECHDOSE
 *
 * what is heap : https://www.youtube.com/watch?v=t0Cq6tVNRBA&ab_channel=HackerRank
 */

public class _215_KthLargestElementInArray {

  public static void main(String[] args) {
    int[] arr = new int[]{7, 10, 4, 3, 20, 15};
    System.out.println(findKthLargest(arr, 3)); // 10

    int[] arr1 = new int[]{3,2,3,1,2,4,5,5,6};
    System.out.println(findKthLargest(arr1, 4));  // 4

    int[] arr2 = new int[]{3,2,1,5,6,4};
    System.out.println(findKthLargest(arr2, 2));  // 5
  }

  public static int findKthLargest(int[] nums, int k) {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int i = 0; i < k; i++) {
      minHeap.offer(nums[i]);
    }

    for (int i = k; i < nums.length; i++) {

      // top of the min heap represents the smallest element
      // so if the current element is greater than the top of the min heap
      // then the current element can be the future possible candidate for kth largest element
      // so put this element into min heap.

      if (minHeap.peek() < nums[i]) {
        minHeap.poll();
        minHeap.offer(nums[i]);
      }
    }

    return minHeap.peek();
  }

}
