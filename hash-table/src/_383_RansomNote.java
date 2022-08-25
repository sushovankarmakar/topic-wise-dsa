package src;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/ransom-note/
 * https://leetcode.com/problems/ransom-note/discuss/1671552/1ms-100-or-Easy-Explanation-or-Java-Solution
 * https://leetcode.com/problems/ransom-note/discuss/85783/Java-O(n)-Solution-Easy-to-understand
 */
public class _383_RansomNote {

    public static void main(String[] args) {
        System.out.println(canConstruct_usingOneMap("bg", "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"));
        System.out.println(canConstruct_usingTwoMaps("bg", "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"));
    }

    private static boolean canConstruct_usingOneMap(String ransomNote, String magazine) {

        int n = ransomNote.length();
        int m = magazine.length();

        if (n > m) {
            return false;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : ransomNote.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        for (char ch : magazine.toCharArray()) {

            if (freq.containsKey(ch)) {
                freq.put(ch, freq.get(ch) - 1);

                if (freq.get(ch) == 0) {
                    freq.remove(ch);
                }
            }
        }

        return freq.size() == 0;
    }

    private static boolean canConstruct_usingTwoMaps(String ransomNote, String magazine) {

        int n = ransomNote.length();
        int m = magazine.length();

        if (n > m) {
            return false;
        }

        Map<Character, Integer> freq1 = new HashMap<>();
        for (char ch : ransomNote.toCharArray()) {
            freq1.put(ch, freq1.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> freq2 = new HashMap<>();
        for (char ch : magazine.toCharArray()) {
            freq2.put(ch, freq2.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : freq1.entrySet()) {

            if (freq2.containsKey(entry.getKey()) &&
                    entry.getValue() <= freq2.get(entry.getKey())) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
