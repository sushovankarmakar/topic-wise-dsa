package src;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * https://practice.geeksforgeeks.org/problems/non-repeating-character-1587115620/1
 * <p>
 * https://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
 * https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86348/Java-7-lines-solution-29ms
 * <p>
 * similar question :
 * https://leetcode.com/problems/first-letter-to-appear-twice/
 */
public class _387_FirstUniqueCharacter {

    public static void main(String[] args) {
        System.out.println(firstUniqueChar_usingArray("leetcode")); // 0
        System.out.println(firstUniqueChar_usingHashMap("loveleetcode")); // 2
        System.out.println(firstUniqueChar_usingLinkedHashMap("aabb")); // -1
    }

    // FASTEST SOLUTION : Count array and single string traversal:
    private static int firstUniqueChar_usingArray(String s) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {

            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    private static int firstUniqueChar_usingHashMap(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {

            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    private static int firstUniqueChar_usingLinkedHashMap(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {

            if (entry.getValue() == 1) {

                for (int i = 0; i < s.length(); i++) {

                    if (s.charAt(i) == entry.getKey()) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }
}
