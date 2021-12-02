package src;

/*
 * https://leetcode.com/problems/odd-even-linked-list/
 * https://practice.geeksforgeeks.org/problems/rearrange-a-linked-list/1
 */

public class _328_OddEvenLinkedList {

  public static void main(String[] args) {

    ListNode head = ListNode.create(new int[]{1, 2, 3, 4, 5});
    ListNode.print(head);

    ListNode newHead = oddEvenList(head);
    ListNode.print(newHead);

    ListNode head1 = ListNode.create(new int[]{2,1,3,5,6,4,7});
    ListNode.print(head1);

    ListNode newHead2 = oddEvenList(head1);
    ListNode.print(newHead2);
  }

  public static ListNode oddEvenList(ListNode head) {

    if (head == null) return null;

    ListNode firstOddNode = head;
    ListNode firstEvenNode = head.next;

    ListNode odd = head;
    ListNode even = head.next;

    // `even != null` rules out the list of only 1 node
    // `even.next != null` rules out the list of only 2 nodes
    while (even != null && even.next != null) {
      odd.next = odd.next.next;     // Put odd to the odd list
      even.next = even.next.next;   // Put even to the even list

      odd = odd.next;         // Move the pointer to the next odd/even
      even = even.next;
    }
    odd.next = firstEvenNode;

    return firstOddNode;
  }


  //.........................................
  // while (odd != null && even != null && odd.next != null && even.next != null)
  // I first wrote it like this,
  // this also works.

}
