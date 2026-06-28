package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Leetcode Weekly Contest 508
 * https://leetcode.com/problems/filter-occupied-intervals/description/
 * https://leetcode.com/problems/merge-intervals/description/ (similar question)
 */
public class _3975_FilterOccupiedIntervals {

    public static void main(String[] args) {

        // int freeStart = 7; 
        // int freeEnd = 11;
        // int[][] occupiedIntervals = {{2,6},{4,8},{10,10},{10,12},{14,16}};

        // int freeStart = 3; 
        // int freeEnd = 8;
        // int[][] occupiedIntervals = {{1,5},{2,3}};

        int freeStart = 100; 
        int freeEnd = 100;
        int[][] occupiedIntervals = {{1,1},{2,2}};

        _3975_FilterOccupiedIntervals obj = new _3975_FilterOccupiedIntervals();

        List<List<Integer>> output = obj.filterOccupiedIntervals(occupiedIntervals, freeStart, freeEnd);
        
        for (List<Integer> list : output) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        if (occupiedIntervals == null || occupiedIntervals.length == 0) {
            return new ArrayList<>();
        }

        /**
         * the intuition is:  merge first, remove later.
         * 
         * 1. merge both (occupied intervals + free interval)
         * 2. now the free interval will completely lie between one of the merged intervals. remove from that interval.
         */
        List<List<Integer>> intervals = convertIntoArrayList(occupiedIntervals);
        intervals.add(Arrays.asList(freeStart, freeEnd));

        List<List<Integer>> mergedIntervals = mergeIntervals(intervals);
        excludeInterval(mergedIntervals, freeStart, freeEnd);

        return mergedIntervals;
    }

    public List<List<Integer>> convertIntoArrayList(int[][] occupiedIntervals) {

        List<List<Integer>> intervals = new ArrayList<>();
        for (int[] row : occupiedIntervals) {
            List<Integer> innerList = new ArrayList<>();
            for (int val : row) {
                innerList.add(val);
            }
            intervals.add(innerList);
        }
        return intervals;
    }

    public List<List<Integer>> mergeIntervals(List<List<Integer>> intervals) {
        
        if (intervals == null || intervals.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. Sort the list by the first element (start time)
        intervals.sort(Comparator.comparingInt(a -> a.get(0)));

        List<List<Integer>> mergedList = new ArrayList<>();

        // 2. Initialize with the first interval
        List<Integer> prevInterval = new ArrayList<>(intervals.get(0));
        mergedList.add(prevInterval);
        
        for (List<Integer> currInterval : intervals) {
            
            // MODIFIED CONDITION:
            // Use + 1 to account for intervals that touch (end + 1 == start)
            if (prevInterval.get(1) + 1 >= currInterval.get(0)) {
                prevInterval.set(1, Math.max(prevInterval.get(1), currInterval.get(1)));
            } else {
                // No overlap, add as a new entry
                mergedList.add(currInterval);
                prevInterval = currInterval;  
            }
        }
        return mergedList;
    }

    public void excludeInterval(List<List<Integer>> mergedIntervals, int freeStart, int freeEnd) {

        for (int i = 0; i < mergedIntervals.size(); i++) {

            if (mergedIntervals.get(i).get(0) <= freeStart && freeEnd <= mergedIntervals.get(i).get(1)) {

                int a = mergedIntervals.get(i).get(0);
                int b = freeStart - 1;

                int c = freeEnd + 1;
                int d = mergedIntervals.get(i).get(1);
                
                mergedIntervals.remove(i);
                if (a <= b) mergedIntervals.add(Arrays.asList(a, b));
                if (c <= d) mergedIntervals.add(Arrays.asList(c, d));
                break;
            }
        }

        mergedIntervals.sort(Comparator.comparingInt(a -> a.get(0)));
    }
}