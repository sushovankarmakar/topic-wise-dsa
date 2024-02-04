package src;

import src.nodes.SingleLLNode;

/**
 * https://leetcode.com/problems/odd-even-linked-list/
 * https://practice.geeksforgeeks.org/problems/rearrange-a-linked-list/1
 * <p>
 * https://www.youtube.com/watch?v=qf6qp7GzD5Q (Striver)
 */
public class _328_OddEvenLinkedList {

    public static void main(String[] args) {

        SingleLLNode head = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        SingleLLNode.print(head);

        SingleLLNode newHead = oddEvenList(head);
        SingleLLNode.print(newHead);

        SingleLLNode head1 = SingleLLNode.create(new int[]{2, 1, 3, 5, 6, 4, 7});
        SingleLLNode.print(head1);

        SingleLLNode newHead2 = oddEvenList(head1);
        SingleLLNode.print(newHead2);
    }

    public static SingleLLNode oddEvenList(SingleLLNode head) {

        if (head == null || head.next == null) return head;

        SingleLLNode firstOddNode = head;
        SingleLLNode firstEvenNode = head.next;

        SingleLLNode odd = head;
        SingleLLNode even = head.next;

        // even is ahead of odd, so check in terms of even
        // `even != null` rules out the list of only 1 node
        // `even.next != null` rules out the list of only 2 nodes

        while (even != null && even.next != null) {
            odd.next = odd.next.next;     // Put odd to the odd list
            even.next = even.next.next;   // Put even to the even list

            odd = odd.next;               // Move the pointer to the next odd/even
            even = even.next;
        }
        odd.next = firstEvenNode;         // connect odd list and even list together

        return firstOddNode;
    }


    //.........................................
    // while (odd != null && even != null && odd.next != null && even.next != null)
    // I first wrote it like this,
    // this also works.

}
