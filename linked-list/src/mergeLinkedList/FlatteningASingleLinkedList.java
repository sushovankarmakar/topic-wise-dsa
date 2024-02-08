package src.mergeLinkedList;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.youtube.com/watch?v=ykelywHJWLg (Striver)
 * <p>
 * https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1
 * https://www.geeksforgeeks.org/problems/flattening-a-linked-list--170645/1
 * https://www.codingninjas.com/studio/problems/flatten-a-linked-list_1112655
 */
public class FlatteningASingleLinkedList {

    /**
     * MOST OPTIMAL SOLUTION
     */
    private static Node flatten_usingMinHeap(Node root) {

        Queue<Node> minHeap = new PriorityQueue<>(
                (node1, node2) -> Integer.compare(node1.data, node2.data)   // MAX HEAP : Integer.compare(node2.val, node1.val), MIN HEAP : Integer.compare(node1.val, node2.val)
        );

        if (root == null) return root;

        Node head = root;
        while (head != null) {

            minHeap.add(head);
            head = head.next;
        }

        Node dummyHead = new Node(-1);
        Node mover = dummyHead;

        while (!minHeap.isEmpty()) {    // MISTAKE I DID : while (mover != null) - it was giving me NPE

            Node smallestNode = minHeap.poll();

            mover.child = smallestNode;
            mover = mover.child;

            if (smallestNode.child != null) minHeap.add(smallestNode.child);
        }

        return dummyHead.child;
    }

    /**
     * SOLUTION - 1
     */
    private static Node flatten(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node mergedHead = flatten(head.next);

        return merge(head, mergedHead);
    }

    private static Node merge(Node head1, Node head2) {

        if (head1 == null) return head2;
        if (head2 == null) return head1;

        Node dummyHead = new Node(-1);
        Node mover = dummyHead;

        Node mover1 = head1;
        Node mover2 = head2;

        while (mover1 != null && mover2 != null) {

            if (mover1.data <= mover2.data) {

                mover.child = mover1;
                mover = mover.child;

                mover1.next = null;     // MAKE SURE to mark the next pointer null, before moving to next child node
                mover1 = mover1.child;

            } else {

                mover.child = mover2;
                mover = mover.child;

                mover2.next = null;     // MAKE SURE to mark the next pointer null, before moving to next child node
                mover2 = mover2.child;
            }
        }

        mover.child = (mover1 != null) ? mover1 : mover2;

        return dummyHead.child; // I was returning dummyHead.next which is wrong.
    }

    static class Node {
        int data;
        Node next;
        Node child;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.child = null;
        }
    }
}
