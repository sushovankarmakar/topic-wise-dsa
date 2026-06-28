package heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
    https://leetcode.com/problems/top-k-frequent-elements/description/
 */
public class _347_TopKFrequentElements {

    /*
    1. Build hash map: character and how often it appears
    2. Keep k top frequent elements in the heap
    3. Build an output array
     */

    public int[] topKFrequent(int[] nums, int k) {

        if (k == nums.length) return nums; // base case, O(1) time
        
        // 1. Build hash map: character and how often it appears
        // O(N) time
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // min heap - 'the less frequent element first'
        Queue<Pair> minHeap = new PriorityQueue<>((p1, p2) -> p1.freq - p2.freq);
        
        // 2. Keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            minHeap.add(new Pair(num, freq));
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 3. Build an output array
        // O(k log k) time
        int[] topK = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            topK[i] = minHeap.poll().num;
        }
        return topK;
    }

    private class Pair {
        int num;
        int freq;
        Pair(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }
    }
}
