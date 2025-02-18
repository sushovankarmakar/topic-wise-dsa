package src.deleteNodes;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://www.youtube.com/watch?v=3kMKYQ2wNIU (Striver)
 * <p>
 * <p>
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
 * https://practice.geeksforgeeks.org/problems/nth-node-from-end-of-linked-list/1
 * https://www.codingninjas.com/studio/problems/delete-kth-node-from-end_799912
 * <p>
 * https://takeuforward.org/data-structure/remove-n-th-node-from-the-end-of-a-linked-list/
 */
public class _19_DeleteNthNodeFromEnd {

    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{9, 6, 10, 12, 16});
        print(initialHead);
        SingleLLNode newHead = removeNthFromEnd(initialHead, 1);    // 1 base indexing
        System.out.println("Deleting 1st Node from back");
        print(newHead);

        System.out.println("----------");
        SingleLLNode initialHead1 = SingleLLNode.create(new int[]{9, 6, 10, 12, 16});
        print(initialHead1);
        SingleLLNode newHead1 = removeNthFromEnd(initialHead1, 5);  // 1 base indexing
        System.out.println("Deleting 5th Node from back");
        print(newHead1);

        System.out.println("----------");
        SingleLLNode initialHead2 = SingleLLNode.create(new int[]{9, 6, 10, 12, 16});
        print(initialHead2);
        SingleLLNode newHead2 = removeNthFromEnd(initialHead2, 3);  // 1 base indexing
        System.out.println("Deleting 3th Node from back");
        print(newHead2);
    }

    private static SingleLLNode removeNthFromEnd(SingleLLNode head, int n) {

        // base case. if there is only one node and n == 1, then return null.
        if (head == null || (head.next == null && n == 1)) {
            return null;
        }

        SingleLLNode back = head;
        SingleLLNode front = head;

        while (n > 0) { //  Move the fast pointer N nodes ahead of the slow pointer.
            front = front.next;
            n = n - 1;
        }

        // EDGE CASE :
        // front will be null, if n == length of linked list,
        // in that case, we need to delete the head.
        if (front == null) return head.next;

        while (front.next != null) {
            front = front.next;
            back = back.next;
        }

        back.next = back.next.next;
        return head;
    }

    public SingleLLNode findKthFromEnd(SingleLLNode head, int k) {

        SingleLLNode back = head, front = head;

        while (k-- > 0) {
            if (front == null) return null; // this condition is VERY IMPORTANT
            front = front.next;
        }

        while (front != null) {
            back = back.next;
            front = front.next;
        }

        return back;
    }

    // https://chat.deepseek.com/a/chat/s/18b039bb-c0d4-41d4-aa70-6643c809c902
    /**
     * Approach
     * --------
     * Dummy Node:
     * Use a dummy node to simplify edge cases, especially when the head node needs to be removed.
     * --
     * Two Pointers:
     * Utilize two pointers, slow and fast, both starting at the dummy node.
     * --
     * Advance Fast Pointer:
     * Move the fast pointer n + 1 steps ahead of the slow pointer. This creates a gap of n nodes between the two pointers.
     * --
     * Traverse to End:
     * Move both pointers simultaneously until the fast pointer reaches the end of the list (null).
     * At this point, the slow pointer will be positioned just before the node to be removed.
     * --
     * Remove Node:
     * Adjust the next pointer of the node pointed to by slow to skip the nth node from the end.
     */
    public SingleLLNode removeNthFromEnd_UsingDummyNode(SingleLLNode head, int n) {
        SingleLLNode dummy = new SingleLLNode(0);
        dummy.next = head;
        SingleLLNode slow = dummy;
        SingleLLNode fast = dummy;

        // Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
    }
}
