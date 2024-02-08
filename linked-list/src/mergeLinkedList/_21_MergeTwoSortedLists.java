package src.mergeLinkedList;

import src.nodes.SingleLLNode;

import static src.nodes.SingleLLNode.print;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * https://www.codingninjas.com/studio/problems/merge-two-sorted-linked-lists_800332
 * https://practice.geeksforgeeks.org/problems/merge-two-sorted-linked-lists/1
 * https://www.callicoder.com/merge-two-sorted-linked-lists/#merge-two-sorted-linked-lists-problem
 * <p>
 * https://www.youtube.com/watch?v=GfRQvf7MB3k&ab_channel=BackToBackSWE (I followed this code)
 * <p>
 * https://www.youtube.com/watch?v=jXu-H7XuClE (Striver) - I followed this NEW video.
 * https://www.youtube.com/watch?v=Xb4slcp1U38&ab_channel=takeUforward (Striver) - OLD Video
 */
public class _21_MergeTwoSortedLists {

    public static void main(String[] args) {

        SingleLLNode head1 = SingleLLNode.create(new int[]{1, 2, 3, 7, 10});
        SingleLLNode head2 = SingleLLNode.create(new int[]{4, 5, 8});

        SingleLLNode newHead1 = mergeTwoLists_extraSpace(head1, head2);
        print(newHead1);

        SingleLLNode newHead2 = mergeTwoLists_inPlace(head1, head2);
        print(newHead2);
    }

    private static SingleLLNode mergeTwoLists_inPlace(SingleLLNode head1, SingleLLNode head2) {

        // BASE CONDITION CHECK
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        SingleLLNode dummyNode = new SingleLLNode(-1);
        SingleLLNode mover = dummyNode;

        SingleLLNode mover1 = head1;
        SingleLLNode mover2 = head2;

        while (mover1 != null && mover2 != null) {

            if (mover1.val <= mover2.val) {   // which node value is smaller,
                mover.next = mover1;
                mover = mover.next;

                mover1 = mover1.next;
            } else {
                mover.next = mover2;
                mover = mover.next;

                mover2 = mover2.next;
            }
        }

        // at this point, one of the list gets exhausted.
        mover.next = (mover1 != null) ? mover1 : mover2;

        return dummyNode.next;
    }

    private static SingleLLNode mergeTwoLists_extraSpace(SingleLLNode list1, SingleLLNode list2) {

        if (list1 == null) return list2;
        if (list2 == null) return list1;

        SingleLLNode dummyNode = new SingleLLNode(-1);
        SingleLLNode mover = dummyNode;

        SingleLLNode mover1 = list1;
        SingleLLNode mover2 = list2;

        while (mover1 != null && mover2 != null) {

            if (mover1.val <= mover2.val) { // which node value is smaller,

                mover.next = new SingleLLNode(mover1.val);
                mover = mover.next;

                mover1 = mover1.next;

            } else {

                mover.next = new SingleLLNode(mover2.val);
                mover = mover.next;

                mover2 = mover2.next;
            }
        }

        // at this point, one of the list gets exhausted.
        while (mover1 != null) {

            mover.next = new SingleLLNode(mover1.val);
            mover = mover.next;

            mover1 = mover1.next;
        }

        while (mover2 != null) {

            mover.next = new SingleLLNode(mover2.val);
            mover = mover.next;

            mover2 = mover2.next;
        }

        return dummyNode.next;
    }
}
