package src;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * https://www.callicoder.com/add-two-numbers/
 *
 * https://www.youtube.com/watch?v=LBVsXSMOIk4&ab_channel=takeUforward
 */
public class _2_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode currNode = dummyHead;
        int sum = 0;
        int carry = 0;

        while (l1 != null || l2 != null) {  // mistake I did : I put && instead of ||. which gives wrong value.

            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            sum = val1 + val2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            ListNode newNode = new ListNode(digit); // 1. create a new node.
            currNode.next = newNode;                // 2. link it with the previous node.
            currNode = currNode.next;               // 3. move forward.

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            currNode.next = newNode;
        }

        return dummyHead.next;
    }

}
