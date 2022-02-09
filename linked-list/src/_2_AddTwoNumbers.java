package src;

/**
 * https://leetcode.com/problems/add-two-numbers/
 */
public class _2_AddTwoNumbers {

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    ListNode dummyHead = new ListNode(0);
    ListNode currNode = dummyHead;
    int sum = 0;
    int digit = 0;
    int rem = 0;

    while (l1 != null || l2 != null) {

      int val1 = (l1 != null) ? l1.val : 0;
      int val2 = (l2 != null) ? l2.val : 0;

      sum = val1 + val2 + rem;
      digit = sum % 10;
      rem = sum / 10;

      ListNode newNode = new ListNode(digit); // 1. create a new node.
      currNode.next = newNode;                // 2. link it with the previous node.
      currNode = currNode.next;               // 3. move forward.

      if (l1 != null) l1 = l1.next;
      if (l2 != null) l2 = l2.next;
    }

    if (rem > 0) {
      ListNode newNode = new ListNode(rem);
      currNode.next = newNode;
    }

    return dummyHead.next;
  }

}
