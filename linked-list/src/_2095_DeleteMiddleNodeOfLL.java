package src;

/**
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 * https://practice.geeksforgeeks.org/problems/delete-middle-of-linked-list/1
 *
 * https://www.geeksforgeeks.org/delete-middle-of-linked-list/
 */

public class _2095_DeleteMiddleNodeOfLL {

  public static void main(String[] args) {
    ListNode head = ListNode.create(new int[]{1, 2, 3, 4, 5});
    ListNode.print(head);

    ListNode newHead = deleteMiddleNode(head);
    System.out.println("After deleting middle element");
    ListNode.print(newHead);
  }

  private static ListNode deleteMiddleNode(ListNode head) {
    if (head == null || head.next == null) return null;

    ListNode slow = head;   // Initialize slow and fast pointer to reach middle of linked list
    ListNode fast = head;
    ListNode prev = head;   // keep track of the previous middle so the middle node can be deleted.

    while (fast != null && fast.next != null) {
      prev = slow;
      slow = slow.next;
      fast = fast.next.next;
    }
    prev.next = slow.next;  // Delete the middle node

    return head;
  }
}
