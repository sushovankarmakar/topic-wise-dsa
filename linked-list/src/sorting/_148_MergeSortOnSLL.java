package src.sorting;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://www.youtube.com/watch?v=8ocB7a_c-Cc&ab_channel=takeUforward (Striver)
 *
 * https://leetcode.com/problems/sort-list/description/
 * https://www.codingninjas.com/studio/problems/sort-linked-list_625193
 * https://www.geeksforgeeks.org/problems/sort-a-linked-list/1
 * https://www.geeksforgeeks.org/problems/quick-sort-on-linked-list/1
 */
public class _148_MergeSortOnSLL {

    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{4, 2, 1, 3});
        print(initialHead);
        SingleLLNode newHead = sortList(initialHead);
        print(newHead);
    }

    private static SingleLLNode sortList(SingleLLNode head) {
        return mergeSort(head);
    }

    private static SingleLLNode mergeSort(SingleLLNode head) {

        // base condition
        if (head == null || head.next == null) return head;

        SingleLLNode mid = getMid(head);

        SingleLLNode leftHead = head;       // head represent the left linked list
        SingleLLNode rightHead = mid.next;  // mid.next represent the right linked list

        mid.next = null;    // we need to separate left and right linked list.

        leftHead = mergeSort(leftHead);     // IMPORTANT to assign it back
        rightHead = mergeSort(rightHead);   // IMPORTANT to assign it back

        return mergeTwoSortedList(leftHead, rightHead);
    }

    private static SingleLLNode getMid(SingleLLNode head) {

        SingleLLNode slow = head;
        SingleLLNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private static SingleLLNode mergeTwoSortedList(SingleLLNode head1, SingleLLNode head2) {

        SingleLLNode dummyHead = new SingleLLNode(-1);
        SingleLLNode mover = dummyHead;
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

        mover.next = (mover1 != null) ? mover1 : mover2;    // IMPORTANT STEP : I forget it sometimes.
        return dummyHead.next;
    }
}
