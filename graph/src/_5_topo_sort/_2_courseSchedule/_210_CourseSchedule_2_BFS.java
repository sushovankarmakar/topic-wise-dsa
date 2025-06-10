package src._5_topo_sort._2_courseSchedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-ii/description/
 * <p>
 * https://www.youtube.com/watch?v=WAOfKpxYHR8&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/
 */
public class _210_CourseSchedule_2_BFS {

    public static void main(String[] args) {

        _210_CourseSchedule_2_BFS obj = new _210_CourseSchedule_2_BFS();

        int[] result = obj.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);

        int[] result1 = obj.findOrder(2, new int[][]{{0, 1}, {1, 0}});
        StringBuilder sb1 = new StringBuilder();
        for (int i : result1) {
            sb1.append(i).append(" ");
        }
        System.out.println(sb1);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] inDegrees = new int[numCourses];
        List<List<Integer>> adjList = getAdjList(numCourses, prerequisites, inDegrees);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int visitedNodes = 0;
        int[] sortedTasks = new int[numCourses];
        int i = 0;

        while (!queue.isEmpty()) {

            int currNode = queue.poll();
            sortedTasks[i++] = currNode;
            ++visitedNodes;

            for (int adjNode : adjList.get(currNode)) {

                if (--inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        return visitedNodes == numCourses ? sortedTasks : new int[]{};
    }

    private static List<List<Integer>> getAdjList(int n, int[][] prerequisites, int[] inDegrees) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; ++i) {

            int dst = prerequisites[i][0];
            int src = prerequisites[i][1];

            adjList.get(src).add(dst);
            ++inDegrees[dst];
        }

        return adjList;
    }
}
