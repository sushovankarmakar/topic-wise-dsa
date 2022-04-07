package src;

/**
 * https://github.com/andreygrehov/dp/blob/master/lecture8/lecture8.go
 * https://www.youtube.com/watch?v=3hHmUszRXjw&t=797s&ab_channel=AndreyGrehov (I followed this)
 */
/*
Problem:
	Paid Staircase II
	You are climbing a paid staircase. It takes n steps to reach to the top and you have to
	pay p[i] to step on the i-th stair. Each time you can climb 1 or 2 steps.
	Return the cheapest path to the top of the staircase.
Template to reconstruct the path
================================
	path = []
	for curr = best_last_state; curr exists; curr = from[curr] {
		path.push(curr)
	}
	return path.reverse()
*/

// Time complexity: O(n)
// Space complexity: O(n)
public class Paid_Staircase_Print_Paths {

    public static void main(String[] args) {
        int n = 8;
        Pair[] pairs = paidStaircase(n, new int[]{0, 3, 2, 4, 6, 1, 1, 5, 3});

        /**
         * Reached 8 from 6. Total cost = 11
         * Reached 6 from 5. Total cost = 8
         * Reached 5 from 3. Total cost = 7
         * Reached 3 from 2. Total cost = 6
         * Reached 2 from 0. Total cost = 2
         */
        for (int i = n; i > 0; ) {
            System.out.println("Reached " + i + " from " + pairs[i].from + ". Total cost = " + pairs[i].minCost);
            i = pairs[i].from;
        }
    }

    private static Pair[] paidStaircase(int n, int[] costs) {

        Pair[] pairs = new Pair[n + 1];
        pairs[0] = new Pair(0, 0);
        pairs[1] = new Pair(costs[1], 0);

        for (int i = 2; i <= n; i++) {

            int minCost;
            int from;
            if (pairs[i - 1].minCost > pairs[i - 2].minCost) {
                minCost = pairs[i - 2].minCost + costs[i];
                from = i - 2;
            } else {
                minCost = pairs[i - 1].minCost + costs[i];
                from = i - 1;
            }

            pairs[i] = new Pair(minCost, from);
        }
        return pairs;
    }

    static class Pair {
        int minCost;
        int from;

        Pair(int minCost, int from) {
            this.minCost = minCost;
            this.from = from;
        }
    }
}
