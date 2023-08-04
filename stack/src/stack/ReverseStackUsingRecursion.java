package stack;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/reverse-a-stack/1
 * https://www.youtube.com/watch?v=8YXQ68oHjAs (Aditya Verma)
 *
 * https://www.youtube.com/watch?v=z0bS9ULg5to (Programming Tutorials)
 * https://webrewrite.com/reverse-a-stack-using-recursion/
 */

public class ReverseStackUsingRecursion {

    /**
     * We know in a stack the element which we pushed at last is the first element to be popped out.
     * It follows Last In First Out (LIFO) order.
     * -----
     * To reverse a stack,
     * First we have to pop all the values of a stack recursively until the stack becomes empty.
     * And then insert each values one by one at the bottom of the stack.
     * -----
     * It means we have to use two recursive functions to solve this problem.
     */
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println(stack);  // 1, 2, ,3, 4, 5
        System.out.println(reverseStack(stack)); // 5, 4, 3, 2, 1
    }

    /**
     *  USE TWO RECURSIVE FUNCTIONS TO SOLVE THIS PROBLEM.
     *
     * function-A
     * 1. store the top element
     * 2. call the same function recursively (func-A). (assume this will store reverse the stack)
     * 3. now we need to put the top element at the bottom (because we're reversing it). so for that, again call a recursive function. (func-B)
     * ----------
     * function-B
     * 1. store the top element
     * 2. call the same function recursively (func-B). (assume this will put the top element from function-A at the right place)
     * 3. now just push the top element which was stored earlier.
     */
    // function-A
    private static Stack<Integer> reverseStack(Stack<Integer> stack) {

        if (stack.size() == 1) {
            return stack;
        }

        int topElement = stack.pop();       // store the top element
        stack = reverseStack(stack);        // call the same function recursively. (assume this will store reverse the stack)
        insertElement(stack, topElement);   // now we need to put the top element at the bottom (because we're reversing it). so for that, again call a recursive function. (func-B)
        return stack;
    }

    // function-B
    private static void insertElement(Stack<Integer> stack, int valueToBeInserted) {
        if (stack.isEmpty()) {
            stack.push(valueToBeInserted);
            return;
        }

        int topElement = stack.pop();               // store the top element
        insertElement(stack, valueToBeInserted);    // call the same function recursively (func-B). (assume this will put the top element from function-A at the right place)
        stack.push(topElement);                     // now just push the top element which was stored earlier.
    }
}
