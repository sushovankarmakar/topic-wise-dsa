package src.twoOrMoreLinkedListGiven;

import src.nodes.SingleLLNode;

import java.util.Stack;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 * https://practice.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1
 * <p>
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/2.%20Easy/src/LL_AddTwoNumRepresentedByLL.java
 * https://www.youtube.com/watch?v=pmS2GAlRNNk&t=699s&ab_channel=Pepcoding (First reverse both list and then solve.)
 */
public class _445_AddTwoNumbers_II {

    /**
     * Taken similar approach like _2_AddTwoNumbers. Very similar code.
     */
    public SingleLLNode addTwoNumbers(SingleLLNode l1, SingleLLNode l2) {

        Stack<Integer> stack1 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        Stack<Integer> stack2 = new Stack<>();
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        SingleLLNode dummyNode = new SingleLLNode();
        SingleLLNode currNode = dummyNode;

        int sum = 0;
        int carry = 0;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {

            int val1 = !stack1.isEmpty() ? stack1.pop() : 0;
            int val2 = !stack2.isEmpty() ? stack2.pop() : 0;

            sum = val1 + val2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            SingleLLNode newNode = new SingleLLNode(digit);
            newNode.next = currNode.next;   // this is basically putting newNode in between two nodes.
            currNode.next = newNode; // here we're not moving the currNode, currNode is still, adding values next to currNode only.
        }

        if (carry > 0) {

            SingleLLNode newNode = new SingleLLNode(carry);
            newNode.next = currNode.next;
            currNode.next = newNode;
        }

        return dummyNode.next;

    }

}
