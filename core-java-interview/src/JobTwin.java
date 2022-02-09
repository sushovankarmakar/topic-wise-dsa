package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobTwin {

  public static void main(String[] args) {
    List<Integer> list1 = new ArrayList<>();
    list1.add(1);
    list1.add(2);
    list1.add(3);

    List<Integer> list2 = new ArrayList<>();
    list2.add(1);
    list2.add(2);
    list2.add(4);

    // O(N), O(N)
    // list1 - store values in a set
    // list2 - set.contains

    List<Integer> list = list1.stream().filter(num1 -> {
        return list2.contains(num1);
    }).collect(Collectors.toList());

    //System.out.println(list);

    // O(n^2) O(1)
    /*for (Integer num1  : list1) {



      for (Integer num2  : list2) {

        if (num1 == num2) {
          System.out.println(num1);
        }
      }
    }*/

    // sentence, how many times it has been repeated.

    String sentence = "His name is Sushovan Sushovan is a good student.";

    Map<String, Integer> freq = new HashMap<>();

    for (String word : sentence.trim().split("\\s+")) {
      freq.put(word, freq.getOrDefault(word, 0) + 1);
    }

    for (Map.Entry<String, Integer> entry : freq.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
  }
}


class ImmutableClass {

  private int id;
  private String name;
  private List<String> books;

  public ImmutableClass(final int id, final String name, final List<String> books) {

    this.id = id;
    this.name = name;
    List<String> booksCopy = new ArrayList<>();
    for (String book : books) {
      booksCopy.add(book);  // doing deep copy
    }
    this.books = booksCopy;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public List<String> getBooks() {

    List<String> booksCopy = new ArrayList<>();
    for (String book : this.books) {
      booksCopy.add(book);  // doing shallow copy
    }
    return booksCopy;
  }

}