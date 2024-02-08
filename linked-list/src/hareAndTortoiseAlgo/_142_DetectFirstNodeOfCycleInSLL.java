package src.hareAndTortoiseAlgo;

import src.nodes.SingleLLNode;

/**
 * https://www.youtube.com/watch?v=2Kd0KKmmHFc (Striver)
 * <p>
 * <p>
 * https://practice.geeksforgeeks.org/problems/remove-loop-in-linked-list/1/
 * https://www.codingninjas.com/studio/problems/linked-list-cycle-ii_1112628
 * https://leetcode.com/problems/linked-list-cycle-ii/description/
 * <p>
 * My Github :
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/LL_RemoveCycleLoopInLinkedList.java
 */
public class _142_DetectFirstNodeOfCycleInSLL {

    public static SingleLLNode firstNodeOfCycle(SingleLLNode head) {

        SingleLLNode meetingPointNode = hasCycle(head);

        if (meetingPointNode == null) return null;

        SingleLLNode slow = head;
        SingleLLNode fast = meetingPointNode;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    private static SingleLLNode hasCycle(SingleLLNode head) {

        if (head == null) return null;
        if (head.next == head) return head;

        SingleLLNode slow = head;
        SingleLLNode fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return slow;
        }

        return null;
    }
}
