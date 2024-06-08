package src.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This problem was asked by Amazon.
 * <p>
 * Given a string s and an integer k,
 * break up the string into multiple lines such that each line has a length of k or less.
 * <p>
 * You must break it up so that words don't break across lines.
 * Each line has to have the maximum possible amount of words. If there's no way to break the text up, then return null.
 * <p>
 * You can assume that there are no spaces at the ends of the string and that there is exactly one space between each word.
 * <p>
 * For example, given the string "the quick brown fox jumps over the lazy dog" and k = 10,
 * you should return: ["the quick", "brown fox", "jumps over", "the lazy", "dog"].
 * No string in the list has a length of more than 10.
 */

public class _20240608_1420_BreakLineIntoKLengthLines {

    // https://chatgpt.com/share/3fbe0dbf-57ae-4e8a-a098-f6576939d4bc

    // The condition (line.isEmpty() ? 0 : 1) ensures that spaces between words are correctly accounted
    // for when determining if a word can be added to the current line.
    // This condition helps maintain the correct line length and ensures words are added to the lines correctly
    // without exceeding the specified length 𝑘

    public static List<String> getLines(String inputString, int k) {
        String[] words = inputString.split(" ");
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (String word : words) {

            // If a single word is longer than k, it's impossible to wrap properly
            if (word.length() > k) {
                return null;
            }

            // Check if adding the next word would exceed the line length
            // we need to check (line.isEmpty() ? 0 : 1) condition because, inside the for loop we're adding a space conditionally
            if (line.length() + word.length() + (line.isEmpty() ? 0 : 1) <= k) {
                // If not, add the word to the current line
                if (!line.isEmpty()) {
                    line.append(" ");
                }
                line.append(word);
            } else {
                // Otherwise, add the current line to the result and start a new line
                lines.add(line.toString());
                line = new StringBuilder(word);
            }
        }

        // Add the last line to the result
        if (!line.isEmpty()) {
            lines.add(line.toString());
        }

        return lines;
    }

    public static void main(String[] args) {
        String s = "a quick brown fox jumps over the lazy dog";
        int k = 12;
        List<String> wrappedText = getLines(s, k);

        if (wrappedText != null) {
            wrappedText.forEach(System.out::println);
        } else {
            System.out.println("Cannot wrap the text with the given constraints.");
        }
    }

    public static void main1(String[] args) {

        List<TestCase> testCases = Arrays.asList(
                // normal given test case
                new TestCase("the quick brown fox jumps over the lazy dog", 10, List.of("the quick", "brown fox", "jumps over", "the lazy", "dog")),
                // Combination of Lengths: A mix of short and long words.
                new TestCase("a quick brown fox jumps over a lazy dog", 12, Arrays.asList("a quick", "brown fox", "jumps over", "a lazy dog")),
                // Single Short Word: A single word that is shorter than k
                new TestCase("hello", 10, List.of("hello")),
                // Single Long Word: A single word that is longer than k
                new TestCase("supercalifragilisticexpialidocious", 10, null),
                // Exact Fit: A string that fits exactly into the given length k
                new TestCase("hello world", 11, List.of("hello world")),
                // Multiple Short Words: Multiple words where each word is shorter than k and can fit together within the limit.
                new TestCase("the quick brown fox", 10, Arrays.asList("the quick", "brown fox")),
                // Words of Length One: Each word is a single character
                new TestCase("a b c d e f", 3, Arrays.asList("a b", "c d", "e f")),
                // Empty String: An empty input string
                new TestCase("", 10, List.of()),
                // Exact Length Words: Words that have lengths exactly equal to k.
                new TestCase("abcdefghij", 10, List.of("abcdefghij")),
                // Edge Case with Minimum k: A very small value for k.
                new TestCase("a b c d", 1, Arrays.asList("a", "b", "c", "d"))
        );

        for (TestCase testCase : testCases) {
            List<String> result = getLines(testCase.input, testCase.k);
            System.out.println("Input: \"" + testCase.input + "\", k = " + testCase.k);
            System.out.println("Expected Output: " + testCase.expectedOutput);
            System.out.println("Actual Output: " + result);
            System.out.println();
        }
    }

    static class TestCase {
        String input;
        int k;
        List<String> expectedOutput;

        TestCase(String input, int k, List<String> expectedOutput) {
            this.input = input;
            this.k = k;
            this.expectedOutput = expectedOutput;
        }
    }
}
