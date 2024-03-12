package src.topo_sort;

import java.util.*;

/**
 * https://leetcode.com/problems/course-schedule/description/
 *
 * https://www.youtube.com/watch?v=WAOfKpxYHR8&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/
 */
public class _207_CourseSchedule {

    public static void main(String[] args) {
        System.out.println(canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));  // true
        System.out.println(canFinish(2, new int[][]{{0, 1}, {1, 0}}));                  // false
    }

    private static boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjList = getAdjList(numCourses, prerequisites);
        int[] inDegrees = getInDegree(numCourses, adjList);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        Stack<Integer> stack = new Stack<>();

        while (!queue.isEmpty()) {

            int currNode = queue.remove();
            stack.push(currNode);

            for (int adjNode : adjList.get(currNode)) {

                inDegrees[adjNode]--;

                if (inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        return stack.size() == numCourses;
    }

    private static List<List<Integer>> getAdjList(int numCourses, int[][] prerequisites) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {

            int src = prerequisites[i][0];
            int dst = prerequisites[i][1];

            adjList.get(src).add(dst);
        }

        return adjList;
    }

    private static int[] getInDegree(int numOfCourses, List<List<Integer>> adjList) {

        int[] inDegrees = new int[numOfCourses];

        for (int i = 0; i < adjList.size(); i++) {
            for (int j = 0; j < adjList.get(i).size(); j++) {

                int dst = adjList.get(i).get(j);
                inDegrees[dst]++;
            }
        }

        return inDegrees;
    }
}
