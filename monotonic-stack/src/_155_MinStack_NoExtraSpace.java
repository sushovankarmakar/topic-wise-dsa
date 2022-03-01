package src;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=ZvaRHYYI0-4&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=QMlDCR9xyd8&ab_channel=TECHDOSE
 * https://www.youtube.com/watch?v=V09NfaGf2ao&ab_channel=takeUforward
 * <p>
 * https://leetcode.com/problems/min-stack/
 * https://practice.geeksforgeeks.org/problems/get-minimum-element-from-stack/1/
 * https://practice.geeksforgeeks.org/problems/special-stack/1/
 *
 * https://leetcode.com/problems/min-stack/discuss/49010/Clean-6ms-Java-solution (Very Good Solution)
 */

/**
 * time complexity : O(1)
 * space complexity : O(1)
 */
public class _155_MinStack_NoExtraSpace {

    /**
     * while popping,
     * if top of the stack is smaller than the current min value, then it is an anomaly which indicates that
     * current top element is the min value in the stack.
     * so if we pop this top element, then we need to go back to previous min value also.
     */

    /**
     * while pushing,
     * if the current value which is going to be pushed, if that value is smaller than current min value then,
     * instead of storing that value in the stack, we assign that value to min value
     * and store some anomaly value in the stack which works as flag.
     *
     * this anomaly flag value should be always smaller than the current value.
     *
     * 2*X - min
     * 2*min - Y
     */

    int minEle;
    Stack<Integer> stack = new Stack();

    /*returns min element from stack*/
    int getMin() {
        return stack.isEmpty() ? -1 : minEle;
    }

    /*returns popped element from stack*/
    int pop() {
        if (stack.isEmpty()) {
            return -1;
        } else {
            int val = stack.pop();

            if (val < minEle) {
                int topVal = minEle;
                minEle = (2 * minEle - val);
                return topVal;
            }
            return val;
        }
    }

    /*push element x into the stack*/
    void push(int val) {
        if (stack.isEmpty()) {

            stack.push(val);
            minEle = val;

        } else {
            if (val < minEle) {

                stack.push(2 * val - minEle);
                minEle = val;

            } else {
                stack.push(val);
            }
        }
    }

    /**
     * Same question, in leetcode variant, we need to use Long instead of Integer. Logic will be same.
     */
}
