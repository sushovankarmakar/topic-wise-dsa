package src.string;

import java.util.HashMap;
import java.util.Map;

public class _20240602_1414_OneToOneCharMapping {

    // explanation : https://chatgpt.com/share/250e10ed-ebef-491b-9e91-57c8c62afcae
    public static boolean isOneToOneMapping(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character,  Integer> freqS1 = new HashMap<>();
        Map<Character,  Integer> freqS2 = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {

            char ch1 = s1.charAt(i);
            char ch2 = s2.charAt(i);

            freqS1.put(ch1, freqS1.getOrDefault(ch1, 0) + 1);
            freqS2.put(ch2, freqS2.getOrDefault(ch2, 0) + 1);
        }

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
