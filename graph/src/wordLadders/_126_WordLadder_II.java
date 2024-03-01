package src.wordLadders;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/
 * https://www.youtube.com/watch?v=AD4SFl7tu7I&ab_channel=takeUforward (G-31. Word Ladder - 2 | Optimised Approach for Leetcode)
 * https://takeuforward.org/graph/word-ladder-ii-optimised-approach-g-31/ (I followed this code to solve the problem)
 */
public class _126_WordLadder_II {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(ladderLength("hit", "cog", words));
        /*
         * [
         * [hit, hot, dot, dog, cog],
         * [hit, hot, lot, log, cog]
         * ]
         */

        List<String> words1 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println(ladderLength("hit", "cog", words1)); // [], as the endWord is not present in the wordList
    }

    private static List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList); // converting wordlist to hashset
        // BASE CONDITION
        if(beginWord.isEmpty() || endWord.isEmpty() || set.isEmpty() || !set.contains(endWord))
            return new ArrayList<>();

        set.add(beginWord); // IMPORTANT to add beginWord to set, wordLevelMap and the queue. ADD in all 3 places.

        Map<String, Integer> wordLevelMap = new HashMap<>();
        wordLevelMap.put(beginWord, 1);

        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));

        while (!queue.isEmpty()) {

            String currWord = queue.peek().word;
            int currSteps = queue.peek().steps;

            // "==" compares the memory location of two objects, while "equals" compares the contents of two objects
            if (currWord.equals(endWord)) break;    // DIFFERENCE

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

                        wordLevelMap.put(newWord, currSteps + 1);    // DIFFERENCE
                    }
                }
            }
        }

        List<List<String>> allShortestPaths = new ArrayList<>();
        dfs(endWord, beginWord, wordLevelMap, new ArrayList<>(Collections.singletonList(endWord)), allShortestPaths);
        return allShortestPaths;
    }

    private static void dfs(String currWord, String beginWord,
                            Map<String, Integer> wordLevelMap,
                            List<String> seq, List<List<String>> allShortestPaths) {

        if (currWord.equals(beginWord)) {
            List<String> temp = new ArrayList<>(seq);
            Collections.reverse(temp);
            allShortestPaths.add(temp);
            return;
        }

        for (int i = 0; i < currWord.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {

                // creating a new word by character replacement
                char[] currWordCharArray = currWord.toCharArray();
                currWordCharArray[i] = ch;
                String newWord = new String(currWordCharArray);

                if (wordLevelMap.containsKey(newWord) && wordLevelMap.get(newWord) == wordLevelMap.get(currWord) - 1) {

                    seq.add(newWord);
                    dfs(newWord, beginWord, wordLevelMap, seq, allShortestPaths);

                    // pop the current word from the back of the queue
                    // to traverse other possibilities.
                    seq.remove(seq.size() - 1);
                }
            }
        }
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
