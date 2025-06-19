package src._8_shortest_path_algo._1_singleSource._1_unweighted_graphs_BFS;

import java.util.*;

/**
 * https://leetcode.com/problems/snakes-and-ladders/
 * https://www.youtube.com/watch?v=8WZA471fV7g
 * https://www.youtube.com/watch?v=N7OhwS_nzhg&t=618s&ab_channel=AdityaVerma
 */
public class _909_SnakesAndLadders {

    private int snakesAndLadders(int[][] board) {

        Map<Integer, Integer> snakeAndLadder = getSnakeAndLadderMap(board);

        int n = board.length;
        List<List<Integer>> adjList = getAdjList(n, snakeAndLadder);

        int srcNode = 1;
        int dstNode = n * n;

        // base case:
        // dstNode shouldn't have any snake or ladder
        // The last square is not the starting point of any snake or ladder.
        if (snakeAndLadder.containsKey(dstNode)) return -1;

        return bfs(srcNode, dstNode, adjList);
    }

    private int bfs(int srcNode, int dstNode, List<List<Integer>> adjList) {

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(srcNode, 0));

        boolean[] isVisited = new boolean[dstNode + 1];
        isVisited[srcNode] = true;

        //int maxDist = -1;

        while (!queue.isEmpty()) {

            Pair currPair = queue.poll();
            int currNode = currPair.node;
            int currDist = currPair.dist;

            //maxDist = Math.max(maxDist, currDist); // MISTAKE I MADE: if I can't reach dstNode due to a loop, then I was returning maxDist.

            // if we've reached the destination, then stop
            if (currNode == dstNode) {
                return currDist;
            }

            // explore the neighbors
            for (int adjNode : adjList.get(currNode)) {

                if (!isVisited[adjNode]) {

                    isVisited[adjNode] = true;
                    queue.add(new Pair(adjNode, currDist + 1));
                }
            }
        }

        return -1;
    }

    private static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    private List<List<Integer>> getAdjList(int n, Map<Integer, Integer> snakeAndLadder) {

        int srcNode = 1;
        int dstNode = n * n;

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= dstNode; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = srcNode; i <= dstNode; i++) {

            for (int j = 1; j <= 6; j++) {

                int currNode = i;
                int adjNode = currNode + j;

                if (adjNode <= dstNode) {

                    if (snakeAndLadder.containsKey(adjNode)) {
                        adjNode = snakeAndLadder.get(adjNode);
                    }

                    adjList.get(currNode).add(adjNode);
                }
            }
        }
        return adjList;
    }

    private Map<Integer, Integer> getSnakeAndLadderMap(int[][] board) {

        Map<Integer, Integer> snakeAndLadder = new HashMap<>();

        int n = board.length;
        int m = board[0].length;

        int count = 1;
        boolean isReverse = false;

        for (int i = n - 1; i >= 0; i--) {

            if (!isReverse) {

                for (int j = 0; j < m; j++) {
                    if (board[i][j] != -1) {
                        snakeAndLadder.put(count, board[i][j]);
                    }
                    count++;
                }
            } else {

                for (int j = m - 1; j >= 0; j--) {
                    if (board[i][j] != -1) {
                        snakeAndLadder.put(count, board[i][j]);
                    }
                    count++;
                }
            }
            isReverse = !isReverse;
        }
        return snakeAndLadder;
    }

    public static void main(String[] args) {
        testCase1(); // op : 4
        testCase2(); // op : 1
        testCase3(); // op: -1
        testCase4(); // op: -1
    }

    private static void testCase1() {
        int[][] board = {
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 35, -1, -1, 13, -1},
                {-1, -1, -1, -1, -1, -1},
                {-1, 15, -1, -1, -1, -1}};

        _909_SnakesAndLadders obj = new _909_SnakesAndLadders();
        System.out.println(obj.snakesAndLadders(board));
    }

    private static void testCase2() {
        int[][] board = {
                {-1, -1},
                {-1, 3}};

        _909_SnakesAndLadders obj = new _909_SnakesAndLadders();
        System.out.println(obj.snakesAndLadders(board));
    }

    private static void testCase3() {
        int[][] board = {
                {1, 1, -1},
                {1, 1, 1},
                {-1, 1, 1}};
        _909_SnakesAndLadders obj = new _909_SnakesAndLadders();
        System.out.println(obj.snakesAndLadders(board));
    }

    private static void testCase4() {
        int[][] board = {
                {-1, 1, 1, 1},
                {-1, 7, 1, 1},
                {1, 1, 1, 1},
                {-1, 1, 9, 1}};

        _909_SnakesAndLadders obj = new _909_SnakesAndLadders();
        System.out.println(obj.snakesAndLadders(board));
    }
}
