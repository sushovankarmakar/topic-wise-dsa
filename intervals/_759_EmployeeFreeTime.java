package intervals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/employee-free-time/description/
 * similar problem : https://leetcode.com/problems/merge-intervals/description/
 */

/**
 * Actually we can use the solution of Merge Intervals as a building block for the solution for this problem. 
 * After merged all intervals, just go through again to find the gaps.
 */
public class _759_EmployeeFreeTime {

    public static void main(String[] args) {

        _759_EmployeeFreeTime obj = new _759_EmployeeFreeTime();

        List<List<Interval>> schedule = new ArrayList<>();
        List<Interval> employee1 = new ArrayList<>();
        employee1.add(new Interval(1, 2));
        employee1.add(new Interval(5, 6));

        List<Interval> employee2 = new ArrayList<>();
        employee2.add(new Interval(1, 3));

        List<Interval> employee3 = new ArrayList<>();
        employee3.add(new Interval(4, 10));

        schedule.add(employee1);
        schedule.add(employee2);
        schedule.add(employee3);

        List<Interval> freeTime = obj.employeeFreeTime(schedule);
        for (Interval interval : freeTime) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<int[]> intervals = new ArrayList<>();

        for (List<Interval> intervalList : schedule) {
            for (Interval interval : intervalList) {
                intervals.add(new int[] { interval.start, interval.end });
            }
        }

        intervals.sort(Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        int[] prevInterval = intervals.get(0);
        merged.add(prevInterval);

        for (int[] currInterval : intervals) {

            if (prevInterval[1] >= currInterval[0]) {
                prevInterval[1] = Math.max(prevInterval[1], currInterval[1]);
            } else {
                merged.add(currInterval);
                prevInterval = currInterval;
            }
        }

        return getFreeTime(merged);
    }

    private List<Interval> getFreeTime(List<int[]> mergedIntevals) {

        List<Interval> freeTimes = new ArrayList<>();

        for (int i = 0; i < mergedIntevals.size() - 1; i++) {

            freeTimes.add(new Interval(
                    mergedIntevals.get(i)[1],
                    mergedIntevals.get(i + 1)[0]));
        }

        return freeTimes;
    }

    static class Interval {
        public int start;
        public int end;

        public Interval() {
        }

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    };

}
