package src;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import src.User.UserBuilder;

//@RestController
public class PayUInterview {

  // User class

  /*@PostMapping("/v1/user")
  public Response createOrUpdateUser(@RequestBody UserDto user) { // 200

  }

  @GetMapping("/v1/user/{user-id}")
  public Response getUser(@PathVariable("user-id") String userId) { // 200
  }

  @DeleteMapping("/v1/user/{user-id}")
  public Response deleteUser(@PathVariable("user-id") String userId) { // 200 or 204

  }

  @GetMapping("/v1/user")
  public Response getAllUserByUsername(@RequestParam("username") String username) { // 200
    Response response = new Response();

  }*/

  // Response {
  /*  1.message
    2.
    List<User> users;
  }*/

  public static void main(String[] args) {
    /*User user = new User.UserBuilder("1", "name")
        .age(30)
        .build();

    System.out.println(user);*/
    System.out.println(collectMaxToffee(new int[]{6, 5}, 3));
  }

  // thief, steal toffiess,
  // boxed of toffee. 5 boxes. - each diff number of toffee

  // num of box
  // each box toffee array []
  // one box of toffee
  // when taken, replace the that box with half number of toffee
  // t unit of time.
  // 1 unit of time = 1 box of toffee.

  // 2 box
  // 6, 5
  // 3 unit time

  // 6 +
  // 3, 5

  // 6 + 5 = 11
  // 3, 2

  // 11 + 3 = 14
  // 1, 2


  // 1. sort the array
  // 2. for loop until that unit of time ends
  // 3. inside loop,
  //      i. take the largest value which is the last of the array. and replace that pos in with half.
  //      ii. sort the array.

  // time com : O(t * nlogn)

  // max-heap
  // time com : O(t * logn)
  // space : O(n)

  public static int collectMaxToffee(int[] boxes, int time) {

    PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
    for (int toffees : boxes) {
      queue.offer(toffees);
    }

    int totalToffeesCollected = 0;

    for (int i = 0; i < time; i++) {
      if (!queue.isEmpty()) {
        int maxVal = queue.poll();
        totalToffeesCollected += maxVal;
        queue.remove(maxVal);
        queue.offer(maxVal / 2);
      }
    }

    return totalToffeesCollected;
  }

}

class User {

  private String id;
  private String name;
  private Integer age;

  private User(UserBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.age = builder.age;
  }

  static class UserBuilder {

    private String id;
    private String name;
    private Integer age;

    public UserBuilder(String id, String name) {
      this.id = id;
      this.name = name;
    }

    public UserBuilder age(Integer age) {
      this.age = age;
      return this;
    }

    public User build() {
      User user = new User(this);
      return user;
    }

  }




}

