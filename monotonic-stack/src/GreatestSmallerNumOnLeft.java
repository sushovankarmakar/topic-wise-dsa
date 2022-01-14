package src;

import java.util.TreeSet;

/**
 * https://practice.geeksforgeeks.org/problems/smaller-on-left20360700/1
 *
 * https://www.geeksforgeeks.org/largest-element-smaller-than-current-element-on-left-for-every-element-in-array/
 *
 * took help from these two :
 * https://discuss.geeksforgeeks.org/comment/5524998772
 * https://ide.geeksforgeeks.org/fIpHiwNyQa
 *
 * https://www.geeksforgeeks.org/treeset-in-java-with-examples/ (Usage of lower method)
 * https://www.geeksforgeeks.org/treeset-lower-method-in-java/
 */
public class GreatestSmallerNumOnLeft {

  public static void main(String[] args) {
    int[] arr = new int[]{1, 2, 3, 2, 5};
    int[] values = greatestSmallestOnLeft(arr, 5);

    for (int val : values) {
      System.out.print(val + " ");  // -1, 1, 2, 1, 3
    }
    System.out.println();
  }

  public static int[] greatestSmallestOnLeft(int[] arr, int n) {

    int[] greatestSmallestNumOnLeft = new int[n];
    TreeSet<Integer> set = new TreeSet<>();

    for (int i = 0; i < n; i++) {
      int num = arr[i];
      set.add(num);

      if (set.lower(num) != null) {
        greatestSmallestNumOnLeft[i] = set.lower(num);
      } else {
        greatestSmallestNumOnLeft[i] = -1;
      }
    }

    return greatestSmallestNumOnLeft;
  }

}
