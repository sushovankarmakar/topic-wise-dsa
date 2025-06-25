package src._8_shortest_path_algo._2_multiSource._1_floyd_warshall_algo._1_mainAlgo;

/**
 * https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
 * <p>
 * https://www.youtube.com/watch?v=YbY8cVwWAvw&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/floyd-warshall-algorithm-g-42/
 * <p>
 * https://www.youtube.com/watch?v=FQthQd6Xymc&ab_channel=AdityaVerma
 */
public class FloydWarshallAlgorithm {

    private static void floydWarshallAlgo(int[][] matrix) {

        int numOfNodes = matrix.length;

        // we can't use Integer.MAX_VALUE as it will cause overflow
        // representing the unreachable nodes as infinity, i.e., 99999
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {

                if (matrix[i][j] == -1) {
                    matrix[i][j] = 99999; // 99,999 is used to represent infinity
                }

                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }

        // here is two parts:
        // part 1: floyd marshall algo to find the shortest distance from every node to every other nodes
        // part 2: detect -ve weight cycle using the same above floyd marshall algo.

        // PART_1: floyd marshall algo: going every node via every node
        for (int k = 0; k < numOfNodes; k++) { // going via k, these are intermediate nodes

            for (int i = 0; i < numOfNodes; i++) { // i = source node

                for (int j = 0; j < numOfNodes; j++) { // j = destination node

                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        // PART_2: if we want to detect a cycle, run the above for loop again.
        for (int k = 0; k < numOfNodes; k++) { // going via k, these are intermediate nodes

            for (int i = 0; i < numOfNodes; i++) { // i = source node

                for (int j = 0; j < numOfNodes; j++) { // j = destination node

                    if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
                        // cycle detected
                        System.out.println("-ve WEIGHT CYCLE DETECTED");
                    }
                }
            }
        }

        // reverting back the infinity node to -1,
        // this is doing just because this question asked to do so. not a core part of the algo
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {
                if (matrix[i][j] == 99999) {
                    matrix[i][j] = -1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 43},
                {-1, 0, 6},
                {-1, -1, 0}
        };

        floydWarshallAlgo(matrix);

        for (int[] row : matrix) {
            for (int ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
