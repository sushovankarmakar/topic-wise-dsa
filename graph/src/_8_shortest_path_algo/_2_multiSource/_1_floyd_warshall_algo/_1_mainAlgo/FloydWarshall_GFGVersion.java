package src._8_shortest_path_algo._2_multiSource._1_floyd_warshall_algo._1_mainAlgo;

/*
 * https://www.geeksforgeeks.org/dsa/floyd-warshall-algorithm-dp-16/
 */
public class FloydWarshall_GFGVersion {

    public void floydWarshall(int[][] dist) {

        int numOfNodes = dist.length;

        for (int k = 0; k < numOfNodes; k++) {
            for (int i = 0; i < numOfNodes; i++) {
                for (int j = 0; j < numOfNodes; j++) {

                    if (dist[i][k] != 1e8 && dist[k][j] != 1e8) {

                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int INF = 100000000;

        int[][] dist = {{0, 4, INF, 5, INF},
                {INF, 0, 1, INF, 6},
                {2, INF, 0, 3, INF},
                {INF, INF, 1, 0, 2},
                {1, INF, INF, 4, 0}};

        FloydWarshall_GFGVersion obj = new FloydWarshall_GFGVersion();

        obj.floydWarshall(dist);
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
