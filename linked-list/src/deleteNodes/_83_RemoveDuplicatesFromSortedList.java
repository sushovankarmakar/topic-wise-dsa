package src.deleteNodes;

import src.nodes.SingleLLNode;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * https://practice.geeksforgeeks.org/problems/remove-duplicate-element-from-sorted-linked-list/1
 * <p>
 * GFG POTD : 28.08.2023 - Solved it by myself.
 */
public class _83_RemoveDuplicatesFromSortedList {

    SingleLLNode removeDuplicates(SingleLLNode head) {

        SingleLLNode curr = head;

        while (curr != null) {

            SingleLLNode next = curr.next;

            /**
             * below while loop is used to detect all the duplicates
             */
            while (next != null && curr.val == next.val) {
                next = next.next;
            }

            curr.next = next;
            curr = next;
        }

        return head;
    }
}
