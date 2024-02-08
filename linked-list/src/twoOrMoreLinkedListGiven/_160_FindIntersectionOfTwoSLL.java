package src.twoOrMoreLinkedListGiven;

import src.nodes.SingleLLNode;

/**
 * https://www.youtube.com/watch?v=0DYoPz2Tpt4 (Striver)
 * https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
 * <p>
 * https://practice.geeksforgeeks.org/problems/intersection-point-in-y-shapped-linked-lists/1
 * https://www.codingninjas.com/studio/problems/-intersection-of-two-linked-lists_630457
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 * <p>
 * My Github :
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/LL_IntersectionPointOfTwoLL.java
 */
public class _160_FindIntersectionOfTwoSLL {

    /**
     * 1. Take two mover pointers : one pointing to head-a and another pointing to head-b
     * 2. Move both the mover pointers one steps together until both the points meet at the intersection.
     *      a. If one of the pointers reaches at its end of linked list then move that to another linked list head.
     *      b. so the idea is to **make both the pointers travel the same distance** although initially they had different lengths.
     * 3. If both the pointer travels the same distance, then it will reach at the intersect point for sure.
     */
    public static int findIntersection(SingleLLNode headA, SingleLLNode headB) {

        SingleLLNode mover1 = headA;
        SingleLLNode mover2 = headB;

        while (mover1 != mover2) {

            mover1 = (mover1 != null) ? mover1.next : headB;
            mover2 = (mover2 != null) ? mover2.next : headA;
        }

        return (mover1 != null) ? mover1.val : -1;
    }
}
