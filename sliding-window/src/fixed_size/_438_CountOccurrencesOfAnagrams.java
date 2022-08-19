package fixed_size;

import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/ (Almost same : here we need to add left index instead of counting the anagrams)
 * <p>
 * https://www.youtube.com/watch?v=MW4lJ8Y0xXk&ab_channel=AdityaVerma
 * <p>
 * https://www.callicoder.com/count-occurrences-of-anagram/
 * <p>
 * Amazon, Microsoft, Apple, Adobe, Bolt, Facebook, Google, Yahoo, Yandex
 */
public class _438_CountOccurrencesOfAnagrams {

    /**
     * text     : f o r x x o r f x d o f r
     * pattern  : f o r
     */
    public static void main(String[] args) {
        System.out.println(getNumberOfAnagrams("forxxorfxdofr", "for"));
    }

    private static int getNumberOfAnagrams(String text, String pattern) {

        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : pattern.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        int count = 0;
        int k = pattern.length();
        int textLength = text.length();
        int freqMapSize = freqMap.size();
        int left = 0;
        int right = 0;

        /**
         * Mistakes I made :
         * 1. I shouldn't use two maps, and then compare.
         * 2. Even if I use one map, I shouldn't remove characters from freqMap, it will decrease the actual size.
         *  Instead, decrease only the freqMapSize variable, which won't decrease the actual map size.
         */

        for (; right < textLength; right++) {

            char charAtRight = text.charAt(right);
            if (freqMap.containsKey(charAtRight)) {

                freqMap.put(charAtRight, freqMap.get(charAtRight) - 1);

                if (freqMap.get(charAtRight) == 0) {
                    freqMapSize--;
                }
            }

            int window = right - left + 1;
            if (window == k) {

                if (freqMapSize == 0) {
                    count++;
                }

                char charAtLeft = text.charAt(left);
                if (freqMap.containsKey(charAtLeft)) {
                    freqMap.put(charAtLeft, freqMap.get(charAtLeft) + 1);

                    if (freqMap.get(charAtLeft) == 1) {  // IMP : increase map size only when first time frequency becomes 1
                        freqMapSize++;
                    }
                }
                left++;
            }
        }
        return count;
    }

    /**
     * STEPS :
     * 1. decrement freq and if freq == 0, then mapSize--;
     * 2. if mapSize == 0, count++;
     * 3. before sliding the window, increment freq and if freq == 1, then mapSize++;
     */
    /**
     * time complexity : O(N)
     * space complexity : O(K) -
     */
    private static int search(String pat, String txt) {

        Map<Character, Integer> patFreq = new HashMap<>();
        for (char ch : pat.toCharArray()) {
            patFreq.put(ch, patFreq.getOrDefault(ch, 0) + 1);
        }

        int i = 0, j = 0;
        int count = 0;
        int patLen = pat.length();
        int txtLen = txt.length();
        int window;
        int freqMapSize = patFreq.size();

        while (j < txtLen) {

            char rightChar = txt.charAt(j);

            /**
             * STEP 1
             */
            if (patFreq.containsKey(rightChar)) {
                patFreq.put(rightChar, patFreq.get(rightChar) - 1);

                if (patFreq.get(rightChar) == 0) {
                    freqMapSize--;
                }
            }

            window = j - i + 1;

            if (window < patLen) {
                j++;
            } else if (window == patLen) {

                /**
                 * STEP 2
                 */
                if (freqMapSize == 0) {
                    count++;
                }

                /**
                 * STEP 3
                 */
                char leftChar = txt.charAt(i);
                if (patFreq.containsKey(leftChar)) {

                    patFreq.put(leftChar, patFreq.get(leftChar) + 1);

                    if (patFreq.get(leftChar) == 1) {// only for the first time, increase the size.
                        freqMapSize++;
                    }
                }
                i++;
                j++;
            }
        }
        return count;
    }

}
