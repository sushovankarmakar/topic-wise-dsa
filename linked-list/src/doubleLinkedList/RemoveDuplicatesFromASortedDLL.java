package src.doubleLinkedList;

import src.nodes.DoubleLLNode;

import static src.nodes.DoubleLLNode.print;
import static src.nodes.DoubleLLNode.printInReverse;

/**
 * https://www.youtube.com/watch?v=YJKVTnOJXSY (Striver)
 * <p>
 * https://practice.geeksforgeeks.org/problems/remove-duplicates-from-a-sorted-doubly-linked-list/1
 * https://www.codingninjas.com/studio/problems/remove-duplicates-from-a-sorted-doubly-linked-list_2420283
 */
public class RemoveDuplicatesFromASortedDLL {

    public static void main(String[] args) {
        DoubleLLNode initialHead = DoubleLLNode.create(new int[]{1, 1, 1, 2, 3, 3, 3, 4, 4, 4});    // linked list is sorted.
        System.out.println("Deleting all the duplicate elements");
        DoubleLLNode newHead = removeDuplicates_1(initialHead);
        print(newHead);
        printInReverse(newHead);
    }

    private static DoubleLLNode removeDuplicates_1(DoubleLLNode head) {

        // BASE CONDITION CHECK : if the head is null or there is only one element
        if (head == null || head.next == null) return head;

        DoubleLLNode mover = head;

        while (mover != null) {

            DoubleLLNode nextNode = mover.next;

            // if there is duplicates, then those duplicates will be in contiguous manner.
            while (nextNode != null && mover.val == nextNode.val) {
                nextNode = nextNode.next;
            }

            mover.next = nextNode;
            if (nextNode != null) nextNode.prev = mover;

            mover = mover.next;
        }

        return head;
    }

    private static DoubleLLNode removeDuplicates(DoubleLLNode head) {

        // BASE CONDITION CHECK : if the head is null or there is only one element
        if (head == null || head.next == null) return head;

        DoubleLLNode dummyHead = new DoubleLLNode(-1);
        dummyHead.next = head;
        head.prev = dummyHead;

        DoubleLLNode mover = dummyHead;

        while (mover != null) {

            DoubleLLNode prevNode = mover.prev;
            DoubleLLNode nextNode = mover.next;

            if (nextNode != null && nextNode.val == mover.val) {

                if (prevNode != null) prevNode.next = nextNode;
                if (nextNode != null) nextNode.prev = prevNode;

                mover.prev = null;
                mover.next = null;
            }

            mover = nextNode;
        }

        return dummyHead.next;
    }

}
