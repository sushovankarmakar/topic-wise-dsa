package src.sorting;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://www.youtube.com/watch?v=gRII7LhdJWc - Striver - changing links.
 * <p>
 * https://www.codingninjas.com/studio/problems/sort-linked-list-of-0s-1s-2s_1071937
 * https://www.geeksforgeeks.org/problems/given-a-linked-list-of-0s-1s-and-2s-sort-it/1
 */

public class SortSingleLinkedListOf012 {

    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{1, 0, 2, 1, 0, 2, 1});
        print(initialHead);
        SingleLLNode newHead = sortList(initialHead);
        print(newHead);
    }

    private static SingleLLNode sortList(SingleLLNode head) {

        if (head == null || head.next == null) return head;

        SingleLLNode mover = head;

        SingleLLNode zeroDummyHead = new SingleLLNode(-1); // dummy head
        SingleLLNode zeroMover = zeroDummyHead;

        SingleLLNode oneDummyHead = new SingleLLNode(-1); // dummy head
        SingleLLNode oneMover = oneDummyHead;

        SingleLLNode twoDummyHead = new SingleLLNode(-1); // dummy head
        SingleLLNode twoMover = twoDummyHead;

        while (mover != null) {

            if (mover.val == 0) {

                System.out.println("D " + mover.val);

                zeroMover.next = mover;
                zeroMover = zeroMover.next;

            } else if (mover.val == 1) {

                System.out.println("D " + mover.val);

                oneMover.next = mover;
                oneMover = oneMover.next;

            } else {

                System.out.println("D " + mover.val);

                twoMover.next = mover;
                twoMover = twoMover.next;
            }

            mover = mover.next;
        }

        // stitching zero with one
        zeroMover.next = oneDummyHead.next != null ? oneDummyHead.next : twoDummyHead.next;
        // stitching one with two
        oneMover.next = twoDummyHead.next;
        // stitching two with null. IMPORTANT or else infinite loop
        twoMover.next = null;
        // decide new head, after stitching
        SingleLLNode newHead = zeroDummyHead.next;
        return newHead;
    }
}
