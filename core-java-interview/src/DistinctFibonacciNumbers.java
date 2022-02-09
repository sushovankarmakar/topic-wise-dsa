package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Distinct Fibonacci Numbers
 *
 * Problem Statement
 *
 * You are given infinite Fibonacci sequence:
 * Where F(0) = 0 and F(1) = X.
 * F(i) = ( F(i-1) + F(i-2) ) mod M, where i>=2;
 *
 * Your task is to count the number of distinct integers that appear in the sequence.
 *
 * Note: where A mod B denotes the remainder when A is divided by B.
 *
 *
 * Constraints
 *
 * •  1 <= T <= 10
 * •  1 <= M <= 1000
 * •  0 <= X < M
 * Input Format
 *
 * •  The first line of the input contains a single integer T denoting the number of test cases.
 * The description of T test cases follows.
 * •  A single line of each test case contains two space-separated integers X and M.
 * Output Format
 *
 * For each test case, print the number of distinct integers.
 *
 * Sample Input
 *
 * 3
 * 2 4
 * 5 45
 * 1 2
 *
 * Sample Output
 *
 * 2
 * 9
 * 2
 *
 * Explanation of Sample
 *
 * Sample case 1: Sequence look like {0, 2, 2, 0, 2 …}. The number of distinct integers that appear in the sequence is 2.
 *
 *
 *
 * Things to Note for the Candidate
 *
 * You can use your own IDE like Visual Studio Code, Eclipse,
 * or any other IDE that you are comfortable with to build your solution code.
 * The IDE provided on the platform is purely for submission. Avoid using the IDE for coding out the solution.
 * Test against any custom input in your own IDE. In the IDE provided on the platform,
 * you cannot pass custom test cases and see the output.
 * Use standard input and standard output in your program for the IDE to run the test cases smoothly against your code.
 * We are giving a sample problem statement along with a solution that will pass the test cases in the IDE.
 * - Sample Problem Statement  (Right Click and Open in New Tab to view.)
 */
public class DistinctFibonacciNumbers {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    String[] personsWithPowers = br.readLine().split("\\s+");
    int[] powers = new int[n];
    int sum = 0;
    for (int i = 0; i < n; i++) {
      powers[i] = Integer.parseInt(personsWithPowers[i]);
      sum += powers[i];
    }

    int totalPower = 0;
    for (int i = 0; i < n; i++) {
      totalPower += ((sum - powers[i]) * powers[i]);
    }
    System.out.println(totalPower);

    //int t = Integer.parseInt(br.readLine());

    //while (t-- > 0) {
      //String[] xm = br.readLine().split("\\s+");
      //int x = Integer.parseInt(xm[0]);
      //int m = Integer.parseInt(xm[1]);
      //System.out.println(x + " " + m);
      //System.out.println(findDistinctFibonacciNumbers(x, m));

      /*int n = Integer.parseInt(br.readLine());
      String[] personsWithPowers = br.readLine().split("\\s+");
      int[] powers = new int[n];
      int sum = 0;
      for (int i = 0; i < n; i++) {
        powers[i] = Integer.parseInt(personsWithPowers[i]);
        sum += powers[i];
      }

      int totalPower = 0;
      for (int i = 0; i < n; i++) {
        totalPower += ((sum - powers[i]) * powers[i]);
      }
      System.out.println(totalPower);*/
    //}
  }

  private static int findDistinctFibonacciNumbers(int x, int m) {
    return x == 0 ? 0 : (m / x);
  }

  /*int first = 0;
    int second = x;
    int third = 0;
    Set<Integer> distinctNums = new HashSet<>();
    distinctNums.add(first);

    while (true) {
      third = (first + second) % m;
      if (third == 0) {
        break;
      }
      //int rem = third % m;
      *//*if (distinctNums.contains(third)) {
        break;
      } else {

      }*//*
      distinctNums.add(third);
      first = second;
      second = third;
    }
    return distinctNums.size();*/

}

/**
 * Handshakes
 *
 * Problem Statement
 *
 * There are N people in a room. Everyone of them is infected by a virus and the power of virus inside everyone is given in the form of an array of size N. Every person in the room shakes hands with one another such that each and every person has shaken hands with exactly N-1 people.
 * Suppose there is person A with a virus of power a and another person B with virus of power b. If both of them shake hands with each other then person A will have a virus of power b accumulated on his hand and b will have virus of power a on his hand. Now A decides to shake hands with person C with a virus of power c. After both of them shake hands C will have virus of power a on his hands and A will have virus of power (b+c) accumulated on his hands.
 * After everyone in the room shakes hands with each other and a certain amount of time passes, the virus accumulated on their hands starts entering their body and then the pre-existing virus and the total accumulated virus mutate such that the power of the virus in the person will become the product of the powers of pre-existing and accumulated viruses. You need to find the total sum power of viruses in the room.
 *
 *
 * Constraints
 *
 * 1 N 10^6
 * 1 A[i] 10^6 where A[i] power of virus in a person.
 *
 *
 * Input Format
 *
 * First line contains a single integer N (number of people).
 * Second line contains N spaced integers representing the power of virus in each person.
 *
 *
 * Output Format
 *
 * Print the total sum power of viruses in the room.
 *
 *
 * Sample Input
 *
 * 4
 * 4 3 5 1
 *
 *
 * Sample Output
 * 118
 *
 * Explanation of Sample
 * Power of virus in first person => 4*(3+5+1) =>36
 * Power of virus in second person => 3*(4+5+1) =>30
 * Power of virus in third person => 5*(4+3+1) =>40
 * Power of virus in fourth person => 1*(4+3+5) =>12
 * Total virus in the room = 36+30+40+12 => 118
 *
 *
 * Things to Note for the Candidate
 *
 * You can use your own IDE like Visual Studio Code, Eclipse, or any other IDE that you are comfortable with to build your solution code.
 * The IDE provided on the platform is purely for submission. Avoid using the IDE for coding out the solution.
 * Test against any custom input in your own IDE. In the IDE provided on the platform, you cannot pass custom test cases and see the output.
 * Use standard input and standard output in your program for the IDE to run the test cases smoothly against your code. We are giving a sample problem statement along with a solution that will pass the test cases in the IDE. - Sample Problem Statement  (Right Click and Open in New Tab to view.)
 */

/**
 * Alice Coin Change
 *
 * Problem Statement:
 *
 *
 *
 * In the store, Alice likes to give a change of X Rupees after a purchase of a watch. But the only coins she can use have values (p, p+1, p+2 …. q). Is it possible to give out the change? If yes then what is the minimum value of q such that the change can be given.
 *
 * Constraints
 *
 * ● 1 <= T <= 1000
 *
 * ● 1 <= p <= 10^18
 *
 * ● 1 <= X <= 10^18
 *
 *
 *
 * Input Format:
 *
 * The first line contains one integer T—the number of test cases. Then T test cases follow. Each test case consists of one line. This line contains two numbers p and X. Output Format For each test case, print ‘YES’ if it is possible to give out the change and the next line print the minimum value of q. Otherwise, print ‘NO’.
 *
 *
 *
 * Sample Input
 *
 *
 *
 * 3 10 21 5 4 100 814
 *
 *
 *
 * Sample Output
 *
 * YES 11 NO YES 102
 *
 *
 *
 * Things to Note for the Candidate
 *
 * You can use your own IDE like Visual Studio Code, Eclipse, or any other IDE that you are comfortable with to build your solution code.
 * The IDE provided on the platform is purely for submission. Avoid using the IDE for coding out the solution.
 * Test against any custom input in your own IDE. In the IDE provided on the platform, you cannot pass custom test cases and see the output.
 * Use standard input and standard output in your program for the IDE to run the test cases smoothly against your code. We are giving a sample problem statement along with a solution that will pass the test cases in the IDE. - Sample Problem Statement  (Right Click and Open in New Tab to view.)
 */
