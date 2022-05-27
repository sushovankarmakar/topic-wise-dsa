package recursion;

/**
 * https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/
 * <p>
 * <p>
 * companies :
 * microsoft, grab, amazon, apple, hrt
 */
public class _1342_NumOfStepsToReduceNumberToZero {

    public static void main(String[] args) {
        System.out.println(numberOfSteps(14));  // 6
        System.out.println(numberOfSteps(8));   // 4
    }

    // using recursion
    /**
     * choice diagram : https://drive.google.com/file/d/1P37qoTr0bcAVzeRBZpTQCTnaKIf7jQUQ/view?usp=sharing
     *
     * so we can see that, to go from an even number to another even number, we need 2 steps.
     */
    private static int numberOfSteps_usingRecursion(int num) {

        if (num == 0) {
            return 0;
        }

        // In one step, if the current number is even, you have to divide it by 2,
        // otherwise, you have to subtract 1 from it.
        if (num % 2 == 0) {
            return 1 + numberOfSteps_usingRecursion(num / 2);
        } else {
            return 1 + numberOfSteps_usingRecursion(num - 1);
        }
    }


    /**
     * Let me explain briefly,
     * Usually we have to divide the number by 2 if it's even and subtract 1 if it's odd.
     * Look if we see the binary form of an even number,
     * we'll get 0 in the last and for odd numbers there will be 1 in the end. 2 -> 10 and 3 -> 11
     * <p>
     * Now "&" operator returns 1 if both operands are 1 otherwise 0.
     * So, if we compute 8 & 1 ->> (1000 & 1) ->> (1000 & 0001) ->> 0000
     * and 3 & 1 ->> 11 & 1 ->> 01
     * this is what the statement does: num & 1 ->> so if returns 0(for even) we'll have to divide by 2,
     * means number of operations are 1 and if returns 1(for odd) we'll have to first subtract 1 then divide,
     * so no. of operations became 2. Hence, we do the above operation.
     * <p>
     * After this num >>= 1 divides the number by 2, as irrespective of it is even or odd we'll get the same answer,
     * so simply divide and as we've already calculated the answer already.
     * <p>
     * And for the left most 1 for which we'll get 0 by just subtracting 1
     * but our logic considers it 2 so at last do res - 1.
     */
    private static int numberOfSteps(int num) {
        if (num == 0) return 0;
        int result = 0;

        while (num != 0) {
            // In order to count steps when right shifting one digit
            // For odd: we will subtract and divide by 2, which takes 2 steps
            // For even: we just divide by 2, which takes 1 step
            result += ((num & 1) == 0 ? 1 : 2);
            num >>= 1;
        }

        // We subtract 1 from result because when we the get to the most significant 1,
        // we only need to subtract it and we no longer need to divide by 2, so
        // only 1 step needed instead of the 2 that we counted when using the above while loop
        return result - 1;
    }
}
