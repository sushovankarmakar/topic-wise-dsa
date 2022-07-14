package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/scramble-string/
 * https://leetcode.com/problems/scramble-string/discuss/29387/Accepted-Java-solution
 * <p>
 * https://www.youtube.com/watch?v=KIy1t21H0Mo ( Live Mock Interview with Striver )
 * https://www.youtube.com/watch?v=SqA0o-DGmEw&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go ( Recursive )
 * https://www.youtube.com/watch?v=VyHEglhbm-A&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go ( Memoized )
 * <p>
 * https://www.geeksforgeeks.org/check-if-a-string-is-a-scrambled-form-of-another-string/
 */
public class _87_ScrambleString {

    public static void main(String[] args) {
        System.out.println(isScramble("great", "rgeat")); // true
        System.out.println(isScramble("abcde", "caebd")); // false
        System.out.println(isScramble("a", "a")); // true
    }

    private static Map<String, Boolean> memo = new HashMap<>();

    private static boolean isScramble(String s1, String s2) {

        String key = s1 + "_" + s2;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (s1.length() != s2.length()) {   // base condition
            return false;
        }

        int n = s1.length();

        if (n == 0) { // base condition
            return false;
        }

        if (s1.equalsIgnoreCase(s2)) {
            return true;
        }

        // An optimization step to consider here is to check beforehand
        // if the two strings are anagrams of each other.
        // If not, it indicates that the strings contain different characters
        // and can’t be a scrambled form of each other.
        if (!isAnagram(s1, s2)) {
            return false;
        }

        for (int i = 1; i <= n - 1; i++) {

            // in case of swap
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, n - i))) {
                return true;
            }

            // in case of no swap
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }

    private static boolean isAnagram(String s1, String s2) {

        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();

        Arrays.sort(char1);
        Arrays.sort(char2);

        s1 = new String(char1);
        s2 = new String(char2);

        return s1.equalsIgnoreCase(s2);
    }
}
