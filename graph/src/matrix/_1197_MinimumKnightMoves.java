package src.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class _1197_MinimumKnightMoves {

    public static void main(String[] args) {

        _1197_MinimumKnightMoves obj = new _1197_MinimumKnightMoves();

        /*int n = 7;
        int[] knightPos = {1, 1};
        int[] targetPos = {7, 5}; // output: 4*/

        int n = 3;
        int[] knightPos = {1, 3};
        int[] targetPos = {3, 3}; // output: 2

        int steps = obj.minStepToReachTarget(knightPos, targetPos, n);
        System.out.println("Steps: " + steps);
    }

    public int minStepToReachTarget(int[] knightPos, int[] targetPos, int n) {

        boolean[][] isVisited = new boolean[n + 1][n + 1]; // n + 1 because 1-base indexing.

        Queue<Tuple> queue = new LinkedList<>();

        int distance = 0;
        Tuple initialTuple = new Tuple(knightPos[0], knightPos[1], distance);
        queue.offer(initialTuple);

        while (!queue.isEmpty()) {

            Tuple currTuple = queue.poll();
            int currRow = currTuple.row;
            int currCol = currTuple.col;
            int currDist = currTuple.dist;
            isVisited[currRow][currCol] = true;

            if (isTargetReached(currTuple, targetPos)) {
                return currDist;
            }

            for (int i = 0; i < eightDirections.length; i++) {

                int newRow = currRow + eightDirections[i][0];
                int newCol = currCol + eightDirections[i][1];

                if (isValidCell(newRow, newCol, n, isVisited)) {

                    isVisited[newRow][newCol] = true;
                    queue.offer(new Tuple(newRow, newCol, currDist + 1));
                }
            }
        }

        return -1;
    }

    private final int[][] eightDirections = {
            {2, 1}, {2, -1}, {-1, -2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {1, 2}
    };

    private boolean isValidCell(int row, int col, int n, boolean[][] isVisited) {

        // for 1-based indexing, this is the condition
        return row >= 1 && col >= 1 &&
                row <= n && col <= n &&
                !isVisited[row][col];

        // for 0-based indexing, this is the condition
        /*return row >= 0 && col >= 0 &&
                row < n && col < n &&
                !isVisited[row][col];*/
    }

    private boolean isTargetReached(Tuple tuple, int[] targetPos) {
        return targetPos[0] == tuple.row && targetPos[1] == tuple.col;
    }

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
}
