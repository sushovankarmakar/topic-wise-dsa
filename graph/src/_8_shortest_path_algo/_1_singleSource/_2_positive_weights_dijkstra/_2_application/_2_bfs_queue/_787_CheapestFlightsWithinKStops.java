package src._8_shortest_path_algo._1_singleSource._2_positive_weights_dijkstra._2_application._2_bfs_queue;

import java.util.*;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * https://www.geeksforgeeks.org/problems/cheapest-flights-within-k-stops/1
 * <p>
 * https://www.youtube.com/watch?v=9XybHVqTHcQ&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/g-38-cheapest-flights-within-k-stops/
 */
public class _787_CheapestFlightsWithinKStops {

    /*
     * The if (currStops > k) continue; already ensures that we don't process nodes with stops > k
     * By the time we reach the inner loop, we're guaranteed that currStops <= k
     */
    private int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        List<List<List<Integer>>> adjList = getAdjList(n, flights);

        int[] costArr = new int[n];
        Arrays.fill(costArr, Integer.MAX_VALUE);
        costArr[src] = 0;

        // using Queue, instead of Priority queue, helps me to prioritize the 'stops' over 'costs'
        Queue<Tuple> queue = new LinkedList<>();
        queue.add(new Tuple(src, 0, 0));

        while (!queue.isEmpty()) {

            Tuple currTuple = queue.poll();

            int currNode = currTuple.node;
            int currStops = currTuple.stops;
            int currCost = currTuple.cost;

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

    private List<List<List<Integer>>> getAdjList(int n, int[][] flights) {

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

    public static void main(String[] args) {
        // Define the number of nodes (n), flights, source node (src), destination node (dst), and maximum number of stops (k)
        testCase1();
        testCase2();
        testCase3();
    }

    private static void testCase1() {
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

        _787_CheapestFlightsWithinKStops obj = new _787_CheapestFlightsWithinKStops();

        // Call the findCheapestPrice method and print the result
        int result = obj.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("The cheapest price from " + src + " to " + dst + " with up to " + k + " stops is: " + result);
    }

    private static void testCase2() {
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
        int k = 0;

        _787_CheapestFlightsWithinKStops obj = new _787_CheapestFlightsWithinKStops();

        // Call the findCheapestPrice method and print the result
        int result = obj.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("The cheapest price from " + src + " to " + dst + " with up to " + k + " stops is: " + result);
    }

    private static void testCase3() { // striver's test case
        int n = 5;
        int[][] flights = {
                {0, 1, 5},
                {0, 3, 2},
                {3, 1, 2},
                {1, 4, 1},
                {1, 2, 5},
                {4, 2, 1}
        };
        int src = 0;
        int dst = 2;
        int k = 2;

        _787_CheapestFlightsWithinKStops obj = new _787_CheapestFlightsWithinKStops();

        // Call the findCheapestPrice method and print the result
        int result = obj.findCheapestPrice(n, flights, src, dst, k);
        System.out.println("The cheapest price from " + src + " to " + dst + " with up to " + k + " stops is: " + result);
    }
}
