package src;

import java.util.Stack;

/**
 * https://practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1/
 * https://leetcode.com/problems/online-stock-span/ (Description is a bit different)
 *
 * https://www.youtube.com/watch?v=p9T-fE1g1pU&ab_channel=AdityaVerma (He told the similarity)
 *
 * https://leetcode.com/problems/online-stock-span/discuss/640358/JAVA-Solution-With-visualization-and-easy-explained!
 */
public class _901_StockSpanProblem {

  public static void main(String[] args) {
    int[] spans = calculateSpan(new int[]{100, 80, 60, 70, 60, 75, 85}, 7);

    for (int span : spans) {
      System.out.print(span + " "); // 1 1 1 2 1 4 6
    }
    System.out.println();
  }

  /**
   * SIMILAR PROBLEM :
   * find the nearest greatest element before it. === stock span problem(consecutive smaller or equal to before it.)
   */
  public static int[] calculateSpan(int[] price, int n) {

    int[] spans = new int[n];
    Stack<Pair> stack = new Stack();

    for (int i = 0; i < n; i++) {

      int currPrice = price[i];

      while (!stack.isEmpty() && stack.peek().price <= currPrice) {
        stack.pop();
      }

      spans[i] = stack.isEmpty() ? (i + 1) : (i - stack.peek().pos);

      stack.push(new Pair(currPrice, i));
    }
    return spans;
  }

  public static class Pair {
    int price;
    int pos;

    Pair (final int price, final int pos) {
      this.price = price;
      this.pos = pos;
    }
  }

  // ----------------------------------------------------------------------------------------------
  // LEETCODE version
  class StockSpanner {

    private Stack<Pair> stack;

    public StockSpanner() {
      stack = new Stack<>();
    }

    public int next(int price) {

      int currSpan = 1;

      // find next greater element on left
      while (!stack.isEmpty() && stack.peek().price <= price) {
        currSpan += stack.peek().span;
        stack.pop();
      }

      stack.push(new Pair(price, currSpan));

      return currSpan;
    }

    class Pair {
      int price;
      int span;

      Pair(int price, int span) {
        this.price = price;
        this.span = span;
      }
    }

    /**
     * Your StockSpanner object will be instantiated and called as such:
     * StockSpanner obj = new StockSpanner();
     * int param_1 = obj.next(price);
     */
  }

}
