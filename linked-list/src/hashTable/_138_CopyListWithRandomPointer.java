package src.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/LL_CloneLLWithNextAndRandomPtr.java
 * <p>
 * https://www.youtube.com/watch?v=OvpKeraoxW0&ab_channel=BackToBackSWE (Best Video Tutorial)
 * <p>
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * https://practice.geeksforgeeks.org/problems/clone-a-linked-list-with-next-and-random-pointer/1
 * <p>
 * https://workat.tech/problem-solving/practice/clone-list-random-pointer
 * https://workat.tech/problem-solving/practice/clone-list-random-pointer/editorial (2nd approach is by HashMap)
 */
public class _138_CopyListWithRandomPointer {

    /**
     * Two types of solutions :
     *
     * 1. Using hashmap (using extra space.)
     * 2. Using no extra space.
     */

    /**
     * https://www.youtube.com/watch?v=q570bKdrnlw&ab_channel=takeUforward (Take you forward) - till 12:45.
     * <p>
     * time : O(2N)
     * space : O(N) + O(N)
     */
    private static ListNode cloneLL(ListNode head) {

        Map<ListNode, ListNode> map = new HashMap<>();  // map contains actual node and cloned nodes.

        ListNode curr = head;
        while (curr != null) {
            ListNode clone = new ListNode(curr.val);
            map.put(curr, clone);

            curr = curr.next;
        }

        for (Map.Entry<ListNode, ListNode> entry : map.entrySet()) {

            ListNode actual = entry.getKey();
            ListNode clone = entry.getValue();

            clone.next = map.get(actual.next);      // connecting next and random pointers in cloned nodes
            clone.random = map.get(actual.random);
        }

        return map.get(head);
    }

    // --------------------------- OPTIMAL CODE ------------------------------------------------------------------
    /**
     * 1. Insert copy nodes in between.
     * 2. Connect random pointers
     * 3. Connect next pointers.
     */

    /**
     * https://www.youtube.com/watch?v=q570bKdrnlw&ab_channel=takeUforward (Take you forward)
     *
     * time complexity : O(3n) = O(n)
     * space complexity : O(1)
     */
    private ListNode copyRandomList(ListNode head) {

        /**
         * FIRST PASS :
         * Clone each node and link each original node to it's copy via the original node's next pointer
         */
        ListNode curr = head;
        while (curr != null) {
            ListNode clone = new ListNode(curr.val);  // Create the copy node.
            clone.next = curr.next;
            curr.next = clone;

            curr = curr.next.next;  // moving the current pointer forward
        }

        /**
         * SECOND PASS :
         * Assign clone the random mapping.
         * we'll use each original node's next value to map to the clone allowing us to reach the clone list.
         */
        curr = head;  // Reset curr to the head of the original list.
        while (curr != null && curr.next != null) {

            if (curr.random != null) {  // some node's random pointer may be null
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;  // moving the current pointer forward
        }

        /**
         * THIRD PASS :
         * Our goal is to restore the original list, and extract the copy list.
         */
        curr = head;
        ListNode dummyHead = new ListNode(-1);
        ListNode cloneCurr = dummyHead;

        while (curr != null) {
            cloneCurr.next = curr.next;
            curr.next = curr.next.next;

            curr = curr.next;             // moving the current pointer forward
            cloneCurr = cloneCurr.next;   // moving the current clone pointer forward
        }

        return dummyHead.next;
    }

    private static class ListNode {

        int val;
        ListNode next;
        ListNode random;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
