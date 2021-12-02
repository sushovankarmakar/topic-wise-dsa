package src;

public class ListNode {

  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }

  public static void print(ListNode head) {
    while(head != null) {
      System.out.print(head.val + " ");
      head = head.next;
    }
    System.out.println();
  }

  public static ListNode create(int[] values) {

    ListNode head = new ListNode(values[0]);
    ListNode tail = head;

    for (int i = 1; i < values.length; i++) {
      tail.next = new ListNode(values[i]);
      tail = tail.next;
    }
    return head;
  }


}
