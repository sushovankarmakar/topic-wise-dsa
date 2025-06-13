package src._7_mult_source_bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://www.geeksforgeeks.org/problems/geeks-village-and-wells--170647/1
 *
 * https://www.youtube.com/watch?v=J3EVQPX9p7U&list=PL_z_8CaSLPWcn5bKG8UMI0St2D5EmQszx&index=34&pp=iAQB
 * https://www.youtube.com/watch?v=zENu0f2VG7Y&t=28s&ab_channel=AdityaVerma
 */
public class GeeksVillageAndWells {

    /*
     * multi-source BFS, treating 'W' as the source
     *
     */
    public int[][] chefAndWells(int n, int m, char[][] c) {

        int[][] distances = new int[n][m];

        Queue<Tuple> queue = new LinkedList<>();

        // pre-processing
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (c[i][j] == 'W') {

                    distances[i][j] = 0;
                    queue.add(new Tuple(i, j, 0));

                } else if (c[i][j] == 'N') {

                    distances[i][j] = 0;

                } else {
                    distances[i][j] = -1;
                }
            }
        }

        // multi-source BFS
        while (!queue.isEmpty()) {

            Tuple tuple = queue.poll();

            int row = tuple.row;
            int col = tuple.col;
            int dist = tuple.dist;

            for (int i = 0; i < fourDirections.length; i++) {

                int newRow = row + fourDirections[i][0];
                int newCol = col + fourDirections[i][1];
                int newDist = dist + 2;

                if (isValid(newRow, newCol, c, distances)) {

                    distances[newRow][newCol] = newDist; // it is also marking as visited also.
                    queue.add(new Tuple(newRow, newCol, newDist));
                }
            }
        }


        // post-processing
        // I inserted path values for those '.', I've to revert them also.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '.') {
                    distances[i][j] = 0;
                }
            }
        }

        return distances;
    }

    private boolean isValid(int row, int col, char[][] c, int[][] distances) {

        int n = distances.length;
        int m = distances[0].length;

        return row >= 0 && col >= 0 &&
                row < n && col < m &&
                (c[row][col] == 'H' || c[row][col] == '.') &&
                distances[row][col] == -1; // it means this cell is still unvisited
    }

    private final int[][] fourDirections = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static class Tuple {
        int row;
        int col;
        int dist;

        Tuple(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        GeeksVillageAndWells obj = new GeeksVillageAndWells();

        char[][] c = {
                {'H', 'H', 'H'},
                {'H', 'W', 'H'},
                {'H', 'H', 'H'}
        };
        /*
         * 4 2 4
         * 2 0 2
         * 4 2 4
         */

        char[][] c1 = {
                {'H', 'N', 'H', 'H', 'H'},
                {'N', 'N', 'H', 'H', 'W'},
                {'W', 'H', 'H', 'H', 'H'},
                {'H', 'H', 'H', 'H', 'H'},
                {'H', 'H', 'H', 'H', 'H'}
        };
        /*
         * -1 0 6 4 2
         * 0 0 4 2 0
         * 0 2 4 4 2
         * 2 4 6 6 4
         * 4 6 8 8 6
         */

        char[][] c2 = {
                {'.', '.', 'W', '.', 'W'},
                {'N', 'W', 'N', 'N', 'W'},
                {'H', '.', '.', 'H', 'N'},
                {'N', 'N', 'N', '.', 'W'}
        };
        /*
         * 0 0 0 0 0
         * 0 0 0 0 0
         * 4 0 0 4 0
         * 0 0 0 0 0
         */


        int[][] distances = obj.chefAndWells(3, 3, c);
        //int[][] distances = obj.chefAndWells(5, 5, c1);
        //int[][] distances = obj.chefAndWells(4, 5, c2);

        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                System.out.print(distances[i][j] + " ");
            }
            System.out.println();
        }
    }
}
