package src;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/number-of-different-integers-in-a-string/
 *
 * https://leetcode.com/problems/number-of-different-integers-in-a-string/discuss/1142567/Java-solution-in-1-line-with-lambdas!
 */
public class _1805_NumOfDiffIntegersInString {

  public static void main(String[] args) {

    /**
     * Input: word = "a123bc34d8ef34"
     * Output: 3
     * Explanation: The three different integers are "123", "34", and "8". Notice that "34" is only counted once.
     */
    System.out.println(numDifferentIntegers("a123bc34d8ef34")); // 3

    /**
     * Input: word = "leet1234code234"
     * Output: 2
     */
    System.out.println(numDifferentIntegers("leet1234code234"));  // 2

    /**
     * Input: word = "a1b01c001"
     * Output: 1
     * Explanation: The three integers "1", "01", and "001" all represent the same integer because
     * the leading zeros are ignored when comparing their decimal values.
     */
    System.out.println(numDifferentIntegers("a1b01c001"));    // 1
  }

  /**
   * https://www.rexegg.com/regex-quickstart.html
   * https://medium.com/javarevisited/the-power-of-java-lambda-expressions-eb3a8b9ae5f8
   */
  public static long numDifferentIntegers(String word) {

    return Arrays.stream(word.replaceAll("[a-z]+", " ").split(" "))
        .filter(str -> !"".equals(str))
        .map(str -> str.replaceAll("^0+", ""))
        .collect(Collectors.toSet())
        .size();

    /**
     * STEPS :
     * -------------------
     * 1. word.replaceAll("[a-z]+", " ") - We replace all successive letters with space.
     *    So we have numbers split with spaces. replaceAll() returns just one String.
     *
     * 2. word.replaceAll("[a-z]+", " ").split(" ") - We split numbers into arrays of numbers (as string array).
     *    First and last elements can be empty strings if we had space on the beginning
     *    or end of string word.replaceAll("[a-z]+", " ")
     *
     * 3. Arrays.stream(word.replaceAll("[a-z]+", " ").split(" ")) - convert array into stream of elements
     *
     * 4. .filter(s -> !"".equals(s)) - removes possibly empty strings on the beginning or end of string stream
     *
     * 5. .map(s -> s.replaceFirst("^0+", "")) - transforms numbers (as strings) into strings without leading zeros.
     *    This is what tells regex ^0+. We remove just first occurrence of this,
     *    because there is just one beginning of the string :-) . replaceAll() would also work.
     *
     * 5. .collect(Collectors.toSet()) - makes set from string stream. Duplicates are eliminated because this is set
     * 6. .size() - The size of the set is number of different numbers
     *
     * We didn't use Long.parseLong() or Integer.parseInt() because number of digits in number can be quite big,
     * and we would have exceptions. Because of that we have used set of strings.
     */
  }

}
