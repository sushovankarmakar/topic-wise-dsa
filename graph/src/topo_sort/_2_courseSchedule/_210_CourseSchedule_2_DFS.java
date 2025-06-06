package src.topo_sort._2_courseSchedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/problems/course-schedule/1
 */
public class _210_CourseSchedule_2_DFS {

    public static void main(String[] args) {

        /**
         * 25 14
         * 10 18    // 18th task has to be done before 10th task
         * 0 18     // 18th task has to be done before 0th task
         * 10 6     // 6th task has to be done before 10th task
         * 16 0
         * 8 7
         * 19 15
         * 24 16
         * 20 14
         * 1 17
         * 14 13
         * 21 21
         * 19 22
         * 23 20
         * 10 5
         */

        int n = 25;
        int m = 14;
        ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
        prerequisites.add(new ArrayList<>(List.of(10, 18)));
        prerequisites.add(new ArrayList<>(List.of(0, 18)));
        prerequisites.add(new ArrayList<>(List.of(10, 6)));
        prerequisites.add(new ArrayList<>(List.of(16, 0)));
        prerequisites.add(new ArrayList<>(List.of(8, 7)));
        prerequisites.add(new ArrayList<>(List.of(19, 15)));
        prerequisites.add(new ArrayList<>(List.of(24, 16)));
        prerequisites.add(new ArrayList<>(List.of(20, 14)));
        prerequisites.add(new ArrayList<>(List.of(1, 17)));
        prerequisites.add(new ArrayList<>(List.of(14, 13)));
        prerequisites.add(new ArrayList<>(List.of(21, 21)));
        prerequisites.add(new ArrayList<>(List.of(19, 22)));
        prerequisites.add(new ArrayList<>(List.of(23, 20)));
        prerequisites.add(new ArrayList<>(List.of(10, 5)));

        int[] result = findOrder(n, m, prerequisites);
        for (int i : result) {
            System.out.print(i + " ");
        }

        /**
         * 35 16
         * 13 10
         * 16 19
         * 5 33
         * 5 28
         * 33 22
         * 2 28
         * 3 8
         * 26 27
         * 25 28
         * 7 12
         * 31 12
         * 25 1
         * 10 1
         * 29 23
         * 8 3
         * 12 11
         */

        /*int n = 35;
        int m = 16;
        ArrayList<ArrayList<Integer>> prerequisites = new ArrayList<>();
        prerequisites.add(new ArrayList<>(List.of(13, 10)));
        prerequisites.add(new ArrayList<>(List.of(16, 19)));
        prerequisites.add(new ArrayList<>(List.of(5, 33)));
        prerequisites.add(new ArrayList<>(List.of(5, 28)));
        prerequisites.add(new ArrayList<>(List.of(33, 22)));
        prerequisites.add(new ArrayList<>(List.of(2, 28)));
        prerequisites.add(new ArrayList<>(List.of(3, 8)));
        prerequisites.add(new ArrayList<>(List.of(26, 27)));
        prerequisites.add(new ArrayList<>(List.of(25, 28)));
        prerequisites.add(new ArrayList<>(List.of(7, 12)));
        prerequisites.add(new ArrayList<>(List.of(31, 12)));
        prerequisites.add(new ArrayList<>(List.of(25, 1)));
        prerequisites.add(new ArrayList<>(List.of(10, 1)));
        prerequisites.add(new ArrayList<>(List.of(29, 23)));
        prerequisites.add(new ArrayList<>(List.of(8, 3)));
        prerequisites.add(new ArrayList<>(List.of(12, 11)));

        int[] result = findOrder(n, m, prerequisites);
        for (int i : result) {
            System.out.print(i + " ");
        }*/
    }

    static int[] findOrder(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) {

        List<List<Integer>> adjList = getAdjList(n, m, prerequisites);

        Stack<Integer> stack = new Stack<>();
        boolean[] isVisited = new boolean[n];
        boolean[] isPathVisited = new boolean[n];

        for (int i = 0; i < n; i++) {

            if (!isVisited[i]) {
                isVisited[i] = true;
                if (dfs(i, adjList, isVisited, isPathVisited, stack)) { // if true, that means cycle is present
                    return new int[]{};
                }
            }
        }

        int[] sortedTasks = new int[stack.size()];
        int idx = 0;
        while (!stack.isEmpty()) {
            sortedTasks[idx++] = stack.pop();
        }

        return sortedTasks;
    }

    private static boolean dfs(int currNode, List<List<Integer>> adjList, boolean[] isVisited, boolean[] isPathVisited, Stack<Integer> stack) {

        isVisited[currNode] = true;
        isPathVisited[currNode] = true;

        for (int neighbour : adjList.get(currNode)) {

            if (isVisited[neighbour] && isPathVisited[neighbour]) {
                return true; // cycle found
            }

            if (!isVisited[neighbour]) {
                isVisited[neighbour] = true;
                if (dfs(neighbour, adjList, isVisited, isPathVisited, stack)) {
                    return true; // cycle found
                }
            }
        }

        stack.push(currNode);
        isPathVisited[currNode] = false;
        return false; // cycle NOT found
    }

    private static List<List<Integer>> getAdjList(int n, int m, ArrayList<ArrayList<Integer>> prerequisites) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (ArrayList<Integer> prerequisite : prerequisites) {

            int courseB = prerequisite.get(0);
            int courseA = prerequisite.get(1);

            adjList.get(courseA).add(courseB);
        }

        return adjList;
    }
}
