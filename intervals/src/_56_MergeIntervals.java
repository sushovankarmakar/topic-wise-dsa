package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/merge-intervals/description/
 * https://leetcode.com/problems/filter-occupied-intervals/description/ (similar question)
 */
public class _56_MergeIntervals {
    
    public int[][] merge(int[][] intervals) {
        List<int[]> mergedList = new ArrayList<>();
        if (intervals == null || intervals.length == 0) {
            return mergedList.toArray(new int[0][]);
        }
        
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        
        int[] prevInterval = intervals[0];
        mergedList.add(prevInterval);
        
        for (int[] currInterval : intervals) {
            
            if (prevInterval[1] >= currInterval[0]) {
                prevInterval[1] = Math.max(prevInterval[1], currInterval[1]);
            } else {
                mergedList.add(currInterval);
                prevInterval = currInterval;  
            }
        }
        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
