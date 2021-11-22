package variable_size;

import java.util.HashMap;
import java.util.Map;

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

  // https://www.youtube.com/watch?v=qtVh-XEpsJo&t=1261s&ab_channel=takeUforward
  // this is striver style.
  public int getLengthOfLongestSubString(String str) {

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
