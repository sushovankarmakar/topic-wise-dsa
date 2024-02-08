package src.designDataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/LL_LRUCache.java (Commented code. Best)
 * <p>
 * https://www.youtube.com/watch?v=S6IfqDXWa10&t=312s&ab_channel=BackToBackSWE (Best video. I followed this)
 * <p>
 * https://leetcode.com/problems/lru-cache/
 * https://workat.tech/problem-solving/practice/lru-cache
 * https://practice.geeksforgeeks.org/problems/lru-cache/1
 */
public class _146_LRUCache {

    private final Map<Integer, ListNode> map;
    private ListNode head;
    private ListNode tail;

    private int currentCapacity;
    private final int maxCapacity;

    public _146_LRUCache(int capacity) {
        this.map = new HashMap<>();

        this.head = new ListNode();
        this.tail = new ListNode();

        this.currentCapacity = 0;
        this.maxCapacity = capacity;

        this.head.next = tail;
        this.tail.prev = head;
    }

    public int get(int key) {
        ListNode currNode = map.get(key);

        if (currNode == null) {
            return -1;
        }

        moveToFront(currNode);
        return currNode.value;
    }

    public void add(int key, int value) {
        ListNode currNode = map.get(key);

        if (currNode == null) {
            currNode = new ListNode(key, value);
            map.put(key, currNode);

            addToFront(currNode);
            currentCapacity++;

            if (currentCapacity > maxCapacity) {
                removeFromTail();
            }
        } else {
            currNode.value = value;
            moveToFront(currNode);
        }
    }

    private void addToFront(ListNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void moveToFront(ListNode node) {
        removeFromList(node);
        addToFront(node);
    }

    private void removeFromList(ListNode node) {
        ListNode prevOfNodeToBeDeleted = node.prev;
        ListNode nextOfNodeToBeDeleted = node.next;

        prevOfNodeToBeDeleted.next = nextOfNodeToBeDeleted;
        nextOfNodeToBeDeleted.prev = prevOfNodeToBeDeleted;
    }

    private void removeFromTail() {
        ListNode tailNode = popFromTail();
        map.remove(tailNode.key);
        currentCapacity--;
    }

    private ListNode popFromTail() {
        ListNode tailNode = tail.prev;
        removeFromList(tailNode);
        return tailNode;
    }

    class ListNode {
        private int key;
        private int value;
        private ListNode prev;
        private ListNode next;

        ListNode() {

        }

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
