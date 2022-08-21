package src;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/largest-substring-between-two-equal-characters/
 * https://leetcode.com/problems/largest-substring-between-two-equal-characters/discuss/899503/One-Pass-HashMap-Solution-and-O(1)-Space-Solution
 * <p>
 * https://www.youtube.com/watch?v=rfjeFs3JuYM
 */
public class _1624_LargestSubStringBetTwoEqualChars {

    public static void main(String[] args) {
        System.out.println(maxLengthBetweenEqualCharacters_usingIndexOf("abcaxyza"));   // 6
        System.out.println(maxLengthBetweenEqualCharacters_usingIndexOf("abca"));       // 2
        System.out.println(maxLengthBetweenEqualCharacters_usingIndexOf("cbzxy"));      // -1
        System.out.println(maxLengthBetweenEqualCharacters_usingMap("aa"));             // 0
    }

    private static int maxLengthBetweenEqualCharacters_usingIndexOf(String s) {

        int maxLen = -1;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            int firstIndex = s.indexOf(ch);
            int lastIndex = s.lastIndexOf(ch);

            int currLen = lastIndex - firstIndex - 1;
            maxLen = Math.max(maxLen, currLen);
        }

        return maxLen;
    }

    private static int maxLengthBetweenEqualCharacters_usingMap(String s) {

        Map<Character, Integer> firstSeenAt = new HashMap<>();
        int maxLen = -1;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            if (firstSeenAt.containsKey(ch)) {

                int currLen = i - firstSeenAt.get(ch) - 1;
                maxLen = Math.max(maxLen, currLen);

            } else {

                firstSeenAt.put(ch, i);
            }
        }
        return maxLen;
    }
}
