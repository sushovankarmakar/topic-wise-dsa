package src.topo_sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/problems/alien-dictionary/1
 *
 * https://www.youtube.com/watch?v=U3N_je7tWAs&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/alien-dictionary-topological-sort-g-26/
 */
public class _269_AlienDictionary {

    public static void main(String[] args) {
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(findOrder(dict, 5, 4));

        String[] dict1 = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(findOrder(dict1, 5, 6));

        String[] dict2 = {"caa", "aaa", "aab"};
        System.out.println(findOrder(dict2, 3, 3));
    }

    /**
     * Steps:
     * 1. Build adjacency list
     * 2. Topological sort
     */
    private static String findOrder(String[] dict, int n, int k) {

        // STEP 1: build adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            buildAdjList(dict[i], dict[i + 1], adjList);
        }

        // STEP 2: topological sort
        boolean[] isVisited = new boolean[k];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < k; i++) {
            if (!isVisited[i]) {
                topoSortDFS(i, isVisited, stack, adjList);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private static void buildAdjList(String s1, String s2, List<List<Integer>> adjList) {

        int len1 = s1.length();
        int len2 = s2.length();

        int i = 0;
        int j = 0;

        while (i < len1 && j < len2) {

            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(j);

            if (ch1 != ch2) {

                int src = ch1 - 'a';
                int dst = ch2 - 'a';

                adjList.get(src).add(dst);
                break;
            }

            i++;
            j++;
        }
    }

    private static void topoSortDFS(int currNode, boolean[] isVisited, Stack<Character> stack, List<List<Integer>> adjList) {

        isVisited[currNode] = true;

        for (int adjNode : adjList.get(currNode)) {

            if (!isVisited[adjNode]) {
                topoSortDFS(adjNode, isVisited, stack, adjList);
            }
        }

        stack.push((char) (currNode + 'a')); // POINT TO REMEMBER : converting int to char
    }
}
