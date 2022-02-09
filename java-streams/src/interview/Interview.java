package src.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Interview {

  // this question was asked to me in Enquero interview and I couldn't answer it.
  public static void main(String[] args) {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("Sushovan", 23, Arrays.asList("C++", "C#", "Java")));
    employees.add(new Employee("Sayan", 21, Arrays.asList("bengali", "Eng", "Java", "Python")));
    employees.add(new Employee("Subhojit", 27, Arrays.asList("Kotlin", "DBMS", "Java")));

    System.out.println("Show all distinct languages know by the employees.");

    System.out.println("APPROACH - 1");
    Set<String> distinctLanguages = new HashSet<>();

    employees.stream()
        .map(employee -> employee.languages)
        .forEach(distinctLanguages::addAll);

    System.out.println(distinctLanguages);

    // https://stackoverflow.com/questions/25147094/how-can-i-turn-a-list-of-lists-into-a-list-in-java-8
    System.out.println("APPROACH - 2 - using flatMap");
    Set<String> distinctLanguages1 = employees.stream()
        .map(employee -> employee.languages)
        .flatMap(List::stream)
        .collect(Collectors.toSet());
    System.out.println(distinctLanguages1);

  }

}

class Employee {

  String name;
  int age;
  List<String> languages;

  public Employee(String name, int age, List<String> languages) {
    this.name = name;
    this.age = age;
    this.languages = languages;
  }
}