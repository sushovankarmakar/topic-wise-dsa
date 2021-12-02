package src;

/**
 * https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
 *
 * https://discuss.geeksforgeeks.org/comment/4f51dbe54863b2507fdb8dae8d2ed1c8
 */

public class SegregateEvenOddNodesInLL {

  public static void main(String[] args) {

    ListNode head = ListNode.create(new int[]{17, 15, 8, 12, 10, 5, 4, 1, 7, 6}); // 8 12 10 4 6 17 15 5 1 7
    ListNode.print(divide(head));

    ListNode head1 = ListNode.create(new int[]{1, 3,  5,  7}); // 1 3  5  7
    ListNode.print(divide(head1));

    ListNode head2 = ListNode.create(new int[]{1, 1, 3}); // 1 1 3
    ListNode.print(divide(head2));

  }

  private static ListNode divide(ListNode head) {

    ListNode oddFirst = null, oddLast = null;
    ListNode evenFirst = null, evenLast = null;
    ListNode current = head;

    while (current != null) {

      if  (current.val % 2 == 0) {

        if (evenFirst == null) { // evenFirst is null, then we are seeing first even node
          evenFirst = current;
          evenLast = current;
        } else {
          evenLast.next = current;
          evenLast = evenLast.next;
        }

      } else {

        if (oddFirst == null) { // oddFirst is null, then we are seeing first odd node
          oddFirst = current;
          oddLast = current;
        } else {
          oddLast.next = current;
          oddLast = oddLast.next;
        }
      }
      current = current.next;
    }

    if (oddFirst == null || evenFirst == null) {
      // either all nodes are even or all nodes are odd, so return the original head node.
      return head;
    }

    evenLast.next = oddFirst; // connect even list with odd list
    oddLast.next = null;

    return evenFirst; // return the first even node.
  }

}
