package heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/top-k-frequent-elements/description/
 */
public class _347_TopKFrequentElements_BucketSort {
    
    public int[] topKFrequent(int[] nums, int k) {

        if (k == nums.length) return nums; // base case
        
        // counting the frequency
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Bucket sort
        // frequency range can be from [1, N], 
        // so we create bucket for each frequency

        // arr: [1,1,1,2,2,3,4,4] k = 3
        // map: [(1:3), (2:2), (3:1), (4:2)]
        // buckets: [[],[3],[2,4],[1],[],[],[],[]]
        // flattened: [1,2,4,3]
        // topK: [1,2,4]

        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int freq = entry.getValue();
            buckets[freq].add(key);
        }

        List<Integer> flattened = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0; i--) {
            for (int num : buckets[i]) {
                flattened.add(num);
            }
        }

        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = flattened.get(i);
        }
        return topK;
    }
}
