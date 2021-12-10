package src;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * https://www.geeksforgeeks.org/kth-most-frequent-character-in-a-given-string/
 *
 * Given a string str and an integer K, the task is to find the K-th most frequent character in the string. If there are
 * multiple characters that can account as K-th most frequent character then, print the smallest one by alphabetically.
 */
public class KthMostFrequentCharInString {

  public static void main(String[] args) {
    /**
     * e 4
     * g 2
     * k 2
     * s 2
     * f 1
     * o 1
     * r 1
     */
    System.out.println(getKthMostFrequency("geeksforgeeks", 3));  // f
    /**
     * i 3
     * a 2
     * l 2
     * o 2
     * t 2
     * c 1
     * h 1
     * m 1
     * n 1
     * r 1
     */
    System.out.println(getKthMostFrequency("trichotillomania", 3)); // c
  }

  private static String getKthMostFrequency(String str, int k) {

    Map<Character, Integer> freq = new HashMap<>();
    for (char ch : str.toCharArray()) {
      freq.put(ch, freq.getOrDefault(ch, 0) + 1);
    }

    /**
     * https://www.geeksforgeeks.org/sorting-hashmap-according-key-value-java/
     * https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
     *
     * https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
     * https://www.java67.com/2019/06/top-5-sorting-examples-of-comparator-and-comparable-in-java.html
     *
     * https://stackoverflow.com/questions/1383797/java-hashmap-how-to-get-key-from-value
     *
     */
    Comparator<Entry<Character, Integer>> byKeyAndThenByValue =
        Comparator.comparing(Entry<Character, Integer>::getValue) // sorted by frequency number in reverse
        .reversed()
        .thenComparing(Entry<Character, Integer>::getKey);  // sorted by alphabetically

    freq = freq.entrySet()
        .stream()
        .sorted(byKeyAndThenByValue)
        .collect(Collectors.toMap(
            Map.Entry::getKey, Map.Entry::getValue,
            (e1, e2) -> e1, LinkedHashMap::new));

    for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }

    int count = 0;
    char prevChar = '-';
    for (Map.Entry<Character, Integer> entry : freq.entrySet()) {

      // Increment count only if frequency is not same as previous
      if (prevChar != entry.getKey() && !Objects.equals(freq.get(prevChar), entry.getValue())) {
        count++;
      }
      prevChar = entry.getKey();

      if (count == k) {
        return entry.getKey() + "";
      }
    }
    return "-1";
  }

}
