package src;

import java.util.HashMap;
import java.util.Map;

/**
 * EDIT DISTANCE
 *
 * https://leetcode.com/problems/edit-distance/
 */

public class MakeMyTripInterview {

  public static void main(String[] args) {
    String src = "Sndayu";
    String dest = "Saturday";

    // 1. add
    // 2. remove
    // 3. replace

    int n = src.length();
    int m = dest.length();
    int minCount = 0;

    /**
     *
     * S n d a y u
     *   i
     *
     * S a t u r d a y
     *   j
     *
     * count = 0
     */

    /*Map<Character, Integer> map = new HashMap<>();
    for (char ch : dest.toCharArray()) {
      map.put(ch, map.getOrDefault(ch, 0) + 1);
    }*/

    for (int i = 0; i < n; i++) {

      for (int j = i; j < m; j++) {
        int count = 0;
        if (src.charAt(i) == dest.charAt(i)) {
          break;
        } {
          minCount++;
        }

        if (j == m) {
          minCount += 1;  // replace ith char
        } else {
          minCount = count; // this number of chars to be added.
        }
      }
    }
    System.out.println(minCount);
  }

}
