package src;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/remove-covered-intervals/
 */
class _1288_RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {

        // test case: [[1,4],[1,2],[1,3]]
        // if start times are same, then sort in reverse of end time.

        // gotcha :
        // If two intervals share the same start point,
        // one has to put the longer interval in front.
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });

        int[] prevInterval = intervals[0];

        int count = 0;

        for (int i = 1; i < intervals.length; i++) {

            int[] currInterval = intervals[i];

            if (prevInterval[0] <= currInterval[0]
                    && currInterval[1] <= prevInterval[1]) {

                count++;
            } else {
                prevInterval = currInterval;
            }
        }

        return intervals.length - count;
    }
}