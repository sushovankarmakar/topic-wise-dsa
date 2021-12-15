package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/generate-parentheses/
 * https://practice.geeksforgeeks.org/problems/generate-all-possible-parentheses/1
 * https://www.interviewbit.com/problems/generate-all-parentheses-ii/
 *
 *
 * https://www.youtube.com/watch?v=eyCj_u3PoJE&t=21s&ab_channel=AdityaVerma (Recursion tree is very good.)
 * https://www.youtube.com/watch?v=sz1qaKt0KGQ&ab_channel=BackToBackSWE
 * https://www.youtube.com/watch?v=s9fokUqJ76A&ab_channel=NeetCode
 *
 *
 * https://www.youtube.com/watch?v=wkDfsKijrZ8&ab_channel=takeUforward  (20. Valid Parentheses)
 */
public class _22_GenerateParentheses {

  public static void main(String[] args) {
    List<String> parenthesis = generateParenthesis(3);
    for (String s : parenthesis) {
      System.out.println(s);
    }
  }

  public static List<String> generateParenthesis(int n) {
    List<String> result = new ArrayList<>();
    String parenthesis = "";
    int left = n;
    int right = n;
    getParenthesis(result, parenthesis, left, right);
    return result;
  }

  private static void getParenthesis(List<String> result, String parenthesis, int left, int right) {
    if (left == 0 && right == 0) {
      result.add(parenthesis);
      return;
    }

    if (left > 0) {
      getParenthesis(result, parenthesis + "(", left - 1, right);
    }

    if (left < right) {
      getParenthesis(result, parenthesis + ")", left, right - 1);
    }
  }

}
