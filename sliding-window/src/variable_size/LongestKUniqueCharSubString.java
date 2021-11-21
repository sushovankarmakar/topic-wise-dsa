package variable_size;

import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
 *
 * https://www.youtube.com/watch?v=Lav6St0W_pQ&ab_channel=AdityaVerma
 */

public class LongestKUniqueCharSubString {

  public static void main(String[] args) {
    System.out.println(getLongestKUniqueCharSubString("aaaa", 2));
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
