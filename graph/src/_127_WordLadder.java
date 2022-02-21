package src;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/word-ladder/
 * https://workat.tech/problem-solving/practice/word-ladder
 * https://practice.geeksforgeeks.org/problems/word-ladder/1
 *
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_WordLadder.java (First Approach)
 * https://www.youtube.com/watch?v=O3hUyXyHeBw&t=669s&ab_channel=jayatitiwari (A good solution)
 *
 * https://www.geeksforgeeks.org/iterators-in-java/
 */
public class _127_WordLadder {

    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        System.out.println(getLengthOfWordLadder("hit", "cog", words));
    }

    /**
     * https://www.youtube.com/watch?v=O3hUyXyHeBw&t=669s&ab_channel=jayatitiwari
     *
     * time complexity : O(n * n * k) where n = number of words, k = length of the longest word
     * space complexity : O(n)
     */
    private static int getLengthOfWordLadder(String startWord, String targetWord, String[] wordList) {

        List<String> dictionary = Arrays.stream(wordList).collect(Collectors.toList());

        if (startWord == null || targetWord == null || startWord.length() == 0 || targetWord.length() == 0
                || dictionary.isEmpty() || !dictionary.contains(targetWord)) {
            return 0;
        }

        //int length = 1;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(startWord, 1));

        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();

            // we need to use iterator because we need to traverse the dictionary along updating the dictionary
            // also, we can't keep iterator outside this while loop, will create problem.
            Iterator<String> itr = dictionary.iterator();

            while (itr.hasNext()) {
                String nextWord = itr.next();
                if (isAdjacentWord(currentPair.word, nextWord)) {
                    if (nextWord.equalsIgnoreCase(targetWord)) return currentPair.len + 1;

                    itr.remove();   // move the current adjacent word from dictionary because we'll consume it
                    queue.add(new Pair(nextWord, currentPair.len + 1));
                }
            }
            //length++; // updating length separately is very confusing. It is easy to use Pair class.
        }
        return 0;
    }

    private static boolean isAdjacentWord(String wordA, String wordB) {
        int difference = 0;
        for (int i = 0; i < wordA.length(); i++) {
            if (wordA.charAt(i) != wordB.charAt(i)) {
                difference++;
                if (difference > 1) return false;
            }
        }
        return true;
    }

    private static class Pair {
        String word;
        int len;

        Pair(String word, int len) {
            this.word = word;
            this.len = len;
        }
    }
}
