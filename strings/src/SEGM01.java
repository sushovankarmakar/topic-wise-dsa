package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SOLVED BY MYSELF
 * <p>
 * https://www.youtube.com/watch?v=5keU0gJ0XLo&ab_channel=CodeChef
 * <p>
 6
 001111110
 00110011
 000
 1111
 101010101
 101111111111
 */
public class SEGM01 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            String input = br.readLine();
            System.out.println(solution_0(input) ? "YES" : "NO");
        }
    }

    private static boolean solution_0(String input) {

        int count = 0;

        int i = 0;
        while (i < input.length()) {

            /**
             * if we found 1 at any index, then check for loop though till the end of that series of 1.
             * and at the end, increase count.
             */
            if (input.charAt(i) == '1') {

                while ((i + 1 < input.length()) && input.charAt(i + 1) == '1') {
                    i++;
                }
                count++;
            }

            if (count >= 2) return false;
            i++;
        }

        return count == 1;
    }

    // https://s3.amazonaws.com/codechef_shared/download/Solutions/LTIME47/Setter/SEGM01.cpp
    private static boolean solution_1(String input) {

        /**
         * thought process :
         * find the first and last index of 1.
         *
         * and then loop through from the first to last index of 1
         * and check if all the characters are 1 or not. if all the characters are 1, then it is true or false.
         */
        int n = input.length();
        int start = n, end = -1;
        for (int i = 0; i < n; ++i) {
            if (input.charAt(i) == '1') {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        if (end == -1) return false;

        for (int i = start; i <= end; ++i) {
            if (input.charAt(i) != '1')
                return false;
        }
        return true;
    }

    // https://www.youtube.com/watch?v=5keU0gJ0XLo&ab_channel=CodeChef
    private static boolean solution_2(String input) {

        int flag = 0;  // flag indicates if kya koi purana jo 1 ka series hain woh continue ho raha ki nhi.
        int count = 0; // number of groups
        int n = input.length();
        for (int i = 0; i < n; i++) {

            if (input.charAt(i) == '1') {
                if (flag == 1) {    // flag == 1 means, 1 ka series continue ho raha hain.
                    continue;
                } else {
                    flag = 1;
                    count++;
                }
            } else {
                flag = 0;
            }
        }

        return count == 1;
    }
}
