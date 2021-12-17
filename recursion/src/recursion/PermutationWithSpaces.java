package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/permutation-with-spaces3627/1
 *
 * https://www.youtube.com/watch?v=1cspuQ6qHW0&ab_channel=AdityaVerma (Recursion tree with input, output method)
 */
public class PermutationWithSpaces {

  public static void main(String[] args) {
    List<String> permutations = permutation("abc");
    for (String permutation : permutations) {
      System.out.println(permutation);
    }
  }

  private static List<String> permutation(String input) {

    List<String> permutations = new ArrayList<>();
    getPermutations(input, "", permutations);

    return permutations;
  }

  private static void getPermutations(String input, String output, List<String> permutations) {

    /**
     * if we draw recursion tree, then we can see that when input length is 1, then we've only 1 option to left i.e
     * just add that to output. that is what we're doing here.
     * add the input directly to the output.
     *
     */
    if (input.length() == 1) {  // BASE CONDITION
      output += input;
      permutations.add(output);
      return;
    }

    char ch = input.charAt(0);

    /**
     * each position, we have two options,
     * 1. add this current character with space after that.
     * 2. add this current character with no space.
     */
    // option 1 : give space
    getPermutations(input.substring(1), output + ch + " ", permutations);

    // option 2 : give no space
    getPermutations(input.substring(1), output + ch, permutations);
  }

}
