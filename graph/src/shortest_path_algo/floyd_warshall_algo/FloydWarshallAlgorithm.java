package src.shortest_path_algo.floyd_warshall_algo;

/**
 * https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
 * <p>
 * https://www.youtube.com/watch?v=YbY8cVwWAvw&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/floyd-warshall-algorithm-g-42/
 */
public class FloydWarshallAlgorithm {

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

    private static void floydWarshallAlgo(int[][] matrix) {

        int numOfNodes = matrix.length;

        // we can't use Integer.MAX_VALUE as it will cause overflow
        // representing the unreachable nodes as infinity, i.e. 99999
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {

                if (matrix[i][j] == -1) {
                    matrix[i][j] = 99999; // 99999 is used to represent infinity
                }

                if (i == j) {
                    matrix[i][j] = 0;
                }
            }
        }

        // floyd marshall algo : going every node via every node
        for (int k = 0; k < numOfNodes; k++) { // going via k

            for (int i = 0; i < numOfNodes; i++) { // i = source node

                for (int j = 0; j < numOfNodes; j++) { // j = destination node

                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        // reverting back the infinity node to -1
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {
                if (matrix[i][j] == 99999) {
                    matrix[i][j] = -1;
                }
            }
        }
    }
}
