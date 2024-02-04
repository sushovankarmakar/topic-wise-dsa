package src.mergeLinkedList;

import src.nodes.SingleLLNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=1zktEppsdig (Striver)
 * <p>
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * https://practice.geeksforgeeks.org/problems/merge-k-sorted-linked-lists/1
 * https://www.codingninjas.com/studio/problems/merge-k-sorted-lists_992772
 * <p>
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Heap_MergeKSortedLL.java
 */
public class _23_MergeKSortedLists {

    /**
     * MOST OPTIMAL SOLUTION
     */
    public static SingleLLNode mergeKLists_usingMinHeap(SingleLLNode[] lists) {

        /**
         * Use the built-in PriorityQueue library,
         * pass a Comparator to make poll() return the smallest item
         * instead of the largest item (default is the largest/highest) priority item.
         *
         * A "Comparator" just tells the structure how to compare individual items to keep the queue sorted
         */
        Queue<SingleLLNode> minHeap = new PriorityQueue<>(
                (node1, node2) -> Integer.compare(node1.val, node2.val)   // MAX HEAP : Integer.compare(node2.val, node1.val), MIN HEAP : Integer.compare(node1.val, node2.val)
        );

        /**
         * Add the head of each linked list to the Priority Queue,
         * the queue will move the node with the smallest value to the front.
         */
        for (SingleLLNode head : lists) {
            if (head != null) minHeap.add(head);
        }

        SingleLLNode dummyHead = new SingleLLNode(-1);
        SingleLLNode mover = dummyHead;

        /**
         * Process each node in the queue while there are nodes to process across all k lists
         */
        while (!minHeap.isEmpty()) {        // MISTAKE I DID : while (mover != null) - it was giving me NPE

            SingleLLNode smallestNode = minHeap.poll();
            mover.next = smallestNode;  // Append the node to the final list
            mover = mover.next;         // Advance the mover pointer


            /**
             * Add the node that is after the node we just appended to the priority queue for consideration (if it is not null)
             */
            if (smallestNode.next != null) minHeap.add(smallestNode.next);
        }

        return dummyHead.next;
    }

    /**
     * SOLUTION - 1
     */
    public SingleLLNode mergeKLists(SingleLLNode[] lists) {

        if (lists.length == 0) return null;

        SingleLLNode mergedHead = lists[0];

        for (int i = 1; i < lists.length; i++) {

            if (lists[i] == null) continue;

            mergedHead = merge(lists[i], mergedHead);
        }

        return mergedHead;
    }

    private SingleLLNode merge(SingleLLNode head1, SingleLLNode head2) {

        if (head1 == null) return head2;
        if (head2 == null) return head1;

        SingleLLNode dummyNode = new SingleLLNode(-1);
        SingleLLNode mover = dummyNode;

        SingleLLNode mover1 = head1;
        SingleLLNode mover2 = head2;

        while (mover1 != null && mover2 != null) {

            if (mover1.val <= mover2.val) {
                mover.next = mover1;
                mover = mover.next;

                mover1 = mover1.next;
            } else {

                mover.next = mover2;
                mover = mover.next;

                mover2 = mover2.next;
            }
        }

        mover.next = (mover1 != null) ? mover1 : mover2;

        return dummyNode.next;
    }
}
