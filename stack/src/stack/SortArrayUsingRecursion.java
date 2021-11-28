package stack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.youtube.com/watch?v=AZ4jEY_JAVc&ab_channel=AdityaVerma
 */

public class SortArrayUsingRecursion {

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();
//    list.add(0);
//    list.add(1);
//    list.add(5);
//    list.add(2);

    list.add(2);
    list.add(3);
    list.add(7);
    list.add(6);
    list.add(4);
    list.add(9);
    list.add(5);

    list = sortUsingRecursion(list);
    for (Integer i : list) {
      System.out.print(i + " ");
    }
  }

  private static List<Integer> sortUsingRecursion(List<Integer> list) {

    // base condition
    if (list.size() == 1) {
      return list;
    }

    int lastElement = list.remove(list.size() - 1);
    list = sortUsingRecursion(list);

    list = insertAtSpecificIndex(list, lastElement);

    return list;
  }

  private static List<Integer> insertAtSpecificIndex(List<Integer> list, int valueToBeInserted) {

    // base condition
    if (list.isEmpty() || list.get(list.size() - 1) <= valueToBeInserted) {
      list.add(valueToBeInserted);
      return list;
    }

    int lastElement = list.remove(list.size() - 1);

    insertAtSpecificIndex(list, valueToBeInserted);

    list.add(lastElement);
    return list;
  }

}
