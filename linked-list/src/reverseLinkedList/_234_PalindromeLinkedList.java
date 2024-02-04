package src.reverseLinkedList;

import src.nodes.SingleLLNode;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=lRY_G-u_8jk (Striver)
 * <p>
 * https://leetcode.com/problems/palindrome-linked-list/
 * https://practice.geeksforgeeks.org/problems/check-if-linked-list-is-pallindrome/1
 * <p>
 * https://leetcode.com/problems/palindrome-linked-list/discuss/64501/Java-easy-to-understand
 * https://www.geeksforgeeks.org/function-to-check-if-a-singly-linked-list-is-palindrome/
 */
public class _234_PalindromeLinkedList {

    public static void main(String[] args) {
        SingleLLNode head = SingleLLNode.create(new int[]{1, 1, 2, 3, 4});
        System.out.println(isPalindrome_usingStack(head));  // false

        SingleLLNode head1 = SingleLLNode.create(new int[]{3, 2, 1, 2, 3});
        System.out.println(isPalindrome_usingStack(head1)); // true

        SingleLLNode head2 = SingleLLNode.create(new int[]{3, 2, 2, 3});
        System.out.println(isPalindrome_usingStack(head2)); // true

        // OPTIMAL SOLUTION APPROACH
        SingleLLNode head3 = SingleLLNode.create(new int[]{3, 2, 1, 2, 3});
        System.out.println(isPalindrome(head3)); // true

        SingleLLNode head4 = SingleLLNode.create(new int[]{3, 2, 2, 3});
        System.out.println(isPalindrome(head4)); // true
    }

    /**
     * time : O(n)
     * space : O(n)
     */
    private static boolean isPalindrome_usingStack(SingleLLNode head) {

        Stack<Integer> stack = new Stack<>();

        SingleLLNode mover = head;
        while (mover != null) {

            stack.push(mover.val);
            mover = mover.next;
        }

        mover = head;

        while (!stack.isEmpty()) {

            if (mover.val != stack.peek()) {
                return false;
            }

            stack.pop();
            mover = mover.next;
        }

        return true;
    }

    /**
     * OPTIMAL SOLUTION APPROACH
     *
     * time : O(n)
     * space : (1)
     *
     * 1. find out the middle element of the linked list.
     * 2. reverse the second half using the middle element.
     * 3. now traverse from front and back simultaneously to check it is palindrome or not
     */
    private static boolean isPalindrome(SingleLLNode head) {

        /**
         * for odd number of nodes, stand at the middle node.
         * for even number of nodes, there are two middle nodes m1 and m2. so stand at m1.
         */
        SingleLLNode mid = getMid(head);

        /**
         * in case of odd number of nodes, there is only middle. (first_half) , middle, (second_half)
         * so to reverse the second half, pass middle.next to reverse function.
         *
         * in case of even number of nodes, there is two middle nodes. (first_half, m1), (m2, second_half)
         * so to reverse the second half, pass the m1.next to reverse function.
         */
        SingleLLNode reverseHead = reverse(mid.next);

        /**
         * original list given:
         * 1 -> 2 ->  3  ->  3 -> 2 -> 1
         *           m1     m2
         *
         * first_half = (1 -> 2 -> 3)
         * second_half = (3 -> 2 -> 1)
         *
         * after reversing the second half :
         * 1 -> 2 -> 3 -> 3 <- 2 <- 1
         * f                        b
         *
         * f = front
         * b = back
         */

        SingleLLNode front = head;
        SingleLLNode back = reverseHead;

        while (front != null && back != null) {

            if (front.val != back.val) {
                return false;
            }

            front = front.next;
            back = back.next;
        }
        return true;
    }

    private static SingleLLNode reverse(SingleLLNode head) {

        // 1 -> 2 -> 3
        // c
        // f
        // p

        if (head == null || head.next == null) return head;

        SingleLLNode curr = head;
        SingleLLNode prev = null;
        SingleLLNode front = null;

        while (curr != null) {
            front = curr.next;
            curr.next = prev;

            prev = curr;
            curr = front;
        }

        return prev;
    }

    private static SingleLLNode getMid(SingleLLNode head) {

        if (head == null) return head;

        SingleLLNode slow = head;
        SingleLLNode fast = head;

        // if we've even length, then we've two middle nodes, m1 and m2. we need to stand at m1.
        // to stand at m1, fast.next != null && fast.next.next != null
        // to stand at m2, fast.next != null
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // returning middle node for odd sized list OR
        // returning m1 node for even sized list
        return slow;
    }

}
