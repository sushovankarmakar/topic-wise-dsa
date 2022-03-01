package src;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=asf9P2Rcopo&t=339s&ab_channel=AdityaVerma
 *
 * https://leetcode.com/problems/min-stack/
 * https://practice.geeksforgeeks.org/problems/get-minimum-element-from-stack/1/
 * https://practice.geeksforgeeks.org/problems/special-stack/1/
 */

/**
 * time complexity : O(1)
 * space complexity : O(N)
 *
 * previous solution is space optimized solution.
 */
public class _155_MinStack_WithExtraSpace {
    /**
     * Idea is to use two stack.
     * mainStack is responsible for normal push() and pop() action.
     * minStack is responsible for getMin() action. minStack is monotonic stack.
     */
    private final Stack<Integer> mainStack; // this stack is responsible for normal push() and pop() action.
    private final Stack<Integer> minStack;  // this stack is responsible for getMin() action. this is monotonic stack.

    public _155_MinStack_WithExtraSpace() {
        mainStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {

        mainStack.push(val);

        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop() {

        int val = mainStack.pop();

        if (!minStack.isEmpty() && minStack.peek() == val) {
            minStack.pop();
        }
    }

    public int top() {
        return mainStack.isEmpty() ? -1 : mainStack.peek();
    }

    public int getMin() {
        return minStack.isEmpty() ? -1 : minStack.peek();
    }

}
