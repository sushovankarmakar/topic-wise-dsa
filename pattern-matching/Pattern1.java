import java.util.ArrayList;
import java.util.List;

/**
 * https://practice.geeksforgeeks.org/problems/print-the-pattern1025/1
 */
public class Pattern1 {

    /**
     * 1*2*3*10*11*12
     * --4*5*8*9
     * ----6*7
     */
    /**
     * divide each line into 3 parts.
     * 1. left numbers
     * 2. right numbers
     * 3. dash part
     */
    static List<String> pattern(int n) {

        List<String> leftNumbers = new ArrayList<>(); // 1 * 2 * 3
        int maxVal = n * (n + 1);
        int num = 1;
        int times = n;
        for (int i = 1; i <= (maxVal / 2); i++) {
            String s = "";
            for (int j = 1; j <= times; j++) {
                s += (num + "*");
                num++;
            }
            leftNumbers.add(s);
            times--;
        }

        List<String> rightNumbers = new ArrayList<>();  // 10 * 11 * 12
        times = n;
        num = maxVal;
        for (int i = 1; i <= (maxVal / 2); i++) {
            String s = "";
            for (int j = 1; j <= times; j++) {
                s = (num + "*") + s;
                num--;
            }
            rightNumbers.add(s);
            times--;
        }

        List<String> dashes = new ArrayList<>();    // ----- dash part
        for (int i = 0; i < n; i++) {
            String dash = "";
            for (int j = 0; j < (i * 2); j++) {
                dash += "-";
            }
            dashes.add(dash);
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            String str = dashes.get(i) + leftNumbers.get(i) + rightNumbers.get(i);
            list.add(str.substring(0, str.length() - 1));
        }

        return list;
    }
}
