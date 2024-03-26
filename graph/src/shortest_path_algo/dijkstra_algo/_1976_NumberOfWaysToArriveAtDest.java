package src.shortest_path_algo.dijkstra_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
 * https://www.geeksforgeeks.org/problems/number-of-ways-to-arrive-at-destination/1
 * <p>
 * https://www.youtube.com/watch?v=_-0mx0SmYxA&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/g-40-number-of-ways-to-arrive-at-destination/
 */
public class _1976_NumberOfWaysToArriveAtDest {

    public static void main(String[] args) {

        // Define the number of nodes (n) and roads
        int n = 4;
        int[][] roads = {
                {0, 1, 1},
                {1, 2, 1},
                {0, 2, 1},
                {0, 3, 1},
                {1, 3, 1},
                {2, 3, 1}
        };

        // Call the countPaths method and print the result
        int result = countPaths(n, roads);
        System.out.println("The number of ways to arrive at destination is: " + result); // 1
    }

    private static int countPaths(int n, int[][] roads) {

        int mod = 1_000_000_007;

        List<List<List<Long>>> adjList = getAdjList(n, roads);

        long[] distArr = new long[n];
        Arrays.fill(distArr, Long.MAX_VALUE);
        distArr[0] = 0;

        long[] numOfWays = new long[n];
        numOfWays[0] = 1;

        PriorityQueue<Pair> pQueue = new PriorityQueue<>((a, b) -> {
            if (a.dist != b.dist) {
                return (int) (a.dist - b.dist); // return the node with smaller distance
            }

            return (int) (a.node - b.node); // if distance is same, return the node with smaller node value.
        });
        pQueue.add(new Pair(0, 0));

        while (!pQueue.isEmpty()) {

            long currNode = pQueue.peek().node;
            long currDist = pQueue.peek().dist;

            pQueue.remove();

            for (List<Long> adjNodeWithDist : adjList.get((int) currNode)) {

                long adjNode = adjNodeWithDist.get(0);
                long adjDist = adjNodeWithDist.get(1);

                if (currDist + adjDist < distArr[(int) adjNode]) {
                    distArr[(int) adjNode] = currDist + adjDist;
                    pQueue.add(new Pair(adjNode, currDist + adjDist));

                    numOfWays[(int) adjNode] = numOfWays[(int) currNode];

                } else if (currDist + adjDist == distArr[(int) adjNode]) {

                    numOfWays[(int) adjNode] = (numOfWays[(int) adjNode] + numOfWays[(int) currNode]) % mod;
                }
            }
        }

        return (int) numOfWays[n - 1] % mod;
    }

    private static class Pair {
        long node;
        long dist;

        Pair(long node, long dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    private static List<List<List<Long>>> getAdjList(int n, int[][] roads) {

        List<List<List<Long>>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < roads.length; i++) {
            long src = roads[i][0];
            long dst = roads[i][1];
            long dist = roads[i][2];

            adjList.get((int) src).add(Arrays.asList(dst, dist));
            adjList.get((int) dst).add(Arrays.asList(src, dist));
        }

        return adjList;
    }
}
