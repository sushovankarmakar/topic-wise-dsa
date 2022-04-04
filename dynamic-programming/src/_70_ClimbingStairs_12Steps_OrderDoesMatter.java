package src;

/**
 * https://practice.geeksforgeeks.org/problems/reach-the-nth-point5433/1/
 * https://leetcode.com/problems/climbing-stairs/
 * https://practice.geeksforgeeks.org/problems/count-ways-to-reach-the-nth-stair-1587115620/1
 * <p>
 * https://www.youtube.com/watch?v=QlT4HG93Gaw&t=1089s&ab_channel=AndreyGrehov (Good explanation)
 * https://github.com/andreygrehov/dp/blob/master/lecture4/lecture4.go
 * <p>
 * https://www.enjoyalgorithms.com/blog/climbing-stairs-problem
 * https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
 * https://www.interviewbit.com/blog/climbing-stairs-problem/
 */

/*
Problem:
	Climbing Stairs
	You are climbing a staircase. It takes n steps to reach to the top.
	Each time you can either climb 1 or 2 steps.
	In how many distinct ways can you climb to the top?
Framework for Solving DP Problems:
	1. Define the objective function
		          f(i) is the number of distinct ways to reach the i-th stair.
	2. Identify base cases
		          f(0) = 1
		          f(1) = 1
	3. Write down a recurrence relation for the optimized objective function
		          f(n) = f(n-1) + f(n-2)
	4. What's the order of execution?
		          bottom-up
	5. Where to look for the answer?
		          f(n)
*/

// Time complexity: O(n)
// Space complexity: O(n)
public class _70_ClimbingStairs_12Steps_OrderDoesMatter {

    public static void main(String[] args) {
        System.out.println(nthPoint_withLessMemory(1));  // 1
        System.out.println(nthPoint_withLessMemory(4));  // 3
        System.out.println(nthPoint_withLessMemory(5000));  // 419609281

    }

    // Fibonacci Number Approach
    // https://www.youtube.com/watch?v=UsPGd7DggRY&ab_channel=AndreyGrehov (memory optimized)
    public static int nthPoint_withLessMemory(int n) {

        if (n <= 1) return 1;

        int mod = 1000000007;
        int stepsToReach2ndLastStair = 1;
        int stepsToReachLastStair = 1;
        int stepsToReachNthStair = 0;

        for (int i = 2; i <= n; i++) {

            stepsToReachNthStair = (stepsToReachLastStair + stepsToReach2ndLastStair) % mod;

            stepsToReach2ndLastStair = stepsToReachLastStair;
            stepsToReachLastStair = stepsToReachNthStair;
        }
        return stepsToReachNthStair;
    }


    public static int nthPoint(int n) {

        if (n <= 1) return 1;

        int mod = 1000000007;
        int[] steps = new int[n + 1];
        steps[0] = 1;
        steps[1] = 1;

        for (int i = 2; i <= n; i++) {

            steps[i] = (steps[i - 1] + steps[i - 2]) % mod;
        }
        return steps[n];
    }

}
