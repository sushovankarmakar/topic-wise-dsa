package src;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JavaStreams {

  /**
   * Java Streams Interview Questions
   * https://www.youtube.com/playlist?list=PLFGoYjJG_fqp52WKlmgF4cV72KS9_9tih
   */
  public static void main(String[] args) {
    //sumOfNumbers();
    //avgOfNumbers();
    //squareOfNumbers();
    //printEvenAndOdd();
    //printNumbersStartsWithPrefix2();
    //printDuplicateValues();
    //findMinMax();
    //sortNums();
    //getOrSkipFirst5NumsOrLast5Nums();
    //findTop5Score();
    find2ndHighestOrLowestNum();
  }

  private static void sumOfNumbers() {
    // REDUCE : Print Sum Of All Numbers
    // https://www.geeksforgeeks.org/stream-reduce-java-examples/
    // https://mkyong.com/java8/java-8-stream-reduce-examples/
    // https://www.baeldung.com/java-stream-reduce

    List<Integer> list = Arrays.asList(1, 4, 5, 6, 22, 3, 90, 89, 2, 1, 3, 4, 55, 6);
    Integer sum = list.stream().reduce(0, (subTotal, element) -> subTotal + element);
    System.out.println("Sum of numbers : " + sum);  // 291
  }

  private static void avgOfNumbers() {

    // https://www.geeksforgeeks.org/stream-maptoint-java-examples/

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    int sum = numbers.stream().mapToInt(e -> e).sum();
    System.out.println(sum);

    double average = numbers.stream().mapToInt(e -> e).average().getAsDouble();

    System.out.println(average);
  }

  private static void squareOfNumbers() {
    List<Integer> numbers = Arrays.asList(1, 10, 20, 30, 15);

    double filteredAverage = numbers.stream()
        .map(num -> num * num)
        .filter(num -> num > 100)
        .mapToInt(num -> num)
        .average()
        .getAsDouble();
    System.out.println(filteredAverage);
    //squares.stream().forEach(System.out::println);
  }

  private static void printEvenAndOdd() {
    List<Integer> values = Arrays.asList(11, 2, 3, 45, 67, 9, 90, 87, 8, 2);

    System.out.println("-- EVEN --");
    values.stream()
        .filter(num -> num % 2 == 0)
        .forEach(System.out::println);

    System.out.println("-- ODD --");
    values.stream()
        .filter(num -> num % 2 != 0)
        .forEach(System.out::println);

  }

  private static void printNumbersStartsWithPrefix2() {
    List<Integer> nums = Arrays.asList(2, 222, -234, 567, 890, 432, 236, 211, 22);

    nums.stream()
        .map(num -> String.valueOf(num))
        .filter(num -> num.startsWith("2") || num.startsWith("-2"))
        .map(Integer::valueOf)
        .forEach(System.out::println);
  }

  private static void printDuplicateValues() {
    List<Integer> nums = Arrays.asList(1, 3, 10, 20, 30, 15, 1, 13, 1, 2, 2, 10, 30, 19, 3);

    // 1st approach

    System.out.println("Duplicate values : ");
    Set<Integer> dupValues = nums.stream()
        .filter(num -> Collections.frequency(nums, num) > 1)
        .collect(Collectors.toSet());
    System.out.println(dupValues);

    System.out.println("Non duplicate values : ");
    nums.stream()
        .filter(num -> Collections.frequency(nums, num) == 1)
        .forEach(System.out::println);

    // 2nd approach
    Set<Integer> dupNums = new HashSet<>();

    Set<Integer> duplicateValues = nums.stream()
        .filter(num -> !dupNums.add(num))
        .collect(Collectors.toSet());

    System.out.println("Duplicate values : ");
    System.out.println(duplicateValues);
  }

  private static void findMinMax() {
    List<Integer> nums = Arrays.asList(1, 3, 10, 20, 30, 15, 1, 13, 1, 2, 2, -10, 30, 19, 3, 0);

    Integer maxValue = nums.stream()
        .max((num1, num2) -> num1 - num2)
        .get();
    System.out.println(maxValue);

    Integer minValue = nums.stream()
        .min((num1, num2) -> num1 - num2)
        .get();
    System.out.println(minValue);
  }

  private static void sortNums() {
    List<Integer> nums = Arrays.asList(1, 3, 10, 20, 30, 15, 1, 13, 1, 2, 2, -10, 30, 19, 3, 0);

    List<Integer> sortedValuesInAsc =
        nums.stream()
            .sorted()
            .collect(Collectors.toList());
    System.out.println("Ascending order : " + sortedValuesInAsc);

    List<Integer> sortedValuesInDesc =
        nums.stream()
            .sorted((a, b) -> b - a)
            .collect(Collectors.toList());
    System.out.println("Descending order : " + sortedValuesInDesc);
  }

  private static void getOrSkipFirst5NumsOrLast5Nums() {
    List<Integer> nums = Arrays.asList(1, 3, 10, 20, 30, 15, 1, 13, 1, 2, 2, -10, 30, 19, 3, 0);

    List<Integer> firstFiveNums = nums.stream()
        .limit(5)
        .collect(Collectors.toList());
    System.out.println("First five nums : " + firstFiveNums);

    Integer sumOfFirstFiveNumbers = nums.stream()
        .limit(5)
        .reduce(0, (subTotal, number) -> subTotal + number);
    System.out.println("Sum of first five numbers : " + sumOfFirstFiveNumbers);

    List<Integer> skippedList = nums.stream()
        .skip(5)
        .collect(Collectors.toList());
    System.out.println("Skipped first five nums : " + skippedList);

    List<Integer> last5Numbers = nums.stream()
        .skip(nums.size() - 5L)
        .collect(Collectors.toList());
    System.out.println("Last five numbers : " + last5Numbers);
  }

  private static void findTop5Score() {
    List<Integer> scores = Arrays.asList(10, 2, 30, 33, 44, 37, 10, 23, 45, 34, 90, 37);

    List<Integer> top5Scores = scores.stream()
        .sorted(Collections.reverseOrder())
        .limit(5)
        .collect(Collectors.toList());
    System.out.println("Top five scores : " + top5Scores); // 90, 45, 44, 37, 37

    // https://howtodoinjava.com/java8/java-stream-distinct-examples/
    List<Integer> top5DistinctScores = scores.stream()
        .sorted(Collections.reverseOrder())
        .distinct()
        .limit(5)
        .collect(Collectors.toList());
    System.out.println("Top five distinct scores : " + top5DistinctScores); // 90, 45, 44, 37, 34
  }

  private static void find2ndHighestOrLowestNum() {
    List<Integer> nums = Arrays.asList(1, 3, 10, 20, 30, 15, 1, 13, 1, 2, 2, -10, 30, 19, 3, 0);
    Integer secondHighest = nums.stream()
        .sorted(Collections.reverseOrder())
        .distinct()
        .skip(1)
        .findFirst()
        .get();
    System.out.println(secondHighest); // 20

    Integer secondLowest = nums.stream()
        .sorted()
        .distinct()
        .skip(1)
        .findFirst()
        .get();
    System.out.println(secondLowest); // 0
  }
}
