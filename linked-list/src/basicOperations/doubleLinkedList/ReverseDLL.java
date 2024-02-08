package src.basicOperations.doubleLinkedList;

import src.nodes.DoubleLLNode;

import static src.nodes.DoubleLLNode.print;

/**
 * https://www.youtube.com/watch?v=u3WUW2qe6ww (Striver)
 *
 * https://takeuforward.org/data-structure/reverse-a-doubly-linked-list/
 *
 * https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1
 * https://www.codingninjas.com/studio/problems/reverse-a-doubly-linked-list_1116098
 */
public class ReverseDLL {

    public static void main(String[] args) {

        DoubleLLNode head = DoubleLLNode.create(new int[]{1, 6, 2, 3, 4});
        print(head);
        DoubleLLNode reversedHead = reverseDLL(head);
        System.out.println("After reversal");
        print(reversedHead);
    }

    public static DoubleLLNode reverseDLL(DoubleLLNode head) {

        if (head == null || head.next == null) return head;

        DoubleLLNode mover = head;

        while (mover != null) {

            // store prev, curr and next
            DoubleLLNode prev = mover.prev;
            DoubleLLNode curr = mover;
            DoubleLLNode next = mover.next;

            // reverse the link. 
            // store next in prev, and store prev in next
            curr.prev = next;
            curr.next = prev;

            // first assign and then move further.
            head = mover;
            // as links are reversed, we have to use mover.prev to go further
            mover = mover.prev;
        }

        return head;
    }
}
