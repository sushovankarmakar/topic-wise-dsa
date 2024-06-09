
package src;

/**
 * This problem was asked by Google.
 * <p>
 * Given a string of words delimited by spaces, reverse the words in string. For example, given "hello world here", return "here world hello"
 * <p>
 * Follow-up: given a mutable string representation, can you perform this operation in-place?
 */

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/description/
 */
public class _151_ReverseWordsInPlace {

    // https://chatgpt.com/share/6f2f5852-783f-4b8f-994d-effd0d0b056b

    public static String reverseWords(String input) {

        String[] words = input.trim().split(" ");

        int left = 0;
        int right = words.length - 1;

        while (left < right) {
            String temp = words[left].trim();
            words[left] = words[right].trim();
            words[right] = temp;

            left++;
            right--;
        }

        StringBuilder reversed = new StringBuilder();
        for (String word : words) {

            if (word.isBlank()) continue;

            reversed.append(word).append(" ");
        }
        return reversed.toString().trim();
    }

    public static void main(String[] args) {
        String input = "hello world here";
        System.out.println(reverseWords(input).equals("here world hello"));  // Output: "here world hello"

        String input1 = "the sky is blue";
        System.out.println(reverseWords(input1).equals("blue is sky the"));  // Output: "blue is sky the"

        String input2 = "  hello world  ";
        System.out.println(reverseWords(input2).equals("world hello"));  // Output: "world hello"

        String input3 = "a good   example";
        System.out.println(reverseWords(input3).equals("example good a"));  // Output: "example good a"
    }
}
