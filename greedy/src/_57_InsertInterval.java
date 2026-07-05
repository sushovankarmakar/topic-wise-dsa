import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/insert-interval/description/
 * 
 * binary search
 */
public class _57_InsertInterval {

    public static void main(String[] args) {

        _57_InsertInterval obj = new _57_InsertInterval();

        int[][] intervals = { { 1, 3 }, { 6, 9 } };
        int[] newInterval = { 2, 5 };

        int[][] merged = obj.insert(intervals, newInterval);

        for (int[] interval : merged) {
            System.out.println(interval[0] + " " + interval[1]);
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        if (intervals.length == 0) {
            return new int[][] { newInterval };
        }

        int insertPos = getInsertPos(intervals, newInterval);

        List<int[]> newIntervals = new ArrayList<>();
        for (int i = 0; i < insertPos; i++) {
            newIntervals.add(intervals[i]);
        }
        newIntervals.add(newInterval);

        for (int i = insertPos; i < n; i++) {
            newIntervals.add(intervals[i]);
        }

        List<int[]> merged = new ArrayList<>();
        int[] prevInterval = newIntervals.get(0);
        merged.add(prevInterval);

        for (int[] currInterval : newIntervals) {

            if (prevInterval[1] >= currInterval[0]) {

                prevInterval[1] = Math.max(prevInterval[1], currInterval[1]);
            } else {
                merged.add(currInterval);
                prevInterval = currInterval;
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    private int getInsertPos(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        int left = 0;
        int right = n - 1;

        int insertPos = 0;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (intervals[mid][0] == newInterval[0]) {
                return mid;
            } else if (newInterval[0] < intervals[mid][0]) {
                right = mid - 1;
                insertPos = mid;
            } else {
                left = mid + 1;
                insertPos = mid + 1;
            }
        }
        return insertPos;
    }
}
