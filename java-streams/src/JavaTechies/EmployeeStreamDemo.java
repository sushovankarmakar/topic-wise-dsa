package src.JavaTechies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.management.Query;

public class EmployeeStreamDemo {

  static List<Employee> employees = new ArrayList<>();

  /**
   * https://www.youtube.com/watch?v=AFmyV43UBgc&ab_channel=JavaTechies
   * https://github.com/javaTechiess/org/blob/main/src/javatechies/org/stream/EmployeeStreamDemo.java
   */
  public static void main(String[] args) {
    setEmployees();

    // Query 1 : How many male and female employees are there in the organization?
    //getEmployeeCountByGender();

    // Query 2 : Print the name of all departments in the organization?
    //getNamesOfAllDepartments();

    // Query 3 : What is the average age of male and female employees?
    //getAvgAgeOfAllEmployees();
    //getAvgAgeByGender();

    // Query 4 : Get the details of highest paid employee in the organization?
    //getHighestPaidEmployee();

    // Query 5 : Get the names of all employees who have joined after 2015?
    //getEmployeesJoinedAfter2015();

    // Query 6 : Count the number of employees in each department?
    //getEmpCountByDept();

    // Query 7 : What is the average salary of each department?
    //getAvgSalaryByDept();

    // Query 8 : Get the details of youngest male employee in the product development department?
    //getYoungestMaleEmpOfProductDevDept();

    // Query 9 : Who has the most working experience in the organization?
    //getMostExperiencedEmp();

    // Query 10 : How many male and female employees are there in the sales and marketing team?
    //getMaleAndFemaleEmpInSalesAndMarketing();

    // Query 11 : What is the average salary of male and female employees?
    //getAvgSalaryByGender();

    // Query 12 : List down the names of all employees in each department?
    //getEmpNamesByDept();

    // Query 13 : What is the average salary and total salary of the whole organization?
    //getAvgSalaryAndTotalSalary();

    // Query 14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
    //separate25YearsOldFromOthers();
  }

  private static void separate25YearsOldFromOthers() {
    System.out.println("Query 14 : Separate the employees who are younger or equal to 25 years "
        + "from those employees who are older than 25 years.");
    employees.stream()
        .collect(Collectors.partitioningBy(employee -> employee.getAge() > 25))
        .forEach((isAbove25, employees) -> {
          if (isAbove25) {
            System.out.println("Employees older than 25 years : ");
            employees.forEach(System.out::println);
          }

          if (!isAbove25) {
            System.out.println("Employees younger or equal to 25 years : ");
            employees.forEach(System.out::println);
          }
        });
  }

  private static void getAvgSalaryAndTotalSalary() {
    System.out.println("Query 13 : What is the average salary and total salary of the whole organization?");
    DoubleSummaryStatistics salaries = employees.stream()
        .collect(Collectors.summarizingDouble(Employee::getSalary));
    System.out.println(salaries);
    System.out.println(salaries.getAverage());
    System.out.println(salaries.getSum());
  }

  private static void getEmpNamesByDept() {
    /*Map<String, List<Employee>> empByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment));

    empByDept.forEach((dept, employees) -> {
      System.out.println("Department : " + dept);

      employees.stream()
          .map(Employee::getName)
          .collect(Collectors.toList())
          .forEach(System.out::println);
    });*/

    System.out.println("Query 12 : List down the names of all employees in each department?");
    employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment))
        .forEach((dept, employees) -> {
          System.out.println("Department : " + dept);

          employees.stream()
              .map(Employee::getName)
              .forEach(System.out::println);
        });

  }

  private static void getAvgSalaryByGender() {
    System.out.println("Query 11 : What is the average salary of male and female employees?");
    Map<String, Double> avgSalaryByGender = employees.stream()
        .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
    avgSalaryByGender.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getMaleAndFemaleEmpInSalesAndMarketing() {
    System.out.println("Query 10 : How many male and female employees are there in the sales and marketing team?");
    Map<String, Long> sales_and_marketing = employees.stream()
        .filter(employee -> employee.getDepartment().equalsIgnoreCase("Sales And Marketing"))
        .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
    sales_and_marketing.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getMostExperiencedEmp() {
    System.out.println("Query 9 : Who has the most working experience in the organization?");
    Employee mostExperiencedEmp = employees.stream()
        .min(Comparator.comparing(Employee::getYearOfJoining))
        .get();
    System.out.println(mostExperiencedEmp);
  }

  private static void getYoungestMaleEmpOfProductDevDept() {
    System.out.println("Query 8 : Get the details of youngest male employee in the product development department?");

    Employee youngestEmpOfProductDevDept = employees.stream()
        .filter(employee ->
            employee.getDepartment().equalsIgnoreCase("Product Development") &&
                employee.getGender().equalsIgnoreCase("Male"))
        .min(Comparator.comparingInt(Employee::getAge))
        .get();
    System.out.println(youngestEmpOfProductDevDept);
  }

  private static void getAvgSalaryByDept() {
    System.out.println("Query 7 : What is the average salary of each department?");
    Map<String, Double> avgSalaryByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment,
            Collectors.averagingDouble(Employee::getSalary)));

    avgSalaryByDept.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getEmpCountByDept() {
    System.out.println("Query 6 : Count the number of employees in each department?");
    Map<String, Long> empCountByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

    empCountByDept.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getEmployeesJoinedAfter2015() {
    System.out.println("Query 5 : Get the names of all employees who have joined after 2015?");
    employees.stream()
        .filter(employee -> employee.getYearOfJoining() > 2015)
        .forEach(System.out::println);
  }

  private static void getHighestPaidEmployee() {
    System.out.println("Query 4 : Get the details of highest paid employee in the organization?");
    System.out.println("--- approach - 1 ---");
    Employee employeeWithMaxSalary1 = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .findFirst()
        .get();
    System.out.println(employeeWithMaxSalary1);

    System.out.println("--- approach - 2 ---");
    Employee employeeWithMaxSalary2 = employees.stream()
        .max(Comparator.comparingDouble(Employee::getSalary))
        .get();
    System.out.println(employeeWithMaxSalary2);

    System.out.println("--- approach - 3 ---");
    Employee employeeWithMaxSalary3 = employees.stream()
        .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))
        .get();
    System.out.println(employeeWithMaxSalary3);
  }

  private static void getAvgAgeOfAllEmployees() {
    System.out.println("Average age of all employees : ");
    double avgAge = employees.stream()
        .mapToInt(Employee::getAge)
        .average()
        .getAsDouble();
    System.out.println("Average age of all employees : " + avgAge);
  }

  private static void getAvgAgeByGender() {
    System.out.println("What is the average age of male and female employees?");
    Map<String, Double> aveAgeByGender = employees.stream()
        .collect(Collectors.groupingBy(Employee::getGender,
            Collectors.averagingInt(Employee::getAge)));

    System.out.println("Average age by gender : ");
    aveAgeByGender.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getNamesOfAllDepartments() {
    System.out.println("Query 2 : Print the name of all departments in the organization?");
    System.out.println("---- APPROACH - 1 -----");
    Set<String> departments = employees.stream()
        .map(Employee::getDepartment)
        .collect(Collectors.toSet());

    departments.stream()
        .forEach(System.out::println);

    System.out.println("---- APPROACH - 2 ----");
    employees.stream()
        .map(Employee::getDepartment)
        .distinct()
        .forEach(System.out::println);
  }

  private static void getEmployeeCountByGender() {
    System.out.println("Query 1 : How many male and female employees are there in the organization?");

    Map<String, List<Employee>> employeeByGender = employees.stream()
        .collect(Collectors.groupingBy(Employee::getGender, Collectors.toList()));

    employeeByGender.forEach((key, value) -> {
      System.out.println(key);
      value.stream().forEach(System.out::println);
    });

    Map<String, Long> employeeCountByGender = employees.stream()
        .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));

    employeeCountByGender.forEach((key, value) -> {
      System.out.println(key + " " + value);
    });

  }

  private static void setEmployees() {
    employees.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
    employees.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
    employees.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
    employees.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
    employees.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
    employees.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
    employees.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
    employees.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
    employees.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
    employees.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
    employees.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
    employees.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
    employees.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
    employees.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
    employees.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
    employees.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
    employees.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
  }
}
