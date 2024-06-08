package src.string;

import java.util.HashMap;
import java.util.Map;

/**
 * This problem was asked by Bloomberg.
 * <p>
 * Determine whether there exists a one-to-one character mapping from one string s1 to another s2.
 * <p>
 * For example, given s1 = abc and s2 = bcd,
 * return true since we can map a to b, b to c, and c to d.
 * <p>
 * Given s1 = foo and s2 = bar, return false since the o cannot map to two characters.
 */

public class _20240602_1414_OneToOneCharMapping {

    // explanation : https://chatgpt.com/share/250e10ed-ebef-491b-9e91-57c8c62afcae

    // we need to focus on allowing any character in s1 to map to any character in s2 such that
    // the frequencies of characters in s1 match the frequencies of characters in s2.
    public static boolean isOneToOneMapping(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        // Frequency Counting: Create two HashMap objects to count the frequencies of characters in s1 and s2.
        Map<Character, Integer> freqS1 = new HashMap<>();
        Map<Character, Integer> freqS2 = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {

            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            freqS1.put(ch1, freqS1.getOrDefault(ch1, 0) + 1);
            freqS2.put(ch2, freqS2.getOrDefault(ch2, 0) + 1);
        }

        // Frequency Comparison: Check if the frequency values in both maps are equal by ensuring both maps' values contain all of each other's values.
        return freqS1.values().containsAll(freqS2.values()) &&
                freqS2.values().containsAll(freqS1.values());
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isOneToOneMapping("abc", "bca")); // Output: true
        System.out.println(isOneToOneMapping("foo", "bar")); // Output: false
        System.out.println(isOneToOneMapping("foi", "bar")); // Output: true
        System.out.println(isOneToOneMapping("aabbcc", "ccbbaa")); // Output: true
        System.out.println(isOneToOneMapping("aabbcc", "ccbbdd")); // Output: true
        System.out.println(isOneToOneMapping("aabbcd", "ccbbdd")); // Output: false
        System.out.println(isOneToOneMapping("aabbca", "ccbbdd")); // Output: false
        System.out.println(isOneToOneMapping("aabbca", "ccbbdc")); // Output: true -> (aa - cc), (bb - bb), (c - c), (a - d)
    }

    public static boolean areFrequencyMapsEqual(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();

            if (!map2.containsKey(key) || !map2.get(key).equals(value)) {
                return false;
            }
        }

        return true;
    }
}
