package src.doubleLinkedList;

import src.nodes.DoubleLLNode;

import java.util.ArrayList;

/**
 * https://www.youtube.com/watch?v=YitR4dQsddE
 * <p>
 * https://practice.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1
 * https://www.codingninjas.com/studio/problems/find-pairs-with-given-sum-in-doubly-linked-list_1164172
 *
 * https://www.geeksforgeeks.org/find-pairs-given-sum-doubly-linked-list/
 */
public class FindAllPairsWithGivenSumInDLL {

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, DoubleLLNode head) {

        ArrayList<ArrayList<Integer>> pairs = new ArrayList<ArrayList<Integer>>();

        if (head == null) return pairs;

        DoubleLLNode front = head;
        DoubleLLNode back = head;
        while (back.next != null) {
            back = back.next;
        }

        // wrong condition I used :  front != back
        // if we use this, then output becomes : (1,4) (2,3) (3,2) (4,1) - which is wrong.
        // right output is : (1,4) (2,3)

        while (front.val < back.val) {

            int sum = front.val + back.val;

            if (sum == target) {

                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(front.val);
                list.add(back.val);

                pairs.add(list);

                front = front.next;
                back = back.prev;

            } else if (target < sum) { // target is smaller than sum, go left to reduce the sum
                back = back.prev;
            } else {                // target is bigger than sum, go right to reduce the sum
                front = front.next;
            }
        }

        return pairs;
    }

}
