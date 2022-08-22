package variable_size;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * https://practice.geeksforgeeks.org/problems/smallest-window-in-a-string-containing-all-the-characters-of-another-string-1587115621/1
 * <p>
 * https://www.youtube.com/watch?v=iwv1llyN6mo (Aditya Verma)
 */
public class _76_MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow_usingWhileLoop("TOTMTAPTAT", "TTA")); // TAT
        System.out.println(minWindow_usingWhileLoop("ADOBECODEBANC", "ABC")); // BANC
        System.out.println(minWindow_usingWhileLoop("a", "a"));  // a
        System.out.println(minWindow_usingWhileLoop("a", "aa")); // ""
        System.out.println(minWindow_usingWhileLoop("cabwefgewcwaefgcf", "cae")); // cwae
    }

    private static String minWindow_usingWhileLoop(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int i = 0, j = 0;
        int n = s.length();

        int minLen = Integer.MAX_VALUE;
        String minSubString = "";

        int count = map.size();

        while (j < n) {

            char charRight = s.charAt(j);

            if (map.containsKey(charRight)) {
                map.put(charRight, map.get(charRight) - 1);

                if (map.get(charRight) == 0) {
                    count--;
                }
            }

            if (count == 0) {

                while (count == 0) {

                    int currWindowLength = j - i + 1;

                    if (currWindowLength < minLen) {

                        minLen = currWindowLength;
                        minSubString = s.substring(i, j + 1);
                    }

                    char charLeft = s.charAt(i);
                    if (map.containsKey(charLeft)) {

                        map.put(charLeft, map.get(charLeft) + 1);

                        if (map.get(charLeft) > 0) {    // IMPORTANT to dry run to understand this part.
                            count++;
                        }
                    }
                    i++;
                }
            }
            j++;
        }

        return minSubString;
    }

    private static String minWindow_usingForLoop(String s, String t) {

        Map<Character, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        int mapLength = freqMap.size();

        int left = 0, right = 0;

        boolean isFound = false;
        String smallestWindow = s;

        for (; right < s.length(); right++) {

            char charAtRight = s.charAt(right);
            if (freqMap.containsKey(charAtRight)) {
                freqMap.put(charAtRight, freqMap.get(charAtRight) - 1);

                if (freqMap.get(charAtRight) == 0) {
                    mapLength--;
                }
            }

            // When we found a valid window, move start to find smaller window.
            if (mapLength == 0) {

                while (mapLength == 0) {
                    char charAtLeft = s.charAt(left);

                    int currWindowLength = right - left + 1;
                    if (currWindowLength <= smallestWindow.length()) {
                        smallestWindow = s.substring(left, right + 1);
                        isFound = true;
                    }


                    if (freqMap.containsKey(charAtLeft)) {
                        freqMap.put(charAtLeft, freqMap.get(charAtLeft) + 1);

                        if (freqMap.get(charAtLeft) > 0) {
                            mapLength++;
                        }
                    }
                    left++;
                }
            }
        }

        return isFound ? smallestWindow : "";
    }
}
