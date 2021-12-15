package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/power-set-using-recursion/1
 * https://practice.geeksforgeeks.org/problems/power-set4302/1
 * https://leetcode.com/problems/subsets/
 *
 * https://www.geeksforgeeks.org/recursive-program-to-generate-power-set/
 *
 * https://www.youtube.com/watch?v=Yg5a2FxU4Fo&ab_channel=AdityaVerma (Recursive method)
 * https://www.youtube.com/watch?v=b7AYbpM5YrE&ab_channel=takeUforward (Bit manipulation method)
 */
public class PrintAllSubsetsOrPowerSet {

  public static void main(String[] args) {
    List<String> powerSet1 = powerSet("abc");

    for (String s : powerSet1) {
      System.out.println(s);
    }

    List<String> powerSet2 = powerSet("dmxn");

    for (String s : powerSet2) {
      System.out.println(s);
    }
  }

  static List<String> powerSet(String input) {

    List<String> subsets = new ArrayList<>();
    //getSubSet_ByRecursion_1(subsets, 0, input, "");
    //getSubSet_ByRecursion_2(subsets, input, "");

    getSubSetsBy_BitManipulation(subsets, input);

    Collections.sort(subsets); // lexicographically sorted
    return subsets;
  }

  /**
   *
   * https://www.youtube.com/watch?v=b7AYbpM5YrE&ab_channel=takeUforward
   * https://github.com/striver79/SDESheet/blob/main/powerSetCpp
   */
  private static void getSubSetsBy_BitManipulation(List<String> subsets, String input) {

    int len = input.length();
    int numberOfSubsets = 1 << len; // 2^len can be written as (1 << len)

    for (int i = 0; i < numberOfSubsets; i++) {

      StringBuilder subset = new StringBuilder();
      for (int j = 0; j < len; j++) {

        if ((i & (1 << j)) != 0) {  // checking jth bit is set or not in i, if set, then append ith character to subset
          subset.append(input.charAt(j));
        }
      }
      subsets.add(subset.toString());
    }
  }

  private static void getSubSet_ByRecursion_1(List<String> subset,
      int index, String input, String output) {

    if (index == input.length()) {
      subset.add(output);
      return;
    }

    char ch = input.charAt(index);

    /**
     * Two cases for every character
     *
     * (i)  We do not consider current character as part of current subset
     * (ii) We consider the character as part of current subset
     */
    getSubSet_ByRecursion_1(subset, index + 1, input, output); // first case
    getSubSet_ByRecursion_1(subset, index + 1, input, output + ch);  // second case
  }

  /**
   * https://discuss.geeksforgeeks.org/comment/4966332887
   * https://uploads.disquscdn.com/images/10142bdbf6302bc8cbe462c56884ffa852b08e999693c8317981a070ceebd88f.png
   */
  private static void getSubSet_ByRecursion_2(List<String> subset, String input, String output) {
    if (input.length() == 0) {
      subset.add(output);
      return;
    }

    char ch = input.charAt(0);

    getSubSet_ByRecursion_2(subset, input.substring(1), output);
    getSubSet_ByRecursion_2(subset, input.substring(1), output + ch);
  }

}
