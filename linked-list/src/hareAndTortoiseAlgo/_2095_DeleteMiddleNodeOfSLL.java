package src.hareAndTortoiseAlgo;

import src.nodes.SingleLLNode;

/**
 * https://www.youtube.com/watch?v=ePpV-_pfOeI
 * <p>
 * https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/
 * https://practice.geeksforgeeks.org/problems/delete-middle-of-linked-list/1
 * https://www.codingninjas.com/studio/problems/delete-middle-node_763267
 * <p>
 * https://www.geeksforgeeks.org/delete-middle-of-linked-list/
 */

public class _2095_DeleteMiddleNodeOfSLL {

    public static void main(String[] args) {
        SingleLLNode head = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        SingleLLNode.print(head);

        SingleLLNode newHead = deleteMiddleNode(head);
        System.out.println("After deleting middle element");
        SingleLLNode.print(newHead);
    }

    private static SingleLLNode deleteMiddleNode(SingleLLNode head) {
        if (head == null || head.next == null) return null;

        SingleLLNode slow = head;   // Initialize slow and fast pointer to reach middle of linked list
        SingleLLNode fast = head;
        SingleLLNode prev = head;   // keep track of the previous middle so the middle node can be deleted.

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = slow.next;  // Delete the middle node

        return head;
    }
}
