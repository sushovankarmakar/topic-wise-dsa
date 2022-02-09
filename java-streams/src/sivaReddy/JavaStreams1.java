package src.sivaReddy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class JavaStreams1 {

  // https://www.youtube.com/watch?v=1pweZskNq7w&ab_channel=SivaReddy
  // https://javaconceptoftheday.com/solving-real-time-queries-using-java-8-features-employee-management-system/
  public static void main(String[] args) {

    /**
     * 1.Write a program to print employee details working in each department
     *
     * 2.Write a program to print employees count working in each department
     *
     * 3.Write a program to print active and inactive employees in the given collection
     *
     * 4.Write a program to print Max/Min employee salary from the given collection
     *
     * 5.Write a program to print the max salary of an employee from each department
     */

    List<Employee> employees = getEmployees();

    // 1.Write a program to print employee details working in each department
    //getEmployeesByDepartments(employees);

    // 2.Write a program to print employees count working in each department
    //getEmpCountByDept(employees);

    // 3.Write a program to print active and inactive employees in the given collection
    //getActiveAndInactiveEmp(employees);

    // 4.Write a program to print Max/Min employee salary from the given collection
    //getMinMaxSalaryOfEmp(employees);

    // 5.Write a program to print the max salary of an employee from each department
    //getMaxSalaryOfEmpByDepartment(employees);

    // 6. sum of salaries of all employees
    getSumOfSalariesOfAllEmployees(employees);

    // 7. sum of salaries of employees by departments
    getSumOfSalariesOfEmployeesByDepartments(employees);

    // 8. employees having salary more than 3k
    getEmployeesHavingSalaryAbove3K(employees);

    // 9. average salary by departments
    getAverageSalaryByDepartments(employees);
  }

  private static void getAverageSalaryByDepartments(List<Employee> employees) {
    Map<Integer, Double> avgSalaryByDept = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDeptId,
            Collectors.averagingDouble(Employee::getSalary)));
    avgSalaryByDept.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getEmployeesHavingSalaryAbove3K(List<Employee> employees) {
    Map<Boolean, List<Employee>> employeeSalaryAbove3K = employees.stream()
        .collect(Collectors.partitioningBy(employee -> employee.getSalary() > 3000));

    employeeSalaryAbove3K.forEach((key, value) -> {
      if (key.equals(true)) {
        System.out.println("Employees salary above 3k : ");
        value.forEach(System.out::println);
      }

      if (key.equals(false)) {
        System.out.println("Employees salary below 3k : ");
        value.forEach(System.out::println);
      }
    });
  }

  private static void getSumOfSalariesOfEmployeesByDepartments(List<Employee> employees) {
    Map<Integer, Integer> sumOfSalariesOfEmployeesByDepartments = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDeptId,
            Collectors.summingInt(Employee::getSalary)));

    sumOfSalariesOfEmployeesByDepartments.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getSumOfSalariesOfAllEmployees(List<Employee> employees) {
    Integer sumOfSalaries = employees.stream()
        .collect(Collectors.summingInt(Employee::getSalary));
    System.out.println("Sum of all salaries : " + sumOfSalaries);
  }

  private static void getMaxSalaryOfEmpByDepartment(List<Employee> employees) {
    Map<Integer, Optional<Employee>> maxSalaryOfEmpByDepartment = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDeptId,
            Collectors.reducing(BinaryOperator.maxBy(Comparator.comparing(Employee::getSalary)))));

    maxSalaryOfEmpByDepartment.forEach((key, value) -> System.out.println(key + " " + value.get()));
  }

  private static void getMinMaxSalaryOfEmp(List<Employee> employees) {

    System.out.println("APPROACH - 1");
    Employee employeeWithMinSalary = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary))
        .findFirst()
        .get();
    System.out.println("Emp with min salary : " + employeeWithMinSalary);

    Employee employeeWithMaxSalary = employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary).reversed())
        .findFirst()
        .get();
    System.out.println("Emp with max salary : " + employeeWithMaxSalary);

    System.out.println("APPROACH - 2");
    Employee employeeWithMinSalary1 = employees.stream()
        .min(Comparator.comparing(Employee::getSalary))
        .get();
    System.out.println("Emp with min salary : " + employeeWithMinSalary1);

    Employee employeeWithMaxSalary1 = employees.stream()
        .max(Comparator.comparing(Employee::getSalary))
        .get();
    System.out.println("Emp with max salary : " + employeeWithMaxSalary1);
  }

  private static void getActiveAndInactiveEmp(List<Employee> employees) {
    Map<String, List<Employee>> empByStatus = employees.stream()
        .collect(Collectors.groupingBy(Employee::getStatus, Collectors.toList()));
    empByStatus.forEach((key, value) -> {
      System.out.println(key);
      value.forEach(System.out::println);
    });
  }

  private static void getEmpCountByDept(List<Employee> employees) {
    Map<Integer, Long> empCountByDepartments = employees.stream()
        .collect(Collectors.groupingBy(Employee::getDeptId, Collectors.counting()));
    empCountByDepartments.forEach((key, value) -> System.out.println(key + " " + value));
  }

  private static void getEmployeesByDepartments(List<Employee> employees) {
    Map<Integer, List<Employee>> employeesByDepartments =
        employees.stream()
            .collect(Collectors.groupingBy(Employee::getDeptId, Collectors.toList()));

    employeesByDepartments.entrySet()
        .forEach(empByDept ->
            System.out.println(empByDept.getKey() + " " + empByDept.getValue()));
  }

  private static List<Employee> getEmployees() {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee(101, "siva", 101, "active", 2000));
    employees.add(new Employee(102, "reddy", 101, "active", 5000));
    employees.add(new Employee(103, "raju", 102, "inactive", 6000));
    employees.add(new Employee(104, "shivam", 102, "inactive", 4000));
    employees.add(new Employee(105, "bob", 103, "active", 3500));
    employees.add(new Employee(106, "alice", 103, "inactive", 3500));
    employees.add(new Employee(107, "srinu", 104, "active", 3500));
    employees.add(new Employee(108, "nam-ra", 105, "active", 1000));
    return employees;
  }

}

