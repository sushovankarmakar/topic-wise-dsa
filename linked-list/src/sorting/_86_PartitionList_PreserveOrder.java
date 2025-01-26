package src.sorting;

import src.nodes.SingleLLNode;

/**
 * <a href="https://leetcode.com/problems/partition-list/">86. Partition List</a>
 */
public class _86_PartitionList_PreserveOrder {

    // concept based on Dutch National Flag Algorithm (DNF)
    public SingleLLNode partition(SingleLLNode head, int x) {

        if (head == null || head.next == null) return head;

        SingleLLNode dummyHeadSmallVal = new SingleLLNode(-1);
        SingleLLNode moverSmallVal = dummyHeadSmallVal;

        SingleLLNode dummyHeadBigVal = new SingleLLNode(-1);
        SingleLLNode moverBigVal = dummyHeadBigVal;

        SingleLLNode mover = head;
        while (mover != null) {

            if (mover.val < x) {
                moverSmallVal.next = mover;
                moverSmallVal = moverSmallVal.next;
            } else { // mover.val >= x
                moverBigVal.next = mover;
                moverBigVal = moverBigVal.next;
            }

            mover = mover.next;
        }

        moverSmallVal.next = dummyHeadBigVal.next;
        moverBigVal.next = null;

        head = dummyHeadSmallVal.next;
        return head;
    }
}
