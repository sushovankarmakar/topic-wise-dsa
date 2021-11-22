package fixed_size;

import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
 *
 * https://www.youtube.com/watch?v=MW4lJ8Y0xXk&ab_channel=AdityaVerma
 */
public class CountOccurrencesOfAnagrams {

  /**
   *
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
    int windowSize = pattern.length();
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

      if (right - left + 1 == windowSize) {

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
}
