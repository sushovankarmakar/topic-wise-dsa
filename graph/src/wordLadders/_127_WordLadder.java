package src.wordLadders;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/
 * https://www.youtube.com/watch?v=tRPda0rcf8E&ab_channel=takeUforward
 * https://takeuforward.org/graph/word-ladder-i-g-29/
 *
 * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Graph_WordLadder.java (Previously solved)
 */
public class _127_WordLadder {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ladderLength("hit", "cog", words)); // 5
    }

    /**
     *
     *          hit (start_word)    1
     *           |
     *          hot                 2
     *         /  \
     *       dot  lot               3
     *      |      |
     *    dog     log               4
     *     |       |
     *    cog     cog (end_word)    5
     *
     * We're going level wise, so we need to apply BFS here.
     */

    /**
     * Time Complexity:     O(N * M * 26)
     * Where N = size of wordList Array and M = word length of words present in the wordList
     *
     * Space Complexity:    O(N)
     * Where N = size of wordList Array.
     */
    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);

        // BASE CONDITION
        if(beginWord.isEmpty() || endWord.isEmpty() || set.isEmpty() || !set.contains(endWord))
            return 0;

        set.add(beginWord);

        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));

        while (!queue.isEmpty()) {

            String currWord = queue.peek().word;
            int currSteps = queue.peek().steps;

            // "==" compares the memory location of two objects, while "equals" compares the contents of two objects
            if (currWord.equals(endWord)) return currSteps;

            queue.remove();
            set.remove(currWord);

            // traverse through curr word's each character and replace it with from a -> z
            for (int i = 0; i < currWord.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    // creating a new word by character replacement
                    char[] currWordCharArray = currWord.toCharArray();
                    currWordCharArray[i] = ch;
                    String newWord = new String(currWordCharArray);

                    // if new word present in set, then remove from set and add to queue
                    if (set.contains(newWord)) {
                        set.remove(newWord);
                        queue.add(new Pair(newWord, currSteps + 1));
                    }
                }
            }
        }

        return 0;
    }

    private static Set<String> convertListToSet(List<String> wordList) {
        /*Set<String> set = new HashSet<>();

        for (int i = 0; i < wordList.size(); i++) {
            set.add(wordList.get(i));
        }

        return set;*/
        return new HashSet<>(wordList);
    }

    private static class Pair {
        String word;
        int steps;

        Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }
}
