package src._5_topo_sort._2_courseSchedule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule/description/
 * <p>
 * https://www.youtube.com/watch?v=WAOfKpxYHR8&ab_channel=takeUforward (Striver)
 * https://takeuforward.org/data-structure/course-schedule-i-and-ii-pre-requisite-tasks-topological-sort-g-24/
 */
public class _207_CourseSchedule {

    public static void main(String[] args) {

        _207_CourseSchedule obj = new _207_CourseSchedule();

        System.out.println(obj.canFinish(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}));  // true
        System.out.println(obj.canFinish(2, new int[][]{{0, 1}, {1, 0}}));                  // false
    }

    private boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] inDegrees = new int[numCourses];
        List<List<Integer>> adjList = getAdjList(numCourses, prerequisites, inDegrees);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int courseVisited = 0;

        while (!queue.isEmpty()) {

            int currNode = queue.remove();
            ++courseVisited;

            for (int adjNode : adjList.get(currNode)) {

                if (--inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }
        }

        return courseVisited == numCourses;
    }

    private List<List<Integer>> getAdjList(int numCourses, int[][] prerequisites, int[] inDegrees) {

        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {

            int src = prerequisites[i][0];
            int dst = prerequisites[i][1];

            adjList.get(src).add(dst);
            inDegrees[dst]++;
        }

        return adjList;
    }

}
