package src;

/**
 * https://practice.geeksforgeeks.org/problems/check-if-strings-are-rotations-of-each-other-or-not-1587115620/1
 * https://leetcode.com/problems/rotate-string/
 *
 * https://www.youtube.com/watch?v=dlt9Gyq6rb0&ab_channel=CodeLibrary-byYogesh%26Shailesh
 * https://www.youtube.com/watch?v=5e63aHK6Q-E&ab_channel=TECHDOSE
 *
 * https://www.geeksforgeeks.org/a-program-to-check-if-strings-are-rotations-of-each-other/
 */
public class _796_CheckIfStringsAreRotationsOfEachOther {

  public static void main(String[] args) {
    System.out.println(areRotations("geeksforgeeks", "forgeeksgeeks")); // true
    System.out.println(areRotations("mightandmagic", "andmagicmigth")); // false

    System.out.println(areRotations_2("geeksforgeeks", "forgeeksgeeks")); // true
    System.out.println(areRotations_2("mightandmagic", "andmagicmigth")); // false
  }

  /**
   * All rotations of A are contained in A+A. Thus, we can simply check whether B is a substring of A+A.
   * We also need to check A.length == B.length, otherwise we will fail cases like A = "a", B = "aa".
   */
  /**
   * Complexity Analysis
   * Time Complexity: O(N^2), where NN is the length of A.
   * Space Complexity: O(N), the space used building A+A.
   */
  public static boolean areRotations(String s1, String s2) {

    boolean isEqualLength = s1.length() == s2.length();
    boolean isSubString = (s1 + s1).contains(s2);

    return isEqualLength && isSubString;
  }

  public static boolean areRotations_2(String s1, String s2) {

    boolean isEqualLength = s1.length() == s2.length();
    return isEqualLength && isRotation(s1, s2);
  }

  /**
   * https://www.youtube.com/watch?v=5e63aHK6Q-E&ab_channel=TECHDOSE
   * (this method is explained in the 1st half of this video)
   *
   * go to each index and rotate the s1 string. and then match it with s2.
   */
  private static boolean isRotation(String s1, String s2) {

    // i is the index and it is the rotation point also.
    for (int i = 0; i < s1.length(); i++) {

      String rotatedString = s1.substring(i) + s1.substring(0, i);

      if (rotatedString.equals(s2)) {
        return true;
      }
    }
    return false;
  }

}
