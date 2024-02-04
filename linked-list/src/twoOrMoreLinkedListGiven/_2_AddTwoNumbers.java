package src.twoOrMoreLinkedListGiven;

import src.nodes.SingleLLNode;

/**
 * https://takeuforward.org/data-structure/add-two-numbers-represented-as-linked-lists/
 *
 * https://www.codingninjas.com/studio/problems/add-two-numbers_1170520
 * https://leetcode.com/problems/add-two-numbers/
 * https://www.callicoder.com/add-two-numbers/
 *
 * https://www.youtube.com/watch?v=LBVsXSMOIk4&ab_channel=takeUforward
 * https://www.youtube.com/watch?v=XmRrGzR6udg (Striver)
 */
public class _2_AddTwoNumbers {

    public SingleLLNode addTwoNumbers(SingleLLNode head1, SingleLLNode head2) {

        // base case checking
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        SingleLLNode dummyHead = new SingleLLNode(0);
        SingleLLNode currNode = dummyHead;
        int digit = 0;
        int carry = 0;

        while (head1 != null || head2 != null) {  // mistake I did : I put && instead of ||. which gives wrong value.

            int sum = carry;
            if (head1 != null) sum += head1.val;
            if (head2 != null) sum += head2.val;

            digit = sum % 10;       // sum 12, then digit = 12 % 10 = 2, carry = 12 / 10 = 1
            carry = sum / 10;

            SingleLLNode newNode = new SingleLLNode(digit); // 1. create a new node.
            currNode.next = newNode;                        // 2. link it with the previous node.
            currNode = currNode.next;                       // 3. move forward.

            if (head1 != null) head1 = head1.next;
            if (head2 != null) head2 = head2.next;
        }

        // edge case, if carry is remaining
        if (carry > 0) {
            SingleLLNode newNode = new SingleLLNode(carry);
            currNode.next = newNode;
        }

        return dummyHead.next;
    }

}
