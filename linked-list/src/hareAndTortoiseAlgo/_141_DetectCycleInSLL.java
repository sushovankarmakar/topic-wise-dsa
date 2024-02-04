package src.hareAndTortoiseAlgo;

import src.nodes.SingleLLNode;

/**
 * Floyd's algorithm for cycle detection.
 * <p>
 * https://www.youtube.com/watch?v=_BG9rjkAXj8&feature=emb_logo (Best Video Explanation)
 * [Striver’s Video’s timestamp](https://youtu.be/wiOo4DC5GGA?t=820) : 13:40
 *
 * <p>
 * https://practice.geeksforgeeks.org/problems/detect-loop-in-linked-list/1/
 * https://leetcode.com/problems/linked-list-cycle/description/
 * https://www.codingninjas.com/studio/problems/cycle-detection-in-a-singly-linked-list_628974
 * <p>
 * My Github Solution :
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/LL_DetectCycleLoopInLinkedList.java
 */
public class _141_DetectCycleInSLL {

    /**
     * 1. At each iteration, slow is moving 1 step and fast moving 2 steps. So gap is getting reducing by 1 step at each iteration.
     * 2. So at some point of time, both slow and fast pointer will meet.
     */
    private static boolean hasCycle(SingleLLNode head) {

        if (head == null) return false;
        if (head.next == head) return true;

        SingleLLNode slow = head;
        SingleLLNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }
}
