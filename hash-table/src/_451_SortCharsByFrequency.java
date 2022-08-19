package src;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/sort-characters-by-frequency/
 *
 * https://leetcode.com/problems/sort-characters-by-frequency/discuss/93420/Java-O(n)-Bucket-Sort-Solution-O(nlogm)-PriorityQueue-Solution-easy-to-understand
 * https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
 */
public class _451_SortCharsByFrequency {

    public static void main(String[] args) {
        System.out.println(frequencySort("tree"));  // eert
        System.out.println(frequencySort("cccaaa")); // aaaccc
        System.out.println(frequencySort("Aabb")); // bbAa
    }

    private static String frequencySort(String s) {

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        Comparator<Map.Entry<Character, Integer>> byValue =
                Comparator.comparing((Map.Entry<Character, Integer> e) -> e.getValue())
                        .reversed();

        freq = freq.entrySet()
                .stream()
                .sorted(byValue)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new
                ));

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> map : freq.entrySet()) {
            for (int i = 0; i < map.getValue(); i++) {
                sb.append(map.getKey());
            }
        }

        return sb.toString();
    }
}
