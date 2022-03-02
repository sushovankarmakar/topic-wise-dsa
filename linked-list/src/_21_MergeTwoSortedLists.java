package src;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * https://practice.geeksforgeeks.org/problems/merge-two-sorted-linked-lists/1
 * https://www.callicoder.com/merge-two-sorted-linked-lists/#merge-two-sorted-linked-lists-problem
 * <p>
 * https://www.youtube.com/watch?v=GfRQvf7MB3k&ab_channel=BackToBackSWE (I followed this code)
 * https://www.youtube.com/watch?v=Xb4slcp1U38&ab_channel=takeUforward
 */
public class _21_MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummyNode = new ListNode(0);
        ListNode currNode = dummyNode;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {   // which node value is smaller,
                currNode.next = new ListNode(list1.val);
                currNode = currNode.next;

                list1 = list1.next;
            } else {
                currNode.next = new ListNode(list2.val);
                currNode = currNode.next;

                list2 = list2.next;
            }
        }

        // at this point, one of the list gets exhausted.
        if (list1 != null) {
            while (list1 != null) {
                currNode.next = new ListNode(list1.val);
                currNode = currNode.next;

                list1 = list1.next;
            }
        } else {
            while (list2 != null) {
                currNode.next = new ListNode(list2.val);
                currNode = currNode.next;

                list2 = list2.next;
            }
        }

        return dummyNode.next;
    }
}
