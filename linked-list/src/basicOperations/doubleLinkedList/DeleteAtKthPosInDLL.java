package src.basicOperations.doubleLinkedList;

import src.nodes.DoubleLLNode;

import static src.nodes.DoubleLLNode.print;
import static src.nodes.DoubleLLNode.printInReverse;

/**
 * https://www.youtube.com/watch?v=0eKMU10uEDI (Striver)
 * https://takeuforward.org/linked-list/delete-the-kth-element-of-a-linked-list/
 */
public class DeleteAtKthPosInDLL {

    public static void main(String[] args) {
        DoubleLLNode initialHead = DoubleLLNode.create(new int[]{1, 2, 3, 4, 5});
        print(initialHead);
        printInReverse(initialHead);

        DoubleLLNode head = DoubleLLNode.create(new int[]{1, 2, 3, 4, 5});
        System.out.println("Deleting 1st element");
        DoubleLLNode newHead = delete(head, 1); // zero based indexing
        print(newHead);
        printInReverse(newHead);

        head = DoubleLLNode.create(new int[]{1, 2, 3, 4, 5});
        newHead = delete(head, 0);
        System.out.println("Deleting 0th element"); // zero based indexing
        print(newHead);
        printInReverse(newHead);

        head = DoubleLLNode.create(new int[]{1, 2, 3, 4, 5});
        newHead = delete(head, 4);
        System.out.println("Deleting 4th element"); // zero based indexing
        print(newHead);
        printInReverse(newHead);
    }

    private static DoubleLLNode delete(DoubleLLNode head, int pos) {

        // Return null if the list is empty or contains only one element
        if (head == null || head.next == null) {
            return null;
        }

        // If k is 0, delete the first node
        if (pos == 0) {

            DoubleLLNode newHead = head.next;
            head.next = null;
            newHead.prev = null;

            return newHead;
        }

        DoubleLLNode mover = head;
        int count = 0;

        // Traverse the list to find the k-th node
        while (mover != null) {

            // we have to stop at kth,
            if (count == pos) {
                // re-create
                if (mover.prev != null) mover.prev.next = mover.next;
                if (mover.next != null) mover.next.prev = mover.prev;

                // free-up
                mover.prev = null;
                mover.next = null;

                break;
            }

            mover = mover.next;
            count++;
        }

        return head;
    }
}
