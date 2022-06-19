package src;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 * https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string3411/1/
 * https://practice.geeksforgeeks.org/problems/longest-palindrome-in-a-string1956/1/
 * <p>
 * https://www.youtube.com/watch?v=Msghl9189X4 (Helpful to understand the logic)
 * https://leetcode.com/problems/longest-palindromic-substring/discuss/1056859/recursion-a-few-dp-variants (I followed this recursive approach)
 */

/**
 * Amazon, Microsoft, Google, Adobe, Apple, Facebook, Goldman Sachs, Oracle,
 * Yahoo, Bloomberg, Visa, tiktok, Uber, Walmart, ByteDance, PayTM
 */
public class _5_LongestPalindromicSubstring_Recursive {

    /**
     * WHAT MISTAKE I DID : ( mentioned in leetcode tutorial and https://www.geeksforgeeks.org/longest-palindrome-substring-set-1/)
     * Longest Common Substring :
     * Reverse of S become S'
     * Find the longest common substring between S and S' which must also be the longest palindromic substring.
     * THIS WON'T WORK. example : aacabdkacaa
     *
     * We could see that the longest common substring method fails when there exists a reversed copy of
     * a non-palindromic substring in some other part of S.
     */
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));   // aca
        System.out.println(longestPalindrome("babad")); // bab
        System.out.println(longestPalindrome("cbbd"));  // bb
        System.out.println(longestPalindrome("abbcccbbbcaaccbababcbcabca")); // will take long time, so go for memoization.
    }

    /**
     * Similar concept to longest palindromic subsequence.
     */
    public static String longestPalindrome(String input) {

        return lps(input, 0, input.length() - 1);
    }

    private static String lps(String input, int start, int end) {

        if (start > end) return "";

        if (start == end) return String.valueOf(input.charAt(start));

        // if start and end char are same,
        // then check if remaining string in between start and end char is palindrome or not.
        if (input.charAt(start) == input.charAt(end)) {

            int lpsRemainingStringLength = end - start - 1;

            String middleString = lps(input, start + 1, end - 1);

            if (lpsRemainingStringLength == middleString.length()) {

                return input.charAt(start) + middleString + input.charAt(end);
            }
        }

        String left = lps(input, start + 1, end);
        String right = lps(input, start, end - 1);

        return left.length() > right.length() ? left : right;
    }
}
