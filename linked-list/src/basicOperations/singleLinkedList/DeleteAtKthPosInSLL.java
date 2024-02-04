package src.basicOperations.singleLinkedList;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://youtu.be/VaECK03Dz-g?t=947 (Striver)
 * https://takeuforward.org/linked-list/delete-the-kth-element-of-a-linked-list/
 */
public class DeleteAtKthPosInSLL {

    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        print(initialHead);

        SingleLLNode head = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        System.out.println("Deleting 1st element");
        SingleLLNode newHead = delete(head, 1); // zero based indexing
        print(newHead);

        head = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        newHead = delete(head, 0);
        System.out.println("Deleting 0th element"); // zero based indexing
        print(newHead);

        head = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        newHead = delete(head, 4);
        System.out.println("Deleting 4th element"); // zero based indexing
        print(newHead);
    }

    private static SingleLLNode delete(SingleLLNode head, int pos) {

        // Check if the list is empty
        if (head == null) {
            return null;
        }

        // If k is 0, delete the first node
        if (pos == 0) {
            return head.next;
        }

        SingleLLNode mover = head;
        int count = 0;

        // Traverse the list to find the (k - 1)th node
        while (mover.next != null) {

            // we have to stop at (k - 1) so that we can delete the node at k
            if (count == pos - 1) {
                mover.next = mover.next.next;
                return head;
            }

            mover = mover.next;
            count++;
        }

        return head;
    }
}
