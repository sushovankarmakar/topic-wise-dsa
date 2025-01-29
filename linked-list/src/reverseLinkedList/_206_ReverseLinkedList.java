package src.reverseLinkedList;

import src.nodes.SingleLLNode;

import java.util.Stack;

import static src.nodes.SingleLLNode.print;

/**
 * https://www.youtube.com/watch?v=D2vI2DNJGd8 (Striver)
 * https://www.youtube.com/watch?v=iRtLEoL-r-g&ab_channel=takeUforward
 * https://www.youtube.com/watch?v=O0By4Zq0OFc&ab_channel=BackToBackSWE
 * <p>
 * <p>
 * https://leetcode.com/problems/reverse-linked-list/
 * https://practice.geeksforgeeks.org/problems/reverse-a-linked-list/1/
 * https://www.callicoder.com/reverse-linked-list/
 * <p>
 * https://www.geeksforgeeks.org/reverse-a-linked-list/
 */
public class _206_ReverseLinkedList {

    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{1, 6, 2, 3, 4});
        print(initialHead);
        System.out.println("After reversing");
        SingleLLNode reversedHead = reverse_usingRecursion(initialHead);
        print(reversedHead);
    }

    private static SingleLLNode reverse_usingRecursion(SingleLLNode head) {

        SingleLLNode newHead = reverse(head);
        return newHead;
    }

    private static SingleLLNode reverse(SingleLLNode curr) {

        /* Base case:
        If the linked list is empty or has only one node,
        return the head as it is already reversed. */
        if (curr == null || curr.next == null) {
            return curr;    // we've reached the last position, so last node is the current newHead.
        }

        /* Recursive step:
        Reverse the linked list starting
        from the second node (head.next). */
        SingleLLNode newHead = reverse(curr.next);  // store the new head, this newHead will be returned in each steps

        /* Save a reference to the node following the current 'head' node. */
        SingleLLNode front = curr.next;

        /* Make the 'front' node point
        to the current
        'head' node in the
        reversed order. */
        front.next = curr; // reversing the list here.

        /* Break the link from
        the current 'head' node
        to the 'front' node
        to avoid cycles. */
        curr.next = null; // Very important to break the chain.

        /* Return the 'newHead,'
        which is the new
        head of the reversed
        linked list. */
        return newHead;
    }

    private static SingleLLNode reverse_withoutExtraSpace(SingleLLNode head) {

        if (head == null) return null;

        SingleLLNode prev = null;
        SingleLLNode curr = head;
        SingleLLNode front = null;

        while (curr != null) {
            front = curr.next;
            curr.next = prev;

            prev = curr;
            curr = front;
        }
        return prev;
    }

    private static SingleLLNode reverse_UsingStack(SingleLLNode head) {

        Stack<SingleLLNode> stack = new Stack<>();
        SingleLLNode mover = head;

        while (mover != null) {
            stack.push(mover);
            mover = mover.next;
        }

        SingleLLNode dummyHead = new SingleLLNode(-1);
        mover = dummyHead;

        while (!stack.isEmpty()) {
            SingleLLNode node = stack.pop();
            node.next = null;

            mover.next = node;
            mover = mover.next;
        }

        return dummyHead.next;
    }
}
