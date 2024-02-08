package src;

import src.nodes.SingleLLNode;

/**
 * https://practice.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
 *
 * https://discuss.geeksforgeeks.org/comment/4f51dbe54863b2507fdb8dae8d2ed1c8
 */

public class SegregateEvenOddNodesInLL {

  public static void main(String[] args) {

    SingleLLNode head = SingleLLNode.create(new int[]{17, 15, 8, 12, 10, 5, 4, 1, 7, 6}); // 8 12 10 4 6 17 15 5 1 7
    SingleLLNode.print(divide(head));

    SingleLLNode head1 = SingleLLNode.create(new int[]{1, 3,  5,  7}); // 1 3  5  7
    SingleLLNode.print(divide(head1));

    SingleLLNode head2 = SingleLLNode.create(new int[]{1, 1, 3}); // 1 1 3
    SingleLLNode.print(divide(head2));

  }

  /*
   * The idea is to split the linked list into two:
   * one containing all even nodes and other containing all odd nodes.
   * And finally, attach the odd node linked list after the even node linked list.
   *
   * To split the Linked List,
   * traverse the original Linked List and move all odd nodes to a separate Linked List of all odd nodes.
   * At the end of loop,
   * the original list will have all the even nodes and
   * the odd node list will have all the odd nodes.
   * To keep the ordering of all nodes same, we must insert all the odd nodes at the end of the odd node list.
   * And to do that in constant time, we must keep track of last pointer in the odd node list.
   *
   * https://www.geeksforgeeks.org/segregate-even-and-odd-elements-in-a-linked-list/ (2nd method)
   *
   * Time Complexity : O(N)
   * Space Complexity : O(1)
   */

  private static SingleLLNode divide(SingleLLNode head) {

    SingleLLNode oddFirst = null, oddLast = null;
    SingleLLNode evenFirst = null, evenLast = null;
    SingleLLNode current = head;

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
