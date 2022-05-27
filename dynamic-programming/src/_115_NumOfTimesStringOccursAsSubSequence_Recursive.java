package src;

/**
 * https://practice.geeksforgeeks.org/problems/find-number-of-times-a-string-occurs-as-a-subsequence3020/1/
 * https://leetcode.com/problems/distinct-subsequences/
 * https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
 * <p>
 * <p>
 * Similar concept like this problem : Count of Subsets Sum with a Given Sum
 * https://www.youtube.com/watch?v=F7wqWbqYn9g&list=PL_z_8CaSLPWekqhdCPmFohncHwz8TY2Go&index=9&ab_channel=AdityaVerma
 *
 * https://leetcode.com/discuss/general-discussion/1276555/find-number-of-times-a-string-occurs-as-a-subsequence-in-given-string
 *
 * companies :
 * amazon, mathworks, google, bloomberg, apple, adobe, goldman sachs
 *
 * choice diagram :
 * https://drive.google.com/file/d/1PSJa3WNpQm-GI0_BSEUYoWdH0qoGH0_s/view?usp=sharing
 */
public class _115_NumOfTimesStringOccursAsSubSequence_Recursive {

    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit")); // 3
        System.out.println(numDistinct("babgbag", "bag"));  // 5
        System.out.println(numDistinct("geeksforgeeks","gks")); // 4
    }

    private static int numDistinct(String s1, String s2) {
        return count(s1, s2, s1.length(), s2.length());
    }

    private static int count(String s1, String s2, int m, int n) {

        // if second string is empty, return 1 because we can always get blank string from
        if (n == 0) {
            return 1;
        }

        // If only first string is empty and second string is not empty,
        // OR first string is smaller than second string, then return 0
        if (m == 0 || m < n) {
            return 0;
        }

        // If last characters are same
        // Recur for remaining strings by
        // 1. considering last characters of both strings
        // 2. ignoring last character of first string
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {

            int includingCurrChar = count(s1, s2, m - 1, n - 1);
            int excludingCurrChar = count(s1, s2, m - 1, n);

            return includingCurrChar + excludingCurrChar;

        } else {

            // If last characters are different, ignore
            // last char of first string and recur for
            // remaining string
            return count(s1, s2, m - 1, n);
        }
    }
}
