package src.JavaTechies;

class Employee {

  private int id;

  private String name;

  private int age;

  private String gender;

  private String department;

  private int yearOfJoining;

  private double salary;

  public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.department = department;
    this.yearOfJoining = yearOfJoining;
    this.salary = salary;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getGender() {
    return gender;
  }

  public String getDepartment() {
    return department;
  }

  public int getYearOfJoining() {
    return yearOfJoining;
  }

  public double getSalary() {
    return salary;
  }

  @Override
  public String toString() {
    return "Id : " + id + ", Name : " + name + ", age : " + age + ", Gender : " + gender + ", Department : "
        + department + ", Year Of Joining : " + yearOfJoining + ", Salary : " + salary;
  }
}