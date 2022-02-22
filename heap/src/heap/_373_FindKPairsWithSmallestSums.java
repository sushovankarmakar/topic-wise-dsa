package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 *
 * (Understood from below two posts - Very useful)
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/1768494/Easy-java-solution-using-priorityQueue
 */
public class _373_FindKPairsWithSmallestSums {

    /**
     * time complexity : O(K * LogK)    since queue size <= k , and we do at most k loop
     * space complexity : O(K)
     * ttps://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
     */
    public List<List<Integer>> kSmallestPairs_optimalSol(int[] nums1, int[] nums2, int k) {

        PriorityQueue<Pair> pqueue = new PriorityQueue<>();

        /**
         * Some observations: For every numbers in nums1, its best partner(yields min sum) always strats from nums2[0]
         * since arrays are all sorted; And for a specific number in nums1,
         * its next candidate sould be [this specific number] + nums2[current_associated_index + 1],
         * unless out of boundary;)
         */
        for (int i = 0; i < nums1.length && i < k; i++) {
            int sum = nums1[i] + nums2[0];
            pqueue.add(new Pair(nums1[i], nums2[0], sum, 0));
        }

        List<List<Integer>> output = new ArrayList<>();

        while (k-- > 0 && !pqueue.isEmpty()) {
            Pair pair = pqueue.poll();
            output.add(Arrays.asList(pair.val1, pair.val2));

            int index = pair.idx;
            if (index + 1 < nums2.length) {

                int nextIndex = index + 1;
                int val1 = pair.val1;
                int val2 = nums2[nextIndex];
                int sum  = val1 + val2;

                pqueue.add(new Pair(val1, val2, sum, nextIndex));
            }
        }

        return output;
    }

    // comparing according to the sum of elements and returning the min sum from priority queue
    class Pair implements Comparable<Pair> {
        int val1;
        int val2;
        int sum;
        int idx;

        Pair(int val1, int val2, int sum, int idx) {
            this.val1 = val1;
            this.val2 = val2;
            this.sum = sum;
            this.idx = idx;
        }

        public int compareTo(Pair other) {
            return this.sum - other.sum;
        }
    }


    /**
     * time : O(m * n)
     * space : O(m)
     * https://www.youtube.com/watch?v=fYS_6C4-tfk&ab_channel=LCBear
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<List<Integer>> pqueue = new PriorityQueue<>(
                (a, b) -> (b.get(0) + b.get(1)) - (a.get(0) + a.get(1))
        );

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                pqueue.add(Arrays.asList(nums1[i], nums2[j]));

                if (pqueue.size() > k) {
                    pqueue.poll();
                }
            }
        }

        return new ArrayList<>(pqueue);
    }
}
