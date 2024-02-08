package src.basicOperations.doubleLinkedList;

import src.nodes.DoubleLLNode;

import static src.nodes.DoubleLLNode.print;
import static src.nodes.DoubleLLNode.printInReverse;

/**
 * https://www.youtube.com/watch?v=0eKMU10uEDI (Striver)
 * https://takeuforward.org/doubly-linked-list/insert-before-the-kth-node-of-a-doubly-linked-list/
 */
public class InsertAtKthPosInDLL {

    public static void main(String[] args) {
        DoubleLLNode initialHead = DoubleLLNode.create(new int[]{1, 6, 2, 3, 4});
        print(initialHead);
        printInReverse(initialHead);

        DoubleLLNode head = DoubleLLNode.create(new int[]{1, 6, 2, 3, 4});
        System.out.println("Inserting 9 at 1st position");
        DoubleLLNode newHead = insert(head, 1, 9); // zero based indexing
        print(newHead);
        printInReverse(newHead);

        head = DoubleLLNode.create(new int[]{1, 6, 2, 3, 4});
        newHead = insert(head, 0, 9);
        System.out.println("Inserting 9 at 0th position"); // zero based indexing
        print(newHead);
        printInReverse(newHead);

        head = DoubleLLNode.create(new int[]{1, 6, 2, 3, 4});
        newHead = insert(head, 4, 9);
        System.out.println("Inserting 9 at 4th position"); // zero based indexing
        print(newHead);
        printInReverse(newHead);
    }

    private static DoubleLLNode insert(DoubleLLNode head, int pos, int val) {

        DoubleLLNode newNode = new DoubleLLNode(val);

        // If the linked list is empty and pos is 0, insert the new node as the head
        if (head == null) {
            if (pos == 0) {
                return newNode;
            } else {
                return null;
            }
        }

        // If k is 0, insert the new node at the beginning of the linked list
        if (pos == 0) {
            newNode.next = head;
            head.prev = newNode;
            return newNode;
        }

        DoubleLLNode mover = head;
        int count = 0;

        // Traverse the linked list to find the node at position (k - 1)
        while (mover.next != null) {

            if (count == pos - 1) {

                // Insert the new node after the node at position (k - 1)
                // fit the new node.
                newNode.prev = mover;
                newNode.next = mover.next;

                // re-create
                mover.next.prev = newNode;
                mover.next = newNode;
                break;
            }

            count++;
            mover = mover.next;
        }

        return head;
    }
}
