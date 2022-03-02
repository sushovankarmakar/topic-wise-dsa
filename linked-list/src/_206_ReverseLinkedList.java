package src;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * https://practice.geeksforgeeks.org/problems/reverse-a-linked-list/1/
 * https://www.callicoder.com/reverse-linked-list/
 *
 * https://www.youtube.com/watch?v=iRtLEoL-r-g&ab_channel=takeUforward
 * https://www.youtube.com/watch?v=O0By4Zq0OFc&ab_channel=BackToBackSWE
 *
 * https://www.geeksforgeeks.org/reverse-a-linked-list/
 */
public class _206_ReverseLinkedList {

    public ListNode reverseList(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}
