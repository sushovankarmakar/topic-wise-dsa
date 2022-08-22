package variable_size;

import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * <p>
 * Below two questions are exactly. Only question description is different.
 * https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/ - here k is not given. because k = 2;
 * https://leetcode.com/problems/fruit-into-baskets/  - longest-substring-with-at-most-two-distinct-characters
 * <p>
 * https://www.youtube.com/watch?v=Lav6St0W_pQ&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=seOKHXB_w74 (Aditya Verma) -
 * 904. Fruit Into Baskets : What is the length of the longest sub array that contains up to two distinct integers ?
 * <p>
 * https://leetcode.com/problems/fruit-into-baskets/discuss/170740/JavaC%2B%2BPython-Sliding-Window-for-K-Elements
 */
public class _159_340_904_LongestSubStringWithKUniqueChar {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("aaaa", 2));   // 4
        System.out.println(lengthOfLongestSubstringKDistinct("aabacbebebe", 3)); // 7
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2)); // 3
    }

    /**
     * in GFG, it says exactly K unique chars. then we only need to calculate answer in (map.size() < k) condition.
     * but
     * in LC, it says at most K unique chars. then we need to calculate answer in (map.size() < k) & (map.size() == k) in both conditions.
     */
    private static int lengthOfLongestSubstringKDistinct(String s, int k) {

        /**
         * IMPORTANT :
         * we need to use map instead of set,
         * because we can only remove the element from the map when all the frequency become zero.
         * but in set, we can't maintain the frequency.
         */
        Map<Character, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;
        int n = s.length();
        int maxLen = 0;

        while (right < n) {

            char charRight = s.charAt(right);
            map.put(charRight, map.getOrDefault(charRight, 0) + 1);

            if (map.size() < k) {

                /**
                 * we need this maxLen here also because in LC, question asked 'at most'.
                 *
                 * BUT as per GFG, if 'exactly' K unique chars, then we only need to calculate when map.size() == k.
                 */
                int currLen = right - left + 1;
                maxLen = Math.max(maxLen, currLen);

                right++;
            } else if (map.size() == k) {

                int currLen = right - left + 1;
                maxLen = Math.max(maxLen, currLen);

                right++;
            } else if (map.size() > k) {

                while (map.size() > k) {

                    char charLeft = s.charAt(left);

                    if (map.containsKey(charLeft)) {
                        map.put(charLeft, map.get(charLeft) - 1);
                    }

                    if (map.get(charLeft) == 0) {
                        map.remove(charLeft);
                    }

                    left++;
                }

                right++;
            }
        }

        return maxLen;
    }

    private static int getLongestKUniqueCharSubString(String str, int k) {

        int longestLength = -1;
        Map<Character, Integer> freqMap = new HashMap<>();
        int left = 0, right = 0, n = str.length();
        for (; right < n; right++) {

            char charAtRight = str.charAt(right);
            freqMap.put(charAtRight, freqMap.getOrDefault(charAtRight, 0) + 1);

            if (freqMap.size() == k) {
                int curLength = right - left + 1;
                longestLength = Math.max(longestLength, curLength);
            }

            if (freqMap.size() > k) {
                char charAtLeft = str.charAt(left);
                freqMap.put(charAtLeft, freqMap.get(charAtLeft) - 1);

                if (freqMap.get(charAtLeft) == 0) {
                    freqMap.remove(charAtLeft);
                }
                left++;
            }
        }
        return longestLength;
    }
}
