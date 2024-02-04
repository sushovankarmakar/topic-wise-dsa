package src.reverseLinkedList;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * https://www.youtube.com/watch?v=TeDcLjOOiK4&ab_channel=Fraz (Recursive)
 * https://www.youtube.com/watch?v=dbRJFnQoKTI&t=585s&ab_channel=Fraz (Iterative)
 */
public class _25_ReverseNodesInKGroup_LC {

    /**
     * VARIANT 2 :
     * If the number of nodes is not a multiple of k then left-out nodes, in the end, should be considered as a group and must be reversed
     * <p>
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [2,1,4,3,5]
     * <p>
     * Input: head = [1,2,3,4,5], k = 3
     * Output: [3,2,1,4,5]
     */
    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        print(initialHead);
        System.out.println("After reversing");
        SingleLLNode reversedHead = reverseKGroup(initialHead, 3); // 3 2 1 4 5
        print(reversedHead);
    }

    /**
     * RECURSIVE :
     * time : O(n)
     * space : O(n/k) - stack space.
     */
    private static SingleLLNode reverseKGroup(SingleLLNode head, int k) {

        if (head == null || head.next == null || k == 1) return head;

        SingleLLNode start = head;
        SingleLLNode end = head;
        int count = 1;

        while (count < k && end != null) {
            end = end.next;
            count++;
        }

        // https://youtu.be/TeDcLjOOiK4?t=897 - explanation. - MOST IMPORTANT
        if (end == null) return head; // if the length of linked list is smaller than the group size, just return head.

        // https://youtu.be/TeDcLjOOiK4?t=1272 (explanation. - why this recursive call should be called before the reverse function)
        SingleLLNode nextHead = reverseKGroup(end.next, k);

        reverse(start, end);

        start.next = nextHead; // connect the link. we should connect start with nextHead as start and end has already changed position.
        return end;
    }

    /**
     * ITERATIVE :
     * time : O(n)
     * space : O(1)
     */
    private static SingleLLNode reverseKGroup_usingConstantSpace(SingleLLNode head, int k) {

        if (head == null || head.next == null || k == 1) return head;

        SingleLLNode dummyHead = new SingleLLNode(-1);
        dummyHead.next = head;

        SingleLLNode beforeStart = dummyHead;
        SingleLLNode end = head;

        int i = 0;

        while (end != null) {

            i++;

            if (i % k == 0) {

                SingleLLNode start = beforeStart.next;
                SingleLLNode nextStart = end.next;

                reverse(start, end);

                beforeStart.next = end;
                start.next = nextStart;

                beforeStart = start;
                end = nextStart;
            } else {
                end = end.next;
            }
        }

        return dummyHead.next;
    }

    private static void reverse(SingleLLNode start, SingleLLNode end) {

        if (start == null) return;

        SingleLLNode prev = null;
        SingleLLNode curr = start;
        SingleLLNode next = null;

        // https://youtu.be/TeDcLjOOiK4?t=1102 (Explanation of why : curr != end)
        while (prev != end) {   // THIS IS CRUCIAL HERE. MISTAKE I MADE : curr != end. if we do this, then the last node don't get reversed.

            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
    }

}
