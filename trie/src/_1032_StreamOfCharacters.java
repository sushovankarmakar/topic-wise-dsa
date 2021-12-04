package src;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/stream-of-characters/
 *
 * SIMILAR QUESTION :
 * https://practice.geeksforgeeks.org/problems/trie-insert-and-search0651/1
 */
public class _1032_StreamOfCharacters {

  public static void main(String[] args) {
    String[] words = new String[]{"cd", "f", "kl"};
    StreamChecker obj = new StreamChecker(words);
    char[] letters = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
    for (char letter : letters) {
      System.out.println(obj.query(letter));
    }
  }

  /**
   * https://github.com/sushovankarmakar/GeeksforGeeks/blob/master/3.%20Medium/src/Trie_InsertSearchDelete.java (Insert and Search in trie)
   * https://www.youtube.com/watch?v=BBFrOzwAAQA&ab_channel=CodingDecoded (Very helpful explanation)
   * https://github.com/Sunchit/Coding-Decoded/blob/master/December2021/StreamOfCharacters.java
   *
   * https://assets.leetcode.com/users/images/ce2bdef1-e727-4f1b-aa40-015eb766fd3b_1597172741.6132298.png ( visualization of trie )
   *
   * *** CONCEPT ***
   * 1. Store the words in the trie with REVERSE ORDER,
   * 2. and check the query string from the END.
   */

  static class StreamChecker {

    class TrieNode {

      private Map<Character, TrieNode> children;
      private boolean isEndOfWord;

      TrieNode() {
        this.children = new HashMap<>();
        this.isEndOfWord = false;
      }
    }

    private TrieNode rootNode;

    private void insert(String word) {
      TrieNode currNode = rootNode;
      for (int i = word.length() - 1; i >= 0; i--) {
        char ch = word.charAt(i);
        TrieNode nextNode = currNode.children.get(ch);

        if (nextNode == null) {
          nextNode = new TrieNode();
          currNode.children.put(ch, nextNode);
        }
        currNode = nextNode;
      }
      currNode.isEndOfWord = true;
    }

    private boolean search(String word) {
      TrieNode currNode = rootNode;
      for (int i = word.length() - 1; i >= 0; i--) {
        char ch = word.charAt(i);
        TrieNode nextNode = currNode.children.get(ch);

        if (nextNode == null) {
          return false;
        }

        if (nextNode.isEndOfWord) { // this is the real trick. was getting error for not writing this line.
          return true;  // this checks the prefix of a word, if this is true then prefix of the word has been found
        }

        currNode = nextNode;
      }
      return currNode.isEndOfWord;  // if this is true then the whole word has been found
    }

    private StringBuilder sb;

    public StreamChecker(String[] words) {
      sb = new StringBuilder();
      rootNode = new TrieNode();
      for (String word : words) {
        insert(word);
      }
    }

    public boolean query(char letter) {
      sb.append(letter);
      return search(sb.toString());
    }
  }

}
