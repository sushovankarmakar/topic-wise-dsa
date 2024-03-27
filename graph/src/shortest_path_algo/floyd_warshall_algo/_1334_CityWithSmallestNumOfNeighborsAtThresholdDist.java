package src.shortest_path_algo.floyd_warshall_algo;

/**
 * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
 * <p>
 * https://www.youtube.com/watch?v=PwMVNSJ5SLI&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/find-the-city-with-the-smallest-number-of-neighbours-at-a-threshold-distance-g-43/
 */
public class _1334_CityWithSmallestNumOfNeighborsAtThresholdDist {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {0, 1, 3},
                {1, 2, 1},
                {1, 3, 4},
                {2, 3, 1}
        };
        int distanceThreshold = 4;

        int city = findTheCity(n, edges, distanceThreshold);
        System.out.println("The city with the smallest number of neighbors at a threshold distance is: " + city); // 3
    }

    private static int findTheCity(int n, int[][] edges, int distanceThreshold) {

        int[][] mat = getAdjMatrix(n, edges);

        // this floyd warshall algo will give us the shortest distance between every pair of nodes
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    mat[i][j] = Math.min(mat[i][j], mat[i][k] + mat[k][j]);
                }
            }
        }

        int count = 0; // count of reachable cities within the threshold
        int countMax = Integer.MAX_VALUE;
        int city = -1; // city with the smallest number of neighbors at a threshold distance

        // For each city, calculating the number of reachable cities within the threshold
        // and updating the city with the smallest number of reachable cities
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (mat[i][j] <= distanceThreshold) {
                    count++;
                }
            }

            // update the city if we get a city by which we can reach smaller number of cities
            // If there are multiple such cities, the city with the greatest number will be stored automatically
            // as we're going from cities having smaller values to bigger values (i -> 0,1,2....n)
            if (count <= countMax) {
                countMax = count;
                city = i;
            }

            count = 0; // reset the count
        }

        // return the city with the smallest number of neighbors at a threshold distance
        return city;
    }

    private static int[][] getAdjMatrix(int n, int[][] edges) {

        int[][] adjMat = new int[n][n];
        // we can't use Integer.MAX_VALUE as it will cause overflow
        // representing the unreachable nodes as infinity, i.e. 99999
        // 99999 is used to represent infinity
        // 0 is used to represent the distance between the same node
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    adjMat[i][j] = 0; // if the source and destination is same node then distance is zero
                } else {
                    adjMat[i][j] = 99999; // 99999 defines infinity
                }
            }
        }

        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dst = edges[i][1];
            int wt = edges[i][2];

            // MISTAKE I MADE : didn't consider edges are bidirectional
            adjMat[src][dst] = wt;
            adjMat[dst][src] = wt;
        }

        return adjMat;
    }
}
