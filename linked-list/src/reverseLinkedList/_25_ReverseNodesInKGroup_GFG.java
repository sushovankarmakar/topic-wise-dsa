package src.reverseLinkedList;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://www.geeksforgeeks.org/problems/reverse-a-linked-list-in-groups-of-given-size/1
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/LL_ReverseLinkedListGroupsOfSizeK.java
 * https://www.youtube.com/watch?v=acJEBQiFPoY&ab_channel=VivekanandKhyade-AlgorithmEveryDay (A very good video tutorial)
 */
public class _25_ReverseNodesInKGroup_GFG {

    /**
     * VARIANT 1 :
     * If the number of nodes is not a multiple of k then left-out nodes, in the end, should be considered as a group and must be reversed
     * <p>
     * Input:
     * LinkedList: 1 -> 2 -> 3 -> 4 -> 5
     * K = 3
     * Output: 3 2 1 5 4
     * <p>
     * Explanation:
     * The first 3 elements are 1,2,3 are reversed
     * first and then elements 4,5 are reversed.Hence,
     * the resultant linked list is 3 -> 2 -> 1 -> 5 -> 4.
     */
    public static void main(String[] args) {
        SingleLLNode initialHead = SingleLLNode.create(new int[]{1, 2, 3, 4, 5});
        print(initialHead);
        System.out.println("After reversing");
        SingleLLNode reversedHead = reverseKGroup(initialHead, 3);
        print(reversedHead);
    }

    /**
     * RECURSIVE :
     * time : O(n)
     * space : O(n/k)
     */
    private static SingleLLNode reverseKGroup(SingleLLNode head, int k) {

        SingleLLNode prev = null, curr = head, next = null;
        int count = 0;

        while (count < k && curr != null) {

            next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;

            count++;
        }

        if (next != null) {
            head.next = reverseKGroup(next, k);
        }

        return prev;
    }
}
