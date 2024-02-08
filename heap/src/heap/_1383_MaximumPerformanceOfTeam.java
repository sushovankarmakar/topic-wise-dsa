package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/maximum-performance-of-a-team/
 * <p>
 * https://www.youtube.com/watch?v=Y7UTvogADH0 (Very good understanding - I understood from here.)
 * https://www.youtube.com/watch?v=V-TuDMMf-S4 (Very good code - I followed this.)
 * <p>
 * TODO : Before commit read the leetcode solution section and then commit it.
 */
public class _1383_MaximumPerformanceOfTeam {

    public static void main(String[] args) {
        System.out.println(maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 2));    //
    }

    /**
     * Approach: Greedy with Priority Queue
     * <p>
     * For each candidate,
     * we treat him/her as the one who has the minimum efficiency in a team.
     * Then, we select the rest of the team members based on this condition.
     */
    private static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {

        int mod = 1000000007;
        Engineer[] engineers = new Engineer[n];

        for (int i = 0; i < n; i++) {
            engineers[i] = new Engineer(speed[i], efficiency[i]);
        }

        Arrays.sort(engineers, new Comparator<Engineer>() {
            public int compare(Engineer e1, Engineer e2) {
                return e2.efficiency - e1.efficiency;
            }
        });

        PriorityQueue<Engineer> currentTeam = new PriorityQueue<>(k, new Comparator<Engineer>() {
            @Override
            public int compare(Engineer e1, Engineer e2) {
                return e1.speed - e2.speed;
            }
        });

        long teamSpeed = 0;
        long maxPerformance = Integer.MIN_VALUE;

        for (Engineer newHire : engineers) {

            if (currentTeam.size() == k) {

                Engineer slowGuy = currentTeam.poll();
                teamSpeed -= slowGuy.speed;
            }

            currentTeam.add(newHire);
            teamSpeed += newHire.speed;

            long performanceWithNewHire = teamSpeed * newHire.efficiency;
            maxPerformance = Math.max(maxPerformance, performanceWithNewHire);
        }

        return (int) (maxPerformance % mod);
    }

    static class Engineer {
        int speed;
        int efficiency;

        Engineer(int speed, int efficiency) {
            this.speed = speed;
            this.efficiency = efficiency;
        }
    }
}
