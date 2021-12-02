package recursion;

/*
 * https://leetcode.com/problems/k-th-symbol-in-grammar/
 *
 * https://www.youtube.com/watch?v=5P84A0YCo_Y&ab_channel=AdityaVerma
 */

/*
 * Find middle point of each string,
 * if this k lies before th middle point, then go to previous row and return k-th position's bit.
 * if this k lies after the middle point, then go to previous row and return k-th position's bit with negation.
 *
 *
 * XOR the value with 1 gives us negation.
 * 0 ^ 1 = 1
 * 1 ^ 1 = 0
 */

public class _779_KthSymbolInGrammar {

  public static void main(String[] args) {
    System.out.println(kthGrammar_1(1, 1)); //  0
    System.out.println(kthGrammar_1(2, 1)); //  0
    System.out.println(kthGrammar_1(2, 2)); //  1
    System.out.println(kthGrammar_1(3, 1)); //  0

    System.out.println(kthGrammar_2(1, 1)); //  0
    System.out.println(kthGrammar_2(2, 1)); //  0
    System.out.println(kthGrammar_2(2, 2)); //  1
    System.out.println(kthGrammar_2(3, 1)); //  0
  }

  // approach - 1
  /*
   * In base condition, we can have if(N == 1 || K == 1) instead of if(N == 1 && K == 1) since,
   * K == 1 for any N, answer will be 0 only so it might save a few function calls also in this case.
   */
  public static int kthGrammar_1(int n, int k) {
    if (n == 1 || k == 1) return 0;   // base condition

    int midLength = (int) Math.pow(2, n - 1) / 2;

    if (k <= midLength) {
      return kthGrammar_1(n - 1, k);    // if k is in 1st half then,  it is same as what is above it
    } else {
      return kthGrammar_1(n - 1, k - midLength) ^ 1;  // because for every row,  2nd half is complement of 1st half
    }
  }

  // approach - 2
  // https://leetcode.com/problems/k-th-symbol-in-grammar/discuss/113697/My-3-lines-C%2B%2B-recursive-solution
  // https://leetcode.com/problems/k-th-symbol-in-grammar/discuss/365364/Java-easy-to-understand-recursion

  /**
   * The whole structure can be viewed a binary tree,
   * when a node is 0, their two children nodes are 0 and 1,
   * similarly, when a node is 1, two children nodes are 1 and 0.
   *
   * We can know whether the position of K is a left node or a right node by dividing 2.
   * If K is even, current node is right child, and its parent is the (K/2)th node in previous row;
   * else if K is odd, current node is left child and its parent is the ((K+1)/2)th node in previous row.
   *
   * The value of current node depends on its parent node,
   * without knowing its parent node value, we still cannot determine current node value.
   * That's why we need recursion,
   * we keep going previous row to find the parent node until reach the first row.
   * Then all the parent node value will be determined after the recursion function returns.
   */

  // think of the problem like this
/*        0
      /       \
     0          1
   /   \      /    \
   0     1    1      0
 / \     / \   / \   / \
 0  1   1   0  1  0  0  1
*/

  public static int kthGrammar_2(int n, int k) {

    if (n == 1) return 0;

    if (k % 2 != 0) { // k is odd, that means it is left child
      return kthGrammar_2(n - 1,  (k + 1) / 2) == 0 ? 0 : 1; // if parent of left child gives 0, then return 0 else 1.
    }
    else { // k is even, that means it is right child
      return kthGrammar_2(n - 1,  k / 2) == 0 ? 1 : 0;  // if parent of right child gives 0, then return 1 else 0.
    }
  }

}
