package src;

import src.nodes.SingleLLNode;

/**
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 * https://practice.geeksforgeeks.org/problems/decimal-equivalent-of-binary-linked-list/1
 * <p>
 * BEST EXPLANATION :
 * https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/discuss/629087/Detailed-explanation-Java-%3A-faster-than-100.00
 * <p>
 * https://www.youtube.com/watch?v=rPbzUW7usJE&ab_channel=LeadCodingbyFRAZ
 */
public class _1290_ConvertBinaryNumLLToInteger {

    public static void main(String[] args) {
        SingleLLNode head = SingleLLNode.create(new int[]{1, 0, 1}); // 5
        System.out.println(getDecimalValue(head));

        SingleLLNode head1 = SingleLLNode.create(new int[]{1, 1, 1, 0}); // 14
        System.out.println(getDecimalValue(head1));
    }

    /**
     * So If we have a Linked List 1 -> 0 -> 1  then this is how it'll be :-
     * <p>
     * Loop	Character	Operation	  Result
     * 1	    ‘1’	        1	          1
     * 2	    ‘0’	        (1x2) + 0	  2
     * 3	    ‘1’	        (2*2) + 1	  5
     */
    /* Note:
     * Operation is always the previous multiplied by the counting system.
     * In this way, we're doing decimal so x10. If we were doing hex, it'll x16. Binary will be x 2.
     */
    // MOST OPTIMAL SOLUTION
    public static int getDecimalValue(SingleLLNode head) {
        int sum = 0;

        while (head != null) {
            sum *= 2;             // when we encounter the next digit, we multiply by 2.
            sum += head.val;

            head = head.next;
        }
        return sum;
    }

    // this below approach is using StringBuilder, we can get the derived solution without using StringBuilder also.
    public int getDecimalValue2(SingleLLNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        return Integer.parseInt(sb.toString(), 2);
    }

    // this below code was my initial solution. but we can improve this using one single pass.
    public int getDecimalValue1(SingleLLNode head) {
        if (head == null) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();

        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        sb = sb.reverse();

        int decimalValue = 0;
        int n = sb.length();

        for (int i = 0; i < n; i++) {

            int val = (int) Math.pow(2, i) * Integer.parseInt((sb.substring(i, i + 1)));
            decimalValue += val;
        }

        return decimalValue;
    }

}
