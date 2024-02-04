package src.reverseLinkedList;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://www.youtube.com/watch?v=aXQWhbvT3w0 (Striver)
 * <p>
 * https://www.codingninjas.com/studio/problems/add-one-to-a-number-represented-as-linked-list_920557
 * https://www.geeksforgeeks.org/problems/add-1-to-a-number-represented-as-linked-list/1
 */
public class AddOneToNumberRepresentedAsSLL {

    public static void main(String[] args) {
        SingleLLNode initialHead1 = SingleLLNode.create(new int[]{1, 2, 8});
        print(initialHead1);
        System.out.println("After adding 1");
        //SingleLLNode head1 = addOne(initialHead1);
        SingleLLNode head1 = addOne_recursive(initialHead1);
        print(head1);

        System.out.println("----------------");
        SingleLLNode initialHead2 = SingleLLNode.create(new int[]{9, 9, 9});
        print(initialHead2);
        System.out.println("After adding 1");
        //SingleLLNode head2 = addOne(initialHead2);
        SingleLLNode head2 = addOne_recursive(initialHead2);
        print(head2);
    }

    private static SingleLLNode addOne_recursive(SingleLLNode head) {
        int carry = func(head);

        if (carry > 0) {
            SingleLLNode newNode = new SingleLLNode(carry);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    private static int func(SingleLLNode head) {

        if (head == null) {
            return 1;
        }

        int carry = func(head.next);

        int sum = head.val + carry;
        int digit = sum % 10;
        carry = sum / 10;

        head.val = digit;

        return carry;
    }


    /**
     * We have to one to the number represented by single linked list.
     * 1. Reverse the single linked list. Now the last digit of the number is the head.
     * <p>
     * 2.
     * CASE 1:
     * ------
     * If the last (right most) digit of the number is less than or equal to 9,
     * then directly add that to that digit.
     * Before returning, reverse it again and then return it.
     * <p>
     * CASE 2:
     * ------
     * If the last digit it 9,
     * then if we add 1 to it, then carry should propagate.
     * <p>
     * At the end, if we have carry left, then we need
     *  i. reverse this list first.
     *  ii. create a new node with the carry value.
     *  iii. stitch this to head of linked list and return.
     */
    private static SingleLLNode addOne(SingleLLNode head) {

        if (head == null) return null;

        SingleLLNode reversedHead = reverse(head);

        if (reversedHead.val < 9) {
            reversedHead.val += 1;

            return reverse(reversedHead);
        }

        // if the last node's digit is 9, 
        // then after adding 1 it's digit will be 0 and carry will be 1
        reversedHead.val = 0;
        SingleLLNode mover = reversedHead.next;

        int digit = 0;
        int carry = 1;

        while (mover != null) {

            int sum = mover.val + carry;

            digit = sum % 10;
            carry = sum / 10;

            mover.val = digit;
            mover = mover.next;
        }

        head = reverse(reversedHead);

        if (carry > 0) {
            SingleLLNode newNode = new SingleLLNode(carry);
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    private static SingleLLNode reverse(SingleLLNode head) {

        if (head == null) return null;

        SingleLLNode curr = head;
        SingleLLNode prev = null;
        SingleLLNode next = null;

        while (curr != null) {

            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }
}
