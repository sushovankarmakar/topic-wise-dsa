package src.hareAndTortoiseAlgo;

import src.nodes.SingleLLNode;

/**
 * https://www.youtube.com/watch?v=7LjQ57RqgEc (Striver)
 * <p>
 * https://leetcode.com/problems/middle-of-the-linked-list/description/
 * https://www.codingninjas.com/studio/problems/middle-of-linked-list_973250
 * <p>
 * My Github link :
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/1.%20Basic/src/LL_FindMiddleOfLL.java
 */
public class _876_MiddleOfSingleLinkedList {

    /**
     * 1. if the linked list length is odd,
     * then the fast pointer will reach the last node (condition : fast.next != null)  at the end and slow pointer will be at mid element
     * 2. if the linked list length is even,
     * then the fast pointer will reach the null (condition : fast.next != null) at the end and slow pointer will be at mid element
     */
    private static SingleLLNode getTheMidElement(SingleLLNode head) {
        if (head == null || head.next == null) return head; /* if there is only one node, return that */

        SingleLLNode slow = head;
        SingleLLNode fast = head;

        // if we've even length, then we've two middle nodes, m1 and m2. we need to stand at m1.
        // to stand at m1, fast.next != null && fast.next.next != null
        // to stand at m2, fast.next != null
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // returning middle node for odd sized list OR
        // returning m2 node for even sized list
        return slow;    /* this point slow pointer points to the mid-element of the linked list */
    }
}
