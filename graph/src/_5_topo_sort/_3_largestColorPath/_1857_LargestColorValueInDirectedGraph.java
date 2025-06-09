package src._5_topo_sort._3_largestColorPath;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _1857_LargestColorValueInDirectedGraph {

    public static void main(String[] args) {

        _1857_LargestColorValueInDirectedGraph obj = new _1857_LargestColorValueInDirectedGraph();
        // --------------- test case 0
        String colors = "abaca";
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};

        int largestPathValue = obj.largestPathValue(colors, edges);
        System.out.println("largestPathValue " + largestPathValue); // 3

        // -------------- test case 1
        String colors1 = "a";
        int[][] edges1 = {{0, 0}};

        int largestPathValue1 = obj.largestPathValue(colors1, edges1);
        System.out.println("largestPathValue " + largestPathValue1); // -1

        // -------------- test case 2
        String colors2 = "hhqhuqhqff";
        int[][] edges2 = {{0, 1}, {0, 2}, {2, 3}, {3, 4}, {3, 5}, {5, 6}, {2, 7}, {6, 7}, {7, 8}, {3, 8}, {5, 8}, {8, 9}, {3, 9}, {6, 9}};

        int largestPathValue2 = obj.largestPathValue(colors2, edges2);
        System.out.println("largestPathValue " + largestPathValue2); // 3

        // -------------- test case 3
        String colors3 = "nnllnzznn";
        int[][] edges3 = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {3, 5}, {4, 6}, {3, 6}, {5, 6}, {6, 7}, {7, 8}};

        int largestPathValue3 = obj.largestPathValue(colors3, edges3);
        System.out.println("largestPathValue " + largestPathValue3); // 5

    }

    /*
    I followed this
     https://leetcode.com/problems/largest-color-value-in-a-directed-graph/solutions/6782119/java-easy-solution/
     */
    public int largestPathValue(String colors, int[][] edges) {

        int v = colors.length();
        int[] inDegreeArr = new int[v];
        List<List<Integer>> adjList = getAdjList(v, edges, inDegreeArr);

        int[][] colorFreq = new int[v][26];

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; ++i) {
            if (inDegreeArr[i] == 0) {
                queue.offer(i);
            }
        }

        int totalVisitedNodes = 0; // Processing vertices count
        int maxColorFreq = Integer.MIN_VALUE; // Answer to store the maximum color count in any path

        while (!queue.isEmpty()) {

            ++totalVisitedNodes; // this will later be used to detect cycle.

            int currNode = queue.poll();
            int currColor = colors.charAt(currNode) - 'a';

            maxColorFreq = Math.max(maxColorFreq, ++colorFreq[currNode][currColor]);

            for (int adjNode : adjList.get(currNode)) {

                // this is the most important part
                // passing colors to the neighbours, updating with max
                for (int color = 0; color < 26; ++color) {

                    int adjNodeColorFreq = colorFreq[adjNode][color];
                    int currNodeColorFreq = colorFreq[currNode][color];

                    colorFreq[adjNode][color] = Math.max(
                            adjNodeColorFreq,
                            currNodeColorFreq
                    );
                }

                if (--inDegreeArr[adjNode] == 0) {
                    queue.offer(adjNode);
                }
            }
        }

        // If all vertices are processed, return answer; otherwise, return -1
        return totalVisitedNodes == v ? maxColorFreq : -1;
    }

    private List<List<Integer>> getAdjList(int v, int[][] edges, int[] inDegreeArr) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; ++i) {

            int src = edges[i][0];
            int dst = edges[i][1];

            adjList.get(src).add(dst);

            ++inDegreeArr[dst];
        }
        return adjList;
    }
}
