package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://practice.geeksforgeeks.org/problems/anagram-1587115620/1
 * https://leetcode.com/problems/valid-anagram/
 * <p>
 * https://www.geeksforgeeks.org/check-whether-two-strings-are-anagram-of-each-other/
 * <p>
 * Amazon, Bloomberg, Apple, Spotify, Microsoft, Facebook, Yahoo, Affirm, Google, Yandex, Tesla, JPMorgan, Goldman Sachs
 * Snapchat, Walmart Global Tech, Oracle, IBM, American Express, Adobe, Cisco, Qualcomm, BlackRock, Paypal, Grab,
 * Salesforce, Visa, Dell, Uber, Yelp
 */
public class _242_ValidAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));    // true
        System.out.println(isAnagram_usingSort("rat", "car"));    // false
    }

    // FASTER SOLUTION
    public static boolean isAnagram_usingSort(String a, String b) {

        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();

        Arrays.sort(charA);
        Arrays.sort(charB);

        a = new String(charA);
        b = new String(charB);

        return a.equals(b);
    }

    private static boolean isAnagram(String s, String t) {

        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        for (char ch : t.toCharArray()) {

            if (freq.containsKey(ch)) {

                freq.put(ch, freq.get(ch) - 1);

                if (freq.get(ch) == 0) {
                    freq.remove(ch);
                }

            } else {
                return false;
            }
        }

        return freq.size() == 0;
    }


}
