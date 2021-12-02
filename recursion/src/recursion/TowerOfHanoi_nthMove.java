package recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * https://practice.geeksforgeeks.org/problems/help-the-old-man3848/1
 *
 * https://practice.geeksforgeeks.org/problems/tower-of-hanoi-1587115621/1 (similar problem)
 */

public class TowerOfHanoi_nthMove {

  public static void main(String[] args) {
    List<Integer> moves = shiftPile(3, 4);
    System.out.println(moves.get(0) + " " + moves.get(1));

    List<Integer> moves1 = shiftPile(4, 5);
    System.out.println(moves1.get(0) + " " + moves1.get(1));
  }

  /*
    http://disq.us/p/2hq4hlz ( JAVA SOLUTION : Correct Answer )
   * Explain :
   *  why are you taking count array and why it isn't working by using an int??
   *  because Java is pass by value, and it is not possible to pass primitives by reference in Java.
   */

  static List<Integer> shiftPile(int numOfPlates, int nthMove) {

    List<Integer> moves = new ArrayList<>();
    int from = 1;
    int to = 3;
    int aux = 2;
    int[] count = new int[1];

    hanoi(numOfPlates, from, to, aux, nthMove, moves, count);

    return moves;
  }

  static void hanoi(int numOfPlates, int from, int to, int aux, int nthMove, List<Integer> moves, int[] count) {

    if (numOfPlates == 0) return;

    hanoi(numOfPlates - 1, from, aux, to, nthMove, moves, count);

    count[0]++;
    if (count[0] == nthMove) {
      moves.add(from);
      moves.add(to);
      return;
    }

    hanoi(numOfPlates - 1, aux, to, from, nthMove, moves, count);
  }
}
