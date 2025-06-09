package src._5_topo_sort;

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
    private static String findOrder(String[] dict, int numOfWords, int numOfAlphabets) {

        // STEP 1: build adjacency list
        List<List<Integer>> adjList = getAdjList(dict, numOfWords, numOfAlphabets);

        // STEP 2: topological sort
        boolean[] isVisited = new boolean[numOfAlphabets];
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < numOfAlphabets; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                topoSortDFS(i, isVisited, stack, adjList);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private static List<List<Integer>> getAdjList(String[] dict, int numOfWords, int numOfAlphabets) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numOfAlphabets; i++) { // here to use numOfAlphabets
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < numOfWords - 1; i++) { // here to use numOfWords
            compareWords(dict[i], dict[i + 1], adjList);
        }

        return adjList;
    }

    private static void compareWords(String s1, String s2, List<List<Integer>> adjList) {

        int len1 = s1.length();
        int len2 = s2.length();

        int i = 0;
        int j = 0;

        while (i < len1 && j < len2) {

            char ch1 = s1.charAt(i++);
            char ch2 = s2.charAt(j++);

            if (ch1 != ch2) {

                int src = ch1 - 'a';
                int dst = ch2 - 'a';

                adjList.get(src).add(dst);
                break; // important to break here. I did mistake here
            }
        }
    }

    private static void topoSortDFS(int currNode, boolean[] isVisited, Stack<Character> stack, List<List<Integer>> adjList) {

        isVisited[currNode] = true;

        for (int adjNode : adjList.get(currNode)) {

            if (!isVisited[adjNode]) {
                isVisited[adjNode] = true;
                topoSortDFS(adjNode, isVisited, stack, adjList);
            }
        }

        stack.push((char) (currNode + 'a')); // POINT TO REMEMBER : converting int to char
    }
}
