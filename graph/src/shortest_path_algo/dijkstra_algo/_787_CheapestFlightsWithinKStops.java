package src.shortest_path_algo.dijkstra_algo;

import java.util.*;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * https://www.geeksforgeeks.org/problems/cheapest-flights-within-k-stops/1
 * <p>
 * https://www.youtube.com/watch?v=9XybHVqTHcQ&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/g-38-cheapest-flights-within-k-stops/
 */
public class _787_CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        // Define the number of nodes (n), flights, source node (src), destination node (dst), and maximum number of stops (k)
        int n = 4;
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };
        int src = 0;
        int dst = 3;
        int k = 1;

        // Call the findCheapestPrice method and print the result
        int result = findCheapestPrice(n, flights, src, dst, k);
        System.out.println("The cheapest price from " + src + " to " + dst + " with up to " + k + " stops is: " + result);

        // Change the maximum number of stops (k) and call the method again
        k = 0;
        result = findCheapestPrice(n, flights, src, dst, k);
        System.out.println("The cheapest price from " + src + " to " + dst + " with up to " + k + " stops is: " + result);
    }

    private static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<List<List<Integer>>> adjList = getAdjList(n, flights);

        int[] costArr = new int[n];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        costArr[src] = 0;

        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(src, 0, 0));

        while (!queue.isEmpty()) {

            int currNode = queue.peek().node;
            int currStops = queue.peek().stops;
            int currCost = queue.peek().cost;
            queue.remove();

            if (currStops > k) continue; // MADE A MISTAKE HERE, I was checking for currCost instead of currStops

            for (List<Integer> adjNodeWithCost : adjList.get(currNode)) {

                int adjNode = adjNodeWithCost.get(0);
                int adjCost = adjNodeWithCost.get(1);

                // first priority should be stops
                // second priority should be cost
                if (currStops <= k && (currCost + adjCost < costArr[adjNode])) {

                    costArr[adjNode] = currCost + adjCost;
                    // we can allow k + 1 stops because, k + 1 should contain the destination node
                    queue.add(new Tuple(adjNode, currStops + 1, currCost + adjCost));
                }
            }
        }

        return costArr[dst] == Integer.MAX_VALUE ? -1 : costArr[dst];
    }

    private static class Tuple {
        int node;
        int stops;
        int cost;

        Tuple(int node, int stops, int cost) {
            this.node = node;
            this.stops = stops;
            this.cost = cost;
        }
    }

    private static List<List<List<Integer>>> getAdjList(int n, int[][] flights) {

        List<List<List<Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < flights.length; i++) {

            int src = flights[i][0];
            int dst = flights[i][1];
            int cost = flights[i][2];

            adjList.get(src).add(Arrays.asList(dst, cost));
            //adjList.get(dst).add(Arrays.asList(src, cost)); // MADE A MISTAKE HERE, flights are not bidirectional
        }
        return adjList;
    }
}
