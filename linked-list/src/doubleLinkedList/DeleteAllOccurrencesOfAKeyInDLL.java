package src.doubleLinkedList;

import src.nodes.DoubleLLNode;

import static src.nodes.DoubleLLNode.print;
import static src.nodes.DoubleLLNode.printInReverse;

/**
 * https://www.youtube.com/watch?v=Mh0NH_SD92k (Striver)
 * <p>
 * https://practice.geeksforgeeks.org/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list/1
 * https://www.codingninjas.com/studio/problems/delete-all-occurrences-of-a-given-key-in-a-doubly-linked-list_8160461
 */
public class DeleteAllOccurrencesOfAKeyInDLL {

    public static void main(String[] args) {
        DoubleLLNode initialHead = DoubleLLNode.create(new int[]{10, 10, 10, 4, 10, 3, 5, 20, 10});
        System.out.println("Deleting all the elements whose value is 10");
        DoubleLLNode newHead = deleteAllOccurrences(initialHead, 10);
        print(newHead);
        printInReverse(newHead);

        DoubleLLNode initialHead1 = DoubleLLNode.create(new int[]{9, 0, 6, 6, 7});
        System.out.println("Deleting all the elements whose value is 0");
        DoubleLLNode newHead1 = deleteAllOccurrences(initialHead1, 0);
        print(newHead1);
        printInReverse(newHead1);
    }

    public static DoubleLLNode deleteAllOccurrences(DoubleLLNode head, int k) {

        // 1. take one dummy head to ease of operations.
        // 2. it may happen that, at the head node's value is equal to k, so in that case we need to return new head.
        // 3. take a mover pointer and move it through the linked list.
        // 4. at each step, mark each node's prev node and next node.
        // 5. now if (mover.val == k)
        //      i. we've to recreate the link by connecting the prev node to next node.
        //      ii. free up the current node's links.
        // 6. at the end, return the dummyHead.next

        if (head == null) return null;

        DoubleLLNode dummyHead = new DoubleLLNode(-1);
        dummyHead.next = head;
        head.prev = dummyHead;

        DoubleLLNode mover = dummyHead;

        while (mover != null) {

            DoubleLLNode prevNode = mover.prev;
            DoubleLLNode nextNode = mover.next;

            if (mover.val == k) {

                // re-create
                // please do null check, it is useful for head and tail nodes or else Null pointer exception
                if (prevNode != null) prevNode.next = mover.next;
                if (nextNode != null) nextNode.prev = mover.prev;

                // free-up
                mover.next = mover.prev = null;
            }

            mover = nextNode;
        }

        return dummyHead.next;
    }
}
