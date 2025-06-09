package src._5_topo_sort._3_largestColorPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1857_OLD_WRONG_LargestColorValueInDirectedGraph {

    public static void main(String[] args) {
        _1857_OLD_WRONG_LargestColorValueInDirectedGraph obj = new _1857_OLD_WRONG_LargestColorValueInDirectedGraph();

        // --------------- test case 0
        /*String colors = "abaca";
        int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};

        int largestPathValue = obj.largestPathValue(colors, edges);
        System.out.println("largestPathValue " + largestPathValue);

        // -------------- test case 1
        String colors1 = "a";
        int[][] edges1 = {{0, 0}};

        int largestPathValue1 = obj.largestPathValue(colors1, edges1);
        System.out.println("largestPathValue " + largestPathValue1);*/

        // -------------- test case 2
        String colors2 = "hhqhuqhqff";
        int[][] edges2 = {{0, 1}, {0, 2}, {2, 3}, {3, 4}, {3, 5}, {5, 6}, {2, 7}, {6, 7}, {7, 8}, {3, 8}, {5, 8}, {8, 9}, {3, 9}, {6, 9}};

        int largestPathValue2 = obj.largestPathValue(colors2, edges2);
        System.out.println("largestPathValue " + largestPathValue2);

        // -------------- test case 3
        String colors3 = "nnllnzznn";
        int[][] edges3 = {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {3, 5}, {4, 6}, {3, 6}, {5, 6}, {6, 7}, {7, 8}};

        int largestPathValue3 = obj.largestPathValue(colors3, edges3);
        System.out.println("largestPathValue " + largestPathValue3);
    }

    /*
    Hint 1:
    Consider how the color frequency map is updated and shared during DFS traversal.

    Think about what happens to the color counts when you backtrack from a node,
    and whether this affects the correctness for different paths.
    ---------------------------------------------------------------------------------

    Hint 2:
    Think about how the colorFrequency map is shared and updated during DFS.

    Consider what happens when two different DFS paths overlap or branch,
    and whether the color counts for one path can affect another.
    */

    private int maxColorFreq = -1;

    public int largestPathValue(String colors, int[][] edges) {

        int v = colors.length();

        Map<Character, Integer> colorFrequency = getColorFreqMap(colors);
        List<List<Integer>> adjList = getAdjList(v, edges);
        boolean[] isVisited = new boolean[v];
        boolean[] isPathVisited = new boolean[v];

        for (int i = 0; i < v; i++) {

            if (!isVisited[i]) {
                if (dfs(i, adjList, isVisited, isPathVisited, colorFrequency, colors)) {
                    return -1; // cycle found
                }
            }
        }

        return maxColorFreq;
    }

    private boolean dfs(int currNode, List<List<Integer>> adjList,
                        boolean[] isVisited, boolean[] isPathVisited,
                        Map<Character, Integer> colorFrequency, String colors) {

        isVisited[currNode] = true;
        isPathVisited[currNode] = true;

        updateColorFreq(currNode, colors, colorFrequency, true);

        for (int adjNode : adjList.get(currNode)) {

            if (isVisited[adjNode] && isPathVisited[adjNode]) { // first check cycle
                return true;
            }

            if (!isVisited[adjNode]) {
                if (dfs(adjNode, adjList, isVisited, isPathVisited, colorFrequency, colors)) {
                    return true; // cycle found, no need to traverse further. return from here.
                }
            }
        }

        updateColorFreq(currNode, colors, colorFrequency, false);
        isPathVisited[currNode] = false; // will be used for backtracking

        return false; // cycle not found
    }

    private void updateColorFreq(int currNode, String colors, Map<Character, Integer> colorFrequency, boolean isGoingForward) {

        char currColor = colors.charAt(currNode);

        int currColorFreq;
        if (isGoingForward) {
            currColorFreq = colorFrequency.get(currColor) + 1;
        } else {
            currColorFreq = colorFrequency.get(currColor) - 1; // backtracking here, so need to reduce this color frequency now
        }
        colorFrequency.put(currColor, currColorFreq);
        maxColorFreq = Math.max(maxColorFreq, currColorFreq);
    }

    private Map<Character, Integer> getColorFreqMap(String colors) {

        Map<Character, Integer> colorFrequency = new HashMap<>();
        for (int i = 0; i < colors.length(); i++) {
            char ch = colors.charAt(i);
            colorFrequency.put(ch, 0);
        }
        return colorFrequency;
    }

    private List<List<Integer>> getAdjList(int v, int[][] edges) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {

            int src = edges[i][0];
            int dst = edges[i][1];

            adjList.get(src).add(dst);
        }

        return adjList;
    }
}
