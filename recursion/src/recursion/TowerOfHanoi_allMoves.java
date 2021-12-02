package recursion;

/*
 * https://practice.geeksforgeeks.org/problems/tower-of-hanoi-1587115621/1
 *
 * https://www.youtube.com/watch?v=hy-xYlsYHPI&ab_channel=ShivamMalhotra
 * https://www.youtube.com/watch?v=l45md3RYX7c&t=320s&ab_channel=AdityaVerma
 * https://www.youtube.com/watch?v=YstLjLCGmgg&t=18s&ab_channel=GeeksforGeeks
 */

public class TowerOfHanoi_allMoves {

  // print all moves of tower of hanoi.
  public static void main(String[] args) {
    System.out.println(toh(2, 1, 3, 2));
    System.out.println(toh(3, 1, 3, 2));
    System.out.println(toh(4, 1, 3, 2));
  }

  private static long toh(int numberOfDisks, int from, int to, int aux) {
    hanoi(numberOfDisks, from, to, aux);
    return (long) Math.pow(2, numberOfDisks) - 1;
  }

  private static void hanoi(int n, int from, int to, int aux) {

    if (n == 0) return;

    hanoi(n - 1, from, aux, to);

    System.out.println("move disk "+ n +" from rod "+ from +" to rod "+ to);

    hanoi(n - 1, aux, to, from);
  }
}
