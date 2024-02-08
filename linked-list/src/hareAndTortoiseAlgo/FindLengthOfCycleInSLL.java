package src.hareAndTortoiseAlgo;

import src.nodes.SingleLLNode;

/**
 * https://www.youtube.com/watch?v=I4g1qbkTPus (Striver)
 * <p>
 * https://practice.geeksforgeeks.org/problems/find-length-of-loop/1
 * https://www.codingninjas.com/studio/problems/find-length-of-loop_8160455
 */
public class FindLengthOfCycleInSLL {

    public static int lengthOfLoop(SingleLLNode head) {

        SingleLLNode meetingPointNode = hasCycle(head);

        return meetingPointNode != null ? findLengthOfLoop(meetingPointNode) : 0;
    }

    private static SingleLLNode hasCycle(SingleLLNode head) {

        if (head == null) return null;

        SingleLLNode slow = head;
        SingleLLNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return slow;
            }
        }

        return null;
    }

    private static int findLengthOfLoop(SingleLLNode meetingPointNode) {

        int count = 1;
        SingleLLNode mover = meetingPointNode;

        while (mover.next != meetingPointNode) {
            mover = mover.next;
            count++;
        }

        return count;
    }
}
