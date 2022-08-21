package variable_size;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/length-of-the-longest-substring3036/1
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * https://www.youtube.com/watch?v=qtVh-XEpsJo&t=1261s&ab_channel=takeUforward
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/discuss/1729/11-line-simple-Java-solution-O(n)-with-explanation
 * <p>
 * https://www.youtube.com/watch?v=L6cffskouPQ&ab_channel=AdityaVerma
 */
public class _3_LongestSubStrNoRepeatingChars {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring_usingMap("abcabcbb"));
        System.out.println(lengthOfLongestSubstring_usingSet("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }

    // https://www.youtube.com/watch?v=qtVh-XEpsJo&t=1261s&ab_channel=takeUforward
    // this is striver style.
    private static int lengthOfLongestSubstring_usingMap(String s) {

        Map<Character, Integer> lastSeenAt = new HashMap<>();
        int left = 0, right = 0;
        int n = s.length();
        int maxLen = 0;

        /**
         * before adding charRight check if that presents in the lastSeenAt or not.
         * if not present, directly add it and right++
         * if already present, then before adding charRight,
         * we need to do two things
         *      1. we need to place the left pointer exactly after the index where charRight was last seen.
         *      2. calculate the window size.
         * after that update charRight with its index.
         *
         */
        while (right < n) {
            char charRight = s.charAt(right);

            if (lastSeenAt.containsKey(charRight)) {
                left = Math.max(left, lastSeenAt.get(charRight) + 1);
            }

            maxLen = Math.max(maxLen, right - left + 1);

            lastSeenAt.put(charRight, right);
            right++;
        }

        return maxLen;
    }

    private static int lengthOfLongestSubstring_usingSet(String s) {

        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        int n = s.length();
        int maxLen = 0;

        /**
         * before adding charRight check if that presents in the set or not.
         *      1. if not present, directly add it and right++
         *      2. if already present, then before adding charRight,
         *         we need to remove elements from set from left until charRight is removed from set.
         *      and keep track of the largest size the set gets at any point.
         */

        while (right < n) {
            char charRight = s.charAt(right);

            while (set.contains(charRight)) {
                char leftChar = s.charAt(left);
                set.remove(leftChar);
                left++;
            }

            set.add(charRight);
            right++;

            maxLen = Math.max(maxLen, set.size());
        }
        return maxLen;
    }

    // https://www.youtube.com/watch?v=qtVh-XEpsJo&t=1261s&ab_channel=takeUforward
    // this is striver style.
    private static int getLengthOfLongestSubString(String str) {

        Map<Character, Integer> lastSeenAt = new HashMap<>();

        int longestWindowLen = 0;
        int left = 0, right = 0;

        for (; right < str.length(); right++) {

            char charAtRight = str.charAt(right);

            if (lastSeenAt.containsKey(charAtRight)) {
                // IMPORTANT : only update left pointer, if that is in the window range (right - left + 1)
                left = Math.max(left, lastSeenAt.get(charAtRight) + 1);
            }

            lastSeenAt.put(charAtRight, right);

            int currentWindowLen = right - left + 1;
            longestWindowLen = Math.max(longestWindowLen, currentWindowLen);
        }

        return longestWindowLen;
    }

    // https://www.youtube.com/watch?v=L6cffskouPQ&ab_channel=AdityaVerma
    // This is Aditya Verma Style. - same code of "Longest SubString With K Unique Characters".
    private static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int n = s.length();
        int maxLen = 0;

        while (right < n) {

            char charRight = s.charAt(right);
            map.put(charRight, map.getOrDefault(charRight, 0) + 1);

            if (map.size() == (right - left + 1)) {

                maxLen = Math.max(maxLen, (right - left + 1));
                right++;

            } else if (map.size() < (right - left + 1)) {

                while (map.size() < (right - left + 1)) {

                    char leftChar = s.charAt(left);

                    if (map.containsKey(leftChar)) {
                        map.put(leftChar, map.get(leftChar) - 1);

                        if (map.get(leftChar) == 0) {
                            map.remove(leftChar);
                        }
                    }
                    left++;
                }
                right++;
            }
        }

        return maxLen;
    }

    // https://www.youtube.com/watch?v=L6cffskouPQ&ab_channel=AdityaVerma
    // This is Aditya Verma Style.
    int longestUniqueSubsttr(String s) {

        Map<Character, Integer> freqMap = new HashMap<>();

        int length = 0;
        int left = 0, right = 0;

        for (; right < s.length(); right++) {

            char ch = s.charAt(right);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);

            if (freqMap.size() == (right - left + 1)) {

                length = Math.max(length, right - left + 1);

            } else if (freqMap.size() < (right - left + 1)) {

                while (freqMap.size() < (right - left + 1)) {

                    char ch1 = s.charAt(left);

                    freqMap.put(ch1, freqMap.get(ch1) - 1);

                    if (freqMap.get(ch1) <= 0) {
                        freqMap.remove(ch1);
                    }
                    left++;
                }
            }
        }
        return length;
    }

}
