package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/find-pairs-with-given-sum-in-doubly-linked-list/1 (Solved it by myself)
 * <p>
 * https://www.geeksforgeeks.org/find-pairs-given-sum-doubly-linked-list/
 */
public class FindPairsWithGivenSum {

    private static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {

        ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();

        Node left = head;
        Node right = head;
        Node curr = head;

        while (curr != null) {
            right = curr;
            curr = curr.next;
        }

        while (left != right && left.data < right.data) {   // termination condition is important.

            int sum = left.data + right.data;

            if (sum == target) {
                pairs.add(new ArrayList<>(Arrays.asList(left.data, right.data)));

                left = left.next;
                right = right.prev;

            } else if (sum < target) {
                left = left.next;
            } else {
                right = right.prev;
            }
        }
        return pairs;
    }

    public static void main(String[] args) throws IOException {

        Node head = Node.inputList("1 2 4 5 6 8 9");
        ArrayList<ArrayList<Integer>> res = findPairsWithGivenSum(7, head);
        System.out.println(res);    //  [[1, 6], [2, 5]]
    }
}
