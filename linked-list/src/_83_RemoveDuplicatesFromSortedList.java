package src;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * https://practice.geeksforgeeks.org/problems/remove-duplicate-element-from-sorted-linked-list/1
 *
 * GFG POTD : 28.08.2023 - Solved it by myself.
 */
public class _83_RemoveDuplicatesFromSortedList {

    Node removeDuplicates(Node head) {

        Node curr = head;

        while (curr != null) {

            Node next = curr.next;

            /**
             * below while loop is used to detect all the duplicates
             */
            while (next != null && curr.data == next.data) {
                next = next.next;
            }

            curr.next = next;
            curr = next;
        }

        return head;
    }
}
