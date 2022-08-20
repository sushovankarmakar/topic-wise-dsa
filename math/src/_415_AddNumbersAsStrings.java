package src;

/**
 * https://practice.geeksforgeeks.org/problems/sum-of-numbers-or-number1219/1 (Here we need to remove leading zeros too)
 * https://practice.geeksforgeeks.org/problems/sum-of-large-numbers5827/1
 * https://leetcode.com/problems/add-strings/
 */
public class _415_AddNumbersAsStrings {

    public static void main(String[] args) {
        System.out.println(findSum("2500", "23")); // 2523
        System.out.println(findSum("25", "23")); // 48
        System.out.println(findSum("000000000", "00")); // 0
        System.out.println(findSum("7693", "4078842")); // 4086535
        System.out.println(findSum("57", "74")); // 131
        System.out.println(findSum("8", "95")); // 103
    }

    private static String findSum(String num1, String num2) {

        StringBuilder sb = new StringBuilder();

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        int digit1, digit2, digit, rem = 0;

        while (i >= 0 || j >= 0) {
            digit1 = (i >= 0) ? num1.charAt(i) - '0' : 0;
            digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;

            int sum = digit1 + digit2 + rem;

            digit = sum % 10;
            rem = sum / 10;
            sb.append(digit);

            i--;
            j--;
        }
        if (rem > 0) sb.append(rem);    // important to add rem. example : 8 + 95 = 103

        return removeLeadingZeros(sb);
    }

    private static String removeLeadingZeros(StringBuilder sb) {

        String output = sb.reverse().toString();
        int n = output.length();
        int k = 0;
        while (k < n && output.charAt(k) == '0') {
            k++;
        }

        return k == n ? "0" : output.substring(k, n);
    }
}
