package src.shortest_path_algo.dijkstra_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/network-delay-time/description/ (Dijkstra's Algorithm)
 * https://www.youtube.com/watch?v=FPbwSphLrkI&ab_channel=codeExplainer
 */
public class _743_NetworkDelayTime {

    public static void main(String[] args) {

        // Define the number of nodes (n), times, and the source node (k)
        int n = 4;
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int k = 2;

        int result = networkDelayTime(times, n, k);
        System.out.println("The network delay time is: " + result); // 2
    }

    // Dijkstra's Algorithm is used here
    private static int networkDelayTime(int[][] times, int n, int k) {

        List<List<List<Integer>>> adjList = getAdjList(n, times);

        PriorityQueue<Pair> pQueue = new PriorityQueue<>((a, b) -> {
            return a.time - b.time; // return the smaller.
        });
        pQueue.add(new Pair(k, 0));

        int[] duration = new int[n + 1];
        Arrays.fill(duration, Integer.MAX_VALUE);
        duration[k] = 0;

        while (!pQueue.isEmpty()) {

            int currNode = pQueue.peek().node;
            int currTime = pQueue.peek().time;

            pQueue.remove();

            for (List<Integer> adjNodeWithTime : adjList.get(currNode)) {

                int adjNode = adjNodeWithTime.get(0);
                int adjTime = adjNodeWithTime.get(1);

                if (currTime + adjTime < duration[adjNode]) {
                    duration[adjNode] = currTime + adjTime;
                    pQueue.add(new Pair(adjNode, duration[adjNode]));
                }
            }
        }

        int maxVal = -1;
        for (int i = 1; i < n + 1; i++) {

            // ignore the source node
            if (i == k) continue;

            // there is one node, which is unreachable from source node
            // Integer.MAX_VALUE indicates unreachable node.
            if (duration[i] == Integer.MAX_VALUE) return -1;

            maxVal = Math.max(maxVal, duration[i]);
        }

        return maxVal;
    }

    private static class Pair {
        int node;
        int time;

        Pair(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    private static List<List<List<Integer>>> getAdjList(int n, int[][] times) {

        List<List<List<Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < times.length; i++) {
            int src = times[i][0];
            int dst = times[i][1];
            int time = times[i][2];

            adjList.get(src).add(Arrays.asList(dst, time));
        }

        return adjList;
    }
}
