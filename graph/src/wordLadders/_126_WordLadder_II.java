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

    /**
     * CONCEPT:
     * --------
     * 1. For each word, we will keep track of the level at which it is present.
     *      a. for this, same code will be used as in _127_WordLadder.java (Word Ladder 1)
     *      b. we will use a map to store the word and its level.
     *      c. use BFS here.
     *
     * 2. We will use DFS to find all the shortest paths from endWord to beginWord.
     *      a. for this, we will use the map created in step 1.
     *      b. while backtracking, we will check if the new word is 1 level less than the current word.
     *      c. if yes, then we will add the new word to the sequence and call the DFS again.
     *      d. we will keep doing this until we reach the beginWord.
     *      e. we will keep adding the sequence to the result list.
     */
    private static List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList); // converting wordlist to hashset
        set.add(beginWord); // IMPORTANT to add beginWord to set, wordLevelMap and the queue. ADD in all 3 places.

        // BASE CONDITION
        if(beginWord.isEmpty() || endWord.isEmpty() || !set.contains(endWord)) {
            return new ArrayList<>();
        }

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

    /**
     * DFS call - 1:
     * ------------
     *          dfs("cog", "hit", wordLevelMap, ["cog"], [])
     *                          |
     *         dfs("log", "hit", wordLevelMap, ["log", "cog"], [])
     *                          |
     *         dfs("lot", "hit", wordLevelMap, ["lot", "log", "cog"], [])
     *                          |
     *        dfs("hot", "hit", wordLevelMap, ["hot", "lot", "log", "cog"], [])
     *                          |
     *       dfs("hit", "hit", wordLevelMap, ["hit", "hot", "lot", "log", "cog"], [])
     */

    /**
     * DFS call - 2:
     * ------------
     *              dfs("cog", "hit", wordLevelMap, ["cog"], [])
     *                              |
     *             dfs("dog", "hit", wordLevelMap, ["dog", "cog"], [])
     *                              |
     *            dfs("dot", "hit", wordLevelMap, ["dot", "dog", "cog"], [])
     *                              |
     *           dfs("hot", "hit", wordLevelMap, ["hot", "dot", "dog", "cog"], [])
     *                              |
     *           dfs("hit", "hit", wordLevelMap, ["hit", "hot", "dot", "dog", "cog"], [])
     */
    private static void dfs(String currWord, String beginWord,
                            Map<String, Integer> wordLevelMap,
                            List<String> seq, List<List<String>> allShortestPaths) {

        if (currWord.equals(beginWord)) {
            List<String> temp = new ArrayList<>(seq);   // MADE MISTAKE : I didn't create a new list here.
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

                // the new word should be 1 level less than the current word.
                // this condition helps us to avoid unnecessary paths
                if (wordLevelMap.containsKey(newWord) &&
                        wordLevelMap.get(newWord) == wordLevelMap.get(currWord) - 1) {

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
