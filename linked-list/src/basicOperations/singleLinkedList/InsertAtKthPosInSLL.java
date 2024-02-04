package src.basicOperations.singleLinkedList;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://youtu.be/VaECK03Dz-g?t=2322 (Striver)
 * https://takeuforward.org/linked-list/insert-before-the-kth-element-of-the-linked-list/
 */
public class InsertAtKthPosInSLL {

    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{1, 1, 2, 3, 4});
        print(initialHead);

        SingleLLNode head = SingleLLNode.create(new int[]{1, 1, 2, 3, 4});
        System.out.println("Inserting 9 at 1st position");
        SingleLLNode newHead = insert(head, 1, 9); // zero based indexing
        print(newHead);

        head = SingleLLNode.create(new int[]{1, 1, 2, 3, 4});
        newHead = insert(head, 0, 9);
        System.out.println("Inserting 9 at 0th position"); // zero based indexing
        print(newHead);

        head = SingleLLNode.create(new int[]{1, 1, 2, 3, 4});
        newHead = insert(head, 5, 9);
        System.out.println("Inserting 9 at 5th position"); // zero based indexing
        print(newHead);
    }

    private static SingleLLNode insert(SingleLLNode head, int pos, int val) {

        SingleLLNode newNode = new SingleLLNode(val);

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
            return newNode;
        }

        SingleLLNode mover = head;
        int count = 0;

        // Traverse the linked list to find the node at position (k - 1)
        while (mover != null) {

            if (count == pos - 1) {

                // Insert the new node after the node at position (k - 1)
                newNode.next = mover.next;
                mover.next = newNode;
                break;
            }

            count++;
            mover = mover.next;
        }

        return head;
    }
}
