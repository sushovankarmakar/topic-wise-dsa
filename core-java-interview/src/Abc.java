package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Abc {

  public static void main(String[] args) {

    List<Person> persons = new ArrayList<>();
    persons.add(new Person(1, "Suso", 23));
    persons.add(new Person(2, "Sayan", 24));
    persons.add(new Person(3, "Rohan", 25));

    persons.stream()
        .filter(person -> person.getAge() >= 24)
        .forEach(person -> System.out.println(person.getName()));

    Map<Integer, Person> map = new HashMap<>();
    map.put(1, new Person(1, "Suso", 23));
    map.put(2, new Person(2, "Sayan", 24));
    map.put(3, new Person(3, "Rohan", 25));

    map.forEach((key, value) -> System.out.println(value.getName() + ", " + value.getAge()));

    int[] array = {0, 1, 0, 1, 0, 1, 0, 1, 0, 1};

    int countZero = 0;
    for (int i = 0; i < array.length; i++) {

    }

  }
}

class Person {

  private Integer id;
  private String name;
  private Integer age;

  public Person(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }
}
