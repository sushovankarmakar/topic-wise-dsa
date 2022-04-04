package src;

// ORDER DOES NOT MATTER

/**
 * https://practice.geeksforgeeks.org/problems/count-ways-to-nth-stairorder-does-not-matter5639/1
 * <p>
 * https://www.geeksforgeeks.org/count-ways-reach-nth-stair/  (Look at Method 4 for 'Order does not matter')
 */
public class _70_ClimbingStairs_12Steps_OrderDoesNotMatter {

    public static void main(String[] args) {
        System.out.println(nthStair(0));  // 1
        System.out.println(nthStair(1));  // 1
        System.out.println(nthStair(2));  // 2 ways to reach 2nd stair. {1, 1} and {2}
        System.out.println(nthStair(3));  // 2 ways to reach 2nd stair. {1, 1, 1} and {1, 2}
        System.out.println(nthStair(4));  // 3 ways to reach 4th stair. {1, 1, 1, 1}, {1, 1, 2} and {2, 2}
        System.out.println(nthStair(5));  // 3 ways to reach 5th stair. {1, 1, 1, 1, 1}, {1, 1, 2, 1} and {1, 2, 2}
        System.out.println(nthStair(6));  // 4
        System.out.println(nthStair(7));  // 4
        System.out.println(nthStair(8));  // 5
        System.out.println(nthStair(9));  // 5
        System.out.println(nthStair(10)); // 6
        System.out.println(nthStair(11)); // 6
    }

    // Here n/2 is done to count the number 2's in n.
    // 1 is added for case where there is no 2.
    // eg: if n = 4 ans will be 3.
    // {1,1,1,1} set having no 2.
    // {1,1,2} ans {2,2} (n/2) sets containing 2.
    public static long nthStair(int n) {

        return (n / 2) + 1;
    }
}
