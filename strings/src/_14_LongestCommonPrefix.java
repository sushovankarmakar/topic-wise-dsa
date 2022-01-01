package src;

import java.util.Arrays;

/**
 * https://workat.tech/problem-solving/practice/longest-common-prefix/editorial (I followed the second approach)
 *
 * https://www.youtube.com/watch?v=1YQmI7F9dJ0&ab_channel=KevinNaughtonJr.
 * https://www.youtube.com/watch?v=fhyIORFDD0k&ab_channel=TECHDOSE
 */
public class _14_LongestCommonPrefix {

  /**
   * OPTIMAL SOLUTION :
   *
   * The optimal and better approach for this problem is
   * to sort the given strings and compare the first and last string to find the longest common prefix.
   *
   * The reason behind checking the first and last strings only is that
   * all the strings in-between must have the same prefix as the strings are sorted lexicographically.
   *
   * Analysis
   * - Time Complexity: O(n * logn + (Max String Size))
   * - Auxiliary Space Complexity: O(1)
   */
  public String longestCommonPrefix_bySoring(String[] inputs) {
    String longestCommonPrefix = "";

    if (inputs == null || inputs.length == 0) {
      return longestCommonPrefix;
    }

    Arrays.sort(inputs);
    int n = inputs.length;
    String firstString = inputs[0];
    String lastString = inputs[n - 1];

    for (int i = 0; i < Math.min(firstString.length(), lastString.length()); i++) {

      char ch1 = firstString.charAt(i);
      char ch2 = lastString.charAt(i);

      if (ch1 == ch2) {
        longestCommonPrefix += ch1;
      } else {
        break;
      }
    }

    return longestCommonPrefix;
  }


  public String longestCommonPrefix(String[] strs) {
    String longestCommonPrefix = "";

    if (strs == null || strs.length == 0) {
      return longestCommonPrefix;
    }

    int index = 0;
    for (char ch : strs[0].toCharArray()) {
      for (int i = 1; i < strs.length; i++) {
        if (index >= strs[i].length() || ch != strs[i].charAt(index)) {
          return longestCommonPrefix;
        }
      }
      longestCommonPrefix += ch;
      index++;
    }
    return longestCommonPrefix;
  }

}
