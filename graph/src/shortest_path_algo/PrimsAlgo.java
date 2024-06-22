package src.shortest_path_algo;

import java.util.List;
import java.util.PriorityQueue;

/**
 * https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
 * https://takeuforward.org/data-structure/prims-algorithm-minimum-spanning-tree-c-and-java-g-45/
 */
public class PrimsAlgo {

    static int spanningTree(int V, int E, List<List<Pair>> adjList) {

        int sumOfWeight = 0;

        PriorityQueue<Pair> pQueue = new PriorityQueue<>((x,y) -> x.weight - y.weight);

        boolean[] isVisited = new boolean[V];

        pQueue.add(new Pair(0, 0)); // we're starting from node 0

        while (!pQueue.isEmpty()) {
            Pair currNode = pQueue.poll(); // pull out the node with the smallest weights from current pQueue

            int currDestination = currNode.destination;
            int currWeight = currNode.weight;

            if (!isVisited[currDestination]) {

                isVisited[currDestination] = true;
                sumOfWeight += currWeight;

                for (Pair neighbours : adjList.get(currDestination)) { // explore it neighbours

                    if (!isVisited[neighbours.destination]) { // if not visited, only then add it into pQueue
                        pQueue.add(neighbours);
                    }
                }
            }
        }

        return sumOfWeight;
    }

    private static class Pair {
        int destination;
        int weight;

        Pair(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
}
