package src;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://leetcode.com/problems/rotate-list/
 * https://practice.geeksforgeeks.org/problems/rotate-a-linked-list/1/
 */
public class _61_RotateLLRightByKPlaces {

    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        print(initialHead);
        SingleLLNode newHead = rotateRight(initialHead, 8);
        print(newHead);
    }

    private static SingleLLNode rotateRight(SingleLLNode head, int k) {

        // if the head is null or there is no rotation, then return head
        if (head == null || k == 0) return head;

        SingleLLNode tail = head;
        int length = 1;             // REMEMBER : we've to initialize the length from 1

        while (tail.next != null) { // REMEMBER : condition should NOT be tail != null, it is (tail.next != null)
            length++;
            tail = tail.next;
        }

        k = k % length;

        if (k == 0) return head;

        int count = length - k - 1;     // MISTAKE I DID : here count is zero based, so we need to - 1 here.

        SingleLLNode mover = head;
        while (count > 0) {
            mover = mover.next;
            count--;
        }

        // node recreation
        SingleLLNode newHead = mover.next;
        mover.next = null;
        tail.next = head;

        return newHead;
    }
}
